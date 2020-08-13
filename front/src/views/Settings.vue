<template>
  <div class='wrap'>
    <h1 class="settings-name">계정설정</h1>
    <div class="settings-btn-area">
      <button class="btn change-password-btn settings-btn" @click="goPassword">비밀번호 변경</button>
      <button class="btn change-search-btn settings-btn">검색내역 지우기</button>
      <button class="btn change-search-btn settings-btn" @click="deleteAllAlarm">알림내역 지우기</button>
      <button class="btn logout-btn settings-btn" @click="goLogout" id="customBtn">로그아웃</button>
      <button class="btn signout-btn settings-btn" @click="leave">회원 탈퇴</button>
    </div>
  </div>
</template>

<script>
import "../components/css/settings.css"
import { mapState, mapMutations, mapActions  } from 'vuex' 
import axios from 'axios'

export default {
  name: 'Settings',
  data() {
    return {}
  },
  computed: {
    ...mapState(['isLoggedIn', 'authToken', 'user', 'flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
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

      this.$router.push('/');
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const H1TAG = document.querySelectorAll('h1')
      const LABEL = document.querySelectorAll('label')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<H1TAG.length ; i++) {
          H1TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<LABEL.length ; i++) {
          LABEL[i].classList.remove('font-dark')
        }
      }
    },


    goPassword() {
      this.$router.push('/newpassword')
    },
    deleteAllAlarm(){
      let nickname=this.user.nickname
      console.log(nickname,1);
      const frm = new FormData();
      frm.append('recevier',nickname);
      axios.post('https://i3b304.p.ssafy.io/api/alarm/del',frm
      )
      .then(console.log("성공"))
      .catch(console.log("실패"))
    },

    leave(){
      let ref=this;

      const formData=new FormData();
      let nickname=this.user.nickname;
      console.log(nickname);
      formData.append('nickname',nickname);
      axios.delete('https://i3b304.p.ssafy.io/api/account/delete',{
        data:formData,
        
      })
      .then((data)=>{
        console.log(data)
        if(data.data.data=="success"){
          ref.$cookies.remove('auth-token')
          ref.$cookies.remove('auth-nickname')
          ref.setToken(null)
          ref.setLoggedIn(false)
          ref.setUser(null)
          ref.$router.push('/')
        }
        else{
          console.log("fail..");
        }
      })
      .catch()
    },
  },
}
</script>
