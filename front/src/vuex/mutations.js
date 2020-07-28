export default {
  LOGIN(state, data) {
    state.user = data;
    },
  LOGOUT(state){
    state.user = null;
  },
  confirmPwd(state, data){
    state.certifNum = data;
  },
  findUserPWd(state, data) {
    state.pwdUser = data;
  },

}