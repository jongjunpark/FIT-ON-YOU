import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Join from '../views/Join.vue'
import Feed from '../views/Feed.vue'
import Search from '../views/Search.vue'
import Community from '../views/Community.vue'
import FindPassword from '../views/FindPassword.vue'
import FindPasswordOk from '../views/FindPasswordOk.vue'
import PasswordChange from '../views/PasswordChange.vue'
import ProfileEdit from '../views/ProfileEdit.vue'
import ProfileInform from '../views/ProfileInform.vue'

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
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/community',
    name: 'Community',
    component: Community
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
    path: '/find/password/passwordchange',
    name: 'PasswordChange',
    component: PasswordChange
  },
  {
    path: '/profileedit',
    name: 'ProfileEdit',
    component: ProfileEdit
  },
  {
    path: '/profileinform',
    name: 'ProfileInform',
    component: ProfileInform
  }
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router