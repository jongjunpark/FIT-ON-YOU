<template>
  <div>
    <div @click='setInfluNav' class="open-influ-nav">
      ∨
    </div>
    <transition name='slide-influ-nav'>
      <div v-if="isInfluNav" class="influ-nav-box">
        <div class="influ-nav">
          <VueSlickCarousel v-bind="settings">
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon"></div>
              </div>
            </div>
          </VueSlickCarousel>
        </div>
      </div>
    </transition>

    <div class='wrap feed-wrap'>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile"></div>
          <div class="feed-article-head">
            <p class='feed-username'>Username</p>
            <p class='feed-article-date'>20.00.00 00:00</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img.jpg" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img2.jpg" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img3.png" alt="#">
            </article>
          </VueSlickCarousel>
          <div class="feed-btn-box">
            <div class='feed-btn-left'>
              <i class="fas fa-heart"></i>
              <i id="show-modal" @click="showModal = true" class="fas fa-comment-alt"></i>
              <CommentModal v-if="showModal" @close="showModal = false">

              </CommentModal>
            </div>
            <div class='feed-btn-right'>
              <i @click="clickBookMark" class="fas fa-bookmark"></i>
            </div>
          </div>
          <header class='feed-content-head'>Text Name</header>
          <aside class='feed-content-tag'>#ABC #DEF #GHI #JKL</aside>
        </section>
      </div>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile"></div>
          <div class="feed-article-head">
            <p class='feed-username'>Username</p>
            <p class='feed-article-date'>20.00.00 00:00</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img.jpg" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img2.jpg" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img3.png" alt="#">
            </article>
          </VueSlickCarousel>
          <div class="feed-btn-box">
            <div class='feed-btn-left'>
              <i @click="clickLike" class="fas fa-heart"></i>
              <i @click="clickComment" class="fas fa-comment-alt"></i>
            </div>
            <div class='feed-btn-right'>
              <i @click="clickBookMark" class="fas fa-bookmark"></i>
            </div>
          </div>
          <header class='feed-content-head'>Text Name</header>
          <aside class='feed-content-tag'>#ABC #DEF #GHI #JKL</aside>
        </section>
      </div>
    </div>
    <div>{{user.nickname}}</div>
  </div>
</template>

<script>
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import "../components/css/feed.css"
import CommentModal from '../components/CommentModal.vue'

export default {
  name: 'Feed',
  data() {
    return {
      showModal: false,
      isInfluNav: false,
      modal: false,
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
      }
    }
  },
  components: { 
    VueSlickCarousel,
    CommentModal
  },
  // created() {
  //   window.addEventListener("resize", this.setFeedImg);
  // },
  methods: {
    ...mapActions(['']),
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
    // setFeedImg() {
    //   const FEEDIMG = document.querySelector('#feed-set-height')
    //   let WINDOWHEIGHT = window.innerHeight
    //   FEEDIMG.style.height = `${WINDOWHEIGHT-400}px`
    // },
    clickLike() {
      this.modal = true
    },
    clickComment() {

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
    }
  },
  updated() {
    this.onNewsFeed()
    const formData = new FormData();
    formData.append('nickname',this.user.nickname);
    axios.post("http://localhost:8080/board/newsfeed",formData).then((data)=>{
      console.log("success")
      console.log(data)
      this.feedlist=data.data;
      for (let index = 0; index < this.feedlist.length; index++) {
        const el = this.feedlist[index];
        const articleNo = new FormData();
          articleNo.append('articleNo',el.articleNo);
          axios.post("http://localhost:8080/board/images",articleNo).then((img)=>{
            console.log(img)
          });
      }
    });
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
  background-color: silver;
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
}
@media (max-width: 375px) {
  .influ-icon {
  width: 30px;
  height: 30px;
  }
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
</style>

