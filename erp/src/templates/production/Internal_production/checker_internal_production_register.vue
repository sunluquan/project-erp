<template>
	<div id="internal_production">
		<checker-production-register
		:manufacture="activeManufacture"
		v-model="checker_dialog_visible"
		@renewal="renewal"
		></checker-production-register>
		<el-table @row-dblclick="checker" :border="true"
		 ref="internalProduction_table" :highlight-current-row="true" style="width: 100%"
		  :data="manufactures"
		 :default-sort="{prop:'manufacture_id',order:'descending'}">
			<el-table-column label="制造单编号" prop="manufacture_id" sortable>
				<template slot-scope="scope">
					{{scope.row.manufacture_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" sortable>
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name" sortable>
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="制定人" sortable>
				<template slot-scope="scope" >
					{{ scope.row.designer|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="登记人">
				<template slot-scope="scope">
					{{ scope.row.designer}}
				</template>
			</el-table-column>
			<el-table-column label="投产数量" prop="amount" sortable>
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="设计物料成本" sortable prop="material_cost_price_sum">
				<template slot-scope="scope">
					{{scope.row.material_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计工时成本" sortable prop="labour_cost_price_sum">
				<template slot-scope="scope">
					{{ scope.row.labour_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="登记时间" prop="cost_price">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					{{ scope.row.register_time|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="说明" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row.remark|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button size="small" @click="checker(scope.row)" type="primary" title="核审" >核审登记</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	import checker_production_register from '@/components/internal_production/checker_production_register.vue'
	export default{
		name:'checker_internal_production_register',
		data(){
			return{
				manufactures:[],
				activeManufacture:{},
				checker_dialog_visible:false
			}
		},methods:{
			checker(data){
				this.activeManufacture=data;
				this.checker_dialog_visible=true
			},getMeetCheckermanufactures(){
				this.$axios.get('/api/manufacture/meetChecker')
					.then(response=>{
						this.manufactures=response.data
					})
			},renewal(){
				this.getMeetCheckermanufactures()
			}
		},created() {
			this.getMeetCheckermanufactures();
		},filters:{
			whetherShow1(data, message) {
				if (data) {
					return data
				} else {
					if (!message) {
						message = "暂无数据!"
					}
					return message;
				}
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
		},components:{
			"checker-production-register":checker_production_register
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
