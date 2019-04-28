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
    <v-snackbar
      v-model="snackbar"
      :bottom="y === 'bottom'"
      :left="x === 'left'"
      :multi-line="mode === 'multi-line'"
      :right="x === 'right'"
      :timeout="timeout"
      :top="y === 'top'"
      :vertical="mode === 'vertical'"
    >
      SOS: {{ alertCount }} people!!
      <v-btn color="yellow" flat to="/" @click="snackbar = false">Open</v-btn>
      <v-btn color="pink" flat @click="snackbar = false">Close</v-btn>
    </v-snackbar>
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
      y: "bottom",
      x: "right",
      mode: "multi-line",
      timeout: 2000
    };
  },
  computed: {
    ...mapGetters(["alertCount"]),
    snackbar: {
      get() {
        return this.alertCount != 0;
      },
      set(value) {
        this.$store.commit("SET_ALERTCOUNT", 0);
      }
    }
  },
  created() {
    this.loop();
    this.timer = setInterval(this.loop, 3000);
  },
  methods: {
    ...mapActions(["fetchStatus", "setAlertCount"]),
    loop: function() {
      this.fetchStatus();
      this.setAlertCount();
    }
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
};
</script>
