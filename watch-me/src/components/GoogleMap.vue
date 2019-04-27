<template>
  <gmap-map
    ref="gmap"
    :center="center"
    :zoom="zoomLevel"
    :options="{
        mapTypeControl: false,
        fullscreenControl: false,
        streetViewControl: false,
        gestureHandling: 'greedy',
        scaleControl: false, 
        zoomControl: false
      }"
    @zoom_changed="zoomOut"
  >
    <gmap-info-window
      v-if="infoWindow.marker"
      :options="infoWindow.infoOptions"
      :position="infoWindow.marker.position"
      :opened="infoWindow.infoWindowOpen"
      @closeclick="closeInfoWindow"
    >
      <h2>name: {{infoWindow.marker.username}}</h2>
      <h3>status: {{infoWindow.marker.status}}</h3>
      <v-divider></v-divider>
      <br>
      <h3>heart-rate: {{infoWindow.marker.heartRate}}</h3>
    </gmap-info-window>
    <gmap-marker
      :key="marker.id"
      v-for="(marker) in markers"
      :position="marker.position"
      :title="marker.title"
      :icon="marker.icon"
      @click="setInfoWindow(marker)"
    ></gmap-marker>
  </gmap-map>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "GoogleMap",
  computed: {
    ...mapGetters(["center", "zoomLevel", "markers", "infoWindow", "status"])
  },
  methods: {
    ...mapActions(["setInfoWindow", "closeInfoWindow"]),
    zoomOut: function(event) {
      if (event == 10) {
        console.log(555);
      }
    }
  }
};
</script>
<style scoped>
.v-content,
.vue-map-container,
.vue-map {
  width: 100%;
  height: 100%;
}
</style>
