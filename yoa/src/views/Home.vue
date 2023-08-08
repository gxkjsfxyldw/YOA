<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">云E办</div>
                <div>
                    <el-button icon="el-icon-bell"
                               type="text"
                               size="normal"
                               style="margin-right: 8px; color: black"
                               @click="goChat">
                    </el-button>
                    <el-dropdown class="userInfo" @command="commandHandler">
                     <span class="el-dropdown-link">
<!--                         {{user.name}}<i><img :src="user.userFace" ></i>-->
                     </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                            <el-dropdown-item command="setting">设置</el-dropdown-item>
                            <el-dropdown-item command="logout">注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>

            <el-container>
                <el-aside width="200px" style="overflow: hidden">
<!--                  获取到整个路由router，然后进行遍历里面的每一个路由选项-->
                    <el-menu router unique-opened>
                        <el-submenu :index="index+''"
                                    v-for="(item,index) in routes" :key="index"
                                    v-if="!item.hidden">
<!--                         v-if="!item.hidden" 非登录路由时才展示-->
                            <template slot="title">
                                <i :class="item.iconCls" style="color: #1accff;margin-right: 5px"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="children.path"
                                          v-for="(children,indexj) in item.children" :key="indexj">
                                {{children.name}}
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>

                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right"
                                   v-if="this.$router.currentRoute.path!='/home'">
                        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
                        欢迎来到云E办系统！
                    </div>

                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>

        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem('user'))
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            },
            // user(){
            //     return this.$store.state.currentAdmin;
            // }
        },
        methods: {
            goChat(){
                this.$router.push('/chat');
            },
            commandHandler(command) {
                if (command == 'logout') {
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        //注销登录
                        this.postRequest('/logout');
                        //清空用户信息
                        window.sessionStorage.removeItem('tokenStr');
                        window.sessionStorage.removeItem('user');
                        //清空菜单
                        this.$store.commit('initRoutes', []);
                        //跳转到登录页
                        this.$router.replace('/')
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消该操作'
                        });
                    });
                }

                if(command=='userinfo'){
                    this.$router.push('/userinfo');
                }

            }
        }
    }
</script>

<style>
    .homeHeader {
        background: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 新宋体;
        color: white;
    }

    .homeHeader .userInfo {
        cursor: pointer;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 新宋体;
        color: #409eff;
        padding-top: 50px;
    }

    .homeRouterView {
        margin-top: 10px;
    }

</style>
