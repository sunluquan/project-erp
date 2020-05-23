<template>
	<div id="internal_production">
		<el-table  :border="true"
		 ref="internalProduction_table" :highlight-current-row="true" style="width: 100%"
		  :data="internalProductions"
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
					<el-button size="small" @click="register" type="primary" title="制定单登记" >登记</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>
<script>
	export default{
		name:'internal_production',//内部生产初始化登记查询
		data(){
			return{
				internalProductions:[]
			}
		},methods:{
			getMeetInternalProductions(){
				//获取满足内部生产登记的派工单
				this.$axios.get('/api/manufacture/meetInternalProductions')
					.then(response=>{
						this.internalProductions=response.data
					})
			},register(){
				
			}
		},created() {
			this.getMeetInternalProductions()
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
		}
	}
</script>

<style>
</style>
