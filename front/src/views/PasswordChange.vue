<template>
  <div class='wrap'>
   <div class='wrap-container'>
     <div class='passwordchange-head'>
      <h1>새로운 <br> 비밀번호로 <br> 변경해주세요.</h1>
    </div>
    <div class='lock-profile-img'>
      <img class='profile-img' src="../assets/images/pngflow.com.png" alt="">
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
    <div v-show='!pwdFlag' class='btn pwc-btn passwordchange-btn'>변경하기</div>
    <div @click='changePassword' v-show='pwdFlag' class='btn pwc-btn on-passwordchange-btn'>변경하기</div>
   </div>
  </div>
</template>

<script>
import "../components/css/passwordchange.css"
import PasswordValidator from 'password-validator'
import axios from 'axios'
import Swal from 'sweetalert2'
import { mapState, mapMutations, mapActions } from 'vuex'
export default {
  name: 'PasswordChange',
  data() {
    return {
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
    flag() {
      this.defaultDark()
    },
  },
  computed: {
    ...mapState(['pwdUser', 'flag'])
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
          if (this.passwordSuccessMsg) {
            this.pwdFlag = true;
          }
        }
      } else {
        this.pwdFlag = false;
      }
    },
    changePassword() {
      const formData= new FormData();
      formData.append('email',this.pwdUser.email);
      formData.append('password',this.passwordConfirm);

      axios.post('https://i3b304.p.ssafy.io/api/account/changePassword',
        formData).then(data => {
        this.$cookies.set('auth-token', data.data.auth_token)
        this.setToken(data.data.auth_token)
        this.setLoggedIn(true);
        this.sendUserInfo();
        Swal.fire(
          '변경되었습니다.',
          '',
          'success'
        )
        this.$router.push('/feed').catch(()=>{})
      })
      .catch();
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const H1TAG = document.querySelectorAll('h1')
      const LABEL = document.querySelectorAll('label')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.remove('font-dark')
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


