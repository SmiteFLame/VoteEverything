<template>
  <div>
    <nav class="navbar navbar-expand-sm bg-light navbar-light">
      <div class="container-fluid">
        <ul class="navbar-nav my-2 my-lg-0">
				  <li class="nav-item"><router-link class="nav-link" to="/login" v-if="username == ''">로그인</router-link></li>
				  <li class="nav-item"><router-link class="nav-link" to="/join" v-if="username == ''">회원가입</router-link></li>
				
				  <li class="nav-item"><a href="#" class="nav-link" @click="logout" v-if="username != ''">로그아웃</a></li>
				  <li class="nav-item"><router-link class="nav-link" to="/join" v-if="username != ''">{{username}}님 환영합니다</router-link></li>

				  <li class="nav-item"><router-link class="nav-link" to="/member" v-if="username =='admin'"> 회원 관리 </router-link></li>		
			  </ul>
      </div>
    </nav>
  </div>
</template>

<script>
import {mapGetters} from "vuex"

export default{
  computed:{
    ...mapGetters(["username"])
  }
}

</script>
