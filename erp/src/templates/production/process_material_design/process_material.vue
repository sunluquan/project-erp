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
					<el-form-item label="工序单创建时间:">
						<date-time v-model="params.dateTimeRange"></date-time>
						<!-- <el-date-picker v-model="params.dateTimeRange" value-format="yyyy-MM-dd HH:mm:ss"  type="datetimerange" align="right" start-placeholder="开始日期" end-placeholder="结束日期"
						 :default-time="['00:00:00', '23:59:59']">
						</el-date-picker> -->
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
			<el-table-column label="产品类别名称" prop="product_name">
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('kindName')}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型" prop="use_type">
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('useName')}}
				</template>
			</el-table-column>
			<el-table-column label="设计人" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row|whetherShow('designer')}}
				</template>
			</el-table-column>
			<el-table-column label="设计要求" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row|whetherShow('message','无设计要求')}}
				</template>
			</el-table-column >
			<el-table-column label="创建时间" prop="register_time" :sortable="custom">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{ scope.row.register_time }}</span>
				</template>
			</el-table-column>
			<el-table-column label="工序物料设计状态" prop="check_tag" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.process_material_tag==0">
						<el-tag effect="dark" type="info">审核中...</el-tag>
					</template>
					<template v-else-if="scope.row.process_material_tag==1">
						<el-tag type="success" effect="dark">审核已通过</el-tag>
					</template>
					<template v-else-if="scope.row.process_material_tag==2">
						<el-popover placement="top-start" title="驳回原因" width="200" trigger="hover" :content="scope.row.check_suggestion">
							<el-link type="danger" title="点击重新设计工序物料" @click="anewAudit(scope.row.id)" slot="reference">
								<el-tag type="danger">审核未通过</el-tag>
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
	import date_time from '@/components/dateTime.vue'
	import process_design_detail from '@/components/product/process_design_detail.vue'
	export default {
		name: 'process_material', //所有的工序单查看页面
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
					},dateTimeRange:[]
				},custom:'custom'
			}
		},
		methods: {
			viewprocessPageDetail(data) { //查看工序设计单的详细信息
				if (data.id == this.activeprocess.id) {
					this.showDetail = !this.showDetail
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
				this.$axios.post('/api/processDesign/searchMaterial', this.params)
					.then(response => {
						this.processPage = response.data
						console.log(this.processPage)
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
			'process-design-detail': process_design_detail,
			'date-time':date_time
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
