<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class="search-box">
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'Community',
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  methods: {
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
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
      }
    },
  },
  mounted() {
    this.goCommunity()
    this.defaultDark()
  }
}
</script>

<style>
.search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.search-inner-box {
  width: 30%;
  padding-top: 30%;
  margin: 5px;
  background-color: grey;
  position: relative
}
</style>