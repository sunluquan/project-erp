<template>
	<el-dialog :before-close="close" title="工序物料报损报溢修改" append-to-body :visible.sync="process_material_dialog_visible">
		<!-- 生产工序所需的物料集合 -->
		{{productProfitLoss.production_process_id}}
		<view-process-material ref="view_process_material_ref" :production_process_id="productProfitLoss.production_process_id"
		 @set_process_materials="setProcessMaterials">
		</view-process-material>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="制定单编号: ">
					<span>{{productProfitLoss.manufacture_id}}</span>
				</el-form-item>
				<el-form-item label="工序名称: ">
					<span>{{productProfitLoss.process_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{productProfitLoss.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{productProfitLoss.product_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item style="text-align: right;">
				</el-form-item>
				<el-form-item style="text-align: right;">
					<el-button type="success" size="small" @click="view_process_material">查看物料</el-button>
				</el-form-item>
			</el-form>
		</div>
		<br />
		<el-table style="text-align: center;width: 100%" :border="true" :data="productProfitLoss.productProfitLossDetails"
		 :highlight-current-row="true">
			<el-table-column label="物料编号">
				<template slot-scope="scope" prop="product_id">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="物料名称" prop="product_name">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="单价" prop="cost_price">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<!-- <el-table-column label="设计数量" prop="amount">
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="已用数量" prop="real_amount">
				<template slot-scope="scope">
					{{scope.row.real_amount|formNumerical1()}}
				</template>
			</el-table-column>
			<el-table-column label="退领数量" prop="real_amount">
				<template slot-scope="scope">
					{{scope.row.renew_amount|formNumerical1()}}
				</template>
			</el-table-column> -->
			<el-table-column label="损益数量" prop="profit_loss_amount">
				<template slot-scope="scope">
					<number v-model="scope.row.profit_loss_amount" :min='-((scope.row.amount+scope.row.renew_amount)-scope.row.real_amount)'></number>
				</template>
			</el-table-column>
			<el-table-column label="合计">
				<template slot-scope="scope">
					{{scope.row.profit_loss_amount|total(scope.row.cost_price,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button type="danger" @click="delete_material(scope.row.product_id)" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{productProfitLoss.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<el-input type="textarea" autosize v-model="productProfitLoss.remark"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">关 闭</el-button>
			<el-button type="success" @click="submitUpdate">提 交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import view_process_material from '@/components/profit_and_loss/view_process_material.vue'
	import number from '@/components/number.vue'
	export default {
		name: 'update_profit_and_loss',
		data() {
			return {
				productProfitLoss: {},
				process_material_dialog_visible: false
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			setProcessMaterials(data) {
				let productProfitLossDetails = this.productProfitLoss.productProfitLossDetails
				let index = productProfitLossDetails.findIndex(item => item.product_id == data.product_id)
				if (index >= 0) {
					productProfitLossDetails[index].profit_loss_amount += 1;
				} else {
					let productProfitLossDetail = {
						product_id: data.product_id,
						product_name: data.product_name,
						cost_price: data.cost_price,
						profit_loss_amount: 1,
					}
					productProfitLossDetails.push(productProfitLossDetail)
				}
			},
			delete_material(product_id) {
				let productProfitLossDetails = this.productProfitLoss.productProfitLossDetails
				let index = productProfitLossDetails.findIndex(item => item.product_id == product_id)
				productProfitLossDetails.splice(index, 1)
			},
			getProductProfitLossById() {
				let id = this.$route.params[Object.keys(this.$route.params)[0]]
				this.$axios.get(`/api/productProfitLoss/byIdAll/${id}`)
					.then(response => {
						this.productProfitLoss = response.data
					})
			},
			view_process_material() {
				this.$refs.view_process_material_ref.open()
			},
			submitUpdate() {
				this.$axios.post('/api/productProfitLoss/update', this.productProfitLoss)
					.then(response => {
						if (response.statusCord == 200) {
							this.$message.success('提交成功 等待审核!')
							this.update();
							this.close()
						} else {
							this.$message.error(response.data)
						}
					})
			},
			close() {
				this.process_material_dialog_visible = false
				this.$router.go(-1)
			},
			open() {
				this.process_material_dialog_visible = true
			}
		},
		created() {
			this.getProductProfitLossById()
			this.open()
		},
		components: {
			number,
			'view-process-material': view_process_material
		},
		filters: {
			whetherShow1(data, suffix) {
				if (suffix) {
					return parseFloat(data).toFixed(2) + suffix;
				}
				return parseFloat(data).toFixed(2)
			},
			whetherShow(obj, property, message) {
				if (message == undefined || message == '')
					message = "无"
				if (obj == '' || obj == null) {
					return message;
				}
				if (obj[property] == '' || obj[property] == null)
					return message;
				return obj[property];
			},
			formNumerical1(numerical, suffix) {
				if (!suffix) {
					suffix = ''
				}
				if (numerical) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return 0.00
			},
			formNumerical(numerical, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return parseFloat(numerical).toFixed(2)
			},
			total(number1, number2, suffix) {
				let totalNumber = parseFloat(number1) * parseFloat(number2)
				return totalNumber.toFixed(2) + ' ' + suffix
			}
		}
	}
</script>

<style scoped="scoped">
	element .style {
		min-height: 50px;
		width: 300px;
	}

	#columns {
		font-size: 0;
	}

	#columns label {
		width: 90px;
		color: #99a9bf;
	}

	#columns .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
</style>
