<template>
  <div class='wrap'>
    <div class='container'>
      <div class="user-data">
        <div class="user-profile"></div>
        <div class="article-head">
          <p class='username'>Username</p>
          <p class='article-date'>20.00.00 00:00</p>
        </div>
      </div>
      <div class="content">
        <div class="content-img"></div>
        <p class='content-head'>Text Name</p>
        <p class='content-tag'>#ABC #DEF #GHI #JKL</p>
      </div>
    </div>
    <div class='container'>
      <div class="user-data">
        <div class="user-profile"></div>
        <div class="article-head">
          <p class='username'>Username</p>
          <p class='article-date'>20.00.00 00:00</p>
        </div>
      </div>
      <div class="content">
        <div class="content-img"></div>
        <p class='content-head'>Text Name</p>
        <p class='content-tag'>#ABC #DEF #GHI #JKL</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Feed'
}
</script>

<style scoped>
.wrap {
  padding: 0 30px;
}

@media (min-width: 1200px) {
    .wrap {
    max-width: 580px;
    width: 100%;
    margin: 0 auto;
  }
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 50px 0;
}
.user-data {
  display: flex;
  width: 100%;
}
.user-profile {
  width: 50px;
  height: 50px;
  background-color: grey;
  border-radius: 50px;
}
.article-head p {
  margin: 0 20px;
}
.article-head .username {
  font-weight: 700;
}
.article-head .article-date {
  font-size: 14px;
  color: grey;
}

.content {
  width: 100%;
  margin: 30px 0;
}
.content .content-img {
  width: 100%;
  height: 500px;
  background-color: grey;
}
.content .content-head {
  font-weight: 900;
  font-size: 30px;
  margin: 5px 0;
}

.content .content-tag {
  font-weight: 700;
  font-size: 20px;
  margin: 5px 0;
}
</style>

