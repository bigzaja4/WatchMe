import Vue from "vue";
import Vuex from "vuex";
import axios from "../utilitys/axios";

Vue.use(Vuex);

const state = {
  center: {
    lat: 13.7563,
    lng: 100.5018
  },
  zoomLevel: 10,
  status: [],
  provinceStatus: [],
  infoWindow: {
    marker: null,
    infoWindowOpen: false,
    currentIndex: null,
    infoOptions: {
      pixelOffset: {
        width: 0,
        height: -35
      }
    }
  },
  alertCount: 0,
  showOnlySOS: 0
};

const getters = {
  center: state => {
    return state.center;
  },
  zoomLevel: state => {
    return state.zoomLevel;
  },
  status: state => {
    if (state.showOnlySOS) {
      return state.status;
    } else {
      return state.status.filter(item => {
        return item.status === "sos";
      });
    }
  },
  provinceStatus: state => {
    return state.provinceStatus;
  },
  markers: state => {
    return state.status.map(item => {
      let check = item.status !== "normal" ? 1 : null;
      return {
        id: item.trackingStatusId,
        username: item.username,
        status: item.status,
        position: {
          lat: item.position.lat,
          lng: item.position.lon
        },
        heartRate: item.heartRate,
        animation: check
      };
    });
  },
  provinceMarkers: state => {
    return state.provinceStatus.map(item => {
      return {
        id: item.trackingStatusId,
        username: item.username,
        status: item.status,
        position: {
          lat: item.position.latitude,
          lng: item.position.longitude
        },
        heartRate: item.heartRate
      };
    });
  },
  markerByUserId: (state, getters) => id => {
    return getters.markers.find(marker => marker.id === id);
  },
  infoWindow: state => {
    return state.infoWindow;
  },
  alertCount: state => {
    return state.alertCount;
  },
  showOnlySOS: state => {
    return state.showOnlySOS;
  }
};

const actions = {
  setCenter: ({ commit }, center) => {
    commit("SET_CENTER", center);
  },
  setZoomLevel: ({ commit }, zoomLevel) => {
    commit("SET_ZOOMLEVEL", zoomLevel);
  },
  fetchStatus: ({ commit }) => {
    axios.get("/status").then(response => {
      commit("SET_STATUS", response.data);
    });
  },
  fetchProvinceStatus: ({ commit }) => {
    axios.get("/status").then(response => {
      commit("SET_PROVINCESTATUS", response.data);
    });
  },
  setInfoWindow: ({ commit }, marker) => {
    commit("SET_INFOWINDOW", marker);
  },
  closeInfoWindow: ({ commit }) => {
    commit("CLOSE_INFOWINDOW");
  },
  setAlertCount: ({ commit }) => {
    let result = state.status.filter(item => {
      return item.status !== "normal";
    });
    commit("SET_ALERTCOUNT", result.length);
  },
  setShowOnlySOS: ({ commit }, showOnlySOS) => {
    commit("SET_SHOWONLYSOS", showOnlySOS);
  }
};

const mutations = {
  SET_CENTER: (state, center) => {
    state.center = center;
  },
  SET_ZOOMLEVEL: (state, zoomLevel) => {
    state.zoomLevel = zoomLevel;
  },
  SET_STATUS: (state, status) => {
    state.status = status;
  },
  SET_PROVINCESTATUS: (state, provinceStatus) => {
    state.provinceStatus = provinceStatus;
  },
  SET_INFOWINDOW: (state, marker) => {
    state.infoWindow.marker = marker;
    if (state.infoWindow.currentIndex === marker.id) {
      state.infoWindow.infoWindowOpen = !state.infoWindow.infoWindowOpen;
    } else {
      state.infoWindow.infoWindowOpen = true;
      state.infoWindow.currentIndex = marker.id;
    }
  },
  CLOSE_INFOWINDOW: state => {
    state.infoWindow.infoWindowOpen = false;
  },
  SET_ALERTCOUNT: (state, alertCount) => {
    state.alertCount = alertCount;
  },
  SET_SHOWONLYSOS: (state, showOnlySOS) => {
    state.showOnlySOS = showOnlySOS;
  }
};

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
});
