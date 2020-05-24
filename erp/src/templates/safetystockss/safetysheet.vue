<template>
 <el-dialog id="safetysheet" :before-close="close" width="61%" title="安全库存配置单" style="text-align: center;" :visible.sync="safetysheet_safetystock_dialog_visible">
  
	<div id="box">
		<el-form :inline="true" :model="safetystock" :label-position="labelPosition" label-width="130px">
		  <el-form-item  label="产品编号:" style="padding-right: 90px;" prop="product_id">
		    <el-input class="input" v-model="safetystock.product_id"></el-input>
		  </el-form-item>
		  <el-form-item label="产品名称:" prop="product_name">
		     <el-input  class="MyClass" v-model="safetystock.product_name"></el-input>
		  </el-form-item>				  		 
		</el-form>	
		
		<el-form :inline="true" :model="safetystocks" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
			<el-form-item label="库存报警下限数:" style="padding-right: 90px;" prop="minamount">
			  <el-input class="MyClass" v-model="safetystocks.minamount"></el-input>
			</el-form-item>
			<el-form-item label="库存报警上限数:" prop="maxamount">
			   <el-input  class="MyClass" v-model="safetystocks.maxamount"></el-input>
			</el-form-item>
			<el-form-item label="设置B/N或S/N:" style="padding-right: 90px;">
			   <el-input  class="MyClass"></el-input>
			</el-form-item>
			<el-form-item label="设计人" prop="register"> 
			   <el-input  class="MyClass" v-model="safetystocks.register"></el-input>
			</el-form-item>
			<el-table :data="datatable" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 93%;margin-left: 40px;">
			    <el-table-column prop="id" label="序号"  >				
			    </el-table-column>	
			    <el-table-column prop="sid" label="仓库"  >
					<template slot-scope="scope">
						
						<el-select v-model="safetystocks.sid" size="small">
						      <el-option v-for="(storehouse,index) in warehousePage" v-bind:key="index"
						       :label="storehouse.storeName" :value="storehouse.sid">
						      </el-option>
						    </el-select>
					</template>					
			    </el-table-column>
				<el-table-column prop="storeAddress" label="库存地址">								
				</el-table-column>
				<el-table-column prop="maxCapacityAmount" label="最大存储量"  >
					<template slot-scope="scope">
						<el-input size="small" v-model="safetystocks.maxCapacityAmount"></el-input>
					</template>										
				</el-table-column>
				<el-table-column prop="address" label="存储单位" >
					<template slot-scope="scope">
						<el-input size="small"  v-model="safetystocks.storeUnit"></el-input>									
					</template>
				</el-table-column>				
			  </el-table>	
			  
				<el-form-item label="登记人:" style="padding-right: 120px;" prop="activeUser">
				   <span>{{activeUser}}</span>
				</el-form-item>	
				   <el-form-item label="登记时间:" prop="registertime">		  	     
						 <el-date-picker type="date" placeholder="选择日期" size="small">						   
						 </el-date-picker>
				   </el-form-item>
				   <el-form-item label="配置要求:" style="padding-right: 80px;">
				      <el-input type="textarea" style="width: 599px;"></el-input>
				   </el-form-item>				   
		</el-form>	
	</div>
	<div slot="footer" class="dialog-footer">
		<el-button size="small" round  v-on:click="insert()">提交</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
	</div>
	</el-dialog>
</template>

<script>

	export default {
		name:'safetysheet',
		data() {			
	     	return {	
				uidName:'',
				id:'',
				dataPicke: new Date,
				custom: 'custom',
				product_id:'',
				labelPosition: 'right',				       
				safetysheet_safetystock_dialog_visible: false,
			   
				
				datatable:[{
					id:'1',
					storeAddress:'湖南省岳麓区',										
					
				}],	
				safetystocks:{
					id:'',
					minamount:'',
					maxamount:'',
					register:'',
					sid:'',
					maxCapacityAmount:'',
					storeUnit:'',
					registertime:'',					
				},
				warehousePage:'',
				isadmin: 3,	
				safetystock: {
					product_id:'',
					product_name:''					
			},	
					res:{
						id:'',
					},
					stocks:{
						id:''
					},
		}
		},
		
		created(){			
			let id=this.$route.params[Object.keys(this.$route.params)[0]];
			this.id=id;
			
			console.log(this.$route);
			this.safetysheet_safetystock_dialog_visible = true;						
			this.getselectByid();
			this.searchWarehouse();	
			this.activeUser();
			this.$store.commit({
				type: 'setActiveUser',
				activeUser: {}
			})
		},
		methods: {
			
			close() {
				this.safetysheet_safetystock_dialog_visible = false;
				this.$router.go(-1)
			},
			
			getselectByid(){	
				let id=this.id;
				this.$axios.get('/api/product/getById/'+this.id).then(response=>{
						this.safetystock=response.data;
						
					})
			},
			insert(){
				        this.safetystocks.productId=this.safetystock.product_id;
						this.$axios.post('/api/safetystock/addSafety',this.safetystocks).then(response => {
							   alert('来了');							   
							    let responseData=response.data;
								this.res=response.data;
								
								
								if (response.statusCord == 200) {
									this.$message({
										showClose: true,
										message: '提交成功!',
										message:'需要复核!!',
										type: 'success',
										duration: 2000
									});	
							    this.update();
							    this.selectProList()
								this.close();									
								}	
								 else {
									this.$message({
										showClose: true,
										message: responseData.message,
										type: 'error',
										duration: 2000
									});
								}
								
							})
							
			},
			update(){
				this.$axios.post('/api/safetystock/updateChecktag',this.safetystocks).then(response =>{
					alert('随机数');
				})
			},selectProList(){			
	  	 			this.$axios.post('/api/product/selectProList').then(response=>{
	  	 				this.productPage=response.data;
						
	  	 				})
	  	 		},
				searchWarehouse(){
				this.$axios.post('/api/warehouse/getByid/'+this.id).then(response=>{
						this.warehousePage=response.data;
					})
			},				
		},
		computed: {
			activeUser() {
				return this.$store.getters.getActiveUser.user.uname;
			},
			routerNames() {
				return this.$store.getters.getRouterNames;
			}			
		}
		
	}
</script>

<style>
	#box{
		width: 855px;
		height: 430px;
		border:1px solid gray;
	}
	.MyClass input.el-input__inner{
		width: 160px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		/*outline: medium;*/
	}
	.input input.el-input__inner{
		width: 160px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 0px;
	}	
</style>
