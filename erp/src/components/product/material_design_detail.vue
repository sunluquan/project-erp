<template>
	<el-table  :summary-method="getSummaries" show-summary  :border="true" ref="material_design" :highlight-current-row="true" style="width: 100%" :data="details"
	 :default-sort="{prop: 'product_id', order: 'descending'}" >
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
		<el-table-column label="单位" >
			<template slot-scope="scope">
				{{scope.row|whetherShow('product_unit','无')}}
			</template>
		</el-table-column>
		<el-table-column label="数量" prop="amount" sortable>
			<template slot-scope="scope"  >
				{{scope.row.amount|formNumerical('个')}}
			</template>
		</el-table-column>
		<el-table-column label="价格" prop="cost_price" sortable>
			<template slot-scope="scope">
				{{scope.row.cost_price|formNumerical('元')}}
			</template>
		</el-table-column>
		<el-table-column label="合计" >
			<template slot-scope="scope">
				{{scope.row.cost_price*scope.row.amount|formNumerical('元')}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	export default{
		name:'material_design_detail',//产品物料详细的展示
		props:['id'],
		data(){
			return{
				details:[],
				mapCache:{}
			}
		},methods:{
			getMaterialDesignDetail(id){//点击表格行时触发的事件
				if(!this.mapCache.has(id)){
					this.$axios.get('/api/materialDesignDetail/byParentId/'+id)
						.then(response=>{
							this.mapCache.set(id,response.data)
							this.details = response.data
						})
				}else{
					this.details = this.mapCache.get(id);
				}
			},getSummaries(param) {
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
					// if(index==length-1){//说明是最后一行合计
					// 	sums[index]='总价格:'+
					// }
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
						if (index == length - 1) {
							let total = 0;
							data.forEach((item => {
								let amount = Number(item.amount)
								let price = Number(item.cost_price)
								if (!isNaN(amount) && !isNaN(price)) {
									total += (amount * price);
								}
							}))
							sums[index] = '物料总成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = 'N/A';
						}
			
					}
				});
				return sums;
			},
		},created() {
			this.mapCache=new Map()
		},watch:{
			id(newVal){
				if(newVal){
					this.getMaterialDesignDetail(newVal)
				}
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
					return parseInt(numerical).toFixed(2)+suffix;
				}
				return parseInt(numerical).toFixed(2);
			}
		}
	}
</script>

<style>
</style>
