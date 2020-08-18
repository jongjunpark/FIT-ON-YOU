<template>
  <div class='following-container'>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'


export default {
  name: 'Following',
  data() {
    return {
      nick: '',
      name: '',
    }
  },
  mounted() {
    this.defaultDark()
    let nickdata = this.$cookies.get('auth-nickname')
    let uri = nickdata;
    let uri_enc = encodeURIComponent(uri);
    let uri_dec = decodeURIComponent(uri_enc);
    let res = uri_dec;
    this.nick = res
    
    this.name = this.$route.params.name

    axios.get('https://i3b304.p.ssafy.io/api/follow/forFollowing',{
      params:{
      userName: this.name,
    }
    }).then((data) => {
      const ARRAYFOLLOW = data.data.result
      const PRFIMGS = data.data.prfimgs;
      ARRAYFOLLOW.forEach((element,index) => {
        console.log(element,index);
        
        const FollowingBox = document.createElement('div')
        const FollowingIcon = document.createElement('div')
        const FollowingImg = document.createElement('img')
        const FollowingText = document.createElement('div')
        const FollowingUser = document.createElement('div')
        const FollowingBtnArea = document.createElement('div')
        const FollowingBtn = document.createElement('div')
        const DMBTN = document.createElement('div')
        const Father = document.querySelector('.following-container')

        FollowingBtn.innerHTML = '팔로우취소'
        DMBTN.innerHTML = 'DM보내기'

        FollowingBtn.classList.add('following-follow-btn')
        DMBTN.classList.add('following-dm-btn')

        FollowingBtnArea.classList.add('following-btn-area')
        FollowingUser.classList.add('following-username')
        FollowingUser.innerHTML = element.followeduser
        FollowingText.classList.add('following-text-area')
        FollowingIcon.classList.add('following-icon-area')
        if(PRFIMGS[index]=="-1") FollowingImg.src = "/images/default-user.png"
        else FollowingImg.src=PRFIMGS[index];
        FollowingBox.classList.add('following-box')

        FollowingIcon.appendChild(FollowingImg)
         if (!(this.nick == element.followeduser)) {
          FollowingBtnArea.appendChild(FollowingBtn)
          FollowingBtnArea.appendChild(DMBTN)
          }

        FollowingText.appendChild(FollowingUser)
        FollowingText.appendChild(FollowingBtnArea)

        FollowingBox.appendChild(FollowingIcon)
        FollowingBox.appendChild(FollowingText)
        if (Father) {

          Father.appendChild(FollowingBox)
        }

        FollowingImg.addEventListener('click',()=>{
          this.$router.push(`/otheruser/${element.followeduser}`).catch(()=>{})
        })


        DMBTN.addEventListener('click', () => {
          axios.get('https://i3b304.p.ssafy.io/api/chat/existroom',{
            params:{
              firstuser: element.followeduser,
              seconduser: this.name
            }
            }).then((data)=>{
              this.$router.push(`/directmessage/${data.data.object.roomname}/${element.followeduser}`).catch(()=>{})
            })
              .catch(
              )
        })
        let Num = element.followno

        axios.get('https://i3b304.p.ssafy.io/api/isfollowed',{
          params:{
          followedUser: element.followeduser,
          followingUser: this.nick
        }
        }).then((data) => {
          if (!data.data.object) {
            FollowingBtn.classList.add('btn-cancel-Ok')
            FollowingBtn.innerHTML = '팔로우하기'
          } else {
            Num = data.data.object.followno
          }
        })
        .catch(
        )
        



        FollowingBtn.addEventListener('click', () => {
          const Action = FollowingBtn.className
          if (Action == 'following-follow-btn') {
            axios.get('https://i3b304.p.ssafy.io/api/isfollowed',{
              params:{
              followedUser: element.followeduser,
              followingUser: this.nick
            }
            }).then((data) => {
              if (!data.data.object) {
            FollowingBtn.classList.add('btn-cancel-Ok')
            FollowingBtn.innerHTML = '팔로우하기'
          } else {
            Num = data.data.object.followno
          }
              axios.get('https://i3b304.p.ssafy.io/api/follow/delete',{
              params:{
                followNo: Num,
              }
              }).then(() => {
                FollowingBtn.classList.add('btn-cancel-Ok')
                FollowingBtn.innerHTML = '팔로우하기'
              })
                .catch(
                )
            })
            .catch(
            )

          } else {
              axios.get('https://i3b304.p.ssafy.io/api/follow/add',{
                params:{
                  followedUser: element.followeduser,
                  followingUser: this.nick
                }
                }).then((data) => {
                  FollowingBtn.classList.remove('btn-cancel-Ok')
                  FollowingBtn.innerHTML = '팔로우취소'
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
      const USERPEOPLE = document.querySelectorAll('.following-username')


      if (Dark === null) {
        this.$cookies.set('dark', 'on')
      }

      if (Dark === 'off') {
        HTML.classList.add('black')
        wrap.classList.add('wrap-dark')

        for (let i=0; i<USERPEOPLE.length ; i++) {
          USERPEOPLE[i].classList.add('following-username-dark')
        }
      } else {
        HTML.classList.remove('black')
        wrap.classList.remove('wrap-dark')

        for (let i=0; i<USERPEOPLE.length ; i++) {
          USERPEOPLE[i].classList.remove('following-username-dark')
        }
      }
    },
  },
}
</script>

<style>
.following-container {
  width: 100%;
  height: 70vh;
  overflow-y: auto;
}
.following-container::-webkit-scrollbar { width: 5px; }
::-webkit-scrollbar-track { background-color: transparent; }
::-webkit-scrollbar-thumb { background: silver;}
::-webkit-scrollbar-button { display: none; }

.following-container .following-box {
  width: 100%;
  height: 8vh;
  display: flex;
  position: relative;
  margin: 1.5vh 0;
}

.following-box .following-icon-area {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 2vh;
  cursor: pointer;
}
@media (max-width: 449px) {
  .following-box .following-icon-area {
    margin: 0 1vh;
  }
}

.following-box .following-icon-area img {
  width: 7vh;
  height: 7vh;
  background-color: #fff;
  border: 1px solid grey;
  border-radius: 50%;
}

.following-box .following-text-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.following-text-area .following-username {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
}

.following-text-area .following-btn-area {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50%;
}
@media (max-width: 449px) {
  .following-text-area .following-btn-area {
    justify-content: space-evenly;
  }
}

.following-btn-area .following-follow-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ebebeb;
  border-radius: 1.5vh;
  margin-right: 10vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
  box-shadow: 6px 6px 10px -1px rgba(0,0,0,0.15),
              -3px -3px 10px -1px rgba(250,250,250,0.15);
  transition: transform 0.5s;
  color: black !important;
}

.btn-cancel-ok {
  background-color: #5AAEFF !important;
}


@media (max-width: 449px) {
  .following-btn-area .following-follow-btn {
    margin-right: 0;
    color: black !important;
  }
}

@media (max-width: 300px) {
  .following-btn-area .following-follow-btn {
    width: 11vh;
    color: black !important;
  }
}

.following-btn-area .following-dm-btn {
  width: 14vh;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #FFBA00;
  border-radius: 1.5vh;
  font-weight: 700;
  font-size: 2vh;
  line-height: 2vh;
  box-shadow: 6px 6px 10px -1px rgba(0,0,0,0.15),
              -3px -3px 10px -1px rgba(250,250,250,0.15);
  transition: transform 0.5s;
  color: black !important;
}
@media (max-width: 300px) {
  .following-btn-area .following-dm-btn {
    width: 11vh;
    color: black !important;
  }
}

.plane-follow {
  margin: 0;
  font-size: 2.5vh;
  color: black !important;
}

.people-follow {
  margin: 0;
  font-size: 2.5vh;
  color: black !important;
}

.following-username-dark {
  font-size: 2vh;
  display: flex;
  align-items: center;
  font-weight: 700;
  height: 50%;
  width: 100%;
  color: #ebebeb !important;
}
.following-dm-btn:hover {
  box-shadow: inset 4px 4px 6px -1px rgba(0,0,0,0.2),
              inset -4px -4px 6px -1px rgba(255,255,255,0.1),
              -0.5px -0.5px 0px rgba(0,0,0,0.05),
              0.5px 0.5px 0px rgba(0,0,0,0.05),
              0px 12px 10px -10px rgba(0,0,0,0.1);
  border: 1px solid rgba(0,0,0,0.01);
  transform: translateY(5px)
}

.following-follow-btn:hover {
  box-shadow: inset 4px 4px 6px -1px rgba(0,0,0,0.2),
              inset -4px -4px 6px -1px rgba(255,255,255,0.1),
              -0.5px -0.5px 0px rgba(0,0,0,0.05),
              0.5px 0.5px 0px rgba(0,0,0,0.05),
              0px 12px 10px -10px rgba(0,0,0,0.1);
  border: 1px solid rgba(0,0,0,0.01);
  transform: translateY(5px)
}

.followed-img {
  width: 100%;
  height: 100%;
}

</style>