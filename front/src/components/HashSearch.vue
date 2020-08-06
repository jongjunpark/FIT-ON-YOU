<template>
  <div class='wrap-container'>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="$" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="$" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HashSearch',
  mounted() {
    this.defaultDark()
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')

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
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
      }
    },
  },
}
</script>

<style scoped>
.hash-search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.hash-search-inner-box {
  width: 33%;
  padding-top: 30%;
  margin: 1px;
  background-color: grey;
  position: relative
}

.hash-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
</style>