<template>
  <div id="app">
    <div class="nav-base"></div>
    <div id="nav">
      <div class="nav-logo">Logo</div>
      <div class="nav-user">
        <div class="nav-user-icon nav-user-img">img</div>
        <div class="nav-user-icon nav-go-mypage">mypage</div>
        <div class="nav-user-icon nav-go-dm">DM</div>
        <div class="nav-user-icon nav-go-alarm">Alarm</div>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/common.css";
export default {
  
}
</script>
