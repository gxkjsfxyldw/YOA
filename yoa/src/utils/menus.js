import {getRequest} from "./api";

//用来将后端发送过来的菜单数据封装前端需求的形式
//初始化菜单的方法  初始化之后放到路由里面router，还有全局状态管理store
export const initMenu = (router, store) => {
    //如果已经初始化就不要继续了
    if (store.state.routes.length > 0) {
        return;
    }
    //请求后端接口
    getRequest('/system/cfg/menu').then(data => {
        if (data) {
            //格式化Router
            let fmtRoutes = formatRoutes(data);
            //添加到router
            router.addRoutes(fmtRoutes);
            //将数据存入vuex中
            store.commit('initRoutes', fmtRoutes);
            //连接WebSocket
            store.dispatch('connect');
        }
    })

};

export const formatRoutes = (routes) => {
    let fmtRoutes = [];
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            iconCls,
            children
        } = router;
        //路由排序
        if (children && children instanceof Array) {
            //递归
            children = formatRoutes(children);
        }
        //单独对某个路由格式化
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            children: children,
            component(resolve) {
                if (component.startsWith('Home')) {
                    require(['../views/' + component + '.vue'], resolve);
                } else if (component.startsWith('Emp')) {
                    require(['../views/resource/' + component + '.vue'], resolve);
                } else if (component.startsWith('Per')) {
                    require(['../views/staff/' + component + '.vue'], resolve);
                } else if (component.startsWith('Sal')) {
                    require(['../views/salary/' + component + '.vue'], resolve);
                } else if (component.startsWith('Sta')) {
                    require(['../views/statistics/' + component + '.vue'], resolve);
                } else if (component.startsWith('Sys')) {
                    require(['../views/system/' + component + '.vue'], resolve);
                }
            }
        }
        fmtRoutes.push(fmRouter);
    });
    return fmtRoutes;
};
