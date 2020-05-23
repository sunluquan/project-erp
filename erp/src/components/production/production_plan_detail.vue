<template>
	<el-table style="text-align: center;width: 100%" :border="true" :data="productionPlanDetails"
	 :highlight-current-row="true"
	 :summary-method="getSummaries" show-summary
	 >
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
				{{scope.row.amount|formNumerical()}}
			</template>
		</el-table-column>
		<el-table-column label="合计" >
			<template slot-scope="scope">
				{{scope.row.amount|total(scope.row.real_cost_price,'元')}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	export default{
		name:'production_plan_detail',
		props:{
			id:Number,
			default:()=>(null)
		},
		data(){
			return{
				productionPlanDetails:[]
			}
		},methods:{
			getProductionPlanDetailsByParentId(){
				let id=this.id;
				this.$axios.get(`/api/productionPlanDetail/byParentId/${id}`)
					.then(response=>{
						this.productionPlanDetails=response.data
					})
			},getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length=columns.length
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
						sums[index] =sums[index].toFixed(2);
					} else {
						if(index===length-1){
							let total=0;
							data.forEach(item=>{
								let amount=Number(item.amount)
								let costPrice=Number(item.real_cost_price)
								if(!isNaN(amount)&&!isNaN(costPrice)){
									total+=amount*costPrice
								}
							})
							sums[index]='生产总成本:'+total.toFixed(2)+'元'
						}else{
							sums[index]="N/A"
						}
					}
				});
				return sums;
			},
		},created() {
			
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
		},watch:{
			id(newVal){
				if(newVal){
					this.getProductionPlanDetailsByParentId()
				}
			}
		}
	}
</script>

<style>
</style>
