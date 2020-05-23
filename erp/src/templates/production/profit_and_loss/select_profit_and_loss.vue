<template>
	<div id="select_profit_and_loss">
		<router-view @update="searchProfitLoss"></router-view>
		<div>
			<el-row type="flex" class="row-bg" justify="space-around">
				<el-form :inline="true" class="demo-form-inline">

					<el-form-item label="关键字查询">
						<el-input v-model="params.keyWord" placeholder="制定单号/产品名称/产品编号/工序名称"></el-input>
					</el-form-item>
					<el-form-item label="页面大小">
						<el-select v-model="params.pageSize" placeholder="页面大小">
							<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
								{{pageSize}}
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="报损报溢单创建时间:">
						<date-time v-model="params.dateTimeRange"></date-time>
						<!-- <el-date-picker v-model="params.dateTimeRange" value-format="yyyy-MM-dd HH:mm:ss"  type="datetimerange" align="right" start-placeholder="开始日期" end-placeholder="结束日期"
						 :default-time="['00:00:00', '23:59:59']">
						</el-date-picker> -->
					</el-form-item>
					<el-form-item>
						<el-button native-type="button" v-on:click.prevent="searchProfitLoss" type="primary" icon="el-icon-search">搜索</el-button>
					</el-form-item>
				</el-form>
			</el-row>
		</div>
		<el-table  :border="true"
		 ref="checker_profit_and_loss_table" :highlight-current-row="true" style="width: 100%"
		  :data="profitLossPage.list"
		 :default-sort="params.orderingRule">
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
			<el-table-column label="产品名称" prop="product_name" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="工序名称" prop="product_name" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.process_name}}
				</template>
			</el-table-column>
			<el-table-column label="登记人" prop="register" :sortable="custom">
				<template slot-scope="scope">
					{{ scope.row.register}}
				</template>
			</el-table-column>
			<el-table-column label="登记时间" prop="register_time" :sortable="custom">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					{{ scope.row.register_time|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="状态" prop="check_tag" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.check_tag==0">
						<el-tag type="" effect="dark">审核中</el-tag>
					</template>
					<template v-if="scope.row.check_tag==1">
						<el-tag type="success" effect="dark">审核通过</el-tag>
					</template>
					<template v-if="scope.row.check_tag==2">
						<el-popover placement="top-start" title="驳回原因" width="200" trigger="hover" :content="scope.row.check_suggestion">
							<el-link type="danger" title="点击重新登记" @click="anewAudit(scope.row.id)" slot="reference">
								<el-tag type="danger">审核未通过</el-tag>
							</el-link>
						</el-popover>
					</template>
				</template>
			</el-table-column>
			<el-table-column label="说明" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row.remark|whetherShow1}}
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;" background :current-page.sync="params.pageNum" layout="total,prev, pager, next,jumper,slot"
			 :total="profitLossPage.total" :page-size="profitLossPage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{profitLossPage.pages}}</span>
			</el-pagination>
		</div>
	</div>
</template>

<script>
	import date_time from '@/components/dateTime.vue'
	export default {
		name: 'select_profit_and_loss',
		data() {
			return {
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'process_id',
						order: 'descending'
					},
					dateTimeRange: []
				},profitLossPage:{},
				custom:'custom',
			}
		},
		methods: {
			searchProfitLoss(){
				this.$axios.post('/api/productProfitLoss/search',this.params)
					.then(response=>{
						this.profitLossPage=response.data
					})
			},currentPageNumChange(order){
				this.params.orderingRule=order
				this.searchProfitLoss()
			},anewAudit(id){
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/update/${id}`})
			}
		},
		created() {
			this.searchProfitLoss()
		},components:{
			'date-time':date_time
		},computed:{
			pageSizes(){
				return this.$store.getters.getPageSizes
			}
		},filters:{
			whetherShow1(data, message) {
				if (message == undefined || message == '')
					message = "无"
				if (data) {
					return data;
				}
				return message
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
		}
	}
</script>

<style>
</style>
