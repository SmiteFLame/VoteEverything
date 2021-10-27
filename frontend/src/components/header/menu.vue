<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container-fluid">
      
		  <router-link to="/"><img src="../../assets/image/logo.png" style="width:100px; height:40px;  margin-top:-20px; margin-bottom:-25px"></router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="navbar-nav me-auto">
          <li class="nav-item"><router-link class="nav-link" to="/vote">투표 전체</router-link></li>
				  <li class="nav-item"><router-link class="nav-link" to="/makeVote">투표 만들기</router-link></li>
				  <li class="nav-item"><router-link class="nav-link" to="/community">커뮤니티</router-link></li>
        </ul>
        <ul class="navbar-nav me-right">
          <li class="nav-item"><router-link class="nav-link" to="/login" v-if="username == ''">로그인</router-link></li>
				  <li class="nav-item"><router-link class="nav-link" to="/join" v-if="username == ''">회원가입</router-link></li>
				
				  <li class="nav-item"><a href="#" class="nav-link" @click="logout" v-if="username != ''">로그아웃</a></li>
				  <li class="nav-item"><a href="#" class="nav-link" @click="getUser" v-if="username != ''">{{username}}님 환영합니다</a></li>

				  <li class="nav-item"><router-link class="nav-link" to="/member" v-if="username =='admin'"> 회원 관리 </router-link></li>
        </ul>
      </div>
    </div>
</nav>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex"

import Vue from "vue";
import VueCookies from "vue-cookies";

export default{
  computed:{
    ...mapGetters(["username"])
  },methods:{
		logout(){
      axios
      .post(`http://localhost:8081/auth/logout`)
      .then(res =>{
        if(res.status == 200){
			    alert("로그아웃 완료");
			    this.$store.commit('setUsername', "");
					this.$cookies.set("jwt", res.data.jwt,"0")
			    location.reload();
        }
      })
		}, getUser(){
      axios
      .get(`http://localhost:8081/auth/user`)
      .then(res =>{
        if(res.status == 200){
						if(res.data.status == "ACTIVE"){
							this.$store.commit('setUsername', res.data.name)
						} else{
              console.log("회원 정보 만료")
						}
				}
      })
      .catch(e => {
        console.log(e)
        console.log("회원 정보 만료")
      })
    }
	}, created(){
    Vue.use(VueCookies);
    Vue.$cookies.config("1d");
    console.log(this.$cookies.get("jwt"))
    axios.defaults.headers.common['jwt'] = this.$cookies.get("jwt")
    this.getUser()
	}
}
</script>
