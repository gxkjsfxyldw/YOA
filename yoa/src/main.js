import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false
//图标
import 'font-awesome/css/font-awesome.css'

// 引入ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 安装ElementUI
Vue.use(ElementUI,{size:'small'});
Vue.config.productionTip = false
//把封装好的请求api以插件的形式在这里引入，就不用每个组件都import了
import {postRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {initMenu} from "../src/utils/menus"

//插件形式使用请求
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;



//全局导航守卫 (路由跳转时加载菜单)
//每一次点击路由之前，都会先经过守卫
router.beforeEach((to,from,next)=>{
  if(window.sessionStorage.getItem('tokenStr')){
    //加载左侧菜单  自己写的组件
    initMenu(router,store);
    //判断用户信息是否存在
    if(!window.sessionStorage.getItem('user')){
      return getRequest('/admin/info').then(resp => {
        if(resp){
          //存入用户信息
          window.sessionStorage.setItem('user',JSON.stringify(resp));
          //store.commit('INIT_ADMIN',resp);
          next();
        }
      })
    }
    next();
  }else {
    if(to.path == '/'){
      next();
    }else {
      //重定向回刚刚直接输入路径的路由
      next('/?redirect='+to.path);
    }
  }
})



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
