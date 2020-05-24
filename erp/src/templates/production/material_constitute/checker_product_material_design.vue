<template>
	<div id="checker_product_design">
		<router-view @update="getMaterialDesigns"></router-view>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" height="300px"-->
		<h6>产品物料设计单</h6>
		<el-table @row-dblclick="getMaterialDesignDetail" max-height="50%" :border="true" ref="material_design" :highlight-current-row="true" style="width: 100%" :data="search"
		 :default-sort="{prop: 'design_id', order: 'descending'}" >
			<el-table-column type="expand">
				<template slot-scope="props" label="详细信息">
					
				</template>
			</el-table-column>
			<el-table-column label="产品物料设计编号" prop="design_id" sortable>
				<template slot-scope="scope">
					{{scope.row.design_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" sortable>
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name" sortable>
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>

			<el-table-column label="设计人" prop="use_type">
				<template slot-scope="scope">
					{{scope.row|whetherShow('designer','暂无设计人')}}
				</template>
			</el-table-column>
			
			<el-table-column label="登记人" >
				<template slot-scope="scope">
					{{scope.row.register}}
				</template>
			</el-table-column>
			<el-table-column align="right" width="100px">
				<template slot="header" slot-scope="scope">
					<el-input v-model="keyWord" placeholder="产品编号或名称" />
				</template>
				<template slot-scope="scope">
					<el-button type="primary" title="核审产品物料组成设计" @click="checker(scope.row.id)" icon="el-icon-edit" circle ></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div v-show="showDetail&&materialDesigns.length>0">
			<h6>产品物料设计单详细:<span style="color: red;">{{activeMaterial.design_id}}</span></h6>
			<material-design-detail :id="activeMaterial.id"></material-design-detail>
		</div>
	</div>
</template>

<script>
	import material_design_detail from '@/components/product/material_design_detail.vue'
	 export default{
		name: 'checker_product_material_design',//产品物料设计审核
		data() {
			return {
				materialDesigns: [],
				keyWord: '',
				showDetail:false,
				activeMaterial:{
					id:''
				}
			}
		},
		methods: {
			getMaterialDesigns() {
				this.$axios.get('/api/materialDesign/checker')
					.then(response => {
						this.materialDesigns = response.data
					})
			},getMaterialDesignDetail(data){
				if(this.activeMaterial.id!=data.id){
					this.activeMaterial=data//JSON.parse(JSON.stringify(data))
					this.showDetail=true
				}else{
					//this.activeMaterial.id=''
					this.showDetail=!this.showDetail
				}
				//this.id=data.id
			},checker(id){
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/confirm/${id}`})
			}
		},
		created() {
			this.getMaterialDesigns()
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
			formNumerical(numerical) {
				//保留两位小数
				return parseInt(numerical).toFixed(2);
			}
		},
		computed: {
			search() {
				let keyWord=this.keyWord
				if (keyWord == '') {
					return this.materialDesigns
				} else {
					return this.materialDesigns.filter(materialDesign => {
						return materialDesign.design_id.includes(keyWord) ||
							materialDesign.product_name.includes(keyWord) ||
							materialDesign.product_id.includes(keyWord)
					})
				}
			}
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
		},components:{
			'material-design-detail':material_design_detail
		}
	}
</script>

<style>
</style>
