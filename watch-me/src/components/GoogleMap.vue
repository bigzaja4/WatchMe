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
  >
    <gmap-info-window
      v-if="infoWindow.marker"
      :options="infoWindow.infoOptions"
      :position="infoWindow.marker.position"
      :opened="infoWindow.infoWindowOpen"
      @closeclick="closeInfoWindow"
    >
      <h2>{{infoWindow.marker.name}} {{infoWindow.marker.surname}}</h2>
      <br>
      <p>heartRate: {{infoWindow.marker.heartRate}}</p>
      <p>attitude: {{infoWindow.marker.attitude}}</p>
      <v-divider></v-divider>
      <br>
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
    ...mapGetters(["center", "zoomLevel", "markers", "infoWindow"])
  },
  methods: {
    ...mapActions(["setInfoWindow", "closeInfoWindow"])
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
