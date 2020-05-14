<template>
	<div id="review">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				
				<el-form-item label="产品名称:">					
					<el-input v-model="params.keyWord" placeholder="产品建档日期"></el-input>					
				</el-form-item>
				<el-form-item label="登记日期:">
					<el-input v-model="params.keyWord" placeholder="产品建档日期"></el-input>					
				</el-form-item>							
				<el-form-item>					
					<el-button native-type="button" @click="searchreview"   type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>						
		</el-row> 
		
		   <el-table :data="reviewPage" border style="width: 100%" :border="true" :highlight-current-row="true">
			   
			  <el-table-column prop="safetystockId" width="230px" label="配置单编号" :sortable="custom" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.safetystockId }}</span>
				  </template>
			  </el-table-column>
		      <el-table-column prop="productid" label="产品编号" :sortable="custom">
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.productId }}</span>
				  </template>
		      </el-table-column>
		      <el-table-column prop="productname" label="产品名称" :sortable="custom" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.productName }}</span>
				  </template>
		      </el-table-column>
		      <el-table-column prop="useName" label="用途类型" width="130px" :sortable="custom">
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.useName }}</span>
				  </template>
		      </el-table-column>			  
			  <el-table-column prop="kindName" label="分类">
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.kindName }}</span>
				  </template>
			  </el-table-column>
			  <el-table-column prop="registertime" label="登记时间" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.registertime }}</span>
				  </template>
			  </el-table-column>
			  <el-table-column prop="date" label="审核" width="100px">
				  <template slot-scope="scope">
				  <el-button type="text" @click="reviewsheet(scope.row.id)">复核</el-button>
				  </template>
			  </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'review',
		 data(){
			 return {				 
			 	custom:'custom',
			 	reviewPage:{},		 	
			 	params:{
			 		pageNum:1,
					keyWord:'',
			 		product_type_id:5,					
			 		orderingRule:{
			 			prop:'sort',
			 			order:'ascending'
			 		}
			 	}
			 }
	  },created() {
	  	 		this.searchreview();
	  	 	},
	  methods: {
	  	 		sortChange(sort){
	  	 			this.searchreview();
	  	 			this.params.orderingRule.prop=sort.prop;
	  	 			this.params.orderingRule.order=sort.order;
	  	 			
	  	 		},
				  searchreview(){
						this.$axios.post('api/safetystock/search').then(response => {
								this.reviewPage = response.data;
					    }).catch(err=>{
			     			alert('请求失败')
			          })
					},
					reviewsheet(id){
				     	this.$router.push({path:'/index/review.html/reviewsheet/'+id})
					},
				/*	  	 								
	  	 		//cx(){
	  	 		//	if(this.keyWords!=''){
	  	 		//						 let storeName=this.keyWords;
	  	 			//					this.$axios.post('/api/stock/selectByName/'+storeName).then(response=>{
	  	 		//							alert('神经病');
	  	 		//								 this.warehousePage=response.data;										 
	  //
	  	 		//									
	  	 		//								 	this.$message({
	  //
	  	 		//								 	})
	  	 										 																			 
	  	 		//						 })
	  	 		//					 }else{
	  	 		//					   this.searchWarehouse();
	  	 		//					 }
	  	 		//},*/
	  	        
	  	 	
			//computed:{
	  	 		//pageSizes(){
	  	 		//	return this.$store.getters.getPageSizes;
	  	 		//}
	  	 	
	}
	}
</script>

<style>
</style>
