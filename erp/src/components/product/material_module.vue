<template>
	<el-dialog  width="60%" append-to-body title="物料查询" :before-close="close" :visible.sync="dialog_visible">
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form size="small" :inline="true" class="demo-form-inline">
				<el-form-item label="物料名称">
					<el-input v-model="params.keyWord" placeholder="权限名称"></el-input>
				</el-form-item>
				<el-form-item label="页面大小">
					<el-select v-model="params.pageSize" placeholder="页面大小">
						<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
							{{pageSize}}
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-button native-type="button" v-on:click.prevent="searchMaterials" type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
		</el-row>
		<!-- 
		 default-sort  默认的排序 是一个对象
		 属性:prop:指定的排序列名 order:排序方式
		 有 ascending or descending
		 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table @cell-dblclick="put_and_close" size="small" :border="true" :highlight-current-row="true" style="width: 100%" :data="materialPage.list"
		 :default-sort="params.orderingRule" @sort-change="sortChange">
			<el-table-column label="物料编号" prop="product_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="物料名称" prop="product_name" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>

			<!-- <el-table-column label="产品类型" prop="product_kind_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.kindName}}
				</template>
			</el-table-column> -->

			<el-table-column label="用途类型" prop="use_type" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.useName}}
				</template>
			</el-table-column>

			<!-- <el-table-column label="产品档次级别">
				<template slot-scope="scope">
					{{scope.row|whetherShow('product_rank','暂无资源数据')}}
				</template>
			</el-table-column> -->
			<el-table-column label="计划成本价" prop="cost_price" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="信息" prop="real_cost_price" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row|whetherShow('message','无')}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="success" @click="put(scope.row)" title="添加物料" icon="el-icon-plus" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="block">
			<el-pagination style="text-align: right;" background :current-page.sync="params.pageNum" layout="total,prev, pager, next,jumper,slot"
			 :total="materialPage.total" :page-size="materialPage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{materialPage.pages}}</span>
			</el-pagination>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'material_module',
		model: {
			prop: 'dialog_visible',
			event: 'change'
		},
		props: {
			dialog_visible: Boolean
		},
		data() {
			return {
				material_module_dialog_visible: false,
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'product_id',
						order: 'descending'
					}
				},
				materialPage: {},
				custom: 'custom'
			}
		},
		created() {
			this.searchMaterials()
		},
		computed: {
			pageSizes() {
				return this.$store.getters.getPageSizes;
			},
		},
		methods: {
			put_and_close(row){
				this.put(row);
				this.close()
			},
			put(row){
				this.$emit('put_material',row)
			},
			currentPageNumChange(pageNum){
				this.params.pageNum = pageNum
				this.searchMaterials()
			},
			sortChange(order) {
				this.params.orderingrule = order;
				this.searchMaterials()
			},
			close() {
				this.$emit("change", false)
			},
			searchMaterials() {
				this.$axios.post('/api/material/search', this.params)
					.then(response => {
						this.materialPage = response.data
					})
			}
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
		}
	}
</script>

<style scoped="scoped">
	.el-input__inner{
		/* width:150px */
	}
	
</style>
