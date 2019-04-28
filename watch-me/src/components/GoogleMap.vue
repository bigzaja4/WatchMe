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
      :animation="marker.animation"
      @click="setInfoWindow(marker)"
    ></gmap-marker>
    <v-btn icon @click="resetZoom" slot="visible">
      <v-icon>zoom_out_map</v-icon>
    </v-btn>
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
  mounted() {
    this.zoomOut(this.zoomLevel);
  },
  methods: {
    ...mapActions(["setInfoWindow", "closeInfoWindow"]),
    zoomOut: function(event) {
      if (event <= 10) {
        this.cluster = true;
      } else {
        this.cluster = false;
      }
    },
    resetZoom: function() {
      this.$refs.gmap.$mapObject.setZoom(5);
      this.$refs.gmap.$mapObject.setCenter({ lat: 13.7563, lng: 100.5018 });
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
.v-btn {
  top: 0%;
  right: 0%;
  background-color: #4169e1;
  color: white;
  position: absolute;
  z-index: 1;
}
</style>
