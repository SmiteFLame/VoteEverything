import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: "",
    userid: "",
  },
  getters: {
    username(state) {
      return state.username;
    },
  },
  mutations: {},
  actions: {},
});
