<template>
  <div class='wrap'>
    <div class='container'>
      <div class='find-head'>
        <h1>이름과<br>이메일을<br>입력해 주세요</h1>
      </div>
      <div class='input-area'>
        <input v-model='name' @keyup="onOkBtn" type="text" id='find-name' placeholder="이름">
      </div>
      <div class='input-area'>
        <input v-model='email' @keyup="onOkBtn" type="text" id='find-email' placeholder="이메일">
      </div>
      <p v-if='errMsg' class='err-msg'>계정 혹은 비밀번호가 틀렸습니다.</p>
      <div v-if='okBtn' class='btn ok-btn'>확인</div>
      <div v-if='!okBtn' @click='checkInput' class='btn on-ok-btn'>확인</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FindPassword',
  data() {
    return {
      name: '',
      email: '',
      okBtn: true,
      errMsg: false,
    }
  },
  watch: {
    email: function() {
      this.setEmailClass();
    },
    name: function() {
      this.setNameClass();
    }
  },
  methods: {
    onOkBtn() {
      if (this.email) {
        if (this.name) {
          this.okBtn = false
        } else {
          this.okBtn = true
        }
      } else {
        this.okBtn = true
      }
    },
    checkInput() {
      this.errMsg = true
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
.find-head {
  width: 100%;
  margin: 30px 0;
  line-height: 300%;
  font-size: 20px;
}

.input-area {
  width: 100%;
  margin-bottom: 20px;
  position: relative;
}
.input-area #find-name {
  height: 60px;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #D5D5D5;
  background-color: #D5D5D5;
  padding: 0 0 0 15px;
  box-sizing: border-box;
  color: #686868;
  font-family: "Noto Sans KR", sans-serif !important;
}

.input-area #find-email {
  height: 60px;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #D5D5D5;
  background-color: #D5D5D5;
  padding: 0 0 0 15px;
  box-sizing: border-box;
  color: #686868;
  font-family: "Noto Sans KR", sans-serif !important;
}

.input-area #find-email:focus, #find-name:focus {
  background-color: white;
  font-weight: 700;
  color: black;
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
  margin-bottom: 10px;
  margin-top: 100px;
}

.btn.ok-btn {
  color: #B0B0B0;
  border: 2px solid #B0B0B0;
  background-color: white;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
  cursor:default;
}

.btn.on-ok-btn {
  color: white;
  background-color: #5AAEFF;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}

.err-msg {
  margin: 0 0 8px 0;
  color: #FF3636;
  width: 100%;
  font-weight: 700;
}
</style>