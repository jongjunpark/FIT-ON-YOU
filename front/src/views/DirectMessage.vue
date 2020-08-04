<template>
  <div class="wrap">
    <div class="wrap-container-direct">
      <div class="wrap-direct">
        <p class="back-btn">〈 </p>
        <div class="in-img">
          <img src="../assets/images/default-user.png" alt="" class="in-img-profile">
        </div>
        <p class="back-btn-name">kidcozyboy</p>
      </div>
      <div class="message-content">
        <div class="user-opponent">
          <img src="../assets/images/default-user.png" alt="" class="in-img-content">
          <p class="in-user-content">Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus modi laudantium.</p>
        </div>
        <div class="user-me">
          <p class="user-me-content">
            안녕하세요.
          </p>
        </div>
        <div class="user-opponent">
          <img src="../assets/images/default-user.png" alt="" class="in-img-content">
          <p class="in-user-content">배고파.</p>
        </div>
      </div>
      <div class="input-message">
        <input type="textarea" name="" id="" class="input-message-in" placeholder="메세지 보내기...">
      </div>
      <button class="butn">↑</button>
    </div>
  </div>
</template>

<script>
import "../components/css/directmessage.css"

export default {
  name: 'DirectMessage',
  data() {
    return {}
  },
}
</script>
