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
export default {
  name: 'Following'
}
</script>

<style>
.following-container {
  width: 100%;
}

.following-container .following-box {
  width: 100%;
  background-color: grey;
  display: flex;
  border-bottom: 1px solid red;
  padding-top: 13vw;
  position: relative;
  margin: 2vh 0;
}
@media (min-width: 1200px) {
  .following-container .following-box {
    padding-top: 10%;
  }
}


.following-box .following-icon-area {
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
  .following-box .following-icon-area {
    width: 20%;
  }
}


.following-box .following-icon-area img {
  width: 100%;
  height: 100%;
  background-color: black;
  margin: 0 25%;
  border-radius: 50%;
  /* width: 100%;
  height: 100%; */
}

.following-box .following-text-area {
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
  .following-box .following-text-area {
    left: 20%;
    width: 80%;
  }
}


.following-text-area .following-username {
  font-size: 4vw;
  font-weight: 700;
  height: 50%;
}
@media (min-width: 1200px) {
  .following-text-area .following-username {
    font-size: 100%;
  }
}


.following-text-area .following-btn-area {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50%;
  background-color: darkcyan;
}

.following-btn-area .following-follow-btn {
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
  .following-btn-area .following-follow-btn {
    font-size: 100%;
    line-height: 1.5;
  }
}


.following-btn-area .following-dm-btn {
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
  .following-btn-area .following-dm-btn {
    font-size: 100%;
    line-height: 1.5;
  }
}
</style>