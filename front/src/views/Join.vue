<template>
  <div class='wrap'>
    <div class='wrap-container wrap-join'>
      <h1 class='join-logo'>회원가입</h1>
      <div class="join-input-area">
        <label for="">이메일</label>
        <input @focus="activeInput" @blur='deactiveInputEmail' v-model='input.email' type="text" id='email-join' placeholder="example">
        <span class='email-join-span'> @ </span>
        <input @focus="activeInput" @blur='deactiveInputEmail' v-model='input.url' v-show='offSelect' type="text" id='email-join2' placeholder="url">
        <span v-show='!offSelect' id='email-join2'>{{ input.url }}</span>
        <span class='email-join-span'> |  </span>
        <select @focus="activeInput" @blur='deactiveInputEmail' v-model='select' name="job" id='email-combo'>
          <option >직접입력</option>
          <option >gmail</option>
          <option > naver</option>
          <option > hanmail</option>
          <option > lycos</option>
          <option > nate</option>
          <option > yahoo</option>
          <option > empal</option>
          <option > paran</option>
          <option > korea</option>
        </select>  
        <p v-if="mailErrMsg" class='err-msg join-err-msg'>유효하지 않은 이메일 형식입니다.</p>
        <p v-if="mailSucMsg && finalMail" class='err-msg join-err-msg'>이미 사용중인 이메일입니다.</p>
        <p v-if="mailSucMsg && !finalMail && joinFlag" class='suc-msg join-suc-msg'>사용가능합니다.</p>
      </div>
      <div class="join-input-area" v-show="mailSucMsg && !finalMail && joinFlag && !finalFlag">
        <label for="">인증번호 입력</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='checkConfirm' type="text" class="common-input-join helllotest" placeholder="인증번호입력" >
        <p v-show="confirmErrorMsg" class='err-msg join-err-msg'>인증번호가 틀렸습니다.</p>
        <p v-show="confirmSuccessMsg" class='suc-msg join-suc-msg'>사용 가능한 비밀번호 입니다.</p>
        <button class="confirm-btnid" @click="goConfrim">전송</button> 
      </div>
      <div class="join-input-area">
        <label for="">비밀번호</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.password' type="password" class="common-input-join" placeholder="password" >
        <p v-if="passwordErrorMsg" class='err-msg join-err-msg'>영문,숫자 포함 8 자리이상이어야 합니다.</p>
        <p v-if="passwordSuccessMsg" class='suc-msg join-suc-msg'>사용 가능한 비밀번호 입니다.</p> 
      </div>
      <div class="join-input-area">
        <label for="">비밀번호 확인</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.passwordConfirm' type="password" class="common-input-join" placeholder="password confirm">
        <p v-if="pwErrMsg" class='err-msg join-err-msg'>비밀번호가 일치하지 않습니다.</p>
        <p v-if="pwSucMsg" class='suc-msg join-suc-msg'>비밀번호가 일치합니다.</p>
      </div>
      <div class="join-input-area">
        <label for="">닉네임</label>
        <input @focus="activeInput" @blur='deactiveInput' v-on:input="input.nickname = $event.target.value" type="text" class="common-input-join" placeholder="nickname"  maxlength="128">
        <p v-if="nickErrMsg" class='err-msg join-err-msg'>이미 사용중인 닉네임입니다.</p>
        <p v-if="nickSucMsg" class='suc-msg join-suc-msg'>사용가능합니다.</p> 
      </div>
      <div class="join-input-area birth-area">
        <label for="">생년월일</label>
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.year' type="text" class="birth-join" placeholder="yyyy" maxlength="4">
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.month' type="text" class="birth-join" placeholder="mm" maxlength="2" >
        <input @focus="activeInput" @blur='deactiveInput' v-model='input.birth.day' type="text" class="birth-join" placeholder="dd" maxlength="2" v-on:keydown.tab='notTab'>
        <p v-if="birthYearErrMsg" class='err-msg join-err-msg'>년도를 ex)1993 식으로 입력해주세요.</p>
        <p v-if="birthMonthErrMsg && !birthYearErrMsg" class='err-msg join-err-msg'>월을 ex)06 식으로 입력해주세요.</p>
        <p v-if="birthDayErrMsg && !birthYearErrMsg && !birthMonthErrMsg" class='err-msg join-err-msg'>일을 ex)05 식으로 입력해주세요.</p>
        <p v-if="birthSucMsg" class='suc-msg join-suc-msg'>사용가능합니다. </p> 
      </div>
      <div class="sex-area">
        <i @click='clickMale' class="fas fa-male"></i>
        <i @click='clickFeMale' class="fas fa-female"></i>
      </div>
      <div v-if='JoinBtn' class='btn join-btn'>가입하기</div>
      <div @click='nextJoin' v-if='!JoinBtn && isMale' class='btn on-join-btn'>가입하기</div>
      <div @click='nextJoin' v-if='!JoinBtn && isFemale' class='btn on-join-btn-woman'>가입하기</div>
    </div>
    <div class='wrap-container center-container join-profile-hidden'>
      <header class='join-profile-header'>
        <i @click='goBack' class="fas fa-arrow-circle-left"></i>
      </header>
      <section class='join-profile-area'>
        <div class='join-profile-img'>
          <div v-if='!input.profileImg'>
            <img class='profile-img' :src="require(`../assets/images/${defaultImg}`)" alt="">
          </div>
          <div @mouseover="onCancleBtn" @mouseout="offCancleBtn" v-show='input.profileImg'>
            <img @click='setDefaultImg' v-show='isCancle' class='cancle-img' src="../assets/images/X.png" alt="">
            <img class='profile-img select-img' :src="input.profileImg" alt="">
          </div>
          <label for='profile-img-edit' class="join-profile-img-edit">
            <input type="file" id="profile-img-edit" accept="image/*" @change="setProfileImg">
          </label>
        </div>
        <p class='join-profile-username'>{{ input.nickname }}</p>
        <textarea class='join-profile-usercontent' name="" id="" cols="50" rows="3" placeholder="자기소개를 작성해 주세요" maxlength="100" v-model="input.textProfile"></textarea>
      </section>
      <div @click='signupFinish' v-show='changeProfile' class='btn on-join-profile-btn'>바로 시작할래요!</div>
      <div @click='changePart' v-show='!changeProfile' class='btn join-skip-btn'>건너뛰기!</div>
    </div>
  </div>
</template>

<script>
import '../components/css/join.css'
import "../components/css/joinprofile.css"
import PasswordValidator from 'password-validator'
import * as EmailValidator from "email-validator"
import Swal from 'sweetalert2'
import axios from 'axios'
import { mapState, mapMutations, mapActions } from 'vuex'


export default {
  name: 'Join',
  data() {
    return {
      birthFlag: 0,
      birthSucMsg: false,
      birthYearErrMsg: false,
      birthMonthErrMsg: false,
      birthDayErrMsg: false,
      passwordErrorMsg: false,
      passwordSuccessMsg: false,
      passwordSchema: new PasswordValidator(),
      select: '직접입력',
      offSelect: true,
      checkConfirm: '',
      checkConfirmFlag: 'QWcbjksbQ',
      gender: '',
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
      JoinBtn: true,
      pwErrMsg: false,
      pwSucMsg: false,
      mailErrMsg: false,
      mailSucMsg: false,
      finalMail: false,
      nickErrMsg: false,
      nickSucMsg: false,
      isFemale: false,
      isEmail: false,
      changeProfile: false,
      confirmErrorMsg: false,
      confirmSuccessMsg: false,
      isMale: false,
      isCancle: false,
      defaultImg: "default-user.png",
      joinFlag: false,
      finalFlag: false,
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
    checkConfirm() {
      this.goCheckConfirm();
    },
    finalFlag() {
      this.checkJoinForm();
    },
    select() {
      this.checkSelect();
      this.checkEmailValidate();
      this.checkJoinForm();
    },
    'input.passwordConfirm'() {
      this.checkPassword();
    },
    'input.password'() {
      this.checkPasswordValidate();
      this.checkPassword();
    },
    'input.email'() {
      this.checkEmailValidate();
    },
    'input.url'() {
      this.checkEmailValidate();
    },
    'input.nickname'() {
      this.checkNickname();
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
    'input.textProfile'() {
      this.checkProfile();
    },
    'input.profileImg'() {
      this.checkProfile();
    },
    'finalMail'() {
      this.finalMailCheck();
    },
    flag() {
      this.defaultDark()
    },
    input: {
      handler() {
        this.checkJoinForm();
      }, deep:true
    },
  },
  computed: {
    ...mapState(['setLoggedIn', 'flag']),
  },
  mounted() {
    this.defaultDark()
  },
  methods: {
    ...mapMutations(['setToken']),
    ...mapActions(['sendUserInfo']),
    checkSelect() {
      if (this.select === '직접입력') {
        this.onSelect = false
        this.offSelect = true
        
      } else {
        this.onSelect = true
        this.offSelect = false
        if (this.select === 'naver') {
          this.input.url = 'naver.com'
        } else if (this.select === 'hanmail') {
          this.input.url = 'hanmail.net'
        } else if (this.select === 'nate') {
          this.input.url = 'nate.com'
        } else if (this.select === 'gmail') {
          this.input.url = 'gmail.com'
        } else if (this.select === 'lycos') {
          this.input.url = 'lycos.co.kr'
        } else if (this.select === 'yahoo') {
          this.input.url = 'yahoo.co.kr'
        } else if (this.select === 'empal') {
          this.input.url = 'empal.com'
        } else if (this.select === 'paran') {
          this.input.url = 'paran.com'
        } else if (this.select === 'korea') {
          this.input.url = 'korea.com'
        }
      }
    },
    goConfrim() {
      axios.get('http://localhost:8080/api/account/confirmemail',{
        params:{
          email: this.input.email + '@' + this.input.url
        }
      }).then(data => {
        if (data.data.certifNum) {
          this.checkConfirmFlag = data.data.certifNum;
        }
      })
      .catch();
      Swal.fire(
        '인증번호가 전송되었습니다.',
        '번호를 입력해주세요',
        'success'
        )
      
      const Confirm = document.querySelector('.confirm-btnid')
      Confirm.innerHTML = '재전송'
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
    checkJoinForm() {
      if(this.input.email && (this.input.url || this.select != '직접입력') && this.input.password && this.finalFlag
      && this.input.passwordConfirm && this.input.nickname && this.passwordSuccessMsg
      && this.input.birth.year && this.input.birth.month && this.input.birth.day
      && (this.isMale || this.isFemale)
      && !this.finalMail && this.pwSucMsg && this.nickSucMsg && this.birthSucMsg){
        this.JoinBtn = false;
      } else {
        this.JoinBtn = true
      }
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
    checkEmail() {
      if(this.input.email && (this.input.url || this.select != '직접입력')) {
        this.mailSucMsg = true
      } else {
        this.mailSucMsg = false
      }
    },
    goCheckConfirm() {
      if (this.checkConfirm == this.checkConfirmFlag) {
        this.confirmSuccessMsg = true;
        this.confirmErrorMsg = false;
        this.finalFlag = true;
      } else {
        this.confirmErrorMsg = true;
        this.confirmSuccessMsg = false;
        this.finalFlag = false;

      }
    },
    checkNickname() {
      axios.get('http://localhost:8080/api/account/checkNickname',{ 
        params: {
          nickname: this.input.nickname
          }
      }).then(data => {
        if (data.data.data == "exist") {
          this.nickErrMsg = true;
          this.nickSucMsg = false;
          this.checkJoinForm()
        } else {
          this.nickSucMsg = true;
          this.nickErrMsg = false;
          this.checkJoinForm()        
        }
      })
      .catch(function(){
          })
      
    },
    activeInput() {
      event.path[1].style.border = '2px solid black'
      event.path[1].style.zIndex = 5
    },
    deactiveInput() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
    },
    deactiveInputEmail() {
      event.path[1].style.border = '1px solid #B0B0B0'
      event.path[1].style.zIndex = 1
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
    nextJoin() {
      const firstPage = document.querySelector('.wrap-container:nth-child(1)')
      const SecondPage = document.querySelector('.wrap-container:nth-child(2)')

      firstPage.classList.add('goNext-front')
      SecondPage.classList.remove('join-profile-hidden')
      firstPage.classList.remove('join-profile-return')
      SecondPage.classList.add('goNext-end')
      if (this.isMale) {
        this.defaultImg = 'default-user.png'
        this.input.sex = 'male';
      }
      else {
        this.defaultImg = 'default-user-female.png'
        this.input.sex = 'female';
      }
    },

    goBack() {
      const firstPage = document.querySelector('.wrap-container:nth-child(1)')
      const SecondPage = document.querySelector('.wrap-container:nth-child(2)')

      firstPage.classList.remove('goNext-front')
      firstPage.classList.add('join-profile-return')
      SecondPage.classList.remove('goNext-end')
      SecondPage.classList.add('join-profile-hidden')
      if(!this.input.textProfile && !this.input.profileImg) {
        this.changeProfile = false
      }

    },
    checkProfile() {
      if (this.input.textProfile !== '' || this.input.profileImg) {
        this.changeProfile = true
      } else {
        this.changeProfile = false
      }
    },
    signupFinish() {
      
      var frm = new FormData();
      var photoFile = document.getElementById("profile-img-edit");
      if (photoFile.files[0]) {
        this.profileImg = "../images/profile/"+this.input.nickname+"_"+photoFile.files[0].name
      } else {
        this.profileImg = null
      }

      axios.post('http://localhost:8080/api/account/signup',{

          email: this.input.email+'@'+this.input.url,
          password: this.input.password,
          nickname: this.input.nickname,
          gender: this.gender,
          birth: this.input.birth.year+' '+this.input.birth.month+' '+this.input.birth.day,
          profile_img: this.profileImg,
          selfintroduce : this.input.textProfile,

      }).then(data => {
        this.$cookies.set('auth-token', data.data.auth_token)
        this.setToken(data.data.auth_token)
        this.$cookies.remove('agree')
        Swal.fire(
        '환영해요!',
        '자신만의 패션을 뽐내보세요!',
        'success'
        )
        this.setLoggedIn(true);
        this.sendUserInfo();
      })
      .catch(function(){
      });
      if (photoFile.files[0]) {
        frm.append("profile-img-edit", photoFile.files[0]);
        frm.append("nickname",this.input.nickname);
        axios.post('http://localhost:8080/api/account/addProfileImg',frm,
        ).then( () =>{


          setTimeout(() => {
            this.$router.go('/feed')
          }, 1000);
          
        })
        .catch(function(){
        });
      } else {setTimeout(() => {
            this.$router.go('/feed')
          }, 1000);}

    },
    notTab() {
      window.addEventListener('keydown', event => {
        const WRAPJOIN = document.querySelector('.wrap-join')
        if (WRAPJOIN) {
          
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
    changePart() {
      this.changeProfile = true;
    },
    finalMailCheck() {
      this.checkJoinForm();
    },
    checkEmailValidate() {
      if (this.input.email.length >= 4 && EmailValidator.validate((this.input.email+'@'+this.input.url)))
        { 
         this.mailSucMsg = true;
         this.mailErrMsg = false;
         this.finalMail = false;
          if (this.mailSucMsg) {
            axios.get('http://localhost:8080/api/account/checkDoubleEmail',{ 
              params: {
                email: this.input.email+'@'+this.input.url
                }
            }).then(data => {
              if (data.data.data == "exist") {
                this.finalMail = true;
                this.joinFlag = false;
              } else {
                this.finalMail = false;
                this.joinFlag = true;
                
              }
            })
            .catch(function(){
            })}
        }
      else { 
      this.mailSucMsg = false;
      this.mailErrMsg = true; }
    },
    setProfileImg() {
      var frm = new FormData();
      var photoFile = document.getElementById("profile-img-edit");
      frm.append("profile-img-edit", photoFile.files[0]);
      this.input.profileImg = URL.createObjectURL(photoFile.files[0]);
    },
    onCancleBtn() {
      this.isCancle = true
    },
    offCancleBtn() {
      this.isCancle = false
    },
    setDefaultImg() {
      this.input.profileImg = ''
      this.isCancle = false
      document.getElementById("profile-img-edit").value = "";
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const H1TAG = document.querySelectorAll('h1')
      const LABEL = document.querySelectorAll('label')
      const SPAN = document.querySelectorAll('span')
      const USERNAME = document.querySelector('.join-profile-username')
      const INPUT = document.querySelectorAll('input')
      const TEXTAREA = document.querySelectorAll('textarea')

      // const BACKBTN = document.querySelector('.join-profile-back-btn')
      const SKIP = document.querySelector('.join-skip-btn')
      const CANCLEIMG = document.querySelector('.cancle-img')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.add('textarea-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.add('font-dark')
        }
        for (let i=0; i<SPAN.length ; i++) {
          SPAN[i].classList.add('font-dark')
        }

        // BACKBTN.classList.add('join-profile-back-btn-dark')
        SKIP.classList.add('join-skip-btn-dark')
        CANCLEIMG.classList.add('join-cancle-img-dark')
        USERNAME.classList.add('font-dark')

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.remove('textarea-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.remove('font-dark')
        }
        for (let i=0; i<SPAN.length ; i++) {
          SPAN[i].classList.remove('font-dark')
        }
        
        // BACKBTN.classList.remove('join-profile-back-btn-dark')
        SKIP.classList.remove('join-skip-btn-dark')
        CANCLEIMG.classList.remove('join-cancle-img-dark')
        USERNAME.classList.remove('font-dark')
      }
    },
  }
}
</script>
