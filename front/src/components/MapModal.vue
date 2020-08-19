<template>
  <transition name="modal">
    <div class="post-modal-mask" @click.self="$emit('close')">
      <i class="fas fa-times" @click.self="$emit('close')"></i>
        <div id="map" class="post-modal-wrap"></div>
    </div>
  </transition>
</template>
<script>
export default {
  name: 'mapModal',
  props:['placeAddrress'],
  data() {
    return {
    }
  },
  mounted(){
    if (window.kakao && window.kakao.maps) {
        this.initMap();
    } else {
        const script = document.createElement('script');
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = 'https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f736b9175fcd30db0d9c8efac1c57950&libraries=services';
        document.head.appendChild(script);
    }

  },

  methods: {
    initMap() {
      let HEAR = this.placeAddrress
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
      };  

      // 지도를 생성합니다    
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(this.placeAddrress, function(result, status) {

          // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {

              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });

              //인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">' + HEAR + '</div>'
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      });   
    }
  }
}
</script>

<style>
.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;
}

.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;
}

.post-modal-mask {
  position: fixed;
  z-index: 99999;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.post-modal-mask .fa-times {
  position: absolute;
  top: 3vh;
  right: 3vh;
  cursor: pointer;
  color: #fff;
  font-size: 5vh;
}

.modal-enter .modal-wrapper,
.modal-leave-active .modal-wrapper {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.post-modal-wrap {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  padding: 1vh;
  transition: 0.3s ease;
  background-color: #fff;
  border-radius: 1vw;
  width: 70%;
  height: 60%;
}

@media (min-width: 450px) {
  .post-modal-wrap {
  max-width: 50vh;
  width: 100%;
  margin: 0 auto;
  height: 65vh;
  }
}

.post-more-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.post-more-user-data {
  display: flex;
  width: 100%;
  align-items: center;
  position: relative;
}
.post-more-user-profile {
  width: 4vh;
  height: 4vh;
  background-color: grey;
  border-radius: 50%;
}

.post-more-user-profile img{
  width: 100%;
  height: 100%;
  border-radius: 50%;
  cursor: pointer;
}

.post-more-article-head p {
  margin: 0 1vh;
}
.post-more-article-head .post-more-username {
  font-weight: 700;
  font-size: 1.5vh;
  cursor: pointer;
}
.post-more-article-head .post-more-article-date {
  font-size: 1vh;
  color: grey;
}

.post-more-del-btn {
  position: absolute;
  top:0;
  right: 1vh;
  font-size: 1.4vh;
  cursor: pointer;
  transition: all 0.5s ease ;
}

.post-more-del-btn:hover {
  color: #5AAEFF;
}

.post-more-content {
  width: 100%;
  margin-top: 0.5vh;
}
.post-more-content .post-more-content-img {
  width: 100%;
  padding-top: 100%;
  position: relative;
  background-color: grey;

}

/* @media (min-height: 569px) and (max-height: 667px) {
  .post-more-content-img {
    height: 400px !important;
  }
} */



.post-more-content .post-more-content-img img{
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
}

.post-more-content .post-more-btn-box {
  display: flex;
  width: 100%;
  padding-top: 1vh;
}

.post-more-content .post-more-btn-box .post-more-btn-left {
  display: flex;
  align-items: center;
  width: 50%;
}

.post-more-content .post-more-btn-box .post-more-btn-left .fas {
  font-size : 100%;
  margin: 0 1vh 0 1vh;
  cursor: pointer;
}

.post-more-content .post-more-btn-box .post-more-btn-right {
  display: flex;
  align-items: center;
  width: 50%;
  justify-content: flex-end;
}

.post-more-content .post-more-btn-box .post-more-btn-right .fas {
  font-size : 100%;
  margin: 0;
  margin-right: 1vh;
  cursor: pointer;
}

.post-more-content .post-more-content-head {
  font-weight: 900;
  font-size: 2vh;
  margin-left: 1vh;
  margin-top: 0.5vh;
}

.post-more-content .post-more-content-tag {
  font-weight: 700;
  font-size: 1.7vh;
  display: flex;
  margin-left: 1vh;
  flex-wrap: wrap;
}

.post-more-content .post-more-content-tag .post-more-content-tag-name{
  margin-right: 1vh;
}

.post-modal-wrap-dark {
  background-color: #272727 !important;
}
</style>