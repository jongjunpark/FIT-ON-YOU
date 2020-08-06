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
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/36842227_2058150601180234_854349517221265408_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=eO6D5fslDBsAX9XpqcX&oh=a84379ba4552588f7ddcab8f82a76c52&oe=5F4B7D7A" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/71533411_449636125903525_7464596574253875200_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=7H2dBLnpXDwAX8ZfLGK&oh=a067d970dab058cd94a1fb9a9838aa27&oe=5F4E0FDB" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/95592923_2739279709639483_5362681631616270336_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=GCShxBSR0FIAX9NNc_g&oh=7d83b275ee97642410e82d748447031f&oe=5F4DB68C" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/116525805_691987371384404_1791999639578458581_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=4pLOGgd8bKwAX9fa0Z-&oh=0f76245424d81a085823c398bcb63b42&oe=5F4C88CE" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/40730683_300262830563612_6253477682965643264_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=DcM0yqd_EVIAX_LqHQz&oh=1b45a10a6ea8373ce72d23ad2fc765ad&oe=5F4C5E5C" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/105365279_3520222574663213_8532879881338910739_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=ESwhrrcwzqUAX8SJnHh&oh=b25b15104625bed706f2c7af4670cbec&oe=5F4CA0F8" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/10809958_859072497460383_1366814555_a.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=WuByDpFo1oIAX8adJtw&oh=d827e456674e7e721957960dae63599c&oe=5F4D1001" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/115961735_273060193994558_7619050446248374354_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=fQB3PN11FYcAX_OW62o&oh=4f9a01ea96035411468273b47cad15ce&oe=5F4DF25A" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/29416658_527605414306437_7146489572367532032_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=s-OJEX7WGOoAX_LRg2-&oh=1756009912651ffa5b48235f3a950095&oe=5F4DC197" alt="">
                </div>
              </div>
            </div>
            <div>
              <div class="influ-box">
                <div class="influ-icon">
                  <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/52840555_1918106758318832_360224314479345664_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=vzNWxQ8xMeoAX-eE035&oh=2ec2bc1df4457ee529b3ea2c923f450f&oe=5F4A60D9" alt="">
                </div>
              </div>
            </div>
          </VueSlickCarousel>
        </div>
      </div>
    </transition>

    <div class='wrap feed-wrap'>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile">
            <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/95592923_2739279709639483_5362681631616270336_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=GCShxBSR0FIAX9NNc_g&oh=7d83b275ee97642410e82d748447031f&oe=5F4DB68C" alt="#">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>kidcozyboy</p>
            <p class='feed-article-date'>3시간 전</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img.jpg" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="../assets/images/feed-test-img4.jpg" alt="#">
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
          <header class='feed-content-head'>COZ</header>
          <aside class='feed-content-tag'>#dazedkorea #ChromeHearts #Burberry</aside>
        </section>
      </div>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile">
            <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/116525805_691987371384404_1791999639578458581_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=4pLOGgd8bKwAX9fa0Z-&oh=0f76245424d81a085823c398bcb63b42&oe=5F4C88CE" alt="#">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>keemhongwu</p>
            <p class='feed-article-date'>4시간 전</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/116152223_1273335322997876_7355345239487507950_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=108&_nc_ohc=WhOnd7XWONsAX-gPf1c&oh=b8a82d5b8217af5abb404fc43fb2a57b&oe=5F4CD285" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/106508030_588611342080828_1832395914087840562_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_ohc=YL8cuGE0tr4AX8QpN-3&oh=1194775b659500300ecaca53f4673fe7&oe=5F4D358F" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/105970916_161940912053134_3670143124224710785_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=110&_nc_ohc=Ulru5KRaUHUAX89y5bE&oh=56362bf86407dcd5d1905675134c1842&oe=5F4BEEE8" alt="#">
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
          <header class='feed-content-head'>불금</header>
          <aside class='feed-content-tag'>#Daily #린넨 #화이트룩</aside>
        </section>
      </div>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile">
            <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/71533411_449636125903525_7464596574253875200_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=7H2dBLnpXDwAX8ZfLGK&oh=a067d970dab058cd94a1fb9a9838aa27&oe=5F4E0FDB" alt="#">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>jennierubyjane</p>
            <p class='feed-article-date'>4시간 전</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/115845973_2329752243986374_794663954761266814_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=AFNbW2TMC2EAX-a3Qgd&oh=1a5b6a4fb01fb7b8408e126a22627f5a&oe=5F4AA4A6" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/109808509_1100148067052880_2770842009460315741_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=vNs69Wls-tgAX8KdFHy&oh=8fe4a3b37eb03ef9ba7146b762a555c3&oe=5F4DE25B" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/107424621_2584108538546487_3451861503933358503_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=I1iW4jAUszUAX94a0Oo&oh=b2c428764b769ee80ff7a37177fd48e6&oe=5F4BB663" alt="#">
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
          <header class='feed-content-head'>오늘봐❄️❄️❄️</header>
          <aside class='feed-content-tag'>#Chanel #Gucci #Blackpink #Azkii</aside>
        </section>
      </div>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile">
            <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/36842227_2058150601180234_854349517221265408_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=eO6D5fslDBsAX9XpqcX&oh=a84379ba4552588f7ddcab8f82a76c52&oe=5F4B7D7A" alt="#">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>artistboy_</p>
            <p class='feed-article-date'>4시간 전</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/116422869_290142065388713_1828280228150552415_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=QdwQEI3gwckAX-UMZIV&oh=e4abf994c19b579e1e5232cafa84b73d&oe=5F4D363A" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/114361583_640709466542685_803387787253716416_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=109&_nc_ohc=waq4blw_T94AX9AMkDN&oh=bc5286b19c313f863450d97a5f3ed0b4&oe=5F4C97E7" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/109498583_1796016527239393_3768403805793298082_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_ohc=HVOTOTBhmpYAX-XC5af&oh=11c23776aa7c19537b58c293cff4b497&oe=5F4AC8A1  " alt="#">
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
          <header class='feed-content-head'>왈가닥뷰티에서 금손선생님들과 함께한 작업!!</header>
          <aside class='feed-content-tag'>#Canvas #BlueLip #멜빵 #뱀무늬셔츠</aside>
        </section>
      </div>
      <div class='wrap-container'>
        <header class="feed-user-data">
          <div class="feed-user-profile">
            <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/40730683_300262830563612_6253477682965643264_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=DcM0yqd_EVIAX_LqHQz&oh=1b45a10a6ea8373ce72d23ad2fc765ad&oe=5F4C5E5C" alt="#">
          </div>
          <div class="feed-article-head">
            <p class='feed-username'>parkbboyoung</p>
            <p class='feed-article-date'>5시간 전</p>
          </div>
        </header>
        <section class="feed-content">
          <VueSlickCarousel v-bind="settings2">
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/c0.115.922.922a/s640x640/115886609_350469826363192_6132658822732527130_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_ohc=5PwfP_Y2CjIAX9h2fht&oh=c33407c4b784b1497ea69a8f74852899&oe=5F4D083E" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/95659011_854314271722943_1271107050899544588_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_ohc=Y4cumoXtEJkAX860cQn&oh=313d228431aba000d594d8ae9457c303&oe=5F4BBB12" alt="#">
            </article>
            <article class="feed-content-img">
              <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/97370334_253565055887659_6144263934509229288_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=107&_nc_ohc=5dNCbyvjBv8AX9m98Ej&oh=4bd89591023b5340672f162e7f259e43&oe=5F4B4D82" alt="#">
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
          <header class='feed-content-head'>뽀블리</header>
          <aside class='feed-content-tag'>#러블리룩 #청자켓 #목폴라 #스프라이트</aside>
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



export default {
  name: 'Feed',
  data() {
    return {
      showModal: false,
      isInfluNav: false,
      modal: false,
      mainfeed:[],
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
  computed:{
    ...mapState(['user']),
  },
  mounted() {
    // this.sendUserInfo(),
    // this.setLoggedIn(true)
    this.defaultDark()
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
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        this.checked = true
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
      }
    },
  },
  updated() {
    this.onNewsFeed()
    const formData = new FormData();
    formData.append('nickname',this.user.nickname);
    axios.post("http://localhost:8080/api/board/newsfeed",formData).then((data)=>{
      console.log("success")
      this.feedlist=data.data;
      for (let index = 0; index < this.feedlist.length; index++) {
        const el = this.feedlist[index];
        const articleNo = new FormData();
        articleNo.append('articleNo',el.articleNo);
        axios.post("http://localhost:8080/api/board/images",articleNo).then((img)=>{
          const temp = img.data;``
          const templist = [];
          for (let i = 0; i < temp.length; i++) {
            const el2= temp[i];
            templist.push({src:el2.imageUrl});  
          }
          if(this.feedlist[index].articleUser!=null){
            this.mainfeed.push({url:templist,content:this.feedlist[index].content,articleDate: this.feedlist[index].articleDate,articleUser: this.feedlist[index].articleUser});
          }else{
            this.mainfeed.push({url:templist,content:this.feedlist[index].content,articleDate: this.feedlist[index].articleDate,articleUser: this.feedlist[index].influeUser});
        }
      });
  }
  console.log(this.mainfeed)});
  }
}

</script>
<style scoped>
@media (min-width: 1200px) {
  .wrap {
  max-width: 440px !important;
  width: 100%;
  margin: 0 auto;
  margin-top: 60px;
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
    margin-top: 60px !important;
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

