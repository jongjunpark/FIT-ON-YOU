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
      <transition-group v-show="userContent" name='fade' tag="div" class="search-user-group" mode="in-out">
        <div class='search-user-item' v-for='user in userList' :key='user.nickname' @click='goProfile(user.nickname)'>
          <div class="search-user-img">
            <img v-show="user.profile_img" :src="user.profile_img" alt="">
            <img v-show="!user.profile_img&&user.gender=='Male'" src="../assets/images/default-user.png" alt="">
            <img v-show="!user.profile_img&&user.gender=='Female'" src="../assets/images/default-user-female.png" alt="">
          </div>
          <div class="search-user-content">
            <div class="search-user-name">{{ user.nickname }}</div>
            <div v-show="!user.selfintroduce" class="search-user-nonuserintro">{{ user.selfintroduce }}</div>
            <div v-show="user.selfintroduce" class="search-user-intro">{{ user.selfintroduce }}</div>
          </div>
        </div>
        <div v-show="userListLength > 0" @click="onUserResult" class='search-user-more' key='0'>{{ userListLength }}개 더보기</div>
      </transition-group>
    </div>
    <div v-if="isDefault" class='wrap-container search-container'>

      <div class="search-box" v-for="(feed,index) in feedList" :key="`feed-${index}`">
        <div class="search-inner-box" v-for="article in feedList[index]" :key="article.articles.articleNo">
          <div @click="onModal(article.articles)" class="search-inner-btn">자세히</div>
          <img v-if='article.imgs[0]' :src="article.imgs[0].imageUrl" :id="index">
        </div>
      </div>
      <SearchModal  v-if="showModal" @close="showModal= false"/>
     
    </div>
    <UserSearch v-if="isUserResult"/>
    <HashSearch v-if="isHashResult"/>
  </div>
</template>

<script>
import { mapState,mapMutations } from 'vuex'
import HashSearch from '../components/HashSearch.vue'
import UserSearch from '../components/UserSearch.vue'
import SearchModal from '../components/SearchModal.vue'
import '../components/css/search.css'
import axios from 'axios'

export default {
  name: 'Search',
  computed: {
    ...mapState(['flag', 'articledata'])
  },
  components: {
    HashSearch,
    UserSearch,
    SearchModal,
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
      hashString: '',
      userContent: '',
      userList: [],
      userListLength: 0,
      isDefault: true,
      isHashResult: false,
      isUserResult: false,
      iconColor: '',
      tempList: [],
      feedList: [],
      showModal: false,
    }
  },
  watch: {
    hashContent() {
      this.checkHashTag();
    },
    hashList() {
      this.setHashList();
    },
    flag() {
      this.defaultDark()
    },
    userContent() {
      this.inUserSearch()
      console.log(this.userList)
    }
  },
  methods: {
    ...mapMutations(['setArticledata', 'setHashSearch', 'setUserSearch']),
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
      if (this.hashContent != ',') {
        this.hashList.push(this.hashContent.slice(0,-1))
        this.hashContent = ''
      } else {
        this.hashContent = ''
      }
    },
    delHashItem(index) {
      this.hashList.splice(index, 1)
    },
    onHashResult() {
      if (this.hashContent || this.hashList.length>0) {
        this.hashList.push(this.hashContent)
        this.setHashSearch(this.hashList);
        if(this.isHashResult) {
          this.isHashResult = false
          this.isHashResult = true
        } else {
          this.isDefault = false
          this.isUserResult = false
          this.isHashResult = true
        }
        this.hashContent = ''
        this.hashList = []
      }
    },
    onUserResult() {
      if (this.userContent) {
        this.setUserSearch(this.userContent);
        this.isDefault = false
        this.isHashResult = false
        this.isUserResult = true
        this.userContent = ''
      }
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
    setList() {
      for (let i=0; i<this.articleList.length/3; i++) {
        for (let j=0; j<3; j++) {
          this.tempList.push(this.articleList[j+i*3])
        }
        this.feedList.push(this.tempList)
        this.tempList = []
      }
    },
    onModal(data) {
      this.setArticledata(data.articleNo);
      this.showModal = true
    },
    setHashList() {
      this.hashString += `#${this.hashList[this.hashList.length-1]} `
    },
    inUserSearch() {
      axios.get(`https://i3b304.p.ssafy.io/api/search/user`,{
      params: {
        username: this.userContent
      },
      }).then((data) => {
        console.log(data, '실시간유저')
        if(data.data.object.length>5) {
          this.userList = data.data.object.splice(0,5)
          this.userListLength = data.data.object.length - 5
        } else {
          this.userList = data.data.object
          this.userListLength = 0
        }
      }).catch()
    },
    goProfile(name) {
      this.$router.push(`/otheruser/${name}`)
    }
  },
  mounted() {
    this.goSearch()
    this.defaultDark()


    axios.post("https://i3b304.p.ssafy.io/api/search/").then((data)=>{
      this.articleList=data.data;
      console.log(this.articleList)
      this.setList();
    })
  }
}
</script>