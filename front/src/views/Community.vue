<template>
  <div class='wrap'>
    <div class='wrap-container community-container'>
      <div class="search-inner-box" v-for="(article,index) in recellList" :key="`recell-${index}`">
        <img :src="article.imgurl" alt=""></div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import "../components/css/community.css"
import axios from 'axios'
export default {
  name: 'Community',
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  data(){
    return{
      tempList:[],
      recellList:[],
    }
  },
  methods: {
    ...mapMutations(['setIsSelectBar']),
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
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
      }
    },
  },
  mounted() {
    this.setIsSelectBar(true)
    this.goCommunity()
    this.defaultDark()

     axios.post("https://i3b304.p.ssafy.io/api/recell/newsfeed/0").then((data)=>{
      console.log("success")
      console.log(data)
      this.tempList=data.data;
      for (let index = 0; index < this.tempList.length; index++) {
        let feeddata={
          recellNo:this.tempList[index].recellNo,
          content:this.tempList[index].recellContent,
          price:this.tempList[index].recellPrice,
          imgurl:this.tempList[index].recellImage,
          date:this.tempList[index].recellDate,
          size:this.tempList[index].recellSize,
          roomname:this.tempList[index].roomname
          }
        this.recellList.push(feeddata);
        }
      })
    },
  beforeDestroy() { 
    this.setIsSelectBar(false)
  }
}
</script>