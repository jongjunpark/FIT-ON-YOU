<template>
  <div class='wrap'>
    <h1 class="settings-name">계정설정</h1>
    <div class="settings-btn-area">
      <button class="btn change-password-btn settings-btn" @click="goPassword">비밀번호 변경</button>
      <button class="btn change-search-btn settings-btn" @click="deleteSearch">검색내역 지우기</button>
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
import Swal from 'sweetalert2'

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

      this.$router.push('/').catch(()=>{});
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
      this.$router.push('/newpassword').catch(()=>{})
    },
    deleteSearch() {
      let tmpNick = this.$cookies.get('auth-nickname');
      let uri = tmpNick;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let resNick = uri_dec;

      let nickname=resNick;
      axios.delete(`https://i3b304.p.ssafy.io/api/search/deleteSearchHistory/${nickname}`
      )
      .then(Swal.fire(
        '검색기록이 삭제되었습니다..',
        '새로운 검색을 입력해주세요!',
        'success'
      ))
      .catch()
    },
    deleteAllAlarm(){

      let tmpNick = this.$cookies.get('auth-nickname');
      let uri = tmpNick;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let resNick = uri_dec;
      let nickname=resNick;
      const frm = new FormData();
      frm.append('recevier',nickname);
      axios.post('https://i3b304.p.ssafy.io/api/alarm/del',frm
      )
      .then(Swal.fire(
        '알림기록이 삭제되었습니다..',
        '새로운 알람을 기대하세요!',
        'success'
      ))
      .catch()
    },

    leave(){
      let ref=this;
      let tmpNick = this.$cookies.get('auth-nickname');
      let uri = tmpNick;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let resNick = uri_dec;

      let nickname=resNick;
      const formData=new FormData();
      formData.append('nickname',nickname);

      Swal.fire({
        title: '정말로 탈퇴하실거에요?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네',
        cancelButtonText: '아니오',
      }).then((result) => {
        if (result.value) {
          axios.delete('https://i3b304.p.ssafy.io/api/account/delete',{
        data:formData,
        
      })
      .then((data)=>{
        if(data.data.data=="success"){
          ref.$cookies.remove('auth-token')
          ref.$cookies.remove('auth-nickname')
          ref.setToken(null)
          ref.setLoggedIn(false)
          ref.setUser(null)
          ref.$router.push('/').catch(()=>{})
        }
      })
      .catch()
          Swal.fire(
            '완료되었습니다.',
          ).then((result2) => {
            if (result2.value) {
              this.$router.push('/').catch(()=>{})
                }
              })
            }
          }
        )
      
    },
  },
}
</script>
