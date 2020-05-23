<template>
	<div :span="24" id="product_design">
		<router-view @update="searchProductionPlan"></router-view>
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
				<el-form-item label="生产计划单创建时间:">
					<date-time v-model="params.dateTimeRange"></date-time>
				</el-form-item>
				<el-form-item>
					<el-button native-type="button" v-on:click.prevent="searchProductionPlan" type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
			<div style="line-height: 160px;">
				<el-button type="danger" v-on:click="$router.push({path:'/index/productionPlan.html/insert'})">新增生产产品计划</el-button>
			</div>
		</el-row>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- expand-change当展开行时发生变化 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table @row-dblclick="viewProductionPlanDetail" ref="productionPlan_table" :border="true" :highlight-current-row="true" style="width: 100%" :data="prodctionPlanPage.list"
		 :default-sort="params.orderingRule" @sort-change="sortChange">
			<el-table-column label="生产计划编号" prop="production_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.production_id}}
				</template>
			</el-table-column>
			<el-table-column label="设计人" prop="designer" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row|whetherShow('designer')}}
				</template>
			</el-table-column>
			<el-table-column label="生产理由" prop="production_reason" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.production_reason}}
				</template>
			</el-table-column>
			<el-table-column label="订单创建时间:" prop="register_time" :sortable="custom">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{ scope.row.register_time }}</span>
				</template>
			</el-table-column>
			<el-table-column label="备注:">
				<template slot-scope="scope">
					<span style="margin-left: 10px">{{ scope.row|whetherShow('remark') }}</span>
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
					<el-button type="primary" title="编辑" icon="el-icon-edit" circle></el-button>
					<el-button type="danger" title="删除" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;" background :current-page.sync="params.pageNum" layout="total,prev, pager, next,jumper,slot"
			 :total="prodctionPlanPage.total" :page-size="prodctionPlanPage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{prodctionPlanPage.pages}}</span>
			</el-pagination>
		</div>
		<div v-show="showDetail&&prodctionPlanPage.list.length>0">
			<h6>生产计划详细:<span style="color: red;">{{activeProductionPlan.production_id}}</span></h6>
			<production-plan-detail :id="activeProductionPlan.id"></production-plan-detail>
		</div>
	</div>
</template>

<script>
	import production_plan_detail from '@/components/production/production_plan_detail.vue'
	import dateTime from '@/components/dateTime.vue'
	export default{
		name:'production_plan',
		data(){
			return{
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'production_id',
						order: 'descending'
					},dateTimeRange:[]
				},prodctionPlanPage:{},
				activeProductionPlan:{},
				showDetail:false,
				custom:'custom'
			}
		},methods:{
			viewProductionPlanDetail(data){
				if(this.activeProductionPlan.id!=undefined&&data.id==this.activeProductionPlan.id){
					this.showDetail=!this.showDetail
				}else{
					this.activeProductionPlan=data;
					this.showDetail=true
				}
			},
			searchProductionPlan(){
				this.$axios.post('/api/productionPlan/search',this.params)
					.then(response=>{
						this.prodctionPlanPage=response.data
					})
			},anewAudit(id){
				let fullPath = this.$route.fullPath;
				this.$router.push({path:`${fullPath}/update/${id}`})
			},sortChange(order){
				this.params.orderingRule=order
				this.searchProductionPlan()
			},currentPageNumChange(pageNum){
				this.params.pageNum=pageNum
				this.searchProductionPlan()
			}
		},created() {
			this.searchProductionPlan()
		},components:{
			'date-time':dateTime,
			'production-plan-detail':production_plan_detail
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
					return parseFloat(numerical).toFixed(2)+' '+suffix
				}
				return parseFloat(numerical).toFixed(2)
			},total(number1,number2,suffix){
				let totalNumber=parseFloat(number1)*parseFloat(number2)
				return totalNumber.toFixed(2)+' '+suffix
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
