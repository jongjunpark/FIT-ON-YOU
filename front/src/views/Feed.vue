<template>
  <div class='wrap'>
    <div class='wrap-container'>
      <header class="feed-user-data">
        <div class="feed-user-profile"></div>
        <div class="feed-article-head">
          <p class='feed-username'>Username</p>
          <p class='feed-article-date'>20.00.00 00:00</p>
        </div>
      </header>
      <section class="feed-content">
        <article class="feed-content-img"></article>
        <header class='feed-content-head'>Text Name</header>
        <aside class='feed-content-tag'>#ABC #DEF #GHI #JKL</aside>
      </section>
    </div>
  </div>
</template>

<script>
import "../components/css/feed.css"
import axios from 'axios'
import { mapState, mapGetters, mapMutations } from 'vuex'

export default {
  name: 'Feed',
  data(){
    return{
      feedlist:{},
    }
  },
  mounted() {
    const formData = new FormData();
    console.log(this)
    console.log(this.user)
    formData.append('nickname',this.user.nickname);
    axios.post("http://localhost:8080/board/newsfeed",formData).then((data)=>{
    console.log("success")
    console.log(data,1)
    });
    
  },
  computed: {
    ...mapState(['user', 'isLoggedIn', 'authToken']),
    ...mapGetters([''])
  },
  methods: {
    ...mapMutations(['setLoggedIn', 'setToken', 'setUser'])
  },
}
</script>

