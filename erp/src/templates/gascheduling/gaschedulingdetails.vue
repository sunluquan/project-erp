<template>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
 <el-dialog id="gaschedulingdetails" :before-close="close" width="65%" title="入库调度单" style="text-align: center;" :visible.sync="gaschedulingdetails_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="scheduling" class="demo-form-inline" :label-position="labelPosition" label-width="100px">
			<el-form-item  label="入库单编号:" style="padding-right: 170px;" prop="gatherId">
			  <el-input class="input" v-model="scheduling[0].gatherId"></el-input>
			</el-form-item>
			<el-form-item label="产品编号:" prop="materielid">
			  <el-input  class="input" v-model="scheduling[0].productId"></el-input>
			</el-form-item>
			<el-form-item  label="产品名称:" style="padding-right: 180px;" prop="materielname">
			   <el-input  class="MyClass" v-model="scheduling[0].productName"></el-input>
			</el-form-item>	
			<el-form-item  label="成本单价:" >
			   <el-input  class="MyClass" v-model="scheduling[0].realCostPrice"></el-input>
			</el-form-item>
			
			<el-table :data="scheduling" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 94%;margin-left: 30px;">
			    <el-table-column prop="id" label="序号"  width="80px;">
					<template slot-scope="scope">
					<span>{{scope.row.id}}</span>
					</template>
			    </el-table-column>	
			    <el-table-column prop="storeName" label="库房名称"  >
					<template slot-scope="scope">
						    <span>{{scope.row.storeName}}</span>
					</template>					
			    </el-table-column>
				<el-table-column prop="storeAddress" label="存放地址" width="180px">		
				    <template slot-scope="scope">
						<span>{{scope.row.storeAddress}}</span>
				    </template>
				</el-table-column>	
				<el-table-column prop="amountSum" label="存储单元"  width="110px">
					<template slot-scope="scope">			
					</template>										
				</el-table-column>		
				<el-table-column prop="amountSum" label="当前可存储数量"  width="180px">
					<template slot-scope="scope">	
						<span>{{scope.row.maxamount}}</span>
					</template>										
				</el-table-column>
				<!--<el-table-column prop="amount"  label="本次入库数量"  width="120px">
					<template slot-scope="scope">	
				      <el-input class="MyClasssum" v-model="scope.row.amount" size="mini"></el-input>			      
					</template>										
				</el-table-column>-->
			  </el-table>
			  <br />
			  <el-form :inline="true" :model="schedulings" class="demo-form-inline" :label-position="labelPosition" label-width="100px">
			   <el-form-item label="本次入库数量" style="padding-right: 170px;">
				   <el-input class="MyClass" v-model="schedulings.amount" size="mini"></el-input>
			   </el-form-item>			  
			  <el-form-item label="序号">
			     <el-input  class="input"></el-input>
			  </el-form-item>
			  </el-form>
			
<<<<<<< HEAD
=======
 <el-dialog id="gaschedulingdetails" :before-close="close" width="61%" title="入库调度单" style="text-align: center;" :visible.sync="gaschedulingdetails_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="scheduling" class="demo-form-inline" :label-position="labelPosition" label-width="100px">
			<el-form-item  label="入库单编号:" style="padding-right: 170px;margin-top: 20px;" prop="gatherId">
			  <el-input class="input" v-model="scheduling.gatherId"></el-input>
			</el-form-item>
			<el-form-item label="产品编号:" prop="materielid" style="margin-top: 20px;">
			  <el-input  class="input" v-model="scheduling.productId"></el-input>
			</el-form-item>
			<el-form-item  label="产品名称:" style="padding-right: 180px;" prop="materielname">
			   <el-input  class="MyClass" v-model="scheduling.productName"></el-input>
			</el-form-item>	
			<el-form-item  label="成本单价:" >
			   <el-input  class="MyClass" v-model="scheduling.realCostPrice"></el-input>
			</el-form-item>
			<!-- {{scheduling.gatherDetails}} -->
			<el-table :data="scheduling.gatherDetails" size="medium" :header-cell-style="{background:'whitesmoke'}"  border style="width: 94%;margin-left: 30px;">
			    <el-table-column prop="sid" label="序号"  width="80px;">
					<template slot-scope="scope">
					<span>{{scope.row.sid}}</span>
					</template>
			    </el-table-column>	
			    <el-table-column prop="storeName" label="库房名称"  >
					<template slot-scope="scope">						
							<el-select v-model="scope.row.sid" size="small" @change="storehouseChange">
							     <el-option v-for="(storehouse,index) in warehousePage" v-bind:key="index"
							      :label="storehouse.storeName" :value="storehouse.sid">
							     </el-option>
							</el-select>					
					</template>					
			    </el-table-column>
				<el-table-column prop="storeAddress" label="存放地址" width="210px">		
				    <template slot-scope="scope">
						 <span>{{scope.row.storehouse|whetherShow('storeAddress')}}</span>
				    </template>
				</el-table-column>	
				<el-table-column prop="amountSum" label="存储单元"  width="90px">
					<template slot-scope="scope">			
					</template>										
				</el-table-column>		
				<el-table-column prop="amountSum" label="当前可存储数量"  width="140px">
					<template slot-scope="scope">	
						<span>{{scope.row.storehouse|whetherShow('maxamount')}}</span> 						
					</template>										
				</el-table-column>
				<el-table-column prop="amount"  label="本次入库数量"  width="120px">
					<template slot-scope="scope">	
				      <el-input class="MyClasssum" v-model="scope.row.amount" size="mini"></el-input>			      
					</template>										
				</el-table-column>
			  </el-table>
			  <br />
			  			
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			<el-form-item label="登记人:" style="padding-right: 250px;" prop="activeUser">
			   <span>{{activeUser}}</span>
			</el-form-item>	
			<el-form-item label="登记时间:" prop="registertime">		  	     
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				<el-date-picker type="date" placeholder="选择日期" size="small" width="40px">						   
			    </el-date-picker>
			</el-form-item>
			<el-form-item label="应入库总件数:" style="padding-right: 170px;" prop="register">
				 <el-input class="input" v-model="scheduling[0].stockamount"></el-input>
			</el-form-item>	
			<el-form-item label="已入库总件数:"  prop="register">
				   <el-input  class="input" v-model="scheduling[0].amountSum"></el-input>
			</el-form-item>			
		    <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 170px;">		  	     						 
			      <el-input class="input" v-model="scheduling[0].moneys"></el-input>	 
			</el-form-item>							
			<el-form-item label="已入库总成本:" prop="registertime">		  	     						 
			      	<el-input  class="input" v-model="scheduling[0].costPriceSum"></el-input>	 
<<<<<<< HEAD
=======
				<el-date-picker type="date" v-model="scheduling.registerTime" placeholder="选择日期" size="small" width="40px">						   
			    </el-date-picker>
			</el-form-item>
			<el-form-item label="应入库总件数:" style="padding-right: 120px;" prop="register">
				 <el-input class="input" v-model="scheduling.mustAmount"></el-input>
			</el-form-item>	
			<el-form-item label="已入库总件数:"  prop="nums">
				   <span>{{nums}}</span>
			</el-form-item>			
		    <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 120px;">		  	     						 
			      <el-input class="input" v-model="scheduling.subtotal"></el-input>	 
			</el-form-item>							
			<el-form-item label="已入库总成本:" prop="moneys">		  	     						 
			      	<span>{{moneys}}</span>	 
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			</el-form-item>			   			  					
		</el-form>
	</div>
	<div slot="footer" class="dialog-footer">		
<<<<<<< HEAD
<<<<<<< HEAD
	     <el-button size="mini" round @click="updnum()">确定</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
=======
	     <el-button type="danger" size="mini" round @click="updnum()">确定</el-button>
		<el-button type="primary" size="mini" round @click="close()">返回</el-button>
>>>>>>> warehouse
=======
	     <el-button size="mini" round @click="updnum()">确定</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	</div>
	</el-dialog>
</template>

<script>
	export default {
		name:'gaschedulingdetails',
		data() {			
	     	return {
				shul:0,
				id:'',
				dataPicke: new Date,
				custom: 'custom',
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				productId:'',				
				amount:'',
				labelPosition: 'right',				       
				gaschedulingdetails_gascheduling_dialog_visible: false,			   								
				scheduling:[],
				schedulings:{},
<<<<<<< HEAD
=======
				productId:'',
				nums:0,
				moneys:0.0,
				amount:'',
				warehousePage:[],
				labelPosition: 'right',				       
				gaschedulingdetails_gascheduling_dialog_visible: false,			   								
				scheduling:{},
				
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				gather:{},
				isadmin: 3,					
		}
		},
		
		created(){			
<<<<<<< HEAD
<<<<<<< HEAD
			let id=this.$route.params[Object.keys(this.$route.params)[0]];
			this.id=id;			
=======
			let productId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.productId=productId;			
>>>>>>> warehouse
=======
			let id=this.$route.params[Object.keys(this.$route.params)[0]];
			this.id=id;			
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			console.log(this.$route);
			this.gaschedulingdetails_gascheduling_dialog_visible = true;						
			this.getAllsafetyById();
			//this.schedulingid.push(schedulingid);
<<<<<<< HEAD
<<<<<<< HEAD
		},
		methods: {
=======
			this.searchWarehouse();
		},
		methods: {
			storehouseChange(id){
				let index=this.warehousePage.findIndex(item=>item.sid===sid);
				this.scheduling.gatherDetails[0].storehouse=this.warehousePage[index];
			},
>>>>>>> warehouse
=======
		},
		methods: {
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			close() {
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1)
			},
			getAllsafetyById(){		
				//let id=this.id;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				this.$axios.get('api/gather/getByidGather/'+this.id).then(response=>{
						this.scheduling.push(response.data);
					})
			},
		    updnum(){
				alert('进来了');
				this.schedulings.id=this.id;
				this.$axios.post('api/gather/updgatherDetailsNum',this.schedulings).then(response=>{
					this.schedulings=response.data;
<<<<<<< HEAD
=======
			this.$axios.get('api/gather/getGatherSafetystock/'+this.productId).then(response=>{
						this.scheduling=response.data;	
						
					})					
				//this.$axios.get('api/gather/getByidGather/'+this.productId).then(response=>{
				//		this.scheduling=response.data;												
				//	})	
			},			
		    updnum(){
				//this.scheduling.productId=this.productId;
				//this.scheduling.sid=this.scheduling.gatherDetails.sid;
				this.$axios.post('api/gather/updgathersum',this.scheduling).then(response=>{
					this.scheduling=response.data;
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
					 if(response.statusCord==200){
					 	this.$message({
					 	    showClose: true,
					 	    message: '产品调度成功!',
					 	    type: 'success',
					 		duration:2000
					 	});
					 	this.close();
					 }else{
					 	this.$message({
					 	    showClose: true,
					 	    message: '调度失败!',
					 	    type: 'error',
					 		duration:2000
					 	});
					 }
				});
				
<<<<<<< HEAD
<<<<<<< HEAD
				alert(this.scheduling[0].gatherId);
=======
				/*alert(this.scheduling[0].gatherId);
>>>>>>> warehouse
=======
				alert(this.scheduling[0].gatherId);
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				this.gather.gatherId=this.scheduling[0].gatherId;
				this.gather.amountSum=this.schedulings.amount;
				alert(this.gather.amountSum);				
				this.gather.costPriceSum=this.scheduling[0].realCostPrice*this.schedulings.amount;				
				this.$axios.post('api/gather/updgathernum',this.gather).then(response=>{
					alert('入库进来了');
					 if(response.statusCord==200){
					 	this.$message({
					 	    showClose: true,
					 	    message: '入库调度成功!',
					 	    type: 'success',
					 		duration:2000
					 	});
					 	this.close();
					 }else{
					 	this.$message({
					 	    showClose: true,
					 	    message: '入库调度失败!',
					 	    type: 'error',
					 		duration:2000
					 	});
					 }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				})
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1);
		},
		
<<<<<<< HEAD
=======
				})*/
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1);
		},
		searchWarehouse(){
			this.$axios.get('/api/gather/StorehousesGather/'+this.productId).then(response=>{
					//this.scheduling=response.data;
					this.warehousePage=response.data;
					console.log(this.warehousePage)
					this.scheduling.gatherDetails[0].sid=this.warehousePage[0].sid;
					
					this.scheduling.gatherDetails[0]['storehouse']=this.warehousePage[0];
				})
		},
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		},
		
		computed: {
			activeUser() {
<<<<<<< HEAD
<<<<<<< HEAD
						return this.$store.getters.getActiveUser.user.uid;
=======
						return this.$store.getters.getActiveUser.user.uname;
>>>>>>> warehouse
=======
						return this.$store.getters.getActiveUser.user.uid;
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				},
			routerNames() {
						return this.$store.getters.getRouterNames;
			}			
<<<<<<< HEAD
<<<<<<< HEAD
=======
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
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		}
				
	}
</script>

<style>
	#box{
		width: 855px;
<<<<<<< HEAD
<<<<<<< HEAD
		height: 500px;
=======
		height: 400px;
>>>>>>> warehouse
=======
		height: 500px;
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
    .MyClasssum input.el-input__inner{
    	width:80px;
    	border-top-width: 0px;
    	border-left-width: 0px;
    	border-right-width: 0px;
    	border-bottom-width: 0px;
    	/*outline: medium;*/
    }		
</style>
