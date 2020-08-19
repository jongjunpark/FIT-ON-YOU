<template>
  <div class="wrap">
    <div class="wrap-container-direct">
      <div class="wrap-direct">
        <p class="back-btn" @click="goDM">〈 </p>
        <div class="in-img" @click="goProfile">
          <img v-if="!profileImg" src="../assets/images/default-user.png" alt="" class="in-img-profile">
          <img v-if="profileImg" :src="profileImg" alt="" class="in-img-profile">
          <i class="fas fa-crown seller"></i>
        </div>
        <p class="back-btn-name" @click="goProfile">{{ othername }}</p>
      </div>
      <div class="message-content-wrap">
        <div class="message-content">
         <div v-for="message in messages" :key="message.id">
          <div v-show='message.senduser == nick' class="user-me">
            <p class="user-me-content">{{ message.message }}</p>
          </div>
          <div v-show='message.senduser != nick && message.senduser != othername' class="user-opponent">
            <!-- <p class="user-me-content">{{ message.senduser }}</p> -->
            <!-- 이미지 보여주기 -->
            <img :src="message.img" alt="" class="in-img-profile" v-if="message.img" @click="goOther(message.senduser)">
            <img src="../assets/images/default-user.png" alt="" class="in-img-content" v-if="!message.img" @click="goOther(message.senduser)">
            <p class="in-user-content">{{ message.message }}</p>
          </div>
          <div v-show='message.senduser != nick && message.senduser == othername' class="user-opponent">
            <!-- <p class="user-me-content">{{ message.senduser }}</p> -->
            <!-- 이미지 보여주기 -->
            <img :src="message.img" alt="" class="in-img-profile" v-if="message.img" @click="goOther(message.senduser)">
            <img src="../assets/images/default-user.png" alt="" class="in-img-content" v-if="!message.img" @click="goOther(message.senduser)">
            <i class="fas fa-crown seller2"></i>
            <p class="in-user-content">{{ message.message }}</p>
          </div>
         </div>         
      </div>
      </div>
      <div class="input-message">
        <input type="textarea" name="" id="" class="input-message-in" placeholder="메세지 보내기..." v-model="text" @keyup.enter="saveMessage">
        <button class="butn" @click="saveMessage">↑</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState}from 'vuex'
import firebase from 'firebase'
import axios from 'axios'
import "../components/css/resellmessage.css"
import "../components/css/directmessage.css"
// Required for side-effects
require("firebase/firestore");

 // Your web app's Firebase configuration
  var firebaseConfig2 = {
     apiKey: "AIzaSyCOTjsP9K6NHMWKjRs38rRoUL_jjCpftB0",
    authDomain: "vue-resell-openchatting.firebaseapp.com",
    databaseURL: "https://vue-resell-openchatting.firebaseio.com",
    projectId: "vue-resell-openchatting",
    storageBucket: "vue-resell-openchatting.appspot.com",
    messagingSenderId: "798044170825",
    appId: "1:798044170825:web:858223bf10501aa1b7ed98",
    measurementId: "G-N6S13DGQ9W"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig2,"secondary");
  firebase.analytics();

var db = firebase.firestore();

window.db = db;

db.settings({
  
});

export default {
  name: 'ResellMessage',
  data() {
    return {
      text: '',
      messages:[],
      authUser:{},
      roomname:'',
      nick: '',
      othername: '',
      profileImg: '',
      myprofile: '',
    }
  },
  computed: {
    ...mapState(['user' ,'flag'])
  },
  updated() {
    this.goDown()
    this.defaultDark()
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },

  methods: {
    saveMessage(){
      //save to fires'tore
      db.collection(this.roomname).add({
        img: this.myprofile,
        message: this.text,
        createdAt: firebase.firestore.Timestamp.fromDate(new Date()),
        senduser: this.user.nickname,
      })
      console.log(this.user);
      this.text = null;
    },
    fetchMessage(){
      db.collection(this.roomname).orderBy('createdAt').onSnapshot((querySnapshot)=>{
        let allMessages = [];
        querySnapshot.forEach(doc=>{
          allMessages.push(doc.data());
        })

        this.messages=allMessages;
        console.dir(this.messages);
        this.goDown()
        this.defaultDark()
        
      })
    },
    goDM() {
      this.$router.go(-1)
    },
    goProfile() {
      this.$router.push(`/otheruser/${this.othername}`).catch(()=>{})
    },
    goOther(data) {
      this.$router.push(`/otheruser/${data}`).catch(()=>{})
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const PTAG = document.querySelectorAll('.in-user-content, .back-btn-name')

      const MessageDM = document.querySelector('.input-message-in')
      const MessageMe = document.querySelectorAll('.user-me-content')
      
      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }

        MessageDM.classList.add('input-message-in-dark')
        for (let i=0; i<MessageMe.length ; i++) {
          MessageMe[i].classList.add('user-me-content-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }

        MessageDM.classList.remove('input-message-in-dark')
        for (let i=0; i<MessageMe.length ; i++) {
          MessageMe[i].classList.remove('user-me-content-dark')
        }
      }
    },
    goDown() {
      document.querySelector('.message-content-wrap').scrollTop = document.querySelector('.message-content-wrap').scrollHeight;
    },
  },
  created(){
    this.roomname = this.$route.params.roomname
    this.othername = this.$route.params.othername
    console.log(this.roomname)
    console.log(this.othername)
    this.fetchMessage();
  },
  mounted(){
    this.defaultDark()  
    this.goDown()
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.nick = res
    axios.get('https://i3b304.p.ssafy.io/api/mypage/otheruser',{
      params:{
      nickname: this.othername,
    }
    }).then((data)=>{
      if (data.data.userinfo.profile_img) {
        
        this.profileImg = data.data.userinfo.profile_img.substring(2,);
      }
    })
    .catch(
    )
    axios.get('https://i3b304.p.ssafy.io/api/mypage/',{
      params:{nickname:this.nick}
    })
    .then((data)=>{
      console.log(data);
      if(data.data.userinfo.profile_img){
        this.myprofile = data.data.userinfo.profile_img.substring(2, );
      }
    })
    .catch()
  }
}
</script>
