<template>
	<div id="safetystockchange">
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
		
		   <el-table :data="changePage" border style="width: 100%" :border="true" :highlight-current-row="true">
			   
			  <el-table-column prop="safetystockId" width="235px" label="配置单编号" :sortable="custom" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.safetystockId }}</span>
				  </template>
			  </el-table-column>
		      <el-table-column prop="productid" label="产品编号" width="180px" :sortable="custom">
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.productId }}</span>
				  </template>
		      </el-table-column>
		      <el-table-column prop="productname" label="产品名称" :sortable="custom" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.product_name }}</span>
				  </template>
		      </el-table-column>
		      <el-table-column prop="useName" label="用途类型" :sortable="custom">
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
			  <el-table-column prop="date" label="变更" width="100px">
				  <template slot-scope="scope">
				  <el-link type="primary" @click="change(scope.row.safetystockId)">变更</el-link>
				  </template>
			  </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'safetystockchange',
		 data(){
			 return {				 
			 	custom:'custom',
			 	changePage:{},		 	
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
	  	 		this.searchChange();
	  	 	},
	  methods: {
	  	 		sortChange(sort){
	  	 			this.searchreview();
	  	 			this.params.orderingRule.prop=sort.prop;
	  	 			this.params.orderingRule.order=sort.order;
	  	 			
	  	 		},
				  searchChange(){
						this.$axios.post('api/safetystock/selectStockChange').then(response => {
								this.changePage = response.data;
					    }).catch(err=>{
			     			alert('请求失败')
			          })
					},
					change(safetystockId){
				     	this.$router.push({path:'/index/safetystockchange.html/updatestockchange/'+safetystockId})
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
	  	 	
	},filters:{	  	 		
    }
	}
</script>

<style>
</style>
