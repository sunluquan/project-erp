<template>
 <el-dialog id="updatestockchange" :before-close="close" width="61%" title="安全库存配置单" style="text-align: center;" :visible.sync="updatestockchange_selectstock_dialog_visible">
  
	<div id="box">
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		  <el-form-item  label="产品编号:" style="padding-right: 90px;" prop="productid">
		    <el-input class="input" v-model="safetystock.product_id"></el-input>
		  </el-form-item>
		  <el-form-item label="产品名称:" prop="productname">
<<<<<<< HEAD
=======
		  <el-form-item  label="产品编号:" style="padding-right: 160px;margin-top: 22px;" prop="productid">
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
			<el-form-item label="库存报警下限数:" style="padding-right: 160px;" prop="minamount">
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
			<el-form-item label="设置B/N或S/N:" style="padding-right: 160px;">
>>>>>>> warehouse
=======
			<el-form-item label="设置B/N或S/N:" style="padding-right: 90px;">
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			   <el-input  class="MyClass"></el-input>
			</el-form-item>
			<el-form-item label="设计人" prop="register"> 
			   <el-input  class="MyClass" v-model="safetystock.register"></el-input>
			</el-form-item>
			
			<el-table :data="datatable" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 93%;margin-left: 40px;">
			    <el-table-column prop="id" label="序号"  >												
			    </el-table-column>	
			    <el-table-column prop="sid" label="仓库"  >
					<template slot-scope="scope">
						    <el-select v-model="safetystock.storeName" size="small">
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
<<<<<<< HEAD
				<el-form-item label="审核人:" style="padding-right: 90px;" prop="register">
=======
				<el-form-item label="审核人:" style="padding-right: 160px;" prop="register">
>>>>>>> warehouse
=======
				<el-form-item label="审核人:" style="padding-right: 90px;" prop="register">
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				   <el-input  class="MyClass" v-model="safetystock.checker"></el-input>
				</el-form-item>
				
				   <el-form-item label="审核时间:" prop="checktime">		  	     						 
					<el-input  class="MyClass" v-model="safetystock.checktime"></el-input>		 
				   </el-form-item>
				   <el-form-item label="配置要求:" style="padding-right: 100px;">
				      <el-input type="textarea" style="width: 570px;"></el-input>
				   </el-form-item>				   			  					
		</el-form>	
	</div>
	<div slot="footer" class="dialog-footer">	
		<el-button size="mini" round @click="update()">确定</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
	</div>
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
		name:'selectstockdetails',
		data() {			
	     	return {
				id:'',
<<<<<<< HEAD
<<<<<<< HEAD
				productId:'',
=======
				safetystockId:'',
>>>>>>> warehouse
=======
				productId:'',
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				dataPicke: new Date,
				custom: 'custom',
				productid:'',
				labelPosition: 'right',				       
				updatestockchange_selectstock_dialog_visible: false,
			   
				
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
				isadmin: 3,	
				warehousePage:'',
				safetystock: {},	
					res:{
						id:'',
					},
					stocks:{
						id:''
					},
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
			this.updatestockchange_selectstock_dialog_visible = true;						
			this.getAllsafetyById();
			this.searchWarehouse();
		},
		methods: {
			close() {
				this.updatestockchange_selectstock_dialog_visible = false;
				this.$router.go(-1)
			},
			getAllsafetyById(){
<<<<<<< HEAD
<<<<<<< HEAD
				let productId=this.productId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.productId).then(response=>{
=======
				//let safetystockId=this.safetystockId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.safetystockId).then(response=>{
>>>>>>> warehouse
=======
				let productId=this.productId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.productId).then(response=>{
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
						this.safetystock=response.data;									
					})
			},
			searchWarehouse(){
				let id=this.safetystock.id;
				this.$axios.post('/api/warehouse/getByid/'+this.id).then(response=>{
						this.warehousePage=response.data;
					})
			},
			update(){
<<<<<<< HEAD
<<<<<<< HEAD
=======
				this.safetystock.safetystockId=this.safetystockId;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				this.$axios.post('/api/safetystock/updateStockChange',this.safetystock).then(response=>{
						if(response.statusCord==200){
							this.$message({
							    showClose: true,
							    message: '修改成功!',
								message:'需要重新复核!!',
							    type: 'success',
								duration:2000
							});
							this.close();
						}else{
							this.$message({
							    showClose: true,
							    message: '修改失败!',
							    type: 'error',
								duration:2000
							});
						}
					})
			},
			
			
				
		},
		
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
