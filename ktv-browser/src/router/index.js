import Vue from 'vue'
import Router from 'vue-router'
import Login from '@C/Login'
import Music from '@C/Music'
import Temp from '@C/Temp'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/music',
      name: 'Music',
      component: Music
    },
    {
      path: '/temp',
      name: 'Temp',
      component: Temp
    }
  ]
})
