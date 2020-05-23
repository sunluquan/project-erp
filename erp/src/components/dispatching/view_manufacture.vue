<template>
	<el-dialog :before-close="close" title="查看生产制定单" :visible.sync="dialog_visible">
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
					<span>{{manufacture.designer}}</span>
					<!-- <el-input v-model="manufacture.designer" placeholder="请输入制定人"></el-input> -->
				</el-form-item>
				<el-form-item label="单个产品物料设计成本: ">
					<span>{{manufacture.material_cost_price_sum|formNumerical(2,'元')}}</span>
					<!-- <el-input v-model="productProcessDesign.designer" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
				<!-- <el-form-item style="text-align: right;">
					<el-button type="success" size="small" v-on:click="insertProcessSearch">添加工序</el-button>
				</el-form-item> -->
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="生产数量: ">
					{{manufacture.amount|formNumerical(2)}}
				</el-form-item>
				<el-form-item label="生产总值: ">
					{{productionTotal}}
					<!-- <span>{{manufacture.amount|total(manufacture.real_cost_price)}}</span> -->
				</el-form-item>
			</el-form>
		</div>
		<br />
		<!-- :summary-method="getSummaries" 
		-->
		<el-table :summary-method="getSummaries" show-summary :border="true" ref="material_design" :highlight-current-row="true"
		 style="width: 100%" :data="processDetails" :default-sort="{prop: 'details_number', order: 'asc'}">
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
					<span>{{manufacture.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="备注">
					<span>{{manufacture.remark}}</span>
					<!-- <el-input type="textarea" autosize v-model="manufacture.remark"></el-input> -->
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button  @click="close">关 闭</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import process_material_view from '@/components/dispatching/view_process_material.vue'
	export default{
		name:'view_manufacture',
		model:{
			prop:'dialog_visible',
			event:'update'
		},
		props:{
			manufacture:{
				type:Object,
				default:()=>({})
			},dialog_visible:{
				type:Boolean
			}
		},
		data(){
			return{
				processDetails:[],
				id:null,
				processMaterialView:false,
				cacheMaterial:{}
			}
		},methods:{
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
			getProcessDetails(){
				let product_id = this.manufacture.product_id
				this.$axios.get(`/api/processDetail/byProductId/${product_id}`)
					.then(response => {
						this.processDetails = response.data
					})
			},
			close(){
				this.$emit('update',false)
			},getSummaries(param) {
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
		},created(){
			
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
				if(suffix){
					return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
				}
				return parseFloat(numerical).toFixed(fixed)
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},computed:{
			product_id(){
				return this.manufacture.product_id
			},productionTotal(){
				let material_price = this.manufacture.material_cost_price_sum
				let amount = this.manufacture.amount
				let labour_price = this.labour_cost_price_sum
				material_price = isNaN(material_price)?0:material_price
				labour_price = isNaN(labour_price)?0:labour_price
				let total=(material_price+labour_price)*amount
				return total.toFixed(2)+'元'
			}
		},watch:{
			product_id(newVal){
				if(newVal){
					this.getProcessDetails()
				}
			}
		},updated() {
			if(this.processDetails.length==0&&this.manufacture.product_id){
				this.getProcessDetails()
			}
		},components:{
			'process-material-view':process_material_view
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
