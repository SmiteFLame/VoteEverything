<template>
  <div class="container">
    <h4 class="text-center">커뮤니티</h4>
	  <br><br>
		<div class="row text-center"> 
			<div class="col-sm-1"/>
			<div class="col-sm-2">
			  <select class="form-control" v-model="id_type" id="id_type">
          <option value="" selected>전체 검색</option>
          <option value="communityId">번호</option>
          <option value="title">제목</option>
          <option value="name">작성자</option>
          <option value="content">내용</option>
        </select>
			</div>
      <div class="col-sm-7">
			  <input class="form-control" placeholder="Enter search word" type="text" v-model="id_word"/>
			</div>
      <div class="col-sm-1">
			  <button class="btn btn-secondary" @click="search()">검색</button>
			</div>
      <div class="col-sm-1">
        <button class="btn btn-light" v-if="username" @click="movePage">등록</button>
			</div>
		</div>
	  <br><br>
    <div v-if="communitys.length > 0">
      <table class="table" style = "text-align: center">
        <colgroup>
          <col width="15%" />
          <col width="50%" />
          <col width="15%" />
          <col width="20%" />
        </colgroup>
        <thead>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>날짜</th>
        </thead>
        <tbody>
          <tr v-for="(community, index) in communitys" :key="index">
            <td>{{ community.communityId }}</td>
            <td>
              <router-link style="text-decoration:none; color:black" :to="'communitysearch?no=' + community.no">{{ community.title }}</router-link>
            </td>
            <td v-text="community.name"></td>
            <td style = "text-align: left" v-text="timeChange(community.time)"></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>게시글이 없습니다.</div>
    
  </div>
</template>
<script>
import axios from "axios";
import {mapGetters} from "vuex";
// import moment from "moment";

export default {
  name: "communitylist",
	computed:{
		...mapGetters(["username"])
	},
  data(){
	  return{
		  limit:10,
      offset:0,
      sort_by:"",
      order_by:"",
      id_type:"",
      id_word:"",
		  communitys:[],
	  }
  }, methods:{
	  movePage(){
          this.$router.push("/communityinsert");
	  },
    search(){
      var url = `http://localhost:8808/community?sort-by=${this.sort_by}&order-by=${this.order_by}&limit=${this.limit}&offset=${this.offset}`
      if(this.id_type != ""){
        url += `&${this.id_type}=${this.id_word}`
      }
      axios
		  .get(url)
		  .then((res) =>{
            this.communitys = res.data.content
		  })
		  .catch(() =>{
			  alert("오류 발생!!!");
		  })
    }, timeChange(time){
      return new Date(time).toLocaleString()
    }
  }, created(){
	    this.search()
	  }
  }
</script>
