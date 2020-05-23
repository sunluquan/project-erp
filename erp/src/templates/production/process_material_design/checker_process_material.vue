<template>
	<div id="making_process_material">
		<router-view @update="getcheckerProcessMaterial"></router-view>
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table @row-dblclick="viewProcessDetail"
		 :border="true" ref="product_table" 
		 :highlight-current-row="true" 
		 style="width: 100%" :data="search"
		 :default-sort="{prop: 'product_id', order: 'descending'}" >
			<el-table-column label="工序设计单编号" prop="process_id" sortable>
				<template slot-scope="scope">
					{{scope.row.process_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" sortable>
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name" >
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('product_name')}}
				</template>
			</el-table-column>
			<el-table-column label="产品类别名称" prop="product_name" >
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('kindName')}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型" prop="use_type">
				<template slot-scope="scope">
					{{scope.row.product|whetherShow('useName')}}
				</template>
			</el-table-column>
			<el-table-column label="设计人" prop="cost_price" >
				<template slot-scope="scope">
					{{ scope.row|whetherShow('designer')}}
				</template>
			</el-table-column>
			<el-table-column label="设计要求" prop="cost_price" >
				<template slot-scope="scope">
					{{ scope.row|whetherShow('message','无设计要求')}}
				</template>
			</el-table-column>
			<el-table-column align="right" width="100px">
				<template slot="header" slot-scope="scope">
					<el-input v-model="keyWord" placeholder="产品编号或名称" />
				</template>
				<template slot-scope="scope">
					<el-button type="primary" size="small" title="核审工序物料组成" @click="checkerProcessMaterial(scope.row.id)">核审工序物料组成</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div v-show="showDetail&&processDesigns.length>0">
			<h6>工序设计详细单:<span style="color: red;">{{activeprocess.processPage_id}}</span></h6>
			<process-design-detail :id="activeprocess.id"></process-design-detail>
		</div>
	</div>
</template>

<script>
	import process_design_detail from '@/components/product/process_design_detail.vue'
	export default{
		name:'checker_process_material',//审核工序物料
		data(){
			return{
				processDesigns:[],
				keyWord:'',
				showDetail:false,
				activeprocess: {
					id: ''
				},
			}
		},methods:{
			getcheckerProcessMaterial(){//获取符合审核工序物料条件的产品集合
				this.$axios.get('/api/processDesign/checkerProcessMaterial')
					.then(repsonse=>{
						this.processDesigns=repsonse.data
					})
			},checkerProcessMaterial(id){//审核工序物料
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/checker/${id}`})
			},viewProcessDetail(data){
				if(this.activeprocess.id==data.id){
					this.showDetail=!this.showDetail
				}else{
					this.showDetail=true;
					this.activeprocess=JSON.parse(JSON.stringify(data))
				}
			}
		},created() {
			this.getcheckerProcessMaterial()
		},computed:{
			search() {
				let keyWord=this.keyWord
				if (keyWord == '') {
					return this.processDesigns
				} else {
					return this.processDesigns.filter(processDesign => {
						return processDesign.initial.includes(keyWord) ||
							processDesign.product.product_name.includes(keyWord) ||
							processDesign.product_id.includes(keyWord)
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
			'process-design-detail':process_design_detail
		}
	}
</script>

<style>
</style>
