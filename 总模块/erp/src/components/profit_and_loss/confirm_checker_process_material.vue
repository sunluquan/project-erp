<template>
	<el-dialog :before-close="close" title="工序物料报损报溢核审" append-to-body :visible.sync="checker_process_material_dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="派工单编号: ">
					<span>{{profit_loss.manufacture_id}}</span>
				</el-form-item>
				<el-form-item label="工序名称: ">
					<span>{{profit_loss.process_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item style="text-align: right;">
				</el-form-item>
				<el-form-item style="text-align: right;">
				</el-form-item>
			</el-form>
		</div>
		<!-- :summary-method="getSummaries" show-summary -->
		<el-table 
		style="text-align: center;width: 100%" :border="true"
		 :data="profitLossDetails" :highlight-current-row="true">
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
			<!-- <el-table-column label="设计数量" prop="amount">
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
			</el-table-column> -->
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
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{profit_loss.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<el-input type="textarea" autosize v-model="profit_loss.remark"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="" @click="close">关 闭</el-button>
			<el-button type="success" @click="pass">提 交</el-button>
			<el-button type="danger" @click="befor_reject">驳 回</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/number.vue'
	export default{
		name:'confirm_checker_process_material',//审核申领退回物料
		props:{
			profit_loss:{
				type:Object,
				default:()=>({})
			}
		},
		data(){
			return{
				checker_process_material_dialog_visible:false,
				profitLossDetails:[],
				old_profit_loss_id:''
			}
		},methods:{
			update(){
				this.$emit('update')
			},befor_reject(){
				this.$prompt('有无驳回原由', '提示', {
					confirmButtonText: '提交(有)',
					cancelButtonText: '提交(无)',
				}).then(({
					value
				}) => {
					this.reject(value==null?"":value)
				}).catch(() => {
					this.reject("")
				});
			},reject(check_suggestion){
				check_suggestion="驳回原由: "+check_suggestion;
				let profit_loss=JSON.parse(JSON.stringify(this.profit_loss))
				profit_loss.check_suggestion=check_suggestion
				this.$axios.post('/api/productProfitLoss/reject',profit_loss)
					.then(response=>{
						if(response.statusCord == 200){
							this.$message.success('审核已驳回!')
							this.update();
							this.close()
						}else{
							this.$message.error(response.data)
						}
					})
			},pass(){
				let profit_loss=JSON.parse(JSON.stringify(this.profit_loss))
				profit_loss.productProfitLossDetails=this.profitLossDetails
				this.$axios.post('/api/productProfitLoss/pass',profit_loss)
					.then(response=>{
						if(response.statusCord == 200){
							this.$message.success('审核通过成功!')
							this.update();
							this.close()
						}else{
							this.$message.error(response.data)
						}
					})
			},open(){
				this.checker_process_material_dialog_visible=true
				this.getProductProfitLossDetailsByParentId();
			},close(){
				this.checker_process_material_dialog_visible=false
			},getProductProfitLossDetailsByParentId(){
				let profit_loss_id=this.profit_loss.id;
				if(profit_loss_id){
					if(profit_loss_id===this.old_profit_loss_id)return;
					this.$axios.get(`/api/productProfitLossDetail/byParentId/${profit_loss_id}`)
						.then(response=>{
							this.profitLossDetails=response.data
							this.old_profit_loss_id=profit_loss_id
						})
				}
				
			}
		},computed:{
			profit_loss_id(){
				return this.profit_loss.id;
			}
		},watch:{
			profit_loss_id(newVal){
				if(newVal){
					this.getProductProfitLossDetailsByParentId()
				}
			}
		},components:{
			number
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
			},total(number1, number2, suffix) {
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
