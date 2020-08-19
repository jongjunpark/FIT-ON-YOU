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
import Settings from '../views/Settings.vue'
import NewPassword from '../views/NewPassword.vue'
import DirectMessage from '../views/DirectMessage.vue'
import ProfileInform from '../views/ProfileInform.vue'
import Alarm from '../views/Alarm.vue'
import DM from '../views/DM.vue'
import FeedWrite from '../views/FeedWrite.vue'
import OtherUser from '../views/OtherUser.vue'
// import SocialJoin from '../views/SocialJoin.vue'
import JoinConfirm from '../views/JoinConfirm.vue'
import PageNotFound from '../views/PageNotFound.vue'
import ResellMessage from '../views/ResellMessage.vue'
import axios from 'axios'
import JoinConfirmNew from '../views/JoinConfirmNew.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        next()
      }    }
  },
  {
    path: '/join',
    name: 'Join',
    component: Join,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        if (Vue.$cookies.get('agree'))
        { Vue.$cookies.remove('agree')
          next() }
        else {
          next('/')
        }
      }    }
  },
  {
    path: '/joinconfirmnew',
    name: 'JoinConfirmNew',
    component: JoinConfirmNew,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        next()
      }    }
  },
  {
    path: '/feed',
    name: 'Feed',
    component: Feed,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
    
  },
  {
    path: '/community',
    name: 'Community',
    component: Community,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/find/password',
    name: 'FindPassword',
    component: FindPassword,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        next()
      }    }
  },
  {
    path: '/find/password/ok',
    name: 'FindPasswordOk',
    component: FindPasswordOk,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        if (Vue.$cookies.get('agree'))
        { Vue.$cookies.remove('agree')
          next()
        }
        else {
          next('/find/password')
        }
      }    }
  },
  {
    path: '/find/password/passwordchange',
    name: 'PasswordChange',
    component: PasswordChange,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/feed')
      } else {
        if (Vue.$cookies.get('agree'))
        { Vue.$cookies.remove('agree')
          next()
        }
        else {
          next('/find/password')
        }
      }    }
  },
  {
    path: '/profileedit',
    name: 'ProfileEdit',
    component: ProfileEdit,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/newpassword',
    name: 'NewPassword',
    component: NewPassword,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/directmessage/:roomname/:othername',
    name: 'DirectMessage',
    component: DirectMessage,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/profileinform/:name',
    name: 'ProfileInform',
    component: ProfileInform,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        let param = to.params.name
        axios.get('https://i3b304.p.ssafy.io/api/account/checkNickname',{ 
          params: {
            nickname: param
            }
        }).then(data => {
          if (data.data.data == "exist") {
            next()
          } else {   
            next('/404')    
          }
        })
        .catch()
      }   
     }
  },
  {
    path: '/alarm',
    name: 'Alarm',
    component: Alarm,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    }
  },
  {
    path: '/dm',
    name: 'DM',
    component: DM,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }    
    }
  },
  {
    path: '/write',
    name: 'FeedWrite',
    component: FeedWrite,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }
    }
  },
  {
    path: '/resellMessage',
    name: 'resell',
    component: ResellMessage,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }
    }
  }, 
  {
    path: '/otheruser/:nickname',
    name: 'OtherUser',
    component: OtherUser,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        let param = to.params.nickname
        let data = Vue.$cookies.get('auth-nickname');
        let uri = data;
        let uri_enc = encodeURIComponent(uri);
        let uri_dec = decodeURIComponent(uri_enc);
        let res = uri_dec;
        axios.get('https://i3b304.p.ssafy.io/api/account/checkNickname',{ 
          params: {
            nickname: param
            }
        }).then(data => {
          if (data.data.data == "exist") {
            next()
          } else {   
            next('/404')    
          }
        })
        .catch(function(){
            })
        if (param == res) {
          next('/profileedit')
        } else {
          next()
        }
      }   
     }
  },
  {
    path: '/joinconfirm',
    name: 'JoinConfirm',
    component: JoinConfirm,
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    name: 'PageNotFound',
    component: PageNotFound,
  },
  {
    path: '/resellmessage/:roomname/:othername',
    name: 'ResellMessage',
    component: ResellMessage,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/')
      } else {
        next()
      }   
     }
  },
  
    // beforeEnter(to, from, next) {
    //   if (!Vue.$cookies.isKey('auth-token')) {
    //     next('/')
    //   } else {
    //     next()
    //   }
    // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router