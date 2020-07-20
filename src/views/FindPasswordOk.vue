<template>
  <div class='wrap'>
   <div class='container'>
      <div class='ok-head'>
        <h1>임시 비밀번호를<br>전송했습니다.<br><br>메일을<br>확인해주세요.</h1>
      </div>
      <div class="btn-area">
        <div class='btn re-submit-btn'>다시보내기</div>
        <div @click='pushLogin' class='btn go-login-btn'>로그인하기</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FindPasswordOk',
  methods: {
    pushLogin() {
      this.$router.push("/")
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
  margin: 50px 0;
}
.ok-head {
  width: 100%;
  margin: 30px 0;
  line-height: 300%;
  font-size: 20px;
}
.btn-area {
  margin-top:50px;
  width: 100%;
}

.btn {
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  cursor: pointer;
  height: 50px;
  line-height: 50px;
  box-sizing: border-box;
  border-radius: 5px;
  margin-top: 30px;
}

.btn.re-submit-btn {
  color: white;
  background-color: #ED4C00;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}

.btn.go-login-btn {
  color: white;
  background-color: #5AAEFF;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}
</style>