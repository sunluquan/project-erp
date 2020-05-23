<template>
	<!--出库登记页面-->
	<div id="stockOutRegist">
		<router-view></router-view><br/>
		<span>当前等待登记的出库登记总数：{{count}}</span>
		<br/><br/>
		   <el-table
		      :data="tableData" border style="width: 100%">
			  <el-table-column prop="payId" label="出库单编号">
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
			  <el-table-column prop="" label="登记" >
				  <template slot-scope="scope">
					 <el-button type="text" @click="stockOutRegistView(scope.row.payId)">登记</el-button>
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
				selectRegists(){				//查询所有已调度的出库单
					this.$axios.post("/api/stockOut/selectRegists").then(response=>{
						this.tableData = response.data;
					}).catch(err=>{
			     		alert('请求失败')
					})
				},
				stockOutRegistView(payId){	//跳转到审核页面
					this.$router.push({path:'/index/stockOutRegist.html/stockOutRegistView/'+payId})
				},
				selectCount(){
					this.$axios.post("/api/stockOut/selectRegistCount").then(response=>{
						this.count = response.data;
					}).catch(err=>{
						alert('请求失败')
					})
				}
		},created() {
				this.selectRegists();
				this.selectCount();
	  	}
	}
</script>

<style>
</style>
