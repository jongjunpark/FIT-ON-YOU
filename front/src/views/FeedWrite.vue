<template>
  <div class="wrap">
    <div class='wrap-container'>
      <div class="write-img-box">
        <img class="write-hanger-img" src="../assets/images/hanger.png" alt="">
        <label v-if="!feedImg[0]" class="write-upload-btn" for='feed-img-edit1'>
          <i class="far fa-images"><i class="fas fa-plus"></i></i>
          <input multiple="multiple" type="file" id="feed-img-edit1" accept="image/*" @change="setFeedImg(1)">
        </label>
        <div class="write-cloth-box">
          <div class="write-cloth-hanger">
            <img class="write-cloth-hanger-img" src="../assets/images/cloth-hanger.png" alt="">
            <div @mouseover="onCancelBtn(1)" @mouseout="offCancleBtn" class="write-uploaded-img first-feed-img">
              <i @click='delFeedImg(1)' v-show='feedImg[0]&&isCancle1' class="far fa-times-circle cancle-img"></i>
              <img v-if="feedImg[0]" class='feed-img' :src="feedImg[0]" alt="">
            </div>
          </div>
          <div class="write-cloth-hanger">
            <img class="write-cloth-hanger-img second-feed-img" src="../assets/images/cloth-hanger.png" alt="">
            <div @mouseover="onCancelBtn(2)" @mouseout="offCancleBtn" class="write-uploaded-img second-feed-img">
              <i @click='delFeedImg(2)' v-show='feedImg[1]&&isCancle2' class="far fa-times-circle cancle-img"></i>
              <img v-if="feedImg[1]" class='feed-img' :src="feedImg[1]" alt="">
              <label for="feed-img-edit2" class='feed-more-label'>
                <i v-if="feedImg[0] && !feedImg[1]" class="fas fa-plus"></i>
                <input multiple="multiple" type="file" id="feed-img-edit2" accept="image/*" @change="setFeedImg(2)">
              </label>
            </div>
          </div>
          <div class="write-cloth-hanger">
            <img class="write-cloth-hanger-img third-feed-img" src="../assets/images/cloth-hanger.png" alt="">
            <div @mouseover="onCancelBtn(3)" @mouseout="offCancleBtn" class="write-uploaded-img third-feed-img">
              <i @click='delFeedImg(3)' v-show='feedImg[2]&&isCancle3' class="far fa-times-circle cancle-img"></i>
              <img v-if="feedImg[2]" class='feed-img' :src="feedImg[2]" alt="">
              <label for="feed-img-edit3" class='feed-more-label'>
                <i v-if="feedImg[0] && !feedImg[2]" class="fas fa-plus"></i>
                <input multiple="multiple" type="file" id="feed-img-edit3" accept="image/*" @change="setFeedImg(3)">
              </label>
            </div>
          </div>
        </div>
      </div>
      <div class="write-content-box">
        <p class='write-content-head'>내용</p>
        <textarea @input="writeContent = $event.target.value" cols="30" rows="3" placeholder="내용"></textarea>
        <p class='write-hash-head'>태그</p>
        <input @input="writeHashContent = $event.target.value" v-model='writeHashContent' @keyup.188="addWriteHash" type="text" placeholder="태그">
        <transition-group name='fade' tag="div" class="write-hash-group" mode="in-out">
          <div class='write-hash-item' v-for='(hash, index) in writeHashList' :key='`hash-${index}`'>
            <div @click='delWriteHashItem(index)' class="write-hash-item-close-btn"><i class="fas fa-times"></i></div>
            {{ hash }}
          </div>
        </transition-group>
      </div>
      <div v-if="!isWriteBtn" class="btn write-btn">작성하기</div>
      <div v-if="isWriteBtn" class="btn write-btn on-write-btn">작성하기</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FeedWrite',
  data() {
    return {
      feedImg : [],
      isCancle1: false,
      isCancle2: false,
      isCancle3: false,
      writeHashContent: '',
      writeHashList: [],
      writeContent: '',
      isWriteBtn: false,
    }
  },
  watch: {
    feedImg() {
      this.checkWriteForm();
    },
    writeContent() {
      this.checkWriteForm();
    },
    writeHashContent() {
      this.checkWriteForm();
    },
    writeHashList() {
      this.checkWriteForm();
    },
  },
  methods: {
    setFeedImg(num) {
      // var frm = new FormData();
      var photoFile = document.getElementById(`feed-img-edit${num}`);
      if (num === 1) {
        if (photoFile.files.length > 3) {
          alert("이미지는 최대 3개까지 업로드 가능합니다.");
        }
      } else if (num === 2) {
        if (photoFile.files.length > 2) {
          alert("이미지는 최대 3개까지 업로드 가능합니다.");
        }
      } else if (num === 3) {
        if (photoFile.files.length > 1) {
          alert("이미지는 최대 3개까지 업로드 가능합니다.");
        }
      }
      for(var i=0; i<photoFile.files.length; i++) {
        if (num === 1 && i >= 3) {
          break;
        } else if (num === 2 && i >= 2) {
          break;
        } else if (num === 3 && i >= 1) {
          break;
        } else {
          this.feedImg.push(URL.createObjectURL(photoFile.files[i]))
          // frm.append("feed-img-edit", photoFile.files[i]);
        }
      }
      // console.log(this.feedImg)
    },
    onCancelBtn(num) {
      if (num === 1){
        this.isCancle1 = true
      } else if (num === 2) {
        this.isCancle2 = true
      } else if (num === 3) {
        this.isCancle3 = true
      }
    },
    offCancleBtn() {
      this.isCancle1 = false
      this.isCancle2 = false
      this.isCancle3 = false
    },
    delFeedImg(num) {
      let idx = num-1
      this.feedImg.splice(idx, 1)
    },
    addWriteHash() {
      this.writeHashList.push(this.writeHashContent.slice(0,-1))
      this.writeHashContent = ''
    },
    delWriteHashItem(index) {
      this.writeHashList.splice(index, 1)
    },
    checkWriteForm() {
      if (this.feedImg[0] && (this.writeHashContent || this.writeHashList[0]) && this.writeContent) {
        this.isWriteBtn = true
      } else {
        this.isWriteBtn = false
      }
    }
  }
}
</script>

<style scoped>
.write-img-box {
  position: relative;
  width: 100%;
  padding-top: 58%;
  margin-top: 1vh;
}

.write-img-box .write-hanger-img {
  position: absolute;
  top: 0; bottom: 0; left: 0; right: 0;
  width: 100%;
  height: 100%;
}

.write-img-box .write-upload-btn {
  position: absolute;
  top: 50%; 
  left: 50%;
  transform: translate(-50%, -50%);
  width: 20vw;
  height: 10vw;
  background-color: #fff;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  border-radius: 20px;
  text-align: center;
  line-height: 10vw;
  cursor: pointer;
  z-index: 20;
}
@media (min-width: 1200px) {
  .write-img-box .write-upload-btn {
  width: 100px;
  height: 40px;
  line-height: 2.8;
  }
}

.write-upload-btn input[type="file"] {
  position: absolute;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
}

.feed-more-label input[type="file"] {
  position: absolute;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
}

.write-img-box .write-upload-btn:hover {
  background-color: black;
}

.write-img-box .write-upload-btn:hover .fa-images{
  color: white;
}
.write-img-box .write-upload-btn:hover .fa-plus{
  color: white;
}

.write-upload-btn .fa-images {
  margin: 0 0 0 5px;
  font-size: 5vw;
}
@media (min-width: 1200px) {
  .write-upload-btn .fa-images {
  font-size: 150%;
  }
}

.write-upload-btn .fa-images .fa-plus {
  margin: 0;
  font-size: 3vw;
}
@media (min-width: 1200px) {
  .write-upload-btn .fa-images .fa-plus {
  font-size: 50%;
  }
}

.write-img-box .write-cloth-box {
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 0; bottom: 0; left: 0; right: 0;
  width: 100%;
  height: 100%;
  padding: 2% 8%;
}

.write-img-box .write-cloth-hanger {
  width: 30%;
  height: 0;
  padding-top: 15%;
  position: relative;
}

.write-cloth-hanger .write-cloth-hanger-img {
  position: absolute;
  top: 0; bottom: 0; left: 0; right: 0;
  width: 100%;
  height: 100%;
}

.write-cloth-hanger .write-uploaded-img {
  position: absolute;
  top: 90%;
  width: 100%;
  height: 250%;
}

.write-cloth-hanger .write-uploaded-img .feed-img {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
}

.write-cloth-hanger .write-uploaded-img .cancle-img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 0;
  font-size: 200%;
  color: black
}

.write-cloth-hanger .write-uploaded-img .fa-plus {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 0;
  font-size: 8vw;
  border-radius: 50%;
  box-shadow: 0 6px 12px 0 rgba(0,0,0,0.25);
  padding: 10%;
}
@media (min-width: 1200px) {
  .write-cloth-hanger .write-uploaded-img .fa-plus {
  font-size: 200%;
  }
}

.write-cloth-hanger .feed-img:hover{
  opacity: 0.7;
}

.cancle-img:hover ~ .feed-img{
  opacity: 0.7;
}

.write-cloth-hanger .write-uploaded-img .fa-plus:hover {
  background-color: black;
  color: #fff;
}

.write-content-box {
  width: 100%;
  margin: 2vh 0 2vh 0;
}

.write-content-box .write-content-head {
  font-size: 2.5vh;
  font-weight: 700;
}

.write-content-box .write-hash-head {
  font-size: 2.5vh;
  font-weight: 700;
}

.write-content-box textarea {
  width: 100%;
  border: 1px solid black;
  border-radius: 3px;
  resize: none;
  padding: 10px;
}

.write-content-box input {
  width: 100%;
  height: 40px;
  border: 1px solid black;
  border-radius: 3px;
  resize: none;
  padding: 10px;
}

.btn.write-btn {  
  color: #B0B0B0;
  border: 2px solid #B0B0B0;
  background-color: white;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
  cursor:default;
  position: sticky;
  bottom: 0;
  margin-top: 0;
}

.btn.on-write-btn {
  color: white;
  background-color: #5AAEFF;
  border: 0;
  width: 100%;
  font-size: 20px;
  box-sizing: border-box;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s !important;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

.write-hash-group {
  width: 100%;
  border-radius: 5px;
  display: flex;
  flex-wrap: wrap;
}

.write-hash-group .write-hash-item {
  padding: 1px 5px;
  background-color: #050505;
  color: #fff;
  border-radius: 10px;
  margin: 0.5vh 5px 0 0;
  font-size: 80%;
  padding: 0 3vw;
  font-weight: 700;
}
@media (min-width: 1200px) {
  .write-hash-group .write-hash-item {
  padding: 0 2%;
  }
}

.write-hash-item .write-hash-item-close-btn {
  display: inline-block;
  cursor: pointer;
  /* margin-right: 5px; */
}

.write-hash-item-close-btn .fa-times {
  font-size: 80%;
  font-weight: 600;
  color: #fff;
  margin: 0 5px 5px 0;
}

.write-hash-item-close-btn .fa-times:hover{
  color: #fc0303;
}

</style>