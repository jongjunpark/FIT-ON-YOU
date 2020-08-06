<template>
  <div class="wrap">
    <div class="wrap-container-alarm">
      <div class="alarm-container-wrap">
        <div class="alarm-container">
          <div class="alarm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="alarm-container-message-img">
            <i class="fas fa-heart heart-alarm"></i>
            <h4 class="">Username님이 회원님의 사진을 좋아요 눌렀습니다.</h4>
            <h5 class="in-text">1달전</h5>
          </div>
          <div class="alarm-container-message-read">
            <img src="../assets/images/default-user.png" alt="" class="alarm-container-message-img">
            <i class="fas fa-user-circle user-alarm"></i>
            <h4 class="">Username님이 회원님을 팔로우하기 시작하였습니다.</h4>
            <h5 class="in-text">1달전</h5>
          </div>
          <div class="alarm-container-message">
            <img src="../assets/images/default-user.png" alt="" class="dm-container-message-img">
            <i class="fas fa-comment-dots chat-alarm"></i>
            <h4 class="">Username님이 회원님의 게시물에 댓글을 남겼습니다.</h4>
            <h5 class="in-text">1년전</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/alarm.css"

export default {
  name: 'Alarm',
  data() {
    return {}
  },
}
</script>
