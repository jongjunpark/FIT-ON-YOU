<template>
	<div class="wrap">
		<div class="wrap-container profile-wrap">
      <div class='profile-head-area'>
        <div class='edit-profile-img'>
          <div v-if='!profileImg'>
            <img class='profile-img' src="../assets/images/default-user.png" alt="">
          </div>
          <div v-if='profileImg'>
            <img class='profile-img' :src="profileImg" alt="">
          </div>
        </div>
        <div class="profile-follow-box">
          <div class="profile-follow">
            <p class='profile-follow-head'>팔로워</p>
            <p class='profile-follow-content'>123,456</p>
          </div>
          <div class="profile-follower">
            <p class='profile-follow-head'>팔로잉</p>
            <p class='profile-follow-content'>123,456</p>
          </div>
        </div>
      </div>
      <div class="profile-edit-area">
        <p class="other-nickname">UserName</p>
        <p class="other-content">자기소개 입니다.</p>
      </div>
      <div class="profile-btn-area">     
        <div class="other-user-child">
          <span><i class="fas fa-user profile-other-btn"></i> 팔로우</span>
        </div>
        <div class="other-user-child-DM">
          <span><i class="fas fa-paper-plane profile-other-btn"></i> DM 보내기</span>
        </div>
      </div>
      <div class="profile-footer-area">
        <div class="profile-user-btn">
          <i class="far fa-file-image mylist-icon"></i>
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
import "../components/css/otheruser.css"
import { mapState } from 'vuex'

export default {
 name: 'OtherUser',
 data() {
	return {
		profileImg: '',
	}
 },
 computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
 mounted() {
   this.defaultDark()
 },
 methods: {
   defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const PTAG = document.querySelectorAll('p')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
      }
    },
 },
}
</script>
