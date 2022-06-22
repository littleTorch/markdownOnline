import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    markPath: ''
  },
  getters: {
  },
  mutations: {
    updatePath(state, path) {
      state.markPath = path
    }
  },
  actions: {
  },
  modules: {
  }
})
