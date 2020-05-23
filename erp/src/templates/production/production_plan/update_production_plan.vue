<template>
	<el-dialog width="70%" :before-close="close" title="产品生产计划设计修改" :visible.sync="dialog_visible">
		<select-product @put="putProductionPlanDetails"
		 v-model="select_product_dialog_visible"></select-product>
		<div id="columns" >
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="生产理由: ">
					<el-select v-model="productionPlan.production_reason" placeholder="请选择" >
					    <el-option placeholder="请选择"
					      v-for="(productionReason,index) in productionReasons"
					      :key="index"
					      :label="productionReason"
					      :value="productionReason">
					    </el-option>
					  </el-select>
				</el-form-item>
				<el-form-item label="设计人: ">
					<el-input v-model="productionPlan.designer" placeholder="请输入设计人"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item >
					<!-- <el-input v-model="productionPlan.designer" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
				<el-form-item style="text-align: right;">
					<el-button type="success" size="small" @click="insertProduct">添加产品</el-button>
				</el-form-item>
			</el-form>
		</div>
		<br />
		<el-table style="text-align: center;width: 100%" :border="true" :summary-method="getSummaries" show-summary :data="productionPlan.productionPlanDetails"
		 ref="product_table" :highlight-current-row="true">
			<template slot="empty">
				暂无产品数据,
				<el-button type="primary" @click="insertProduct" title="点击添加" icon="el-icon-plus" circle></el-button>
			</template>
			<el-table-column label="产品编号">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型">
				<template slot-scope="scope">
					{{scope.row.useName}}
				</template>
			</el-table-column>
		
			<el-table-column label="单位">
				<template slot-scope="scope">
					{{scope.row.product_unit}}
				</template>
			</el-table-column>
			<el-table-column label="单价" prop="cost_price">
				<template slot-scope="scope">
					{{scope.row.real_cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="生产数量" prop="amount">
				<template slot-scope="scope">
					<number v-model="scope.row.amount"></number>
					<!-- <el-input-number size="mini" v-model="scope.row.amount"></el-input-number> -->
				</template>
			</el-table-column>
			<el-table-column label="小计" prop="total">
				<template slot-scope="scope">
					{{scope.row|total('amount','real_cost_price',2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="danger" @click="deleteProduct(scope.row.id)" title="取消该产品生产" icon="el-icon-delete" circle></el-button>
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
				<el-form-item label="说明/备注:">
					<el-input type="textarea" autosize v-model="productionPlan.remark"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="submitProductionPlan">提交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/number.vue'
	import select_product from '@/components/production/select_product.vue'
	export default{
		name:'update_production_plan',
		data(){
			return{
				dialog_visible:false,
				select_product_dialog_visible:false,
				productionPlan:{},
				productionReasons:[]
			}
		},methods:{
			update(){
				this.$emit('update')
			},
			submitProductionPlan(){
				let products=this.productionPlan.productionPlanDetails
				if(products.length>0){
					// let productionPlanDetails=[];
					// products.forEach(item=>{
					// 	productionPlanDetails.push({product_id:item.product_id,amount:item.amount})
					// })
					// this.productionPlan.productionPlanDetails = productionPlanDetails;
					this.$axios.post('/api/productionPlan/update',this.productionPlan)
						.then(response=>{
							if(response.statusCord == 200){
								this.$message.success('修改生产计划单成功!')
								this.update()
								this.close()
							}else{
								this.$message.error(response.message)
							}
						})
				}else{
					this.$message.error('请选择至少一个需要生产的产品!');
				}
			},close(){
				this.dialog_visible=false
				this.$router.go(-1);
			},insertProduct(){
				this.select_product_dialog_visible=true
			},deleteProduct(id){
				let products = this.productionPlan.productionPlanDetails
				let index = products.findIndex(item=>{
					return item.id==id
				})
				products.splice(index,1)
			},putProductionPlanDetails(data){
				let products = this.productionPlan.productionPlanDetails
				let index = products.findIndex(item=>{
					return item.id==data.id
				})
				if(index>=0){
					products[index].amount+=1;
				}else{
					let product=JSON.parse(JSON.stringify(data))
					this.$set(product,'amount',1)
					products.push(product)
				}
			},
			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length = columns.length
				columns.forEach((column, index) => {
					if (index === 0) {
						sums[index] = '合计';
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
						sums[index] = sums[index].toFixed(2)
					} else {
						if (index == length - 2) {
							let total = 0;
							data.forEach((item => {
								let amount = Number(item.amount)
								let price = Number(item.real_cost_price)
								if (!isNaN(amount) && !isNaN(price)) {
									total += (amount * price);
								}
							}))
							sums[index] = '总成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = 'N/A';
						}
					}
				});
				return sums;
			},init(){
				this.getProductionPlanById()
			},getProductionReasonUnits(){//获取生产理由
				this.$axios.get('/api/unit/production_reason')
					.then(response=>{
						this.productionReasons=response.data.units
						this.productionPlan.production_reason=this.productionReasons[0]
					})
			},getProductionPlanById(){
				let id=this.$route.params[Object.keys(this.$route.params)[0]]
				this.$axios.get(`/api/productionPlan/byIdAll/${id}`)
					.then(response=>{
						this.productionPlan=response.data
						this.getProductionReasonUnits()
					})
			}
		},created() {
			this.dialog_visible=true
			this.init()
		},components:{
			'select-product':select_product,
			number
		},computed:{
			register(){
				let register = this.$store.getters.getActiveUser.user.uid
				this.productionPlan.designer=register
				return register;
			}
		},filters:{
			total(data, price, number, fixed, suffix) {
				let price1 = parseFloat(data[price]);
				let number1 = parseInt(data[number]);
				let totalPrice = price1 * number1
				return totalPrice.toFixed(fixed) + suffix
			},
			formNumerical(numerical, prefix) {
				//保留两位小数
				if (prefix) {
					return parseInt(numerical).toFixed(2) + prefix;
				} else {
					return parseInt(numerical).toFixed(2);
				}
			
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
