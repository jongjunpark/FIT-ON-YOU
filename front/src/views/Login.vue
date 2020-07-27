<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <h1 class='main-logo'>Logo</h1>
      <p v-if='errormsg' class='err-msg'>계정 혹은 비밀번호가 틀렸습니다.</p>
      <div class='login-input-area'>
        <input v-model='email' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="text" class='login-email non-text'>
        <label class='login-email-label' for="login-email">이메일</label>
      </div>
      <div class='login-input-area'>
        <input v-model='password' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="password" class='login-password non-text'>
        <label class='login-password-label' for="login-password">비밀번호</label>
      </div>
      <div class="login-checkbox-area">
        <input type="checkbox" class='login-checkbox'>
        <label for="login-checkbox"> 로그인상태 유지</label>
      </div>
      <div v-if='offLoginBtn' class='btn login-btn'>로그인</div>
      <div v-if='onLoginBtn' @click='loginHandler' class='btn on-login-btn'>로그인</div>
      <div class="social-area">
        <div class="btn google-btn" id="customBtn">
          <img class="google-img" src="../assets/images/google2.png"/>
        </div>
        <div class="btn kakao-btn" @click="loginWithKakao"> 
          <img class="kakao-img" src="../assets/images/kakao.png"/>
        </div>
      </div>
      <div class="login-link-area">
        <p @click='pathFind' class='go-find'>비밀번호 찾기</p>
        <p @click='pathJoin' class='go-join'>회원가입</p>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import "../components/css/login.css"
<<<<<<< HEAD
=======
import GoogleLogin from 'vue-google-login'
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
import axios from 'axios';
import { mapActions, mapGetters } from 'vuex'

Kakao.init('713af847cf1784de91646f5cb2455cbf');

<<<<<<< HEAD
var userData={
  
}
=======
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1


const Store='Store'
 function attachSignin(element) {
    console.log(element.id);
    
  }
export default {
  name: 'Login',
  modules:{
    store:Store
  },
  data () {
    return {
      email: '',
      password: '',
      offLoginBtn: true,
      onLoginBtn: false,
      errormsg: false,
      params: {
          client_id: "834514064011-bqc7hgss1hil5965mdbgf57420u04lvv.apps.googleusercontent.com"
      },
      renderParams: {
        width: 250,
        height: 50,
        longtitle: true
      },
    }
  },
  watch: {
    email() {
      this.setEmailClass();
    },
    password() {
      this.setPasswordClass();
    }
  },
<<<<<<< HEAD
  
=======
  components: {
    GoogleLogin,
  },
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
  computed:{
    ...mapGetters([
      'user',
    ]),
    
    
  },
  mounted() {
<<<<<<< HEAD
    window.addEventListener("google-loaded", this.startApp);    
  }, 
  methods:{
    ...mapActions(['AC_USER']),
    
    loginWithKakao(){
      let ref=this;
=======
        window.addEventListener("google-loaded", this.startApp);
  }, 
  methods:{
    ...mapActions(['AC_USER']),

    loginWithKakao :()=> {
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
      Kakao.Auth.loginForm({
        success: function(authObj) {
          Kakao.Auth.setAccessToken(authObj.access_token);
          let ac_token = authObj.access_token;
          Kakao.API.request({
            url: '/v2/user/me',
            success: function(response) {
<<<<<<< HEAD
              userData  = {
=======
              const userData  = {
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
                access_token : ac_token,
                token_type : 'Bearer',
                nickname : response.kakao_account.profile.nickname,
                profile_image : response.kakao_account.profile.profile_image_url,
                email : response.kakao_account.email,
                gender : response.kakao_account.gender,
                age_range : response.kakao_account.age_range
              }
<<<<<<< HEAD
              ref.AC_USER(userData);
              console.log(ref.$store.state.user);
              // window.AC_USER(userData)
=======
              console.log(userData);
              this.AC_USER(userData);
              console.log(this.$store.state.user);

>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
            },
            fail: function(error) {
                console.log(error);
            }
          });
        },
        fail: function(err) {
          alert(JSON.stringify(err))
        },
      })
<<<<<<< HEAD
    },
   
    startApp() {
      let ref = this;
=======
    },  
   
    startApp() {
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
      gapi.load('auth2', function(){
        let auth2 = gapi.auth2.init({
          client_id: '834514064011-bqc7hgss1hil5965mdbgf57420u04lvv.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
<<<<<<< HEAD
=======
          // Request scopes in addition to 'profile' and 'email'
          //scope: 'additional_scope'
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
        });
        auth2.attachClickHandler('customBtn', {},
        function(googleUser) {
          const userData  = {
                access_token : googleUser.getAuthResponse(true).access_token,
                idToken : googleUser.getAuthResponse(true).id_token,
<<<<<<< HEAD
                nickname : googleUser.getBasicProfile().Cd,
                profile_image : googleUser.getBasicProfile().fL,
                email : googleUser.getBasicProfile().zu,
                token_type : 'Bearer',
          }
          ref.AC_USER(userData);
          console.log(ref.$store.state.user);
=======
                token_type : 'Bearer',
                nickname : googleUser.getBasicProfile().Cd,
                profile_image : googleUser.getBasicProfile().fL,
                email : googleUser.getBasicProfile().zu,
          }

          console.log(userData);
          this.AC_USER(userData);
          console.log(this.$store.state.user);
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
      });
<<<<<<< HEAD

=======
>>>>>>> 699e418346eece8f6e83a27bc04997d2fdfbe3d1
    },
    

    
    onLoginButton() {
      if (this.email) {
        if (this.password) {
          this.offLoginBtn = false
          this.onLoginBtn = true
        } else {
          this.offLoginBtn = true
          this.onLoginBtn = false
        }
      } else {
        this.offLoginBtn = true
        this.onLoginBtn = false
      }
    },
    checkLoginBtn() {
      this.onLoginButton()
    },
    checkLoginInf() {
      this.errormsg = true
    },
    pathJoin() {
      this.$router.push("/join")
    },
    pathFind() {
      this.$router.push("/find/password")
    },
    setEmailClass() {
      const label = document.querySelector('.login-email-label')
      if (this.email) {
        label.classList.add('is-email')
      } else {
        label.classList.remove('is-email')
      }
    },
    setPasswordClass() {
      const label = document.querySelector('.login-password-label')
      if (this.password) {
        label.classList.add('is-password')
      } else {
        label.classList.remove('is-password')
      }
    },


    loginHandler() { 
      console.log(this.email);
      console.log(this.password);
      axios.get('http://localhost:8080/account/login',{
        params:{email:this.email,
                  password:this.password},
      }).then((response)=>{
        // 로그인 성공
        if(response.data.result==1){
          console.log(response.data);
          this.AC_USER(response.data);

          console.log(this.$store.state.user);
        }
        // 이메일 없음
        else if(response.data.result==-1){
          alert("이메일이 존재하지 않습니다.");
        }
        
        // 비밀번호 틀림
        else if(response.data.result==2){
          alert("비밀번호가 틀립니다.");
        }

      });
    },

    
  }
  /* eslint-enable */
}
</script>
