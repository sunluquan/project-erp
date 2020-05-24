<template>
	<el-dialog :before-close="close" title="工序物料登记" append-to-body :visible.sync="production_register_dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="派工单编号: ">
					<span>{{manufacture.manufacture_id}}</span>
				</el-form-item>
				<el-form-item label="工序名称: ">
					<span>{{production_process.process_name}}</span>
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
		<el-table style="text-align: center;width: 100%" 
		:border="true" :data="materials" 
		:highlight-current-row="true">
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
			<el-table-column label="已领取数量" prop="renew_amount">
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
					<!-- <number v-model="scope.row.use_amount" :min=0 :max='((scope.row.amount+scope.row.renew_amount)-scope.row.real_amount)'></number> -->
					<number @change="useAmountChange" :production_process_material="scope.row" v-model="scope.row.use_amount" :min=0 :max="(scope.row.renew_amount-scope.row.real_amount)"
					 :step="scope.row.single_material_design_amount" :step_strictly="true"></number>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{register}}</span>
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
			<el-button type="success" @click="isfinish">提 交</el-button>
			<!-- <el-button type="success" @click="submitRegister">提 交</el-button> -->
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/production_register_number.vue'
	export default {
		name: 'production_register', //生产登记
		model: {
			prop: 'production_register_dialog_visible',
			event: 'update'
		},
		props: {
			production_register_dialog_visible: {
				type: Boolean
			},
			production_process: {
				type: Object,
				default: () => (null)
			},
			manufacture: {
				type: Object,
				default: () => ({})
			}
		},
		data() {
			return {
				
				materials: [],
				production_process_course: {
					labour_hour_amount: 0, //本次工时数
					responsible_person: '',
					procedure_describe: '',
				}
			}
		},
		methods: {
			useAmountChange(data){
				//当某一个物料的使用数量发生改变时  其他的物料也会跟着改变
				//获取当前物料的使用数量倍数
				//倍数为 使用数量/一个产品的这道工序需要的使用数量
				let multiple=0;
				alert(data.use_amount)
				if(data.use_amount>0){
					
					multiple=data.use_amount/data.single_material_design_amount
				}
				this.materials.forEach(item=>{
					if(item.id!=data.id){
						let use_amount=item.single_material_design_amount*multiple
						//最大可用数量：库存领取物料数量-已使用的物料数量
						let max_amount=item.renew_amount-item.real_amount
						if(use_amount>max_amount){
							use_amount=max_amount
						}
						item.use_amount=use_amount;
					}
				})
			},
			//只有当这道工序没有物料的时候会弹出
			
			isfinish() { //当前工序是否完工
				if(this.materials.length==0){
					this.$confirm('当前工序是否已完工?', '当前工序完工状态', {
						confirmButtonText: '已完工状态',
						cancelButtonText: '未完工状态',
						type: 'info',
						center: true
					}).then(() => {
						this.submitRegister(true)
					}).catch(() => {
						this.submitRegister(false)
					});
				}else{
					this.submitRegister(false)
				}
			},
			refresh() {
				this.$emit('refresh')
			},
			setProductionProcessCourse() {
				let production_process = this.production_process
				for (let lt in production_process) {
					this.production_process_course[lt] = production_process[lt]
				}
			},
			close() {
				this.$emit('update', false)
			},
			getProcessMaterials() {
				let production_process_id = this.production_process.id
				this.$axios.get(`/api/productionProcessMaterial/byParentId/${production_process_id}`)
					.then(response => {
						this.materials = response.data
					})
			},
			submitRegister(isFinish) {//
				let manufacture = {
					id: this.manufacture.id,
					productionProcess1: {},
					productionProcessCourse: this.production_process_course
				}
				manufacture.productionProcess1 = JSON.parse(JSON.stringify(this.production_process))
				manufacture.productionProcess1['productionProcessMaterials'] = this.materials
				alert(isFinish)
				//只有当前工序没有物料的情况下 才会进行确认
				if (isFinish) { //已完工得话  设置生产工序得工序完成状态为3 完成时得工序审核
					manufacture.productionProcess1['process_finish_tag'] = 3;
				}
				this.$axios.post('/api/productionProcessCourse/register', manufacture)
					.then(response => {
						if (response.statusCord == 200) {
							this.$message.success('登记成功!');
							this.refresh()
							this.close()
						} else {
							this.$message.error(response.message)
						}
					})
			}
		},
		created() {},
		filters: {
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
		},
		watch: {
			production_process_id(newVal) {
				if (newVal) {
					this.getProcessMaterials()
				}
			}
		},
		components: {
			number,
		},
		computed: {
			production_process_id() {
				return this.production_process.id;
			},
			register() {
				let register = this.$store.getters.getActiveUser.user.uid
				this.production_process_course.responsible_person = register
				return register;
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
