<template>
	<!--出库申请审核页面-->
	<div id="stockOutCheck">
		<router-view></router-view><br/>
		<span>当前等待审核的出库单总数：{{count}}</span>
		<br/><br/>
		   <el-table
		      :data="tableData" border style="width: 100%">
			  <el-table-column prop="payId" label="申请单编号">
				<template slot-scope="scope">
					<span>{{scope.row.payId}}</span>
				</template>
			  </el-table-column>
		      <el-table-column prop="reasonexact" label="出库理由" >
				<template slot-scope="scope">
					<span>{{scope.row.reasonexact}}</span>
				</template>	
		      </el-table-column>
		      <el-table-column prop="registerTime" label="登记时间" >
				<template slot-scope="scope">
					<span>{{scope.row.registerTime}}</span>
				</template>
		      </el-table-column>
		      <el-table-column prop="amountSum" label="总件数">
				  <template slot-scope="scope">
				  	<span>{{scope.row.amountSum}}</span>
				  </template>
		      </el-table-column>
			  <el-table-column prop="costPriceSum" label="总金额">
				  <template slot-scope="scope">
				  	<span>{{scope.row.costPriceSum}}</span>
				  </template>
			  </el-table-column>
			  <el-table-column prop="date" label="审核" >
				  <template slot-scope="scope">
					 <el-button type="text" @click="stockOutCheckView(scope.row.payId)">审核</el-button>
				  </template>
			 </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'stockOutCheck',
		 data(){
			 return {
				 tableData:[],
				 count:'0'
			}
		 },
		methods: {
				selectTag(){				//查询所有未审核的出库单
					this.$axios.post("/api/stockOut/selectByTag").then(response=>{
						this.tableData = response.data;
					}).catch(err=>{
			     		alert('请求失败')
					})
				},
				stockOutCheckView(payId){	//跳转到审核页面
					this.$router.push({path:'/index/stockOutCheck.html/stockOutCheckView/'+payId})
				},
				selectCount(){
					this.$axios.post("/api/stockOut/selectCount").then(response=>{
						this.count = response.data;
					}).catch(err=>{
						alert('请求失败')
					})
				}
		},created() {
				this.selectTag();
				this.selectCount();
	  	}
	}
</script>

<style>
</style>
