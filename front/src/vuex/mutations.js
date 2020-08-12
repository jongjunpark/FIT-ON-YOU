

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
  },
  setUserIntro(state, data){
    state.user.selfintroduce=data
  },
  setUserNick(state,data){
    state.user.nickname=data;
  },
  setFlag(state, data) {
    state.flag = data
  },
  setArticledata(state, data) {
    state.articledata = data;
  },
  setArticleimgs(state, data) {
    state.articleimgs = data;
  },
  setArticletags(state, data) {
    state.articletags = data;
  },
  setHashSearch(state, data) {
    let hashString = ''
    for (let i=0; i<data.length; i++) {
      hashString += `#${data[i]} `
    }
    state.hashSearchList = hashString;
  },
  setUserSearch(state, data) {
    let data_enc = encodeURIComponent(data);
    let data_dec = decodeURIComponent(data_enc);
    state.userSearch = data_dec
  }
}