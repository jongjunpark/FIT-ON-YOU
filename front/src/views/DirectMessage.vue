<template>
  <div class="wrap">
    <div class="wrap-container-direct">
      <div class="wrap-direct">
        <p class="back-btn">〈 </p>
        <div class="in-img">
          <img src="../assets/images/default-user.png" alt="" class="in-img-profile">
        </div>
        <p class="back-btn-name">kidcozyboy</p>
      </div>
      <div class="message-content-wrap">
        <div class="message-content">
          <div class="user-opponent">
            <img src="../assets/images/default-user.png" alt="" class="in-img-content">
            <p class="in-user-content">Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus modi laudantium.</p>
          </div>
          <div class="user-me">
            <p class="user-me-content">
              안녕하세요.
            </p>
          </div>
          <div class="user-opponent">
            <img src="../assets/images/default-user.png" alt="" class="in-img-content">
            <p class="in-user-content">배고파.</p>
          </div>
      </div>
      </div>
      <div class="input-message">
        <input type="textarea" name="" id="" class="input-message-in" placeholder="메세지 보내기..." v-model="text" @keyup.enter="sendMessage">
      </div>
      <button class="butn" @click="sendMessage">↑</button>
    </div>
  </div>
</template>

<script>
import "../components/css/directmessage.css"

export default {
  name: 'DirectMessage',
  data() {
    return {
      text: '',
    }
  },
  mounted() {
    this.getInfiniteChat()
    this.defaultDark()
  },
  methods: {
    sendMessage() {
      // axios 요청후
      var divParent = document.querySelector('.message-content');
      divParent.className = 'message-content';

      var div = document.createElement('div');
      div.className = 'user-me'; 

      var p = document.createElement('p');
      p.className = 'user-me-content';
      p.innerText = this.text;

      div.appendChild(p);
      divParent.appendChild(div)

      document.querySelector('.message-content-wrap').scrollTop = document.querySelector('.message-content-wrap').scrollHeight;
      
      this.text = '';
    },
    getInfiniteChat() {
      setInterval(this.chatListFunction, 3000);
    },
    chatListFunction() {
      console.log('1');
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        this.checked = true
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        this.checked = false
      }
    },
  },
}
</script>
