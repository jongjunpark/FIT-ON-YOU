import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// const state = {
//     isUser: false,
// }

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