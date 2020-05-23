<template>
	<el-dialog width="80%" :before-close="close" title="工序物料设计" :visible.sync="dialog_visible">
		<process-material-design
		v-model="process_material_dialog_visible"
		:product_id="processDesign.product_id"
		:id="activeProcess.id"
		:material_tag="activeProcess.material_tag"
		@update_material_tag="update_material_tag"
		></process-material-design>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="工序单编号: ">
					{{processDesign.process_id}}
					<!-- <el-input v-model="process.process_id" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
				<el-form-item label="设计人: ">
					{{processDesign.designer}}
					<!-- <el-input v-model="productProcessDesign.designer" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{processDesign.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{processDesign.product|whetherShow('product_name')}}</span>
				</el-form-item>
			</el-form>
		</div>

		<!-- 		<div style="text-align: right;">
			<el-button type="success" size="small" v-on:click="material_module_dialog_visible=true">添加物料</el-button>
		</div> -->
		<br />
		<!-- :summary-method="getSummaries" 
		
		-->
		<el-table :summary-method="getSummaries" show-summary :border="true" ref="material_design" :highlight-current-row="true"
		 style="width: 100%" :data="processDesign.processDetails" :default-sort="{prop: 'details_number', order: 'asc'}">
			<el-table-column label="工序执行顺序">
				<template slot-scope="scope">
					{{scope.row.details_number}}
				</template>
			</el-table-column>
			<el-table-column label="工序名称">
				<template slot-scope="scope">
					{{scope.row.process.processName}}
				</template>
			</el-table-column>
			<el-table-column label="工序描述">
				<template slot-scope="scope">
					<span title="scope.row.message">{{scope.row.process|whetherShow('message','暂无描述信息')}}</span>
				</template>
			</el-table-column>
			<el-table-column label="工时数" prop="labour_hour_amount" width="120px">
				<template slot-scope="scope">
					<span>{{scope.row.labour_hour_amount|formNumerical(2,scope.row.time_unit)}}</span>
				</template>
			</el-table-column>
			<el-table-column width="120px" label="工时/单位 成本" prop="cost_price">
				<template slot-scope="scope">
					<span>{{scope.row.cost_price|formNumerical(2,'元')}}</span>
				</template>
			</el-table-column>
			<el-table-column label="小计" prop="total">
				<template slot-scope="scope">
					{{scope.row.cost_price|total(scope.row.labour_hour_amount)}}
				</template>
			</el-table-column>
			
			<el-table-column label="设计">
				<template slot-scope="scope" >
					<template v-if="scope.row.material_tag==0">
						<el-button size="small" type="primary" @click="designProcessMaterial(scope.row)" title="工序物料设计"  >设计</el-button>
					</template>
					<template v-else>
						<el-button size="small" type="primary" @click="designProcessMaterial(scope.row)" title="工序物料重新设计"  >重新设计</el-button>
					</template>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="登记人:">
					<span>{{processDesign.register}}</span>
				</el-form-item>
				<el-form-item label="设计要求:">
					<span>{{processDesign.message}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="submitMaterialDesign">提交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import process_material_design from '@/components/process/process_material_design.vue'
	export default {
		name: 'making_process_material', //工序物料设计单
		data() {
			return {
				processDesign: {},
				dialog_visible: false,
				process_material_dialog_visible:false,//工序物料制定的模态框
				activeProcess:{id:null,material_tag:0}
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			checkMaterial(){
				let product_id=this.processDesign.product_id
				this.$axios.get(`/api/materialDesign/useState/${product_id}`)
					.then(response=>{
						if(response.data==true){
							this.submitMaterialDesign()
						}else{
							this.$message.error('你还有物料暂未使用!')
						}
					})
			},
			submitMaterialDesign(){
				let product_id=this.processDesign.product_id
				let id=this.processDesign.id
				let processDesign={
					product_id:product_id,
					id:id
				}
				//改变工序物料设计
				this.$axios.post('/api/processDesign/alterProcessMaterialDesign',processDesign)
					.then(response=>{
						if(response.statusCord==200){
							this.$message.success('设置成功!');
							this.update()
							this.close()
						}else{
							this.$message.error(response.message);
						}
					})
			},
			update_material_tag(id,material_tag){
				//保存成功后  更改activeProcess的状态
				if(this.activeProcess.id == id&&this.activeProcess.material_tag!=material_tag){
					this.activeProcess.material_tag=material_tag
				}
			},
			// setProcessMaterial(id,materials){//设置工序物料  再process_material_design模块中调用
			// 	let processDetails = this.processDesign.processDetails
			// 	let index = processDetails.findIndex(item=>{
			// 		return item.id==id
			// 	})
			// 	let data=JSON.parse(JSON.stringify(materials))
			// 	this.$set(processDetails[index],'processMaterialDetails',data)
				
			// },
			designProcessMaterial(process){
				this.activeProcess=process
				this.process_material_dialog_visible=true;
			},
			getProcessById() {
				let id = this.$route.params[Object.keys(this.$route.params)[0]];
				this.$axios.get(`/api/processDesign/byId/${id}`)
					.then(response => {
						this.processDesign = response.data
					})
			},
			close() {
				this.dialog_visible = false
				this.$router.go(-1)
			},
			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length = columns.length
				columns.forEach((column, index) => {
					if (index === 0) {
						sums[index] = '总价';
						return;
					}
					const values = data.map(item => Number(item[column.property]));
					if (!values.every(value => isNaN(value))) {
						sums[index] = values.reduce((prev, curr) => {
							const value = Number(curr);
							if (!isNaN(value)) {
								return prev + curr;
							} else {
								return prev;
							}
						}, 0);
						sums[index] = sums[index].toFixed(2);
					} else {
						if (index === length - 2) {
							let total = 0;
							data.forEach(item => {
								let hourAmount = Number(item.labour_hour_amount)
								let costPrice = Number(item.cost_price)
								if (!isNaN(hourAmount) && !isNaN(costPrice)) {
									total += hourAmount * costPrice
								}
							})
							sums[index] = '工时成本:' + total.toFixed(2) + '元'
						} else {
							sums[index] = "N/A"
						}
					}
				});
				return sums;
			},
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
			formNumerical(numerical, fixed, suffix) {
				//保留两位小数
				return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},
		computed: {
			
		},
		created() {
			this.dialog_visible = true;
			this.getProcessById();
		},components:{
			'process-material-design':process_material_design
		}
	}
</script>

<style scoped="scoped">
	element .style {
		min-height: 50px;
		width: 300px;
	}
	
	#columns {
		font-size: 0;
	}
	
	#columns label {
		width: 90px;
		color: #99a9bf;
	}
	
	#columns .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
</style>
