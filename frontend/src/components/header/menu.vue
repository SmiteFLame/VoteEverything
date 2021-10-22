<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container-fluid">
      
		  <router-link to="/"><img src="../../assets/image/logo.png" style="width:100px; height:40px;  margin-top:-20px; margin-bottom:-25px"></router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="navbar-nav me-auto">
        </ul>
        <ul class="navbar-nav me-right">
          <li class="nav-item"><router-link class="nav-link" to="/login" v-if="username == ''">로그인</router-link></li>
				  <li class="nav-item"><router-link class="nav-link" to="/join" v-if="username == ''">회원가입</router-link></li>
				
				  <li class="nav-item"><a href="#" class="nav-link" @click="logout" v-if="username != ''">로그아웃</a></li>
				  <li class="nav-item"><router-link class="nav-link" to="/join" v-if="username != ''">{{username}}님 환영합니다</router-link></li>

				  <li class="nav-item"><router-link class="nav-link" to="/member" v-if="username =='admin'"> 회원 관리 </router-link></li>
        </ul>
      </div>
    </div>
</nav>
</template>

<script>
import {mapGetters} from "vuex"


export default{
  computed:{
    ...mapGetters(["username"])
  }
}
</script>
