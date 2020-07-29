<template>
  <div id="app">
    <div id="nav">
    </div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/common.css";
import { mapState, mapGetters, mapMutations } from 'vuex';

export default {
  name: 'App',
  data() {
    return {
    }
  },
  computed: {
    ...mapState([]),
    ...mapGetters(['authToken', 'isLoggedIn'])
  },
  methods: {
    ...mapMutations(['setLoggedIn', 'setToken'])
  },
  mounted() {
    if (this.$cookies.isKey('auth-token')) {
      this.setLoggedIn(true);
      this.setToken(this.$cookies.get('auth-token'));
    }
    else {
      this.setLoggedIn(false);
    }

  },
}
</script>
