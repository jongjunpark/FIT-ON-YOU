<template>
  <div>
    <div class='influ-nav'>
      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active" data-interval="10000">
            <div class="carousel-inner-box">
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="carousel-inner-box">
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
              <div class="influ-box"></div>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev carousel-btn" href="#carouselExampleControls" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon carousel-btn-arrow" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next carousel-btn" href="#carouselExampleControls" role="button" data-slide="next">
          <span class="carousel-control-next-icon carousel-btn-arrow" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
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
import "../components/css/feed.css"


export default {
  name: 'Feed',
  created() {
    window.addEventListener("resize", this.setFeedImg);
  },
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
    setFeedImg() {
      const FEEDIMG = document.querySelector('#feed-set-height')
      let WINDOWHEIGHT = window.innerHeight
      FEEDIMG.style.height = `${WINDOWHEIGHT-300}px`
    },
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
.feed-wrap {
  margin-top: 130px;
}

.influ-nav {
  width: 100%;
  position: absolute;
  top: 50px;
  left: 0;
  right: 0;
}
@media (min-width: 1200px) {
  .influ-nav {
    max-width: 580px;
    width: 100%;
    margin: 0 auto;
  }
}

.carousel-inner-box {
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
}

</style>

