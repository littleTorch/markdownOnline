import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/mainL/login',
  },

  {
    path: '/mainL',
    name: 'mainL',
    redirect: '/mainL/login',
    component: () => import('../views/login.vue'),
    children: [
      {
        path: 'login',
        name: 'login',

        component: () => import('../views/login/loginmain.vue'),
      }, {
        path: 'register',
        name: 'register',
        component: () => import('../views/login/register.vue')
      }
    ]

  }
  ,
  {
    path: '/Notes',
    name: 'Notes',
    redirect: '/Notes/newFile',
    component: function () {
      return import('../views/noteMain.vue')
    },
    children: [
      {
        path: "/Notes/newFile",
        name: "新建笔记",
        component: function () {
          return import('@/components/newFile.vue')
        }
      }, {
        path: "/Notes/",
        name: "MyNote",
        component: () => import('@/components/markdonwMain.vue')
      }
    ]
  }
]


const router = new VueRouter({
  routes,
  mode: 'history',
  linkActiveClass: "active",
})


export default router
