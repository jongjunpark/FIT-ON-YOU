<template>
	<div class="wrap">
		<div class="wrap-container profile-wrap">
      <section class='edit-profile-area'>
        <div class='edit-profile-img'>
          <div v-if='!profileImg'>
            <img class='profile-img' src="../assets/images/default-user.png" alt="">
          </div>
          <div v-if='profileImg'>
            <img class='profile-img' :src="profileImg" alt="">
          </div>
        </div>
        <div class="follow">
          <h2>팔로우</h2>
          <h3>{{followingCnt}}</h3>
        </div>
        <div class="follower">
          <h2>팔로워</h2>
          <h3>{{followedCnt}}</h3>
        </div>
      <p class="other-nickname">{{nickname}}</p>
      <div class="other-content">
        <h3>{{selfintro}}</h3>
      </div>
			<div class="parent-other">
				<div class="other-user-follow">
					<div class="other-user-child">
						<span><i class="fas fa-user fa-2x settings"></i> 팔로우</span>
					</div>
				</div>
				<div class="other-user-DM">
					<div class="other-user-child-DM">
						<span><i class="fas fa-paper-plane fa-2x settings"></i> DM 보내기</span>
					</div>
				</div>
			</div>
      </section>
    </div>
    <div class="List-parent">
      <div class="List-other">
        <i class="far fa-file-alt fa-3x mylist"></i>
      </div>
      <div class="List2-other">
        <i class="far fa-file-alt fa-3x mylist"></i>
      </div>
      <div class="List3-other">
        <i class="far fa-address-book fa-3x mylist"></i>
      </div>  
    </div>
	</div>
</template>

<script>
import "../components/css/otheruser.css"
import "../components/css/profileedit.css"
import { mapState } from 'vuex'
import axios from 'axios'

export default {
 name: 'OtherUser',
 data() {
	return {
    profileImg:false,
    nickname:'',
    selfintro:'자기소개 입니다.',
    followingCnt:'',
    followedCnt:'',


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
   let ref=this;
   let uNick=this.$route.params.nickname;
   axios.get('http://localhost:8080/api/mypage/otheruser',{
     params:{
      nickname:uNick,
    }
   }).then((data)=>{
     console.log(data);
     ref.nickname=data.data.userinfo.nickname;
     ref.profileImg=data.data.userinfo.profile_img;
     if(data.data.userinfo.selfintroduce!=null){
       ref.selfintro=data.data.userinfo.selfintroduce
     }
     ref.followedCnt=data.data.followedCnt
     ref.followingCnt=data.data.followingCnt

    
   })
   .catch(
   )

 },
 methods: {
   defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const INPUT = document.querySelectorAll('input')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        this.checked = true
        for (var i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
        for (var j=0; j<INPUT.length ; j++) {
          INPUT[j].classList.remove('input-dark')
        }
      }
    },
 },
}
</script>
