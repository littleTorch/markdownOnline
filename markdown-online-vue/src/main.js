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

//过滤器
// router.beforeEach((to, from, next) => {
  //to 将要进入的路由
  // from 将要离开的路由
  // next 让路由继续执行
  // let token = sessionStorage.getItem("username");
  // if (to.path === '/mainL/login'||to.path === '/mainL/register'||to.path==='/AdminmainL/login') {
  //   console.log(to.path)
  //   next();
  // }else if(!token){
  //   next({path:"/mainL/login"});
  // }
  // next();
// })

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
