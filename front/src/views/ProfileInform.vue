<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class="profile-nav">
        <div class="profile-nav-select"></div>
        <div @click="goMyFeed" class="profile-nav-btn profile-nav-icon"><i class="far fa-file-image"></i></div>
        <div @click="goBookMark" class="profile-nav-btn profile-nav-icon"><i class="fas fa-bookmark"></i></div>
        <div @click="goFollowing" class="profile-nav-btn profile-nav-icon"><i class="fas fa-user following"><i class="fas fa-arrow-right follow-inner"></i></i></div>
        <div @click="goFollower" class="profile-nav-btn profile-nav-icon"><i class="fas fa-user follower"><i class="fas fa-arrow-left follow-inner"></i></i></div>
        <div @click="goProfile" class="profile-nav-btn profile-nav-icon profile-nav-back"><i class="fas fa-reply"></i></div>
      </div>
      <MyFeed v-if="isMyFeed"></MyFeed>
      <BookMark v-if="isBookMark"></BookMark>
      <Following v-if="isFollowing"></Following>
      <Follower v-if="isFollower"></Follower>
    </div>
  </div>
</template>

<script>
import MyFeed from '../components/MyFeed.vue'
import BookMark from '../components/BookMark.vue'
import Following from '../components/Following.vue'
import Follower from '../components/Follower.vue'

export default {
  name: 'ProfileInform',
  components: { 
    MyFeed,
    BookMark,
    Following,
    Follower,
  },
  data() {
    return {
      isMyFeed: true,
      isBookMark: false,
      isFollowing: false,
      isFollower: false,
    }
  },
  methods: {
    goMyFeed() {
      this.isMyFeed = true; this.isBookMark = false; this.isFollowing = false; this.isFollower = false;
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      myFeed.style.color = '#5AAEFF'
      bookMark.style.color = 'grey'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      selectBar.classList.add('go-myfeed-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-follower-menu')
    },
    goBookMark() {
      this.isMyFeed = false; this.isBookMark = true; this.isFollowing = false; this.isFollower = false;
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      myFeed.style.color = 'grey'
      bookMark.style.color = '#5AAEFF'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      selectBar.classList.add('go-bookmark-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-follower-menu')
    },
    goFollowing() {
      this.isMyFeed = false; this.isBookMark = false; this.isFollowing = true; this.isFollower = false;
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      myFeed.style.color = 'grey'
      bookMark.style.color = 'grey'
      following.style.color = '#5AAEFF'; following.children[0].style.backgroundColor = '#5AAEFF';
      follower.style.color = 'grey'; follower.children[0].style.backgroundColor = 'grey';
      selectBar.classList.add('go-following-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-bookmark-menu')
      selectBar.classList.remove('go-follower-menu')
    },
    goFollower() {
      this.isMyFeed = false; this.isBookMark = false; this.isFollowing = false; this.isFollower = true;
      const selectBar = document.querySelector('.profile-nav-select')
      const myFeed = document.querySelector('.fa-file-image')
      const bookMark = document.querySelector('.fa-bookmark')
      const following = document.querySelector('.following')
      const follower = document.querySelector('.follower')
      myFeed.style.color = 'grey'
      bookMark.style.color = 'grey'
      following.style.color = 'grey'; following.children[0].style.backgroundColor = 'grey';
      follower.style.color = '#5AAEFF'; follower.children[0].style.backgroundColor = '#5AAEFF';
      selectBar.classList.add('go-follower-menu')
      selectBar.classList.remove('go-myfeed-menu')
      selectBar.classList.remove('go-following-menu')
      selectBar.classList.remove('go-bookmark-menu')
    },
    goProfile() {
      this.$router.push("/profileedit")
    }
  },
  mounted() {
    if (this.isMyFeed) {
      this.goMyFeed()
    } else if (this.isBookMark) {
      this.goBookMark()
    } else if (this.isFollowing) {
      this.goFollowing()
    } else if (this.isFollower) {
      this.goFollower()
    }
  }
}
</script>

<style scoped>
.profile-nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 50%;
  margin: 5px 0;
  position: relative;
  /* background-color: gray; */
}

.profile-nav .profile-nav-select {
  position: absolute;
  bottom: -5px;
  left: 0;
  background-color: #5AAEFF;
  border-radius: 5px;
  height: 5px;
  width: 20%;
}

.profile-nav .fas, .profile-nav .far{
  margin: 0;
  color: grey;
  font-size: 200%;
}

@media (max-width: 380px) {
  .profile-nav .fas, .profile-nav .far {
    font-size: 170%;
  }
}

@media (max-width: 320px) {
  .profile-nav .fas, .profile-nav .far {
    font-size: 120%;
  }
}


.profile-nav .profile-nav-btn:hover .fas {
  color: #5AAEFF !important;
}

.profile-nav .profile-nav-btn:hover .far {
  color: #5AAEFF !important;
}

.profile-nav .profile-nav-btn:hover .fa-user .follow-inner {
  background-color: #5AAEFF !important;
  color: white !important;
} 

.profile-nav .profile-nav-icon {
  margin-right: 1%;
  width: 20%;
  height: 43px;
  line-height: 3.5;
  text-align: center;
  background-color: #fff;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  border-radius: 5px;
  cursor: pointer;
}
@media (max-width: 380px) {
  .profile-nav .profile-nav-icon {
    height: 35px;
    line-height: 2.6;
  }
}

@media (max-width: 320px) {
  .profile-nav .profile-nav-icon {
    height: 25px;
    line-height: 1.7;
  }
}


.profile-nav .profile-nav-back {
  width: 10%;
  padding: 0;
  line-height: 3;
}
@media (max-width: 380px) {
  .profile-nav .profile-nav-back {
    line-height: 2.4;
  }
}

@media (max-width: 320px) {
  .profile-nav .profile-nav-back {
    line-height: 1.5;
  }
}



.profile-nav .profile-nav-back .fa-reply {
  font-size: 150%;
}

@media (max-width: 380px) {
  .profile-nav .profile-nav-back .fa-reply {
    font-size: 130%;
  }
}

@media (max-width: 320px) {
  .profile-nav .profile-nav-back .fa-reply {
    font-size: 100%;
  }
}

.profile-nav .profile-nav-icon .following{
  position: relative;
}

.profile-nav .profile-nav-icon .follower{
  position: relative;
  top: 0;
  right: 0;
}

.profile-nav .profile-nav-icon .follow-inner{
  font-size: 40%;
  position: absolute;
  bottom: 0;
  right: -30%;
  color: white;
  background-color: grey;
  padding: 2px;
  border-radius: 50%;
}
@media (max-width: 380px) {
  .profile-nav .profile-nav-icon .follow-inner {
    font-size: 30%;
  }
}

@media (max-width: 280px) {
  .profile-nav .profile-nav-icon .follow-inner {
    font-size: 20%;
  }
}


.go-myfeed-menu {
  left: 0 !important;
  transition: 0.5s ease-out;
}

.go-bookmark-menu {
  left: 22.3% !important;
  transition: 0.5s ease-out;
}

.go-following-menu {
  left: 44.6% !important;
  transition: 0.5s ease-out;
}

.go-follower-menu {
  left: 66.9% !important;
  transition: 0.5s ease-out;
}

.test-content-box {
  width: 100%;
  height: 500px;
  background-color: grey;
  opacity: 0.5;
  margin-top: 30px;
}
</style>