<template>
	<el-dialog :before-close="close" title="工序物料登记核审" append-to-body :visible.sync="confirm_dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="派工单编号: ">
					<span>{{manufacture.manufacture_id}}</span>
				</el-form-item>
				<el-form-item label="工序名称: ">
					<span>{{production_process_course.process_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="负责人: ">
					<el-input v-model="production_process_course.responsible_person" placeholder="本次工时数"></el-input>
				</el-form-item>
				<el-form-item label="设计工时数: ">
					<span>{{production_process.labour_hour_amount|whetherShow1(production_process.time_unit)}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="已用工时数: ">
					<!-- 也就是已用的工时数 -->
					<span>{{production_process.real_labour_hour_amount|formNumerical1}}</span>
				</el-form-item>
				<el-form-item label="本次工时数: ">
					<el-input v-model="production_process_course.labour_hour_amount" placeholder="本次工时数"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<el-table  style="text-align: center;width: 100%" :border="true" 
		:data="production_process_course.productionProcessMaterials" :highlight-current-row="true">
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
			<el-table-column label="设计数量" prop="amount">
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="补充数量" prop="renew_amount">
				<template slot-scope="scope">
					{{scope.row.renew_amount|formNumerical1()}}
				</template>
			</el-table-column>
			<el-table-column label="已使用数量" prop="real_amount">
				<template slot-scope="scope">
					{{scope.row.real_amount|formNumerical1()}}
				</template>
			</el-table-column>
			<el-table-column label="当前使用数量">
				<template slot-scope="scope">
					<number v-model="scope.row.productionProcessMaterialCourse.use_amount" :min=0 :max='((scope.row.amount+scope.row.renew_amount)-scope.row.real_amount)'></number>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{production_process_course.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<el-input type="textarea" autosize v-model="production_process_course.procedure_describe"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">关 闭</el-button>
			<el-button type="success" @click="checker">审核通过</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/number.vue'
	export default{
		name:'confirm_checker_production_register',
		model:{
			prop:'confirm_dialog_visible',
			event:'update'
		},props:{
			confirm_dialog_visible:{
				type:Boolean
			},manufacture:{
				type:Object,
				default:()=>({})
			},production_process:{
				type:Object,
				default:()=>({})
			}
		},data(){
			return{
				production_process_course:{}
			}
		},methods:{
			renewal(allUpdate){
				//allUpdate:跟新所有吗  true更新所有 false更新上一个
				this.$emit('renewal',allUpdate);
			},
			checker(){
				let manufacture={
					id:this.manufacture.id,
					productionProcess1:this.production_process,
					productionProcessCourse:this.production_process_course
				}
				this.$axios.post('/api/productionProcessCourse/checker',manufacture)
					.then(response=>{
						if(response.statusCord == 200){
							if(response.data){
								//说明这个生产的产品没有需要审核的登记和交接了
								
							}else{//如果为false 说明还是有得
								
							}
							this.$message.success('审核成功!');
							this.renewal(response.data)
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
			},
			close(){
				this.$emit('update',false)
			},getProductionProcessCourse(){
				let manufacture_id=this.manufacture.id;
				//通过生产工序的process_id
				let process_id=this.production_process.process_id;
				//生产工序的id
				let production_process_id = this.production_process.id;
				if(manufacture_id&&process_id){
					this.$axios.get('/api/productionProcessCourse/byProcessIdAndmanufactureId',{params:{
						manufacture_id:manufacture_id,
						production_process_id:production_process_id,
						process_id:process_id
					}}).then(response=>{
						this.production_process_course=response.data
					})
				}
			}
		},watch:{
			process_id(newVal){
				// if(newVal){
				// 	this.getProductionProcessCourse()
				// }
			},confirm_dialog_visible(newVal){
				if(newVal){
					this.getProductionProcessCourse()
				}
			},
		},computed:{
			
			process_id(){
				return this.production_process.process_id
			}
		},filters:{
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
		},components:{
			number
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
