import Vue from "vue";
import "./plugins/vuetify";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./registerServiceWorker";
import * as VueGoogleMaps from "vue2-google-maps";

Vue.config.productionTip = false;
Vue.use(VueGoogleMaps, {
  load: {
    libraries: "places,geometry,directions",
    key: process.env.VUE_APP_GOOGLE_KEY
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
