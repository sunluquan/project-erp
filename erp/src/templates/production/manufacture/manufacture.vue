<template>
	<div id="manufacture">
		<router-view @update="searchManufacture"></router-view>
<<<<<<< HEAD
		<!-- 查看派工单 生产工序过程和生产工序物料过程 -->
		<view-manufacture v-model="dialog_visible" :manufacture="activeManufacture"></view-manufacture>
		<!-- 查看派工单 按时间生产先后顺序 -->
		<view-production-progress ref="production_progress" :manufacture="activeManufacture"></view-production-progress>
=======
		<view-manufacture v-model="dialog_visible" :manufacture="activeManufacture"></view-manufacture>
>>>>>>> warehouse
		<!-- {{activeManufacture}}
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
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
				<el-form-item label="计划单创建时间:">
					<date-time v-model="params.dateTimeRange"></date-time>
				</el-form-item>
				<el-form-item>
					<el-button native-type="button" v-on:click.prevent="searchManufacture" type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
		</el-row>
		<el-table @row-dblclick="viewActiveManufacture" :border="true" ref="manufacture_table" :highlight-current-row="true" style="width: 100%" :data="manufacturePage.list"
		 :default-sort="params.orderingRule" @sort-change="sortChange">
			<el-table-column label="制造单编号" prop="manufacture_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.manufacture_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="制定人">
				<template slot-scope="scope">
					{{ scope.row.designer|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="登记人">
				<template slot-scope="scope">
					{{ scope.row.designer}}
				</template>
			</el-table-column>
			<el-table-column label="投产数量" prop="amount" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="设计物料成本" :sortable="custom" prop="material_cost_price_sum">
				<template slot-scope="scope">
					{{scope.row.material_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计工时成本" :sortable="custom" prop="labour_cost_price_sum">
				<template slot-scope="scope">
					{{ scope.row.labour_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="说明" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row.remark|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="核审状态" prop="check_tag" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.check_tag==0">
						<el-tag effect="dark" type="info">审核中...</el-tag>
					</template>
					<template v-else-if="scope.row.check_tag==1">
						<el-tag type="success" effect="dark">审核已通过</el-tag>
					</template>
					<template v-else>
						<el-popover placement="top-start" title="驳回原因" width="200" trigger="hover" :content="scope.row.check_suggestion">
							<el-link type="danger" title="点击重新设计工序物料" @click="anewAudit(scope.row.id)" slot="reference">
								<el-tag type="danger">审核未通过</el-tag>
							</el-link>
						</el-popover>
					</template>
				</template>
			</el-table-column>
			<el-table-column label="生产状态" prop="check_tag" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.manufacture_procedure_tag==-1">
						<el-tag effect="dark" type="info">等待生产</el-tag>
					</template>
					<!-- 0：生产中2：审核中 也说明在生产中 -->
					<template v-else-if="scope.row.manufacture_procedure_tag==0||scope.row.manufacture_procedure_tag==2">
						<el-tag type="info" effect="dark">生产中</el-tag>
					</template>
					<template v-else-if="scope.row.manufacture_procedure_tag==1">
						<el-tag type="success" effect="dark">已完工</el-tag>
					</template>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
<<<<<<< HEAD
					<el-button type="primary"  title="查看进度" @click="viewProductionProgress(scope.row)" icon="el-icon-view" circle></el-button>
					<!-- <el-button type="primary" title="编辑" icon="el-icon-edit" circle></el-button>
					<el-button type="danger" title="删除" icon="el-icon-delete" circle></el-button>
					 -->
=======
					<el-button type="primary" title="编辑" icon="el-icon-edit" circle></el-button>
					<el-button type="danger" title="删除" icon="el-icon-delete" circle></el-button>
>>>>>>> warehouse
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;" background :current-page.sync="params.pageNum" layout="total,prev, pager, next,jumper,slot"
			 :total="manufacturePage.total" :page-size="manufacturePage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{manufacturePage.pages}}</span>
			</el-pagination>
		</div>
	</div>
</template>

<script>
<<<<<<< HEAD
	import view_production_progress from '@/components/manufacture/view_production_progress.vue'
=======
>>>>>>> warehouse
	import view_manufacture from '@/components/dispatching/view_manufacture1.vue'
	//import view_manufacture from '@/components/dispatching/view_manufacture.vue'
	import date_time from '@/components/dateTime.vue'
	export default {
		name: 'manufacture',
		data() {
			return {
				manufacturePage: {},
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'manufacture_id',
						order: 'descending'
					},
<<<<<<< HEAD
=======

>>>>>>> warehouse
				},custom:'custom',
				dialog_visible:false,
				activeManufacture:{}
			}
		},
		methods: {
<<<<<<< HEAD
			viewProductionProgress(data){
				//查看生产工序过程
				this.activeManufacture=data;
				this.$refs.production_progress.open()
			},
=======
>>>>>>> warehouse
			viewActiveManufacture(data){
				this.dialog_visible=true
				this.activeManufacture=data
			},
			searchManufacture() {
				this.$axios.post('/api/manufacture/search', this.params)
					.then(response => {
						this.manufacturePage = response.data
					})
			},
			currentPageNumChange(pageNum) {
				this.params.pageNum = pageNum
				this.searchManufacture()
			},
			sortChange(order) {
				this.params.orderingRule = order;
				this.searchManufacture()
			},
			anewAudit(id) {
				let fullPath = this.$route.fullPath
				this.$router.push({
					path: `${fullPath}/update/${id}`
				})
			}
		},created() {
			this.searchManufacture()
		},computed:{
			pageSizes(){
				return this.$store.getters.getPageSizes
			}
		},components:{
			"date-time":date_time,
<<<<<<< HEAD
			"view-manufacture":view_manufacture,
			"view-production-progress":view_production_progress
=======
			"view-manufacture":view_manufacture
>>>>>>> warehouse
		},filters:{
			whetherShow1(data, message) {
				if (data) {
					return data
				} else {
					if (!message) {
						message = "暂无数据!"
					}
					return message;
				}
			},
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
			formNumerical(numerical, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return parseFloat(numerical).toFixed(2)
			},
			total(number1, number2, suffix) {
				let totalNumber = parseFloat(number1) * parseFloat(number2)
				return totalNumber.toFixed(2) + ' ' + suffix
			}
		}
	}
</script>

<style>
</style>
