<template>
	<!--出库页面-->
	<div id="stockOut">
		<router-view @insert="insertgather"></router-view>
		<stock-out-preview v-model="dialog_visible" :spay="spay"></stock-out-preview>
		<div style="margin-left: 730px;">
			<el-button type="primary" v-on:click="addproduct" size="mini" plain>添加产品</el-button>
			<el-button type="primary" size="mini" v-on:click="preview" plain>预览</el-button>
		</div>
		<div id="body">
			<p style="color: #000000;text-align: center;font-size: 18px;margin-top: 0px;">出库申请单</p>
			<el-form :inline="true" :model="spay" :label-position="labelPosition" label-width="165px">
				<el-form-item label="出库人:" style="padding-right: 110px;" prop="product_id">
					<el-input class="MyClass" v-model="spay.storer"></el-input>
				</el-form-item>
				<el-form-item label="出库理由:" prop="product_name">
					<el-input class="MyClass" v-model="spay.reasonexact"></el-input>
				</el-form-item>
				<el-table ref="multipleTable" :data="gatherDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 89%;margin-left: 60px;"
				 tooltip-effect="dark" @selection-change="handleSelectionChange">
					<el-table-column prop="productName" label="产品名称">
						<template slot-scope="scope">
							<span>{{scope.row.productName}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="productId" label="产品编号">
						<template slot-scope="scope">
							<span>{{scope.row.productId}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="count" label="数量">
						<template slot-scope="scope">
							<el-input class="MyClasssum" size="mini" v-model="scope.row.amount">
							</el-input>
						</template>
					</el-table-column>
					<el-table-column prop="amounUnit" label="单位">
						<template slot-scope="scope">
							<span>{{scope.row.amounUnit}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="costPrice" label="成本单价">
						<template slot-scope="scope">
							<span>{{scope.row.costPrice}}</span>
						</template>
					</el-table-column>
					<el-table-column label="操作">
						<template slot-scope="scope">
							<el-button type="danger" size="mini" @click="del(scope.row.id)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<el-form-item label="总件数:" style="padding-right: 120px;" prop="register">
					<span>{{total_js}}</span>
				</el-form-item>
				<el-form-item label="总金额:" prop="registertime">
					<span>{{total_price}}</span>
				</el-form-item><br />
				<el-form-item label="登记人:" style="padding-right: 120px;" prop="register">
					<span>{{register}}</span>
				</el-form-item>
				<el-form-item label="登记时间:" prop="registertime">
					<el-date-picker type="date" placeholder="选择日期" size="small">
					</el-date-picker>
				</el-form-item>
				<el-form-item label="备注:" style="padding-right: 85px;">
					<el-input type="textarea" style="width:700px;height:80px" v-model="spay.remark"></el-input>
				</el-form-item>
			</el-form>
		</div>
	</div>

</template>

<script>
	import stock_out_preview from '@/templates/stockOut/stockOutPreview.vue'
	export default {
		name: 'stockout',
		data() {
			return {
				inputs: '',
				warehousePage: '', //仓库下拉框对象
				gatherDetails: [], //传过来的产品信息
				multipleSelection: [],
				labelPosition: 'right',
				spay: {
					id: '',
					storer: '',
					amountSum: '', //总件数
					costPriceSum: '', //总金额
					amounUnit:'',
					costPrice:'',
					reasonexact: '',
					remark: '',
					register: '',
					registerTime: '',
					spayDetails: []
				},
				isadmin: 3,
				dialog_visible: false
			}
		},
		methods: {
			del(id) {
				let index = this.multipleSelection.findIndex(item => {
					return item.id === id
				})
				this.gatherDetails.splice(index, 1);
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			addproduct() { //添加产品模态框
				this.$router.push({
					path: '/index/stockOut.html/stockOut_product'
				})
			},
			preview() {
				this.dialog_visible = true;
			},
			insertgather(product) { //添加产品选择产品时候传过来的表单信息
				let index = this.gatherDetails.findIndex(item=>{
					return item.id===product.id
				})
				console.log(product)
				if(index>=0){
					this.gatherDetails[index].amount+=1;
				}else{
					let gatherDetail = JSON.parse(JSON.stringify(product));
					this.gatherDetails.push(gatherDetail)
				}
				//this.gatherDetails.push(gatherDetail);
				 this.spay.spayDetails = this.gatherDetails;
			}
		},
		computed: {
			register() {
				return this.$store.getters.getActiveUser.user.uname
			},
			total_price() {		//计算总金额
				var sum = 0;
				this.gatherDetails.forEach(item => {
					let amount = item.amount? item.amount: 0
					let total = parseFloat(item.costPrice) * parseFloat(amount)
					sum += total;
				})
				return sum;
			},
			total_js(){		//计算总件数
				var sum = 0;
				this.gatherDetails.forEach(item => {
					let amount = item.amount? item.amount: 0
					let total = parseFloat(amount)
					sum += total;
				})
				return sum;
			}
		},
		components: {
			'stock-out-preview': stock_out_preview //给组件名称去别名
		}
	}
</script>

<style scoped="scoped">
	#body {
		padding-top: 20px;
		margin-top: 5px;
		margin-left: 80px;
		width: 970px;
		height: 500px;
		border: 1px solid gray;
	}

	.MyClass input.el-input__inner {
		width: 200px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		/*outline: medium;*/
	}

	.input input.el-input__inner {
		width: 200px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 0px;
	}

	.MyClasssum input.el-input__inner {
		width: 80px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 0px;
		/*outline: medium;*/
	}
</style>
