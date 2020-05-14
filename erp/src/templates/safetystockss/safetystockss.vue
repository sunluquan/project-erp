<template>
	<div id="safetystockss">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				
				<el-form-item label="产品建档日期">
					<el-input  placeholder="产品建档日期"></el-input>
				</el-form-item>
				
				<el-form-item>					
					<el-button native-type="button" v-on:click.prevent="cx()"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>						
		</el-row> 
		
		   <el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="productPage"  border
		   :default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange">
		      <el-table-column prop="product_id" label="产品编号" :sortable="custom" >
				  <template slot-scope="scope">
				  	{{scope.row.productId}}
				  </template>
		      </el-table-column>
		      <el-table-column prop="product_name" label="产品名称" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.productName}}
				  </template>
		      </el-table-column>
		      <el-table-column prop="useName" label="用途类型" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.useName}}
				  </template>
		      </el-table-column>
			  <el-table-column prop="product_rank" label="档次级别" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.productRank}}
				  </template>
			  </el-table-column>
			  <el-table-column prop="kindName" label="分类" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.kindName}}
				  </template>
			  </el-table-column>
			  <el-table-column label="操作" width="120px">
			  	<template slot-scope="scope">
			  		<el-button type="text" @click="safetysheet(scope.row.productId)">制定</el-button>
			  	</template>
			  </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'safetystockss',
		 data(){
			 return {				
			 	custom:'custom',
			 	productPage:[],		 	
			 	params:{
			 		pageNum:1,					
			 		product_kind_id:5,					
			 		orderingRule:{
			 			prop:'sort',
			 			order:'ascending'
			 		}
			 	}
			 }
	  },
	  methods: {
	  	 		sortChange(sort){
	  	 			this.selectProList();
	  	 			this.params.orderingRule.prop=sort.prop;
	  	 			this.params.orderingRule.order=sort.order;
	  	 			
	  	 		},
	  	 		selectProList(){			
	  	 			this.$axios.post('/api/product/selectProList').then(response=>{
	  	 				this.productPage=response.data;
						
	  	 				})
	  	 		},	
			   safetysheet(productId){
					this.$router.push({path: '/index/safetystockss.html/safetysheet/'+productId})
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
	  	 		//},
				*/				
	  	 	currentPageNumChange(pageNum){//当前页改变时触发
	  	 		this.params.pageNum=pageNum;
	  	 		this.selectProList();
	  	 	},
			},
	  	 	created() {
	  	 		this.selectProList();
	  	 	},
			computed:{
	  	 		pageSizes(){
	  	 			return this.$store.getters.getPageSizes;
	  	 		},
	  	 	filters:{
	  	 		whetherShow(obj,property,message){
	  	 			if(message==undefined||message=='')
	  	 				message="无"
	  	 			if(obj==''||obj==null){
	  	 				return message;
	  	 			}
	  	 			if(obj[property]==''||obj[property]==null)
	  	 				return message;
	  	 			return obj[property];
	  	 		}
	  	 	},
	},
	
	}
</script>

<style>
</style>
