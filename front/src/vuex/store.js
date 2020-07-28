import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'
// import cookies from 'vue-cookies'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:{},
  },
  getters: {
    user(state) {
        return state.user;
    },
  },
  mutations:{
    LOGIN(state,data){
      state.user=data;
      },
    LOGOUT(state){
      state.user=null;
    }
  },
  actions:{
    AC_USER:({commit},payload)=>{
      commit('LOGIN',payload)
    }
  }
})