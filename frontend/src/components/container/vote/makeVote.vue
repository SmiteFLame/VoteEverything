<template>
	<div class="container" align="center">
		<div class="card col-lg-12" align="center" style="width:400px; height:600px">
			<div class="row" style="width:300px; margin: auto">
                <h2>투표 추가하기</h2>
				<div class="card-body  col-sm-6" style="margin:auto">
					<form id="joinform" method="post" action="post">
						<label for="">투표 이름</label> 
						<div class="form-group" align="left"><input type="text" class="form-control"
								id="votename" ref="votename" v-model="votename" placeholder="">
						</div>
						<label for="">시간</label> 
						<div class="form-group" align="left"><input type="number" class="form-control"
								id="votetime" ref="votetime" v-model="votetime" placeholder="">
						</div>
                        <label for="">투표 목록</label> 
                        <div class="row form-group" align="left"  v-for="index in cnt" :key="index">
                            <div class="col-sm-10">
                            <input type="text" class=" form-control"
								id="votecolumn" ref="votecolumn" v-model="votecolumn[index]" placeholder="">
                            </div>
                                <button type="button" class="col-sm-2 btn btn-light">X</button>
						</div>
                        <div class="form-group" align="center">
							<button type="button" class="btn btn-light"
								@click="insertColumn">항목 추가하기</button>
						</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-warning"
								@click="createHandler">투표 추가하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  data() {
    return {
        votename:"",
        votetime:0,
        votecolumn:[],
        cnt:5

    };
  },
  methods: {
      insertColumn(){
          if(this.cnt == 10){
              alert("최대 10개까지 가능합니다")
          } else{
            this.cnt++
          }
      }
  }
}
</script>
