<template>
  <div class="wrap">
    <div class="wrap-container-dm">
      <div class="dm-container-wrap">
        <div class="dm-container">
          <div class="dm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
            <h3 class='dm-user-name'>Username</h3>
            <h5 class="dm-in-text">마지막 메세지입니다.</h5>
            <h5 class="dm-in-text">1주전</h5>
          </div>
          <div class="dm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
            <h3 class='dm-user-name'>Username2</h3>
            <h5 class="dm-in-text">안녕하세요.</h5>
            <h5 class="dm-in-text">1달전</h5>
          </div>
          <div class="dm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
            <h3 class='dm-user-name'>Username</h3>
            <h5 class="dm-in-text">Lorem ipsum, dolor sit amet</h5>
            <h5 class="dm-in-text">1년전</h5>
          </div>
        </div>

      </div>
      <div class="search-dm">
        <input type="text" name="" id="" class="search-dm-input" placeholder="검색">
        <i class="fas fa-search search-img-dm"></i>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/dm.css"
import { mapState } from 'vuex'

export default {
  name: 'DM',
  data() {
    return {}
  },
  mounted() {
    this.defaultDark()
  },
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const H3TAG = document.querySelectorAll('h3')
      
      const searchIMGDM = document.querySelector('.search-img-dm')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.add('font-dark')
        }
        
        searchIMGDM.classList.add('search-img-dm-dark')

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.remove('font-dark')
        }
        
        searchIMGDM.classList.remove('search-img-dm-dark')
      }
    },
  },

}
</script>
