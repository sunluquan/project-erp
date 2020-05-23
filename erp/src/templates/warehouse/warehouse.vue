<template>
	<div id="warehouse">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				<el-form-item label="仓库名称">
					<el-input v-model="keyWords" placeholder="仓库名称或编号"></el-input>
				</el-form-item>
				<el-form-item label="页面大小">
					<el-select v-model="params.pageSize" placeholder="页面大小">
						<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
							{{pageSize}}
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					
					<el-button native-type="button" v-on:click.prevent="cx()"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>
			<div>
				<el-button type="danger" v-on:click="insertwarehouse">新增仓库</el-button>
			</div>
		</el-row>
		<!-- 
		 default-sort  默认的排序 是一个对象
		 属性:prop:指定的排序列名 order:排序方式
		 有 ascending or descending
		 -->
		 <!-- :default-sort="sort_regulation" -->
		<el-table :border="true"
			:highlight-current-row="true"
		    style="width: 100%" :data="warehousePage" 
			:default-sort = "{prop: 'sort', order: 'ascending'}"
			 @sort-change="sortChange">
			<el-table-column label="仓库编号" prop="sid" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.sid}}
				</template>
			</el-table-column>
			<el-table-column label="仓库名称" prop="storeName" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.storeName}}
				</template>
			</el-table-column>
            <el-table-column label="仓库地址" prop="storeAddress" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.storeAddress}}
				</template>
			</el-table-column>
			<el-table-column label="状态" prop="storeStatus" :sortable="custom">
				<template slot-scope="scope">
					<el-tooltip v-bind:content="scope.row.storeStatus==0?'可用状态':'禁用状态'" placement="top">
						<el-switch @change="warehouseStatusChange(scope.row)" v-model="scope.row.storeStatus" active-color="#13ce66" inactive-color="red" 
						  :active-value="0"
						  :inactive-value="1">
						</el-switch>
					</el-tooltip>
				</template>
			</el-table-column>
			<el-table-column label="说明" prop="storeRemark" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.storeRemark}}
				</template>
			</el-table-column>
			<el-table-column label="操作" width="120px">
			  <template slot-scope="scope">
				  <!-- circle是否为圆形按钮  false -->
			    <el-button type="primary" title="编辑" icon="el-icon-edit" circle @click="updateWarehouse(scope.row.sid)"></el-button>
				<el-button type="danger" title="删除" icon="el-icon-delete" circle></el-button>
			  </template>
			</el-table-column>
		</el-table>
		<br />
		<div class="block">
			<el-pagination style="text-align: right;"
			  background
			  :current-page.sync="params.pageNum"
			  layout="total,prev, pager, next,jumper,slot"
			  :total="warehousePage.total"
			  :page-size="warehousePage.pageSize"
			  @current-change="currentPageNumChange">
			  <span>&nbsp;&nbsp;总页数:{{warehousePage.pages}}</span>
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'warehouse',
		data() {
			return {
				custom:'custom',
				warehousePage:[],
				keyWords:'',
				params:{
					pageNum:1,
					pageSize:5,					
					orderingRule:{
						prop:'sort',
						order:'ascending'
					}
				}
			}
		},
		methods: {
			sortChange(sort){
				this.searchWarehouse();
			},
			searchWarehouse(){			
				this.$axios.post('/api/warehouse/getAllHouse')
					.then(response=>{
						this.warehousePage=response.data;
					})
			},
			updateWarehouse(sid){//编辑权限
				this.$router.push({path:'/index/warehouse.html/update/'+sid})
			},
			insertwarehouse(){
				this.$router.push({path:'/index/warehouse.html/insert'})
			},
			warehouseStatusChange(warehouse){//改变状态
				let sid=warehouse.sid;
				let newstoreStatus=warehouse.newstoreStatus;
				this.$axios.get('/api/warehouse/updateStatus/'+sid+'/'+newstoreStatus)
					.then(response=>{
						if(response.statusCord==200){
							//permission.permissionStatus=response.data
							this.$message({
								message:'设置成功!',
								type:'success'
							})
						}else{
							this.$message({
								type:'error',
								message:'设置失败!'
							})
						}
					})
			},
			cx(){
				if(this.keyWords!=''){
									 let storeName=this.keyWords;
									this.$axios.post('/api/warehouse/selectByName/'+storeName).then(response=>{
										alert('神经病');
											 this.warehousePage=response.data;										 
											 	//permission.permissionStatus=response.data
												
											 	this.$message({
											 		message:'查询成功!',
											 		type:'success'
											 	})
											 																			 
									 })
								 }else{
								   this.searchWarehouse();
								 }
			},
			deletePermission(pId){
				this.$confirm('是否删除该条数据?','提示',{
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					center: true
				}).then(()=>{
					this.$axios.get('/api/permission/delete/'+pId)
						.then(response=>{
							if(response.statusCord==200){
								this.$message({
									type:'success',
									message:response.message?'删除成功':response.message
								})
								this.searchPermission();
							}else{
								this.$message({
									type:'error',
									message:'删除失败!'
								})
							}
						})
				}).catch(()=>{
					this.$message({
						type:'info',
						message:'已取消删除!'
					})
				})
			},currentPageNumChange(pageNum){//当前页改变时触发
				this.params.pageNum=pageNum;
				this.searchWarehouse();
			}
		},
		created() {
			this.searchWarehouse();
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
