<template>
  <div id="app">
    <div class="nav-base"></div>
    <div id="nav">
      <div class="nav-logo">Logo</div>
      <div class="nav-user">
        <div @click='setUserBar' class="nav-user-icon nav-user-img">img</div>
        <transition name='slide-user-bar'>
          <div v-if="isUserIcon" class="user-bar">
            <ul class='user-bar-list'>
              <li class="nav-user-icon user-bar-menu">
                <i class="user-bar-img fas fa-user"></i>
              </li>
              <li class="nav-user-icon user-bar-menu">
                <i class="user-bar-img fas fa-comment-alt"></i>
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
    
    <div id="nav2">
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
      <div class="write-icon">
        <i class="write-icon-img fas fa-pen"></i>
      </div>
    </div>
  </div>
</template>

<script>
import "./assets/css/common.css";
export default {
  name: 'App',
  data() {
    return {
      isUserIcon: false
    }
  },
  methods: {
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
    },
  }
}
</script>
