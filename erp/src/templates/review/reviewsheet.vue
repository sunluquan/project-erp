<template>
 <el-dialog id="reviewsheet" :before-close="close" width="61%" title="安全库存配置单" style="text-align: center;" :visible.sync="reviewsheet_safetystock_dialog_visible">
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
  
	<div id="box">
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
		  <el-form-item  label="产品编号:" style="padding-right: 90px;" prop="productid">
		    <el-input class="input" v-model="safetystock.productId"></el-input>
		  </el-form-item>
		  <el-form-item label="产品名称:" prop="productname">
<<<<<<< HEAD
=======
   <div style="margin-left: 630px;">
   	<el-button type="primary" size="mini" round  @click="review()">审核通过</el-button>
   	<el-button type="primary" size="mini" round  @click="noreview()">审核不通过</el-button>	
   </div>
	<div id="box">
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
		  <el-form-item  label="产品编号:" style="padding-right:180px;margin-top: 22px;" prop="productid">
		    <el-input class="input" v-model="safetystock.productId"></el-input>
		  </el-form-item>
		  <el-form-item label="产品名称:" prop="productname" style="margin-top: 22px;">
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		     <el-input  class="MyClass" v-model="safetystock.product_name"></el-input>
		  </el-form-item>				  		 
		</el-form>	
		
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
<<<<<<< HEAD
<<<<<<< HEAD
			<el-form-item label="库存报警下限数:" style="padding-right: 90px;" prop="minamount">
=======
			<el-form-item label="库存报警下限数:" style="padding-right: 180px;" prop="minamount">
>>>>>>> warehouse
=======
			<el-form-item label="库存报警下限数:" style="padding-right: 90px;" prop="minamount">
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			  <el-input class="MyClass" v-model="safetystock.minamount"></el-input>
			</el-form-item>
			<el-form-item label="库存报警上限数:" prop="maxamount">
			   <el-input  class="MyClass" v-model="safetystock.maxamount"></el-input>
			</el-form-item>
<<<<<<< HEAD
<<<<<<< HEAD
			<el-form-item label="设置B/N或S/N:" style="padding-right: 90px;">
=======
			<el-form-item label="设置B/N或S/N:" style="padding-right: 180px;">
>>>>>>> warehouse
=======
			<el-form-item label="设置B/N或S/N:" style="padding-right: 90px;">
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			   <el-input  class="MyClass"></el-input>
			</el-form-item>
			<el-form-item label="设计人" prop="register"> 
			   <el-input  class="MyClass" v-model="safetystock.register"></el-input>
			</el-form-item>
			
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			<el-table :data="datatable" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 93%;margin-left: 40px;">
			    <el-table-column prop="id" label="序号"  >												
			    </el-table-column>	
			    <el-table-column prop="sid" label="仓库"  >
					<template slot-scope="scope">
						    <el-select v-model="safetystock.storeName" size="small">
								
						    </el-select>
					</template>					
			    </el-table-column>
				<el-table-column prop="storeAddress" label="库存地址">								
				</el-table-column>
				<el-table-column prop="maxCapacityAmount" label="最大存储量"  >
					<template slot-scope="scope">
						<el-input size="small" v-model="safetystock.maxCapacityAmount"></el-input>
					</template>										
				</el-table-column>
				<el-table-column prop="address" label="存储单位" >
					<template slot-scope="scope">
						<el-input size="small"  v-model="safetystock.storeUnit"></el-input>									
					</template>
				</el-table-column>				
			  </el-table>	
			  
<<<<<<< HEAD
=======
			<el-table :data="datatable" size="medium" :header-cell-style="{background:'whitesmoke'}"  border style="width: 93%;margin-left: 40px;">
			    
								<el-table-column prop="sid" label="序号"  >
									<template slot-scope="scope">
										<el-input class="input" v-model="safetystock.sid"></el-input>
									</template>
								</el-table-column>	
								<el-table-column prop="sid" label="仓库"  >
									<template slot-scope="scope">
										    <el-input v-model="safetystock.storeName" size="small">					      
										    </el-input>
									</template>					
								</el-table-column>
								<el-table-column prop="storeAddress" label="库存地址">								
								</el-table-column>
								<el-table-column prop="maxCapacityAmount" label="最大存储量"  >
									<template slot-scope="scope">
										<el-input size="small" v-model="safetystock.maxCapacityAmount"></el-input>
									</template>										
								</el-table-column>
								<el-table-column prop="address" label="存储单位" >
									<template slot-scope="scope">
										<el-input size="small"  v-model="safetystock.storeUnit"></el-input>									
									</template>
								</el-table-column>
			  </el-table>	
			  <br />
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				<el-form-item label="审核人:" style="padding-right: 278px;" prop="register">
				   <span>{{activeUser}}</span>
				</el-form-item>
				
				   <el-form-item label="登记时间:" prop="registertime">		  	     						 
					<el-input  class="MyClass" v-model="safetystock.registertime"></el-input>		 
				   </el-form-item>
				   <el-form-item label="配置要求:" style="padding-right: 90px;">
				      <el-input type="textarea" style="width: 570px;"></el-input>
				   </el-form-item>				   			  					
		</el-form>	
	</div>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	<div slot="footer" class="dialog-footer">
		<el-button size="mini" round @click="review()">审核通过</el-button>
		<el-button size="mini" round @click="noreview()">审核不通过</el-button>	
		<el-button size="mini" round @click="close()">取消</el-button>
	</div>
<<<<<<< HEAD
=======
	
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	</el-dialog>
</template>

<script>
	
	function changes(){
	if(document.getElementById("test1").style.display="none"){
	document.getElementById("test1").style.display="";
	}else{
	document.getElementById("test1").style.display ="none";
	}
	}
	export default {
		name:'safetysheet',
		data() {			
	     	return {							
				dataPicke: new Date,
				custom: 'custom',
<<<<<<< HEAD
<<<<<<< HEAD
				productId:'',
=======
				safetystockId:'',
>>>>>>> warehouse
=======
				productId:'',
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				labelPosition: 'right',				       
				reviewsheet_safetystock_dialog_visible: false,	
				warehousePage:'',							
				safetystock:{},
<<<<<<< HEAD
<<<<<<< HEAD
=======
				safetystocks:{},
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				datatable:[{
					id:'1',
					storeAddress:'湖南省岳麓区',										
					
				}],										
			}						
		},		
		
		created(){			
<<<<<<< HEAD
<<<<<<< HEAD
			let productId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.productId=productId;
=======
			let safetystockId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.safetystockId=safetystockId;
>>>>>>> warehouse
=======
			let productId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.productId=productId;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			console.log(this.$route);
			this.reviewsheet_safetystock_dialog_visible = true;						
			this.getAllsafetyById();
			this.activeUser();
		},
		methods: {
			close() {
				this.reviewsheet_safetystock_dialog_visible = false;
				this.$router.go(-1)
			},			
			getAllsafetyById(){
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				let productId=this.productId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.productId).then(response=>{
						this.safetystock=response.data;						
					})
			},
			/*searchWarehouse(){
				let id=this.safetystock.id;
				this.$axios.post('/api/warehouse/getByid/'+this.id).then(response=>{
						this.warehousePage=response.data;
					})
			},*/
			review(){
				alert('进来了');								
<<<<<<< HEAD
=======
				//let safetystockId=this.safetystockId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.safetystockId).then(response=>{
						//this.safetystock.push(response.data);	
						this.safetystock=response.data;
					})
			},			
			review(){
							this.safetystock.safetystockId=this.safetystockId;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
								this.$axios.post('/api/safetystock/updateSefeStatus',this.safetystock).then(response=>{
									this.check=response.data;
										if(response.statusCord==200){
											this.$message({
											    showClose: true,
											    message: '审核通过!',
											    type: 'success',
												duration:2000
											});
											alert(this.check.id);
											this.close();
										}else{
											this.$message({
											    showClose: true,
											    message: '审核失败!',
											    type: 'error',
												duration:2000
											});
										}
									})													
				//this.id=this.safetystock.id;
				
			
			},
			noreview(){
<<<<<<< HEAD
<<<<<<< HEAD
				alert('进来了');
=======
				           this.safetystocks.safetystockId=this.safetystockId;	
>>>>>>> warehouse
=======
				alert('进来了');
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
								this.$axios.post('/api/safetystock/Checktag',this.safetystock).then(response=>{
									
										if(response.statusCord==200){
											this.$message({
											    showClose: true,
											    message: '审核不通过!',
											    type: 'success',
												duration:2000
											});										
											this.close();
										}else{
											this.$message({
											    showClose: true,
											    message: '审核失败!',
											    type: 'error',
												duration:2000
											});
										}
									})													
				//this.id=this.safetystock.id;
				
							
			},
					
		},computed: {
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
<<<<<<< HEAD
<<<<<<< HEAD
		height: 430px;
=======
		height: 400px;
>>>>>>> warehouse
=======
		height: 430px;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
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
