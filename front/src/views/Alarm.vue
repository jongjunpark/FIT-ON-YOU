<template>
  <div class="wrap">
    <div class="wrap-container-alarm">
      <div class="alarm-container-wrap">
        <div class="alarm-container">
          <div v-show="alist!=null && alist.length>0">
            <div v-for="(alarm,index) in alist" :key="index">
              <div :class="isRead[alarm.isRead]">
                <div class="alarm-icon-box">
                  <img v-show="alarm.user.profile_img" :src="alarm.user.profile_img" alt="" class="alarm-container-message-img" @click="goToUserPage(alarm.follower)">
                  <img v-show="!alarm.user.profile_img" src="../assets/images/default-user.png" alt="" class="alarm-container-message-img" @click="goToUserPage(alarm.follower)">
                  <i :class="alarmIcon[alarm.type-1]"></i>
                </div>
                <div class="alarmclass">
                  <span @click="goToUserPage(alarm.follower)">{{alarm.follower}}</span>
                  {{alarmMsg[alarm.type-1][0]}}
                  <span @click="onModal(alarm.articleNo)">{{alarmMsg[alarm.type-1][1]}}</span>
                  {{alarmMsg[alarm.type-1][2]}}
                  <span @click="goCmtModal(alarm.articleNo)">{{alarmMsg[alarm.type-1][3]}}</span>
                  {{alarmMsg[alarm.type-1][4]}}
                </div>
                <div class="alrarm-time-box">
                  <h5 class="in-text">{{timeCal(alarm.createAt)}}</h5>
                </div>
              </div>
            </div>
          </div>
          <div v-show="!(alist!=null && alist.length>0)" id="DARKDARK">
            알림 내역이 없습니다.. :)
          </div> 

        </div>
      </div>
    </div>
    <SearchModal  v-if="showModal" @close="showModal= false"/>
    <CommentModal v-if="showCmtModal" @close="showCmtModal= false" :modalArticleNo="modalArticleNo" :modalArticleUser="mynick"/>
  </div>
</template>

<script>
import "../components/css/alarm.css"
import axios from 'axios'
import { mapState,mapMutations } from 'vuex'
import time from '../utils/timecal.js'
import SearchModal from '../components/SearchModal.vue'
import CommentModal from '../components/CommentModal.vue'
export default {
  name: 'Alarm',
  components:{
    SearchModal,
    CommentModal,
  },
  data() {
    //댓글 1 팔로우 2 좋아요 3
    return {
      alarmIcon:['fas fa-comment-dots chat-alarm',
                  'fas fa-user-circle user-alarm',
                  'fas fa-heart heart-alarm'],
      alarmMsg:[
        ['님이 회원님의 ','게시글','에 ','댓글','을 남겼습니다.'],
        ['님이 회원님을 ','팔로우','하기','','시작하였습니다'],
        ['님이 회원님의 ','사진','에 좋아요를','','눌렀습니다.'],
      ],
      alist :[],
      isRead:[
        'alarm-container-message-read',
        'alarm-container-message'
      ],
      showModal:false,
      showCmtModal:false,
      modalArticleNo:'',
      modalArticleUser:'',
      mynick:'',
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
  updated() {
    this.defaultDark()
  },
  mounted() {
    this.defaultDark()
    let ref=this;

    let tmpNick = this.$cookies.get('auth-nickname');
    let uri = tmpNick;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let resNick = uri_dec;
    this.mynick=resNick;
    
    axios.get('https://i3b304.p.ssafy.io/api/alarm',{
      params:{
        nickname:resNick,
      }
    })
    .then((data)=>{
      ref.alist=data.data.alist
    })
    .catch()
  },

  beforeUpdate(){
    let ref=this;
    if(ref.alist!=null && ref.alist.length>0){
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

        )
        .catch()
      }
    }
  },
  methods: {
    ...mapMutations(['setArticledata']),
    timeCal(val){
      return time.timeForToday(val);
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const READ = document.querySelectorAll('.alarm-container-message-read')
      const H4TAG = document.querySelectorAll('.alarm-container-message > h4')
      const H5TAG = document.querySelectorAll('.alarm-container-message > h5')
      const ALARM_TEXT = document.querySelectorAll('.alarmclass')
      const ALARM_TIME = document.querySelectorAll('h5')
      const DARKDARK = document.querySelector('#DARKDARK')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<ALARM_TEXT.length ; i++) {
          ALARM_TEXT[i].classList.add('font-dark')
        }
        for (let i=0; i<ALARM_TIME.length ; i++) {
          ALARM_TIME[i].classList.add('font-dark')
        }
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.add('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H5TAG[i].classList.add('font-dark')
        }
        if (DARKDARK) {
          DARKDARK.classList.add('alarmtext-dark')
          
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<ALARM_TEXT.length ; i++) {
          ALARM_TEXT[i].classList.remove('font-dark')
        }
        for (let i=0; i<ALARM_TIME.length ; i++) {
          ALARM_TIME[i].classList.remove('font-dark')
        }      
        for (let i=0; i<READ.length ; i++) {
          READ[i].classList.remove('alarm-container-message-read-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H4TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<H4TAG.length ; i++) {
          H5TAG[i].classList.remove('font-dark')
        }
        if (DARKDARK) {
          DARKDARK.classList.remove('alarmtext-dark')
          
        }
      }
    },
    goToUserPage(nickname){
      this.$router.push(`/otheruser/${nickname}`).catch(()=>{})
    },
    onModal(articleNo) {
      if(articleNo==-1 || articleNo==null) return
      else{
        this.setArticledata(articleNo);
        this.showModal = true
      }
    },
    goCmtModal(articleNo){
      if(articleNo==-1 || articleNo==null) return
      else{
        this.modalArticleNo=articleNo;
        this.modalArticleUser=this.mynick
        this.showCmtModal=true;
      }
    }
  },
}
</script>
