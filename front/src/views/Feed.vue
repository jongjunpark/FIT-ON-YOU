<template>
  <div>
    <div @click='setInfluNav' class="open-influ-nav">
      ∨
    </div>
    <transition name='slide-influ-nav'>
      <div v-if="isInfluNav" class="influ-nav-box">
        <div class="influ-nav">
          <VueSlickCarousel v-bind="settings">
            <div v-for="influ in influencer" :key="influ.nickname">
              <div class="influ-box">
                <div class="influ-icon">
                  <img :src="influ.profile_img">
                </div>
              </div>
            </div>
          </VueSlickCarousel>
        </div>
      </div>
    </transition>
    
    <CommentModal v-if="showModal" @close="showModal= false" v-bind:modalArticleNo="modalArticleNo"/>

    <div class='wrap feed-wrap' v-for="feed in mainfeed" :key="feed.articleUser">
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile" @click="goUserProfile(feed.articleUser)">
            <img :src="feed.userProfile">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>{{feed.articleUser}}</p>
            <p class='feed-article-date'>{{feed.articleDate}}</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img" v-for="imgs in feed.images" :key="imgs.src">
              <img :src="imgs.src">
            </article>
          </VueSlickCarousel>
          <div class="feed-btn-box">
            <div class='feed-btn-left'>
              <i class="fas fa-heart"></i>
              <i :id="'show-modal'+ feed.articleNo" @click="clickComment(feed.articleNo)" class="fas fa-comment-alt"></i>

            </div>
            <div class='feed-btn-right'>
              <i @click="clickBookMark" class="fas fa-bookmark"></i>
            </div>
          </div>
          <header class='feed-content-head'>{{feed.content}}</header>
          <aside class='feed-content-tag'><span v-for="ta in feed.tags" :key="ta.tagname">{{ta.tagname}}</span></aside>
        </section>
      </div>
      <div class="margin-box"></div>
    </div>
  </div>
</template>

<script>
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import "../components/css/feed.css"
import axios from 'axios'
import CommentModal from '../components/CommentModal.vue'
import { mapState, mapActions  } from 'vuex'

function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);
        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }
        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }
        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }
        return `${Math.floor(betweenTimeDay / 365)}년전`;
 }

export default {
  name: 'Feed',
  data() {
    return {
      showModal: false,
      isInfluNav: false,
      modal: false,
      mainfeed:[],
      influencer:[],
      settings: {
        "dots": false,
        "arrows": true,
        "infinite": true,
        "speed": 500,
        "slidesToShow": 5,
        "slidesToScroll": 1,
        "touchThreshold": 1
      },
      settings2: {
        "dots": true,
        "arrows": false,
        "dotsClass": "slick-dots custom-dot-class",
        "edgeFriction": 0.35,
        "infinite": false,
        "speed": 500,
        "slidesToShow": 1,
        "slidesToScroll": 1
      },
      modalArticleNo : '',
    }
  },
  components: { 
    VueSlickCarousel,
    CommentModal
  },
  computed:{
    ...mapState(['user']),
  },
  
  methods: {
    ...mapActions(['sendUserInfo', 'setLoggedIn', 'setToken']),
    onNewsFeed() {
      const selectBar = document.querySelector('.menu-bar-select')
      const newsFeed = document.querySelector('.fa-newspaper')
      const search = document.querySelector('.fa-search')
      const community = document.querySelector('.fa-users')
      newsFeed.style.color = 'black'
      search.style.color = 'grey'
      community.style.color = 'grey'
      selectBar.classList.add('go-first-menu')
      selectBar.classList.remove('go-second-menu')
      selectBar.classList.remove('go-third-menu')
    },
  
    clickLike() {
      this.modal = true
    },
    clickComment(articleNo) {
      this.modalArticleNo=articleNo;
      console.log(articleNo,12312);
      this.showModal = true
    },
    clickBookMark() {

    },
    setInfluNav() {
      this.isInfluNav = !this.isInfluNav
      const INFLUBTN = document.querySelector('.open-influ-nav')
      if(INFLUBTN.innerHTML === '∧'){
        INFLUBTN.innerHTML = '∨'
      } else {
        INFLUBTN.innerHTML = '∧'
      }
    },
    // goUserProfile(name){
    //   this.$router.push(`/`)
    // }
  },
  mounted() {
    this.onNewsFeed()
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    axios.post("http://localhost:8080/api/board/influencer").then((data)=>{
      this.influencer=data.data;
      console.log(this.influencer)
    });
    
    const formData = new FormData();
    
    formData.append('nickname',res);

    axios.post("http://localhost:8080/api/board/newsfeed",formData).then((data)=>{
      console.log("success")
      this.feedlist=data.data;
      for (let index = 0; index < this.feedlist.length; index++) {
        let feeddata={tags:[],
                      images:[],
                      content:"",
                      articleUser:"",
                      userProfile:"",}

        const el = this.feedlist[index];

        let follow = new FormData();

        follow.append('follow',el.articleUser);

        axios.post("http://localhost:8080/api/board/profileimg",follow).then((proff)=>{
          feeddata.userProfile=proff.data.profile_img;
        });

        const articleNo = new FormData();
        articleNo.append('articleNo',el.articleNo);

        axios.post("http://localhost:8080/api/board/images",articleNo).then((img)=>{
          const imgs = img.data;
          const imglist = [];
          for (let i = 0; i < imgs.length; i++) {
            const el2= imgs[i];
            imglist.push({src:el2.imageUrl});  
          }
          if(this.feedlist[index].articleUser!=null){
            feeddata.images=imglist;
            feeddata.content=this.feedlist[index].content;
            feeddata.articleDate= timeForToday(this.feedlist[index].articleDate);
            feeddata.articleUser= this.feedlist[index].articleUser;
            feeddata.articleNo=this.feedlist[index].articleNo;
          }else{
            feeddata.url=imglist;
            feeddata.content=this.feedlist[index].content;
            feeddata.articleDate= timeForToday(this.feedlist[index].articleDate);
            feeddata.articleUser= this.feedlist[index].influeUser;
            feeddata.articleNo=this.feedlist[index].articleNo;
        }
        
      });
        axios.post("http://localhost:8080/api/board/tags",articleNo).then((tag)=>{
        const tags = tag.data;
        const taglist = [];
        for (let i = 0; i < tags.length; i++) {
            const el2= tags[i];
            taglist.push({tagname:el2.tagName});  
          }
            feeddata.tags=taglist;
        });
       
        this.mainfeed.push(feeddata)
  }
  });
  console.log(this.mainfeed)
  }
}

</script>
<style scoped>
@media (min-width: 1200px) {
  .wrap {
  max-width: 440px !important;
  width: 100%;
  margin: 0 auto;
  margin-top: 70px;
  }
}

.wrap-container {
  margin-bottom: 50px;
}

@media (max-width: 280px) {
  .feed-wrap {
    margin-top: 150px;
  }
}
@media (min-width: 1200px) {
  .feed-wrap {
    margin-top: 70px !important;
  }
}
@media (min-height: 700px) {
  .feed-wrap {
    margin-top: 150px;
  }
}
@media (min-height: 800px) {
  .feed-wrap {
    margin-top: 200px;
  }
}
@media (min-height: 1000px) {
  .feed-wrap {
    margin-top: 80px;
  }
}
.open-influ-nav {
  position: fixed;
  top: 60px;
  left: 50%;
  transform: translate(-50%, 0);
  width: 50px;
  height: 20px;
  line-height: 20px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #DFDFDF;
  box-shadow: 0 6px 12px 0 rgba(0, 0, 0, 0.25);
  text-align: center;
  font-weight: 900;
  z-index: 10;
  cursor: pointer;
}

.open-influ-nav:hover {
  background-color: rgb(199, 199, 199);
}

.influ-nav-box {
  position: fixed;
  top: 70px;
  left: 0;
  right: 0;
  margin: 0 10px;
  z-index: 9;
  overflow-y: hidden;
  height: 100px;
}

.influ-nav {
  padding: 10px 30px;
  background-color: white;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  border-radius: 10px;
  
}

@media (min-width: 1200px) {
  .influ-nav {
    max-width: 580px;
    width: 100%;
    margin: 0 auto;
  }
}

.influ-nav div {
  outline: none;
}

.influ-box {
  display: flex;
  justify-content: center;  
}

.influ-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: black;
  cursor: pointer;
  border: 0.5px solid grey;
}
@media (max-width: 375px) {
  .influ-icon {
  width: 30px;
  height: 30px;
  }
}

.influ-icon img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.slide-influ-nav-enter-active, .slide-influ-nav-leave-active {
  transition: all .5s ease-in-out;
}
.slide-influ-nav-enter, .slide-influ-nav-leave-to {
  height: 0;
}

/* .carousel-inner-box {
  padding: 0 30px;
  margin: 20px 0;
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: yellowgreen;
}

.carousel-btn {
  height: 30px;
  width: 30px;
  position: absolute;
  top: 50%;
  transform:translate(0,-50%);
  background-color: grey;
  border-radius: 50%;
}

.influ-box{
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: slateblue;
} */

.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
.margin-box {
  height: 20px;
}
</style>

