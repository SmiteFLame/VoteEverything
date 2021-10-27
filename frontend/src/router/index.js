import Vue from "vue";
import VueRouter from "vue-router";

import Index from "../components/container/index.vue";

import Vote from "../components/container/vote/vote.vue";
import MakeVote from "../components/container/vote/makeVote.vue";

import Login from "../components/container/login/login.vue";
import Join from "../components/container/login/join.vue";

import Community from "../components/container/community/community.vue";
import CommunityInsert from "../components/container/community/communityinsert.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/vote",
    name: "Vote",
    component: Vote,
  },
  {
    path: "/makeVote",
    name: "MakeVote",
    component: MakeVote,
  },
  {
    path: "/community",
    name: "Community",
    component: Community,
  },
  {
    path: "/communityinsert",
    name: "CommunityInsert",
    component: CommunityInsert,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
