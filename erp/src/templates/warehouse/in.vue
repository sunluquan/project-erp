<template>
	<div id="inventory">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				
				<el-form-item label="产品类别">					
					<el-input  placeholder="产品编号"></el-input>
				</el-form-item>
				
				<el-form-item label="产品编号">
					<el-input placeholder="产品编号"></el-input>
				</el-form-item>
				
				<el-form-item>					
					<el-button native-type="button" v-on:click.prevent="cx()"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>						
		</el-row>
		<!-- 
		 default-sort  默认的排序 是一个对象
		 属性:prop:指定的排序列名 order:排序方式
		 有 ascending or descending
		 -->
		 <!-- :default-sort="sort_regulation" -->
		<el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="stockPage" 
			:default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange">
			<el-table-column label="库存编号" width="106px" prop="stock_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.stockId}}
				</template>
			</el-table-column>
			<el-table-column  label="仓库" width="78px" prop="storeName" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.storeName}}
				</template>
			</el-table-column>
            <el-table-column  label="产品编号" width="145px" prop="product_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.productId}}
				</template>
			</el-table-column>
			<el-table-column  label="产品名称" prop="product_name" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.productName}}
				</template>
			</el-table-column>
			<el-table-column  label="产品种类" prop="kindName" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.kindName}}
				</template>
			</el-table-column>
		    <el-table-column  label="产品用途" prop="kindName" :sortable="custom">
		    	<template slot-scope="scope">
		    		{{scope.row.useName}}
		    	</template>
		    </el-table-column>
			<el-table-column label="商品档次" prop="product_rank" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.productRank}}
				</template>
			</el-table-column>
			<el-table-column label="当前库存量" prop="amount" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.amount}}
				</template>
			</el-table-column>
			<el-table-column label="库存报警" prop="min_amount" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.minamount}}
				</template>
			</el-table-column>					
			<el-table-column label="最大存储量" prop="maxCapacityAmount" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.maxCapacityAmount}}
				</template>
			</el-table-column>
									
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;"
			  background
			  :current-page.sync="params.pageNum"
			  layout="total,prev, pager, next,jumper,slot"
			  :total="stockPage.total"
			  :page-size="stockPage.pageSize"
			  @current-change="currentPageNumChange">
			  <span>&nbsp;&nbsp;总页数:{{stockPage.pages}}</span>
			</el-pagination>
		</div>
	
	</div>
</template>

<script>
	 export default {
	  	name: 'inventory',
	 	 data(){
	 		return {
	 			custom:'custom',
	 			stockPage:{},
	 			keyWords:'',
	 			params:{
	 				pageNum:1,
	 				product_type_id:5,					
	 				orderingRule:{
	 					prop:'sort',
	 					order:'ascending'
	 				}
	 			}
	 		}
	 	},
	 	methods: {
	 		sortChange(sort){
	 			this.stockPage();
	 		},
	 		searchStocks(){			
	 			this.$axios.get('/api/stocks/getStockslist').then(response=>{
					
	 				this.stockPage=response.data;
	 				})
	 		},
	 								
	 		/*cx(){
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
	 			currentPageNumChange(pageNum){//当前页改变时触发
	 			this.params.pageNum=pageNum;
	 			this.searchWarehouse();
	 		}*/
	 	},
	 	created() {
			alert(1);
	 		this.searchStocks();
	 	},computed:{
	 		pageSizes(){
	 			return this.$store.getters.getPageSizes;
	 		}
	 	},filters:{
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
		
	 }
</script>

<style>
</style>
