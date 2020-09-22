<template>
  <div class="wrap">
    <div class="write-toggle-area">
      <div @click='writeFormChange(false)' class="feed-write-btn write-toggle-btn">Feed</div>
      <div @click='writeFormChange(true)' class="commu-write-btn write-toggle-btn">Resell</div>
    </div>
    <div class="write-hanger-box"></div>
    <div class='wrap-container feed-write-container'>
      <div class='write-feed-box' v-show="!isCommu">
        <div class="write-img-box">
          <!-- <img class="write-hanger-img" src="../assets/images/hanger.png" alt=""> -->
          <label v-if="!feedImg[0]" class="write-upload-btn" for='feed-img-edit0'>
            <i class="far fa-images"><i class="fas fa-plus"></i></i>
            <input multiple="multiple" class="imgdata" type="file" id="feed-img-edit0" accept="image/*" @change="setFeedImg(0)">
          </label>
        </div>
        <div class="write-cloth-box">
          <div class="write-cloth-hanger">
            <img class="write-cloth-hanger-img first-feed-img" src="../assets/images/cloth-hanger.png" alt="">
            <div @mouseover="onCancelBtn(1)" @mouseout="offCancleBtn" class="write-uploaded-img first-feed-img">
              <i @click='delFeedImg(1)' v-show='feedImg[0]&&isCancle1' class="far fa-times-circle cancle-img"></i>
              <img v-if="feedImg[0]" class='feed-img' :src="feedImg[0]" alt="">
              <label for="feed-img-edit1" class='feed-more-label'>
                <input multiple="multiple" class="imgdata" type="file" id="feed-img-edit1" accept="image/*" @change="setFeedImg(1)">
              </label>
            </div>
          </div>
          <div class="write-cloth-hanger">
            <img class="write-cloth-hanger-img second-feed-img" src="../assets/images/cloth-hanger.png" alt="">
            <div @mouseover="onCancelBtn(2)" @mouseout="offCancleBtn" class="write-uploaded-img second-feed-img">
              <i @click='delFeedImg(2)' v-show='feedImg[1]&&isCancle2' class="far fa-times-circle cancle-img"></i>
              <img v-if="feedImg[1]" class='feed-img' :src="feedImg[1]" alt="">
              <label for="feed-img-edit2" class='feed-more-label'>
                <i v-if="feedImg[0] && !feedImg[1]" class="fas fa-plus"></i>
                <input multiple="multiple" class="imgdata" type="file" id="feed-img-edit2" accept="image/*" @change="setFeedImg(2)">
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
                <input multiple="multiple" class="imgdata" type="file" id="feed-img-edit3" accept="image/*" @change="setFeedImg(3)">
              </label>
            </div>
          </div>
        </div>
        <div class="write-content-box">
          <p class='write-content-head'>내용</p>
          <textarea @input="writeContent = $event.target.value" cols="30" rows="3" placeholder=" 100자 이내로 작성해주세요"></textarea>
          <p class='write-hash-head'>태그</p>
          <input @input="writeHashContent = $event.target.value" v-model='writeHashContent' @keyup.188="addWriteHash" type="text" placeholder=" , 를 통해 태그를 추가해주세요">
          <transition-group name='fade' tag="div" class="write-hash-group" mode="in-out">
            <div class='write-hash-item' v-for='(hash, index) in writeHashList' :key='`hash-${index}`'>
              <div @click='delWriteHashItem(index)' class="write-hash-item-close-btn"><i class="fas fa-times"></i></div>
              {{ hash }}
            </div>
          </transition-group>
        </div>
        <div class="write-btn-box">
          <div v-if="!isWriteBtn" class="btn write-btn">작성하기</div>
          <div v-if="isWriteBtn" class="btn write-btn on-write-btn" @click="sendBoardData">작성하기</div>
        </div>
      </div>
    </div>


    <div v-show="isCommu">
      <div class="write-commu-img-box">
        <div class="write-commu-upload">
          <div class="write-commu-cloth-hanger">
            <img class="write-commu-cloth-hanger-img first-feed-img" src="../assets/images/cloth-hanger.png" alt="">
          </div>
          <label v-show="!commuImg" class="write-upload-btn" for='feed-img-edit'>
            <i class="far fa-images"><i class="fas fa-plus"></i></i>
            <input type= "file" id="feed-img-edit" accept="image/*" @change="setCommuImg">
          </label>
          <div class="write-commu-img" @mouseover="onCancelBtn" @mouseout="offCancleBtn">
            <i @click='delCommuImg' v-show='commuImg&&isCommuCancle' class="far fa-times-circle cancle-img"></i>
            <img v-show="commuImg" class="write-commu-image" :src="commuImg" alt="">
          </div>
        </div>
      </div>
      <div class="write-content-box">
        <p class='write-content-head'>내용</p>
        <textarea class="commu-content-textbox" @input="commuContent = $event.target.value" cols="30" rows="3" placeholder="100자 이내로 작성해주세요"></textarea>
        <div class="commu-body-box">
          <div class="commu-price-box">
            <p class='write-content-head'>가격</p>
            <textarea class="commu-price-textbox" @input="commuPrice = $event.target.value" cols="30" rows="3" placeholder="숫자만 입력하세요"></textarea>
          </div>
          <div class="commu-size-box">
            <p class='write-content-head'>사이즈</p>
            <textarea class='commu-size-textbox' @input="commuSize = $event.target.value" cols="30" rows="3" placeholder="내용"></textarea>
          </div>
          <div class="commu-size-box">
            <p class='write-content-head'>카테고리</p>
            <textarea class='commu-category-textbox' @input="commuCategory = $event.target.value" cols="30" rows="3" placeholder="ex)신발"></textarea>
          </div>
        </div>
        <div class="commu-footer-box">
          <div class="commu-location-box">  
            <p class='write-content-head'>장소</p>  
            <textarea class='commu-location-textbox' @input="commuPlace = $event.target.value" cols="30" rows="3" placeholder="버튼을 통해 검색해주세요" 
            v-model="commuPlace" readonly></textarea>
          </div>
          <div class="commu-loc-search-box" @click="onModal">주소검색</div>
        </div>
      </div>
      <div class="write-btn-box">
          <div v-if="!isCommuBtn" class="btn write-btn">작성하기</div>
          <div v-if="isCommuBtn" class="btn write-btn on-write-btn" @click="sendRecellData">작성하기</div>
        </div>
    </div>
    <PostModal @child-event="receiveAddress" v-if="postModal" @close="postModal= false" />
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import axios from 'axios';
import "../components/css/feedwrite.css"
import Swal from 'sweetalert2'
import PostModal from '../components/PostModal.vue'

export default {
  
  name: 'FeedWrite',
  computed: {
    ...mapState(['flag'])
  },
  components:{
    PostModal,
  },
  data() {
    return {
      photo : [],
      commuPhoto: [],
      feedImg : [],
      commuImg: '',
      isCancle1: false,
      isCancle2: false,
      isCancle3: false,
      isCommuCancle: false,
      isNumPrice: false,
      writeHashContent: '',
      writeHashList: [],
      writeContent: '',
      commuContent: '',
      commuPrice: '',
      commuSize: '',
      commuPlace:'',
      commuCategory: '',
      isWriteBtn: false,
      isCommuBtn: false,
      isCommu: false,
      postModal: false,
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
      setTimeout(function() {
        document.querySelector('.write-hash-group').scrollTop = document.querySelector('.write-hash-group').scrollHeight;
      },1)
    },
    flag() {
      this.defaultDark()
    },
    commuImg() {
      this.checkCommuForm()
    },
    commuContent() {
      this.checkCommuForm()
    },
    commuPrice() {
      if (isNaN(this.commuPrice) || this.commuPrice === '') {
        this.isNumPrice = false
      } else {
        this.isNumPrice = true
      }
      this.checkCommuForm()
      this.inNumber()
    },
    commuSize() {
      this.checkCommuForm()
    },
    commuCategory() {
      this.checkCommuForm()
    },
  },
  mounted() {
    this.setIsWrite(false)
    const FEEDBTN = document.querySelector('.feed-write-btn')
    FEEDBTN.classList.add('on-feed-btn')
    this.defaultDark()
  },
  methods: {
    ...mapMutations(['setIsWrite']),
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const PTAG = document.querySelectorAll('p')
      // const HANGER = document.querySelector('.write-hanger-img')
      const HANGER = document.querySelector('.write-hanger-box')
      const CLOTH_HANGER = document.querySelectorAll('.write-cloth-hanger-img')
      const CLOTH_HANGER2 = document.querySelector('.write-commu-cloth-hanger-img')
      const ON_TOGGLE = document.querySelector('.on-feed-btn')
      const WRITE_PLUS = document.querySelectorAll('.write-plus')
      const CANCLE_IMG = document.querySelectorAll('.cancle-img')
      const INPUT = document.querySelectorAll('input')
      const TEXTAREA = document.querySelectorAll('textarea')
      const HASHTAG = document.querySelectorAll('.write-hash-item')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        CLOTH_HANGER2.classList.add('cloth-hanger-dark')
        ON_TOGGLE.classList.add('toggle-dark')
        // HANGER.classList.add('hanger-dark')
        HANGER.classList.add('hanger-dark')
        for (let i=0; i<CLOTH_HANGER.length; i++) {
          CLOTH_HANGER[i].classList.add('cloth-hanger-dark')
        }
        for (let i=0; i<WRITE_PLUS.length; i++) {
          WRITE_PLUS[i].classList.add('write-plus-dark')
        }
        for (let i=0; i<CANCLE_IMG.length; i++) {
          CANCLE_IMG[i].classList.add('write-cancle-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.add('textarea-dark')
        }
        for (let i=0; i<HASHTAG.length ; i++) {
          HASHTAG[i].classList.add('write-plus-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        CLOTH_HANGER2.classList.remove('cloth-hanger-dark')
        ON_TOGGLE.classList.remove('toggle-dark')
        // HANGER.classList.remove('hanger-dark')
        HANGER.classList.remove('hanger-dark')
        for (let i=0; i<CLOTH_HANGER.length; i++) {
          CLOTH_HANGER[i].classList.remove('cloth-hanger-dark')
        }
        for (let i=0; i<WRITE_PLUS.length; i++) {
          WRITE_PLUS[i].classList.remove('write-plus-dark')
        }
        for (let i=0; i<CANCLE_IMG.length; i++) {
          CANCLE_IMG[i].classList.remove('write-cancle-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<TEXTAREA.length ; i++) {
          TEXTAREA[i].classList.remove('textarea-dark')
        }
        for (let i=0; i<HASHTAG.length ; i++) {
          HASHTAG[i].classList.remove('write-plus-dark')
        }
      }
    },

    setFeedImg(num) {
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
          this.photo.push(photoFile.files[i]);
        }
      }
    },
    setCommuImg() {
      const CommuPhotoFile = document.getElementById('feed-img-edit')
      this.commuImg = URL.createObjectURL(CommuPhotoFile.files[0])
      this.commuPhoto.push(CommuPhotoFile.files[0])
    },
    onCancelBtn(num) {
      if (num === 1){
        this.isCancle1 = true
      } else if (num === 2) {
        this.isCancle2 = true
      } else if (num === 3) {
        this.isCancle3 = true
      } else {
        this.isCommuCancle = true
      }
    },
    offCancleBtn() {
      this.isCancle1 = false
      this.isCancle2 = false
      this.isCancle3 = false
      this.isCommuCancle = false
    },
    delFeedImg(num) {
      let idx = num-1
      this.feedImg.splice(idx, 1)
      this.photo.splice(idx, 1)
    },
    delCommuImg() {
      this.commuImg = ''
      document.getElementById('feed-img-edit').value = ""
    },
    addWriteHash() {
      if (this.writeHashContent != ',') {
        if (this.writeHashContent[0] === '#') {
          this.writeHashList.push(this.writeHashContent.slice(1,-1))
        }
        else {
          this.writeHashList.push(this.writeHashContent.slice(0,-1))
        }
        this.writeHashContent = ''
      } else {
        this.writeHashContent = ''
      }
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
    },
    checkCommuForm() {
      if (this.commuImg && this.commuContent && this.isNumPrice && this.commuSize && this.commuCategory) {
        this.isCommuBtn = true
      } else {
        this.isCommuBtn = false
      }
    },
    sendBoardData(){
      if (this.writeHashContent[0]) {
        if (this.writeHashContent[0] === '#') {
        this.writeHashList.push(this.writeHashContent.slice(1, ))
        } else {
          this.writeHashList.push(this.writeHashContent)
        }
      }
      let dataforms = new FormData();
      for (let index = 0; index < this.photo.length; index++) {
        dataforms.append("imgdata",this.photo[index]);
      }
      dataforms.append("nickname",this.$cookies.get('auth-nickname'));
      dataforms.append("content", this.writeContent);
      dataforms.append("tags",this.writeHashList);
     
      axios.post("http://localhost:8080/api/board/upload",dataforms).then()
        Swal.fire({
          icon: 'success',
          title: '작성이 완료됐습니다!',
          confirmButtonText: '확인'
        }).then((result) => {
          if (result.value) {
            this.$router.push('/feed')
          }
        })
    },
    sendRecellData(){
      let dataform = new FormData();
      dataform.append("recellimg",this.commuPhoto[0]);
      dataform.append("nickname",this.$cookies.get('auth-nickname'));
      dataform.append("content", this.commuContent);
      dataform.append("price",this.commuPrice);
      dataform.append("size",this.commuSize)
      dataform.append("place",this.commuPlace);
      dataform.append("category",this.commuCategory);
      
      axios.post("http://localhost:8080/api/recell/upload", dataform).then()
        Swal.fire({
          icon: 'success',
          title: '작성이 완료됐습니다!',
          confirmButtonText: '확인'
        }).then((result) => {
          if (result.value) {
            this.$router.push('/community')
          }
        })
      },
    writeFormChange(bool) {
      const FEEDBTN = document.querySelector('.feed-write-btn')
      const COMMUBTN = document.querySelector('.commu-write-btn')
      const Dark = this.$cookies.get('dark')
      if (bool) {
        COMMUBTN.classList.add('on-feed-btn')
        FEEDBTN.classList.remove('on-feed-btn')
      } else {
        FEEDBTN.classList.add('on-feed-btn')
        COMMUBTN.classList.remove('on-feed-btn')
      }
      this.isCommu = bool
      if (Dark === 'off') {
        const DARK_TOGGLE = document.querySelector('.toggle-dark')
        DARK_TOGGLE.classList.remove('toggle-dark')
        this.defaultDark()
      }
    },
    onModal(){
      this.postModal = true;
    },
    receiveAddress(place){
      this.commuPlace=place;
    },
    inNumber() {
      if(event.keyCode<48 || event.keyCode>57){
        event.returnValue=false
      }
    }
  },
  beforeDestroy() { 
    this.setIsWrite(true)
  }
}
</script>
<style scoped>

</style>