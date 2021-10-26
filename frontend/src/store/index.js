import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    storage: window.sessionStorage,
    username: "",
    jwt: "",
  },
  getters: {
    username(state) {
      return state.username;
    },
    jwt(state) {
      return state.jwt;
    },
  },
  mutations: {
    setUsername(state, payload) {
      state.username = payload;
    },
    setjwt(state, payload) {
      state.jwt = payload;
    },
  },
  actions: {},
});
