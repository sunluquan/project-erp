<template>
	<el-dialog :before-close="close" title="产品物料设计核审" :visible.sync="dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{materialDesign.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{materialDesign.product_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="设计人: ">
					<span>{{materialDesign|whetherShow('designer','暂无设计人')}}</span>
					<!-- <el-input  v-model="materialDesign.designer" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
					<el-form-item label="登记人:">
						<span>{{materialDesign.register}}</span>
					</el-form-item>
			</el-form>
		</div>
		<!-- 		<div style="text-align: right;">
			<el-button type="success" size="small" v-on:click="material_module_dialog_visible=true">添加物料</el-button>
		</div> -->
		<br />
		<!-- :summary-method="getSummaries" -->
		<el-table style="text-align: center;width: 100%" :border="true" :summary-method="getSummaries" show-summary :data="materialDesign.materials"
		 ref="product_table" :highlight-current-row="true">
			<el-table-column label="物料编号">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="物料名称">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型">
				<template slot-scope="scope">
					{{scope.row.useName}}
				</template>
			</el-table-column>

			<el-table-column label="单位">
				<template slot-scope="scope">
					{{scope.row.product_unit}}
				</template>
			</el-table-column>
			<el-table-column label="单价" prop="cost_price">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="需要数量" prop="amount">
				<template slot-scope="scope">
					{{scope.row.amount}}
					<!-- <number v-model="scope.row.amount"></number> -->
					<!-- <el-input-number size="mini" v-model="scope.row.amount"></el-input-number> -->
				</template>
			</el-table-column>
			<el-table-column label="小计" prop="total">
				<template slot-scope="scope">
					{{scope.row|total('amount','cost_price',2,'元')}}
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-form-item label="变更人:" :span="10">
				<span>{{materialDesign|whetherShow('changer','无')}}</span>
			</el-form-item>
			<el-col :span="14">
				<el-form-item  label="设计要求">
					{{materialDesign|whetherShow('module_describe','无设计要求')}}
					<!-- <el-input :disabled="true" type="textarea" autosize v-model="materialDesign.module_describe"></el-input> -->
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close">取 消</el-button>
			<el-button type="primary" @click="pass">通过核审</el-button>
			<el-button type="danger" @click="reject_cause">驳回</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'confirm_product_material_design', //确认产品物料设计审核
		data() {
			return {
				dialog_visible: false,
				materialDesign: {
					id: ''
				}
			}
		},
		methods: {
			update() {
				this.$emit('update')
			},
			pass() {
				let id = this.materialDesign.id;
				let product_id=this.materialDesign.product_id
				let productMaterialDesign={
					id:id,
					product_id:product_id
				}
				this.$axios.post('/api/materialDesign/pass',productMaterialDesign)
					.then(response => {
						if (response.statusCord == 200) {
							this.$message.success('提交通过成功!');
							this.update()
							this.close()
						} else {
							this.$message.error(response.message)
						}
					})
			},
			reject_cause() {
				this.$prompt('有无驳回原由', '提示', {
					confirmButtonText: '提交(有)',
					cancelButtonText: '提交(无)',
				}).then(({
					value
				}) => {
					this.reject(value)
				}).catch(() => {
					this.reject("")
				});
			},
			reject(value) {
				value = "驳回原由:" + value;
				let id = this.materialDesign.id;
				let product_id=this.materialDesign.product_id
				let productMaterialDesign={
					id:id,
					product_id:product_id,
					module_describe:value
				}
				this.$axios.post('/api/materialDesign/reject',productMaterialDesign)
					.then(response => {
						if (response.statusCord == 200) {
							this.$message.success('已驳回!');
							this.update()
							this.close()
						} else {
							this.$message.error(response.message)
						}
					})
			},
			close() {
				this.dialog_visible = false
				this.$router.go(-1)
			},
			getMaterialDesign() {
				this.$axios.get('/api/materialDesign/byId/'+this.materialDesign.id)
					.then(response => {
						this.materialDesign = response.data
					})
			},
			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length = columns.length
				columns.forEach((column, index) => {
					if (index === 0) {
						sums[index] = '合计';
						return;
					}
					// if(index==length-1){//说明是最后一行合计
					// 	sums[index]='总价格:'+
					// }
					const values = data.map(item => Number(item[column.property]));
					if (!values.every(value => isNaN(value))) {
						sums[index] = values.reduce((prev, curr) => {
							const value = Number(curr);
							if (!isNaN(value)) {
								return prev + curr;
							} else {
								return prev;
							}
						}, 0);
						sums[index] = sums[index].toFixed(2)
					} else {
						if (index == length - 1) {
							let total = 0;
							data.forEach((item => {
								let amount = Number(item.amount)
								let price = Number(item.cost_price)
								if (!isNaN(amount) && !isNaN(price)) {
									total += (amount * price);
								}
							}))
							sums[index] = '物料总成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = 'N/A';
						}

					}
				});
				return sums;
			}
		},
		created() {
			this.dialog_visible = true
			this.materialDesign.id = this.$route.params[Object.keys(this.$route.params)[0]]
			this.getMaterialDesign()
		},filters:{
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
			total(data, price, number, fixed, suffix) {
				let price1 = parseFloat(data[price]);
				let number1 = parseInt(data[number]);
				let totalPrice = price1 * number1
				return totalPrice.toFixed(fixed) + suffix
			},
			formNumerical(numerical, prefix) {
				//保留两位小数
				if (prefix) {
					return parseInt(numerical).toFixed(2) + prefix;
				} else {
					return parseInt(numerical).toFixed(2);
				}
			
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

