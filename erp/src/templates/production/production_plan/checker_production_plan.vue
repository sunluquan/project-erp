<template>
	<div id="checker_production_plan">
		<router-view @update="getCheckerProductionPlans"></router-view>
		<el-table @row-dblclick="viewProductionPlanDetail" ref="productionPlan_table" :border="true" :highlight-current-row="true"
		 style="width: 100%" :data="prodctionPlans" 
		 :default-sort="{prop:'production_id',order:'descending'}" >
			<el-table-column label="生产计划编号" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.production_id}}
				</template>
			</el-table-column>
			<el-table-column label="设计人" prop="designer" sortable>
				<template slot-scope="scope">
					{{scope.row|whetherShow('designer')}}
				</template>
			</el-table-column>
			<el-table-column label="生产理由" prop="production_reason" sortable>
				<template slot-scope="scope">
					{{scope.row.production_reason}}
				</template>
			</el-table-column>
			<el-table-column label="订单创建时间:" prop="register_time" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{ scope.row.register_time }}</span>
				</template>
			</el-table-column>
			<el-table-column label="备注:">
				<template slot-scope="scope">
					<span style="margin-left: 10px">{{ scope.row|whetherShow('remark') }}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="120px">
				<template slot-scope="scope">
					<el-button @click="checkerProductionPlan(scope.row.id)" type="primary" title="审核生产计划单" icon="el-icon-edit" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div v-show="showDetail&&prodctionPlans.length>0">
			<h6>生产计划详细:<span style="color: red;">{{activeProductionPlan.production_id}}</span></h6>
			<production-plan-detail :id="activeProductionPlan.id"></production-plan-detail>
		</div>
	</div>
</template>

<script>
	import production_plan_detail from '@/components/production/production_plan_detail.vue'
	export default {
		name: 'checker_production_plan',
		data() {
			return {
				prodctionPlans:[],
				activeProductionPlan:{},
				showDetail:false
			}
		},
		methods: {
			viewProductionPlanDetail(data){
				if(this.activeProductionPlan.id!=undefined&&this.activeProductionPlan.id==data.id){
					this.showDetail=!this.showDetail
				}else{
					this.showDetail=true
					this.activeProductionPlan=data
				}
			},getCheckerProductionPlans(){
				this.$axios.get('/api/productionPlan/checker')
					.then(response=>{
						this.prodctionPlans=response.data
					})
			},checkerProductionPlan(id){
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/checker/${id}`})
			}
		},
		created() {
			this.getCheckerProductionPlans()
		},components:{
			'production-plan-detail':production_plan_detail
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
			formNumerical(numerical,suffix) {
				//保留两位小数
				if(suffix){
					return parseFloat(numerical).toFixed(2)+' '+suffix
				}
				return parseFloat(numerical).toFixed(2)
			},total(number1,number2,suffix){
				let totalNumber=parseFloat(number1)*parseFloat(number2)
				return totalNumber.toFixed(2)+' '+suffix
			}
		}
	}
</script>

<style>
</style>
