<template>
	<el-dialog id="confirm_checker_product" title="产品审核" :before-close="close" :visible.sync="confirm_checker_product_dialog_visible">
		<!--  -->
		<el-form label-position="right" :inline="true" :model="product" :rules="rules" ref="product" label-width="100px">
			<el-form-item label="产品名称:" prop="product_name">
				<el-input placeholder="产品名称" v-model="product.product_name"></el-input>
			</el-form-item>
			<template v-if="product.changer">
				<el-form-item label="变更人:">
					<el-input :disabled="true" v-model="product.changer"></el-input>
				</el-form-item>
			</template>
			<template v-else>
				<el-form-item label="登记人:">
					<el-input :disabled="true" placeholder="登记人" v-model="product.register"></el-input>
				</el-form-item>
			</template>
			<el-form-item label="产品类别">
				<el-cascader v-model="product.product_kind_id" :options="productKinds" placeholder="请选择" :props="propsParams">
					<template slot-scope="{ node, data }">
						<span>{{ data.kindName }}</span>
						<span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
					</template>
				</el-cascader>
			</el-form-item>
			<el-form-item label="用途类型">
				<el-select v-model="product.use_type" placeholder="请选择">
					<el-option placeholder="请选择" v-for="(productUse,key,index) in productUses" :key="productUse.index" :label="productUse.useName"
					 :title="productUse.describe" :value="productUse.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="商品档次级别">
				<el-select v-model="product.product_rank" placeholder="请选择">
					<el-option placeholder="请选择" v-for="(productRank,index) in productRanks" :key="productRank.index" :label="productRank"
					 :value="productRank">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="商品计量单位">
				<el-select v-model="product.product_unit" placeholder="请选择">
					<el-option placeholder="请选择" v-for="(productUnit,index) in productUnits" :key="productUnit.index" :label="productUnit"
					 :value="productUnit">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="制造商:">
				<el-select v-model="product.factoryName" filterable placeholder="请选择">
					<el-option v-for="(item,key,index) in factoryNames" :key="item.index" :label="item.factoryName" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="市场价格" prop="price">
				<el-input v-model.number="product.list_price" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="计划成本价" prop="price">
				<el-input v-model.number="product.cost_price" autocomplete="off"></el-input>
			</el-form-item>
			<template v-if="product.real_cost_price">
				<el-form-item label="实际成本价" >
					<el-input :disabled="true" v-model.number="product.real_cost_price" autocomplete="off"></el-input>
				</el-form-item>
			</template>
			<el-form-item label="信息:">
				<el-input type="textarea" autosize placeholder="请输入内容" v-model="product.message">
				</el-input>
			</el-form-item>
		</el-form>

		<div slot="footer" class="dialog-footer">
			<el-button @click="close">取 消</el-button>
			<el-button type="primary" @click="pass">通过</el-button>
			<el-button type="danger" @click="reject_cause">驳回</el-button>
		</div>
	</el-dialog>
</template>

<script>
	const pass = "1"; //通过核审
	const reject = "2"; //驳回核审
	export default {
		name: 'confirm_checker_product',
		data() {
			let price = (rule, value, callback) => {
				if (!value) {
					return callback(new Error('价格不能为空'));
				}
				setTimeout(() => {
					if (!Number.isInteger(value)) {
						callback(new Error('价格必须为数字类型'));
					} else {
						if (value <= 0) {
							callback(new Error('请输入合理的价格方案'));
						} else {
							callback();
						}
					}
				}, 1000);
			};
			return {
				confirm_checker_product_dialog_visible: false,
				product: {
					id: '',
				},
				propsParams: {
					expandTrigger: 'hover',
					label: 'kindName',
					value: 'id',
					checkStrictly: false //是否可以任选一级 默认false
				},
				productKinds: [], //产品类别
				productUses: [], //产品用途
				productRanks: [], //产品级别
				productUnits: [], //单位
				factoryNames: [{
					id: 1,
					factoryName: '默认供货商'
				}],
				rules: {
					product_name: {
						required: true,
						message: '请输入产品名称',
						trigger: 'blur'
					},
					list_price: [{
						validator: price,
						trigger: 'blur'
					}],
					cost_price: [{
						validator: price,
						trigger: 'blur'
					}],
					real_cost_price: [{
						validator: price,
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			update() {
				this.$emit('update');
			},
			reject_cause(){
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
			},reject(cause) {
				let prefix="驳回原因: "
				cause=prefix+cause
				let product={
					id:this.product.id,
					message:cause
				}
				this.$axios.post('/api/product/reject',product)
				//this.$axios.get('/api/product/reject/' + this.product.id, {params:{cause:cause}})
					.then(response => {
						if (response.statusCord == 200) {
							this.$message.success('产品设计方案已驳回!')
							this.update();
							this.close()
						} else {
							this.$message.error(response.message)
						}
					})
			},
			pass() {
				this.product.check_tag = pass;
				this.$refs.product.validate((valid) => {
					if (valid) {
						this.$axios.post('/api/product/updateCheckTag', this.product)
							.then(response => {
								if (response.statusCord == 200) {
									this.$message.success('审核成功!')
									this.update();
									this.close()
								} else {
									this.$message.error(response.message)
								}
							})
					}
				})
			},
			close() {
				this.$router.go(-1);
				this.confirm_checker_product_dialog_visible = false;
			},
			getProduct() {
				return this.$axios.get('/api/product/checkerProduct/' + this.product.id)
			},
			getProductKinds() {
				//获取级联 产品类别的数据 数据
				return this.$axios.get('/api/productKind/getKinds')
				// .then(response=>{
				// 	this.productKinds=response.data
				// })
			},
			getProductUses() {
				//获取所有的用途
				return this.$axios.get('/api/productUse/All')
				// .then(response=>{
				// 	this.productUses=response.data;
				// })
			},
			getProductRanks() { //获取级别
				return this.$axios.get('/api/unit/product_rank')
				// .then(response=>{
				// 	this.productRanks=response.data
				// })
			},
			getProductUnits() { //获取单位
				return this.$axios.get('/api/unit/product_unit')
				// .then(response=>{
				// 	this.productUnits=response.data
				// })
			},
			getFactoryNames() { //获取所有供货商的
				//不在我这个模块中
				return this.$axios.get('/api/')
			},
			sendMultipleRequest() { //发送多个请求
				this.$axios.all([this.getProduct(), this.getProductKinds(), this.getProductUses(), this.getProductRanks(), this.getProductUnits()])
					.then(this.$axios.spread((product, kinds, uses, ranks, units) => {
						this.product = product.data
						this.productKinds = kinds.data
						this.productUses = uses.data
						this.productRanks = ranks.data.units;
						this.productUnits = units.data.units;
					}))
			}
		},
		created() {
			this.product.id = this.$route.params[Object.keys(this.$route.params)[0]];
			this.confirm_checker_product_dialog_visible = true;
			this.sendMultipleRequest();
		},
		computed: {

		}
	}
</script>

<style>
</style>
