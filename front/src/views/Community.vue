<template>
  <div class='wrap'>
    <div class='community-toggle-box'>
      <span>내글보기</span>
      <input @change="onToggle" type="checkbox" id="community-toggle" name="" value=""/> 
        <div class='community-toggle-inner'>
          <label for="community-toggle"><span/></label> 
        </div>
    </div>
    <div v-show="isMyCommu" class='wrap-container community-container'>
      <div class="community-inner-box" v-for="(myarticle,index) in myList" :key="`recell-${index}`">
        <img :src="myarticle.recellImage" alt="">
        <div class="community-content">
          <p class='community-content-head'>{{ myarticle.recellContent }}</p>
          <p class='community-content-body'>·판매자: {{ myarticle.recellUser }}</p>
          <p class='community-content-body'>·가격: {{ myarticle.recellPrice }}원</p>
          <p class='community-content-body'>·사이즈: {{ myarticle.recellSize }}</p>
          <div class="community-content-footer">
            <div @click="goDM(myarticle.roomname, myarticle.recellUser)" class="community-content-btn dm-btn">DM</div>
            <div v-show="!myarticle.salecheck" @click="soldItem(myarticle.recellNo)" class="community-content-btn del-btn">판매완료</div>
            <div v-show="myarticle.salecheck" class="community-content-btn non-del-btn">완료됨</div>
          </div>
        </div>
      </div>
    </div>
    <div v-show="!isMyCommu" class='wrap-container community-container'>
      <div class="community-inner-box" v-for="(article,index) in recellList" :key="`recell-${index}`">
        <img :src="article.imgurl" alt="">
        <div class="community-content">
          <p class='community-content-head'>{{ article.content }}</p>
          <p class='community-content-body'>·판매자: {{ article.user }}</p>
          <p class='community-content-body'>·가격: {{ article.price }}원</p>
          <p class='community-content-body'>·사이즈: {{ article.size }}</p>
          <div class="community-content-footer">
            <div @click="goDM(article.roomname, article.user)" class="community-content-btn dm-btn other-btn">DM보내기</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import "../components/css/community.css"
import axios from 'axios'
import Swal from 'sweetalert2'
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
      myList:[],
      nickName:[],
      limit:'1',
      isMyCommu: false,
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
    onToggle() {
      if(event.target.checked) {
        this.isMyCommu = true
      } else {
        this.isMyCommu = false
      }
    },
    goDM(room, name) {
      this.$router.push(`/resellmessage/${room}/${name}`)
    },
    infiniteHandler($state){
      let ref=this;
      axios.post('https://i3b304.p.ssafy.io/api/recell/all/'+ref.limit)
      .then((data)=>{
        setTimeout(() => {
          if(data.data.object.length){
            ref.tempList=data.data.object;
            for (let index = 0; index < ref.tempList.length; index++) {
              let feeddata={
              recellNo:ref.tempList[index].recellNo,
              content:ref.tempList[index].recellContent,
              price:ref.tempList[index].recellPrice,
              imgurl:ref.tempList[index].recellImage,
              date:ref.tempList[index].recellDate,
              size:ref.tempList[index].recellSize,
              roomname:ref.tempList[index].roomname,
              user:ref.tempList[index].recellUser
              }
            this.recellList.push(feeddata);
            }
            ref.tempList=[];

            $state.loaded();
            ref.limit+=1;
          }
          else{
            $state.complete();
          }
        }, 1000);
      })
      .catch()
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
    getNickName() {
      let nickdata = this.$cookies.get('auth-nickname')
      let uri = nickdata;
      let uri_enc = encodeURIComponent(uri);
      this.nickName = decodeURIComponent(uri_enc);
    },
    getAllList() {
      axios.post("https://i3b304.p.ssafy.io/api/recell/newsfeed/0").then((data)=>{
      console.log("success")
      console.log(data)
      this.tempList=data.data;
      for (let index = 0; index < this.tempList.length; index++) {
        if (this.tempList[index].recellUser !== this.nickName) {
          let feeddata={
            recellNo:this.tempList[index].recellNo,
            content:this.tempList[index].recellContent,
            price:this.tempList[index].recellPrice,
            imgurl:this.tempList[index].recellImage,
            date:this.tempList[index].recellDate,
            size:this.tempList[index].recellSize,
            roomname:this.tempList[index].roomname,
            user:this.tempList[index].recellUser
            }
          this.recellList.push(feeddata);
        }
      }
      this.tempList=[];
      })
    },
    getMyList() {
      axios.get("https://i3b304.p.ssafy.io/api/recell/myContents",{
        params: {
          username: this.nickName
        },
      }).then((data) => {
        this.myList = data.data.object
        console.log(this.myList, '내글')
      }).catch()
    },
    soldItem(roomNo) {
      Swal.fire({
        title: '완료하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '완료하기',
        cancelButtonText: '아니오',
      }).then((result) => {
        if (result.value) {
          const frm = new FormData();
          frm.append("num",roomNo);
          axios.post('https://i3b304.p.ssafy.io/api/recell/soldout', frm)
          .then(console.log("팔았다"))
          .catch()
          Swal.fire(
            '완료되었습니다.',
          ).then((result2) => {
            if (result2.value) {
              this.$router.go(this.$router.currentRoute)
            }
          })
        }
      }
    )}
  },
  mounted() {
    this.setIsSelectBar(true)
    this.goCommunity()
    this.defaultDark()
    this.getNickName()
    this.getMyList()
    this.getAllList()
    },
  beforeDestroy() { 
    this.setIsSelectBar(false)
  }
}
</script>