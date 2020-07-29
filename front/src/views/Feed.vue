<template>
  <div>
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
          <article id="feed-set-height" class="feed-content-img">
            <img src="../assets/images/feed-test-img.jpg" alt="#">
          </article>
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
  </div>
</template>

<script>
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import "../components/css/feed.css"

export default {
  name: 'Feed',
  data() {
    return {
      settings: {
        "dots": false,
        "arrows": true,
        "infinite": true,
        "speed": 500,
        "slidesToShow": 5,
        "slidesToScroll": 1,
        "touchThreshold": 1
      }
    }
  },
  components: { VueSlickCarousel },
  // created() {
  //   window.addEventListener("resize", this.setFeedImg);
  // },
  methods: {
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

    },
    clickComment() {

    },
    clickBookMark() {

    }
  },
  mounted() {
    this.onNewsFeed()
    // this.setFeedImg()
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


.influ-nav {
  position: absolute;
  top: 70px;
  left: 0;
  right: 0;
  padding: 10px 30px;
  background-color: white;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  border-radius: 10px;
  z-index: -1;
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


</style>

