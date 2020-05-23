<template>
	<el-dialog width="80%" :before-close="close" title="产品物料设计修改" :visible.sync="dialog_visible">
		<material-module @put_material="putMaterial" v-model="material_module_dialog_visible"></material-module>
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
					<el-input v-model="materialDesign.designer" placeholder="请输入设计人"></el-input>
				</el-form-item>
				<el-form-item style="text-align: right;" >
					<el-button type="success" size="small" v-on:click="material_module_dialog_visible=true">添加物料</el-button>
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
			<template slot="empty">
				暂无物料数据,
				<el-button type="primary" @click="materialModule" title="点击添加" icon="el-icon-plus" circle></el-button>
			</template>
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
					<number v-model="scope.row.amount"></number>
					<!-- <el-input-number size="mini" v-model="scope.row.amount"></el-input-number> -->
				</template>
			</el-table-column>
			<el-table-column label="小计" prop="total">
				<template slot-scope="scope">
					{{scope.row|total('amount','cost_price',2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="danger" @click="deleteMaterial(scope.row.product_id)" title="取消该设计物料" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item >
					<span>登记人:  {{materialDesign.register}}</span>
					&nbsp;&nbsp;
					<span>更改人:  {{register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="设计要求">
					<el-input type="textarea" autosize v-model="materialDesign.module_describe"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="submitDesign">提交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/number.vue'
	import material_module from '@/components/product/material_module.vue'
	export default {
		name: 'update_product_material_design', //产品物料组成设计
		data() {
			return {
				materialDesign: {},
				demand: '',
				dialog_visible: false,
				material_module_dialog_visible: false //子模态框的显示隐藏
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			getMaterialDesign() {
				this.$axios.get('/api/materialDesign/byId/' + this.materialDesign.id)
					.then(response => {
						this.materialDesign = response.data
						if(this.materialDesign.module_describe.indexOf('驳回原由:')==0){
							this.materialDesign.module_describe=''
						}
					})
			},
			deleteMaterial(id) {
				let index = this.materialDesign.materials.findIndex(item => {
					return item.product_id === id
				})
				this.materialDesign.materials.splice(index, 1)
			},
			putMaterial(material) {
				material = JSON.parse(JSON.stringify(material))
				material['amount'] = 1;
				let materials = this.materialDesign.materials
				if (materials.length > 0) {
					let index = materials.findIndex(item => {
						return material.product_id == item.product_id
					})
					if (index >= 0) {
						materials[index].amount += material.amount
					} else {
						materials.push(material)
					}
				} else {
					materials.push(material)
				}
			},
			materialModule() {
				this.material_module_dialog_visible = true
			},
			submitDesign() {
				if(this.materialDesign.materials&&this.materialDesign.materials.length>0){
					this.materialDesign.module_describe="设计要求: "+this.materialDesign.module_describe
					this.$axios.post('/api/materialDesign/update',this.materialDesign)
						.then(response=>{
							if (response.statusCord == 200) {
								this.$message.success('修改设计成功!等待审核')
								this.update()
								this.close(true)
							} else {
								this.$message.error(response.message)
							}
						})
				}else{
					this.$message.error('至少选择一种物料')
				}
			},
			close(fals) {
				this.$router.go(-1)
				this.dialog_visible = false
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
						if (index == length - 2) {
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
			this.getMaterialDesign();

		},
		computed: {
			register() {
				return this.$store.getters.getActiveUser.user.uid
			}
		},
		components: {
			"material-module": material_module,
			number
		},
		filters: {
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
