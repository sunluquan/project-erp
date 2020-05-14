<template>
	<div id="process">
		<router-view @update="searchProcess"></router-view>
		<div>
			<el-row type="flex" class="row-bg" justify="space-around">
				<el-form :inline="true" class="demo-form-inline">

					<el-form-item label="工序设计订单号/产品名称">
						<el-input v-model="params.keyWord" placeholder="工序设计订单号/产品名称"></el-input>
					</el-form-item>
					<el-form-item label="页面大小">
						<el-select v-model="params.pageSize" placeholder="页面大小">
							<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
								{{pageSize}}
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button native-type="button" v-on:click.prevent="searchProcess" type="primary" icon="el-icon-search">搜索</el-button>
					</el-form-item>
				</el-form>
			</el-row>
		</div>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- expand-change当展开行时发生变化 -->
		<!-- :default-sort="sort_regulation" -->
		<!-- @expand-change="expandChange" -->
		<el-table @row-dblclick="viewprocessPageDetail" ref="product_table" :border="true" :highlight-current-row="true"
		 style="width: 100%" :data="processPage.list" :default-sort="params.orderingRule" @sort-change="sortChange">
			<!-- <el-table-column type="expand">
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
			</el-table-column> -->
			<el-table-column label="产品工序设计单编号" prop="process_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.process_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="产品类别名称" prop="product_name" >
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('kindName')}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型" prop="use_type">
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('useName')}}
				</template>
			</el-table-column>
			<el-table-column label="设计人" prop="cost_price" >
				<template slot-scope="scope">
					{{ scope.row|whetherShow('designer')}}
				</template>
			</el-table-column>
			<el-table-column label="设计要求" prop="cost_price" >
				<template slot-scope="scope">
					{{ scope.row|whetherShow('message','无设计要求')}}
				</template>
			</el-table-column>
			<el-table-column label="状态" prop="check_tag" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.check_tag==0">
						<el-tag effect="dark" type="info">审核中...</el-tag>
					</template>
					<template v-else-if="scope.row.check_tag==1">
						<el-tag type="success" effect="dark">审核已通过</el-tag>
					</template>
					<template v-else>
						<el-popover
						    placement="top-start"
						    title="驳回原因"
						    width="200"
						    trigger="hover"
						    :content="scope.row.check_suggestion">
							 <el-link type="danger" @click="anewAudit(scope.row.id)"  slot="reference">
								 <el-tag type="danger" >审核未通过</el-tag>
							 </el-link>
						  </el-popover>
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
			 :total="processPage.total" :page-size="processPage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{processPage.pages}}</span>
			</el-pagination>
		</div>
		<div v-show="showDetail">
			<h6>工序设计详细单:<span style="color: red;">{{activeprocess.processPage_id}}</span></h6>
			<process-design-detail :id="activeprocess.id"></process-design-detail>
		</div>
	</div>
</template>

<script>
	import process_design_detail from '@/components/product/process_design_detail.vue'
	export default {
		name: 'process', //所有的工序单查看页面
		data() {
			return {
				showDetail: false,
				activeprocess: {
					id: ''
				},
				processPage: {},
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'process_id',
						order: 'descending'
					}
				},custom:'custom'
			}
		},
		methods: {
			viewprocessPageDetail(data) { //查看工序设计单的详细信息
				if (data.id == this.activeprocess.id) {
					this.showDetail=!this.showDetail
				} else {
					this.showDetail = true
					this.activeprocess = JSON.parse(JSON.stringify(data));
				}

			},
			sortChange(order) {//排序改变
				this.params.orderingRule = order;
				this.searchProcess()
			},
			searchProcess() {//按条件分页查询
				this.$axios.post('/api/processDesign/search', this.params)
					.then(response => {
						this.processPage = response.data
					})
			},
			currentPageNumChange(pageNum) {//当前页数改变
				this.params.pageNum = pageNum
				this.searchProcess()
			},anewAudit(id){//审核未通过
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/update/${id}`})
			}
		},
		created() {
			this.searchProcess()
		},
		components: {
			'process-design-detail': process_design_detail
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
			formNumerical(numerical, fixed, suffix) {
				//保留两位小数
				return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},computed:{
			pageSizes(){
				return this.$store.getters.getPageSizes
			}
		}
	}
</script>

<style>
</style>
