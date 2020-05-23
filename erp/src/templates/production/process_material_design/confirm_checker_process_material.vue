<template>
	<el-dialog  :before-close="close" title="产品生产工序物料设计核审" :visible.sync="dialog_visible">
		<to-view-process-material
		v-model="to_view_material_dialog_visible"
		:materials="activeProcessMaterial.processMaterials"
		></to-view-process-material>
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
		<br />
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
			
			<el-table-column label="设计核审">
				<template slot-scope="scope" >
					<template v-if="scope.row.material_tag==0">
						<el-button size="small" disabled type="primary"  title="无设计方案"  >无设计方案</el-button>
					</template>
					<template v-else>
						<el-button size="small" type="primary" @click="toViewProcessMaterial(scope.row)" title="点击查看设计方案"  >查看设计</el-button>
					</template>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item >
					<span>工时总成本:{{processDesign.manhour_cost_price_sum|formNumerical(2,'元')}}</span>
					&nbsp;&nbsp;
					<span>物料总成本:{{processDesign.material_cost_price_sum|formNumerical(2,'元')}}</span>
				</el-form-item>
				<el-form-item label="设计要求:">
					<span>{{processDesign.message}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div slot="footer" class="dialog-footer">
			<el-button  @click="close">取 消</el-button>
			<el-button type="primary" @click="pass">通过</el-button>
			<el-button type="danger" @click="before_reject">驳回</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import to_view_process_material from '@/components/process/to_view_process_material.vue'
	export default{
		name:'confirm_checker_process_material',//确认审核工序物料
		data(){
			return{
				dialog_visible:false,
				to_view_material_dialog_visible:false,
				processDesign:{},
				activeProcessMaterial:{}
			}
		},methods:{
			update(){
				this.$emit('update')
			},
			pass(){
				let id=this.processDesign.id
				let product_id=this.processDesign.product_id
				let manhour_cost_price_sum=this.processDesign.manhour_cost_price_sum
				let material_cost_price_sum=this.processDesign.material_cost_price_sum
				let processDesign={
					id:id,
					product_id:product_id,
					manhour_cost_price_sum:manhour_cost_price_sum,
					material_cost_price_sum:material_cost_price_sum
				}
				this.$axios.post('/api/processDesign/passMaterial',processDesign)
					.then(response=>{
						if(response.statusCord ==200){
							this.$message.success('工序物料设计通过成功!');
							this.update()
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
			},before_reject(){
				this.$prompt('有无驳回原由', '提示', {
					confirmButtonText: '提交(有)',
					cancelButtonText: '提交(无)',
				}).then(({
					value
				}) => {
					this.reject(value==null?"":value)
				}).catch(() => {
					this.reject("")
				});
			},reject(value){
				let check_suggestion="驳回原由: "+value 
				let id=this.processDesign.id
				let product_id=this.processDesign.product_id
				let processDesign={
					id:id,
					product_id:product_id,
					check_suggestion:check_suggestion
				}
				this.$axios.post('/api/processDesign/rejectMaterial',processDesign)
					.then(response=>{
						if(response.statusCord ==200){
							this.$message.success('工序物料设计已驳回!');
							this.update()
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
				
			},toViewProcessMaterial(data){
				this.to_view_material_dialog_visible=true
				this.activeProcessMaterial=data
			},close(){
				this.dialog_visible=false
				this.$router.go(-1)
			},init(){
				this.dialog_visible=true
				this.getProcessById()
			},getProcessById() {
				let id = this.$route.params[Object.keys(this.$route.params)[0]];
				this.$axios.get(`/api/processDesign/byIdAll/${id}`)
					.then(response => {
						this.processDesign = response.data
					})
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
		},created() {
			this.init()
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
			formNumerical(numerical, fixed, suffix) {
				//保留两位小数
				return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},components:{
			'to-view-process-material':to_view_process_material
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
