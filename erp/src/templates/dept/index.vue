<template>
	<div id="dept">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				<el-form-item label="部门名称">
					<el-input v-model="params.keyWord" placeholder="部门名称"></el-input>
				</el-form-item>
				<el-form-item label="页面大小">
					<el-select v-model="params.pageSize" placeholder="页面大小">
						<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
							{{pageSize}}
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					
					<el-button native-type="button" v-on:click.prevent="searchdept"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
			<div>
				<el-button type="danger" v-on:click="insertdept">新增部门</el-button>
			</div>
		</el-row>
		<!-- 
		 default-sort  默认的排序 是一个对象
		 属性:prop:指定的排序列名 order:排序方式
		 有 ascending or descending
		 -->
		 <!-- :default-sort="sort_regulation" -->
		<el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="deptPage" 
		 :default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange">
		 
			<el-table-column label="部门编号" prop="deptId" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.deptId}}
				</template>
			</el-table-column>
			<el-table-column label="部门名称">
				<template slot-scope="scope">
					{{scope.row.deptName}}
				</template>
			</el-table-column>

			<el-table-column label="部门说明" prop="deptRemark" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.deptRemark}}
				</template>
			</el-table-column>
			
			<el-table-column label="排序号" prop="sort" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.sort}}
				</template>
			</el-table-column>			
			<el-table-column label="操作" width="120px">
			  <template slot-scope="scope">
				  <!-- circle是否为圆形按钮  false -->
			    <el-button type="primary" title="编辑" icon="el-icon-edit" circle @click="updatedept(scope.row.deptId)"></el-button>
				<el-button type="danger" @click="delete(scope.row.deptd)" title="删除" icon="el-icon-delete" circle></el-button>
				
			  </template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;"
			  background
			  :current-page.sync="params.pageNum"
			  layout="total,prev, pager, next,jumper,slot"
			  :total="deptPage.total"
			  :page-size="deptPage.pageSize"
			  @current-change="currentPageNumChange">
			  <span>&nbsp;&nbsp;总页数:{{deptPage.pages}}</span>
			</el-pagination>
		</div>
		
	</div>
</template>

<script>
	export default {
		name: 'dept',
		data() {
			return {
				custom:'custom',
				sort:1,
				deptPage:[],
				params:{
					pageNum:1,
					pageSize:5,
					keyWord:'',
					orderingRule:{
						prop:'sort',
						order:'ascending'
					}
				}
			}
		},		
		methods: {
			sortChange(sort){
				this.searchdept();
				this.params.orderingRule.prop=sort.prop;
				this.params.orderingRule.order=sort.order;
				
			},
			searchdept(){			
				this.$axios.post('/api/dept/getAllDept').then(response=>{
						this.deptPage=response.data;		
					})
			},
			insertdept(){
				this.$router.push({path:'/index/dept.html/insert'})
			},
			updatedept(deptId){//编辑权限
				this.$router.push({path: `/index/dept.html/update/${deptId}`})
			},
			currentPageNumChange(pageNum){//当前页改变时触发
				this.params.pageNum=pageNum;
				this.searchdept();
			}
		},
		created() {
			this.searchdept();
		},computed:{
			pageSizes(){
				return this.$store.getters.getPageSizes;
			}
		},filters:{
			whetherShow(obj,property,message){
				if(message==undefined||message=='')
					message="无"
				if(obj==''||obj==null){
					return message;
				}
				if(obj[property]==''||obj[property]==null)
					return message;
				return obj[property];
			}
		}
	}
</script>

<style>
</style>
