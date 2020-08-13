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
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'HashSearch',
  data() {
    return {
      scrollDown: 0,
      hashList: [],
      username: '',
      userprofileimg: '',
      time: '',
      content: '',
      longContent: '',
      imgs: [],
      img: '',
      tags: [],
      loginUserName: '',
    }
  },
  computed: {
    ...mapState(['flag', 'hashSearchList'])
  },
  watch: {
    flag() {
      this.defaultDark()
    },
    hashSearchList() {
      this.getHashSearch()
    }
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
      }
    },
    getHashSearch() {
      axios.get(`https://i3b304.p.ssafy.io/api/search/hash/${this.scrollDown}`,{
      params:{
        findContent: this.hashSearchList,
        username: this.loginUserName,
      },
      }).then((data) => {
        console.log(data.object)
      }).catch()
    }
  },
  mounted() {
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.loginUserName = res
    this.defaultDark();
    this.getHashSearch();
  }
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
  padding-top: 33%;
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