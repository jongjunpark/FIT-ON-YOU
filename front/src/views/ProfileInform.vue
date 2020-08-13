<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class="profile-nav">
        <div @click="goMyFeed" class="profile-nav-btn0 profile-nav-icon"><i class="far fa-file-image"></i></div>
        <div @click="goBookMark" class="profile-nav-btn1 profile-nav-icon"><i class="fas fa-bookmark"></i></div>
        <div @click="goFollowing" class="profile-nav-btn2 profile-nav-icon"><i class="fas fa-user following"><i class="fas fa-arrow-right follow-inner"></i></i></div>
        <div @click="goFollower" class="profile-nav-btn3 profile-nav-icon"><i class="fas fa-user follower"><i class="fas fa-arrow-left follow-inner"></i></i></div>
        <div @click="goCuration" class="profile-nav-btn4 profile-nav-icon"><i class="fas fa-check curation"></i></div>
        <div @click="goBack" class="profile-nav-btn5 profile-nav-icon profile-nav-back"><i class="fas fa-reply"></i></div>
        <div class="profile-nav-select"></div>
      </div>
      <MyFeed v-if="isMyFeed"></MyFeed>
      <BookMark v-if="isBookMark"></BookMark>
      <Following v-if="isFollowing"></Following>
      <Follower v-if="isFollower"></Follower>
      <Curation v-if="isCuration"></Curation>
    </div>
  </div>
</template>

<script>
import MyFeed from '../components/MyFeed.vue'
import BookMark from '../components/BookMark.vue'
import Following from '../components/Following.vue'
import Follower from '../components/Follower.vue'
import Curation from '../components/Curation.vue'
import '../components/css/profileinform.css'
import { mapState, mapMutations } from 'vuex'

export default {
  name: 'ProfileInform',
  components: { 
    MyFeed,
    BookMark,
    Following,
    Follower,
    Curation,
  },
  data() {
    return {
    }
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  computed: {
    ...mapState(['flag', 'isMyFeed', 'isBookMark', 'isFollowing', 'isFollower', 'isCuration']),
  },
  methods: {
    ...mapMutations(['setMyFeed','setBookMark','setFollower','setFollowing', 'setCuration']),
    goMyFeed() {
      this.setMyFeed();
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      const curation = document.querySelector('.curation')
      myFeed.style.color = '#5AAEFF'
      bookMark.style.color = 'grey'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      curation.style.color = 'grey'
      selectBar.classList.add('go-myfeed-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-follower-menu')
      selectBar.classList.remove('go-curation-menu')
    },
    goBookMark() {
      this.setBookMark();
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      const curation = document.querySelector('.curation')
      myFeed.style.color = 'grey'
      bookMark.style.color = '#5AAEFF'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      curation.style.color = 'grey'
      selectBar.classList.add('go-bookmark-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-follower-menu')
      selectBar.classList.remove('go-curation-menu')
    },
    goFollowing() {
      this.setFollowing();
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      const curation = document.querySelector('.curation')
      myFeed.style.color = 'grey'
      bookMark.style.color = 'grey'
      following.style.color = '#5AAEFF'; following.children[0].style.backgroundColor = '#5AAEFF';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      curation.style.color = 'grey'
      selectBar.classList.add('go-following-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.remove('go-follower-menu')
      selectBar.classList.remove('go-curation-menu')
    },
    goFollower() {
      this.setFollower();
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      const curation = document.querySelector('.curation')
      myFeed.style.color = 'grey'
      bookMark.style.color = 'grey'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = '#5AAEFF'; follower.children[0].style.backgroundColor = '#5AAEFF';
      curation.style.color = 'grey'
      selectBar.classList.add('go-follower-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.remove('go-curation-menu')
    },
    goCuration() {
      this.setCuration();
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      const curation = document.querySelector('.curation')
      myFeed.style.color = 'grey'
      bookMark.style.color = 'grey'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      curation.style.color = '#5AAEFF'
      selectBar.classList.remove('go-follower-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.add('go-curation-menu')
    },
    goBack() {
      this.$router.go(-1)
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
      }
    },
  },
  mounted() {
    this.defaultDark()
    
    if (this.isMyFeed) {
      this.goMyFeed()
    } else if (this.isBookMark) {
      this.goBookMark()
    } else if (this.isFollowing) {
      this.goFollowing()
    } else if (this.isFollower) {
      this.goFollower()
    } else if (this.isCuration) {
      this.goCuration()
    }
  }
}
</script>