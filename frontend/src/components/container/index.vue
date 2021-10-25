<template>
	<div>
		<div class="container mt-3">
  			<h2>Vote</h2>
  			<div class="card" style="width:400px; float:left" v-for="(vote, index) in votes" :key="index">
    			<div class="card-header">{{vote.vote.voteName}}</div>
    			<div class="card-body" style="width:400px" v-for="(voteColumn, index) in vote.voteColumnOutputList" :key="index">
    				{{voteColumn.voteColumn.columnName}}
  				</div>
    			<div class="card-footer">{{vote.vote.email}}</div>
  			</div>
		</div>
	</div>
</template>

<script>
import axios from "axios";

export default{
	data(){
		return{
			votes:[],
			limit:10,
			offset:0
		}
	},
	methods:{
		getVotes(){
			axios
			.get(`http://localhost:8888/votes?limit=${this.limit}&offset=${this.offset}`)
			.then((res) =>{
				this.votes = res.data
				console.log(this.votes)
			})
		}
	}, created(){
		this.getVotes()
	}

}


</script>