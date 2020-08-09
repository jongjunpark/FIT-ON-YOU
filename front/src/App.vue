<template>
  <div id="app">
    <div class="nav-base"></div>
    <div id="nav">
      <div class="nav-logo">
        <i @click='goHome' class="fas fa-hat-cowboy"></i>
      </div>
      <div class="nav-user" >
        <div @click='setUserBar' class="nav-user-img">
          <i class="fas fa-bars"></i>
        </div>
        <transition name='slide-user-bar'>
          <div v-show="isUserIcon" class="user-bar">
            <ul class='user-bar-list'>
              <li class="nav-user-icon user-bar-menu"  @click="goProfile">
                <i class="user-bar-img fas fa-user"></i>
              </li>
              <li class="nav-user-icon user-bar-menu" @click="goDM">
                <!-- <i class="user-bar-img fas fa-comment-alt"></i> -->
                <i class="user-bar-img far fa-paper-plane"></i>
              </li>
              <li class="nav-user-icon user-bar-menu" @click="goAlarm">
                <i class="user-bar-img fas fa-bell"></i>
              </li>
            </ul>
          </div>
        </transition>
      </div>
    </div>
    
    <router-view/>
    
    <div v-if="isLoggedIn" id="nav2" >
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
     <div class="darkmode" >
      <input type="checkbox" id="theme-toggle" @click="darkOn" v-model="checked">
      <label for="theme-toggle"><span></span></label>
    </div>
    
  </div>
</template>

<script>
import "./assets/css/common.css";
import "./assets/css/darkmode.scss";
import { mapState, mapGetters, mapMutations, mapActions } from 'vuex';
// import axios from 'axios';


export default {
  name: 'App',
  data() {
    return {
      isUserIcon: false,
      checked: false,
    }
  },
  computed: {
    ...mapState(['authToken', 'isLoggedIn', 'user', 'flag']),
    ...mapGetters([])
  },
  watch: {
    checked() {
      this.setFlag(this.checked)
    },
    flag() {
      this.defaultDark()
    }
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
    this.defaultDark()

  },
  methods: {
    ...mapMutations(['setLoggedIn', 'setToken', 'setUser', 'setFlag']),
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
    },
    goDM() {
      this.isUserIcon = false;
      this.$router.push('/dm')
    },
    goAlarm() {
      this.isUserIcon = false;
      this.$router.push('/alarm')
    },
    darkOn() {
      // const HTML = document.querySelector('html')
      // const wrap = document.querySelector('.wrap')
      // const NAV = document.querySelector('#nav')
      // const NAVBASE = document.querySelector('.nav-base')
      // const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const Dark = this.$cookies.get('dark')
      // const INPUT = document.querySelectorAll('input')
      // const HAMBURGER = document.querySelector('.fa-bars') 
      // const TEXTAREA = document.querySelectorAll('textarea')
      // const H1TAG = document.querySelectorAll('h1')
      // const H2TAG = document.querySelectorAll('h2')
      // const H3TAG = document.querySelectorAll('h3')
      // const ALARM_H4 = document.querySelectorAll('.alarm-container-message > h4')
      // const PTAG = document.querySelectorAll('p')
      // const LABEL = document.querySelectorAll('label')
      // const SPAN = document.querySelectorAll('span')
      // const USERBAR = document.querySelectorAll('.nav-user-icon')
      // const EDITPROFILEIMG = document.querySelectorAll('.profile-edit-img')


      if (Dark === 'on') {
        // HTML.classList.add('black')
        // wrap.classList.add('wrap-dark')
        // NAV.classList.add('nav-dark')
        // NAVBASE.classList.add('nav-dark')
        // NAVLOGO.classList.add('nav-logo-dark')
        // HAMBURGER.classList.add('fa-bars-dark')
        // for (let i=0; i<H1TAG.length ; i++) {
        //   H1TAG[i].classList.add('font-dark')
        // }
        // for (let i=0; i<H2TAG.length ; i++) {
        //   H2TAG[i].classList.add('font-dark')
        // }
        // for (let i=0; i<H3TAG.length ; i++) {
        //   H3TAG[i].classList.add('font-dark')
        // }
        // for (let i=0; i<ALARM_H4.length ; i++) {
        //   ALARM_H4[i].classList.add('font-dark')
        // }
        // for (let i=0; i<PTAG.length ; i++) {
        //   PTAG[i].classList.add('font-dark')
        // }
        // for (let i=0; i<LABEL.length ; i++) {
        //   LABEL[i].classList.add('font-dark')
        // }
        // for (let i=0; i<INPUT.length ; i++) {
        //   INPUT[i].classList.add('input-dark')
        // }
        // for (let i=0; i<TEXTAREA.length ; i++) {
        //   TEXTAREA[i].classList.add('textarea-dark')
        // }
        // for (let i=0; i<SPAN.length ; i++) {
        //   SPAN[i].classList.add('font-dark')
        // }
        // for (let i=0; i<USERBAR.length ; i++) {
        //   USERBAR[i].classList.add('user-bar-dark')
        // }
        // for (let i=0; i<EDITPROFILEIMG.length ; i++) {
        //   EDITPROFILEIMG[i].classList.add('profile-edit-img-dark')
        // }

        this.$cookies.set('dark', 'off')

      } else {
        // HTML.classList.remove('black')
        // wrap.classList.remove('wrap-dark')
        // NAV.classList.remove('nav-dark')
        // NAVBASE.classList.remove('nav-dark')
        // NAVLOGO.classList.remove('nav-logo-dark')

        // for (let i=0; i<H1TAG.length ; i++) {
        //   H1TAG[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<H2TAG.length ; i++) {
        //   H2TAG[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<H3TAG.length ; i++) {
        //   H3TAG[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<ALARM_H4.length ; i++) {
        //   ALARM_H4[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<PTAG.length ; i++) {
        //   PTAG[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<LABEL.length ; i++) {
        //   LABEL[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<INPUT.length ; i++) {
        //   INPUT[i].classList.remove('input-dark')
        // }
        // for (let i=0; i<TEXTAREA.length ; i++) {
        //   TEXTAREA[i].classList.remove('textarea-dark')
        // }
        // for (let i=0; i<SPAN.length ; i++) {
        //   SPAN[i].classList.remove('font-dark')
        // }
        // for (let i=0; i<USERBAR.length; i++) {
        //   USERBAR[i].classList.remove('user-bar-dark')
        // }
        // for (let i=0; i<EDITPROFILEIMG.length ; i++) {
        //   EDITPROFILEIMG[i].classList.remove('profile-edit-img-dark')
        // }
        // HAMBURGER.classList.remove('fa-bars-dark')
        this.$cookies.set('dark', 'on')
      }
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const HAMBURGER = document.querySelector('.fa-bars')
      const USERBAR = document.querySelectorAll('.nav-user-icon')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        HAMBURGER.classList.add('fa-bars-dark')
        for (let i=0; i<USERBAR.length; i++) {
          USERBAR[i].classList.add('user-bar-dark')
        }
        this.checked = true
      } else {
        HTML.classList.remove('black')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        HAMBURGER.classList.remove('fa-bars-dark')
        for (let i=0; i<USERBAR.length; i++) {
          USERBAR[i].classList.remove('user-bar-dark')
        }
        this.checked = false
      }
    }, 
  }
}
</script>
