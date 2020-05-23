<template>
	<!--出库申请查询页面-->
	<div id="stockOutSelect">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				<el-form-item label="名称">
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
					<el-button native-type="button" v-on:click.prevent="searchSpay"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
		</el-row>
		<el-table :data="spayPage.list" border style="width: 100%" 
		:default-sort = "params.orderingRule"
		@sort-change="sortChange"
		>
			<el-table-column prop="payId" label="申请单编号"  :sortable="custom">
				<template slot-scope="scope">
					<span>{{scope.row.payId}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="reasonexact" label="出库理由" :sortable="custom">
				<template slot-scope="scope">
					<span>{{scope.row.reasonexact}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="registerTime" label="登记时间" :sortable="custom">
				<template slot-scope="scope">
					<span>{{scope.row.registerTime}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="amountSum" label="总件数" :sortable="custom">
				<template slot-scope="scope">
					<span>{{scope.row.amountSum}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="costPriceSum" label="总金额" :sortable="custom">
				<template slot-scope="scope">
					<span>{{scope.row.costPriceSum}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="checkTag" label="审核状态" :sortable="custom">
				<template slot-scope="scope">
					<template v-if="scope.row.checkTag=='审核通过'">
						<el-tag  type="success" >
							<span>{{scope.row.checkTag}}</span>
						</el-tag>
					</template>
					<template v-else-if="scope.row.checkTag=='审核未通过'">
						<el-tag  type="warning" >
							<span>{{scope.row.checkTag}}</span>
						</el-tag>
					</template>
					<template v-else-if="scope.row.checkTag=='等待审核'">
						<el-tag  type="danger">
							<span>{{scope.row.checkTag}}</span>
						</el-tag>
					</template>
				</template>
			</el-table-column>
			<el-table-column prop="" label="查看详情">
				<template slot-scope="scope">
					<el-button type="text" @click="sel(scope.row.payId)">查看详情</el-button>
				</template>
			</el-table-column>
		</el-table>
		
		<br />
		<div class="block">
			<el-pagination style="text-align: right;"
			  background
			  :current-page.sync="params.pageNum"
			  layout="total,prev, pager, next,jumper,slot"
			  :total="spayPage.total"
			  :page-size="spayPage.pageSize"
			  @current-change="currentPageNumChange">
			  <span>&nbsp;&nbsp;总页数:{{spayPage.pages}}</span>
			</el-pagination>
		</div>
	</div>

</template>

<script>
	export default {
		name: 'stockOutSelect',
		data() {
			return {
				spay: [],
				params:{
					pageNum:1,
					pageSize:5,
					keyWord:'',
					orderingRule:{
						prop:'payId',
						order:'descending'
					}
				},spayPage:{},
				custom:'custom'
			}
		},
		methods: {
			sortChange(order){
				this.params.orderingRule=order
				this.searchSpay()
			},
			currentPageNumChange(pageNum){
				this.params.pageNum=pageNum;
				this.searchSpay()
			},
			searchSpay(){
				this.$axios.post('/api/stockOut/searchSpay',this.params)
					.then(response=>{
						this.spayPage=response.data
					})
			},
			selectAll() {
				this.$axios.post("/api/stockOut/selectAll").then(response => {
					this.spay = response.data;
				}).catch(err => {
					alert('请求失败')
				})
			},
			sel(id){
				this.$router.push({path:'/index/stockOutSelect.html/stockOutSelectAll/'+id})
			}
		},
		created() {
			this.searchSpay()
			//this.selectAll();
		},
		computed: {
			pageSizes(){
				return this.$store.getters.getPageSizes;
			}
		},
		filters: {

		},
	}
</script>

<style>
</style>
