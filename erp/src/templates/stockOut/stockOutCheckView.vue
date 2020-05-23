<template>
	<!--申请出库审核的具体页面-->
	<el-dialog id="stockOutCheckView" :before-close="close" width="80%" :visible.sync="dialog_visible">
		<div id="stockOutCheckView">
			<div id="body" :data="spay">
				<p style="color: #000000;text-align: center;font-size: 18px;">出库申请审核</p>
				<div style="margin-left:600px;">
					<template>
						<!--审核未通过的理由弹框-->
						<el-radio v-model="radio" @click="open" label="1">未通过</el-radio>
						<el-radio v-model="radio" label="2">通过</el-radio>
					</template>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<el-button type="success" @click="insert()" plain>提交</el-button>
				</div><br/>
				<el-form :inline="true" style="padding-left: 200px;" :model="spay" label-width="90px">
					<el-form-item label="出库人:" prop="storer" style="padding-left: 20px;">
						<span>{{spay.storer}}</span>
					</el-form-item>
					<el-form-item label="出库理由:" prop="reasonexact" style="padding-left:200px;">
						<span>{{spay.reasonexact}}</span>
					</el-form-item>
					<el-table ref="multipleTable" :data="spay.spayDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 65%;margin-left: 60px;"
					 tooltip-effect="dark">
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
					<el-form-item label="总金额:" prop="costPriceSum" style="padding-left: 20px;"> 
						<template slot-scope="scope">
							<span>{{spay.costPriceSum}}</span>
						</template>
					</el-form-item><br />
					<el-form-item label="登记人:" style="padding-right: 120px;" prop="register">
						<span>{{spay.storer}}</span>
					</el-form-item>
					<el-form-item label="登记时间:" prop="registertime" style="padding-left: 20px;">
						<span>{{spay.registerTime}}</span>
					</el-form-item><br />
					<el-form-item label="备注:" prop="remark">
						<span>{{spay.remark}}</span>
					</el-form-item>

				</el-form>
			</div>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'stockOutCheckView',
		data() {
			return {
				dialog_visible: false,
				spay: {},
				radio: '1'
			}
		},
		methods: {
			close() {
				this.$router.go(-1);
				this.dialog_visible = false;
			},
			selectByPayId() { //获取出库单编号，查询当前			
				let payId = this.$route.params[Object.keys(this.$route.params)[0]];
				this.$axios.post("/api/stockOut/selectByPayId/" + payId).then(response => {
					this.spay = response.data;
				}).catch(err => {
					alert('请求失败')
				})
			},
			insert() { 			//通过单选框提交审核状态
				if (this.radio == 1) { //未通过
					this.open().then(message => {
						this.spay.checkNotReason=message;
						this.$axios.post('/api/stockOut/upNotStatus', this.spay).then(response => {
							let responseData = response.data;
							if (response.statusCord == 200) {
								this.$message({
									showClose: true,
									message: '提交成功!',
									message: '审核未通过!!',
									type: 'success',
									duration: 2000
								});
								this.close();
							} else {
								this.$message({
									showClose: true,
									message: responseData.message,
									type: 'error',
									duration: 2000
								});
							}
						})
					})
				} else { //通过
					this.$axios.post('/api/stockOut/updateStatus', this.spay).then(response => {
						let responseData = response.data;
						if (response.statusCord == 200) {
							this.$message({
								showClose: true,
								message: '提交成功!',
								message: '审核成功!!',
								type: 'success',
								duration: 2000
							});
							this.close();
						} else {
							this.$message({
								showClose: true,
								message: responseData.message,
								type: 'error',
								duration: 2000
							});
						}
					})
				}
			},
			open() { //审核未通过的弹框
				return new Promise((resolve, reject) => {
					this.$prompt('请输入未通过的理由', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消'
					}).then(({
						value
					}) => {
						resolve(value)
					}).catch(() => {
						resolve('')
					});
				})

			}
		},
		created() {
			this.dialog_visible = true;
			this.selectByPayId();
		}
	}
</script>

<style>
</style>
