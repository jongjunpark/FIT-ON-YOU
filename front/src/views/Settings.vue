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
  },
}
</script>
