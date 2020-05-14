<template>
 <el-dialog id="gaschedulingdetails" :before-close="close" width="61%" title="入库调度单" style="text-align: center;" :visible.sync="gaschedulingdetails_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="schedulingid" class="demo-form-inline" :label-position="labelPosition" label-width="150px">
			<el-form-item  label="入库单编号:" style="padding-right: 180px;" prop="gatherId">
			  <el-input class="input" v-model="schedulingid.gatherId"></el-input>
			</el-form-item>
			<el-form-item label="产品编号:" prop="materielid">
			  <el-input  class="input" v-model="schedulingid.materielid"></el-input>
			</el-form-item>
			<el-form-item  label="产品名称:" style="padding-right: 180px;" prop="materielname">
			   <el-input  class="MyClass" v-model="schedulingid.materielname"></el-input>
			</el-form-item>	
			<el-form-item  label="成本单价:" >
			   <el-input  class="MyClass" v-model="schedulingid.unitprice"></el-input>
			</el-form-item>
			
			<el-table :data="schedulingid" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 94%;margin-left: 30px;">
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
				<el-table-column  label="本次入库数量"  width="120px">
					<template slot-scope="scope">	
				      <el-input class="MyClasssum" size="mini"></el-input>			      
					</template>										
				</el-table-column>
			  </el-table>	
			<br />
			<el-form-item label="登记人:" style="padding-right: 250px;" prop="activeUser">
			   <span>{{activeUser}}</span>
			</el-form-item>	
			<el-form-item label="登记时间:" prop="registertime">		  	     
				<el-date-picker type="date" placeholder="选择日期" size="small" width="40px">						   
			    </el-date-picker>
			</el-form-item>
			<el-form-item label="应入库总件数:" style="padding-right: 190px;" prop="register">
				 <el-input class="input" v-model="schedulingid.amount"></el-input>
			</el-form-item>	
			<el-form-item label="已入库总件数:"  prop="register">
				   <el-input  class="input" v-model="schedulingid.amountSum"></el-input>
			</el-form-item>			
		    <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 191px;">		  	     						 
			      <el-input class="input" v-model="schedulingid.moneys"></el-input>	 
			</el-form-item>							
			<el-form-item label="已入库总成本:" prop="registertime">		  	     						 
			      	<el-input  class="input" v-model="schedulingid.costPriceSum"></el-input>	 
			</el-form-item>			   			  					
		</el-form>
	</div>
	<div slot="footer" class="dialog-footer">		
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
				labelPosition: 'right',				       
				gaschedulingdetails_gascheduling_dialog_visible: false,			   								
				schedulingid:[],
				isadmin: 3,					
		}
		},
		
		created(){			
			let id=this.$route.params[Object.keys(this.$route.params)[0]];
			this.id=id;			
			console.log(this.$route);
			this.gaschedulingdetails_gascheduling_dialog_visible = true;						
			this.getAllsafetyById();
			this.schedulingid.push(schedulingid);
		},
		methods: {
			close() {
				this.gaschedulingdetails_gascheduling_dialog_visible = false;
				this.$router.go(-1)
			},
			getAllsafetyById(){		
				this.$axios.get('api/gather/getByidGather/'+this.id).then(response=>{
						this.schedulingid=response.data;
												
					})
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
		height: 400px;
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
