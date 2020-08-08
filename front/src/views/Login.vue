<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <h1 class='main-logo'>FIT ON U</h1>
      <p v-if='errormsgEmail' class='err-msg'>이메일이 존재하지 않습니다.</p>
      <p v-if='errormsgPwd' class='err-msg'>비밀번호가 틀렸습니다.</p>
      <div class='login-input-area'>
        <input v-model='email' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="text" class='login-email non-text'>
        <label class='login-email-label' for="login-email">이메일</label>
      </div>
      <div class='login-input-area'>
        <input v-model='password' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="password" class='login-password non-text'>
        <label class='login-password-label' for="login-password">비밀번호</label>
      </div>
      <div class="login-checkbox-area">
        <input type="checkbox" id='login-checkbox' @change="setLoginInf">
        <label for="login-checkbox"><i class="far fa-check-circle"></i></label>
        <label for="login-checkbox" class='login-inf-label'> 로그인상태 유지</label>
      </div>
      <div v-if='offLoginBtn' class='btn login-btn'>로그인</div>
      <div v-if='onLoginBtn' @click='loginHandler' class='btn on-login-btn'>로그인</div>
      <div class="social-area">
        <div class="btn google-btn" id="customBtn">
          <i class="fab fa-google"></i>
          <!-- <img class="google-img" src="../assets/images/google-mini.png"/> -->
        </div>
        <div class="btn kakao-btn" @click="loginWithKakao">
          <i class="fas fa-comment"></i> 
          <!-- <img class="kakao-img" src="../assets/images/kakao-mini.png"/> -->
        </div>
      </div>
      <div class="login-link-area">
        <p class='go-find'><span @click='pathFind' class="go-find-in">비밀번호 찾기</span></p>
        <p class='go-join'><span @click='pathJoin' class="go-join-in">회원가입</span></p>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import "../components/css/login.css"
import axios from 'axios';
import { mapState, mapGetters, mapMutations, mapActions  } from 'vuex'

Kakao.init('713af847cf1784de91646f5cb2455cbf');


var userData={
  
}

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
      errormsgEmail: false,
      errormsgPwd: false,
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
    },
    flag() {
      this.defaultDark()
    },
  },
  mounted() {
    window.addEventListener("google-loaded", this.startApp);
    this.defaultDark()    
  },
  computed: {
    ...mapState(['flag']),
    ...mapGetters([]),
  }, 
  methods:{
    ...mapMutations(['setToken', 'setUser', 'setLoggedIn']),
    ...mapActions(['AC_USER', 'sendUserInfo']),

    loginWithKakao(){
      let ref= this;
      console.log(ref);
      Kakao.Auth.loginForm({
        success: function(authObj) {
          Kakao.Auth.setAccessToken(authObj.access_token);
          let ac_token = authObj.access_token;
          Kakao.API.request({
            url: '/v2/user/me',
            success: function(response) {
              let userData  = {

                access_token : ac_token,
                token_type : 'Bearer',
                nickname : response.kakao_account.profile.nickname,
                profile_image : response.kakao_account.profile.profile_image_url,
                email : response.kakao_account.email,
                gender : response.kakao_account.gender,
                age_range : response.kakao_account.age_range
              };
              ref.AC_USER(userData);
              console.log(ref.$store.state.user);
              // window.AC_USER(userData)

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
    },
   
    startApp() {
      let ref = this;

      gapi.load('auth2', function(){
        let auth2 = gapi.auth2.init({
          client_id: '834514064011-bqc7hgss1hil5965mdbgf57420u04lvv.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
        });
        auth2.attachClickHandler('customBtn', {},
        function(googleUser) {
          const userData  = {
                access_token : googleUser.getAuthResponse(true).access_token,
                idToken : googleUser.getAuthResponse(true).id_token,
                nickname : googleUser.getBasicProfile().Cd,
                profile_image : googleUser.getBasicProfile().fL,
                email : googleUser.getBasicProfile().zu,
                token_type : 'Bearer',
          }
          ref.AC_USER(userData);
          console.log(ref.$store.state.user);

        }, function(error) {
        });
      });
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
      axios.get('http://localhost:8080/api/account/login',{
        params:{email:this.email,
                  password:this.password},
      }).then( response => {
        // 로그인 성공
        if(response.data.result==1){
          this.AC_USER(response.data);
          console.log(response.data)
          this.$cookies.set('auth-token', response.data.auth_token)
          this.setToken(response.data.auth_token)
          this.sendUserInfo();
          this.setLoggedIn(true);
          this.$router.push('/feed')
        }
        // 이메일 없음
        else if(response.data.result==-1){
          this.errormsgEmail = true;
          this.errormsgPwd = false;
          const ERROR = document.querySelector('.btn')
          const ERRORMESSAGE1 = document.querySelector('.login-email-label')
          const ERRORMESSAGE2 = document.querySelector('.login-password-label')
          const ERRORMESSAGE3 = document.querySelector('.login-email')
          const ERRORMESSAGE4 = document.querySelector('.login-password')
          ERROR.classList.add('on-login-btn-error')
          ERRORMESSAGE1.classList.add('login-email-label-error')
          ERRORMESSAGE2.classList.remove('login-password-label-error')
          ERRORMESSAGE3.classList.add('login-email-error')
          ERRORMESSAGE4.classList.remove('login-password-error')
        }
        
        // 비밀번호 틀림
        else if(response.data.result==2){
          this.errormsgPwd = true;
          this.errormsgEmail = false;
          const ERROR = document.querySelector('.btn')
          const ERRORMESSAGE1 = document.querySelector('.login-email-label')
          const ERRORMESSAGE2 = document.querySelector('.login-password-label')
          const ERRORMESSAGE3 = document.querySelector('.login-email')
          const ERRORMESSAGE4 = document.querySelector('.login-password')
          ERROR.classList.add('on-login-btn-error')
          ERRORMESSAGE1.classList.remove('login-email-label-error')
          ERRORMESSAGE2.classList.add('login-password-label-error')
          ERRORMESSAGE3.classList.remove('login-email-error')
          ERRORMESSAGE4.classList.add('login-password-error')
        }

      });
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const H1TAG = document.querySelectorAll('h1')
      const PTAG = document.querySelectorAll('p')
      const LABEL = document.querySelectorAll('label')
      const SPAN = document.querySelectorAll('span')
      const GOOGLE = document.querySelector('.google-btn')
      const KAKAO = document.querySelector('.kakao-btn')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }
        LABEL[3].classList.add('font-dark')
        for (let i=0; i<SPAN.length ; i++) {
          SPAN[i].classList.add('font-dark')
        }
        GOOGLE.classList.add('social-btn-dark')
        KAKAO.classList.add('social-btn-dark')
      } else {
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
        LABEL[3].classList.remove('font-dark')
        for (let i=0; i<SPAN.length ; i++) {
          SPAN[i].classList.remove('font-dark')
        }
        GOOGLE.classList.remove('social-btn-dark')
        KAKAO.classList.remove('social-btn-dark')
      }
    },
    setLoginInf() {
      const LOGINF = document.querySelector('.fa-check-circle')
      if (event.target.checked) {
        LOGINF.classList.add('on-login-checkbox')
      } else {
        LOGINF.classList.remove('on-login-checkbox')
      }
    }
  },
  /* eslint-enable */
  }
</script>