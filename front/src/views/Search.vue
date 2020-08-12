<template>
  <div class='wrap'>
    <div class="search-bar">
      <i v-if='isReturn' @click='setReturn' class="fas fa-undo-alt"></i>
      <transition name='fade' mode="out-in">
        <i key=1 v-if='isHashSearch' :style='`color:${iconColor}`' @click='setHash' class="fas fa-hashtag"></i>
        <i key=2 v-else class="fas fa-user" :style='`color:${iconColor}`'></i>
      </transition>
      <i v-show='isUserSearch' @click='setUser' class="fas fa-user search-user-icon"></i>
      <input @input="hashContent = $event.target.value" v-model='hashContent' 
        v-show='isHashInput' @keypress.enter='onHashResult' @keyup.188="addHash" type="text" class="search-input" placeholder=" , 를 통해 구분해주세요">
      <span v-show='isHashInput'>
        <i @click='onHashResult' class="fab inner-search-btn fa-sistrix"></i>
      </span>
      <input @input="userContent = $event.target.value" v-model='userContent' 
        v-show='isUserInput' @keypress.enter='onUserResult' type="text" class="search-input" placeholder="유저이름을 입력하세요">
      <span v-show='isUserInput'>
        <i @click='onUserResult' class="fab inner-search-btn fa-sistrix"></i>
      </span>
      <transition-group name='fade' tag="div" class="hash-group" mode="in-out">
        <div class='hash-item' v-for='(hash, index) in hashList' :key='`hash-${index}`'>
          <div @click='delHashItem(index)' class="hash-item-close-btn"><i class="fas fa-times"></i></div>
          {{ hash }}
        </div>
      </transition-group>
    </div>
    <div v-if="isDefault" class='wrap-container'>

      <div class="search-box" v-for="(feed,index) in articleList" :key = "feed.articles.articleNo">
        <div class="search-inner-box">
          <img :src="feed.imgs[0].imageUrl" :id="index">
        </div>
      </div>
     
    </div>
    <HashSearch v-if="isHashResult"></HashSearch>
    <UserSearch v-if="isUserResult"></UserSearch>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import HashSearch from '../components/HashSearch.vue'
import UserSearch from '../components/UserSearch.vue'
import '../components/css/search.css'
import axios from 'axios'

export default {
  name: 'Search',
  computed: {
    ...mapState(['flag'])
  },
  components: {
    HashSearch,
    UserSearch,
  },
  data() {
    return {
      articleList:[],
      isHashSearch: true,
      isUserSearch: true,
      isReturn: false,
      isHashInput: false,
      isUserInput: false,
      hashContent: '',
      hashList: [],
      userContent: '',
      isDefault: true,
      isHashResult: false,
      isUserResult: false,
      iconColor: '',
    }
  },
  watch: {
    hashContent() {
      this.checkHashTag();
    },
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    goSearch() {
        const selectBar = document.querySelector('.menu-bar-select')
        const newsFeed = document.querySelector('.fa-newspaper')
        const search = document.querySelector('.fa-search')
        const community = document.querySelector('.fa-users')
        newsFeed.style.color = 'grey'
        search.style.color = 'black'
        community.style.color = 'grey'
        selectBar.classList.add('go-second-menu')
        selectBar.classList.remove('go-first-menu')
        selectBar.classList.remove('go-third-menu')
      },
    setHash() {
      // const HASH = document.querySelector('.fa-hashtag')
      // HASH.style.paddingRight = '50%'
      this.isUserSearch = false
      this.isHashInput = true
      this.isReturn = true
    },
    setUser() {
      // const USER = document.querySelector('.fa-user')
      // USER.style.paddingRight = '80%'
      this.isHashSearch = false
      this.isUserSearch = false
      this.isUserInput = true
      this.isReturn = true
    },
    setReturn() {
      this.isHashSearch = !this.isHashSearch
      this.hashList = []
      if(this.isUserInput) {
        this.isUserInput = false
        this.isHashInput = true
        this.hashContent = null
      } else if (this.isHashInput) {
        this.isHashInput = false
        this.isUserInput = true
        this.userContent = null
      }
    },
    checkHashTag() {
      
    },
    addHash() {
      this.hashList.push(this.hashContent.slice(0,-1))
      this.hashContent = ''
    },
    delHashItem(index) {
      this.hashList.splice(index, 1)
    },
    onHashResult() {
      this.isDefault = false
      this.isUserResult = false
      this.isHashResult = true
    },
    onUserResult() {
      this.isDefault = false
      this.isHashResult = false
      this.isUserResult = true
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('.search-input')

      const SEARCH_ICON = document.querySelectorAll('.fa-sistrix')
      const USERICON = document.querySelector('.search-user-icon')
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        USERICON.classList.add('search-icon-black')
        this.iconColor = '#ebebeb'
        for (let i=0; i<INPUT.length ; i++) {
          SEARCH_ICON[i].classList.add('search-icon-black')
        }
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('search-input-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        USERICON.classList.remove('search-icon-black')
        this.iconColor = 'black'
        for (let i=0; i<INPUT.length ; i++) {
          SEARCH_ICON[i].classList.remove('search-icon-black')
        }
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('search-input-dark')
        }
      }
    },
  },
  mounted() {
    this.goSearch()
    this.defaultDark()


    axios.post("https://i3b304.p.ssafy.io/api/search/").then((data)=>{
      this.articleList=data.data;
     console.log(this.articleList)

    })
  }
}
</script>