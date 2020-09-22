<template>
  <div class='wrap'>
    <div class='wrap-container wrap-social-join'>
      <div class="social-join-input-area">
        <label for="">이메일</label>
      </div>
      <div class="social-join-input-area">
        <label for="">비밀번호</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.password' type="password" class="common-input-social-join" placeholder="password" >
        <p v-if="passwordErrorMsg" class='err-msg social-join-err-msg'>영문,숫자 포함 8 자리이상이어야 합니다.</p>
        <p v-if="passwordSuccessMsg" class='suc-msg social-join-suc-msg'>사용 가능한 비밀번호 입니다.</p> 
      </div>
      <div class="social-join-input-area">
        <label for="">비밀번호 확인</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.passwordConfirm' type="password" class="common-input-social-join" placeholder="password confirm">
        <p v-if="pwErrMsg" class='err-msg social-join-err-msg'>비밀번호가 일치하지 않습니다.</p>
        <p v-if="pwSucMsg" class='suc-msg social-join-suc-msg'>비밀번호가 일치합니다.</p>
      </div>
      <div class="social-join-input-area">
        <label for="">닉네임</label>
      </div>
      <div class="social-join-input-area birth-area">
        <label for="">생년월일</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.year' type="text" class="birth-social-join" placeholder="yyyy" maxlength="4">
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.month' type="text" class="birth-social-join" placeholder="mm" maxlength="2" >
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.day' type="text" class="birth-social-join" placeholder="dd" maxlength="2" v-on:keydown.tab='notTab'>
        <p v-if="birthYearErrMsg" class='err-msg social-join-err-msg'>년도를 ex)1993 식으로 입력해주세요.</p>
        <p v-if="birthMonthErrMsg && !birthYearErrMsg" class='err-msg social-join-err-msg'>월을 ex)06 식으로 입력해주세요.</p>
        <p v-if="birthDayErrMsg && !birthYearErrMsg && !birthMonthErrMsg" class='err-msg social-join-err-msg'>일을 ex)05 식으로 입력해주세요.</p>
        <p v-if="birthSucMsg" class='suc-msg social-join-suc-msg'>사용가능합니다. </p> 
      </div>
      <div class="social-join-sex-area">
        <i @click='clickMale' class="fas fa-male"></i>
        <i @click='clickFeMale' class="fas fa-female"></i>
      </div>
      <div v-if='socialJoinBtn' class='btn social-join-btn'>가입하기</div>
      <div @click='JoinComplete' v-if='!socialJoinBtn && isMale' class='btn on-social-join-btn'>가입하기</div>
      <div @click='JoinComplete' v-if='!socialJoinBtn && isFemale' class='btn on-social-join-btn-woman'>가입하기</div>
    </div>
  </div>
</template>

<script>
import '../components/css/socialjoin.css'
import PasswordValidator from 'password-validator'
import Swal from 'sweetalert2'
import axios from 'axios'
import { mapState, mapMutations, mapActions } from 'vuex'

export default {
  name: 'SocialJoin',
  data() {
    return {
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
        },
        sex: '',
        profileImg: '',
        textProfile: '',
      },
      passwordSchema: new PasswordValidator(),
      passwordErrorMsg: false,
      passwordSuccessMsg: false,
      pwErrMsg: false,
      pwSucMsg: false,
      birthSucMsg: false,
      birthYearErrMsg: false,
      birthMonthErrMsg: false,
      birthDayErrMsg: false,
      socialJoinBtn: true,
      isMale: false,
      isFemale: false,
      gender: '',
    }
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    'input.passwordConfirm'() {
      this.checkPassword();
    },
    'input.password'() {
      this.checkPasswordValidate();
      this.checkPassword();
    },
    'input.birth.year'() {
      this.checkYear();
    },
    'input.birth.month'() {
      this.checkMonth();
    },
    'input.birth.day'() {
      this.checkDay();
    },
    input: {
      handler() {
        this.checkJoinForm();
      }, deep:true
    }
  },
  computed: {
    ...mapState(['setLoggedIn']),
    ...mapActions(['sendUserInfo'])
  },
  methods: {
    ...mapMutations(['setToken']),
    activeInput() {
      event.path[1].style.border = '2px solid black'
      event.path[1].style.zIndex = 5
    },
    deactiveInput() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
    },
    notTab() {
      window.addEventListener('keydown', event => {
        const WRAPSOCIALJOIN = document.querySelector('.wrap-social-join')
        if (WRAPSOCIALJOIN) {
          
          if(event.defaultPrevented) {
            return;
          }
          var handled = false;
        
          if (event.keyCode === 9) {
            handled = true;
          }
        
          if (handled) {
            event.preventDefault();
          }
        }
      })
    },
    clickMale() {
      const male = document.querySelector('.fa-male')
      const female = document.querySelector('.fa-female')
      if (document.querySelector('.change-color') || document.querySelector('.change-color-woman')){
        male.classList.remove('change-color')
        this.isMale = false
        this.gender=''
        this.checkJoinForm()
        if (document.querySelector('.change-color-woman')) {
          female.classList.remove('change-color-woman')
          male.classList.add('change-color')
          this.isMale = true
          this.isFemale = false
          this.gender = 'Male';
          this.checkJoinForm()
        }
      } else {
        male.classList.add('change-color')
        this.isMale = true
        this.gender = 'Male'
        this.checkJoinForm()
      }
    },
    clickFeMale() {
      const male = document.querySelector('.fa-male')
      const female = document.querySelector('.fa-female')
      if (document.querySelector('.change-color') || document.querySelector('.change-color-woman')){
        female.classList.remove('change-color-woman')
        this.isFemale = false
        this.gender=''
        this.checkJoinForm()
        if (document.querySelector('.change-color')) {
          male.classList.remove('change-color')
          female.classList.add('change-color-woman')
          this.isMale = false
          this.isFemale = true
          this.checkJoinForm()
          this.gender = 'Female'
        }
      } else {
        female.classList.add('change-color-woman')
        this.isFemale = true
        this.gender = 'Female'
        this.checkJoinForm()
      }
    },
    JoinComplete() {
      axios.post('http://localhost:8080/api/account/signup',{

          // email: this.input.email+'@'+this.input.url,
          password: this.input.password,
          // nickname: this.input.nickname,
          gender: this.gender,
          birth: this.input.birth.year+' '+this.input.birth.month+' '+this.input.birth.day,
          // profile_img: this.profileImg
      }).then(data => {
        this.$cookies.set('auth-token', data.data.auth_token)
        this.setToken(data.data.auth_token)
        Swal.fire(
        '환영해요!',
        '자신만의 패션을 뽐내보세요!',
        'success'
        )
        this.setLoggedIn(true);
        this.sendUserInfo();
        this.$router.push('/feed').catch(()=>{})
      })
      .catch();
    },
    checkPassword() {
      if (this.input.passwordConfirm !== '') {

        if(this.input.password != this.input.passwordConfirm) {
          this.pwErrMsg = true
          this.pwSucMsg = false
        } else if (this.input.password && this.input.passwordConfirm && this.input.password === this.input.passwordConfirm) {
          this.pwErrMsg = false
          this.pwSucMsg = true
        }
      }
    },
    checkPasswordValidate() {
      if (
        this.input.password.length >= 0 &&
        !this.passwordSchema.validate(this.input.password)
      )
        { this.passwordErrorMsg= true;
        this.passwordSuccessMsg = false; }
      else { this.passwordSuccessMsg = true; 
      this.passwordErrorMsg= false;
      }
    },
    checkYear() {
      if (!(this.input.birth.year >= 1900 && this.input.birth.year <= 2020)) {
        this.birthYearErrMsg = true
        this.birthSucMsg = false
      } else {
        this.birthYearErrMsg = false
      }
      if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false && this.input.birth.year && this.input.birth.month && this.input.birth.day) {
        this.birthSucMsg = true
      }
    },
    checkMonth() {
      if (!(this.input.birth.month >= 1 && this.input.birth.month <= 12)) {
        this.birthMonthErrMsg = true
        this.birthSucMsg = false
      } else {
        this.birthMonthErrMsg = false
      }
      if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false  && this.input.birth.year && this.input.birth.month && this.input.birth.day) {
        this.birthSucMsg = true
      }
    },
    checkDay() {
      if (!(this.input.birth.day >= 1 && this.input.birth.day <= 31)) {
        this.birthDayErrMsg = true
        this.birthSucMsg = false
      } else {
        this.birthDayErrMsg = false
      }
      if (this.birthYearErrMsg === false && this.birthMonthErrMsg === false && this.birthDayErrMsg === false  && this.input.birth.year && this.input.birth.month && this.input.birth.day) {
        this.birthSucMsg = true
      }
    },
    // checkJoinForm() {
    //   if(this.input.email && (this.input.url || this.select != '직접입력') && this.input.password
    //   && this.input.passwordConfirm && this.input.nickname && this.passwordSuccessMsg
    //   && this.input.birth.year && this.input.birth.month && this.input.birth.day
    //   && (this.isMale || this.isFemale)
    //   && !this.finalMail && this.pwSucMsg && this.nickSucMsg && this.birthSucMsg){
    //     this.socialJoinBtn = false;
    //   } else {
    //     this.socialJoinBtn = true
    //   }
    // },
    checkJoinForm() {
      if(this.input.password && this.input.passwordConfirm && this.passwordSuccessMsg
        && this.input.birth.year && this.input.birth.month && this.input.birth.day
        && (this.isMale || this.isFemale)
        && this.pwSucMsg && this.birthSucMsg){
        this.socialJoinBtn = false;
      } else {
        this.socialJoinBtn = true
      }
    },
  }
}
</script>

<style>

</style>