<template>
	<el-dialog title="新增产品设计" :before-close="close" :visible.sync="insert_product_design_dialog_visible">
		<!--  -->
		<el-form label-position="right" :inline="true" :model="product" :rules="rules" ref="product" label-width="100px">
			<el-form-item   label="产品名称:" prop="product_name">
				<el-input placeholder="产品名称" v-model="product.product_name"></el-input>
			</el-form-item>
			<el-form-item   label="登记人:" >
				<el-input :disabled="true" placeholder="登记人" v-model="register"></el-input>
			</el-form-item>
			<el-form-item label="产品类别" >
				<el-cascader v-model="product.product_kind_id" :options="productKinds" placeholder="请选择"
				:props="propsParams" clearable >
				  <template slot-scope="{ node, data }" >
						<span >{{ data.kindName }}</span>
						<span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
				  </template>
				</el-cascader>
			</el-form-item>
			<el-form-item label="用途类型">
				<el-select v-model="product.use_type" placeholder="请选择">
				    <el-option placeholder="请选择"
				      v-for="(productUse,key,index) in productUses"
				      :key="productUse.index"
				      :label="productUse.useName"
					  :title="productUse.describe"
				      :value="productUse.id">
				    </el-option>
				  </el-select>
			</el-form-item>
			<el-form-item label="商品档次级别">
				<el-select v-model="product.product_rank" placeholder="请选择">
				    <el-option placeholder="请选择"
				      v-for="(productRank,index) in productRanks"
				      :key="productRank.index"
				      :label="productRank"
				      :value="productRank">
				    </el-option>
				  </el-select>
			</el-form-item>
			<el-form-item label="商品计量单位">
				<el-select v-model="product.product_unit" placeholder="请选择" >
				    <el-option placeholder="请选择"
				      v-for="(productUnit,index) in productUnits"
				      :key="productUnit.index"
				      :label="productUnit"
				      :value="productUnit">
				    </el-option>
				  </el-select>
			</el-form-item>
			<el-form-item label="制造商,供货商:">
				<el-select v-model="product.factoryName" filterable placeholder="请选择">
				    <el-option
				      v-for="(item,key,index) in factoryNames"
				      :key="item.index"
				      :label="item.factoryName"
				      :value="item.id">
				    </el-option>
				  </el-select>
			</el-form-item>
			<el-form-item label="市场价格" prop="price">
				<el-input  v-model.number="product.list_price" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="计划成本价" prop="price">
				<el-input  v-model.number="product.cost_price" autocomplete="off"></el-input>
			</el-form-item>
			<!-- 实际成本单价不是自己写出来的 而是通过它的物料+人工成本计算出来的 -->
			<!-- <el-form-item label="实际成本价" prop="price">
				<el-input  v-model.number="product.real_cost_price" autocomplete="off"></el-input>
			</el-form-item> -->
			<el-form-item label="信息:">
				<el-input
				  type="textarea"
				  autosize
				  placeholder="请输入内容"
				  v-model="product.message">
				</el-input>
			</el-form-item>
		</el-form>
		
		<div slot="footer" class="dialog-footer">
			<el-button @click="close">取 消</el-button>
			<el-button type="primary" @click="insertProduct">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	const product_prefix="";
	export default{
		name:'insert_product_design',
		data(){
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
			return{
				insert_product_design_dialog_visible:false,
				product:{
					product_name:'',
					product_kind_id:'',
					use_type:'',
					product_rank:'',
					product_unit:'',
					list_price:0.00,
					cost_price:0.00,
					real_cost_price:0.00,
					message:'',
					factoryName:''
				},
				propsParams:{
					expandTrigger:'hover',
					label:'kindName',
					value:'id',
					checkStrictly:false //是否可以任选一级 默认false
				},productKinds:[],//产品类别
				productUses:[],//产品用途
				productRanks:[],//产品级别
				productUnits:[],//单位
				factoryNames:[{id:1,factoryName:'默认供货商'}],
				rules:{
					product_name:{
						required:true,
						message: '请输入产品名称',
						trigger: 'blur'
					},
					list_price:[
						{validator:price,trigger:'blur'}
					],
					cost_price:[
						{validator:price,trigger:'blur'}
					],
					real_cost_price:[
						{validator:price,trigger:'blur'}
					]
				}
			}
		},methods:{
			update(){
				this.$emit('update')
			},
			insertProduct(){
				this.$refs.product.validate((valid)=>{
					if(valid){
						if(this.product.product_kind_id instanceof Array){
							let product_kind_ids=this.product.product_kind_id;
							this.product.product_kind_id=product_kind_ids[product_kind_ids.length-1];
						}
						this.$axios.post('/api/product/insert',this.product)
							.then(response=>{
								if(response.statusCord==200){
									this.$message.success('新增产品设计成功,等待审核!')
									this.update()
									this.close()
								}else{
									this.$message.error(response.message)
								}
							})
					}
				})
			},
			close(){
				
				this.$router.go(-1);
				this.insert_product_design_dialog_visible=false;
			},getProductKinds(){
				//获取级联 产品类别的数据 数据
				return this.$axios.get('/api/productKind/getKinds')
					// .then(response=>{
					// 	this.productKinds=response.data
					// })
			},getProductUses(){
				//获取所有的用途
				return this.$axios.get('/api/productUse/All')
					// .then(response=>{
					// 	this.productUses=response.data;
					// })
			},getProductRanks(){//获取级别
				return this.$axios.get('/api/unit/product_rank')
					// .then(response=>{
					// 	this.productRanks=response.data
					// })
			},getProductUnits(){//获取单位
				return this.$axios.get('/api/unit/product_unit')
					// .then(response=>{
					// 	this.productUnits=response.data
					// })
			},getFactoryNames(){//获取所有供货商的
				//不在我这个模块中
				return this.$axios.get('/api/')
			},sendMultipleRequest(){//发送多个请求
				let _this=this;
				this.$axios.all([this.getProductKinds(),this.getProductUses(),this.getProductRanks(),this.getProductUnits()])
					.then(this.$axios.spread((kinds,uses,ranks,units)=>{
						this.productKinds=kinds.data
						this.productUses=uses.data
						this.productRanks=ranks.data.units;
						this.productUnits=units.data.units;
					}))
			}
		},created() {
			this.insert_product_design_dialog_visible=true;
			this.sendMultipleRequest();
		},computed:{
			register(){
				return this.$store.getters.getActiveUser.user.uid;
			}
		}
	}
</script>

<style>
</style>
