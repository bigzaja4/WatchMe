<template>
  <v-app>
    <Navbar/>
    <v-content>
      <keep-alive>
        <v-container fill-height>
        <router-view></router-view>
        </v-container>
      </keep-alive>
    </v-content>
  </v-app>
</template>

<script>
import Navbar from "./components/Navbar";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "App",
  components: {
    Navbar
  },
  data() {
    return {
      //
    };
  },
  created() {
    this.fetchStatus();
    this.timer = setInterval(this.fetchStatus, 3000);
  },
  methods: {
    ...mapActions(["fetchStatus"])
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
};
</script>
