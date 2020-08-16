

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
  },
  setMyFeed(state) {
    state.isMyFeed = true; state.isBookMark = false;
    state.isFollower = false; state.isFollowing = false;
    state.isCuration = false;
  },
  setBookMark(state) {
    state.isMyFeed = false; state.isBookMark = true;
    state.isFollower = false; state.isFollowing = false;
    state.isCuration = false;
  },
  setFollower(state) {
    state.isMyFeed = false; state.isBookMark = false;
    state.isFollower = true; state.isFollowing = false;
    state.isCuration = false;
  },
  setFollowing(state) {
    state.isMyFeed = false; state.isBookMark = false;
    state.isFollower = false; state.isFollowing = true;
    state.isCuration = false;
  },
  setCuration(state) {
    state.isMyFeed = false; state.isBookMark = false;
    state.isFollower = false; state.isFollowing = false;
    state.isCuration = true;
  },
  setIsMe(state, data) {
    state.isMe = data
  },
  setOtherUser(state, data) {
    state.otherUserName = data
  },
  setIsWrite(state, data) {
    state.isWrite = data
  },
  setIsSelectBar(state, data) {
    state.isSelectBar = data
  },
  setDmProfileImg(state,data){
    state.dmProfileImg=data;
  }
}