<template>
	<el-dialog :before-close="close" title="生产损益登记" :visible.sync="register_dialog_visible">
		<process-material-register
		ref="process_material_register"
		:production_process="activeProductionProcess"
		:manufacture="manufacture">
		</process-material-register>
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
		
		:summary-method="getSummaries" show-summary
		-->
		<el-table @row-dblclick="register"  :border="true" ref="production_process" :highlight-current-row="true"
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
					<template v-if="scope.row.process_finish_tag==0">
						<el-link type="primary" @click="register(scope.row)">
							<el-tag type="primary" effect="dark">登记</el-tag>
						</el-link>
					</template>
					<template v-else-if="scope.row.process_finish_tag==1">
						<el-tag type="success" effect="dark">完成</el-tag>
					</template>
					<!-- 如果为完成中的审核中  则这道工序不能够进行报损报溢登记  
					 但是好像又可以-->
					<template v-else-if="scope.row.process_finish_tag==3">
						<el-tag type="" effect="dark">审核中</el-tag>
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
	import process_material_register from '@/components/profit_and_loss/process_material_register.vue'
	export default{
		name:'view-register-profit-and-loss',//登记具体的派工单下的工序集合
		props:{
			manufacture:{
				type:Object,
				default:()=>({})
			}
		},
		data(){
			return{
				register_dialog_visible:false,
				productionProcess:[],
				activeProductionProcess:{}
			}
		},methods:{
			open(){
				this.register_dialog_visible=true
				this.getProductionProcessByParentId()
			},close(){
				this.register_dialog_visible=false
			},register(data){//弹出该生产工序的物料框
				if(data.process_finish_tag==0){
					this.activeProductionProcess=data
					this.$refs.process_material_register.open()
				}
			},getProductionProcessByParentId(){
				let manufacture_id = this.manufacture.id
				if(manufacture_id){
					this.$axios.get(`/api/productionProcess/byParentId/${manufacture_id}`)
						.then(response => {
							this.productionProcess = response.data
						})
				}
			}
		},filters:{
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
		},computed:{
			manufacture_id(){
				return this.manufacture.id
			}
		},watch:{
			manufacture_id(newVal){
				if(newVal){
					this.getProductionProcessByParentId()
				}
			}
		},components:{
			'process-material-register':process_material_register
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
