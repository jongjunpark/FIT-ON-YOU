<template>
  <div class="wrap">
    <div class="wrap-container-alarm">
      <div class="alarm-container-wrap">
        <div class="alarm-container">
          <div class="alarm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="alarm-container-message-img">
            <i class="fas fa-heart heart-alarm"></i>
            <h4 class="">Username님이 회원님의 사진을 좋아요 눌렀습니다.</h4>
            <h5 class="in-text">1달전</h5>
          </div>
          <div class="alarm-container-message-read">
            <img src="../assets/images/default-user.png" alt="" class="alarm-container-message-img">
            <i class="fas fa-user-circle user-alarm"></i>
            <h4 class="">Username님이 회원님을 팔로우하기 시작하였습니다.</h4>
            <h5 class="in-text">1달전</h5>
          </div>
          <div class="alarm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
            <i class="fas fa-comment-dots chat-alarm"></i>
            <h4 class="">Username님이 회원님의 게시물에 댓글을 남겼습니다.</h4>
            <h5 class="in-text">1년전</h5>
          </div>
          <div class="alarm-container-message-read">
            <img src="../assets/images/default-user.png" alt="" class="alarm-container-message-img">
            <i class="fas fa-user-circle user-alarm"></i>
            <h4 class="">Username님이 회원님을 팔로우하기 시작하였습니다.</h4>
            <h5 class="in-text">1달전</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/alarm.css"
import { mapState } from 'vuex'


export default {
  name: 'Alarm',
  data() {
    return {}
  },
  computed: {
    ...mapState(['flag'])
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
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const INPUT = document.querySelectorAll('input')
      
      const READ = document.querySelectorAll('.alarm-container-message-read')
      

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
        for (var i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.add('alarm-container-message-read-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
        for (var j=0; j<INPUT.length ; j++) {
          INPUT[j].classList.remove('input-dark')
        }
        
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.remove('alarm-container-message-read-dark')
        }
      }
    },
  },
}
</script>
