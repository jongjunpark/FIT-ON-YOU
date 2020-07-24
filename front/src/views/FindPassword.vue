<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class='find-head'>
        <h1>이름과<br>이메일을<br>입력해 주세요</h1>
      </div>
      <div class='find-input-area'>
        <input v-model='name' @keyup="onOkBtn" type="text" id='find-name' placeholder="이름">
      </div>
      <div class='find-input-area'>
        <input v-model='email' @keyup="onOkBtn" type="text" id='find-email' placeholder="이메일">
      </div>
      <p v-if='errMsg' class='err-msg'>계정 혹은 비밀번호가 틀렸습니다.</p>
      <div v-if='okBtn' class='btn find-btn'>확인</div>
      <div v-if='!okBtn' @click='findPassword' class='btn on-find-btn'>확인</div>
    </div>
  </div>
</template>

<script>
import "../components/css/findpassword.css"
import axios from "axios";
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
    emai() {
      this.setEmailClass();
    },
    name() {
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
    },
    findPassword(){
      axios.get('http://localhost:8080/account/findPassword',{
        params:{
          email: this.email,
          nickname: this.name
        }
      }).then(function(data){
        console.log("성공")
        console.dir(data)
      })
      .catch(function(){
        alert("이메일 혹은 닉네임이 존재하지 않거나 틀립니다")
      });
    },
  }
}
</script>
