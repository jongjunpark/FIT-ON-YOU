import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'
// import cookies from 'vue-cookies'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: '',
    certifNum: '',
    pwdUser: '',
  },
  getters,
  mutations,
  actions
})

