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
      <!-- <div class="join-input-area birth-area">
        <label for="">생년월일</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='birth.year' type="text" class="birth-join" placeholder="yyyy" maxlength="4">
        <input @focus="activeInput" @blur='deactiveInput' v-model='birth.month' type="text" class="birth-join" placeholder="mm" maxlength="2" >
        <input @focus="activeInput" @blur='deactiveInput' v-model='birth.day' type="text" class="birth-join" placeholder="dd" maxlength="2">
        <p v-if="birthYearErrMsg" class='err-msg join-err-msg'>년도를 ex)1993 식으로 입력해주세요.</p>
        <p v-if="birthMonthErrMsg && !birthYearErrMsg" class='err-msg join-err-msg'>월을 ex)06 식으로 입력해주세요.</p>
        <p v-if="birthDayErrMsg && !birthYearErrMsg && !birthMonthErrMsg" class='err-msg join-err-msg'>일을 ex)05 식으로 입력해주세요.</p>
        <p v-if="birthSucMsg" class='suc-msg join-suc-msg'>사용가능합니다. </p> 
      </div> -->
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
      // birthYearErrMsg: false,
      // birthMonthErrMsg: false,
      // birthDayErrMsg: false,
      // birthSucMsg: false,
      flag: false,
    }
  },
  watch: {
    email() {
      this.checkEmailValidate();
    },
    birth() {
      // this.checkBirth();
    },
    // 'birth.year'() {
    //   this.checkYear();
    // },
    // 'birth.month'() {
    //   this.checkMonth();
    // },
    // 'birth.day'() {
    //   this.checkDay();
    // },
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
    // checkYear() {
    //   if (!(this.birth.year >= 1900 && this.birth.year <= 2020)) {
    //     this.birthYearErrMsg = true
    //     this.birthSucMsg = false
    //   } else {
    //     this.birthYearErrMsg = false
    //   }
    //   if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false && this.birth.year && this.birth.month && this.birth.day) {
    //     this.birthSucMsg = true
    //   }
    // },
    // checkMonth() {
    //   if (!(this.birth.month >= 1 && this.birth.month <= 12)) {
    //     this.birthMonthErrMsg = true
    //     this.birthSucMsg = false
    //   } else {
    //     this.birthMonthErrMsg = false
    //   }
    //   if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false  && this.birth.year && this.birth.month && this.birth.day) {
    //     this.birthSucMsg = true
    //   }
    // },
    // checkDay() {
    //   if (!(this.birth.day >= 1 && this.birth.day <= 31)) {
    //     this.birthDayErrMsg = true
    //     this.birthSucMsg = false
    //   } else {
    //     this.birthDayErrMsg = false
    //   }
    //   if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false  && this.birth.year && this.birth.month && this.birth.day) {
    //   this.birthSucMsg = true
    // }   
    // },
  }
}
</script>
