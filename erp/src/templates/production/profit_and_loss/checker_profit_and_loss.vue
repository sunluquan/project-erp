<template>
	<div id="checker_profit_and_loss">
		
		<confirm-checker-process-material
		ref="checker_process_material"
		:profit_loss="activeProfitloss"
		@update="getMeetCheckProfitLossList"
		></confirm-checker-process-material>
		<el-table  :border="true" @row-dblclick="checker"
		 ref="checker_profit_and_loss_table" :highlight-current-row="true" style="width: 100%"
		  :data="profitlossList"
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
			<el-table-column label="工序名称" prop="product_name" sortable>
				<template slot-scope="scope">
					{{scope.row.process_name}}
				</template>
			</el-table-column>
			<el-table-column label="登记人">
				<template slot-scope="scope">
					{{ scope.row.register}}
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
					<el-button size="small" @click="checker(scope.row)" type="primary" title="核审" >核审</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	import confirm_checker_process_material from '@/components/profit_and_loss/confirm_checker_process_material.vue'
	export default{
		name:'checker_profit_and_loss',
		data(){
			return{
				profitlossList:[],
				activeProfitloss:{}
			}
		},methods:{
			getMeetCheckProfitLossList(){
				this.$axios.get('/api/productProfitLoss/check')
					.then(response=>{
						this.profitlossList=response.data
					})
			},checker(data){
				this.activeProfitloss=data;
				this.$refs.checker_process_material.open()
			}
		},created() {
			this.getMeetCheckProfitLossList()
		},components:{
			'confirm-checker-process-material':confirm_checker_process_material
		},filters:{
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
		}
	}
</script>

<style>
</style>
