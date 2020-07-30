<template>
  <div class='wrap'>
    <div class="hidden-box">
      <p v-if="!isChange" class="nickInput">
        <input type="text" :placeholder="user.nickname" v-model="nickname" maxlength="20">
      </p>
      <img v-if="!isChange && !nickname" class='nick-cancel' src="../assets/images/X.png" alt="" @click="cancel">
      <img v-if="!isChange && nickname" class='nick-cancel' src="../assets/images/pngguru.com (1).png" alt="" @click="change">
      
      <p v-if="!isChange2" class="contentInput">
        <input type="text" :placeholder="user.selfintroduce" v-model="content" maxlength="100">
      </p>
      <img v-if="!isChange2 && !content" class='content-cancel' src="../assets/images/X.png" alt="" @click="cancelInput">
      <img v-if="!isChange2 && content" class='content-cancel' src="../assets/images/pngguru.com (1).png" alt="" @click="changeInput">
    </div>
    <div class="wrap-container profile-wrap">
      <section class='edit-profile-area'>
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
        <div class="follow">
          <h3>팔로우</h3>
          <h4>123,456</h4>
        </div>
        <div class="follower">
          <h3>팔로워</h3>
          <h4>123,456</h4>
        </div>
      <p v-if="isChange && isChange2" class="nickname" @click="changeNickName">{{ user.nickname }} <img src="../assets/images/edit.png" alt="" class="edit-img"></p>
      <div v-if="isChange2 && isChange" class="edit-content" @click="changeContent">
        <h3>자기소개 입니다. <img src="../assets/images/edit.png" alt="" class="edit-img"></h3>
      </div>
      </section>
    </div>
    <div v-if="isChange && isChange2" class="user-change">
        <span><i class="fas fa-user-cog fa-2x settings"></i> 계정설정</span>
      </div>
  </div>
</template>

<script>
import "../components/css/profileedit.css"
import { mapState } from 'vuex'

export default {
  name: 'ProfileEdit',
  data() {
    return {
      profileImg: false,
      nickname: '',
      isChange: true,
      content:'',
      isChange2: true,
    }
  },
  computed: {
    ...mapState(['isLoggedIn', 'user'])
  },
  methods: {
    setProfileImg() {
      var frm = new FormData();
      var photoFile = document.getElementById("profile-img-edit");
      frm.append("profile-img-edit", photoFile.files[0]);
      console.log(photoFile.files[0].name);
      this.profileImg = URL.createObjectURL(photoFile.files[0]);
    },
    changeNickName() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.add('opacity-wrap')
      wrapNav.classList.add('opacity-wrap')
      wrapBottom.classList.add('opacity-wrap')
      hidden.style.zIndex = 1000
      this.isChange = false;
    },
    cancel() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.remove('opacity-wrap')
      wrapNav.classList.remove('opacity-wrap')
      wrapBottom.classList.remove('opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange = true;
    },
    change() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.remove('opacity-wrap')
      wrapNav.classList.remove('opacity-wrap')
      wrapBottom.classList.remove('opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange = true;
    },
    changeContent() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')

      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.add('opacity-wrap')
      wrapNav.classList.add('opacity-wrap')
      wrapBottom.classList.add('opacity-wrap')
      hidden.style.zIndex = 1000
      this.isChange2 = false;
    },
    cancelInput() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.remove('opacity-wrap')
      wrapNav.classList.remove('opacity-wrap')
      wrapBottom.classList.remove('opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange2 = true;
    },
    changeInput() {
      const wrapContainer = document.querySelector('.wrap-container')
      const wrapNav = document.querySelector('#nav')
      const wrapBottom = document.querySelector('#nav2')
      const hidden = document.querySelector('.hidden-box')
      wrapContainer.classList.remove('opacity-wrap')
      wrapNav.classList.remove('opacity-wrap')
      wrapBottom.classList.remove('opacity-wrap')
      hidden.style.zIndex = -1000
      this.isChange2 = true;
    },

  },
}
</script>