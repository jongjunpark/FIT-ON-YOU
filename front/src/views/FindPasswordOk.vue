<template>
  <div class='wrap'>
   <div class='wrap-container'>
      <div class='findpasswordok-head'>
        <h1>임시 비밀번호를<br>전송했습니다.<br><br>메일을<br>확인해주세요.</h1>
      </div>
      <div class="findpasswordok-btn-area">
        <input v-model='uuid' type="text" class="uuid" placeholder="인증번호를 입력해주세요.">
        <div class='btn go-login-btn'>확인</div>
        <div class='btn re-submit-btn' @click="retry">다시보내기</div>
      </div>
      <div>Registration closes in <span id="time">03:00</span> minutes!</div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpasswordok.css"

export default {
  name: 'FindPasswordOk',
  data() {
    return {
      uuid: '',
      reTime: '',
      remainMinute: 0,
      remainSeconds: 0,
    }
  },
  mounted() {
    var fiveMinutes = 60 * 3,
    display = document.querySelector('#time');
    this.startTimer(fiveMinutes, display);
  },
  methods: {
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

      if (--timer < 0) {
        display.textContent = '00' + ":" + '00';
      }
    }, 1000);
    },
    retry() {
      clearInterval(this.reTime);
      var fiveMinutes = 60 * 3,
      display = document.querySelector('#time');
      this.startTimer(fiveMinutes, display);
    },
  }
}
</script>


