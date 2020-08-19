<template>
  <div class='myfeed-container'>
    <div class="myfeed-search-inner-box" v-for="myFeed in myFeedList" :key="myFeed.articleNo">
      <img :src="myFeed.imageUrl" :alt="`articleNo : ${myFeed.articleNo}`" @click="onModal(myFeed.articleNo)">
    </div>
    <SearchModal  v-if="showModal" @close="showModal= false"/>
  </div>
</template>

<script>
import { mapState ,mapMutations} from 'vuex'
import SearchModal from '../components/SearchModal.vue'
import axios from 'axios'
export default {
  name: 'MyFeed',
  components:{
    SearchModal
  },
  data(){
    return{
      myFeedList:[],
      username:'',
      showModal:false,
      name: '',
    }
  },
  computed: {
    ...mapState(['flag', 'isMe', 'otherUserName','articledata'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  mounted(){
    this.defaultDark()
    this.name = this.$route.params.name
    this.getUserFeed()
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
    getUserFeed() {
      this.username = this.$router.currentRoute.params.name
      axios.get('https://i3b304.p.ssafy.io/api/mypage/myboard',{
        params:{
          nickname : this.name,
        }
      })
      .then((data)=>{
        this.myFeedList = data.data.myBoards
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
.myfeed-container {
  width: 100%;
  height: 70vh;
  overflow: auto;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
}

.myfeed-container::-webkit-scrollbar {
  width: 0;
  height: 0;
}

.myfeed-search-inner-box {
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
  .myfeed-search-inner-box {
    margin: 1px;
  }
}

.myfeed-search-inner-box:hover {
  transform: scale(1.1);
  z-index: 10;
  box-shadow: 0 5px 20px rgba(0,0,0,1);
}

.myfeed-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
</style>