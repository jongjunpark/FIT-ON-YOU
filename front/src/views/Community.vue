<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <div class="search-box">
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
        <div class="search-inner-box">
        </div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
      <div class="search-box">
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
        <div class="search-inner-box"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Community',
  methods: {
    goCommunity() {
      const selectBar = document.querySelector('.menu-bar-select')
      const newsFeed = document.querySelector('.fa-newspaper')
      const search = document.querySelector('.fa-search')
      const community = document.querySelector('.fa-users')
      newsFeed.style.color = 'grey'
      search.style.color = 'grey'
      community.style.color = 'black'
      selectBar.classList.add('go-third-menu')
      selectBar.classList.remove('go-second-menu')
      selectBar.classList.remove('go-first-menu')
    },
  },
  mounted() {
    this.goCommunity()
  }
}
</script>

<style>
.search-box {
  display: flex;
  justify-content: center;
  width: 100%;
}

.search-inner-box {
  width: 30%;
  padding-top: 30%;
  margin: 5px;
  background-color: grey;
  position: relative
}
</style>