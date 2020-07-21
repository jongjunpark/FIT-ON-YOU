<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <h1 class='join-logo'>Welcome</h1>
      <div class="join-input-area">
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
        <p v-if="mailErrMsg" class='err-msg join-err-msg'>이미 사용중인 이메일입니다.</p>
        <p v-if="mailSucMsg" class='suc-msg join-suc-msg'>사용가능합니다.</p> 
      </div>
      <div class="join-input-area">
        <label for="">비밀번호</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.password' type="password" class="common-input-join" placeholder="password">
      </div>
      <div class="join-input-area">
        <label for="">비밀번호 확인</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.passwordConfirm' type="password" class="common-input-join" placeholder="password confirm">
        <p v-if="pwErrMsg" class='err-msg join-err-msg'>비밀번호가 일치하지 않습니다.</p>
        <p v-if="pwSucMsg" class='suc-msg join-suc-msg'>비밀번호가 일치합니다.</p>
      </div>
      <div class="join-input-area">
        <label for="">닉네임</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.nickname' type="text" class="common-input-join" placeholder="nickname">
        <p v-if="nickErrMsg" class='err-msg join-err-msg'>이미 사용중인 닉네임입니다.</p>
        <p v-if="nickSucMsg" class='suc-msg join-suc-msg'>사용가능합니다.</p> 
      </div>
      <div class="join-input-area birth-area">
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
      <div v-if='!JoinBtn && isMale' class='btn on-join-btn'>가입하기</div>
      <div v-if='!JoinBtn && isFemale' class='btn on-join-btn-woman'>가입하기</div>
    </div>
  </div>
</template>

<script>
import '../components/css/join.css'

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
    select() {
      this.checkSelect();
      this.checkEmail();
      this.checkJoinForm();
    },
    'input.passwordConfirm'() {
      this.checkPassword();
    },
    'input.password'() {
      this.checkPassword();
    },
    'input.email'() {
      this.checkEmail();
    },
    'input.url'() {
      this.checkEmail();
    },
    'input.nickname'() {
      this.checkNickname();
    },
    input: {
      handler() {
        this.checkJoinForm();
      }, deep:true
    },
  },
  methods: {
    checkSelect() {
      if (this.select === '직접입력') {
        this.onSelect = false
        this.offSelect = true
<<<<<<< HEAD
=======
        
>>>>>>> 48d7c0af45b2efe320a4a0e6b71e6bf4ef66b4e5
      } else {
        this.onSelect = true
        this.offSelect = false
        if (this.select === '네이버') {
          this.url = 'naver.com'
        } else if (this.select === '한메일') {
          this.url = 'hanmail.net'
        } else if (this.select === '네이트') {
          this.url = 'nate.com'
        }
      }
    },
    clickMale() {
      const male = document.querySelector('.fa-male')
      const female = document.querySelector('.fa-female')
      if (document.querySelector('.change-color') || document.querySelector('.change-color-woman')){
        male.classList.remove('change-color')
        this.isMale = false
        this.checkJoinForm()
        if (document.querySelector('.change-color-woman')) {
          female.classList.remove('change-color-woman')
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
      if (document.querySelector('.change-color') || document.querySelector('.change-color-woman')){
        female.classList.remove('change-color-woman')
        this.isFemale = false
        this.checkJoinForm()
        if (document.querySelector('.change-color')) {
          male.classList.remove('change-color')
          female.classList.add('change-color-woman')
          this.isMale = false
          this.isFemale = true
          this.checkJoinForm()
        }
      } else {
        female.classList.add('change-color-woman')
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