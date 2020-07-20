<template>
  <div class='wrap'>
    <div class='container'>
      <h1 class='join-logo'>Welcome</h1>
      <div class="input-area">
        <label for="">이메일</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.email' type="text" id='email-join' placeholder="example">
        <span class='email-join-span'> @ </span>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.url' v-if='offSelect' type="text" id='email-join2' placeholder="url">
        <span v-if='!offSelect' id='email-join2'>{{ url }}</span>
        <span class='email-join-span'> |</span>
        <select @focus="activeInput" @blur='deactiveInput' v-model='select' name="job" id='email-combo'>
          <option >직접입력</option>
          <option >네이버</option>
          <option >한메일</option>
          <option >네이트</option>
        </select>
        <p v-if="mailErrMsg" class='err-msg'>이미 사용중인 이메일입니다.</p>
        <p v-if="mailSucMsg" class='suc-msg'>사용가능합니다.</p> 
      </div>
      <div class="input-area">
        <label for="">비밀번호</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.password' type="password" class="common-input-join" placeholder="password">
      </div>
      <div class="input-area">
        <label for="">비밀번호 확인</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.passwordConfirm' type="password" class="common-input-join" placeholder="password confirm">
        <p v-if="pwErrMsg" class='err-msg'>비밀번호가 일치하지 않습니다.</p>
        <p v-if="pwSucMsg" class='suc-msg'>비밀번호가 일치합니다.</p>
      </div>
      <div class="input-area">
        <label for="">닉네임</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.nickname' type="text" class="common-input-join" placeholder="nickname">
        <p v-if="nickErrMsg" class='err-msg'>이미 사용중인 닉네임입니다.</p>
        <p v-if="nickSucMsg" class='suc-msg'>사용가능합니다.</p> 
      </div>
      <div class="input-area birth-area">
        <label for="">생년월일</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.year' type="text" class="birth-join" placeholder="year">
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.month' type="text" class="birth-join" placeholder="month">
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.day' type="text" class="birth-join" placeholder="day">
      </div>
      <div class="sex-area">
        <i @click='clickMale' class="fas fa-male"></i>
        <i @click='clickFeMale' class="fas fa-female"></i>
      </div>
      <div v-if='JoinBtn' class='btn join-btn'>가입하기</div>
      <div v-if='!JoinBtn' class='btn on-join-btn'>가입하기</div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Join',
  data() {
    return {
      select: '직접입력',
      offSelect: true,
      input: {
        email: '',
        url: '',
        password: '',
        passwordConfirm: '',
        nickname: '',
        birth: {
          year: '',
          month: '',
          day: '',
        }
      },
      JoinBtn: true,
      pwErrMsg: false,
      pwSucMsg: false,
      mailErrMsg: false,
      mailSucMsg: false,
      nickErrMsg: false,
      nickSucMsg: false,
      isFemale: false,
      isEmail: false,
    }
  },
  watch: {
    select: function() {
      this.checkSelect();
    },
    'input.passwordConfirm': function() {
      this.checkPassword();
    },
    'input.email': function() {
      this.checkEmail();
    },
    'input.url': function() {
      this.checkEmail();
    },
    'input.nickname': function() {
      this.checkNickname();
    },
    input: {
      handler: function() {
        this.checkJoinForm();
      }, deep:true
    },
  },
  methods: {
    checkSelect() {
      if (this.select === '직접입력') {
        this.onSelect = false
        this.offSelect = true
        this.checkEmail()
      } else {
        this.onSelect = true
        this.offSelect = false
        if (this.select === '네이버') {
          this.url = 'naver.com'
        } else if (this.select === '한메일') {
          this.url = 'hanmail.net'
        } else if (this.select === '네이트') {
          this.url = 'nate.com'
        } this.checkEmail()
      }
    },
    clickMale() {
      const male = document.querySelector('.fa-male')
      const female = document.querySelector('.fa-female')
      if (document.querySelector('.change-color')){
        male.classList.remove('change-color')
        this.isMale = false
        this.checkJoinForm()
        if (document.querySelector('.change-color')) {
          female.classList.remove('change-color')
          male.classList.add('change-color')
          this.isMale = true
          this.isFemale = false
          this.checkJoinForm()
        }
      } else {
        male.classList.add('change-color')
        this.isMale = true
        this.checkJoinForm()
      }
    },
    clickFeMale() {
      const male = document.querySelector('.fa-male')
      const female = document.querySelector('.fa-female')
      if (document.querySelector('.change-color')){
        female.classList.remove('change-color')
        this.isFemale = false
        this.checkJoinForm()
        if (document.querySelector('.change-color')) {
          male.classList.remove('change-color')
          female.classList.add('change-color')
          this.isMale = false
          this.isFemale = true
          this.checkJoinForm()
        }
      } else {
        female.classList.add('change-color')
        this.isFemale = true
        this.checkJoinForm()
      }
    },
    checkJoinForm() {
      if(this.input.email && (this.input.url || this.select != '직접입력') && this.input.password
      && this.input.passwordConfirm && this.input.nickname
      && this.input.birth.year && this.input.birth.month && this.input.birth.day
      && (this.isMale || this.isFemale)
      && this.mailSucMsg && this.pwSucMsg && this.nickSucMsg){
        this.JoinBtn = false
      } else {
        this.JoinBtn = true
      }
    },
    checkPassword() {
      if(this.input.password != this.input.passwordConfirm) {
        this.pwErrMsg = true
        this.pwSucMsg = false
      } else if (this.input.password && this.input.passwordConfirm && this.input.password === this.input.passwordConfirm) {
        this.pwErrMsg = false
        this.pwSucMsg = true
      }
    },
    checkEmail() {
      if(this.input.email && (this.input.url || this.select != '직접입력')) {
        this.mailSucMsg = true
      } else {
        this.mailSucMsg = false
      }
    },
    checkNickname() {
      if(this.input.nickname) {
        this.nickSucMsg = true
      } else {
        this.nickSucMsg = false
      }
    },
    activeInput() {
      event.path[1].style.border = '2px solid black'
      event.path[1].style.zIndex = 5
    },
    deactiveInput() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
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
}
.join-logo {
  margin: 50px 0;
}

.input-area {
  width: 100%;
  margin: 0 0 -1px 0;
  height: 80px;
  border-radius: 5px;
  border: 1px solid #B0B0B0;
  position: relative;
}
.input-area:focus {
  border: 2px solid black;
}
.input-area.birth-area {
  display: flex;
  justify-content: space-around;
}
.sex-area {
  width: 100%;
  height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
}


.input-area #email-join {
  width: 30%;
  margin: 30px 0 0 20px;
  box-sizing: border-box;
  border: 0;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
}
.input-area #email-join:focus {
  outline: none !important;
  border: 2px solid #5AAEFF;
  border-radius: 5px;
  font-weight: 700;
}

.input-area #email-join2 {
  display: inline-block;
  width: 30%;
  box-sizing: border-box;
  border: 0;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
}
.input-area #email-join2:focus {
  outline: none !important;
  border: 2px solid #5AAEFF;
  border-radius: 5px;
  font-weight: 700;
}

.input-area #email-combo{
  width: 20%;
  box-sizing: border-box;
  border: 0;
  font-family: "Noto Sans KR", sans-serif !important;
  color: #686868;
}
.input-area #email-combo:focus{
  outline: none;
}

.input-area .common-input-join {
  width: 90%;
  box-sizing: border-box;
  margin: 30px 0 0 20px;
  border: 0;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
}
.input-area .common-input-join:focus {
  outline: none !important;
  border: 2px solid #5AAEFF;
  border-radius: 5px;
  font-weight: 700;
}

.input-area .birth-join {
  box-sizing: border-box;
  margin: 30px 20px 25px 20px;
  width: 28%;
  border: 0;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
}
.input-area .birth-join:focus {
  outline: none !important;
  border: 2px solid #5AAEFF;
  border-radius: 5px;
  font-weight: 700;
}




.input-area label {
  position: absolute;
  top: 2px;
  left: 10px;
  font-weight: 600;
}

.email-join-span {
  color: #B0B0B0;
}

.btn {
  font-weight: 500;
  font-size: 18px;
  text-align: center;
  cursor: pointer;
  height: 50px;
  line-height: 50px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.btn.join-btn {
  color: #B0B0B0;
  border: 2px solid #B0B0B0;
  background-color: white;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
  cursor:default;
}

.btn.on-join-btn {
  color: white;
  background-color: #5AAEFF;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}

.err-msg {
  margin: 0 0 0 20px;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
  color: #FF3636;
  font-weight: 700;
}
.suc-msg {
  margin: 0 0 0 20px;
  font-size: 14px;
  font-family: "Noto Sans KR", sans-serif !important;
  color: #1DDB16;
  font-weight: 700;
}


i {
  font-size: 5rem;
  margin: 0 80px;
  cursor: pointer;
  color: #686868;
}

.change-color {
  color: #5AAEFF !important;
}
</style>