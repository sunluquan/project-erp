<template>
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
			
			<el-form-item label="登记人:" style="padding-right: 250px;" prop="activeUser">
			   <span>{{activeUser}}</span>
			</el-form-item>	
			<el-form-item label="登记时间:" prop="registertime">		  	     
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
			</el-form-item>			   			  					
		</el-form>
	</div>
	<div slot="footer" class="dialog-footer">		
	     <el-button size="mini" round @click="updnum()">确定</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
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
				productId:'',				
				amount:'',
				labelPosition: 'right',				       
				gaschedulingdetails_gascheduling_dialog_visible: false,			   								
				scheduling:[],
				schedulings:{},
				gather:{},
				isadmin: 3,					
		}
		},
		
		created(){			
			let id=this.$route.params[Object.keys(this.$route.params)[0]];
			this.id=id;			
			console.log(this.$route);
			this.gaschedulingdetails_gascheduling_dialog_visible = true;						
			this.getAllsafetyById();
			//this.schedulingid.push(schedulingid);
		},
		methods: {
			close() {
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1)
			},
			getAllsafetyById(){		
				//let id=this.id;
				this.$axios.get('api/gather/getByidGather/'+this.id).then(response=>{
						this.scheduling.push(response.data);
					})
			},
		    updnum(){
				alert('进来了');
				this.schedulings.id=this.id;
				this.$axios.post('api/gather/updgatherDetailsNum',this.schedulings).then(response=>{
					this.schedulings=response.data;
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
				
				alert(this.scheduling[0].gatherId);
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
				})
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1);
		},
		
		},
		
		computed: {
			activeUser() {
						return this.$store.getters.getActiveUser.user.uid;
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
		height: 500px;
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
