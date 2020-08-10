<template>
  <div class='follower-container'>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username">Username</div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn">팔로우하기</div>
          <div class="follower-dm-btn">DM보내기</div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
    <div class="follower-box">
      <div class="follower-icon-area">
        <img src="#" alt="#">
      </div>
      <div class="follower-text-area">
        <div class="follower-username"></div>
        <div class="follower-btn-area">
          <div class="follower-follow-btn"></div>
          <div class="follower-dm-btn"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Follower',
  mounted() {
    this.defaultDark()
  },
  methods: {
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
}
</script>

<style>
.follower-container {
  width: 100%;
}

.follower-container .follower-box {
  width: 100%;
  background-color: grey;
  display: flex;
  border-bottom: 1px solid red;
  padding-top: 13vw;
  position: relative;
  margin: 2vh 0;
}
@media (min-width: 1200px) {
  .follower-container .follower-box {
    padding-top: 10%;
  }
}


.follower-box .follower-icon-area {
  position: absolute;
  top:0; bottom:0; left:0; right:0;
  width: 30%;
  height: 100%;
  background-color: burlywood;
  display: flex;
  justify-content: center;
  align-items: center;
}
@media (min-width: 1200px) {
  .follower-box .follower-icon-area {
    width: 20%;
  }
}


.follower-box .follower-icon-area img {
  width: 100%;
  height: 100%;
  background-color: black;
  margin: 0 25%;
  border-radius: 50%;
  /* width: 100%;
  height: 100%; */
}

.follower-box .follower-text-area {
  position: absolute;
  top:0; bottom:0; left:30%; right:0;
  background-color: cornflowerblue;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 70%;
  height: 100%;
}
@media (min-width: 1200px) {
  .follower-box .follower-text-area {
    left: 20%;
    width: 80%;
  }
}


.follower-text-area .follower-username {
  font-size: 4vw;
  font-weight: 700;
  height: 50%;
}
@media (min-width: 1200px) {
  .follower-text-area .follower-username {
    font-size: 100%;
  }
}


.follower-text-area .follower-btn-area {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50%;
  background-color: darkcyan;
}

.follower-btn-area .follower-follow-btn {
  width: 30%;
  height: 100%;
  background-color: orange;
  border-radius: 10px;
  text-align: center;
  font-weight: 700;
  font-size: 3vw;
  line-height: 2;
  margin-right: 20%;
}
@media (min-width: 1200px) {
  .follower-btn-area .follower-follow-btn {
    font-size: 100%;
    line-height: 1.5;
  }
}


.follower-btn-area .follower-dm-btn {
  width: 30%;
  height: 100%;
  background-color: palegreen;
  border-radius: 10px;
  text-align: center;
  font-weight: 700;
  font-size: 3vw;
  line-height: 2;
}
@media (min-width: 1200px) {
  .follower-btn-area .follower-dm-btn {
    font-size: 100%;
    line-height: 1.5;
  }
}
</style>