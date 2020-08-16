<template>
  <div class='curation-container'>
    <div class="curation-search-inner-box" v-for="curation in curationList" :key="curation.articleNo">
      <img :src="curation.imgUrl" :alt="`articleNo : ${curation.articleNo}`" @click="onModal(curation.articleNo)">
    </div>
    <SearchModal  v-if="showModal" @close="showModal= false"/>
  </div>
</template>

<script>
import { mapState ,mapMutations} from 'vuex'
import axios from 'axios'
import SearchModal from '../components/SearchModal.vue'
export default {
  name: 'Curation',
  components:{
    SearchModal
  },
  data(){
    return{
      curationList: [],
      showModal:false,
    }
  },
  
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  mounted(){
    this.defaultDark()
    this.getCuration()
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
    getCuration() {
      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;
      axios.get('https://i3b304.p.ssafy.io/api/board/api/board/curation',{
        params:{
          username:res,
        }
      })
      .then((data)=>{
        this.curationList = data.data.object
        console.log(data)
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
.curation-container {
  width: 100%;
  height: 70vh;
  overflow: auto;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
}

.curation-container::-webkit-scrollbar {
  width: 0;
  height: 0;
}

.curation-search-inner-box {
  width: 32.8%;
  height: 0;
  padding-top: 32.8%;
  margin: 1px;
  background-color: grey;
  position: relative
}
@media (min-width:1200px) {
  .bookmark-search-inner-box {
    margin: 1px;
  }
}

.curation-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
</style>