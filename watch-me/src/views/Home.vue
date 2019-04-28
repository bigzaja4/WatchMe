<template>
  <v-layout row wrap justify-center>
    <v-flex xs12>
      Show only status SOS
      <v-btn-toggle flat v-model="toggle" mandatory>
        <v-btn flat>On</v-btn>
        <v-btn flat>Off</v-btn>
      </v-btn-toggle>
      <v-data-table :headers="headers" :items="status" class="elevation-1">
        <template v-slot:items="props">
          <tr @click="showChart(props.item.trackingStatusId,props.item.status)">
            <td class="text-xs-left">{{ props.item.username }}</td>
            <td class="text-xs-left">{{ props.item.heartRate}}</td>
            <td class="text-xs-left">
              <v-chip
                :color="getStatusColor(props.item.status)"
                text-color="white"
              >{{ props.item.status }}</v-chip>
            </td>
            <td class="text-xs-left">
              <v-btn small @click="findLocation(props.item.trackingStatusId)">Location</v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-flex>

    <v-flex xs9 :key="idClicked" v-if="isShowChart">
      <chartInfo :status="statusId"/>
    </v-flex>
  </v-layout>
</template>

<script>
const chartInfo = () => import("../components/Chart");
import { mapGetters, mapActions } from "vuex";
export default {
  components: {
    chartInfo
  },
  data() {
    return {
      statusId: 0,
      isShowChart: false,
      idClicked: 0,
      headers: [
        {
          text: "Username",
          align: "left",
          sortable: false,
          value: "username"
        },
        { text: "Heart Rate", value: "heartRate" },
        { text: "Status", value: "status" },
        { text: "Location", value: "location" }
      ]
    };
  },
  methods: {
    ...mapActions([
      "setCenter",
      "setZoomLevel",
      "setInfoWindow",
      "setShowOnlySOS"
    ]),
    showChart: function(trackId, status) {
      if (this.idClicked == trackId) {
        this.idClicked = 0;
        this.isShowChart = false;
      } else {
        this.idClicked = trackId;
        this.isShowChart = true;
      }

      if (status == "normal") {
        this.statusId = 0;
      } else {
        this.statusId = 1;
      }
    },
    getStatusColor: function(status) {
      if (status == "normal") {
        return "primary";
      } else {
        return "red";
      }
    },
    findLocation: function(id) {
      let marker = this.markerByUserId(id);
      this.setZoomLevel(17);
      this.setCenter(marker.position);
      this.setInfoWindow(marker);
      this.$router.push("/map");
    }
  },
  computed: {
    ...mapGetters(["status", "markerByUserId", "showOnlySOS"]),
    toggle: {
      get() {
        return this.showOnlySOS;
      },
      set(value) {
        this.setShowOnlySOS(value);
      }
    }
  }
};
</script>
