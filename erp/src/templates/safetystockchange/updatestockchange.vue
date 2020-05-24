<template>
 <el-dialog id="updatestockchange" :before-close="close" width="61%" title="安全库存配置单" style="text-align: center;" :visible.sync="updatestockchange_selectstock_dialog_visible">
  
	<div id="box">
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
		  <el-form-item  label="产品编号:" style="padding-right: 90px;" prop="productid">
		    <el-input class="input" v-model="safetystock.product_id"></el-input>
		  </el-form-item>
		  <el-form-item label="产品名称:" prop="productname">
		     <el-input  class="MyClass" v-model="safetystock.product_name"></el-input>
		  </el-form-item>				  		 
		</el-form>	
		
		<el-form :inline="true" :model="safetystock" class="demo-form-inline" :label-position="labelPosition" label-width="130px">
			<el-form-item label="库存报警下限数:" style="padding-right: 90px;" prop="minamount">
			  <el-input class="MyClass" v-model="safetystock.minamount"></el-input>
			</el-form-item>
			<el-form-item label="库存报警上限数:" prop="maxamount">
			   <el-input  class="MyClass" v-model="safetystock.maxamount"></el-input>
			</el-form-item>
			<el-form-item label="设置B/N或S/N:" style="padding-right: 90px;">
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
			  
				<el-form-item label="审核人:" style="padding-right: 90px;" prop="register">
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
				productId:'',
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
			let productId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.productId=productId;
			
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
				let productId=this.productId;
				this.$axios.get('api/safetystock/getAllsafetyById/'+this.productId).then(response=>{
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
