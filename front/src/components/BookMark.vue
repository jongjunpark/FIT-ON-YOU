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
      axios.get('https://i3b304.p.ssafy.io/api/mypage/bookmark',{
        params:{
          nickname:this.name,
        }
      })
      .then((data)=>{
        console.log(data,"bookmark");
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
  cursor: pointer;
}
@media (min-width:1200px) {
  .bookmark-search-inner-box {
    margin: 1px;
  }
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