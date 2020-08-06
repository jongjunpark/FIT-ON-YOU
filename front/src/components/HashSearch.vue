<template>
  <div class='wrap-container'>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="$" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="$" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
    <div class="hash-search-box">
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
      <div class="hash-search-inner-box">
        <img src="#" alt="">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HashSearch'
}
</script>

<style scoped>
.hash-search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.hash-search-inner-box {
  width: 33%;
  padding-top: 30%;
  margin: 1px;
  background-color: grey;
  position: relative
}

.hash-search-inner-box img {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
</style>