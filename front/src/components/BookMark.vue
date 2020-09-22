<template>
  <div class='bookmark-container'>
    <div class="bookmark-search-inner-box" v-for="bookMark in bookMarkList" :key="bookMark.articleNo">
      <img :src="bookMark.imageUrl" :alt="`articleNo : ${bookMark.articleNo}`"  @click="onModal(bookMark.articleNo)">
    </div>
    <SearchModal  v-if="showModal" @close="showModal= false"/>
  </div>
</template>

<script>
import { mapState ,mapMutations} from 'vuex'
import SearchModal from '../components/SearchModal.vue'
import axios from 'axios'
export default {
  name: 'BookMark',
  components:{
    SearchModal
  },
  data(){
    return{
      bookMarkList:[],
      showModal:false,
      name: '',
    }
  },
  mounted() {
    this.defaultDark()
    this.name = this.$route.params.name
    this.getBookMark()
  },
  computed: {
    ...mapState(['flag','articledata'])
  },
  watch: {
    flag() {
      this.defaultDark()
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
    getBookMark() {
      // let data = this.$cookies.get('auth-nickname');
      // let uri = data;
      // let uri_enc = encodeURIComponent(uri);
      // let uri_dec = decodeURIComponent(uri_enc);
      // let res = uri_dec;
      axios.get('http://localhost:8080/api/mypage/bookmark',{
        params:{
          nickname:this.name,
        }
      })
      .then((data)=>{
        this.bookMarkList = data.data.bmImgList
      })
    },
    onModal(articleNo) {
      this.setArticledata(articleNo);
      this.showModal = true
    },
  },
}
</script>

<style scoped>
.bookmark-container {
  width: 100%;
  height: 70vh;
  overflow: auto;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
}

.bookmark-container::-webkit-scrollbar {
  width: 0;
  height: 0;
}

.bookmark-search-inner-box {
  width: 32.5%;
  height: 0;
  padding-top: 32.5%;
  margin: 0.3vw;
  background-color: grey;
  position: relative;
  transition: 0.2s ease;
  cursor: pointer;
}
@media (min-width:1200px) {
  .bookmark-search-inner-box {
    margin: 1px;
  }
}

.bookmark-search-inner-box:hover {
  transform: scale(1.1);
  z-index: 10;
  box-shadow: 0 5px 20px rgba(0,0,0,1);
}

.bookmark-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
</style>