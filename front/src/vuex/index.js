import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


// 작성한 모듈을 가져옵니다.
import Store from '@/vuex/store.js'

const store = new Vuex.Store({
  modules: {
    // 키: 값 형태로 저장됩니다.
    Store: Store,
  },
})

export default store