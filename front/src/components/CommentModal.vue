<template>
<transition name="modal">
    <div class="modal-mask" @click.self="$emit('close')">
      <div class="modal-wrapper" @click.self="$emit('close')">
        <div class="modal-head">
          <div class='modal-back-btn' @click="$emit('close')">
            <i class="fas fa-arrow-left"></i>
          </div>
          <div class='modal-category'>댓글</div>
        </div>
        <div class="modal-container">
          <div class="" v-for="(comment,index) in commentList" :key="index">
            <div class="comment-box">
              <div class="comment-user-icon">
                <img class="comment-user-icon" :src="comment.user.profile_img">
              </div>
              <div class="comment-article">
                <div class="comment-article-head">
                  <div class="comment-username comment-text">{{comment.writer}}</div>
                  <div class="comment-update-time comment-text">10시간전</div>
                </div>
                <div class="comment-content comment-text">{{comment.content}}</div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <div class="comment-my-icon"></div>
          <input @input="comment_content = $event.target.value" class='comment-input' type="text" placeholder="댓글을 입력해 주세요.">
          <i class="fas fa-check-circle" @click="addComment"></i>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'CommentModal',
  props:['modalArticleNo','modalArticleUser'],
  computed: {
    ...mapState(['flag','user'])
  },
  data() {
    return {
      comment_content: '',
      commentList: [],
      profileList:[],
    }
  },
  mounted(){
    this.defaultDark()
    let ref=this;
    axios.get('http://localhost:8080/api/comment',{
      params:{
        articleNo:this.modalArticleNo,
      }
    })
    .then((res)=>{
      console.log(res,2)
      ref.commentList=res.data.commentli;

    })
    .catch()

  },
  computed: {
    ...mapState(['flag'])
  },
  watch: {
    comment_content() {
      this.checkCommentInput();
    },
    flag() {
      this.defaultDark()
    }
  },
  methods: {
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const NAV = document.querySelector('#nav')
      const NAVBASE = document.querySelector('.nav-base')
      const NAVLOGO = document.querySelector('.fa-hat-cowboy')
      const INPUT = document.querySelectorAll('input')
      const TEXTAREA = document.querySelectorAll('textarea')
      const COMMENT_HEAD = document.querySelector('.modal-head')
      const ARROW_ICON = document.querySelector('.fa-arrow-left')
      const COMMENT_BODY = document.querySelector('.modal-container')
      const COMMENT_FOOTER = document.querySelector('.modal-footer')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        NAV.classList.add('nav-dark')
        NAVBASE.classList.add('nav-dark')
        NAVLOGO.classList.add('nav-logo-dark')
        COMMENT_HEAD.classList.add('comment-head-dark')
        ARROW_ICON.classList.add('comment-back-dark')
        COMMENT_BODY.classList.add('comment-head-dark')
        COMMENT_FOOTER.classList.add('comment-head-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('comment-input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.add('textarea-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        NAV.classList.remove('nav-dark')
        NAVBASE.classList.remove('nav-dark')
        NAVLOGO.classList.remove('nav-logo-dark')
        COMMENT_HEAD.classList.remove('comment-head-dark')
        ARROW_ICON.classList.remove('comment-back-dark')
        COMMENT_BODY.classList.remove('comment-head-dark')
        COMMENT_FOOTER.classList.remove('comment-head-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('comment-input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.remove('textarea-dark')
        }
        
      }
    },
    checkCommentInput() {
      const INPUTBTN = document.querySelector('.fa-check-circle')
      if (this.comment_content) {
        INPUTBTN.classList.add('on-comment-input')
      } else {
        INPUTBTN.classList.remove('on-comment-input')
      }
    },
    addComment(){
      let ref=this;

      let data = this.$cookies.get('auth-nickname');
      let uri = data;
      let uri_enc = encodeURIComponent(uri);
      let uri_dec = decodeURIComponent(uri_enc);
      let res = uri_dec;

      const frm = new FormData();
      frm.append("articleNo", this.modalArticleNo);
      frm.append("writer", res);
      frm.append("content", this.comment_content);
      frm.append("articleUser",this.modalArticleUser);
      console.log(this.modalArticleNo);

      axios.post('http://localhost:8080/api/comment',frm
      )
      .then((data)=>{
        let tmp={
          commentNo:data.data.rescmt.commentNo,
          writer:data.data.rescmt.writer,
          articleNo:data.data.rescmt.articleNo,
          content:data.data.rescmt.content,
          user:{
            profile_img:ref.user.profile_img,
          }
        }
        ref.commentList.push(tmp);
        ref.comment_content='';

      })
      .catch(()=>{
        console.log("fail");
      })
    },
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const INPUT = document.querySelectorAll('input')
      const COMMENT_HEAD = document.querySelector('.modal-head')
      const ARROW_ICON = document.querySelector('.fa-arrow-left')
      const COMMENT_BODY = document.querySelector('.modal-container')
      const COMMENT_FOOTER = document.querySelector('.modal-footer')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        COMMENT_HEAD.classList.add('comment-head-dark')
        ARROW_ICON.classList.add('comment-back-dark')
        COMMENT_BODY.classList.add('comment-head-dark')
        COMMENT_FOOTER.classList.add('comment-head-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('comment-input-dark')
        }
      } else {
        HTML.classList.remove('black')
        COMMENT_HEAD.classList.remove('comment-head-dark')
        ARROW_ICON.classList.remove('comment-back-dark')
        COMMENT_BODY.classList.remove('comment-head-dark')
        COMMENT_FOOTER.classList.remove('comment-head-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('comment-input-dark')
        }
      }
    },
  }
}
</script>

<style scoped>
.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;

}

.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;

}

.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  padding: 0 30px;
  height: 100%;
  margin-top: 30%;
  transition: all .3s ease;
}
@media (min-width: 1200px) {
  .modal-wrapper {
  max-width: 400px;
  width: 100%;
  margin: 0 auto;
  margin-top: 150px;
  }
}

.modal-head {
  width: 100%;
  height: 5%;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  background-color: white;
  border-bottom: 0.5px solid rgb(175, 175, 175);
  display: flex;
  align-items: center;
  transition: all .3s ease;
}

.modal-footer {
  height: 7%;
  padding: 0px 15px;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border-top: 0.5px solid rgb(175, 175, 175);
  background-color: white;
  display: flex;
  align-items: center;
  transition: all .3s ease;
}


.modal-back-btn {
  margin: 0 10px;
  width: 25px;
  height: 25px;
  cursor: pointer;
}

.modal-back-btn .fas {
  font-size: 150% !important;
  padding-left: 5%;
  color: black;
}

.modal-category {
  font-weight: 700;
}


.modal-container {
  max-height: 50%;
  overflow-y: auto;
  margin: 0px auto;
  padding: 0px 15px;
  background-color: #fff;
  transition: all .3s ease;
}

.modal-container::-webkit-scrollbar { width: 10px; }
/* 스크롤바의 width */
::-webkit-scrollbar-track { background-color: transparent; }
/* 스크롤바의 전체 배경색 */
::-webkit-scrollbar-thumb { background: silver;}
/* 스크롤바 색 */
::-webkit-scrollbar-button { display: none; }
/* 스크롤바 버튼 */

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-wrapper,
.modal-leave-active .modal-wrapper {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.comment-user-icon {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: rgb(199, 199, 199);
}

.comment-box {
  display: flex;
  /* background-color: grey; */
  margin: 10px 0;
  height: 40px;
}

.comment-my-icon {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: rgb(199, 199, 199);
  margin-right: 20px;
}

.comment-article {
  width: 80%;
  height: 40px;
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}

.comment-article-head {
  display: flex;
  height: 50%;
}

.comment-content {
  height: 50%;
}

.comment-input {
  height: 30px;
  width: 70%;
  border: none;
  margin-right: 20px;
}

.modal-footer .fa-check-circle {
  font-size: 150% !important;
}

.on-comment-input {
  color: #5AAEFF;
}

.comment-box .comment-text {
  font-size: 90%;
  line-height: 1;
}

.comment-username {
  font-weight: 700;
  margin-right: 10px;
}

.comment-update-time {
  color: rgb(99, 99, 99);
  font-size: 70% !important;
}

.comment-head-dark {
  background-color: #202020;
}

.comment-back-dark {
  color: #ebebeb !important;
}

.comment-input-dark {
  color: #ebebeb;
  border-color: #ebebeb !important;
}
</style>