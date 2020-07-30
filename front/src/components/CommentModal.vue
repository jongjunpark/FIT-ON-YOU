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
          <div class="comment-box">
            <div class="comment-user-icon"></div>
            <div class="comment-article">
              <div class="comment-article-head">
                <div class="comment-username comment-text">Username</div>
                <div class="comment-update-time comment-text">10시간 전</div>
              </div>
              <div class="comment-content comment-text">안녕하세요 옷 정말 잘입네요.....</div>
            </div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
          <div class="comment-box">
            <div class="comment-user-icon"></div>
          </div>
        </div>
        <div class="modal-footer">
          <div class="comment-my-icon"></div>
          <input @input="comment_content = $event.target.value" class='comment-input' type="text" placeholder="댓글을 입력해 주세요.">
          <i class="fas fa-check-circle"></i>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  ame: 'CommentModal',
  data() {
    return {
      comment_content: ''
    }
  },
  watch: {
    comment_content() {
      this.checkCommentInput();
    }
  },
  methods: {
    checkCommentInput() {
      const INPUTBTN = document.querySelector('.fa-check-circle')
      if (this.comment_content) {
        INPUTBTN.classList.add('on-comment-input')
      } else {
        INPUTBTN.classList.remove('on-comment-input')
      }
    }
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

.modal-enter .modal-container,
.modal-leave-active .modal-container {
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
</style>