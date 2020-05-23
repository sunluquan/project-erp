<template>
	<el-dialog width="70%" :before-close="close" title="产品生产工序设计修改" :visible.sync="dialog_visible">
		<process-module v-model="process_dialog_visible" :selected="selected" @put_process_detail="putProcessDetail"></process-module>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="产品编号: ">
					<span>{{productProcessDesign.product_id}}</span>
				</el-form-item>
				<el-form-item label="产品名称: ">
					<span>{{productProcessDesign.product|whetherShow('product_name')}}</span>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="设计人: ">
					<el-input v-model="productProcessDesign.designer" placeholder="请输入设计人"></el-input>
				</el-form-item>
				<el-form-item style="text-align: right;">
					<el-button type="success" size="small" v-on:click="insertProcessSearch">添加工序</el-button>
				</el-form-item>
			</el-form>
		</div>
		<br />
		<!-- :summary-method="getSummaries" -->
		<el-table :summary-method="getSummaries" show-summary style="text-align: center;width: 100%" :border="true" :data="productProcessDesign.processDetails"
		 ref="product_table" :highlight-current-row="true" :default-sort="{prop: 'details_number', order: 'ascending'}">
			<template slot="empty">
				暂无工序,
				<el-button type="primary" @click="insertProcessSearch" title="点击添加" icon="el-icon-plus" circle></el-button>
			</template>
			<el-table-column label="工序执行顺序">
				<template slot-scope="scope">
					<!-- {{scope.row.details_number}} -->
					<number :max="productProcessDesign.processDetails.length"
					 :min="1" v-model="scope.row.details_number"></number>
				</template>
			</el-table-column>
			<el-table-column label="工序名称">
				<template slot-scope="scope">
					{{scope.row.process.processName}}
				</template>
			</el-table-column>
			<el-table-column label="工序描述">
				<template slot-scope="scope">
					<span title="scope.row.message">{{scope.row|whetherShow('message','暂无描述信息')}}</span>
				</template>
			</el-table-column>
			<el-table-column label="工时数" prop="labour_hour_amount" width="120px">
				<template slot-scope="scope">
					<number :precision="2" :min="0" v-model="scope.row.labour_hour_amount"></number>
				</template>
			</el-table-column>
			<el-table-column label="工时单位" width="180px">
				<template slot-scope="scope">
					<select-component :default_units="timeUnits" v-model="scope.row.time_unit"></select-component>
				</template>
			</el-table-column>
			<el-table-column width="120px" label="工时/单位 成本" prop="cost_price">
				<template slot-scope="scope">
					<number :precision="2" :min="0" v-model="scope.row.cost_price"></number>
				</template>
			</el-table-column>
			<el-table-column label="小计" prop="total">
				<template slot-scope="scope">
					{{scope.row.cost_price|total(scope.row.labour_hour_amount)}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="danger" title="删除该工序" @click="deleteProcess(scope.row.process_id)" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<br />
		<el-form label-position="left" inline class="demo-table-expand">
			<el-col :span="10">
				<el-form-item >
					<span>登记人: {{productProcessDesign.register}}</span>
					&nbsp;&nbsp;&nbsp;
					<span>修改人: {{register}}</span>
				</el-form-item>
			</el-col>
			<el-col :span="14">
				<el-form-item label="设计要求">
					<el-input type="textarea" autosize v-model="productProcessDesign.message"></el-input>
				</el-form-item>
			</el-col>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="submitProcessOneDesign">提交</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import number from '@/components/number.vue'
	import select_component from '@/components/selectComponent.vue'
	import process_module from '@/components/product/process_module.vue'
	export default {
		name: 'making_process_one', //制定工序设计单
		data() {
			return {
				unit_key: 'time_unit',
				dialog_visible: false,
				process_dialog_visible: false, //添加工序模态框
				productProcessDesign: {
					id: '',
					message: '',
					designer: '',
					processDetails: []
				},
				timeUnits: [], //时间单位数组
				selected: [],
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length=columns.length
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
						sums[index] =sums[index].toFixed(2);
					} else {
						if(index===length-2){
							let total=0;
							data.forEach(item=>{
								let hourAmount=Number(item.labour_hour_amount)
								let costPrice=Number(item.cost_price)
								if(!isNaN(hourAmount)&&!isNaN(costPrice)){
									total+=hourAmount*costPrice
								}
							})
							sums[index]='总工时价:'+total.toFixed(2)+'元'
						}else{
							sums[index]="N/A"
						}
					}
				});

				return sums;
			},
			putProcessDetail(data) {//新增  工序
				//使用了 点击相应工序  就会把id存放到selected
				//中去  然后传给查询工序的组件  然后使用双重循环
				//排除包含selected中的id的值的行
				//但是会有个小bug就是点击的太快的话 还是会出现重复的问题
				//所有写了一下判断条件
				// if (this.selected.length > 0) {
				// 	let index0 = this.selected.findIndex(id => {
				// 		return id == data.process_id
				// 	})
				// 	if (index0 >= 0) return;
				// }
				let processDetails = this.productProcessDesign.processDetails;
				// if(processDetails.length>0&&processDetails.process_id==data.id){
				// 	return false;
				// }
				let index = processDetails.length + 1
				console.log('data:'+JSON.stringify(data))
				//let processDetail=JSON.parse(JSON.stringify(data))
				let processDetail = {
					details_number: index,
					process_id: data.id,
					processName: data.processName,
					labour_hour_amount: 0,
					time_unit: '',
					cost_price: 0
				}
				processDetails.push(processDetail)
				this.selected.push(data.id)
				//return true;
			},
			getTimeUnit() {
				//获取时间单位  初始化的时候获取的好处和坏处
				//好处就是到时候如果是修改啥的就好搞一些
				//坏处就是万一他只是来看一下没有选择工序
				//就会多一次axios请求
				if (this.timeUnits.length > 0) return;
				this.$axios.get('/api/unit/time_unit')
					.then(response => {
						this.timeUnits = response.data.units
					})
			},
			submitProcessOneDesign() {
				let processDetails=this.productProcessDesign.processDetails;
				if(processDetails.length>0){
					this.$axios.post('/api/processDesign/update',this.productProcessDesign)
						.then(response=>{
							if (response.statusCord == 200) {
								this.$message.success('修改产品工序设计单成功!等待审核!')
								this.update()
								this.close()
							}else{
								this.$message.error(response.message)
							}
						})
				}else{
					this.$message.error('请至少选择一个工序设计')
				}
			},
			close() {
				this.dialog_visible = false;
				this.$router.go(-1)
			},
			insertProcessSearch() { //添加工序
				this.process_dialog_visible = true
			},
			init() {
				this.getTimeUnit();
				this.getProductProcessDesign()
			},getProductProcessDesign(){
				let id=this.$route.params[Object.keys(this.$route.params)[0]]
				this.$axios.get(`/api/processDesign/byId/${id}`)
					.then(response=>{
						this.productProcessDesign=response.data
						this.productProcessDesign.processDetails.forEach(processDetail=>{
							this.selected.push(processDetail.process_id)
						})
					})
			},
			deleteProcess(id) { //取消这道工序
				let processDetails = this.productProcessDesign.processDetails;
				let index = -1;
				processDetails.forEach((item, index1) => {
					if (item.process_id == id) {
						index = index1
					}
					if (index != -1) {
						item.details_number = item.details_number - 1
					}
				})
				processDetails.splice(index, 1)
				this.selected.splice(index, 1)
			}
		},
		created() {
			this.init()
			this.dialog_visible = true
		},
		computed: {
			register() {
				let designer = this.$store.getters.getActiveUser.user.uid
				if(this.productProcessDesign.designer==''){
					this.productProcessDesign.designer=designer
				}
				return designer
			}
			// ,detailsNumbers(){
			// 	let numbers=[]
			// 	 this.productProcessDesign.processDetails.forEach(item=>{
			// 		 number.push(item.details_number)
			// 	 })
			// 	 return numbers;
			// }
		},
		components: {
			'process-module': process_module,
			'select-component': select_component,
			number
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
				return parseFloat(numerical).toFixed(2);
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},watch:{
			// detailsNumbers:{
			// 	handler(newVal,oldVal){
					
			// 	},deep:true
			// }
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
