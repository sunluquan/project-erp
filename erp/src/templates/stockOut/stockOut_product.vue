<template>
	<!--出库添加产品模态框-->
	<el-dialog id="stockOut_product" :before-close="close" width="55%" title="新增产品" :visible.sync="insert_gatherproduct_dialog_visible">
		 <el-table :data="changePage" border :header-cell-style="{background:'whitesmoke'}"  :border="true" :highlight-current-row="true">		 			   		 	
		    <el-table-column prop="productid" label="产品编号"  width="200px">
		 				  <template slot-scope="scope">
		 				  	<span>{{ scope.row.productId }}</span>
		 				  </template>
		    </el-table-column>
		    <el-table-column prop="productname" label="产品名称" >
		 				  <template slot-scope="scope">
		 				  	<span>{{ scope.row.productName }}</span>
		 				  </template>
		    </el-table-column>
		    <el-table-column prop="useName" label="用途类型" width="100px">
		 				  <template slot-scope="scope">
		 				  	<span>{{ scope.row.useName }}</span>
		 				  </template>
		    </el-table-column>
		 	<el-table-column prop="amounUnit" label="单位" width="100px">
		 			 				  <template slot-scope="scope">
		 			 				  	<span>{{ scope.row.amounUnit }}</span>
		 			 				  </template>
		 	</el-table-column>		  		 			  
		 	<el-table-column prop="realCostPrice" label="成本单价" width="140px">
		 			 				  <template slot-scope="scope">
		 			 				  	<span>{{ scope.row.costPrice }}</span>
		 			 				  </template>
		 	</el-table-column>		  
		 			  <el-table-column prop="date" label="出库" width="90px">
		 				  <template slot-scope="scope">
		 				  <el-link type="primary" @click="insert(scope.row)">出库</el-link>
		 				  </template>
		 			  </el-table-column>
		  </el-table>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close()">取 消</el-button>
			<el-button type="button" @click="close()">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name:'gatherproduct',
		data() {			
			return {				
				labelPosition: 'right',				       
				insert_gatherproduct_dialog_visible: false,
				changePage:{},
				isadmin: 3,				
			}
		},
		created() {
			this.insert_gatherproduct_dialog_visible = true;
			this.searchChange();
		},
		methods: {
			close() {
				this.insert_gatherproduct_dialog_visible = false;
				this.$router.go(-1)
			},			
             searchChange(){
				this.$axios.post('api/stock/getStocks').then(response => {
								this.changePage = response.data;
				}).catch(err=>{
			     			alert('请求失败')
			    })
			},
			insert(product){
				console.log(product);
				this.$emit('insert',product);
			}
		},
	}
</script>

<style>
</style>
