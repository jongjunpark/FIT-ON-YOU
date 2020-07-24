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
      <div v-if='onLoginBtn' @click='checkLoginInf' class='btn on-login-btn'>로그인</div>
      <div class="social-area">
        <div class="btn google-btn">
          <GoogleLogin :params="params" :onSuccess="onSuccess" :onFailure="onFailure">login</GoogleLogin>
        </div>
        <div class="btn kakao-btn">
          <KakaoLogin
            class="kakao-img"
            api-key="713af847cf1784de91646f5cb2455cbf"
            :on-success="onSuccessKakao"
            :on-failure="onFailureKakao"
          />
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
import "../components/css/login.css"
import KakaoLogin from 'vue-kakao-login'
import GoogleLogin from 'vue-google-login'


let onSuccess= (data) => {
  console.log(data)
  
  console.log("success")
}
let onFailure = (data) => {
  console.log(data)
  console.log("failure")
}


export default {
  name: 'Login',
  data () {
    return {
      tokens:{
        kakaoAcToken:'',
      },
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
      }
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
  components: {
    KakaoLogin,
    GoogleLogin
  },
  methods: {
      onSuccessKakao : (data) => {
      
      console.log("success")
      console.log(data)
      
    },
    onFailureKakao : (data) => {
      console.log(data)
      console.log("failure")
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
    onSuccess,
    onFailure,
    
  }
}
</script>
