<template>
	<el-dialog :before-close="close" title="新增派工单" :visible.sync="insert_dispatching_dialog_visible">
		<process-material-view 
		:id="id"
		v-model="processMaterialView"
		@get_cache_material="getCacheMaterial"
		@set_cache_material="setCacheMaterial"></process-material-view>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{manufacture.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{manufacture.product_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="制定人: ">
					<el-input v-model="manufacture.designer" placeholder="请输入制定人"></el-input>
				</el-form-item>
			<!-- 	<el-form-item label="入库名称: ">
					 <el-select v-model="storehouse" placeholder="请选择">
					    <el-option
					      v-for="(item,key,index) in storehouses"
					      :key="item.index"
					      :label="item.storeName"
					      :value="item.sid">
					    </el-option>
					  </el-select>
				</el-form-item> -->
				<!-- <el-form-item style="text-align: right;">
					<el-button type="success" size="small" v-on:click="insertProcessSearch">添加工序</el-button>
				</el-form-item> -->
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="生产数量: ">
					{{manufacture.amount|formNumerical(2,product.product_unit)}}
				</el-form-item>
				<el-form-item label="生产总值: ">
					<span>{{manufacture.amount|total(manufacture.real_cost_price)}}</span>
				</el-form-item>
				<!-- <el-form-item style="text-align: right;">
					<el-button type="success" size="small" v-on:click="insertProcessSearch">添加工序</el-button>
				</el-form-item> -->
			</el-form>
		</div>
		<!-- 		<div style="text-align: right;">
			<el-button type="success" size="small" v-on:click="material_module_dialog_visible=true">添加物料</el-button>
		</div> -->
		<br />
		<!-- :summary-method="getSummaries" 
		-->
		<el-table :summary-method="getSummaries" show-summary :border="true" ref="material_design" :highlight-current-row="true"
		 style="width: 100%" :data="manufacture.processDesign.processDetails" :default-sort="{prop: 'details_number', order: 'asc'}">
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
			<el-table-column label="操作" prop="total">
				<template slot-scope="scope">
					<el-button type="primary" :disabled="scope.row.material_tag==0" :title="scope.row.material_tag==0?'无工序物料信息':'查看工序物料信息'" @click="processMaterialViewMethod(scope.row.id)" icon="el-icon-view" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<el-input type="textarea" autosize v-model="manufacture.remark"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="submitDispatching">提 交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import process_material_view from '@/components/dispatching/view_process_material.vue'
	export default {
		name: 'insert_dispatching',
		model: {
			prop: 'insert_dispatching_dialog_visible',
			event: 'update'
		},
		props: {
			insert_dispatching_dialog_visible: {
				type: Boolean
			},
			product: {
				type: Object,
				default: () => (null)
			}
		},
		data() {
			return {
				manufacture: {
					product_id: '',
					product_name: '',
					amount: 0,
					productionPlanDetails: [],
					real_cost_price:0.00,
					designer:'',
					remark:'',
					processDesign:{}
				},
				processDetails: [],
				processMaterialView:false,
				cacheMaterial:{},
				id:null,//工序编号
				//storehouses:[],//生产完工后的入库仓库
				//processDesign:{}
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			getCacheMaterial(id,callback){
				if(this.cacheMaterial instanceof Map){
					if(this.cacheMaterial.has(id)){
						callback(this.cacheMaterial.get(id))
						return 
					}
				}
				callback([])
			},setCacheMaterial(data){
				if(this.cacheMaterial instanceof Map == false){
					this.cacheMaterial=new Map()
				}
				this.cacheMaterial.set(this.id,data)
			},
			processMaterialViewMethod(id){
				this.id=id
				this.processMaterialView=true
			},
			submitDispatching() {
				this.$axios.post('/api/manufacture/insert',this.manufacture)
					.then(response=>{
						if(response.statusCord == 200){
							this.$message.success('提交成功!')
							this.update()
							this.close()
						}else{
							this.$message.error(response.data)
						}
					})
			},
			setManfacture() {
				if (this.product != null) {
					let product_id=this.manufacture.product_id
					for (let lt in this.manufacture) {
<<<<<<< HEAD
<<<<<<< HEAD
						console.log(lt+'---'+this.product[lt])
=======
>>>>>>> warehouse
=======
						console.log(lt+'---'+this.product[lt])
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
						if(this.product[lt])
							this.manufacture[lt] = this.product[lt]
					}
					if(product_id!=this.manufacture.product_id){
						this.getProcessDesign()
						//this.getProcessDetail()
					}
				}
			},
			// getProcessDetail() {
			// 	let product_id = this.manufacture.product_id
			// 	this.$axios.get(`/api/processDetail/byProductId/${product_id}`)
			// 		.then(response => {
			// 			this.processDetails = response.data
			// 		})
			// }, 已过时
			getProcessDesign(){
				let product_id = this.manufacture.product_id
					this.$axios.get(`/api/processDesign/byProductId/${product_id}`)
						.then(response => {
							this.manufacture.processDesign = response.data
						})
			},
			close() {
				this.$emit('update', false)
			},
			init() {
				this.setManfacture()
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
						if (index === length - 1) {
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
		created() {
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
				if(suffix){
					return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
				}else{
					return parseFloat(numerical).toFixed(fixed)
				}
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},
		watch: {
			insert_dispatching_dialog_visible(newVal){
				if(newVal){
					this.init()
				}
			},product_id(newVal){
				if(!newVal){ 
					//this.close()
				}
			}
		},computed:{
			register(){
				let register=this.$store.getters.getActiveUser.user.uid
				this.manufacture.designer=register
				return register
			},product_id(){
				return this.manufacture.product_id
			}
		},components:{
			"process-material-view":process_material_view
		},updated() {
			if(!this.manufacture.product_id){
				this.setManfacture()
			}
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
