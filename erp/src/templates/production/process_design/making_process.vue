<template>
	<div id="making_process">
		<router-view @update="getAccordMakingProcess"></router-view>
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
					<el-button type="primary" size="small" title="制定工序单" @click="makingProcess(scope.row)">制定工序单</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default{
		name:'making_process',//制定工序
		data(){
			return{
				products:[],
				keyWord:''
			}
		},methods:{
			getAccordMakingProcess(){//获取符合制定工序条件的产品集合
				this.$axios.get('/api/product/accordMakingProcess')
					.then(repsonse=>{
						this.products=repsonse.data
					})
			},makingProcess(data){//制定工序单
				//makingProcess.html/design/id
				let fullPath=this.$route.fullPath
				let params={id:data.id,product_id:data.product_id,product_name:data.product_name}
				params=JSON.stringify(params)
				this.$router.push({path:`${fullPath}/design/${params}`})
			}
		},created() {
			this.getAccordMakingProcess()
		},computed:{
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
