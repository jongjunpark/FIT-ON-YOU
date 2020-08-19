<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class='main-logo'>
        <svg v-show='!isDark' id='logo' class='animated-logo' width="715" height="129" viewBox="0 0 715 129" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M3.36801 5.59999H89.48V28.64H29.576V55.28H82.856V78.032H29.576V122.96H3.36801V5.59999Z" stroke="black"
            stroke-width="5" />
          <path d="M130.123 5.59999V122.96H103.915V5.59999H130.123Z" stroke="black" stroke-width="5" />
          <path d="M246.788 28.784H208.628V122.96H182.42V28.784H144.26V5.59999H246.788V28.784Z" stroke="black"
            stroke-width="5" />
          <path
            d="M359.388 125.84C347.388 125.84 336.54 123.152 326.844 117.776C317.244 112.496 309.708 105.2 304.236 95.888C298.764 86.48 296.028 75.92 296.028 64.208C296.028 52.496 298.764 41.984 304.236 32.672C309.708 23.264 317.244 15.92 326.844 10.64C336.54 5.26399 347.388 2.57599 359.388 2.57599C371.388 2.57599 382.188 5.26399 391.788 10.64C401.484 15.92 409.068 23.264 414.54 32.672C420.012 41.984 422.748 52.496 422.748 64.208C422.748 75.92 420.012 86.48 414.54 95.888C409.068 105.2 401.484 112.496 391.788 117.776C382.188 123.152 371.388 125.84 359.388 125.84ZM359.388 102.224C366.396 102.224 372.732 100.592 378.396 97.328C384.06 94.064 388.476 89.552 391.644 83.792C394.908 77.936 396.54 71.408 396.54 64.208C396.54 56.912 394.908 50.384 391.644 44.624C388.476 38.864 384.06 34.352 378.396 31.088C372.732 27.824 366.396 26.192 359.388 26.192C352.38 26.192 346.044 27.824 340.38 31.088C334.716 34.352 330.252 38.864 326.988 44.624C323.82 50.384 322.236 56.912 322.236 64.208C322.236 71.408 323.82 77.936 326.988 83.792C330.252 89.552 334.716 94.064 340.38 97.328C346.044 100.592 352.38 102.224 359.388 102.224Z"
            stroke="black" stroke-width="5" />
          <path d="M438.321 5.59999H460.209L518.817 76.16V4.15999H545.025V121.52H523.137L464.529 50.96V122.96H438.321V5.59999Z"
            stroke="black" stroke-width="5" />
          <path
            d="M604.647 5.59999H630.855V70.976C630.855 80.384 633.303 87.968 638.199 93.728C643.191 99.392 649.911 102.224 658.359 102.224C666.807 102.224 673.479 99.392 678.375 93.728C683.367 87.968 685.863 80.384 685.863 70.976V5.59999H712.071V70.976C712.071 81.92 709.815 91.568 705.303 99.92C700.887 108.176 694.599 114.56 686.439 119.072C678.375 123.584 669.015 125.84 658.359 125.84C647.703 125.84 638.295 123.584 630.135 119.072C622.071 114.56 615.783 108.176 611.271 99.92C606.855 91.568 604.647 81.92 604.647 70.976V5.59999Z"
            stroke="black" stroke-width="5" />
        </svg>
        <svg v-show='isDark' id='logo2' class='animated-logo' width="715" height="129" viewBox="0 0 715 129" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M3.36801 5.59999H89.48V28.64H29.576V55.28H82.856V78.032H29.576V122.96H3.36801V5.59999Z" stroke="white"
            stroke-width="5" />
          <path d="M130.123 5.59999V122.96H103.915V5.59999H130.123Z" stroke="white" stroke-width="5" />
          <path d="M246.788 28.784H208.628V122.96H182.42V28.784H144.26V5.59999H246.788V28.784Z" stroke="white"
            stroke-width="5" />
          <path
            d="M359.388 125.84C347.388 125.84 336.54 123.152 326.844 117.776C317.244 112.496 309.708 105.2 304.236 95.888C298.764 86.48 296.028 75.92 296.028 64.208C296.028 52.496 298.764 41.984 304.236 32.672C309.708 23.264 317.244 15.92 326.844 10.64C336.54 5.26399 347.388 2.57599 359.388 2.57599C371.388 2.57599 382.188 5.26399 391.788 10.64C401.484 15.92 409.068 23.264 414.54 32.672C420.012 41.984 422.748 52.496 422.748 64.208C422.748 75.92 420.012 86.48 414.54 95.888C409.068 105.2 401.484 112.496 391.788 117.776C382.188 123.152 371.388 125.84 359.388 125.84ZM359.388 102.224C366.396 102.224 372.732 100.592 378.396 97.328C384.06 94.064 388.476 89.552 391.644 83.792C394.908 77.936 396.54 71.408 396.54 64.208C396.54 56.912 394.908 50.384 391.644 44.624C388.476 38.864 384.06 34.352 378.396 31.088C372.732 27.824 366.396 26.192 359.388 26.192C352.38 26.192 346.044 27.824 340.38 31.088C334.716 34.352 330.252 38.864 326.988 44.624C323.82 50.384 322.236 56.912 322.236 64.208C322.236 71.408 323.82 77.936 326.988 83.792C330.252 89.552 334.716 94.064 340.38 97.328C346.044 100.592 352.38 102.224 359.388 102.224Z"
            stroke="white" stroke-width="5" />
          <path d="M438.321 5.59999H460.209L518.817 76.16V4.15999H545.025V121.52H523.137L464.529 50.96V122.96H438.321V5.59999Z"
            stroke="white" stroke-width="5" />
          <path
            d="M604.647 5.59999H630.855V70.976C630.855 80.384 633.303 87.968 638.199 93.728C643.191 99.392 649.911 102.224 658.359 102.224C666.807 102.224 673.479 99.392 678.375 93.728C683.367 87.968 685.863 80.384 685.863 70.976V5.59999H712.071V70.976C712.071 81.92 709.815 91.568 705.303 99.92C700.887 108.176 694.599 114.56 686.439 119.072C678.375 123.584 669.015 125.84 658.359 125.84C647.703 125.84 638.295 123.584 630.135 119.072C622.071 114.56 615.783 108.176 611.271 99.92C606.855 91.568 604.647 81.92 604.647 70.976V5.59999Z"
            stroke="white" stroke-width="5" />
        </svg>
      </div>
      <p v-if='errormsgEmail' class='err-msg login-err-msg'>이메일이 존재하지 않습니다.</p>
      <p v-if='errormsgPwd' class='err-msg login-err-msg'>비밀번호가 틀렸습니다.</p>
      <div class='login-input-area'>
        <input @keydown.enter="loginHandler" v-model='email' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="text" class='login-email non-text'>
        <label class='login-email-label' for="login-email">이메일</label>
      </div>
      <div class='login-input-area'>
        <input @keydown.enter="loginHandler" v-model='password' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="password" class='login-password non-text'>
        <label class='login-password-label' for="login-password">비밀번호</label>
      </div>
      <div class="login-checkbox-area">
        <input type="checkbox" id='login-checkbox' @change="setLoginInf" v-model="loginContinue">
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

const dummy = ''
const Store='Store'
 function attachSignin(element) {
    dummy = element.id
  }
export default {
  name: 'Login',
  modules:{
    store:Store
  },
  data () {
    return {
      isDark: false,
      email: '',
      password: '',
      offLoginBtn: true,
      onLoginBtn: false,
      errormsgEmail: false,
      errormsgPwd: false,
      params: {
          client_id: "834514064011-lcb0a6a4b4bu6p22bho4r5g94tcvknjf.apps.googleusercontent.com"
      },
      renderParams: {
        width: 250,
        height: 50,
        longtitle: true
      },
      loginContinue: false
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
      Kakao.Auth.loginForm({
        success: function(authObj) {
          Kakao.Auth.setAccessToken(authObj.access_token);
          let ac_token = authObj.access_token;
          Kakao.API.request({
            url: '/v2/user/me',
            success: function(response) {
              let userData  = {

                // access_token : ac_token,
                // token_type : 'Bearer',
                nickname : response.kakao_account.profile.nickname,
                profile_image : response.kakao_account.profile.profile_image_url,
                email : response.kakao_account.email,
                gender : response.kakao_account.gender,
                // age_range : response.kakao_account.age_range
              };
              // ref.AC_USER(userData);
              // window.AC_USER(userData)

              axios.post('https://i3b304.p.ssafy.io/api/account/social/0',{
                nickname : response.kakao_account.profile.nickname,
                profile_image : response.kakao_account.profile.profile_image_url,
                email : response.kakao_account.email,
                gender : response.kakao_account.gender,
              })
              .then((data)=>{
                if(data.data.result.data=="1"){ // 이미 존재하는 경우
                  ref.$cookies.set('auth-token', data.data.auth_token)
                  ref.setToken(data.data.auth_token)
                  ref.sendUserInfo();
                  ref.setLoggedIn(true)
                  ref.AC_USER(data.data.userinfo);

                  setTimeout(() => {
                    ref.$router.push('/feed')
                  }, 100)
                }
                else if (data.data.result.data=="0"){
                  ref.AC_USER(userData);

                  ref.$router.push('/joinconfirm').catch(()=>{})
                }


              })
              .catch()
            },
            fail: () => {}
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
          client_id: '834514064011-lcb0a6a4b4bu6p22bho4r5g94tcvknjf.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
        });
        auth2.attachClickHandler('customBtn', {},
        function(googleUser) {
          let userData  = {
                // access_token : googleUser.getAuthResponse(true).access_token,
                // idToken : googleUser.getAuthResponse(true).id_token,
                nickname : googleUser.getBasicProfile().Ad,
                profile_image : googleUser.getBasicProfile().jK,
                email : googleUser.getBasicProfile().bu,
                // token_type : 'Bearer',
          }
          ref.AC_USER(userData);
        
          axios.post('https://i3b304.p.ssafy.io/api/account/social/0',{
                 nickname : googleUser.getBasicProfile().Ad,
                profile_image : googleUser.getBasicProfile().jK,
                email : googleUser.getBasicProfile().bu,
              })
              .then((data)=>{
                if(data.data.result.data=="1"){ // 이미 존재하는 경우
                  ref.$cookies.set('auth-token', data.data.auth_token)
                  ref.setToken(data.data.auth_token)
                  ref.sendUserInfo();
                  ref.setLoggedIn(true)
                  setTimeout(() => {
                    ref.$router.push('/feed')
                  }, 100)
                }
                else if (data.data.result.data=="0"){
                  ref.AC_USER(userData);
                  ref.$router.push('/joinconfirm').catch(()=>{})
                }


              })
              .catch()

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
      this.$router.push("/join").catch(()=>{})
    },
    pathFind() {
      this.$router.push("/find/password").catch(()=>{})
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
      axios.get('https://i3b304.p.ssafy.io/api/account/login',{
        params:{email:this.email,
                  password:this.password},
      }).then( response => {
        // 로그인 성공
        if(response.data.result==1){
          this.AC_USER(response.data);
          this.$cookies.set('auth-token', response.data.auth_token)
          this.setToken(response.data.auth_token)
          this.sendUserInfo();
          this.setLoggedIn(true);

          if (!this.loginContinue) {
            this.$cookies.set('still', 'on')
          } else {
            this.$cookies.set('still', 'off')
          }
          setTimeout(() => {
            this.$router.push('/feed')
          }, 100)
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
        this.isDark = true
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
        this.isDark = false
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