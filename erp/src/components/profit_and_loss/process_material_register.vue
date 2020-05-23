<template>
	<el-dialog :before-close="close" title="工序物料报损报溢登记" append-to-body :visible.sync="process_material_dialog_visible">
		<!-- 生产工序所需的物料集合 -->
		<view-process-material
		 ref="view_process_material_ref"
		 :production_process_id="production_process.id"
		 @set_process_materials="setProcessMaterials">
		 </view-process-material>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="工序名称: ">
					<span>{{production_process.process_name}}</span>
				</el-form-item>
				<el-form-item label="负责人: ">
					<span>{{production_process.responsible_person}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item style="text-align: right;">
				</el-form-item>
				<el-form-item style="text-align: right;">
					  <el-button type="success" size="small" @click="view_process_material">查看物料</el-button>
				</el-form-item>
			</el-form>
		</div>
		<el-table :summary-method="getSummaries" show-summary
		style="text-align: center;width: 100%" :border="true" :data="processMaterials" :highlight-current-row="true">
			<template slot="empty">
				暂无物料数据,
				<el-button type="primary" @click="view_process_material" title="点击添加" icon="el-icon-plus" circle></el-button>
			</template>
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
			<el-table-column label="已用数量" prop="real_amount">
				<template slot-scope="scope">
					{{scope.row.real_amount|formNumerical1()}}
				</template>
			</el-table-column>
			<el-table-column label="退领数量" prop="real_amount">
				<template slot-scope="scope">
					{{scope.row.renew_amount|formNumerical1()}}
				</template>
			</el-table-column>
			<el-table-column label="损益数量" prop="profit_loss_amount">
				<template slot-scope="scope">
					<number v-model="scope.row.profit_loss_amount" :min='-((scope.row.amount+scope.row.renew_amount)-scope.row.real_amount)' ></number>
				</template>
			</el-table-column>
			<el-table-column label="合计">
				<template slot-scope="scope">
					{{scope.row.profit_loss_amount|total(scope.row.cost_price,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					  <el-button type="danger" @click="delete_material(scope.row.id)" icon="el-icon-delete" title="删除" circle></el-button>
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
					<el-input type="textarea" autosize v-model="productProfitLoss.remark"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">关 闭</el-button>
			<el-button type="success" @click="submitRegister">提 交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import view_process_material1 from '@/components/profit_and_loss/view_process_material.vue'
	import number from '@/components/number.vue'
	export default{
		name:'process_material_register',//生产派工单的指定工序的物料申领或者退回
		props:{
			production_process:{
				type:Object,
				default:()=>({})
			},manufacture:{
				type:Object,
				default:()=>({})
			},
		},
		data(){
			return{
				processMaterials:[],
				process_material_dialog_visible:false,
				productProfitLoss:{
					remark:''
				}
			}
		},methods:{
			open(){
				this.process_material_dialog_visible=true
			},close(){
				this.process_material_dialog_visible=false
			},view_process_material(){
				this.$refs.view_process_material_ref.open()
			},setProcessMaterials(data){
				let index = this.processMaterials.findIndex((item)=>item.id===data.id)
				if(index>=0){
					this.processMaterials[index].profit_loss_amount+=1;
				}else{
					let processMaterial=JSON.parse(JSON.stringify(data))
					this.$set(processMaterial,'profit_loss_amount',1)
					this.processMaterials.push(processMaterial)
				}
			},setdatastructure(){
				let remark=this.productProfitLoss.remark;
				let productProfitLoss={ 
					manufacture_id:this.manufacture.manufacture_id,
					product_id:this.manufacture.product_id,
					product_name:this.manufacture.product_name,
					process_id:this.production_process.process_id,
					production_process_id:this.production_process.id,
					process_name:this.production_process.process_name,
					responsible_person:this.manufacture.responsible_person,
					remark:remark,
					productProfitLossDetails:this.processMaterials
				}
				return productProfitLoss;
			},submitRegister(){
				let productProfitLoss=this.setdatastructure()
				this.$axios.post('/api/productProfitLoss/insert',productProfitLoss)
					.then(response=>{
						if(response.statusCord == 200){
							this.$message.success('提交成功!等待审核!')
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
			},delete_material(id){
				let index = this.processMaterials.findIndex((item)=>item.id===id)
				this.processMaterials.splice(index,1)
			},getSummaries(param){
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
								let profit_loss_amount = Number(item.profit_loss_amount)
								let costPrice = Number(item.cost_price)
								if (!isNaN(profit_loss_amount) && !isNaN(costPrice)) {
									total += profit_loss_amount * costPrice
								}
							})
							sums[index] = '物料成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = ""
						}
					}
				});
				return sums;
			}
		},components:{
			"view-process-material":view_process_material1,
			number
		},created() {
			
		},computed:{
			register(){
				return this.$store.getters.getActiveUser.user.uid
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
