<template>
 <el-dialog id="gaschedulingsheet" :before-close="close" width="66%" title="入库调度单" style="text-align: center;" :visible.sync="gaschedulingsheet_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="schedulingid" class="demo-form-inline" :label-position="labelPosition" label-width="150px">
			<el-form-item  label="入库单编号:" style="padding-right: 180px;" prop="gatherId">
			  <el-input class="input" v-model="schedulingid[0].gatherId"></el-input>
			</el-form-item>
			<el-form-item label=".......">
			   <el-input  class="input"></el-input>
			</el-form-item>
			<el-form-item  label="入库理由:" style="padding-right: 180px;" prop="reasonexact">
			   <el-input  class="MyClass" v-model="schedulingid[0].reasonexact"></el-input>
			</el-form-item>	
			<el-form-item  label="入库详细理由:" >
			   <el-input  class="MyClass"></el-input>
			</el-form-item>
			
			<el-table :data="schedulingid" size="mini" :header-cell-style="{background:'whitesmoke'}"  border style="width: 93%;margin-left: 40px;">
			    <el-table-column prop="id" label="序号"  width="80px;">
					<template slot-scope="scope">
					<span>{{scope.row.id}}</span>
					</template>
			    </el-table-column>	
			    <el-table-column prop="materielname" label="产品名称"  >
					<template slot-scope="scope">
						    <span>{{scope.row.productName}}</span>
					</template>					
			    </el-table-column>
				<el-table-column prop="materielid" label="产品编号" width="160px">		
				    <template slot-scope="scope">
						<span>{{scope.row.productId}}</span>
				    </template>
				</el-table-column>
				<el-table-column prop="amount" label="应入库件数"  width="90px">
					<template slot-scope="scope">
						<span>{{scope.row.stockamount}}</span>
					</template>										
				</el-table-column>
				<el-table-column prop="amount" label="已入库件数"  width="90px">
					<template slot-scope="scope">
						<span>{{scope.row.paidAmount}}</span>
					</template>										
				</el-table-column>		
				<el-table-column prop="amountSum" label="存放地址集合"  width="178px">
					<template slot-scope="scope">						
					</template>										
				</el-table-column>
				<el-table-column  label="调度"  width="90px">
					<template slot-scope="scope">	
						<el-button style="font-size: 13px;" type="text" @click="schedulingids(scope.row.id)">调度</el-button>
					</template>										
				</el-table-column>
			  </el-table>	
			<br /> 
			<el-form-item label="应入库总件数:" style="padding-right: 280px;" prop="register">
				   <span>{{shul}}</span>
			</el-form-item>	
			<el-form-item label="已入库总件数:"  prop="register">
				   <el-input  class="input" v-model="schedulingid[0].amountSum"></el-input>
			</el-form-item>			
		    <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 280px;">		  	     						 
			       <span>0.0</span>	 
			</el-form-item>							
			<el-form-item label="已入库总成本:" prop="registertime">		  	     						 
			      	<el-input  class="input" v-model="schedulingid[0].costPriceSum"></el-input>	 
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
		name:'gaschedulingsheet',
		data() {			
	     	return {
				shul:0,
				gatherId:'',
				dataPicke: new Date,
				custom: 'custom',
				productId:'',
				labelPosition: 'right',				       
				gaschedulingsheet_gascheduling_dialog_visible: false,			   								
				schedulingid:[],
				isadmin: 3,					
		}
		},
		
		created(){			
			let gatherId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.gatherId=gatherId;
			
			console.log(this.$route);
			this.gaschedulingsheet_gascheduling_dialog_visible = true;						
			this.getAllsafetyById();           
		},
		methods: {
			close() {
				this.gaschedulingsheet_gascheduling_dialog_visible = false;
				this.$router.go(-1)
			},
			getAllsafetyById(){		
				this.$axios.get('api/gather/getSchedulingid/'+this.gatherId).then(response=>{
						this.schedulingid=response.data;
						var b=0;
						for(var a=0;this.schedulingid.length>a;a++){
							b=b+this.schedulingid[a].stockamount;
						}
						this.shul=b;
					})
			},
			schedulingids(id){
				this.$router.push({path: '/index/gascheduling.html/gaschedulingdetails/'+id})
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
</style>
