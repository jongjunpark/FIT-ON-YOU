<template>
  <div id="app">
    <div id="nav">
    </div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/common.css";
import axios from 'axios'
import { mapState, mapGetters, mapMutations } from 'vuex';

export default {
  name: 'App',
  data() {
    return {
    }
  },
  computed: {
    ...mapState(['authToken', 'isLoggedIn', 'user']),
    ...mapGetters([])
  },
  methods: {
    ...mapMutations(['setLoggedIn', 'setToken', 'setUser']),
  },
  mounted() {
     if (this.$cookies.isKey('auth-token')) {
      this.setLoggedIn(true);
      this.setToken(this.$cookies.get('auth-token'));
      axios.get('http://localhost:8080/account/token',{
        params:{jwt : this.authToken}
      }).then((res)=>{
        console.log(res);
        this.setUser(res.data.userInfo.User)
      })
      .catch(()=>{

      });
    }
    else {
      this.setLoggedIn(false);
      this.setUser('')
    }

  },
}
</script>
