<template>
	<el-col :span="24" id="product_design">
		<router-view @update="searchProductPage"></router-view>
		<!-- 树形菜单控件 -->
		<el-col :span="4" style="margin-top: 50px;">
			<h3>产品类别</h3>
			<el-tree :data="productKinds" @node-click="kindChange" :props="kindProps">
			</el-tree>
		</el-col>
		<el-col :span="20">
			<el-row type="flex" class="row-bg" justify="space-around">
				<el-form :inline="true" class="demo-form-inline">

					<el-form-item label="产品名称">
						<el-input v-model="params.keyWord" placeholder="产品名称"></el-input>
					</el-form-item>
					<el-form-item label="页面大小">
						<el-select v-model="params.pageSize" placeholder="页面大小">
							<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
								{{pageSize}}
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button native-type="button" v-on:click.prevent="searchProductPage" type="primary" icon="el-icon-search">搜索</el-button>
					</el-form-item>
				</el-form>
				<div>
					<el-button type="danger" v-on:click="$router.push({path:'/index/product.html/insert'})">新增产品设计</el-button>
				</div>
			</el-row>
			<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
			 <!-- expand-change当展开行时发生变化 -->
			<!-- :default-sort="sort_regulation" -->
			<el-table ref="product_table" :border="true" :highlight-current-row="true" style="width: 100%" :data="productPage.list" :default-sort="{prop: 'product_id', order: 'ascending'}"
			 @sort-change="sortChange" @expand-change="expandChange">
				<el-table-column type="expand">
					<template slot-scope="props" label="详细">
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
				<el-table-column label="产品编号" prop="product_id" :sortable="custom">
					<template slot-scope="scope">
						{{scope.row.product_id}}
					</template>
				</el-table-column>
				<el-table-column label="产品名称" prop="product_name" :sortable="custom">
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

				<el-table-column label="市场价格" prop="list_price" :sortable="custom">
					<template slot-scope="scope">
						{{scope.row.list_price|formNumerical}}
					</template>
				</el-table-column>
				<el-table-column label="计划成本价" prop="cost_price" :sortable="custom">
					<template slot-scope="scope">
						{{scope.row.cost_price|formNumerical}}
					</template>
				</el-table-column>
				<el-table-column label="实际成本价" prop="real_cost_price" :sortable="custom">
					<template slot-scope="scope">
						{{scope.row.real_cost_price|formNumerical}}
					</template>
				</el-table-column>
				<el-table-column label="登记人" >
					<template slot-scope="scope">
						{{scope.row.register}}
					</template>
				</el-table-column>
				<!-- <el-table-column label="创建时间" prop="create_time" :sortable="custom">
					<template slot-scope="scope">
						<i class="el-icon-time"></i>
						<span style="margin-left: 10px">{{ scope.row.create_time }}</span>
					</template>
				</el-table-column> -->
				<el-table-column label="状态" prop="check_tag" :sortable="custom">
					<template slot-scope="scope">
						<template v-if="scope.row.check_tag==0">
							<el-tag effect="dark" type="info">审核中...</el-tag>
						</template>
						<template v-else-if="scope.row.check_tag==1">
							<el-tag type="success" effect="dark">审核已通过</el-tag>
						</template>
						<template v-else>
							<a title="点击重新提交审核" @click="anewAudit(scope.row.id)">
								<el-tag type="danger" >审核未通过</el-tag>
							</a>
						</template>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="120px">
					<template slot-scope="scope">
						<!-- circle是否为圆形按钮  false -->
						<el-button type="primary" title="编辑" icon="el-icon-edit" circle></el-button>
						<el-button type="danger" title="删除" icon="el-icon-delete" circle></el-button>
					</template>
				</el-table-column>
			</el-table>
			<br />
			<div class="block">
				<el-pagination style="text-align: right;" background :current-page.sync="params.pageNum" layout="total,prev, pager, next,jumper,slot"
				 :total="productPage.total" :page-size="productPage.pageSize" @current-change="currentPageNumChange">
					<span>&nbsp;&nbsp;总页数:{{productPage.pages}}</span>
				</el-pagination>
			</div>
		</el-col>

	</el-col>
</template>

<script>
	export default {
		name: 'product_design',
		data() {
			return {
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'product_id',
						order: 'ascending'
					}
				},
				productPage: {},
				custom: 'custom',
				kindProps: {
					label: 'kindName',
					children: 'children',

				},
				productKinds: [{
					id: null,
					kindName: '所有的产品类别',
					children: []
				}], //树状类别数据
				check: null,
				checkKinds: [],
				active_product:{},//当前的展开行
				current_path:''
			}
		},watch:{
		},
		methods: {
			anewAudit(id){
				let fullPath=this.$route.fullPath;
				this.$router.push({path:`${fullPath}/update/${id}`})
				
			},
			kindChange(data) {
				//如果他们相等就说明  这个已经是突出选中的了
				if (this.check == data.id) return
				// if(data.id==null){//说明是根级节点 直接查询所有就行了
				// 	this.checkKinds=[];
				// }else{
				// 	this.check = data.id;
				// 	if(this.checkKinds.length>0){
				// 		this.checkKinds=[]
				// 	}
				// 	this.checkKinds.push(data.id)
				// 	this.getKindChildren(data.children);
				// }
				//不管怎么样都需要清空
				this.checkKinds = [];
				if (data.children && data.children.length > 0) {
					this.getKindChildren(data.children);
				} else {
					this.checkKinds.push(data.id);
				}
				this.searchProductPage();
			},
			getKindChildren(data) {
				//下面这个是获取  这个节点下面所有的节点id
				//但是因为  所选的商品类别永远最下面的子节点
				//但是最下面的子节点可能也有多个所以还是需要数组接收
				//if(data&&data.length>0){
				data.forEach(item => {
					if (!item.children || !item.children.length > 0) {
						this.checkKinds.push(item.id);
					} else {
						this.getKindChildren(item.children);
					}

				})
				//}
			},
			currentPageNumChange(pageNum) {
				this.params.pageNum = pageNum
				this.searchProductPage()
			},
			sortChange(sort) {
				this.params.orderingrule = sort;
				this.searchProductPage();
			},
			searchProductPage() {
				//let params=this.$Qs.stringify(this.params)this.checkKinds
				this.$axios.post('/api/product/search', {
						"params": this.params,
						"checkKinds": this.checkKinds
					})
					.then(response => {
						this.productPage = response.data;
					})
			},
			getProductKinds() {
				this.$axios.get('/api/productKind/getKinds')
					.then(response => {
						this.productKinds[0].children = response.data;
					})
			},expandChange(row, expandedRows) {
				//说明点击的是同一个展开行
				if (this.active_product != '' && this.active_product.id == row.id) {
					this.active_product='';
				} else {
					//说明打开了多个展开行 关闭之前哪一个
					if (this.active_product != '')
						this.$refs.product_table.toggleRowExpansion(this.active_product, false)
					this.active_product = row;
				}
			},
		},
		computed: {
			pageSizes() {
				return this.$store.getters.getPageSizes;
			}
		},
		created() {
			this.searchProductPage();
			this.getProductKinds();
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
