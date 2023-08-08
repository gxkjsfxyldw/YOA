import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  //可以理解为一个全局的对象，可以保存所有组件的公共的数据
  state: {
    //菜单
    routes: [],
    sessions: [],
    admins: [],
    currentSessionId: -1,
    filterKey: '',
    stomp: null
  },
  getters: {
  },
  //可以修改state里面对应值的方法，同步执行
  mutations: {
    //初始化路由
    initRoutes(state, data) {
      state.routes = data;
    },
  },
  //可以修改state里面对应值的方法，异步执行
  actions: {
  },
  modules: {
  }
})
