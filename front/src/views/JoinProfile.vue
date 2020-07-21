<template>
  <div class='wrap'>
    <div class='wrap-container center-container'>
      <section class='join-profile-area'>
        <div class='join-profile-img'>
          <div class="join-profile-img-edit"></div>
        </div>
        <p class='join-profile-username'>Username</p>
        <textarea class='join-profile-usercontent' name="" id="" cols="50" rows="3" placeholder="자기소개를 작성해 주세요"></textarea>
      </section>
      <div class='btn join-profile-btn'>저장하기</div>
      <div class='btn on-join-profile-btn'>저장하기</div>
      <div class='btn join-skip-btn'>건너뛰기</div>
    </div>
  </div>
</template>

<script>
import "../components/css/joinprofile.css"

export default {
  name: 'JoinProfile.vue'
}
</script>
