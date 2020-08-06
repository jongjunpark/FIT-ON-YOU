<template>
  <div class='wrap'>
    <div class="wrap-container">
      <p class='join-confirm-logo'>FIT ON U</p>
      <form action="" id="joinForm">
        <ul class="join_box">
          <li class="checkBox check01">
            <ul class="clearfix">
              <li>이용약관, 개인정보 수집 및 이용에 모두 동의합니다.</li>
              <li class="checkAllBtn">
                <i class="far fa-check-circle check-btn-1" @click='onBtn(1)'></i>
              </li>
            </ul>
          </li>
          <hr class='join-confirm-divide-line'>
          <li class="checkBox check02">
            <ul class="clearfix">
              <li>이용약관 동의 (필수)</li>
              <li class="checkBtn">
                <i class="far fa-check-circle check-btn-2" @click='onBtn(2)'></i> 
              </li>
            </ul>
            <textarea name="" id=""> 여러분을 환영합니다. FIT ON U 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 FIT ON U 서비스의 이용과 관련하여 FIT ON U 서비스를 제공하는 FIT ON U 주식회사(이하 ‘FIT ON U’)와 이를 이용하는 FIT ON U 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 FIT ON U 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
            </textarea>
          </li>
          <li class="checkBox check03">
            <ul class="clearfix">
              <li>개인정보 수집 및 이용에 대한 안내 (필수)</li>
              <li class="checkBtn">
                <i class="far fa-check-circle check-btn-3" @click='onBtn(3)'></i>
              </li>
            </ul>
            <textarea name="" id=""> 여러분을 환영합니다. FIT ON U 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 FIT ON U 서비스의 이용과 관련하여 FIT ON U 서비스를 제공하는 FIT ON U 주식회사(이하 ‘FIT ON U’)와 이를 이용하는 FIT ON U 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 FIT ON U 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
            </textarea>
          </li>
        </ul>
        <div class="join-confirm-btn-area">
          <div v-if="!(isCheck1&&isCheck2&&isCheck3)" class="join-confirm-agree-off join-confirm-btn">동의</div>
          <div v-if="isCheck1&&isCheck2&&isCheck3" class="join-confirm-agree join-confirm-btn">동의</div>
          <div class="join-confirm-disagree join-confirm-btn">비동의</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import "../components/css/joinconfirm.css"
export default {
  name: 'JoinConfirm',
  data() {
    return {
      isCheck1: false,
      isCheck2: false,
      isCheck3: false,
    }
  },
  methods: {
    onBtn(num) {
      const CHECK1 = document.querySelector('.check-btn-1')
      const CHECK2 = document.querySelector('.check-btn-2')
      const CHECK3 = document.querySelector('.check-btn-3')
      if (num === 1) {
        if (!this.isCheck1) {
          CHECK1.classList.add('on-check-btn')
          CHECK2.classList.add('on-check-btn')
          CHECK3.classList.add('on-check-btn')
          this.isCheck1 = true
          this.isCheck2 = true
          this.isCheck3 = true
        } else {
          CHECK1.classList.remove('on-check-btn')
          CHECK2.classList.remove('on-check-btn')
          CHECK3.classList.remove('on-check-btn')
          this.isCheck1 = false
          this.isCheck2 = false
          this.isCheck3 = false
        }
      } else if (num === 2) {
        if (!this.isCheck2) {
          CHECK2.classList.add('on-check-btn')
          this.isCheck2 = true
          if (this.isCheck3) {
            CHECK1.classList.add('on-check-btn')
            this.isCheck1 = true
          }
        } else {
          if (this.isCheck1) {
            CHECK1.classList.remove('on-check-btn')
            this.isCheck1 = false
          }
          CHECK2.classList.remove('on-check-btn')
          this.isCheck2 = false
        }
      } else {
        if (!this.isCheck3) {
          CHECK3.classList.add('on-check-btn')
          this.isCheck3 = true
          if (this.isCheck2) {
            CHECK1.classList.add('on-check-btn')
            this.isCheck1 = true
          }
        } else {
          if (this.isCheck1) {
            CHECK1.classList.remove('on-check-btn')
            this.isCheck1 = false
          }
          CHECK3.classList.remove('on-check-btn')
          this.isCheck3 = false
        }
      }
    }
  }
}
</script>

<style>

</style>