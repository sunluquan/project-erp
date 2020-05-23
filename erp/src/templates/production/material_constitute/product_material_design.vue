<template>
	<div id="product_design">
		<router-view @update="search"></router-view>
		<div>
			<el-row type="flex" class="row-bg" justify="space-around">
				<el-form :inline="true" class="demo-form-inline">

					<el-form-item label="物料订单号">
						<el-input v-model="params.keyWord" placeholder="物料订单号"></el-input>
					</el-form-item>
					<el-form-item label="页面大小">
						<el-select v-model="params.pageSize" placeholder="页面大小">
							<el-option v-for="(pageSize,index) in pageSizes" v-bind:key="index" v-bind:value="pageSize">
								{{pageSize}}
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button native-type="button" v-on:click.prevent="search" type="primary" icon="el-icon-search">搜索</el-button>
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
		<el-table @row-dblclick="getMaterialDesignDetail" ref="product_table" :border="true" :highlight-current-row="true" style="width: 100%" :data="materialDesignPage.list"
		 :default-sort="params.orderingRule" @sort-change="sortChange" >
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
			<el-table-column label="物料设计单编号" prop="design_id" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row.design_id}}
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
			<el-table-column label="设计人" prop="designer" :sortable="custom">
				<template slot-scope="scope">
					{{scope.row|whetherShow('designer','暂无设计人')}}
				</template>
			</el-table-column>
			<el-table-column label="信息">
				<template slot-scope="scope">
					{{scope.row|whetherShow('module_describe','无')}}
				</template>
			</el-table-column>
			<!-- <el-table-column label="产品类型" prop="product_kind_id" :sortable="custom">
					<template slot-scope="scope">
						{{scope.row.kindName}}
					</template>
				</el-table-column> -->
			<!-- <el-table-column label="产品档次级别">
					<template slot-scope="scope">
						{{scope.row|whetherShow('product_rank','暂无资源数据')}}
					</template>
				</el-table-column> -->
			<el-table-column label="登记人">
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
							<el-tag type="danger">审核未通过</el-tag>
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
			 :total="materialDesignPage.total" :page-size="materialDesignPage.pageSize" @current-change="currentPageNumChange">
				<span>&nbsp;&nbsp;总页数:{{materialDesignPage.pages}}</span>
			</el-pagination>
		</div>
		<!-- <h6>产品物料设计单详细</h6>
		<material-design-detail :id="id"></material-design-detail> -->
		
		<div v-show="showDetail&&materialDesignPage.list.length>0">
			<h6>产品物料设计单详细:<span style="color: red;">{{activeMaterial.design_id}}</span></h6>
			<material-design-detail :id="activeMaterial.id"></material-design-detail>
		</div>
	</div>
</template>

<script>
	import material_design_detail from '@/components/product/material_design_detail.vue'
	export default {
		name: 'product_material_design',//查询所有的产品物料信息单
		data() {
			return {
				params: {
					pageNum: 1,
					pageSize: 5,
					keyWord: '',
					orderingRule: {
						prop: 'design_id',
						order: 'descending'
					}
				},
				materialDesignPage:{},
				id:'',
				custom:'custom',
				activeMaterial:{
					id:''
				},showDetail:false
			}
		},
		methods: {
			anewAudit(id){
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/update/${id}`})
			},
			getMaterialDesignDetail(data){//
				if(data.id==this.activeMaterial.id){
					this.showDetail=!this.showDetail
				}else{
					this.showDetail=true
					this.activeMaterial=data//JSON.parse(JSON.stringify(data))
				}
			},
			currentPageNumChange(pageNum){
				this.params.pageNum=pageNum
				this.search()
			},sortChange(order){
				this.params.orderingRule=order
				this.search()
			},search(){
				this.$axios.post('/api/materialDesign/search',this.params)
					.then(response=>{
						this.materialDesignPage=response.data
					})
			}
		},
		created() {
			this.search()
		},
		computed: {
			pageSizes() {
				return this.$store.getters.pageSizes
			}
		},components:{
			"material-design-detail":material_design_detail
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
					return parseInt(numerical).toFixed(2)+suffix;
				}
				return parseInt(numerical).toFixed(2);
			}
		}
	}
</script>

<style>
</style>
