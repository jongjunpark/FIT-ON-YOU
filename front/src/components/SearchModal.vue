<template>
  <transition name="modal">
    <div class="search-modal-mask" @click.self="$emit('close')">
      <i class="fas fa-times" @click.self="$emit('close')"></i>
      <div class="search-modal-wrap">
        <div class="search-more-box">
          <header class="search-more-user-data">
            <div class="search-more-user-profile">
              <img src="userProfile">
            </div>
            <div class="search-more-article-head">
              <p class='search-more-username'>{{ username }}</p>
              <p class='search-more-article-date'>{{ time }}</p>
            </div>
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
                <i class="fas fa-heart"></i>
              </div>
              <div class='search-more-btn-right'>
                <i class="fas fa-bookmark"></i>
              </div>
            </div>
            <p v-show="content" class='search-more-content-head'>{{ content }}</p>
            <p v-show="longContent" class='search-more-content-head'>{{ longContent }}</p>
            <p v-for="tag in tags" :key="tag.id" class='search-more-content-tag'>{{ tag.tagName }}</p>
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
      time: '',
      content: '',
      longContent: '',
      imgs: [],
      img: '',
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
    if (this.articledata.articleUser) {
      this.username = this.articledata.articleUser
    } else {
      this.username = this.articledata.influeUser
    }
    this.time = timeForToday(this.articledata.articleDate)
    if (this.articledata.content.length>60) {
      for (let i=0; i<60; i++) {
        this.longContent += this.articledata.content[i]
      }
      this.longContent += ' ....'
    } else {
      this.content = this.articledata.content
    }
    this.imgs = this.articleimgs
    this.tags = this.articletags
  },
  computed: {
    ...mapState(['flag','articledata','articleimgs','articletags'])
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
  height: 65vh;
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
}
.search-more-user-profile {
  width: 4vh;
  height: 4vh;
  background-color: grey;
  border-radius: 50%;
}

.search-more-user-profile img{
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.search-more-article-head p {
  margin: 0 1vh;
}
.search-more-article-head .search-more-username {
  font-weight: 700;
  font-size: 1.5vh;
}
.search-more-article-head .search-more-article-date {
  font-size: 1vh;
  color: grey;
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

.search-more-content .search-more-content-head {
  font-weight: 900;
  font-size: 2vh;
  margin-left: 1vh;
  margin-top: 0.5vh;
}

.search-more-content .search-more-content-tag {
  font-weight: 700;
  font-size: 1.7vh;
  margin-left: 1vh;
}

.search-modal-wrap-dark {
  background-color: #272727 !important;
}
</style>
