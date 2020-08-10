import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import store from './vuex/store';
import VueCookies from 'vue-cookies'
// import firebase from 'firebase'

// // Required for side-effects
// require("firebase/firestore");

// // Your web app's Firebase configuration
// var firebaseConfig = {
//   apiKey: "AIzaSyCPKM_f3wVIMx9PG9A62_c7ObfSShrqXBQ",
//   authDomain: "vue-firestore-704a4.firebaseapp.com",
//   databaseURL: "https://vue-firestore-704a4.firebaseio.com",
//   projectId: "vue-firestore-704a4",
//   storageBucket: "vue-firestore-704a4.appspot.com",
//   messagingSenderId: "880449748292",
//   appId: "1:880449748292:web:c13cb68cfd9815dff16b11",
//   measurementId: "G-HX35ED5RHD"
// };
// // Initialize Firebase
// firebase.initializeApp(firebaseConfig);
// firebase.analytics();

// var db = firebase.firestore();

// window.db = db;

// db.settings({
// });

Vue.config.productionTip = false

Vue.use(VueCookies)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')