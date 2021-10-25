<template>
	<div>

		<!-- Best 3 -->
		<div class="container mt-3">
  			<h2>Best 3</h2>
			<div class="row text-center">
  				<div class="col-sm-4 card" style="width:400px;">
    				<div class="card-header">{{votesBest[1].vote.voteName}}</div>
    				<div class="card-body" style="width:400px" v-for="(voteColumn, index) in votesBest[0].voteColumnOutputList" :key="index">
    					{{voteColumn.voteColumn.columnName}}
  					</div>
    				<div class="card-footer">{{votesBest[1].vote.email}}</div>
  				</div>
				<div class="col-sm-4 card" style="width:400px;">
    				<div class="card-header">{{votesBest[0].vote.voteName}}</div>
    				<div class="card-body" style="width:400px" v-for="(voteColumn, index) in votesBest[0].voteColumnOutputList" :key="index">
    					{{voteColumn.voteColumn.columnName}}
  					</div>
    				<div class="card-footer">{{votesBest[0].vote.email}}</div>
  				</div>
				<div class="col-sm-4 card" style="width:400px;">
    				<div class="card-header">{{votesBest[1].vote.voteName}}</div>
    				<div class="card-body" style="width:400px" v-for="(voteColumn, index) in votesBest[1].voteColumnOutputList" :key="index">
    					{{voteColumn.voteColumn.columnName}}
  					</div>
    				<div class="card-footer">{{votesBest[1].vote.email}}</div>
  				</div>
			</div>
		</div>
		<hr>
		<!-- 전체 목록 보기 -->
	</div>
</template>

<script>
import axios from "axios";

export default{
	data(){
		return{
			votesBest:[],
		}
	},
	methods:{
		getBestVotes(){
			axios
			.get(`http://localhost:8888/votes/hottest-votes/3`)
			.then((res) =>{
				this.votesBest = res.data
			})
		}
	}, created(){
		this.getBestVotes()
	}

}


</script>