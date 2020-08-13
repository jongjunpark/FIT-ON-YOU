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
          <div class="profile-follow" @click='goFollower'>
            <p class='profile-follow-head'>팔로워</p>
            <p class='profile-follow-content'>{{ followedCnt }}</p>
          </div>
          <div class="profile-follow" @click='goFollowing'>
            <p class='profile-follow-head'>팔로잉</p>
            <p class='profile-follow-content'>{{ followingCnt }}</p>
          </div>
        </div>
      </div>
      <div class="profile-edit-area">
        <p class="other-nickname">{{ nickname }}</p>
        <p class="other-content">{{ selfintro }}</p>
      </div>
      <div class="profile-btn-area">     
        <div v-show="isFollwed" class="other-user-child" @click="followAdd">
          <span><i class="fas fa-user profile-other-btn" style="color: #fff"></i> 팔로우</span>
        </div>
        <div v-show="!isFollwed" class="other-user-child-no" @click="followDelete">
          <span><i class="fas fa-user profile-other-btn-no" style="color: black"></i> 팔로우 취소</span>
        </div>
        <div class="other-user-child-DM" @click="goChatting">
          <span><i class="fas fa-paper-plane profile-other-btn"></i> DM 보내기</span>
        </div>
      </div>
      <div class="profile-footer-area">
        <div class="profile-user-btn" @click='goMyFeed'>
          <i class="far fa-file-image mylist-icon"></i>
        </div>
        <div class="profile-user-btn" @click='goFollower'>
          <i class="fas fa-user following-icon"><i class="fas fa-arrow-left follow-inner"></i></i>
        </div>  
        <div class="profile-user-btn" @click='goFollowing'>
          <i class="fas fa-user follower-icon"><i class="fas fa-arrow-right follow-inner"></i></i>
        </div>
      </div>
    </div>
    
	</div>
</template>

<script>
import "../components/css/profileedit.css"
import "../components/css/otheruser.css"
import { mapState, mapMutations } from 'vuex'
import axios from 'axios'

export default {
  name: 'OtherUser',
  data() {
    return {
      profileImg: false,
      nickname:'',
      selfintro:'자기소개 입니다.',
      followingCnt:'',
      followedCnt:'',
      nick: '',
      isFollwed: true,
      followNo: false,
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
    let uNick = this.$route.params.nickname;
    this.nickname = uNick
    axios.get('https://i3b304.p.ssafy.io/api/mypage/otheruser',{
      params:{
      nickname: uNick,
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
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.nick = res
    console.log(this.nickname, this.nick)
    
    axios.get('https://i3b304.p.ssafy.io/api/isfollowed',{
      params:{
      followedUser: this.nickname,
      followingUser: this.nick
    }
    }).then((data) => {
      if (data.data.object) {
        this.isFollwed = false
        this.followNo = data.data.object.followno
      }
    })
    .catch(
    )

  },
  methods: {
    ...mapMutations(['setMyFeed','setBookMark','setFollower', 'setFollowing', 'setCuration', 'setIsMe', 'setOtherUser']),
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
    goMyFeed() {
      this.setMyFeed();
      this.setIsMe(false);
      this.setOtherUser(this.$route.params.nickname);
      this.$router.push('/profileinform')
    },
    goFollowing() {
      this.setFollowing();
      this.setIsMe(false);
      this.setOtherUser(this.$route.params.nickname);
      this.$router.push('/profileinform')
    },
    goFollower() {
      this.setFollower();
      this.setIsMe(false);
      this.setOtherUser(this.$route.params.nickname);
      this.$router.push('/profileinform')
    },
    goChatting() {
      axios.get('https://i3b304.p.ssafy.io/api/chat/existroom',{
      params:{
        firstuser: this.nickname,
        seconduser: this.nick
      }
      }).then((data)=>{
        this.$router.push(`/directmessage/${data.data.object.roomname}/${this.nickname}`)
      })
        .catch(
        )
    },
    followAdd() {
      axios.get('https://i3b304.p.ssafy.io/api/follow/add',{
      params:{
        followedUser: this.nickname,
        followingUser: this.nick
      }
      }).then((data) => {
        this.isFollwed = false
        console.log(data.data.object.followno)
        this.followNo = data.data.object.followno
        this.followedCnt = this.followedCnt + 1
      })
        .catch(
        )
    },
    followDelete() {
      console.log(this.followNo)
      axios.get('https://i3b304.p.ssafy.io/api/follow/delete',{
      params:{
        followNo: this.followNo,
      }
      }).then((data) => {
        console.log(data.data)
        this.isFollwed = true
        this.followedCnt = this.followedCnt - 1
      })
        .catch(
        )
    },
  },
}
</script>
