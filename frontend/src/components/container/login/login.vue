<template>
	<div class="container" align="center">
		<div class="card col-lg-12" align="center" style="width:400px; height:600px">
			<div class="row" style="width:300px; margin: auto">
                <h2>로그인</h2>
				<div class="card-body  col-sm-6" style="margin:auto">
				    <img src="../../../assets/image/logo.png" style="width:200px; height:90px;  margin-top:-0px; margin-bottom:-25px">
					<form id="loginform" method="post" action="post">
						<div class="form-group" align="left">
							<label for="">아이디</label> <input type="text" class="form-control"
								id="email" ref="email" v-model="email" placeholder="">
						</div>
						<br>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <input type="password"
								class="form-control" id="password" ref="password" v-model="password" placeholder=""
								onkeydown="javascript:if(event.keyCode == 13) {login();}">
						</div>
						<br>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-warning"
								@click="login">로그인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
	computed:{
		...mapGetters(["storage"])
	},
	data(){
		return {
			email: "",
			password: "",
			info:""
		};
	},
	methods:{
		setInfo(status, info){
			this.status = status;
			this.info = info;
		},
		login(){
			let err = true;
			let msg = "";
			!this.email && ((msg = "작성자를 입력해 주세요"), (err = false), this.$refs.email.focus());
			err && !this.password && ((msg = "작성자를 입력해 주세요"), (err = false), this.$refs.password.focus());
			if(!err){
				alert(msg);
			} else{
				axios
				.post(`http://localhost:8081/auth/login`, {
					email: this.email,
					password: this.password
				})
          		.then(res => {
					  if(res.status == 200){
						  if(res.data.status == "ACTIVE"){
							  alert("로그인 되었습니다.")
							  this.$store.commit('setUsername', res.data.name)
			    			  this.$router.push('/');
						  } else{
							  alert("회원정보가 만료되었습니다.")
						  }
					  } else{
						  alert("회원정보가 없습니다.")
					  }
          		})
          		.catch(() => {
            		alert("회원정보가 없습니다.");
          		});
			}
		}
	}
}
</script>

