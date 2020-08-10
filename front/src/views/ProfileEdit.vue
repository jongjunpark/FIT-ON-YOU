<template>
  <div class='wrap'>
    <div class="profile-hidden-box">
      <p v-show="!isChange" class="profile-nick-input">
        <input type="text" :placeholder="user.nickname" v-model="nickname" maxlength="20">
      </p>
      <img v-show="!isChange && !nickname" class='profile-nick-cancel' src="../assets/images/X.png" alt="" @click="cancel">
      <img v-show="!isChange && nickname" class='profile-nick-cancel' src="../assets/images/pngguru.com (1).png" alt="" @click="change">
      
      <p v-show="!isChange2" class="profile-content-input">
        <input type="text" :placeholder="user.selfintroduce" v-model="content" maxlength="100">
      </p>
      <img v-show="!isChange2 && !content" class='profile-content-cancel' src="../assets/images/X.png" alt="" @click="cancelInput">
      <img v-show="!isChange2 && content" class='profile-content-cancel' src="../assets/images/pngguru.com (1).png" alt="" @click="changeInput">
    </div>
    <div class="wrap-container profile-wrap">
      <div class='profile-head-area'>
        <div class='edit-profile-img'>
          <div v-if='!profileImg'>
            <img class='profile-img' src="../assets/images/default-user.png" alt="">
          </div>
          <div v-if='profileImg'>
            <img class='profile-img' :src="profileImg" alt="">
          </div>
          <label for='profile-img-edit' class="edit-profile-img-edit">
            <input type="file" id="profile-img-edit" accept="image/*" @change="setProfileImg">
          </label>
        </div>
        <div class="profile-follow-box">
          <div class="profile-follow">
            <p class='profile-follow-head'>팔로워</p>
            <p class='profile-follow-content'>{{ followedCnt }}</p>
          </div>
          <div class="profile-follower">
            <p class='profile-follow-head'>팔로잉</p>
            <p class='profile-follow-content'>{{ followingCnt }}</p>
          </div>
        </div>
      </div>
      <div class="profile-edit-area">
        <p v-show="isChange && isChange2" class="my-nickname" @click="changeNickName">{{ user.nickname }}닉
          <img src="../assets/images/edit.png" alt="" class="profile-edit-img">
        </p>
        <div v-show="isChange2 && isChange" class="profile-edit-content" @click="changeContent">
          <p class='my-content'>{{user.selfintroduce}}소개
            <img src="../assets/images/edit.png" alt="" class="profile-edit-img">
          </p>
        </div>
      </div>
      <div class="profile-btn-area">
        <div v-if="isChange && isChange2" class="profile-user-change" @click="goSettings">
          <span><i class="fas fa-user-cog fa-2x profile-setting-btn"></i> 계정설정</span>
        </div>
      </div>
      <div class="profile-footer-area" v-if="isChange2 && isChange">
        <div class="profile-user-btn">
          <i class="far fa-file-image mylist-icon"></i>
        </div>
        <div class="profile-user-btn">
          <i class="fas fa-bookmark bookmark-icon"></i>
        </div>
        <div class="profile-user-btn">
          <i class="fas fa-user follower-icon"><i class="fas fa-arrow-right follow-inner"></i></i>
        </div>
        <div class="profile-user-btn">
          <i class="fas fa-user following-icon"><i class="fas fa-arrow-left follow-inner"></i></i>
        </div>       
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/profileedit.css"
import axios from 'axios'
import { mapState,mapMutations, mapActions } from 'vuex'

export default {
  name: 'ProfileEdit',
  data() {
    return {
      profileImg: false,
      nickname: '',
      isChange: true,
      content:'',
      isChange2: true,
      followingCnt :'',
      followedCnt :'',
      tempNickName:'' ,
      test:'',
    }
  },
  mounted(){
    this.defaultDark()
    let ref=this;
    let data = this.$cookies.get('auth-nickname');
    let uri = data;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.tempNickName=res;
    this.nickname=res;
    axios.get('http://localhost:8080/api/mypage/',{
      params:{nickname:res}
    })
    .then((data)=>{
      ref.followedCnt=data.data.followedCnt;
      ref.followingCnt=data.data.followingCnt;
    })
    .catch()

  },
  beforeUpdate(){
    this.profileImg=this.user.profile_img;
  },
  computed: {
    ...mapState(['isLoggedIn', 'user', 'flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    ...mapMutations(['setUserIntro','setUserNick','setToken']),
    ...mapActions(['sendUserInfo']),
    setProfileImg() {
      let ref=this;
      var frm = new FormData();
      var photoFile = document.getElementById("profile-img-edit");
      frm.append("profile-img-edit", photoFile.files[0]);
      frm.append("nickname",this.nickname);
      axios.post('http://localhost:8080/api/account/addProfileImg',frm)
      .then((data)=>{
        console.log(data)
        ref.$cookies.set('auth-token', data.data.auth_token)
        ref.setToken(data.data.auth_token)
        ref.sendUserInfo();
        ref.profileImg=ref.user.profileImg;
      })
      .catch()


    },
    changeNickName() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.add('profile-opacity-wrap')
      wrapNav.classList.add('profile-opacity-wrap')
      wrapBottom.classList.add('profile-opacity-wrap')
      hidden.style.zIndex = 6000
      this.isChange = false;
    },
    cancel() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.remove('profile-opacity-wrap')
      wrapNav.classList.remove('profile-opacity-wrap')
      wrapBottom.classList.remove('profile-opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange = true;
    },
    change() {
      let ref=this;

      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.remove('profile-opacity-wrap')
      wrapNav.classList.remove('profile-opacity-wrap')
      wrapBottom.classList.remove('profile-opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange = true;
      const formData=new FormData();
      formData.append("prev",this.tempNickName);
      formData.append("cur",this.nickname);
      axios.post('http://localhost:8080/api/account/nickchange',formData)
      .then((data)=>{
        console.log(data);
        if(data.data.result.data=="success"){
          ref.$cookies.set('auth-token', data.data.auth_token)
          ref.setToken(data.data.auth_token)
          ref.sendUserInfo();
        }
        else if(data.data.result.data=="fail"){
          console.log("중복된 닉네임인 경우");
        }
      })
      .catch()


    },
    changeContent() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')

      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.add('profile-opacity-wrap')
      wrapNav.classList.add('profile-opacity-wrap')
      wrapBottom.classList.add('profile-opacity-wrap')
      hidden.style.zIndex = 6000
      this.isChange2 = false;
    },
    cancelInput() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.remove('profile-opacity-wrap')
      wrapNav.classList.remove('profile-opacity-wrap')
      wrapBottom.classList.remove('profile-opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange2 = true;
    },
    changeInput() {
      let ref=this

      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.profile-hidden-box')
      wrapContainer.classList.remove('profile-opacity-wrap')
      wrapNav.classList.remove('profile-opacity-wrap')
      wrapBottom.classList.remove('profile-opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange2 = true;

      const formData = new FormData();
      formData.append("nickname",this.nickname);
      formData.append("selfintroduce",this.content);
      axios.put('http://localhost:8080/api/account/selfintro',formData)
      .then((data)=>{
        console.log(data);
        ref.$cookies.set('auth-token', data.data.auth_token)
        ref.setToken(data.data.auth_token)
        ref.sendUserInfo();
        
      })
      .catch()

    },
    goSettings() {
      this.$router.push('/settings')
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const PTAG = document.querySelectorAll('p')
      const H3TAG = document.querySelectorAll('h3')
      const H4TAG = document.querySelectorAll('h4')
      const changeinput = document.querySelectorAll('.profile-nick-cancel')
      const changecontent = document.querySelectorAll('.profile-content-cancel')

      const EDITPROFILEIMG = document.querySelectorAll('.profile-edit-img')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('profile-dark-content')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.add('font-dark')
        }

        for (let i=0; i<EDITPROFILEIMG.length ; i++) {
          EDITPROFILEIMG[i].classList.add('profile-edit-img-dark')
        }
        for (let i=0; i<changeinput.length ; i++) {
          changeinput[i].classList.add('img-change-dark')
        }
        for (let i=0; i<changecontent.length ; i++) {
          changecontent[i].classList.add('img-change-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')

        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('profile-dark-content')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.remove('font-dark')
        }

        for (let i=0; i<EDITPROFILEIMG.length ; i++) {
          EDITPROFILEIMG[i].classList.remove('profile-edit-img-dark')
        }
        for (let i=0; i<changeinput.length ; i++) {
          changeinput[i].classList.remove('img-change-dark')
        }
        for (let i=0; i<changecontent.length ; i++) {
          changecontent[i].classList.remove('img-change-dark')
        }
      }
    },
  },
}
</script>