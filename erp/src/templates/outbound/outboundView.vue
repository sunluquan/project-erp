<template>
	<!--出库调度单-->
	<el-dialog id="outboundView" :before-close="close" width="80%" :visible.sync="dialog_visible">
		<div id="outboundView">
				<router-view></router-view>
				<div id="body" :data="spay">
					<p style="color: #000000;text-align: center;font-size: 18px;">出库调度单</p>
					<el-form :inline="true" style="padding-left: 200px;" :model="spay" label-width="95px">
						&nbsp;&nbsp;
						<el-form-item label="出库单编号:" prop="payId">
							<span>{{spay.payId}}</span>
						</el-form-item>
						<el-form-item label="">
							<span>                      </span>
						</el-form-item><br/>
						<el-form-item label="出库人:" prop="storer">
							<span>{{spay.storer}}</span>
						</el-form-item>
						<el-form-item label="出库理由:" prop="reasonexact"  style="padding-left:200px;">
							<span>{{spay.reasonexact}}</span>
						</el-form-item>
						<el-table ref="multipleTable" :data="spay.spayDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 75%;margin-left: -20px;"
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
							<el-table-column prop="costPrice" label="调度状态">
								<template slot-scope="scope">
									<span style="color:red">{{scope.row.payTag}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="costPrice" label="调度">
								<template slot-scope="scope">
									<el-button type="text" @click="outboudResult(spay.payId,scope.row.productId)">调度</el-button>
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
						<el-form-item label="备注:" prop="remark">
							<span>{{spay.remark}}</span>
						</el-form-item>
						<el-form-item style="padding-left:420px;">
							<el-button type="primary" @click="aksure()">确定调度</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
	</el-dialog>
</template>
	
<script>
		export default{
			name:'outboundView',
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
				},check(){		//判断是否已经全部调度完
					let index = this.spay.spayDetails.findIndex(item=>item.payTag!="已调度")
					return index>=0?true:false
				},
				aksure(){
					if(this.check()){		//如果有未调度完的产品
						this.$message.error('你还有未调度的产品')
						return;
					}
					this.$axios.post('/api/stockOut/aksure',this.spay).then(response => {
							let responseData = response.data;
							if (response.statusCord == 200) {
								this.$message({
									showClose: true,
									message: '提交成功!',
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
						this.dialog_visible = false;
					})
				},
				outboudResult(payId,productId){		//跳转到调度详细页面
					this.$router.push({path:'/index/outbound.html/outboundResult/'+payId+'/'+productId})
				},
				selectByPayId(){					//获取出库单编号，查询当前			
					let payId=this.$route.params[Object.keys(this.$route.params)[0]];
					this.$axios.post("/api/stockOut/selectByPayId/"+payId).then(response=>{
						this.spay = response.data;
					}).catch(err=>{
						alert('请求失败')
					})
				},
				insert(){							//通过单选框提交审核状态
					if(this.radio==1){		//未通过
						this.$axios.post('/api/stockOut/upNotStatus',this.spay).then(response => {
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
					}else{					//通过
						this.$axios.post('/api/stockOut/updateStatus',this.spay).then(response => {
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
			}
		},
		created(){
			this.dialog_visible = true;
			this.selectByPayId();
		},
	}
	</script>

<style>
	#body{
		height: 550px;
	}
</style>
