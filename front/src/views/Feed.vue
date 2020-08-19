<template>
  <div>
    <div @click='setInfluNav' class="open-influ-nav">
      ∨
    </div>
    <transition name='slide-influ-nav'>
      <div v-show="isInfluNav" class="influ-nav-box">
        <div class="influ-nav">
          <VueSlickCarousel v-bind="settings" v-if="influencer.length">
            <div v-for="influ in influencer" :key="influ.nickname">
              <div class="influ-box">
                <div class="influ-icon" @click="goProfile(influ.nickname)">
                  <img :src="influ.profile_img">
                </div>
                <p class="influ-name" @click="goProfile(influ.nickname)">{{ influ.nickname }}</p>
              </div>
            </div>
            <span></span>
          </VueSlickCarousel>
        </div>
      </div>
    </transition>
    
    <CommentModal v-if="showModal" @close="showModal= false" :modalArticleNo="modalArticleNo" :modalArticleUser="modalArticleUser"/>

    <div class='wrap feed-wrap'>
      <div class='wrap-container feed-wrap-container' v-for="(feed,index) in mainfeed" :key="index">
        <header class="feed-user-data">
          <div class="feed-user-profile" @click="goToUserPage(feed.articleUser)">
            <img v-show="feed.userProfile" :src="feed.userProfile">
            <img v-show="!feed.userProfile" src="../assets/images/default-user.png" alt="dafault">
          </div>
          <div class="feed-article-head">
            <p class='feed-username' @click="goToUserPage(feed.articleUser)">{{feed.articleUser}}</p>
            <p class='feed-article-date'>{{feed.articleDate}}</p>         
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2" v-if="feed.images[1]">
            <article class="feed-content-img" v-for="imgs in feed.images" :key="imgs.src">
              <img :src="imgs.src">
            </article>
          </VueSlickCarousel>
          <div v-else>
            <article v-for="image in feed.images" :key="image.src" class="feed-content-img">
              <img :src="image.src">
            </article>
          </div>
          <div class="feed-btn-box">
            <div class='feed-btn-left'>
             
              <i :class="'fas fa-heart '+likeicon[likeStates[index]]" 
              @click="clickLike(feed.articleNo,likeStates[index],index,$event)"></i>
              <i :id="'show-modal'+ feed.articleNo" @click="clickComment(feed.articleNo,feed.articleUser)" class="fas fa-comment-alt"></i>

            </div>
            <div class='feed-btn-right'>
              <i :class="'fas fa-bookmark ' +markicon[bookmarkStates[index]]"
              @click="clickBookMark(feed.articleNo,bookmarkStates[index],index,$event)"></i>
            </div>
          </div>
          <div class="feed-like-cnt">
            <p v-show="likeStates[index]">{{ myName }}님 외 {{feed.favoriteCnt - 1}}명이 좋아합니다</p>
            <p v-show="!likeStates[index]">{{feed.favoriteCnt}}명이 좋아합니다</p>
          </div>
          <header class='feed-content-head'>{{feed.content}}</header>
          <aside class='feed-content-tag'>
            <div v-for="ta in feed.tags" :key="ta.tagname">
              <p v-show="ta.tagname[0]==='#'">{{ ta.tagname }}</p>
              <p v-show="ta.tagname[0]!=='#'">#{{ ta.tagname }}</p>
            </div>
          </aside>
        </section>
      </div>
      <infinite-loading @infinite="infiniteHandler" spinner="spinner">
        <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
        <div slot="no-results" class="no-result" v-show="mainfeed.length==0"><h1>현재<br> 팔로우가 없거나<br> 친구들의 <br> 게시글이 없어요 <br>팔로우하여<br>피드를 받아보세요</h1></div>
      </infinite-loading>
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
import { mapState, mapActions, mapMutations  } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'

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
      myName: '',
      mainfeed:[],
      influencer:[],
      feedlist:[],
      likeStates:[],
      bookmarkStates:[],
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
      modalArticleUser:'',
      likeicon:['','heart'],
      markicon:['','mark'],
      limit:1,
    }
  },
  components: { 
    VueSlickCarousel,
    CommentModal,
    InfiniteLoading,
  },
  computed:{
    ...mapState(['user', 'flag']),
  },
  watch: {
    flag() {
      this.defaultDark()
    },
  },
  created() {
    this.getInflu()
    window.addEventListener("scroll", this.defaultDark)
  },
  destroyed() {
		window.removeEventListener("srcoll", this.defaultDark)
	},
  methods: {
    ...mapActions(['sendUserInfo', 'setLoggedIn', 'setToken']),
    ...mapMutations(['setIsSelectBar']),
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
    getInflu() {
      axios.get("https://i3b304.p.ssafy.io/api/board/influencer").then((data)=>{
        this.influencer=data.data;
      });
    },
    clickLike(articleNo,flag,index,e) {
      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;

      if(flag==0){
        this.likeStates[index]=1
        e.target.classList.add('heart')
        this.modal = true


        this.mainfeed[index].favoriteCnt++;

        // let tmp=this.mainfeed[index]
        // tmp.favoriteCnt++;

        // this.$set(this.mainfeed,index,tmp);

        

        axios.post('https://i3b304.p.ssafy.io/api/board/likes',{
            articleNo:articleNo,
            nickname:res
          })
          .then()
          .catch()
      }
      else if(flag==1){
        this.likeStates[index]=0
        e.target.classList.remove('heart')
        
        this.mainfeed[index].favoriteCnt--

        axios.delete('https://i3b304.p.ssafy.io/api/board/likes',{
          data:{
            articleNo:articleNo,
            nickname:res
          }
        })
        .then()
        .catch()
      }
      
    },
    clickComment(articleNo,articleUser) {
      this.modalArticleNo=articleNo;
      this.modalArticleUser=articleUser;
      this.showModal = true
    },
    clickBookMark(articleNo,flag,index,e) {


      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;

      if(flag==0){
        this.bookmarkStates[index]=1
        e.target.classList.add('mark')
        axios.post('https://i3b304.p.ssafy.io/api/board/bookmark',{
            bookedArticle:articleNo,
            bookUser:res
          })
          .then()
          .catch()
      }
      else if(flag==1){
        this.bookmarkStates[index]=0
        e.target.classList.remove('mark')
        axios.delete('https://i3b304.p.ssafy.io/api/board/bookmark',{
          data:{
            bookedArticle:articleNo,
            bookUser:res
          }
        })
        .then()
        .catch()
      }

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
    goProfile(name) {
      this.$router.push(`/otheruser/${name}`)
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INFLUNAVBTN = document.querySelector('.open-influ-nav')
      const INFLUNAV = document.querySelector('.influ-nav')
      const H1tag = document.querySelector('.no-result')
      const PTAG = document.querySelectorAll('p')
      const FEED_HEAD = document.querySelectorAll('header')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        if (INFLUNAVBTN && INFLUNAV && H1tag && PTAG && FEED_HEAD ) {
          INFLUNAVBTN.classList.add('nav-influ-btn-dark')
          INFLUNAV.classList.add('nav-influ-dark')
          H1tag.classList.add('no-result-dark')
          for (let i=0; i<FEED_HEAD.length ; i++) {
            FEED_HEAD[i].classList.add('font-dark')
          }
          for (let i=0; i<PTAG.length ; i++) {
            PTAG[i].classList.add('font-dark')
          }
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        if (INFLUNAVBTN && INFLUNAV && H1tag && PTAG && FEED_HEAD) {
          INFLUNAVBTN.classList.remove('nav-influ-btn-dark')
          INFLUNAV.classList.remove('nav-influ-dark')
          H1tag.classList.remove('no-result-dark')
          for (let i=0; i<FEED_HEAD.length ; i++) {
            FEED_HEAD[i].classList.remove('font-dark')
          }
          for (let i=0; i<PTAG.length ; i++) {
            PTAG[i].classList.remove('font-dark')
          }
        }
      }
    },
    infiniteHandler($state){
      let ref = this
      let nickdata = this.$cookies.get('auth-nickname')
      let uri = nickdata;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;


      const frm = new FormData();
      frm.append('nickname',res);
      frm.append('page',ref.limit);
      axios.post('https://i3b304.p.ssafy.io/api/board/newsfeed/'+ref.limit,frm)
      .then((data)=>{
        setTimeout(() => {
          if(data.data.length){
            this.feedlist=data.data;
            for (let index = 0; index < this.feedlist.length; index++) {
              let feeddata={tags:[],
                            images:[],
                            content:"",
                            articleUser:"",
                            userProfile:"",
                            favoriteCnt:"",
                            }

              const el = this.feedlist[index];

              let follow = new FormData();

              follow.append('follow',el.articleUser);

              axios.post("https://i3b304.p.ssafy.io/api/board/profileimg",follow).then((proff)=>{
                feeddata.userProfile=proff.data.profile_img;
              });

              const articleNo = new FormData();
              articleNo.append('articleNo',el.articleNo);

              axios.post("https://i3b304.p.ssafy.io/api/board/images",articleNo).then((img)=>{
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
               feeddata.favoriteCnt=this.feedlist[index].favoriteCnt;
              
            });
              axios.post("https://i3b304.p.ssafy.io/api/board/tags",articleNo).then((tag)=>{
              const tags = tag.data;
              const taglist = [];
              for (let i = 0; i < tags.length; i++) {
                  const el2= tags[i];
                  taglist.push({tagname:el2.tagName});     
                }
                  feeddata.tags=taglist;
              });
            
              this.mainfeed.push(feeddata)
              ref.likeStates.push(this.feedlist[index].likechk);
              ref.bookmarkStates.push(this.feedlist[index].markchk);
            }

            $state.loaded();
            ref.limit+=1;
            if(ref.mainfeed.length/10==0){
              $state.loaded();
            }
      

          }
          else{
            $state.complete();
          }
        }, 1000);
      })
      .catch()
    },
    goToUserPage(nickname){
      this.$router.push(`/otheruser/${nickname}`).catch(()=>{})
    },

  },
  mounted() {
    this.setIsSelectBar(true)
    this.onNewsFeed()
    let ref=this;
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.myName = res;
    const formData = new FormData();
    formData.append('nickname',res);
    // formData.append('pageNum',ref.limit),
    axios.post('https://i3b304.p.ssafy.io/api/board/newsfeed/0',formData).then((data)=>{
      this.feedlist=data.data;
      for (let index = 0; index < this.feedlist.length; index++) {
        let feeddata={tags:[],
                      images:[],
                      content:"",
                      articleUser:"",
                      userProfile:"",
                      favoriteCnt:"",
                      }

        const el = this.feedlist[index];
        let follow = new FormData();
        follow.append('follow',el.articleUser);
        
        axios.post("https://i3b304.p.ssafy.io/api/board/profileimg",follow).then((proff)=>{
          feeddata.userProfile=proff.data.profile_img;
          this.defaultDark()
        });

        const articleNo = new FormData();
        articleNo.append('articleNo',el.articleNo);

        axios.post("https://i3b304.p.ssafy.io/api/board/images",articleNo).then((img)=>{
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
          feeddata.favoriteCnt=this.feedlist[index].favoriteCnt;
          this.defaultDark()
        });
        axios.post("https://i3b304.p.ssafy.io/api/board/tags",articleNo).then((tag)=>{
        const tags = tag.data;
        const taglist = [];
        for (let i = 0; i < tags.length; i++) {
            const el2= tags[i];
            taglist.push({tagname:el2.tagName});  
          }
            feeddata.tags=taglist;
        this.defaultDark()
        });
        
        this.mainfeed.push(feeddata)
        ref.likeStates.push(this.feedlist[index].likechk);
        ref.bookmarkStates.push(this.feedlist[index].markchk);
        this.defaultDark()
      }
    });
    this.defaultDark()
  },
  beforeDestroy() { 
    this.setIsSelectBar(false)
  }
}

</script>

