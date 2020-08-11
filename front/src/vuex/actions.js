import axios from 'axios'

export default {
  AC_USER:({commit},payload)=>{
    commit('LOGIN',payload)
  },
  sendUserInfo(context) {
    if (context.state.authToken) {
      axios.get('http://localhost:8080/api/account/token',{
        params:{jwt : context.state.authToken}
      }).then((res)=>{
        console.log(res);
        context.commit('setUser', res.data.userInfo.User)
        context.commit('setAuthUser', res.data.userInfo.User.nickname)
      })
      .catch(()=>{

      }); 
    }}


}
