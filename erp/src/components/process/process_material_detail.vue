<template>
	<el-table style="text-align: center;width: 100%" :border="true" :data="materials"
	 :highlight-current-row="true"
	 :summary-method="getSummaries" show-summary
	 >
		<el-table-column label="物料编号">
			<template slot-scope="scope">
				{{scope.row.product_id}}
			</template>
		</el-table-column>
		<el-table-column label="物料名称">
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
				{{scope.row.cost_price|formNumerical('元')}}
			</template>
		</el-table-column>
		<el-table-column label="设计数量" prop="amount">
			<template slot-scope="scope">
				{{scope.row.amount|formNumerical()}}
			</template>
		</el-table-column>
		<el-table-column label="使用数量" prop="useAmount">
			<template slot-scope="scope">
				{{scope.row.useAmount|formNumerical()}}
			</template>
		</el-table-column>
		<el-table-column label="合计" >
			<template slot-scope="scope">
				{{scope.row.useAmount|total(scope.row.cost_price,'元')}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	export default{
		name:'process_material_detail',//点击工序表格的时候 显示的工序物料详细
		props:['id'],
		// props: {
		//         // 子组件接收函数  如果没有就是 undefined
		//         beforeUpload: {
		//             type: Function
		//         }
		//     },
		data(){
			return{
				materials:[],
				activeProcess:{}
			}
		},methods:{
			getProcessMaterialByParentId(){
				let id=this.id
				this.$emit('get_cache_material',id,val=>{this.materials=val})
				if(this.materials.length==0){
					if(id){
						this.$axios.get(`/api/processMaterialDetail/byParentId/${id}`)
							.then(response=>{
								this.materials=response.data
								this.$emit('set_cache_material',this.materials)
							})
					}
				}
				
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
								let useAmount=Number(item.useAmount)
								let costPrice=Number(item.cost_price)
								if(!isNaN(useAmount)&&!isNaN(costPrice)){
									total+=useAmount*costPrice
								}
							})
							sums[index]='物料成本:'+total.toFixed(2)+'元'
						}else{
							sums[index]="N/A"
						}
					}
				});

				return sums;
			},
		},created() {
			this.getProcessMaterialByParentId()
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
