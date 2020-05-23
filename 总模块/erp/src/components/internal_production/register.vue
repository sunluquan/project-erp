<template>
	<el-dialog :before-close="close" title="生产登记" :visible.sync="register_dialog_visible">
		<!-- 工序登记 -->
		<production-register :manufacture="manufacture"
		 :production_process="activeProductionProcess"
		 v-model="production_register_dialog_visible" 
		 @refresh="getProductionProcess"></production-register>
		 <!-- 工序交接 -->
		 <isfinish v-model="isfinish_dialog_visible"
		  :production_process="activeProductionProcess"
		   @refresh="getProductionProcess"></isfinish>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="派工单编号: ">
					<span>{{manufacture.manufacture_id}}</span>
				</el-form-item>
				<el-form-item label="制定人: ">
					<span>{{manufacture.designer}}</span>
				</el-form-item>
			</el-form>
		</div>

		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{manufacture.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{manufacture.product_name}}</span>
				</el-form-item>
			</el-form>
		</div>

		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="投产数量: ">
					{{manufacture.amount|formNumerical(2)}}
				</el-form-item>
				<el-form-item label="合格数量: ">
					{{manufacture.tested_amount|formNumerical1('暂无合格数量',2)}}
				</el-form-item>
			</el-form>
		</div>
		<br />
		<!-- :summary-method="getSummaries" 
		-->
		<el-table @row-dblclick="checkerProcess" :summary-method="getSummaries" show-summary :border="true" ref="material_design" :highlight-current-row="true"
		 style="width: 100%" :data="productionProcess" :default-sort="{prop: 'details_number', order: 'ascending'}">
			<el-table-column label="工序执行顺序" prop="details_number">
				<template slot-scope="scope">
					{{scope.row.details_number}}
				</template>
			</el-table-column>
			<el-table-column label="工序名称">
				<template slot-scope="scope">
					{{scope.row.process_name}}
				</template>
			</el-table-column>
			<el-table-column label="设计工时">
				<template slot-scope="scope">
					{{scope.row.labour_hour_amount|formNumerical(2,scope.row.time_unit)}}
				</template>
			</el-table-column>
			<el-table-column label="实际工时">
				<template slot-scope="scope">
					{{scope.row.real_labour_hour_amount|formNumerical1('暂无实际工时信息',2,scope.row.time_unit)}}
				</template>
			</el-table-column>
			<el-table-column label="设计工时成本">
				<template slot-scope="scope">
					{{scope.row.subtotal|formNumerical(2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="实际工时成本">
				<template slot-scope="scope">
					{{scope.row.real_subtotal|formNumerical1('暂无实际工时成本信息',2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计物料成本">
				<template slot-scope="scope">
					{{scope.row.module_subtotal|formNumerical(2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="实际物料成本">
				<template slot-scope="scope">
					{{scope.row.real_module_subtotal|formNumerical1('暂无实际物料成本信息',2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="单位工时成本">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical(2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					{{scope.row.process_finish_tag}}
					<template v-if="scope.row.process_finish_tag==0">
						<el-link type="primary" @click="register(scope.row)">
							<el-tag type="info" effect="dark">登记</el-tag>
						</el-link>
					</template>
					<template v-else-if="scope.row.process_finish_tag==1">
						<el-tag type="success" effect="dark">完成</el-tag>
					</template>
					<template v-else-if="scope.row.process_finish_tag==2||scope.row.process_finish_tag==3">
						<el-tag type="" effect="dark">审核中</el-tag>
					</template>
				</template>
			</el-table-column>
			<el-table-column label="工序交接">
				<template slot-scope="scope">
					<template v-if="scope.row.process_finish_tag==1">
						<template v-if="scope.row.process_transfer_tag==0">
							<el-link type="primary" @click="handover(scope.row)">
								<el-tag type="info" effect="dark">交接</el-tag>
							</el-link>
						</template>
						<template v-else-if="scope.row.process_transfer_tag==1">
							<el-tag type="success" effect="dark">交接成功</el-tag>
						</template>
						<template v-else-if="scope.row.process_transfer_tag==2">
							<el-tag type="" effect="dark">交接审核中</el-tag>
						</template>
					</template>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="设计工时总成本: ">
					{{manufacture.labour_cost_price_sum|formNumerical(2,'元')}}
				</el-form-item>
				<el-form-item label="实际工时总成本: ">
					{{manufacture.real_labour_cost_price_sum|formNumerical1('暂无实际工时总成本',2,'元')}}
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="设计物料总成本: ">
					{{manufacture.material_cost_price_sum|formNumerical(2,'元')}}
				</el-form-item>
				<el-form-item label="实际物料总成本: ">
					{{manufacture.real_material_cost_price_sum|formNumerical1('暂无实际物料总成本',2,'元')}}
				</el-form-item>
			</el-form>
		</div>
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{manufacture.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<span>{{manufacture.remark|whetherShow('暂无备注信息')}}</span>
					<!-- <el-input type="textarea" autosize v-model="manufacture.remark"></el-input> -->
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="dranger" @click="close">关 闭</el-button>
			<el-button type="success">提 交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import isfinish from '@/components/internal_production/isfinish.vue'
	import production_register from '@/components/internal_production/production_register.vue'
	export default {
		name: 'register', //内部生产登记组件
		model: {
			prop: 'register_dialog_visible',
			event: 'update'
		},
		props: {
			register_dialog_visible: {
				type: Boolean
			},
			manufacture: {
				type: Object,
				default: () => ({})
			}
		},
		data() {
			return {
				productionProcess: [],
				production_register_dialog_visible:false,
				isfinish_dialog_visible: false,
				activeProductionProcess:{},
			}
		},
		methods: {
			checkerProcess(data){//双击登记或者交接行
				if(data.process_finish_tag==-1||data.process_finish_tag==0){
					this.register(data)
				}else if(data.process_finish_tag==1&&data.process_transfer_tag==0){
					this.handover(data)
				}
			},
			handover(data){//工序交接
				if(data.process_finish_tag==1&& data.process_transfer_tag==0){
					this.activeProductionProcess=data
					this.isfinish_dialog_visible=true;
					this.production_register_dialog_visible=false
				}
			},
			register(data) {//工序登记
				if(data.process_finish_tag==0||data.process_finish_tag==-1){
					this.activeProductionProcess=data
					this.production_register_dialog_visible=true
					this.isfinish_dialog_visible=false
				}
			},
			getProductionProcess() {
				let manufacture_id = this.manufacture.id
				this.$axios.get(`/api/productionProcess/byParentId/${manufacture_id}`)
					.then(response => {
						this.productionProcess = response.data
					})
			},
			close() {
				this.$emit('update', false)
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
						sums[index] = '总价';
						return;
					}
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
						sums[index] = sums[index].toFixed(2);
					} else {
						if (index === length - 2) {
							let total = 0;
							data.forEach(item => {
								let hourAmount = Number(item.labour_hour_amount)
								let costPrice = Number(item.cost_price)
								if (!isNaN(hourAmount) && !isNaN(costPrice)) {
									total += hourAmount * costPrice
								}
							})
							sums[index] = '工时成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = "N/A"
						}
					}
				});
				return sums;
			}
		},
		created() {

		},
		filters: {
			formNumerical1(numerical, message, fixed, suffix) {
				if (message == undefined || message == '')
					message = "无"
				if (!fixed) {
					fixed = 2
				}
				if (!suffix) {
					suffix = ''
				}
				if (numerical) {
					return parseFloat(numerical).toFixed(fixed) + suffix
				} else {
					return message
				}
			},
			whetherShow1(data, message) {
				if (message == undefined || message == '')
					message = "无"
				if (data) {
					return data;
				}
				return message
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
			formNumerical(numerical, fixed, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
				}
				return parseFloat(numerical).toFixed(fixed)
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},
		computed: {
			manufacture_id() {
				return this.manufacture.id
			}
		},
		watch: {
			manufacture_id(newVal) {
				if (newVal != undefined && newVal) {
					this.getProductionProcess()
				}
			}
		},components:{
			"production-register":production_register,
			isfinish
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
