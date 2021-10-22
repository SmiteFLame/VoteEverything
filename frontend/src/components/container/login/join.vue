<template>
	<div class="container" align="center">
		<div class="card col-lg-12" align="center" style="width:400px; height:600px">
			<div class="row" style="width:300px; margin: auto">
                <h2>회원가입</h2>
				<div class="card-body  col-sm-6" style="margin:auto">
				    <img src="../../../assets/image/logo.png" style="width:200px; height:90px;  margin-top:-0px; margin-bottom:-25px">
					<form id="joinform" method="post" action="post">
						<div class="form-group" align="left">
							<label for="">아이디</label> <input type="email" class="form-control"
								id="userid" ref="userid" v-model="useremail" placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <input type="password"
								class="form-control" ref="userpwd" id="userpwd" v-model="userpwd">
						</div>
						<div class="form-group" align="left">
							<label for="">비밀번호 확인</label> <input type="password"
								class="form-control" ref="userpwd2" id="userpwd2"  v-model="userpwd2">
						</div>
						<div class="form-group" align="left">
							<label for="">이름</label> <input type="text" class="form-control"
								id="username" ref="username" v-model="username" placeholder="">
						</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-warning"
								@click="createHandler">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
		userpwd:"",
		userpwd2:"",
		username:"",
		useremail:"",
    };
  },
  methods: {
    createHandler() {
      //데이타 검증
      let err = true;
      let msg = "";

      !this.useremail && ((msg = "아이디를 입력해 주세요"), (err = false), this.$refs.useremail.focus());
      err &&
        !this.userpwd &&
        ((msg = "비밀번호을 입력해 주세요"), (err = false), this.$refs.userpwd.focus());
      err &&
        !this.userpwd2 &&
        ((msg = "비밀번호 확인을 입력해 주세요"), (err = false), this.$refs.userpwd2.focus());
      err &&
        this.userpwd != this.userpwd2 &&
        ((msg = "비밀번호가 서로 다릅니다."), (err = false), this.$refs.userpwd2.focus());
      err &&
        !this.username &&
        ((msg = "이름을 입력해 주세요"), (err = false), this.$refs.username.focus());
      err &&
        !this.useremail &&
        ((msg = "내용을 입력해 주세요"), (err = false), this.$refs.useremail.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .post(`http://localhost:8081/auth/user`, {
            email: this.useremail,
            password: this.userpwd,
            status: "ACTIVE",
            name: this.username,

          })
          .then(({data}) => {
              console.log(data)
			  if(data == "SUCCESS"){
			  	alert("회원 가입이 되었습니다.");
			    this.$router.push('/');
			  } else{
				alert("아이디 중복이 되었습니다.");
			  }
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
  }
}
</script>
