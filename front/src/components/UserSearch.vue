<template>
  <div class='wrap-container user-search-container'>
    <div v-show="userResultList" class="user-search-box" v-for="user in userResultList" :key="user.nickname">
      <div class="user-search-icon-area">
        <img v-show="user.profile_img" @click="goProfile(user.nickname)" :src="user.profile_img" :alt="user.nickname">
        <img v-show="!user.profile_img&&user.gender=='Male'" @click="goProfile(user.nickname)" src="../assets/images/default-user.png" alt="">
        <img v-show="!user.profile_img&&user.gender=='Female'" @click="goProfile(user.nickname)" src="../assets/images/default-user-female.png" alt="">
      </div>
      <div class="user-search-text-area">
        <div @click="goProfile(user.nickname)" class="user-search-username">{{ user.nickname }}</div>
        <div v-show="!user.selfintroduce" class="user-search-nonuserintro"></div>
        <div v-show="user.selfintroduce[20]" class="user-search-userintro">{{ user.selfintroduce.substring(0,20) }}</div>
        <div v-show="user.selfintroduce&&!user.selfintroduce[20]" class="user-search-userintro">{{ user.selfintroduce }}</div>
      </div>
    </div>
    <div v-show="!userResultList" class='user-search-not-result'>
        <p>일치하는 검색결과가 없습니다.</p>
      </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'UserSearch',
  data() {
    return {
      userResultList: [],
    }
  },
  computed: {
    ...mapState(['flag', 'userSearch'])
  },
  watch: {
    flag() {
      this.defaultDark()
    },
    userSearch() {
      this.getUserSearch()
    }
  },
  updated() {
    this.defaultDark();
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const PTAGI = document.querySelector('p')
      const USERNAME = document.querySelectorAll('.user-search-username')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<USERNAME.length; i++) {
          USERNAME[i].classList.add('font-dark')
        }
        if (PTAGI) {
          PTAGI.classList.add('dark-onon')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<USERNAME.length; i++) {
          USERNAME[i].classList.remove('font-dark')
        }
        if (PTAGI) {
          PTAGI.classList.remove('dark-onon')
        }
      }
    },
    getUserSearch() {
      axios.get(`https://i3b304.p.ssafy.io/api/search/user`,{
      params: {
        username: this.userSearch
      },
      }).then((data) => {
        this.userResultList = data.data.object
      }).catch()
    },
    goProfile(name) {
      this.$router.push(`/otheruser/${name}`).catch(() => {})
    },
  },
  mounted() {
    this.defaultDark();
    this.getUserSearch();
  }
}
</script>

<style scoped>
.user-search-container {
  width: 100%;
  height: 80vh;
  overflow-y: auto;
}
.user-search-container::-webkit-scrollbar { width: 5px; }
::-webkit-scrollbar-track { background-color: transparent; }
::-webkit-scrollbar-thumb { background: silver;}
::-webkit-scrollbar-button { display: none; }

.user-search-box {
  width: 100%;
  height: 8vh;
  display: flex;
  position: relative;
  margin: 1vh 0;
}

.user-search-box:hover {
  background-color: rgba(0,0,0,0.1);
}

.user-search-box .user-search-icon-area {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 2vh;
}
@media (max-width: 449px) {
  .user-search-box .user-search-icon-area {
    margin: 0 1vh;
  }
}

.user-search-box .user-search-icon-area img {
  width: 7vh;
  height: 7vh;
  border-radius: 50%;
  border: 0.5px solid black;
  cursor: pointer;
}

.user-search-box .user-search-text-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.user-search-text-area .user-search-username {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
  cursor: pointer;
}

.user-search-text-area .user-search-userintro {
  font-size: 1.5vh;
  color: grey;
  display: flex;
  align-items: center;
  font-weight: 500;
  height: 50%;
  width: 100%;
  padding-bottom: 1vh;
}

.user-search-text-area .user-search-nonuserintro {
  height: 20%;
  width: 100%;
}

.user-search-not-result {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  padding-top: 20vh;
  font-size: 2.5vh;
  font-weight: 700;
}

.dark-onon {
  color: white;
}
</style>