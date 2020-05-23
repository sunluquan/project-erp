<template>
	<!--出库预览模态框-->
 <el-dialog id="stockOut_product" :before-close="close" width="80%" title="" style="text-align: center;" :visible.sync="dialog_visible">
	<div id="stockOutPreview">
			<div id="body">
				<p style="color: #000000;text-align: center;font-size: 18px;">出库申请预览</p>
				<div style="margin-left:570px;">
					<el-button type="success"  v-on:click="insert()" plain>提交</el-button>
				</div><br />
				<el-form :inline="true" :model="spay" :label-position="labelPosition" label-width="90px">
					<el-form-item label="出库人:" prop="product_id">
						<el-input class="MyClass" v-model="spay.storer"></el-input>
					</el-form-item>
					<el-form-item label="出库理由:" prop="product_name">
						<el-input class="MyClass" v-model="spay.reasonexact"></el-input>
					</el-form-item>
					<el-table ref="multipleTable" :data="spay.spayDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 65%;margin-left: 160px;"
					 tooltip-effect="dark" @selection-change="handleSelectionChange">
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
								<el-input class="MyClasssum" size="mini" v-model="scope.row.amount">
								</el-input>
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
					<el-form-item label="总件数:" style="padding-right: 120px;" prop="register">
						<span>{{total_js}}</span>
					</el-form-item>
					<el-form-item label="总金额:" prop="registertime">
						<span>{{total_price}}</span>
					</el-form-item><br />
					<el-form-item label="登记人:" style="padding-right: 120px;" prop="register">
						<span>{{register}}</span>
					</el-form-item>
					<el-form-item label="登记时间:" prop="registertime">
						<el-date-picker type="date" placeholder="选择日期" size="small">
						</el-date-picker>
					</el-form-item>
					<el-form-item label="备注:">
						<el-input type="textarea" style="width:500px;height:70px" v-model="spay.remark"></el-input>
					</el-form-item>
				</el-form>
			</div>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'stockout',
		model:{
			prop:'dialog_visible',
			event:'update'
		},
		props:{
			dialog_visible:{
				type:Boolean
			},spay:{
				type:Object,
				default:()=>({})
			}
		},
		methods: {
			close(){
				this.$emit('update',false);
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			insert() { 			//点击提交
				this.$axios.post('/api/stockOut/addStockOut',this.spay).then(response => {
					let responseData = response.data;
					if (response.statusCord == 200) {
						this.$message({
							showClose: true,
							message: '提交成功!',
							message: '需要复核!!',
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
			},
		},
		computed: {
			register() {
				return this.$store.getters.getActiveUser.user.uname
			},
			total_price() {		//计算总金额
				var sum = 0;
				this.spay.spayDetails.forEach(item => {
					let amount = item.amount? item.amount: 0
					let total = parseFloat(item.costPrice) * parseFloat(amount)
					sum += total;
				})
				this.spay.costPriceSum=sum;
				return sum;
			},
			total_js(){		//计算总件数
				var sum = 0;
				this.spay.spayDetails.forEach(item => {
					let amount = item.amount? item.amount: 0
					let total = parseFloat(amount)
					sum += total;
					item.amountSum=sum;
				})
				this.spay.amountSum=sum;
				return sum;
			}
		}
	}
</script>
