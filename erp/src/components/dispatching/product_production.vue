<template>
	
	<div id="product_production">
		<el-table size="small" :border="true"
		 :highlight-current-row="true" 
		 style="width: 100%" 
		 :data="product.productionPlanDetails" 
		 :default-sort="{prop:'production_id',order:'descending'}"
		  :summary-method="getSummaries" show-summary 
		  @selection-change="selectionChange"
		  >
			<el-table-column 
			      type="selection"
			      width="55">
			</el-table-column>
			<el-table-column label="生产计划单号" prop="production_id" sortable>
				<template slot-scope="scope">
					{{scope.row.productionPlan.production_id}}
				</template>
			</el-table-column>
			<el-table-column label="单价(人工成本+物料成本)" prop="amount" sortable>
				<template slot-scope="scope">
					{{product.real_cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="生产数量" prop="amount" sortable>
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="合计" >
				<template slot-scope="scope">
					{{scope.row.amount|total(product.real_cost_price,'元')}}
				</template>
			</el-table-column>
		</el-table>
		
		 <div style="text-align: center;margin-top: 5px;">
		 	<el-button type="primary" @click="makeDispatching" :disabled="selectedProductionPlans.length==0">制定派工单</el-button>
		 	<el-button type="danger" >关闭</el-button>
		 </div>
	</div>
</template>

<script>
	export default{
		name:'product_production',//产品所对应的所有生产单下有这个产品集合的展示
		props:{
			product:{
				type:Object,
				default:()=>({})
			}
		},
		data(){
			return{
				selectedProductionPlans:[]
			}
		},methods:{
			close(){
				this.$emit('close')
			},makeDispatching(){
				console.log(this.product)
				let product={
					product_id:this.product.product_id,
					product_name:this.product.product_name,
					productionPlanDetails:this.selectedProductionPlans,
					amount:this.totalAmount,
					real_cost_price:this.product.real_cost_price,
					product_unit:this.product.product_unit,
					message:this.product.message
				}
				this.$emit('make_dispatching',product)
			},
			// makeDispatching(){
			// 	let ids =[]
			// 	this.selectedProductionPlans.forEach(item=>ids.push(item.id))
			// 	this.$emit('make_dispatching',this.product.product_id,ids)
			// },
			selectionChange(data){
				this.selectedProductionPlans=data
			},
			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length=columns.length
				let total=0
				let sumNumber=0
				data.forEach(item=>{
					let amount=Number(item.amount)
					let costPrice=this.product.real_cost_price
					sumNumber+=amount
					if(!isNaN(amount)&&!isNaN(costPrice)){
						total+=amount*costPrice
					}
				})
				sums[length-2]="总数量为:"+sumNumber.toFixed(2)+'个'
				sums[length-1]="总成本价格:"+total.toFixed(2)+'元'
				sums[0] = '合计';
				return sums;
			},
		},computed:{
			totalAmount(){
				let total=0;
				this.selectedProductionPlans.forEach(item=>total+=item.amount)
				return total;
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
		},beforeDestroy() {
			
		}
	}
</script>

<style>
</style>
