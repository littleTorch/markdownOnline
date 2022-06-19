import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'


import SlideVerify from 'vue-monoplasty-slide-verify';
import leMarkdownEditor from 'le-markdown-editor'

Vue.use(leMarkdownEditor)
Vue.use(SlideVerify);
Vue.config.productionTip = false



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
