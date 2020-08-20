<template>
<transition name="modal">
    <div class="comment-modal-mask" @click.self="$emit('close')">
      <div class="comment-modal-wrapper" @click.self="$emit('close')">
        <div class="comment-modal-head">
          <div class='comment-modal-back-btn' @click="$emit('close')">
            <i class="fas fa-arrow-left"></i>
          </div>
          <div class='comment-modal-category'>댓글</div>
        </div>
        <div class="comment-modal-container">
          <div class="" v-for="(comment,index) in commentList" :key="index">
            <div class="comment-box">
              <div @click="goToUserPage(comment.writer)">
                <img v-show="comment.user.profile_img" class="comment-user-icon" :src="comment.user.profile_img">
                <img v-show="!comment.user.profile_img" class="comment-user-icon" src="../assets/images/default-user.png">
              </div>
              <div class="comment-article">
                <div class="comment-article-head">
                  <div class="comment-username comment-text"><span  @click="goToUserPage(comment.writer)">{{comment.writer}}</span></div>
                  <div class="comment-update-time comment-text">{{timeCal(comment.createAt)}}</div>
                </div>
                <div class="comment-content comment-text">{{comment.content}}</div>
              </div>
              <i v-show="comment.writer==nickname" class="fas fa-times comment-del-icon" @click="deleteComment(comment.commentNo,index)"></i>
            </div>
          </div>
        </div>
        <div class="comment-modal-footer">
          <div class="comment-my-icon">
            <img v-show="myProfileImg" class="comment-user-icon" :src="myProfileImg">
            <img v-show="!myProfileImg" class="comment-user-icon" src="../assets/images/default-user.png">
          </div>
          <input @input="commentContent = $event.target.value" @keydown.enter="addComment" class='comment-input' type="text" placeholder="댓글을 입력해 주세요." v-model="commentContent">
          <i class="fas fa-check-circle" @click="addComment"></i>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import Swal from 'sweetalert2'
import time from '../utils/timecal.js'

export default {
  name: 'CommentModal',
  props:['modalArticleNo','modalArticleUser'],
  computed: {
    ...mapState(['user'])
  },
  data() {
    return {
      commentContent: '',
      commentList: [],
      profileList:[],
      nickname:'',
      myProfileImg:'',
    }
  },
  mounted(){
    let ref=this;

    let data = this.$cookies.get('auth-nickname');
    let uri = data;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    this.nickname = uri_dec;
    axios.get('https://i3b304.p.ssafy.io/api/comment',{
      params:{
        articleNo: this.modalArticleNo,
        nickname:this.nickname,
      }
    })
    .then((res)=>{
      ref.commentList=res.data.commentli;
      if(res.data.myprofile) ref.myProfileImg=res.data.myprofile;      
    })
    .catch()

  },
  watch: {
    commentContent() {
      this.checkCommentInput();
    },
   
  },
  methods: {
    timeCal(val){
      return time.timeForToday(val);
    },
   
    checkCommentInput() {
      const INPUTBTN = document.querySelector('.fa-check-circle')
      if (this.commentContent) {
        INPUTBTN.classList.add('on-comment-input')
      } else {
        INPUTBTN.classList.remove('on-comment-input')
      }
    },
    addComment(){
      if(this.commentContent) {
        let ref=this;
  
        let data = this.$cookies.get('auth-nickname');
        let uri = data;
        let uri_enc = encodeURIComponent(uri);
        let uri_dec = decodeURIComponent(uri_enc);
        let res = uri_dec;
  
        const frm = new FormData();
        frm.append("articleNo", this.modalArticleNo);
        frm.append("writer", res);
        frm.append("content", this.commentContent);
        frm.append("articleUser",this.modalArticleUser);
        let today=new Date();
  
        axios.post('https://i3b304.p.ssafy.io/api/comment',frm
        )
        .then((data)=>{
          let tmp={
            commentNo:data.data.rescmt.commentNo,
            writer:data.data.rescmt.writer,
            articleNo:data.data.rescmt.articleNo,
            content:data.data.rescmt.content,
            user:{
              profile_img:ref.myProfileImg
            },
            createAt:today,
          }
          ref.commentList.push(tmp);
          ref.commentContent='';
  
        })
        .catch()
      }
    },

    deleteComment(commentNo,index){
      Swal.fire({
        title: '삭제하시겠습니까?',
        text: "삭제된 댓글은 복구할 수 없습니다.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제하기',
        cancelButtonText: '아니오',
      }).then((result) => {
        if (result.value) {
          let frm=new FormData();
          frm.append('commentNo',commentNo);
          axios.post('https://i3b304.p.ssafy.io/api/comment/del',frm)
          .then(()=>{
            this.commentList.splice(index,1);
            this.profileList.splice(index,1);

          })
          .catch()
          Swal.fire(
            '삭제되었습니다',
          )
        }
      })

    },
    goToUserPage(nickname){
      this.$router.push(`/otheruser/${nickname}`).catch(()=>{})
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

.comment-modal-mask {
  position: fixed;
  z-index: 10000;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.comment-modal-wrapper {
  padding: 0 30px;
  height: 100%;
  transition: all .3s ease;
  display: flex;
  flex-direction: column;
  justify-content: center;
  max-width: 100vw;
  width: 100%;
}
@media (min-width: 1200px) {
  .comment-modal-wrapper {
  max-width: 400px;
  width: 100%;
  margin: 0 auto;
  }
}

.comment-modal-head {
  width: 100%;
  height: 6vh;
  border-top-left-radius: 1vh;
  border-top-right-radius: 1vh;
  background-color: white;
  border-bottom: 0.1vh solid rgb(175, 175, 175);
  display: flex;
  align-items: center;
  transition: all .3s ease;
}

.comment-modal-footer {
  height: 8vh;
  padding: 0px 2vh;
  border-bottom-left-radius: 1vh;
  border-bottom-right-radius: 1vh;
  border-top: 0.1vh solid rgb(175, 175, 175);
  background-color: white;
  display: flex;
  align-items: center;
  transition: all .3s ease;
}


.comment-modal-back-btn {
  margin: 0 1.4vh;
  width: 3vh;
  height: 3vh;
  cursor: pointer;
}

.comment-modal-back-btn .fas {
  font-size: 3vh !important;
  padding-left: 0.5vh;
  color: black;
}

.comment-modal-category {
  font-weight: 700;
  font-size: 2.3vh;
  color: black !important;
}


.comment-modal-container {
  max-height: 50%;
  overflow-y: auto;
  padding: 0px 15px;
  background-color: #fff;
  transition: all .3s ease;
}

.comment-modal-container::-webkit-scrollbar { width: 10px; height: 0;}
/* 스크롤바의 width */
::-webkit-scrollbar-track { background-color: transparent; }
/* 스크롤바의 전체 배경색 */
::-webkit-scrollbar-thumb { background: silver;}
/* 스크롤바 색 */
::-webkit-scrollbar-button { display: none; }
/* 스크롤바 버튼 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .comment-modal-wrapper,
.modal-leave-active .comment-modal-wrapper {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.comment-user-icon {
  width: 5.5vh;
  height: 5.5vh;
  border-radius: 50%;
  border: 1px solid rgb(199, 199, 199);
  cursor: pointer;
}

.comment-box {
  display: flex;
  /* background-color: grey; */
  margin: 2vh 0;
  position: relative;
}

.comment-my-icon {
  margin-right: 2vh;
}

.comment-article {
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-left: 1.4vh;
}

.comment-del-icon {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 2vh;
}

.comment-article-head {
  display: flex;
  margin-bottom: 1vh;
}

.comment-content {
  height: 50%;
  width: 80%;
  word-wrap:break-word;
}

.comment-input {
  height: 4vh;
  width: 100%;
  border: none;
  margin-right: 3vh;
  font-size: 2vh;
}

.comment-modal-footer .fa-check-circle {
  font-size: 3.5vh !important;
}

.on-comment-input {
  color: #5AAEFF;
}

.comment-box .comment-text {
  font-size: 2vh;
  line-height: 2vh;
  color: black !important;
}

.comment-username {
  font-weight: 700;
  margin-right: 1.4vh;
  cursor: pointer;
}

.comment-update-time {
  color: rgb(99, 99, 99);
  font-size: 1.3vh !important;
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