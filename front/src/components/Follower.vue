<template>
  <div class='follower-container'>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'Follower',
  data() {
    return {
      nick: '',
      name: '',
    }
  },
  mounted() {
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.nick = res
    

    this.name = this.$route.params.name

    axios.get('https://i3b304.p.ssafy.io/api/follow/forFollower',{
      params:{
      userName: this.name,
    }
    }).then((data) => {
      const ARRAYFOLLOW = data.data
      ARRAYFOLLOW.forEach(element => {
        const FollowerBox = document.createElement('div')
        const FollowerIcon = document.createElement('div')
        const FollowerImg = document.createElement('img')
        const FollowerText = document.createElement('div')
        const FollowerUser = document.createElement('div')
        const FollowerBtnArea = document.createElement('div')
        const FollowerBtn = document.createElement('div')
        const DMBTN = document.createElement('div')
        const Father = document.querySelector('.follower-container')

        FollowerBtn.innerHTML = '팔로우취소'
        DMBTN.innerHTML = 'DM보내기'

        FollowerBtn.classList.add('follower-follow-btn')
        DMBTN.classList.add('follower-dm-btn')

        FollowerBtnArea.classList.add('follower-btn-area')
        FollowerUser.classList.add('follower-username')
        FollowerUser.innerHTML = element.followinguser
        FollowerText.classList.add('follower-text-area')
        FollowerIcon.classList.add('follower-icon-area')
        FollowerImg.src = "/images/default-user.png"
        FollowerBox.classList.add('follower-box')

        FollowerIcon.appendChild(FollowerImg)
        if (!(this.nick == element.followinguser)) {
          FollowerBtnArea.appendChild(FollowerBtn)
          FollowerBtnArea.appendChild(DMBTN)
          }
        FollowerText.appendChild(FollowerUser)
        FollowerText.appendChild(FollowerBtnArea)

        FollowerBox.appendChild(FollowerIcon)
        FollowerBox.appendChild(FollowerText)
        Father.appendChild(FollowerBox)

        DMBTN.addEventListener('click', () => {
          axios.get('https://i3b304.p.ssafy.io/api/chat/existroom',{
            params:{
              firstuser: element.followinguser,
              seconduser: this.name
            }
            }).then((data)=>{
              this.$router.push(`/directmessage/${data.data.object.roomname}/${element.followinguser}`).catch(()=>{})
            })
              .catch(
              )
        })
        let Num = element.followno

        axios.get('https://i3b304.p.ssafy.io/api/isfollowed',{
          params:{
          followedUser: element.followinguser,
          followingUser: this.name
        }
        }).then((data) => {
          if (!data.data.object) {
            FollowerBtn.classList.add('btn-cancel-Ok')
            FollowerBtn.innerHTML = '팔로우하기'
          } else {
            Num = data.data.object.followno
          }
        })
        .catch(
        )

        

        FollowerBtn.addEventListener('click', () => {
          const Action = FollowerBtn.className
          if (Action == 'follower-follow-btn') {
            axios.get('https://i3b304.p.ssafy.io/api/follow/delete',{
              params:{
                followNo: Num,
              }
              }).then(() => {
                FollowerBtn.classList.add('btn-cancel-Ok')
                FollowerBtn.innerHTML = '팔로우하기'
              })
                .catch(
                )
          } else {
              axios.get('https://i3b304.p.ssafy.io/api/follow/add',{
                params:{
                  followedUser: element.followinguser,
                  followingUser: this.name
                }
                }).then((data) => {
                  FollowerBtn.classList.remove('btn-cancel-Ok')
                  FollowerBtn.innerHTML = '팔로우취소'
                  Num = data.data.object.followno
                })
                  .catch(
                  )
          }
        })


        this.defaultDark()
      });
    })
    .catch(
    )
    
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
    defaultDark() {
      const Dark = this.$cookies.get('dark')
      const HTML = document.querySelector('html')
      const wrap = document.querySelector('.wrap')
      const USERPEOPLE = document.querySelectorAll('.follower-username')

      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')
        for (let i=0; i<USERPEOPLE.length ; i++) {
          USERPEOPLE[i].classList.add('follower-username-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')
        for (let i=0; i<USERPEOPLE.length ; i++) {
          USERPEOPLE[i].classList.remove('follower-username-dark')
        }
      }
    },
  },
}
</script>

<style>
.follower-container {
  width: 100%;
  height: 70vh;
  overflow-y: auto;
}
.follower-container::-webkit-scrollbar { width: 5px; }
::-webkit-scrollbar-track { background-color: transparent; }
::-webkit-scrollbar-thumb { background: silver;}
::-webkit-scrollbar-button { display: none; }

.follower-container .follower-box {
  width: 100%;
  height: 8vh;
  display: flex;
  position: relative;
  margin: 1.5vh 0;
}

.follower-box .follower-icon-area {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 2vh;
}
@media (max-width: 449px) {
  .follower-box .follower-icon-area {
    margin: 0 1vh;
  }
}

.follower-box .follower-icon-area img {
  width: 7vh;
  height: 7vh;
  background-color: black;
  border-radius: 50%;
}

.follower-box .follower-text-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.follower-text-area .follower-username {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
}

.follower-text-area .follower-btn-area {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50%;
}
@media (max-width: 449px) {
  .follower-text-area .follower-btn-area {
    justify-content: space-evenly;
  }
}

.follower-btn-area .follower-follow-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1.5vh;
  margin-right: 10vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
  background-color: #ebebeb;
  color: black !important;
}
@media (max-width: 449px) {
  .follower-btn-area .follower-follow-btn {
    margin-right: 0;
    color: black !important;
  }
}
@media (max-width: 300px) {
  .follower-btn-area .follower-follow-btn {
    width: 11vh;
    color: black !important;
  }
}

.follower-btn-area .follower-dm-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1.5vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
  background-color: #FFBA00;
  color: black !important;
}
@media (max-width: 300px) {
  .follower-btn-area .follower-dm-btn {
    width: 11vh;
    color: black !important;
  }
}

.follower-username-dark {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
  color: #ebebeb !important;
}
.follower-dm-btn:hover {
  box-shadow: inset 4px 4px 6px -1px rgba(0,0,0,0.2),
              inset -4px -4px 6px -1px rgba(255,255,255,0.1),
              -0.5px -0.5px 0px rgba(0,0,0,0.05),
              0.5px 0.5px 0px rgba(0,0,0,0.05),
              0px 12px 10px -10px rgba(0,0,0,0.1);
  border: 1px solid rgba(0,0,0,0.01);
  transform: translateY(5px)
}

.follower-follow-btn:hover {
  box-shadow: inset 4px 4px 6px -1px rgba(0,0,0,0.2),
              inset -4px -4px 6px -1px rgba(255,255,255,0.1),
              -0.5px -0.5px 0px rgba(0,0,0,0.05),
              0.5px 0.5px 0px rgba(0,0,0,0.05),
              0px 12px 10px -10px rgba(0,0,0,0.1);
  border: 1px solid rgba(0,0,0,0.01);
  transform: translateY(5px)
}

.follower-img {
  width: 100%;
  height: 100%;
}

.btn-cancel-Ok {
  background-color: #5AAEFF !important;
}

</style>