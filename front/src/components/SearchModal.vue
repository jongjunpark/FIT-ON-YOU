<template>
  <transition name="modal">
    <div class="search-modal-mask" @click.self="$emit('close')">
      <div class="search-modal-wrap">
        <div class="search-more-box">
          <header class="search-more-user-data">
            <div class="search-more-user-profile">
              <img src="userProfile">
            </div>
            <div class="search-more-article-head">
              <p class='search-more-username'>Username</p>
              <p class='search-more-article-date'>3시간전</p>
            </div>
          </header>
          <section class="search-more-content">
            <VueSlickCarousel v-bind="settings">
              <article class="search-more-content-img">
                <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/s640x640/115999683_208712917242400_7853725733733513666_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_ohc=AvPIi_dvY8YAX9_vvo7&oh=33cf0cb25092a6bda81d298f097107dd&oe=5F4DE678">
              </article>
              <article class="search-more-content-img">
                <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116363553_752834785516476_1926326966447616076_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=100&_nc_ohc=OQtQ2R5KUcwAX-dbIaU&oh=e0b1affcf56a2cdf469c6339ae1af958&oe=5F4D1E40">
              </article>
              <article class="search-more-content-img">
                <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116592429_1139437373095582_3420540484434190690_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=110&_nc_ohc=rYAxs7P8CVoAX_E0Hwf&oh=c5cc8b2e3a48d679177594901f603aa6&oe=5F4A9ACC">
              </article>
            </VueSlickCarousel>
            <div class="search-more-btn-box">
              <div class='search-more-btn-left'>
                <i class="fas fa-heart"></i>
              </div>
              <div class='search-more-btn-right'>
                <i class="fas fa-bookmark"></i>
              </div>
            </div>
            <p class='search-more-content-head'>Content</p>
            <p class='search-more-content-tag'>#abc #def #ghi</p>
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

export default {
  name: 'SearchModal',
  data() {
    return {
      comment_content: '',
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
  },
  computed: {
    ...mapState(['flag'])
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
