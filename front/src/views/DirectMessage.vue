<template>
  <div class="wrap">
    <div class="wrap-container-direct">
      <div class="wrap-direct">
        <p class="back-btn" @click="goDM">〈 </p>
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
import { mapState } from 'vuex'
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
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    sendMessage() {
      // axios 요청후
      if (this.text !== '') {
        var divParent = document.querySelector('.message-content');
        divParent.className = 'message-content';

        var div = document.createElement('div');
        div.className = 'user-me'; 

        var p = document.createElement('p');
        p.className = 'user-me-content';
        p.innerText = this.text;

        if(this.$cookies.get('dark') === 'off') {p.classList.add('user-me-content-dark')}

        div.appendChild(p);
        divParent.appendChild(div)

        document.querySelector('.message-content-wrap').scrollTop = document.querySelector('.message-content-wrap').scrollHeight;
        
        this.text = '';}
    },
    getInfiniteChat() {
      setInterval(this.chatListFunction, 3000);
    },
    chatListFunction() {
      console.log('1');
    },
    goDM() {
      this.$router.push('/dm')
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const PTAG = document.querySelectorAll('p')

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
  },
}
</script>
