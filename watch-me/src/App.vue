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

export default {
  name: "App",
  components: {
    Navbar
  },
  data() {
    return {
      //
    };
  }
};
</script>
