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
  setToken(state, token) {
    state.authToken = token
  },
  setLoggedIn(state, data) {
    state.isLoggedIn = data
  },
  setUser(state, data) {
    state.user = data
  },
  setAuthUser(state, data) {
    console.log(state)
    // var uri = data;
    // var uri_enc = encodeURIComponent(uri);
    // var uri_dec = decodeURIComponent(uri_enc);
    // var res = uri_dec;
    window.$cookies.set('auth-nickname', data)
  }


}