<template>
  <div class='wrap-container'>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
    <div class="user-search-box">
      <div class="user-search-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="user-search-text-area">
        <div class="user-search-username">Username</div>
        <div class="user-search-userintro">한줄소개</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'UserSearch',
  mounted() {
    this.defaultDark()
  },
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
      }
    },
  },
}
</script>

<style scoped>
/* .user-search-container {
  width: 100%;
  height: 70vh;
  overflow-y: auto;
}
.user-search-container::-webkit-scrollbar { width: 5px; }
::-webkit-scrollbar-track { background-color: transparent; }
::-webkit-scrollbar-thumb { background: silver;}
::-webkit-scrollbar-button { display: none; } */

.user-search-box {
  width: 100%;
  height: 8vh;
  background-color: grey;
  display: flex;
  border-bottom: 1px solid red;
  position: relative;
  margin: 1.5vh 0;
}

.user-search-box .user-search-icon-area {
  height: 100%;
  background-color: burlywood;
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
  background-color: black;
  border-radius: 50%;
}

.user-search-box .user-search-text-area {
  background-color: cornflowerblue;
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
}

.user-search-text-area .user-search-userintro {
  font-size: 1.7vh;
  display: flex;
  align-items: center;
  background-color: rgb(166, 248, 186);
  font-weight: 500;
  height: 50%;
  width: 100%;
}
</style>