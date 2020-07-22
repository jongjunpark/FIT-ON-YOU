import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Join from '../views/Join.vue'
import Feed from '../views/Feed.vue'
import FindPassword from '../views/FindPassword.vue'
import FindPasswordOk from '../views/FindPasswordOk.vue'
import JoinProfile from '../views/JoinProfile.vue'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/feed',
    name: 'Feed',
    component: Feed
  },
  {
    path: '/find/password',
    name: 'FindPassword',
    component: FindPassword
  },
  {
    path: '/find/password/ok',
    name: 'FindPasswordOk',
    component: FindPasswordOk
  },
  {
    path: '/test',
    name: 'JoinProfile',
    component: JoinProfile
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
