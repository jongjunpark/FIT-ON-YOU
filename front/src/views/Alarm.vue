<template>
  <div class="wrap">
    <div class="wrap-container-alarm">
      <div class="alarm-container-wrap">
        <div class="alarm-container">
          <div v-for="(alarm,index) in alist" :key="index">
            <div :class="isRead[alarm.isRead]">
              <img :src="alarm.user.profile_img" alt="" class="alarm-container-message-img" @click="goToUserPage(alarm.follower)">
              <i :class="alarmIcon[alarm.type-1]"></i>
              <h4 class=""><span @click="goToUserPage(alarm.follower)">{{alarm.follower}}</span>
              {{alarmMsg[alarm.type-1]}}</h4>
              <h5 class="in-text">{{timeCal(alarm.createAt)}}</h5>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../components/css/alarm.css"
import axios from 'axios'
import { mapState } from 'vuex'
import time from '../utils/timecal.js'
export default {
  name: 'Alarm',
  data() {
    //댓글 1 팔로우 2 좋아요 3
    return {
      alarmIcon:['fas fa-comment-dots chat-alarm',
                  'fas fa-user-circle user-alarm',
                  'fas fa-heart heart-alarm'],
      alarmMsg:[
        '님이 회원님의 게시글에 댓글을 남겼습니다.', 
        '님이 회원님을 팔로우하기 시작하였습니다',
        '님이 회원님의 사진에 좋아요 눌렀습니다.'
      ],
      alist :[],
      isRead:[
        'alarm-container-message-read',
        'alarm-container-message'
      ]
      
    }
  },
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  mounted() {
    this.defaultDark()
    let ref=this;

    let tmpNick = this.$cookies.get('auth-nickname');
    let uri = tmpNick;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let resNick = uri_dec;
    
    axios.get('https://i3b304.p.ssafy.io/api/alarm',{
      params:{
        nickname:resNick,
      }
    })
    .then((data)=>{
      ref.alist=data.data.alist
      console.log(ref.alist)
    })
    .catch()
  },

  beforeUpdate(){
    let ref=this;
    if(ref.alist.length>0){
      let flag=false;
      const frm = new FormData();
      for(let i=0;i<ref.alist.length;i++){
        if(ref.alist[i].isRead==0){
          flag=true;
          frm.append("alramNo",ref.alist[i].alramNo);
        }
      }
      if(flag){
        axios.post('https://i3b304.p.ssafy.io/api/alarm',frm)
        .then(
          console.log("성공")
        )
        .catch()
      }
    }
  },
  methods: {
    timeCal(val){
      return time.timeForToday(val);
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const READ = document.querySelectorAll('.alarm-container-message-read')
      const H4TAG = document.querySelectorAll('.alarm-container-message > h4')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.add('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')      
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.remove('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.remove('font-dark')
        }
      }
    },
    goToUserPage(nickname){
      this.$router.push(`/otheruser/${nickname}`)
    },
    
  },
}
</script>
