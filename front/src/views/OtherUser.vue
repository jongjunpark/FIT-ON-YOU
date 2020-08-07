<template>
	<div class="wrap">
		<div class="wrap-container profile-wrap">
      <section class='edit-profile-area'>
        <div class='edit-profile-img'>
          <div v-if='!profileImg'>
            <img class='profile-img' src="../assets/images/default-user.png" alt="">
          </div>
          <div v-if='profileImg'>
            <img class='profile-img' :src="profileImg" alt="">
          </div>
        </div>
        <div class="follow">
          <h2>팔로우</h2>
          <h3>123,456</h3>
        </div>
        <div class="follower">
          <h2>팔로워</h2>
          <h3>123,456</h3>
        </div>
      <p class="other-nickname">UserName</p>
      <div class="other-content">
        <h3>자기소개 입니다.</h3>
      </div>
			<div class="parent-other">
				<div class="other-user-follow">
					<div class="other-user-child">
						<span><i class="fas fa-user fa-2x settings"></i> 팔로우</span>
					</div>
				</div>
				<div class="other-user-DM">
					<div class="other-user-child-DM">
						<span><i class="fas fa-paper-plane fa-2x settings"></i> DM 보내기</span>
					</div>
				</div>
			</div>
      </section>
    </div>
    <div class="List-parent">
      <div class="List-other">
        <i class="far fa-file-alt fa-3x mylist"></i>
      </div>
      <div class="List2-other">
        <i class="far fa-file-alt fa-3x mylist"></i>
      </div>
      <div class="List3-other">
        <i class="far fa-address-book fa-3x mylist"></i>
      </div>  
    </div>
	</div>
</template>

<script>
import "../components/css/otheruser.css"
import "../components/css/profileedit.css"
import { mapState } from 'vuex'

export default {
 name: 'OtherUser',
 data() {
	return {
		profileImg: '',
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
 },
 methods: {
   defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const INPUT = document.querySelectorAll('input')
      const H2TAG = document.querySelectorAll('h2')
      const H3TAG = document.querySelectorAll('h3')
      const PTAG = document.querySelectorAll('p')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.add('input-dark')
        }
        for (let i=0; i<H2TAG.length ; i++) {
          H2TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.add('font-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.add('font-dark')
        }

      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<INPUT.length ; i++) {
          INPUT[i].classList.remove('input-dark')
        }
        for (let i=0; i<H2TAG.length ; i++) {
          H2TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<H3TAG.length ; i++) {
          H3TAG[i].classList.remove('font-dark')
        }
        for (let i=0; i<PTAG.length ; i++) {
          PTAG[i].classList.remove('font-dark')
        }
      }
    },
 },
}
</script>
