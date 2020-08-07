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
      const READ = document.querySelectorAll('.alarm-container-message-read')
      const H4TAG = document.querySelectorAll('.alarm-container-message > h4')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.add('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')      
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.remove('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.remove('font-dark')
        }
      }
    },
  },
}
</script>
