<template>
  <div class='wrap'>
   <div class='wrap-container'>
      <div class='findpasswordok-head'>
        <h1>임시 비밀번호를<br>전송했습니다.<br><br>메일을<br>확인해주세요.</h1>
      </div>
      <div class="findpasswordok-btn-area">
        <div class="timeParent">
          <input v-model='uuid' type="text" class="uuid" placeholder="인증번호를 입력해주세요.">
          <span id="time">03:00</span>
        </div>
        <div class='btn go-login-btn' @click="changePwd">확인</div>
        <div class='btn re-submit-btn' @click="retry">다시보내기</div>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpasswordok.css"
import axios from 'axios'
import { mapState, mapMutations } from 'vuex'
import Swal from 'sweetalert2'

export default {
  name: 'FindPasswordOk',
  data() {
    return {
      uuid: '',
      reTime: '',
      remaintime: false,
    }
  },
  mounted() {
    var fiveMinutes = 60 * 3,
    display = document.querySelector('#time');
    this.startTimer(fiveMinutes, display);
    this.defaultDark()

  },
  computed: {
    ...mapState(['pwdUser', 'certifNum']),
  },
  methods: {
    ...mapMutations(['confirmPwd', 'findUserPWd']),
    pushLogin() {
      this.$router.push("/")
    },
    startTimer(duration, display) {
      var timer = duration, minutes, seconds;
      this.reTime = setInterval(function () {
      minutes = parseInt(timer / 60, 10);
      seconds = parseInt(timer % 60, 10);

      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;

      display.textContent = minutes + ":" + seconds;

      if (--timer <= 0) {
        display.textContent = '00' + ":" + '00';
        this.remaintime = true;
        clearInterval(this.reTime);
      }
    }, 1000);
    },
    retry() {
      console.log(this.pwdUser.birth.substring(0, 4) + this.pwdUser.birth.substring(5, 7) + this.pwdUser.birth.substring(8, 10) )
      clearInterval(this.reTime);
      var fiveMinutes = 60 * 3,
      display = document.querySelector('#time');
      this.startTimer(fiveMinutes, display);
      this.remaintime = false;
      axios.get('http://localhost:8080/api/account/findPassword',{
        params:{
          email: this.pwdUser.email,
          pTime: this.pwdUser.birth.substring(0, 4) + this.pwdUser.birth.substring(5, 7) + this.pwdUser.birth.substring(8, 10) 
        }
      }).then(data => {
        console.log("성공")
        console.dir(data)
        this.confirmPwd(data.data.certifNum)
        this.findUserPWd(data.data.userInfo)
      })
      .catch(data => {
        console.log(data)
      });
    },
    changePwd() {
      console.log(1)
      if (this.uuid == this.certifNum && !this.remaintime) {
        console.log(2)
        this.$router.push("/find/password/passwordchange")
      } else {
        Swal.fire({
          icon: 'error',
          title: '인증번호가 틀렸습니다.',
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
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
      }
    },
  }
}
</script>


