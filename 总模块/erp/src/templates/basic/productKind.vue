<template>
	<div id="role">
		<router-view @update="searchProductKind"></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				<el-form-item label="类别名称">
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
					<el-button native-type="button" v-on:click.prevent="searchProductKind" type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
			<div>
				<el-button type="danger" @click="insertProductKind">新增产品类别</el-button>
			</div>
		</el-row>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
		<!-- expand-change: 树状表格的状态改变时的回调函数-->
		<el-table ref="role_table" :border="true" :highlight-current-row="true" style="width: 100%" :data="productKindPage.list"
		 :default-sort="params.orderingRule" @sort-change="sortChange"
		  >
			<el-table-column label="类别名称" prop="rname" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.kindName}}
				</template>
			</el-table-column>
			<el-table-column label="父类别名称" prop="rname" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.parent|whetherShow('kindName','无父类信息')}}
				</template>
			</el-table-column>
			<el-table-column label="状态" prop="state" :sortable="custom">
				<template slot-scope="scope">
					<el-tooltip v-bind:content="scope.row.state==1?'可用状态':'禁用状态'" placement="top">
						<el-switch @change="prdocutKindStateChange(scope.row)" v-model="scope.row.state" active-color="#13ce66"
						 inactive-color="red" :active-value="1" :inactive-value="2">
						</el-switch>
					</el-tooltip>
				</template>
			</el-table-column>
			<el-table-column label="描述">
				<template slot-scope="scope">
					{{scope.row|whetherShow('describe','暂无描述信息')}}
				</template>
			</el-table-column>
			<el-table-column label="操作" width="120px">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="primary" title="编辑" icon="el-icon-edit" circle @click="compileRole(scope.row.rid)"></el-button>
					<el-button type="danger" @click="deleteProductKind(scope.row.rid)" title="删除" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;"
			 background
			  :current-page.sync="params.pageNum"
			   layout="total,prev, pager, next,jumper,slot"
			 :total="productKindPage.total"
			 :page-size="productKindPage.pageSize"
			 @current-change="currentPageNumChange">
			 <span>&nbsp;&nbsp;&nbsp;总页数:<span>{{productKindPage.pages}}</span></span>
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default{
		name:'productKind',
		data(){
			return{
				productKindPage:{},
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'id',
						order: 'descending'
					}
				},custom:'custom'
			}
		},methods:{
			insertProductKind(){
				let fullPath=this.$route.fullPath;
				this.$router.push({path:`${fullPath}/insert`})
			},
			prdocutKindStateChange(row){
				this.$axios.get('/api/productKind/updateState',
					{id:row.id,state:row.state})
						.then(response=>{
							
						})
			},
			deleteProductKind(id){
				if(confirm('确认删除当条记录吗?')){
					this.$axios.get(`/api/productKind/delete/${id}`)
						.then(response=>{
							
						})
				}
			},
			searchProductKind(){
				this.$axios.post('/api/productKind/search',this.params)
					.then(response=>{
						this.productKindPage=response.data
					})
			},sortChange(order){
				this.params.order=order;
				this.searchProductKind()
			},currentPageNumChange(pageNum){
				this.params.pageNum=pageNum
				this.searchProductKind()
			}
		},created() {
			this.searchProductKind()
		},computed:{
			pageSizes(){
				return this.$store.getters.getPageSizes
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
