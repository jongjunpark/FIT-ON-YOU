<template>
  <transition name="modal">
    <div class="search-modal-mask" @click.self="$emit('close')">
      <i class="fas fa-times" @click.self="$emit('close')"></i>
      <div class="search-modal-wrap">
        <div class="search-more-box">
          <header class="search-more-user-data">
            <div class="search-more-user-profile" @click="goToUserPage(username)">
              <img v-show="profile" :src="profile">
              <img v-show="!profile" src="../assets/images/default-user.png" alt="">
            </div>
            <div class="search-more-article-head">
              <p class='search-more-username' @click="goToUserPage(username)">{{ username }}</p>
              <p class='search-more-article-date'>{{ time }}</p>
            </div>
            <div v-show="myname === username" class="search-more-del-btn" @click="deleteArticle">삭제</div>
          </header>
          <section class="search-more-content">
            <VueSlickCarousel v-bind="settings" v-if="imgs[1]">
              <article v-for="img in imgs" :key="img.imageNo" class="search-more-content-img">
                <img :src="img.imageUrl">
              </article>
            </VueSlickCarousel>
            <div v-else>
              <article v-for="img in imgs" :key="img.imageNo" class="search-more-content-img">
                <img :src="img.imageUrl">
              </article>
            </div>
            <div class="search-more-btn-box">
              <div class='search-more-btn-left'>
                <i :class="'fas fa-heart '+likeicon[likechk]" @click="clickLike(articleNo,likechk,$event)"></i>
              </div>
              <div class='search-more-btn-right'>
                <i :class="'fas fa-bookmark '+markicon[markchk]" @click="clickBookMark(articleNo,markchk,$event)"></i>
              </div>
            </div>
            <div class="search-more-like-cnt">
              <p v-show="likechk">{{ myname }}님 외 {{favoriteCnt}}명이 좋아합니다</p>
              <p v-show="!likechk">{{favoriteCnt}}명이 좋아합니다</p>
            </div>
            <p v-show="content" class='search-more-content-head'>{{ content }}</p>
            <p v-show="longContent" class='search-more-content-head'>{{ longContent }}</p>
            <div class='search-more-content-tag'>
              <div v-for="tag in tags" :key="tag.id">
                <p class='search-more-content-tag-name' v-show="tag.tagName[0]==='#'">{{ tag.tagName }}</p>
                <p class='search-more-content-tag-name' v-show="tag.tagName[0]!=='#'">#{{ tag.tagName }}</p>
              </div>
            </div>
          </section>
        </div>

      </div>
    </div>
  </transition>
</template>

<script>
import { mapState } from 'vuex'
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import axios from 'axios'

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
  name: 'SearchModal',
  data() {
    return {
      username: '',
      myname: '',
      userprofileimg: '',
      time: '',
      content: '',
      longContent: '',
      imgs: [],
      img: '',
      profile:'',
      tags: [],
      settings: {
        "dots": true,
        "arrows": false,
        "dotsClass": "slick-dots custom-dot-class",
        "edgeFriction": 0.35,
        "infinite": false,
        "speed": 500,
        "slidesToShow": 1,
        "slidesToScroll": 1
      },
      likechk :'',
      markchk :'',
      likeicon:['','heart'],
      markicon:['','mark'],
      articleNo:'',
      favoriteCnt:'',
    }
  },
  components: {
    VueSlickCarousel,
  },
  watch: {
    comment_content() {
      this.checkCommentInput();
    },
    flag() {
      this.defaultDark()
    }
  },
  mounted() {
    this.defaultDark()
    this.articleNo=this.articledata

    let articleNo = this.articledata
    let data = this.$cookies.get('auth-nickname');
    let uri = data;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.myname = res;
    let frm = new FormData();
    frm.append('nickname',res);
    axios.post(`https://i3b304.p.ssafy.io/api/search/${articleNo}`,frm)
    .then((response)=>{
      console.log(response.data)
      this.username = response.data[0].aarticles.articleUser
      this.time = timeForToday(response.data[0].aarticles.articleDate)
      if (response.data[0].aarticles.content.length>100) {
        for (let i=0; i<100; i++) {
          this.longContent += response.data[0].aarticles.content[i]
        }
        this.longContent += ' ....'
      } else {
        this.content = response.data[0].aarticles.content
      }
      this.imgs = response.data[0].imgs
      this.tags = response.data[0].tags
      this.profile = response.data[0].profile
      this.likechk=response.data[0].aarticles.likechk;
      this.markchk=response.data[0].aarticles.markchk;
      this.favoriteCnt=response.data[0].aarticles.favoriteCnt;
      });
  },
  computed: {
    ...mapState(['flag','articledata'])
  },
  methods: {
    checkCommentInput() {
      const INPUTBTN = document.querySelector('.fa-check-circle')
      if (this.comment_content) {
        INPUTBTN.classList.add('on-comment-input')
      } else {
        INPUTBTN.classList.remove('on-comment-input')
      }
    },
    deleteArticle(){
      let article =this.articledata
       axios.delete(`https://i3b304.p.ssafy.io/api/board/${article}`,{
        }).then(
        console.log("success")
      )
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.search-modal-wrap')
      const PTAG = document.querySelectorAll('p')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('search-modal-wrap-dark')
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('search-modal-wrap-dark')
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
      }
    },

    clickLike(articleNo,flag,e) {
      let ref=this

      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;

      if(flag==0){
        this.likechk=1
        e.target.classList.add('heart')
        this.favoriteCnt++;

        axios.post('https://i3b304.p.ssafy.io/api/board/likes',{
            articleNo:articleNo,
            nickname:res
          })
          .then(console.log("좋아요"))
          .catch()
      }
      else if(flag==1){
        this.likechk=0
        e.target.classList.remove('heart')
        this.favoriteCnt--;
        axios.delete('https://i3b304.p.ssafy.io/api/board/likes',{
          data:{
            articleNo:articleNo,
            nickname:res
          }
        })
        .then(console.log(ref.likechk,"좋아요 취소"))
        .catch()
      }
      
    },
    clickComment(articleNo,articleUser) {
      this.modalArticleNo=articleNo;
      this.modalArticleUser=articleUser;
      this.showModal = true
    },
    clickBookMark(articleNo,flag,e) {
      let ref=this

      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;

      if(flag==0){
        this.markchk=1
        e.target.classList.add('mark')
        axios.post('https://i3b304.p.ssafy.io/api/board/bookmark',{
            bookedArticle:articleNo,
            bookUser:res
          })
          .then(console.log("북마크 등록"))
          .catch()
      }
      else if(flag==1){
        this.markchk=0
        e.target.classList.remove('mark')
        axios.delete('https://i3b304.p.ssafy.io/api/board/bookmark',{
          data:{
            bookedArticle:articleNo,
            bookUser:res
          }
        })
        .then(console.log(ref.markchk,"북마크 취소"))
        .catch()
      }

    },
    goToUserPage(nickname){
      this.$router.push(`/otheruser/${nickname}`).catch(()=>{})
    },

  }
}
</script>

<style scoped>
.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;

}

.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;

}

.search-modal-mask {
  position: fixed;
  z-index: 99999;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.search-modal-mask .fa-times {
  position: absolute;
  top: 3vh;
  right: 3vh;
  cursor: pointer;
  color: #fff;
  font-size: 5vh;
}

.modal-enter .modal-wrapper,
.modal-leave-active .modal-wrapper {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.search-modal-wrap {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  padding: 1vh;
  transition: 0.3s ease;
  background-color: #fff;
  border-radius: 1vw;
  width: 88%
}

@media (min-width: 450px) {
  .search-modal-wrap {
  max-width: 50vh;
  width: 100%;
  margin: 0 auto;
  }
}

.search-more-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.search-more-user-data {
  display: flex;
  width: 100%;
  align-items: center;
  position: relative;
}
.search-more-user-profile {
  width: 5vh;
  height: 5vh;
  border: 1px solid rgba(0,0,0,0.5);
  background-color: #fff;
  border-radius: 50%;
}

.search-more-user-profile img{
  width: 100%;
  height: 100%;
  border-radius: 50%;
  cursor: pointer;
}

.search-more-article-head p {
  margin: 0 1vh;
}
.search-more-article-head .search-more-username {
  font-weight: 700;
  font-size: 1.8vh;
  cursor: pointer;
}
.search-more-article-head .search-more-article-date {
  font-size: 1.5vh;
  color: grey;
}

.search-more-del-btn {
  position: absolute;
  top:0;
  right: 1vh;
  font-size: 1.4vh;
  cursor: pointer;
  transition: all 0.5s ease ;
}

.search-more-del-btn:hover {
  color: #5AAEFF;
}

.search-more-content {
  width: 100%;
  margin-top: 0.5vh;
}
.search-more-content .search-more-content-img {
  width: 100%;
  padding-top: 100%;
  position: relative;
  background-color: grey;

}

/* @media (min-height: 569px) and (max-height: 667px) {
  .search-more-content-img {
    height: 400px !important;
  }
} */



.search-more-content .search-more-content-img img{
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
}

.search-more-content .search-more-btn-box {
  display: flex;
  width: 100%;
  padding-top: 1vh;
}

.search-more-content .search-more-btn-box .search-more-btn-left {
  display: flex;
  align-items: center;
  width: 50%;
}

.search-more-content .search-more-btn-box .search-more-btn-left .fas {
  font-size : 100%;
  margin: 0 1vh 0 1vh;
  cursor: pointer;
}

.search-more-content .search-more-btn-box .search-more-btn-right {
  display: flex;
  align-items: center;
  width: 50%;
  justify-content: flex-end;
}

.search-more-content .search-more-btn-box .search-more-btn-right .fas {
  font-size : 100%;
  margin: 0;
  margin-right: 1vh;
  cursor: pointer;
}

.search-more-content .search-more-like-cnt {
  font-size: 1.8vh;
  margin-bottom: 1vh;
  margin-left: 1vh;
}

.search-more-content .search-more-content-head {
  font-weight: 900;
  font-size: 2vh;
  margin-left: 1vh;
  margin-top: 0.5vh;
}

.search-more-content .search-more-content-tag {
  font-weight: 700;
  font-size: 1.7vh;
  display: flex;
  margin-left: 1vh;
  flex-wrap: wrap;
}

.search-more-content .search-more-content-tag .search-more-content-tag-name{
  margin-right: 1vh;
}

.search-modal-wrap-dark {
  background-color: #272727 !important;
}

.heart{
  color:crimson;
  animation-name: check;
  animation-duration: 0.5s;
}
.mark{
  color:gold;
  animation-name: check;
  animation-duration: 0.5s;
}

@keyframes check {
	50% {transform: scale(1.2)}
	100% {transform: scale(1)}
}
</style>
