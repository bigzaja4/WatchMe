<template>
  <gmap-map
    ref="gmap"
    :center="{lat:13.736717, lng:100.523186}"
    :zoom="8"
    :options="{
        mapTypeControl: false,
        fullscreenControl: false,
        streetViewControl: false,
        gestureHandling: 'greedy',
        scaleControl: false, 
        zoomControl: false
      }"
  ></gmap-map>
</template>

<script>
export default {
  name: "GoogleMap"
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
