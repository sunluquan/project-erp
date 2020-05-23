<template>
	<el-dialog :before-close="close" title="产品生产工序设计" :visible.sync="dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{productProcessDesign.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{productProcessDesign.product.product_name}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="设计人: ">
					<span>{{productProcessDesign.designer}}</span>
					<!-- <el-input v-model="productProcessDesign.designer" placeholder="请输入设计人"></el-input> -->
				</el-form-item>
				<el-form-item label="审核人: ">
					<span>{{checker}}</span>
					<!-- <el-input v-model="productProcessDesign.designer" placeholder="请输入设计人"></el-input> -->
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
		 style="width: 100%" :data="productProcessDesign.processDetails" :default-sort="{prop: 'details_number', order: 'asc'}">
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
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item label="登记人:">
					<span>{{productProcessDesign.register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="设计要求">
					<span :title="productProcessDesign.message">{{productProcessDesign|whetherShow('message','暂无设计要求')}}</span>
					<!-- <el-input type="textarea" autosize v-model="productProcessDesign.message"></el-input> -->
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close">取 消</el-button>
			<el-button type="primary" @click="pass">通过</el-button>
			<el-button type="danger" @click="before_reject">驳回</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'confirm_checker_process',
		data() {
			return {
				productProcessDesign: {},
				dialog_visible: false
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			pass(){
				let id=this.productProcessDesign.id;
				let product_id=this.productProcessDesign.product_id
				let processDesign={
					id:id,
					product_id:product_id
				}
				this.$axios.post('/api/processDesign/pass',processDesign)
					.then(response=>{
						if (response.statusCord == 200) {
							this.$message.success('已通过产品工序设计单!')
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
					this.reject(value)
				}).catch(() => {
					this.reject("")
				});
			},reject(value){
				value="驳回原因: "+value
				let id=this.productProcessDesign.id;
				let product_id=this.productProcessDesign.product_id
				let processDesign={
					id:id,
					product_id:product_id,
					check_suggestion:value
				}
				this.$axios.post('/api/processDesign/reject',processDesign)
					.then(response=>{
						if (response.statusCord == 200) {
							this.$message.success('已驳回产品工序设计单!')
							this.update()
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
			},
			init() {
				let id = this.$route.params[Object.keys(this.$route.params)[0]]
				this.$axios.get(`/api/processDesign/byId/${id}`)
					.then(response => {
						this.productProcessDesign = response.data
					})
			},
			close() {
				this.dialog_visible = false
				this.$router.go(-1);
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
			this.dialog_visible = true
			this.init();
		},
		computed: {
			checker(){
				return this.$store.getters.getActiveUser.user.uid
			}
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
