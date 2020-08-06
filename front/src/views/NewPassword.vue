<template>
  <div class='wrap'>
  <h1 class='newpassword-head'>새로운 비밀번호로 <br> 변경해주세요.</h1>
   <div class='wrap-container'>
    <div class='lock-profile-img'>
      <img class='profile-img' src="../assets/images/pngflow.com.png" alt="">
    </div>
    <div class="passwordchange-input-area">
      <label for="">현재 비밀번호</label>
      <input @focus="activeInput" @blur='deactiveInput' v-model='nowPassword' type="password" class="common-input-join" placeholder="password" >
    </div>
    <div class="passwordchange-input-area">
      <label for="">새 비밀번호</label>
      <input @focus="activeInput" @blur='deactiveInput' v-model='password' type="password" class="common-input-join" placeholder="password" >
      <p v-if="passwordErrorMsg" class='err-msg join-err-msg'>영문,숫자 포함 8 자리이상이어야 합니다.</p>
      <p v-if="passwordSuccessMsg" class='suc-msg join-suc-msg'>사용 가능한 비밀번호 입니다.</p> 
    </div>
    <div class="passwordchange-input-area">
      <label for="">새 비밀번호 확인</label>
      <input @focus="activeInput" @blur='deactiveInput' v-model='passwordConfirm' type="password" class="common-input-join" placeholder="password confirm">
      <p v-if="pwErrMsg" class='err-msg join-err-msg'>비밀번호가 일치하지 않습니다.</p>
      <p v-if="pwSucMsg" class='suc-msg join-suc-msg'>비밀번호가 일치합니다.</p>
    </div>
    <div v-show='!pwdFlag' class='btn passwordchange-btn'>변경하기</div>
    <div @click='changePassword' v-show='pwdFlag' class='btn on-passwordchange-btn'>변경하기</div>
   </div>
  </div>
</template>

<script>
import "../components/css/passwordchange.css"
import "../components/css/newpassword.css"
import PasswordValidator from 'password-validator'
import axios from 'axios'
import Swal from 'sweetalert2'
import { mapState, mapMutations, mapActions } from 'vuex'
export default {
  name: 'NewPassword',
  data() {
    return {
      nowPassword:'',
      password: '',
      passwordConfirm: '',
      passwordErrorMsg: false,
      passwordSuccessMsg: false,
      pwErrMsg: false,
      pwSucMsg: false,
      passwordSchema: new PasswordValidator(),
      pwdFlag: false,
    }
  },
  watch: {
    'passwordConfirm'() {
      this.checkPassword();
      this.checkPasswordValidate();
    },
    'password'() {
      this.checkPasswordValidate();
      this.checkPassword();
    },
    'nowPassword'() {
      this.checkPassword();
    },
    
  },
  computed: {
    ...mapState(['user'])
  },
  mounted() {
    this.defaultDark()
  },
  methods: {
    ...mapMutations(['setToken', 'setLoggedIn']),
    ...mapActions(['sendUserInfo']),
    activeInput() {
      event.path[1].style.border = '2px solid black'
      event.path[1].style.zIndex = 5
    },
    deactiveInput() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
    },
    checkPasswordValidate() {
      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        { this.passwordErrorMsg = true;
        this.passwordSuccessMsg = false; }
      else { this.passwordSuccessMsg = true; 
      this.passwordErrorMsg= false;
      }
    },
    checkPassword() {
      if (this.passwordConfirm !== '') {

        if(this.password != this.passwordConfirm) {
          this.pwErrMsg = true
          this.pwSucMsg = false
          this.pwdFlag = false;
        } else if (this.password && this.passwordConfirm && this.password === this.passwordConfirm) {
          this.pwErrMsg = false
          this.pwSucMsg = true
          this.pwdFlag = false;
          if (this.passwordSuccessMsg && this.nowPassword) {
            this.pwdFlag = true;
          }
        }
      } else {
        this.pwdFlag = false;
      }
    },
    changePassword() {
      console.log(this.user);
      const formData= new FormData();
      formData.append('email',this.user.email);
      formData.append('password',this.passwordConfirm);

      if (this.user.password === this.nowPassword) {
        axios.post('http://localhost:8080/api/account/changePassword',
          formData).then(data => {
          console.log(data)
          this.$cookies.set('auth-token', data.data.auth_token)
          this.setToken(data.data.auth_token)
          this.setLoggedIn(true);
          this.sendUserInfo();
          Swal.fire(
            '변경되었습니다.',
            '',
            'success'
          )
          this.$router.push('/feed')
        })
        .catch(data => {
          console.log(data)
        });
      } else {
        Swal.fire({
          icon: 'error',
          title: '비밀번호가 틀렸습니다.',
          text: '한번 더 확인해주세요',
        })
      }
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const INPUT = document.querySelectorAll('input')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        this.checked = true
        for (var i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
        for (var j=0; j<INPUT.length ; j++) {
          INPUT[j].classList.remove('input-dark')
        }
      }
    },
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
}
</script>


