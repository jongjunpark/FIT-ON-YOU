import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'
// import cookies from 'vue-cookies'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

const state = {
  user: '',
  certifNum: '',
  pwdUser: '',
  authToken: '',
  isLoggedIn: false,
  flag: false,
  articledata: '',
  hashSearchList: '',
  userSearch: '',
  isMyFeed: false,
  isBookMark: false,
  isFollowing: false,
  isFollower: false,
  isCuration: false,
  isMe: false,
  isWrite: true,
  isSelectBar: false,
  otherUserName: '',
}

Vue.use(Vuex)

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions
})

