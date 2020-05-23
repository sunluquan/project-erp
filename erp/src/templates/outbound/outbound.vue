<template>
	<!--制定出库单页面-->
	<div id="outbound">
		<router-view></router-view>
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
			  <el-table-column prop="date" label="出库调度" >
				  <template slot-scope="scope">
					 <el-button type="text" @click="outboundView(scope.row.payId)">出库调度</el-button>
				  </template>
			 </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'outbound',
		 data(){
			 return {
				 tableData:[]
			}
		 },
		methods: {
				selectTag(){				//查询所有审核通过的出库单
					this.$axios.post("/api/stockOut/selectTags").then(response=>{
						this.tableData = response.data;
					}).catch(err=>{
			     		alert('请求失败')
					})
				},
				outboundView(payId){	//跳转到审核页面
					this.$router.push({path:'/index/outbound.html/outboundView/'+payId})
				}
		},created() {
				this.selectTag();
	  	}
	}
</script>

<style>
</style>
