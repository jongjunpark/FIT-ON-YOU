<template>
  <div id="app">
    <div class="nav-base"></div>
    <div id="nav">
      <div class="nav-logo">
        <i @click='goHome' class="fas fa-hat-cowboy"></i>
      </div>
      <div class="nav-user" v-show="isLoggedIn">
        <div @click='setUserBar' class="nav-user-img">
          <i class="fas fa-bars"></i>
        </div>
        <transition name='slide-user-bar'>
          <div v-if="isUserIcon" class="user-bar">
            <ul class='user-bar-list'>
              <li class="nav-user-icon user-bar-menu"  @click="goProfile">
                <i class="user-bar-img fas fa-user"></i>
              </li>
              <li class="nav-user-icon user-bar-menu">
                <!-- <i class="user-bar-img fas fa-comment-alt"></i> -->
                <i class="user-bar-img far fa-paper-plane"></i>
              </li>
              <li class="nav-user-icon user-bar-menu">
                <i class="user-bar-img fas fa-bell"></i>
              </li>
            </ul>
          </div>
        </transition>
      </div>
    </div>
    
    <router-view/>
    
    <div v-show="isLoggedIn" id="nav2">
      <div class="bottom-nav">
        <div class='menu-bar-list'>
          <div class="menu-bar-select"></div>
          <div @click="goNewsFeed" class="nav-menu-icon">
            <i class="menu-bar-img far fa-newspaper"></i>
          </div>
          <div @click="goSearch" class="nav-menu-icon">
            <i class="menu-bar-img fas fa-search"></i>
          </div>
          <div @click="goCommunity" class="nav-menu-icon">
            <i class="menu-bar-img fas fa-users"></i>
          </div>
        </div>
      </div>
      <div @click='goWrite' class="write-icon">
        <i class="write-icon-img fas fa-pen"></i>
      </div>
    </div>
  </div>
</template>

<script>
import "./assets/css/common.css";
import { mapState, mapGetters, mapMutations, mapActions } from 'vuex';
// import axios from 'axios';


export default {
  name: 'App',
  data() {
    return {
      isUserIcon: false
    }
  },
  computed: {
    ...mapState(['authToken', 'isLoggedIn', 'user']),
    ...mapGetters([])
  },
 
  mounted() {
     if (this.$cookies.isKey('auth-token')) {
      this.setLoggedIn(true);
      this.setToken(this.$cookies.get('auth-token'));
      this.sendUserInfo();
    }
    else {
      this.setLoggedIn(false);
      this.setUser('')
    }

  },
  methods: {
    ...mapMutations(['setLoggedIn', 'setToken', 'setUser']),
    ...mapActions(['sendUserInfo']),

    setUserBar() {
      this.isUserIcon = !this.isUserIcon
    },
    goNewsFeed() {
      const selectBar = document.querySelector('.menu-bar-select')
      const newsFeed = document.querySelector('.fa-newspaper')
      const search = document.querySelector('.fa-search')
      const community = document.querySelector('.fa-users')
      newsFeed.style.color = 'black'
      search.style.color = 'grey'
      community.style.color = 'grey'
      selectBar.classList.add('go-first-menu')
      selectBar.classList.remove('go-second-menu')
      selectBar.classList.remove('go-third-menu')
      if (this.$route.name === 'Feed') {
        this.$router.go(this.$router.currentRoute)
      } else {
        this.$router.push("/feed")
      }
    },
    goSearch() {
      const selectBar = document.querySelector('.menu-bar-select')
      const newsFeed = document.querySelector('.fa-newspaper')
      const search = document.querySelector('.fa-search')
      const community = document.querySelector('.fa-users')
      newsFeed.style.color = 'grey'
      search.style.color = 'black'
      community.style.color = 'grey'
      selectBar.classList.add('go-second-menu')
      selectBar.classList.remove('go-first-menu')
      selectBar.classList.remove('go-third-menu')
      if (this.$route.name === 'Search') {
        this.$router.go(this.$router.currentRoute)
      } else {
        this.$router.push("/search")
      }
    },
    goCommunity() {
      const selectBar = document.querySelector('.menu-bar-select')
      const newsFeed = document.querySelector('.fa-newspaper')
      const search = document.querySelector('.fa-search')
      const community = document.querySelector('.fa-users')
      newsFeed.style.color = 'grey'
      search.style.color = 'grey'
      community.style.color = 'black'
      selectBar.classList.add('go-third-menu')
      selectBar.classList.remove('go-second-menu')
      selectBar.classList.remove('go-first-menu')
      if (this.$route.name === 'Community') {
        this.$router.go(this.$router.currentRoute)
      } else {
        this.$router.push("/community")
      }
    },
    goHome() {
      if(this.isLoggedIn) {
        if(this.$route.name === 'Feed') {
          this.$router.go(this.$router.currentRoute)
        } else {
          this.$router.push("/feed")
        }
      } else {
        if(this.$route.name === 'Login') {
          this.$router.go(this.$router.currentRoute)
        } else {
          this.$router.push("/")
        }
      }
    },
    goProfile() {
      this.isUserIcon = false;
      this.$router.push('/profileedit')
    },
    goWrite() {
      this.$router.push('/write')
    }
  }
}
</script>
