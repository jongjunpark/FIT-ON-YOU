<template>
  <div class='wrap'>
    <div class="search-bar">
      <i v-if='isReturn' @click='setReturn' class="fas fa-undo-alt"></i>
      <transition name='fade' mode="out-in">
        <i key=1 v-if='isHashSearch' @click='setHash' class="fas fa-hashtag"></i>
        <i key=2 v-else class="fas fa-user"></i>
      </transition>
      <i v-if='isUserSearch' @click='setUser' class="fas fa-user"></i>
      <input @input="hashContent = $event.target.value" v-model='hashContent' 
        v-if='isHashInput' @keypress.enter='onHashResult' @keyup.188="addHash" type="text" class="search-input" placeholder=" , 를 통해 구분해주세요">
      <span v-if='isHashInput'>
        <i @click='onHashResult' class="fab inner-search-btn fa-sistrix"></i>
      </span>
      <input @input="userContent = $event.target.value" v-model='userContent' 
        v-if='isUserInput' @keypress.enter='onUserResult' type="text" class="search-input" placeholder="유저이름을 입력하세요">
      <span v-if='isUserInput'>
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

      <div class="search-box">
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/s640x640/115999683_208712917242400_7853725733733513666_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_ohc=AvPIi_dvY8YAX9_vvo7&oh=33cf0cb25092a6bda81d298f097107dd&oe=5F4DE678" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/c0.25.1440.1440a/s640x640/116404648_153039273082921_5182030600312684607_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_ohc=IBpQweG5lHMAX_QSmWF&oh=5c3ef3b19e8d9df0f4e1b28922208630&oe=5F4A8DBF" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/c0.180.1440.1440a/s640x640/115908392_3143316965736561_5295481708585333350_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=109&_nc_ohc=plvc2NP7j5AAX-XaM0C&oh=db8994197f977d6a53393c8122811946&oe=5F4DA6CB" alt="">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116363553_752834785516476_1926326966447616076_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=100&_nc_ohc=OQtQ2R5KUcwAX-dbIaU&oh=e0b1affcf56a2cdf469c6339ae1af958&oe=5F4D1E40" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/c5.0.1069.1069a/s640x640/116258824_768107933923433_7405040603524361891_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=8_VGBuiEN-4AX_3Sxon&oh=c3b5b0dc3d977c8c1ef5014fb0e8075a&oe=5F4D7643" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116592429_1139437373095582_3420540484434190690_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=110&_nc_ohc=rYAxs7P8CVoAX_E0Hwf&oh=c5cc8b2e3a48d679177594901f603aa6&oe=5F4A9ACC" alt="">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116328862_289433112117542_7542864421156657669_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=102&_nc_ohc=YuC-qqESf-wAX-CiGoE&oh=cc30844440724a9856cf4ea0307cfdf9&oe=5F4C92E6" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/116340371_573653673326588_4109177457182452730_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=108&_nc_ohc=A3LQGhrDZiQAX_HJ-ye&oh=1fa6e2c2a41621fdb093c58ade12bb12&oe=5F4BF43F" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/115907077_990834288004690_2656502429638811500_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=110&_nc_ohc=s57L4UMgJLsAX8St89P&oh=945a14bc80ecb6137d969d46329af174&oe=5F4DBAE5" alt="">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/s640x640/116473579_2695116987444102_90220358719912267_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=aHiKsGVQZWIAX8O46An&oh=69fdb8ae2cb580c65dbd10f8c6646158&oe=5F4C8B47" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/s640x640/116049969_1434087843442893_1447786432385798003_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=109&_nc_ohc=kNvn88lz7ssAX_WoY6D&oh=56d0fdfdbc6720c51ef2e64e42a9f7e6&oe=5F4BE581" alt="">
        </div>
        <div class="search-inner-box">
          <img src="https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/sh0.08/e35/s640x640/116266169_338416570508927_1633965425111792034_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_ohc=j3qT-_BzercAX9-FKaT&oh=0830e3111f252b5eb124fbf5cfa0df58&oe=5F4AA8B4" alt="">
        </div>
      </div>
    </div>
    <HashSearch v-if="isHashResult"></HashSearch>
    <UserSearch v-if="isUserResult"></UserSearch>
  </div>
</template>

<script>
import HashSearch from '../components/HashSearch.vue'
import UserSearch from '../components/UserSearch.vue'

export default {
  name: 'Search',
  components: {
    HashSearch,
    UserSearch,
  },
  data() {
    return {
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
    }
  },
  watch: {
    hashContent() {
      this.checkHashTag();
    },
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
  mounted() {
    this.goSearch()
    this.defaultDark()
  }
}
</script>

<style scoped>
.search-bar {
  position: fixed;
  top: 0;
  left: 50%;
  transform: translate(-50%, 0);
  width: 50%;
  height: 60px;
  z-index: 50000;
  display: flex;
  justify-content: center;
  align-items: center;
}
@media (min-width: 1200px) {
  .search-bar {
  width: 300px;
  }
}
@media (max-width: 290px) {
  .search-bar {
  width: 100px;
  }
}

.search-bar .fas {
  transition: ease 0.3s;
}

.search-bar .fa-hashtag, .fa-user{
  font-size: 150%;
  margin: 0 10px;
  color: black;
  transition: ease 0.3s;
}

.search-bar .fas:hover{
  color: #5AAEFF;
}

.search-bar .fa-user:hover {
  color: #5AAEFF; 
}

.search-bar .fa-undo-alt {
  font-size: 100%;
  margin: 0;
  transform: rotate(110deg)
}

.search-bar .fa-undo-alt:hover {
  color: tomato;
  transform: rotate(480deg);
  transition: ease 0.8s;
}


.search-input {
  border: none;
  border-bottom: 2px solid black;
  background-color: #fff;
  width: 50%;
}

.search-input:focus {
  outline: none;
}

.search-input::placeholder {
  font-size: 1vw;
}

.search-bar .inner-search-btn {
  font-size: 100%;
  margin: 0;
  color: #050505;
}

.search-bar .inner-search-btn:hover {
  color: #5AAEFF;
}



.search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.search-inner-box {
  width: 33%;
  padding-top: 30%;
  margin: 1px;
  background-color: grey;
  position: relative
}

.search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s !important;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

.hash-group {
  position: absolute;
  top: 60px;
  left: 50%;
  transform: translate(-50%, 0);
  width: 120%;
  border-radius: 5px;
  background-color: white;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.hash-group .hash-item {
  padding: 1px 5px;
  background-color: #050505;
  color: #fff;
  border-radius: 10px;
  margin: 5px;
  font-size: 80%;
  padding-left: 3%;
  font-weight: 700;
}

.hash-item .hash-item-close-btn {
  display: inline-block;
  cursor: pointer;
  /* margin-right: 5px; */
}

.hash-item-close-btn .fa-times {
  font-size: 80%;
  font-weight: 600;
  color: #fff;
  margin: 0;
  margin-bottom: 5px;
  margin-right: 5px;
}

.hash-item-close-btn .fa-times:hover{
  color: #fc0303;
}


.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

</style>