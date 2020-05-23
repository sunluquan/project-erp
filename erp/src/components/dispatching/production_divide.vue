<template>
<<<<<<< HEAD
	<div id="production_divide">
		<div id="columns">
			<el-form label-position="left" class="demo-table-expand">
				<el-form-item  style="text-align: right;">
					  <el-button type="danger" @click="insertDsipatching">新增派工单</el-button>
				</el-form-item>
			</el-form>
		</div>
		<br />
		<el-table ref="productionPlan_table" :border="true" :highlight-current-row="true" style="width: 100%" :data="productions"
		 :default-sort="{prop:'production_id',order:'descending'}"
		 @selection-change="selectionChange">
			<el-table-column
			      type="selection"
			      width="55">
			</el-table-column>
			<el-table-column label="生产计划编号" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.production_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlanDetail.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlanDetail.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="生产数量" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlanDetail.amount|formNumerical()}} {{scope.row.productionPlanDetail|whetherShow('product_unit')}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlanDetail|whetherShow('useName')}}
				</template>
			</el-table-column>
			<el-table-column label="实际单价" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlanDetail.real_cost_price|formNumerical('元')}}
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
		</el-table>
	</div>
=======
	
>>>>>>> warehouse
</template>

<script>
	export default{
<<<<<<< HEAD
		name:'production_divide',//按生产产品计划编号划分 来制定派工单
		data(){
			return{
				productions:[],
				activeProductions:[]
=======
		name:'production_divide',//按生产产品计划编号划分
		data(){
			return{
				productions:[]
>>>>>>> warehouse
			}
		},methods:{
			getMeetDispatchingProductions(){//获取满足可以派工生产的生产单
				this.$axios.get('/api/productionPlan/meetDespatching')
					.then(response=>{
						this.productions=response.data
					})
<<<<<<< HEAD
			},insertDsipatching(){
				let activeProductions=this.activeProductions;
				if(activeProductions.length==0){
					this.$message.error('请选择产品进行派工!')
				}
				let id=activeProductions[0].id
				let totalAmount=0;
				let fals=true
				let index = activeProductions.forEach(item=>{
					if(item.id!=id){
						fals=false
						return;
					}
					totalAmount+=item.productionPlanDetail.amount
				})
				if(!fals){
					this.$message.error('请选择相同的产品进行派工!')
				}else{
					let productionPlanDetail=activeProductions[0].productionPlanDetail
					let product={
						product_id:productionPlanDetail.product_id,
						product_name:productionPlanDetail.product_name,
						productionPlanDetails:activeProductions,
						amount:totalAmount,
						real_cost_price:productionPlanDetail.real_cost_price,
						product_unit:productionPlanDetail.product_unit,
						message:productionPlanDetail.message
					}
					this.$emit('make_dispatching',product)
				}
			},selectionChange(data){
				this.activeProductions=data
			}
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
		},created() {
			this.getMeetDispatchingProductions()
		},computed:{
			
=======
			}
>>>>>>> warehouse
		}
	}
</script>

<style>
</style>
