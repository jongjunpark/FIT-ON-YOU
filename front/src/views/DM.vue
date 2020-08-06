<template>
  <div class="wrap">
    <div class="wrap-container-dm">
      <div class="dm-container">
        <div class="dm-container-message">
          <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
          <h3>Username</h3>
          <h5 class="in-text">마지막 메세지입니다.</h5>
          <h5 class="in-text">1주전</h5>
        </div>
        <div class="dm-container-message">
          <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
          <h3>Username2</h3>
          <h5 class="in-text">안녕하세요.</h5>
          <h5 class="in-text">1달전</h5>
        </div>
        <div class="dm-container-message">
          <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
          <h3>Username</h3>
          <h5 class="in-text">Lorem ipsum, dolor sit amet</h5>
          <h5 class="in-text">1년전</h5>
        </div>
      </div>
      <div class="search-dm">
        <input type="text" name="" id="" class="search-dm-input" placeholder="검색">
      </div>
      <i class="fas fa-search search-img-dm"></i>
    </div>
  </div>
</template>

<script>
import "../components/css/dm.css"

export default {
  name: 'DM',
  data() {
    return {}
  },
}
</script>
