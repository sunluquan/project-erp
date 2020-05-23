<template>
	<div id="reserve">
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
		
		<el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="stockPage" 
			:default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange" :header-cell-style="{background:'whitesmoke'}">						
            <el-table-column  label="产品编号" width="175px" prop="product_id">
				<template slot-scope="scope">
					{{scope.row.productId}}
				</template>
			</el-table-column>
			<el-table-column  label="产品名称" width="160px" prop="product_name" >
				<template slot-scope="scope">
					{{scope.row.productName}}
				</template>
			</el-table-column>
			<!-- <el-table-column  label="产品种类" prop="kindName" > 
				<template slot-scope="scope">
					{{scope.row.kindName}}
				</template>
			</el-table-column>-->
		    <el-table-column  label="产品用途"  width="100px" prop="kindName" >
		    	<template slot-scope="scope">
		    		{{scope.row.useName}}
		    	</template>
		    </el-table-column>
			<!-- <el-table-column label="商品档次" prop="product_rank" > 
				<template slot-scope="scope">
					{{scope.row.product_rank}}
				</template>
			</el-table-column>-->
			<el-table-column label="成本" prop="realCostPrice"  width="100px">
				<template slot-scope="scope">
					{{scope.row.realCostPrice}}
				</template>
			</el-table-column>
			<el-table-column label="当前库存" prop="amount" >
				<template slot-scope="scope">
					{{scope.row.amount}}
				</template>
			</el-table-column>
			<el-table-column label="当前入库量" prop="paidAmount">
				<template slot-scope="scope">
					{{scope.row.paidAmount}}
				</template>
			</el-table-column>
			<el-table-column label="库存报警" prop="minamount">
				<template slot-scope="scope">
					{{scope.row.minamount}}
				</template>
			</el-table-column>					
			<el-table-column label="最大存储量" prop="maxCapacityAmount">
				<template slot-scope="scope">
					{{scope.row.maxCapacityAmount}}
				</template>
			</el-table-column>
				<el-table-column  label="仓库" width="90px" prop="storeName">
					<template slot-scope="scope">
						{{scope.row.storeName}}
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
	  	name: 'reserve',
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
