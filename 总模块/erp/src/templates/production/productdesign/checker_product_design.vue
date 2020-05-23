<template>
	<div id="checker_product_design">
		<router-view @update="getChecker_product"></router-view>
		<!-- <el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">

				<el-form-item label="产品编号或名称">
					<el-input v-model="keyWord" placeholder="产品编号或名称"></el-input>
				</el-form-item>
			</el-form>
		</el-row> -->
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table :border="true" ref="product_table" :highlight-current-row="true" style="width: 100%" :data="search"
		 :default-sort="{prop: 'product_id', order: 'descending'}" @expand-change="expandChange">
			<el-table-column type="expand" label="详细" width="40px">
				<template slot-scope="props">
					<el-form label-position="left" inline class="demo-table-expand">
						<el-form-item label="产品名称:">
							<span>{{ props.row.product_name }}</span>
						</el-form-item>
						<el-form-item label="产品类别:">
							<span>{{ props.row.kindName }}</span>
						</el-form-item>
						<el-form-item label="计量单位:">
							<span>{{ props.row|whetherShow('product_unit','暂无资源数据') }}</span>
						</el-form-item>
						<el-form-item label="商品档次级别:">
							<span>{{ props.row|whetherShow('product_rank','暂无资源数据') }}</span>
						</el-form-item>
						<el-form-item label="创建时间:">
							<span>
								<i class="el-icon-time"></i>
								<span style="margin-left: 10px">{{ props.row.create_time }}</span>
							</span>
						</el-form-item>
						<el-form-item label="变更人:">
							<span>{{ props.row|whetherShow('changer','暂无变更') }}</span>
						</el-form-item>
						<el-form-item label="变更时间:">
							<span>{{ props.row|whetherShow('change_time','暂无变更时间') }}</span>
						</el-form-item>
						<el-form-item label="相关信息:">
							<span>{{ props.row|whetherShow('message','暂无相关信息') }}</span>
						</el-form-item>
					</el-form>
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
			<el-table-column label="实际成本价" prop="real_cost_price" sortable>
				<template slot-scope="scope">
					{{scope.row.real_cost_price|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="登记人" prop="real_cost_price" sortable>
				<template slot-scope="scope">
					{{scope.row.register}}
				</template>
			</el-table-column>
			<el-table-column label="登记时间" prop="create_time" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{ scope.row.create_time }}</span>
				</template>
			</el-table-column>
			<el-table-column align="right" width="100px">
				<template slot="header" slot-scope="scope">
					<el-input v-model="keyWord" placeholder="产品编号或名称" />
				</template>
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="primary" title="核审产品" @click="checker(scope.row.id)" icon="el-icon-edit" circle ></el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		name: 'checker_product_design',
		data() {
			return {
				checker_product: [],
				keyWord: '',
				activeProduct: ''
			}
		},
		methods: {
			checker(id){//核审
			let fullPath=this.$route.fullPath;
				this.$router.push({path:`${fullPath}/confirm/${id}`})
			},
			getChecker_product() {
				this.$axios.get('/api/product/checker')
					.then(response => {
						this.checker_product = response.data;
					})
			},
			expandChange(row, expandedRows) {
				if (this.activeProduct != "" && this.activeProduct.id == row.id) {
					this.activeProduct = ''
				} else {
					if (this.activeProduct != "") {
						this.$refs.product_table.toggleRowExpansion(this.activeProduct, false)
					}
					this.activeProduct = row;
				}
			}
		},
		computed: {
			search() {
				let keyWord = this.keyWord
				if (keyWord == '') {
					return this.checker_product
				}
				return this.checker_product.filter(product => {
					return product.initial.includes(keyWord) ||
						product.product_name.includes(keyWord) ||
						product.product_id.includes(keyWord)
				})
			},getCommunicationData(){//获取通信数据
				return this.$store.websocket.getCommunicationData;
			}
		},watch:{
			
		},
		created() {
			this.getChecker_product();
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
	.demo-table-expand {
	   font-size: 0;
	 }
	 .demo-table-expand label {
	   width: 90px;
	   color: #99a9bf;
	 }
	 .demo-table-expand .el-form-item {
	   margin-right: 0;
	   margin-bottom: 0;
	   width: 50%;
	 }
</style>
