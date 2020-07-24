<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class='find-head'>
        <h1>이메일과<br>생년월일을<br>입력해 주세요</h1>
      </div>
      <div class='find-input-area'>
        <input v-model='email' @keyup="onOkBtn" type="text" id='find-email' placeholder="이메일">
      </div>
      <div class='find-input-area'>
        <input v-model='birth' @keyup="onOkBtn" type="text" id='find-birth' placeholder="생년월일 ex)19930321">
      </div>
      <p v-if='errMsg' class='err-msg'>계정 혹은 생년월일이 틀렸습니다.</p>
      <div v-if='!okBtn' class='btn find-btn'>인증메일 발송</div>
      <div v-if='okBtn' @click='checkInput' class='btn on-find-btn'>인증메일 발송</div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpassword.css"
import '../components/css/join.css'
import * as EmailValidator from "email-validator"


export default {
  name: 'FindPassword',
  data() {
    return {
      birth: '',
      email: '',
      emailVaild: false,
      okBtn: false,
      errMsg: false,
      flag: false,
    }
  },
  watch: {
    email() {
      this.checkEmailValidate();
    },
  },
  methods: {
    onOkBtn() {
      if (this.emailVaild && this.birth.length >= 8) {
          this.okBtn = true
      } else {
        this.okBtn = false
      }
    },
    checkInput() {
      this.errMsg = true
    },
    checkEmailValidate() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        { console.log('올바르지 않습니다.'); this.emailVaild = false;}
      else { console.log('올바릅니다.'); this.emailVaild = true;}
    },
    activeInput() {
      event.path[1].style.border = '2px solid black'
      event.path[1].style.zIndex = 5
    },
    deactiveInput() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
    }, 
  }
}
</script>
