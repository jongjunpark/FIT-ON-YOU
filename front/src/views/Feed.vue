<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <header class="user-data">
        <div class="user-profile"></div>
        <div class="article-head">
          <p class='username'>Username</p>
          <p class='article-date'>20.00.00 00:00</p>
        </div>
      </header>
      <section class="content">
        <article class="content-img"></article>
        <header class='content-head'>Text Name</header>
        <aside class='content-tag'>#ABC #DEF #GHI #JKL</aside>
      </section>
    </div>
  </div>
</template>

<script>
import "../components/css/feed.css"

export default {
  name: 'Feed'
}
</script>

