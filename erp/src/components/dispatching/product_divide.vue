<template>
	<el-table size="small" :border="true" 
	ref="dispatching_table"
	:highlight-current-row="true" 
	style="width: 100%" :data="product" 
	:default-sort="{prop:'product_id',order:'descending'}"
	@expand-change="expandChange"
	>
		<el-table-column type="expand">
			<template slot-scope="props">
				<product-production @make_dispatching="makeDispatching" @close="close" :product="props.row"></product-production>
			</template>
		</el-table-column>
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

		<el-table-column label="产品类型" prop="product_kind_id" sortable>
			<template slot-scope="scope">
				{{scope.row.kindName}}
			</template>
		</el-table-column>

		<el-table-column label="用途类型" prop="use_type" sortable>
			<template slot-scope="scope">
				{{scope.row.useName}}
			</template>
		</el-table-column>

		<el-table-column label="产品档次级别">
			<template slot-scope="scope">
				{{scope.row|whetherShow('product_rank','暂无资源数据')}}
			</template>
		</el-table-column>
		<el-table-column label="计划成本价" prop="cost_price" sortable>
			<template slot-scope="scope">
				{{scope.row.cost_price|formNumerical('元')}}
			</template>
		</el-table-column>
		<el-table-column label="实际成本价" prop="real_cost_price" sortable>
			<template slot-scope="scope">
				{{scope.row.real_cost_price|formNumerical('元')}}
			</template>
		</el-table-column>
		<el-table-column label="信息" prop="message">
			<template slot-scope="scope">
				{{scope.row|whetherShow('message','无')}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	import product_production from '@/components/dispatching/product_production.vue'
	export default {
		name: 'product_divide', //按产品划分,
		data() {
			return {
				product: [],
				activeProduct:'',
			}
		},
		methods: {
			makeDispatching(id,data){
				this.$emit('make_dispatching',id,data)
			},
			close(){
				this.$refs.dispatching_table.toggleRowExpansion(this.activeProduct,false)
				this.activeProduct=''
			},
			getMeetDispatchingProducts() {
				this.$axios.get('/api/product/meetDispatching')
					.then(response => {
						this.product = response.data
					})
			},expandChange(rowData){//当用户对某一行展开或者关闭时
				//dispatching_table
				if(this.activeProduct!=''&&this.activeProduct.id==rowData.id){
					this.activeProduct=''
				}else{
					if(this.activeProduct!=''){
						this.$refs.dispatching_table.toggleRowExpansion(this.activeProduct,false)
					}
					this.activeProduct=rowData
				}
			}
		},
		created() {
			this.getMeetDispatchingProducts()
		},
		filters: {
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
					return parseInt(numerical).toFixed(2)+' '+suffix;
				}
				return parseInt(numerical).toFixed(2)
			}
		},
		components: {
			"product-production": product_production
		}
	}
</script>

<style >
	.hahaha .el-checkbox {
		display:none;
	}
</style>
