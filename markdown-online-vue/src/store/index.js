import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    markPath: '',
    name:"用户名"
  },
  getters: {

  },
  mutations: {
    updatePath(state, path) {
      state.markPath = path
    },
    setname(state,name){
      state.name=name;
    }
  },
  actions: {

  },
  modules: {
  }
})
