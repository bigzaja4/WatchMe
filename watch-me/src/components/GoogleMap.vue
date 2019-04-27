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
    <div :key="1" v-if="cluster">
      <gmap-marker
        :key="11111"
        :position="{lat:13.7248936,lng:100.4930261}"
        @click="setInfoWindow({heartRate:100})"
      ></gmap-marker>
    </div>
    <div :key="2" v-else>
      <gmap-marker
        :key="marker.id"
        v-for="(marker) in markers"
        :position="marker.position"
        :title="marker.title"
        @click="setInfoWindow(marker)"
      ></gmap-marker>
    </div>
  </gmap-map>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import GmapCluster from "vue2-google-maps/dist/components/cluster";
export default {
  name: "GoogleMap",
  components: {
    GmapCluster
  },
  data() {
    return {
      cluster: true
    };
  },
  computed: {
    ...mapGetters(["center", "zoomLevel", "markers", "infoWindow", "status"])
  },
  methods: {
    ...mapActions(["setInfoWindow", "closeInfoWindow"]),
    zoomOut: function(event) {
      if (event <= 10) {
        this.cluster = true;
      } else {
        this.cluster = false;
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
