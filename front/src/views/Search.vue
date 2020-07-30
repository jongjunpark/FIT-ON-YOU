<template>
  <div class='wrap'>
    <div class="search-bar">
      <i v-if='isReturn' @click='setReturn' class="fas fa-undo-alt"></i>
      <transition name='fade' mode="out-in">
        <i key=1 v-if='isHashSearch' @click='setHash' class="fas fa-hashtag"></i>
        <i key=2 v-else class="fas fa-user"></i>
      </transition>
      <i v-if='isUserSearch' @click='setUser' class="fas fa-user"></i>
      <input @input="hashContent = $event.target.value" v-if='isHashInput' type="text" class="search-input" placeholder="'#'은 필수입니다.">
      <input @input="userContent = $event.target.value" v-if='isUserInput' type="text" class="search-input" placeholder="유저이름을 입력하세요">
    </div>
    <div class='wrap-container'>
      <div class="search-box">
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Search',
  data() {
    return {
      isHashSearch: true,
      isUserSearch: true,
      isReturn: false,
      isHashInput: false,
      isUserInput: false,
      hashContent: '',
      userContent: '',
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
      if(this.isUserInput) {
        this.isUserInput = false
        this.isHashInput = true
      } else if (this.isHashInput) {
        this.isHashInput = false
        this.isUserInput = true
      }
    },
    checkHashTag() {
      
    }
  },
  mounted() {
    this.goSearch()
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
  z-index: 50;
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


.search-bar .fas{
  font-size: 150%;
  margin: 0 10px;
  color: black;
  transition: ease 0.3s;
}

.search-bar .fas:hover {
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
  background-color: rgb(223, 223, 223);
  width: 50%;
}

.search-input:focus {
  outline: none;
}

.search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.search-inner-box {
  width: 30%;
  padding-top: 30%;
  margin: 5px;
  background-color: grey;
  position: relative
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s !important;
}

.fade-enter,
.fade-leave-to
/* .fade-leave-active below version 2.1.8 */

{
  opacity: 0;
}
</style>