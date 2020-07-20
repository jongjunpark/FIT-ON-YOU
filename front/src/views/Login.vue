<template>
  <div class='wrap'>
    <div class='container'>
      <h1 class='logo'>Logo</h1>
      <p v-if='errormsg' class='err-msg'>계정 혹은 비밀번호가 틀렸습니다.</p>
      <div class='input-area'>
        <input v-model='email' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="text" id='email' class='non-text'>
        <label class='email-label' for="email">계정이름</label>
      </div>
      <div class='input-area'>
        <input v-model='password' @keyup.enter='checkLoginInf' @keyup="checkLoginBtn" type="password" id='password' class='non-text'>
        <label class='password-label' for="password">비밀번호</label>
      </div>
      <div class="checkbox-area">
        <input type="checkbox" id='checkbox'>
        <label for="checkbox"> 로그인상태 유지</label>
      </div>
      <div v-if='offLoginBtn' class='btn login-btn'>로그인</div>
      <div v-if='onLoginBtn' @click='checkLoginInf' class='btn on-login-btn'>로그인</div>
      <div class="social-area">
        <div class="btn google-btn">
          <img class='google-img' src="../assets/images/google2.png" alt="google">
        </div>
        <div class="btn kakao-btn">
          <img class="kakao-img" src="../assets/images/kakao.png" alt="kakao">
        </div>
      </div>
      <div class="link-area">
        <p @click='pathFind' class='go-find'>비밀번호 찾기</p>
        <p @click='pathJoin' class='go-join'>회원가입</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      email: '',
      password: '',
      offLoginBtn: true,
      onLoginBtn: false,
      errormsg: false,
    }
  },
  watch: {
    email: function() {
      this.setEmailClass();
    },
    password: function() {
      this.setPasswordClass();
    }
  },
  methods: {
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
      const label = document.querySelector('.email-label')
      if (this.email) {
        label.classList.add('is-email')
      } else {
        label.classList.remove('is-email')
      }
    },
    setPasswordClass() {
      const label = document.querySelector('.password-label')
      if (this.password) {
        label.classList.add('is-password')
      } else {
        label.classList.remove('is-password')
      }
    }
  }
}
</script>

<style scoped>
.wrap {
  padding: 0 30px;
}

@media (min-width: 1200px) {
    .wrap {
    max-width: 580px;
    width: 100%;
    margin: 0 auto;
  }
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.logo {
  margin: 50px 0;
}

.err-msg {
  margin: 0 0 8px 0;
  color: #FF3636;
  width: 100%;
  font-weight: 700;
}

.checkbox-area {
  width: 100%;
  margin-bottom: 20px;
}

.input-area {
  width: 100%;
  margin-bottom: 20px;
  position: relative;
}

.social-area {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.link-area {
  color: #686868;
  text-align: left;
  width: 100%;
}

.input-area #email {
  height: 60px;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #D5D5D5;
  background-color: #D5D5D5;
  padding: 15px 0 0 15px;
  box-sizing: border-box;
  color: #686868;
  font-family: "Noto Sans KR", sans-serif !important;
}

.input-area #password {
  height: 60px;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #D5D5D5;
  background-color: #D5D5D5;
  padding: 15px 0 0 15px;
  box-sizing: border-box;
  color: #686868;
  font-family: "Noto Sans KR", sans-serif !important;
}

.input-area label {
  position: absolute;
  top: 50%;
  left: 20px;
  transform: translate(0, -50%);
  color: #686868;
}

.input-area label.is-email {
  position: absolute;
  top: 15px !important;
  left: 10px !important;
}

.input-area label.is-password {
  position: absolute;
  top: 15px !important;
  left: 10px !important;
}

.input-area #email:focus, #password:focus {
  background-color: white;
  font-weight: 700;
  color: black;
}

.input-area #email.non-text:focus + label{
  transition: ease 0.3s;
  position: absolute;
  top: 15px;
  left: 10px;
}

.input-area #email.non-text:not(:focus) + label{
  transition: ease 0.3s;
  position: absolute;
  top: 50%;
  left: 20px;
}

.input-area #password.non-text:focus + label{
  transition: ease 0.3s;
  position: absolute;
  top: 15px;
  left: 10px;
}

.input-area #password.non-text:not(:focus) + label{
  transition: ease 0.3s;
  position: absolute;
  top: 50%;
  left: 20px;
}

.btn {
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  cursor: pointer;
  height: 50px;
  line-height: 50px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.btn.login-btn {
  color: #B0B0B0;
  border: 2px solid #B0B0B0;
  background-color: white;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
  cursor:default;
}

.btn.on-login-btn {
  color: white;
  background-color: #FF3636;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}

.btn.google-btn {
  color: #B0B0B0;
  border: 1px solid #B0B0B0;
  background-color: white;
  width: 50%;
  margin-right: 3px;
}

.btn.kakao-btn {
  color: #B0B0B0;
  border: 1px solid #B0B0B0;
  background-color: #ffe812;
  width: 50%;
  margin-left: 3px;
}

.btn.google-btn .google-img {
  height: 100%;
  padding: 10px;
  box-sizing: border-box;
}

.btn.kakao-btn .kakao-img {
  height: 100%;
  padding: 16px;
  box-sizing: border-box;
}

.go-join, .go-find {
  cursor: pointer;
}
</style>