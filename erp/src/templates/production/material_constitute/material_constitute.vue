<template>
	<div id="checker_product_design">
		<router-view @update="getProducts"></router-view>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table :border="true" ref="product_table" :highlight-current-row="true" style="width: 100%" :data="search"
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
			<el-table-column label="产品类别名称" prop="product_name" sortable>
				<template slot-scope="scope">
					{{scope.row.kindName}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型" prop="use_type">
				<template slot-scope="scope">
					{{scope.row.useName}}
				</template>
			</el-table-column>
			<el-table-column label="市场价格" prop="list_price" sortable>
				<template slot-scope="scope">
					{{scope.row.list_price|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="计划成本价" prop="cost_price" sortable>
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="计量单位" prop="cost_price" sortable>
				<template slot-scope="scope">
					{{ scope.row|whetherShow('product_unit','无') }}
				</template>
			</el-table-column>
			<el-table-column label="相关信息" prop="cost_price" sortable>
				<template slot-scope="scope">
					{{ scope.row|whetherShow('message','无相关信息') }}
				</template>
			</el-table-column>
			<el-table-column align="right" width="100px">
				<template slot="header" slot-scope="scope">
					<el-input v-model="keyWord" placeholder="产品编号或名称" />
				</template>
				<template slot-scope="scope">
					<el-button type="primary" size="small" title="制定设计单" @click="materialConstitue(scope.row)">制定设计单</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	 export default{
		name: 'material_constitute',//产品物料设计
		data() {
			return {
				products: [],
				keyWord: ''
			}
		},
		methods: {
			materialConstitue(product){
				let obj={
					id:product.id,
					product_id:product.product_id,
					product_name:product.product_name,
					}
				let params=this.$Qs.stringify(obj)
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/design/${params}`})
			},
			getProducts() {
				this.$axios.get('/api/product/wantMaterialDesign')
					.then(response => {
						this.products = response.data
					})
			}
		},
		created() {
			this.getProducts()
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
			formNumerical(numerical) {
				//保留两位小数
				return parseInt(numerical).toFixed(2);
			}
		},
		computed: {
			search() {
				let keyWord=this.keyWord
				if (keyWord == '') {
					return this.products
				} else {
					return this.products.filter(product => {
						return product.initial.includes(keyWord) ||
							product.product_name.includes(keyWord) ||
							product.product_id.includes(keyWord)
					})
				}
			}
		}
	}
</script>

<style>
</style>
