<template>
  <div class='following-container'>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username">Username</div>
        <div class="following-btn-area">
          <div class="following-follow-btn">팔로우하기</div>
          <div class="following-dm-btn">DM보내기</div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="following-box">
      <div class="following-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="following-text-area">
        <div class="following-username"></div>
        <div class="following-btn-area">
          <div class="following-follow-btn"></div>
          <div class="following-dm-btn"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'Following',
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
.following-container {
  width: 100%;
  height: 70vh;
  overflow-y: auto;
}
.following-container::-webkit-scrollbar { width: 5px; }
::-webkit-scrollbar-track { background-color: transparent; }
::-webkit-scrollbar-thumb { background: silver;}
::-webkit-scrollbar-button { display: none; }

.following-container .following-box {
  width: 100%;
  height: 8vh;
  background-color: grey;
  display: flex;
  border-bottom: 1px solid red;
  position: relative;
  margin: 1.5vh 0;
}

.following-box .following-icon-area {
  height: 100%;
  background-color: burlywood;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 2vh;
}
@media (max-width: 449px) {
  .following-box .following-icon-area {
    margin: 0 1vh;
  }
}

.following-box .following-icon-area img {
  width: 7vh;
  height: 7vh;
  background-color: black;
  border-radius: 50%;
}

.following-box .following-text-area {
  background-color: cornflowerblue;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.following-text-area .following-username {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
}

.following-text-area .following-btn-area {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50%;
  background-color: darkcyan;
}
@media (max-width: 449px) {
  .following-text-area .following-btn-area {
    justify-content: space-evenly;
  }
}

.following-btn-area .following-follow-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: orange;
  border-radius: 1.5vh;
  margin-right: 10vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
}
@media (max-width: 449px) {
  .following-btn-area .following-follow-btn {
    margin-right: 0;
  }
}
@media (max-width: 300px) {
  .following-btn-area .following-follow-btn {
    width: 11vh
  }
}

.following-btn-area .following-dm-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: palegreen;
  border-radius: 1.5vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
}
@media (max-width: 300px) {
  .following-btn-area .following-dm-btn {
    width: 11vh
  }
}
</style>