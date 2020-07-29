import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import store from './vuex/store';
import VueCookies from 'vue-cookies'

Vue.config.productionTip = false

Vue.use(VueCookies)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')