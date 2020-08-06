<template>
  <div class='wrap'>
    <h1 class="settings-name">계정설정</h1>
    <button class="btn change-password-btn" @click="goPassword">비밀번호 변경</button>
    <button class="btn change-search-btn">검색내역 지우기</button>
    <button class="btn change-search-btn">알림내역 지우기</button>
    <button class="btn logout-btn" @click="goLogout">로그아웃</button>
    <button class="btn signout-btn">회원 탈퇴</button>
  </div>
</template>

<script>
import "../components/css/settings.css"
import { mapState, mapMutations, mapActions  } from 'vuex' 


export default {
  name: 'Settings',
  data() {
    return {}
  },
  computed: {
    ...mapState(['isLoggedIn', 'authToken', 'user'])
  },
  mounted() {
    this.defaultDark()
  },
  methods: {
    ...mapMutations(['setToken', 'setLoggedIn', 'setUser']),
    ...mapActions([]),
    goLogout() {
      this.$cookies.remove('auth-token')
      this.$cookies.remove('auth-nickname')
      this.setToken(null)
      this.setLoggedIn(false)
      this.setUser(null)
      this.$router.push('/')
    },
    goPassword() {
      this.$router.push('/newpassword')
    },
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
