<template>
  <div class='wrap'>
   <div class='wrap-container'>
      <div class='findpasswordok-head'>
        <h1>임시 비밀번호를<br>전송했습니다.<br><br>메일을<br>확인해주세요.</h1>
      </div>
      <div class="findpasswordok-btn-area">
        <div class='btn re-submit-btn'>다시보내기</div>
        <div @click='pushLogin' class='btn go-login-btn'>로그인하기</div>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpasswordok.css"

export default {
  name: 'FindPasswordOk',
  methods: {
    pushLogin() {
      this.$router.push("/")
    }
  }
}
</script>
