<template>
  <div class='wrap'>
    <div class="wrap-container">
      <p class='join-confirm-logo'>FIT ON U</p>
      <form action="" id="joinForm">
        <ul class="join_box">
          <li class="checkBox check01">
            <ul class="clearfix">
              <li>이용약관, 개인정보 수집 및 이용에 <br>모두 동의합니다.</li>
              <li class="checkAllBtn">
                <input type="checkbox" name="chkAll" id="chk" class="chkAll">
              </li>
            </ul>
          </li>
          <hr class='join-confirm-divide-line'>
          <li class="checkBox check02">
            <ul class="clearfix">
              <li>이용약관 동의 (필수)</li>
              <li class="checkBtn">
                <input type="checkbox" name="chk"> 
              </li>
            </ul>
            <textarea name="" id=""> 여러분을 환영합니다. FIT ON U 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 FIT ON U 서비스의 이용과 관련하여 FIT ON U 서비스를 제공하는 FIT ON U 주식회사(이하 ‘FIT ON U’)와 이를 이용하는 FIT ON U 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 FIT ON U 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
            </textarea>
          </li>
          <li class="checkBox check03">
            <ul class="clearfix">
              <li>개인정보 수집 및 이용에 대한 안내 (필수)</li>
              <li class="checkBtn">
                <input type="checkbox" name="chk">
              </li>
            </ul>
            <textarea name="" id=""> 여러분을 환영합니다. FIT ON U 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 FIT ON U 서비스의 이용과 관련하여 FIT ON U 서비스를 제공하는 FIT ON U 주식회사(이하 ‘FIT ON U’)와 이를 이용하는 FIT ON U 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 FIT ON U 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
            </textarea>
          </li>
        </ul>
        <div class="join-confirm-btn-area">
          <div class="join-confirm-agree join-confirm-btn">동의</div>
          <div class="join-confirm-disagree join-confirm-btn">비동의</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import "../components/css/joinconfirm.css"
export default {
  name: 'JoinConfirm'
}
</script>

<style>

</style>