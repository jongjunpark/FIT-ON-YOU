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
export default {
  name: 'UserSearch'
}
</script>

<style>
.wrap-container .user-search-box {
  width: 100%;
  background-color: grey;
  display: flex;
  border-bottom: 1px solid red;
  padding-top: 13vw;
  position: relative;
  margin: 2vh 0;
}
@media (min-width: 1200px) {
  .wrap-container .user-search-box {
    padding-top: 10%;
  }
}


.user-search-box .user-search-icon-area {
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
  .user-search-box .user-search-icon-area {
    width: 20%;
  }
}


.user-search-box .user-search-icon-area img {
  width: 100%;
  height: 100%;
  background-color: black;
  margin: 0 25%;
  border-radius: 50%;
  /* width: 100%;
  height: 100%; */
}

.user-search-box .user-search-text-area {
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
  .user-search-box .user-search-text-area {
    left: 20%;
    width: 80%;
  }
}


.user-search-text-area .user-search-username {
  font-size: 4vw;
  font-weight: 700;
  height: 50%;
}
@media (min-width: 1200px) {
  .user-search-text-area .user-search-username {
    font-size: 100%;
  }
}


.user-search-text-area .user-search-userintro {
  font-size: 3vw;
  font-weight: 500;
  height: 50%;
}
@media (min-width: 1200px) {
  .user-search-text-area .user-search-userintro {
    font-size: 80%;
  }
}
</style>