<template>
	<!--申请出库查询具体页面-->
	<el-dialog id="stockOutSelectAll" :before-close="close" width="80%" :visible.sync="dialog_visible">
		<div id="stockOutSelectAll" >
				<div id="body" :data="spay">
					<p style="color: #000000;text-align: center;font-size: 18px;">出库单详情</p>
					<el-form :inline="true" style="padding-left: 200px;" :model="spay" label-width="90px">
						<el-form-item label="出库人:" prop="storer">
							<span>{{spay.storer}}</span>
						</el-form-item>
						<el-form-item label="出库理由:" prop="reasonexact" style="padding-left:250px;">
							<span>{{spay.reasonexact}}</span>
						</el-form-item>
						<el-table ref="multipleTable" :data="spay.spayDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 65%;margin-left: 60px;"
						 tooltip-effect="dark" >
							<el-table-column prop="productName" label="产品名称">
								<template slot-scope="scope">
									<span>{{scope.row.productName}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="productId" label="产品编号">
								<template slot-scope="scope">
									<span>{{scope.row.productId}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="address" label="数量">
								<template slot-scope="scope">
									<span>{{scope.row.amount}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="amounUnit" label="单位">
								<template slot-scope="scope">
									<span>{{scope.row.amounUnit}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="costPrice" label="成本单价">
								<template slot-scope="scope">
									<span>{{scope.row.costPrice}}</span>
								</template>
							</el-table-column>
						</el-table>
						<el-form-item label="总件数:" style="padding-right: 120px;" prop="amountSum">
							<template slot-scope="scope">
								<span>{{spay.amountSum}}</span>
							</template>
						</el-form-item>
						<el-form-item label="总金额:" prop="costPriceSum">
							<template slot-scope="scope">
								<span>{{spay.costPriceSum}}</span>
							</template>
						</el-form-item><br />
						<el-form-item label="登记人:" style="padding-right: 120px;" prop="register">
							<span>{{spay.storer}}</span>
						</el-form-item>
						<el-form-item label="登记时间:" prop="registertime">
							<span>{{spay.registerTime}}</span>
						</el-form-item><br/>
						<el-form-item label="审核人:" prop="checker">
							<span>{{spay.checker}}</span>
						</el-form-item>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<el-form-item label="审核时间:" prop="checkTime">
							<span>{{spay.checkTime}}</span>
						</el-form-item><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<el-form-item label="未通过理由:" prop="checkNotReason">
							<span>{{spay.checkNotReason}}</span>
						</el-form-item>
						<el-form-item label="备注:" prop="remark" style="padding-left: 100px;">
							<span>{{spay.remark}}</span>
						</el-form-item>
					</el-form>
				</div>
			</div>
	</el-dialog>
</template>
	
<script>
		export default{
			name:'stockOutSelectAll',
			data(){
				return{
					dialog_visible:false,
					spay:{},
					radio: '1'
				}
			},
			methods:{
				close(){
					this.$router.go(-1);
					this.dialog_visible = false;
				},
				selectByPayId(){		//获取出库单编号，查询当前			
					let payId=this.$route.params[Object.keys(this.$route.params)[0]];
					this.$axios.post("/api/stockOut/selectByPayId/"+payId).then(response=>{
						this.spay = response.data;
					}).catch(err=>{
						alert('请求失败')
					})
				}
		},
		created(){
			this.dialog_visible = true;
			this.selectByPayId();
		}
	}
	</script>

<style>
</style>
