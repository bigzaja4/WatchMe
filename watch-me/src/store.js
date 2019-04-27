import Vue from "vue";
import Vuex from "vuex";
import axios from "../utilitys/axios";

Vue.use(Vuex);

const state = {
  center: {
    lat: 13.7563,
    lng: 100.5018
  },
  zoomLevel: 8,
  status: [],
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
  }
};

const getters = {
  center: state => {
    return state.center;
  },
  zoomLevel: state => {
    return state.zoomLevel;
  },
  status: state => {
    return state.status;
  },
  markers: (state, getters) => {
    return getters.status.map(item => {
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
      console.log(response.data);
      commit("SET_STATUS", response.data);
    });
  },
  setInfoWindow: ({ commit }, marker) => {
    commit("SET_INFOWINDOW", marker);
  },
  closeInfoWindow: ({ commit }) => {
    commit("CLOSE_INFOWINDOW");
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
  }
};

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
});
