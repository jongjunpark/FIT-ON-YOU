<template>
  <div class='wrap-container hash-container'>
      <div v-show="hashResultList" class="hash-search-inner-box" v-for="hash in hashResultList" :key="hash.articleNo">
        <img :src="hash.imgList[0]" :alt="`articleNo : ${hash.articleNo}`" @click="onModal(hash.articleNo)">
      </div>
      <div v-show="hashResultList.length===0" class='hash-search-not-result'>
        <p>일치하는 검색결과가 없습니다.</p>
      </div>
      <SearchModal  v-if="showModal" @close="showModal= false"/>
  </div>
</template>

<script>
import { mapState,mapMutations } from 'vuex'
import axios from 'axios'
import SearchModal from '../components/SearchModal.vue'

export default {
  name: 'HashSearch',
  components:{
    SearchModal,
  },
  data() {
    return {
      scrollDown: 0,
      hashResultList: [],
      showModal:false,
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
    ...mapMutations(['setArticledata']),
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
        this.hashResultList = data.data.object
        console.log(data.data)
        console.log(this.hashResultList, 'rr')
      }).catch()
    },
    onModal(articleNo) {
      this.setArticledata(articleNo);
      this.showModal = true
    },
    
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
.hash-container {
  width: 100%;
  height: 70vh;
  overflow: auto;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  flex-direction: row;
}

.hash-container::-webkit-scrollbar {
  width: 0;
  height: 0;
}

.hash-search-inner-box {
  width: 32.5%;
  height: 0;
  padding-top: 32.5%;
  margin: 0.3vw;
  background-color: grey;
  position: relative
}
@media (min-width:1200px) {
  .hash-search-inner-box {
    margin: 1px;
  }
}

.hash-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}

.hash-search-not-result {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  padding-top: 20vh;
  font-size: 2.5vh;
  font-weight: 700;
}
</style>