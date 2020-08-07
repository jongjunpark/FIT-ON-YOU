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
      <div v-if='okBtn' @click='findPassword' class='btn on-find-btn'>인증메일 발송</div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpassword.css"
import '../components/css/join.css'
import * as EmailValidator from "email-validator"
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import Swal from 'sweetalert2'


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
    flag() {
      this.defaultDark()
    },
  },
  computed: {
    ...mapState(['certifNum', 'flag'])
  },
  mounted() {
    this.defaultDark()
  },
  methods: {
    ...mapMutations(['confirmPwd', 'findUserPWd']),
    onOkBtn() {
      if (this.emailVaild && this.birth.length >= 8 && (this.birth >= 190000 && this.birth <= 20201231)) {
          this.okBtn = true
      } else {
        this.okBtn = false
      }
    },
    findPassword(){
      axios.get('http://i3b304.p.ssafy.io:8080/api/account/findPassword',{
        params:{
          email: this.email,
          pTime: this.birth
        }
      }).then(data => {
        console.log("성공")
        console.dir(data)
        if (data.data.certifNum) {
          this.confirmPwd(data.data.certifNum)
          this.findUserPWd(data.data.userInfo)
          this.$router.push("/find/password/ok")
        } else {
          Swal.fire({
          icon: 'error',
          title: '다시 한번 더 확인해주세요',
          text: '이메일 혹은 생년월일이 틀렸어요',
        })
        }
      })
      .catch(data => {
        console.log(data)
      });
    },
    // checkInput() {
    //   // this.errMsg = true
    //   this.$router.push("/find/password/ok")
    // },
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
  }
}
</script>
