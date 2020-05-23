<template>
 <el-dialog id="gaschedulingsheet" :before-close="close" width="61%" title="入库调度单" style="text-align: center;" :visible.sync="gaschedulingsheet_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="schedulingid" class="demo-form-inline" :label-position="labelPosition" label-width="150px">
			<el-form-item  label="入库单编号:" style="padding-right: 180px;margin-top: 22px;color: crimson;" prop="gatherId">
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
			
			<el-table :data="schedulingid" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 95%;margin-left: 24px;">
			    <el-table-column prop="id" label="序号"  width="80px;">
					<template slot-scope="scope">
					<span>{{scope.row.id}}</span>
					</template>
			    </el-table-column>	
			    <el-table-column prop="materielname" label="产品名称"  width="150px">
					<template slot-scope="scope">
						    <span>{{scope.row.productName}}</span>
					</template>					
			    </el-table-column>
				<el-table-column prop="materielid" label="产品编号" width="176px">		
				    <template slot-scope="scope">
						<span>{{scope.row.productId}}</span>
				    </template>
				</el-table-column>
				<el-table-column prop="mustAmount" label="应入库件数"  width="110px">
					<template slot-scope="scope">
						<span>{{scope.row.mustAmount}}</span>
					</template>										
				</el-table-column>
				<el-table-column prop="amount" label="已入库件数"  width="90px">
					<template slot-scope="scope">
						<span>0</span>
					</template>										
				</el-table-column>		
				
				<el-table-column prop="payTags" label="状态"  width="110px">
					<template slot-scope="scope">	
						<!-- <el-button type="danger" plain size="mini" v-model="payTags"></el-button> -->
						<span>{{payTags}}</span>
					</template>										
				</el-table-column>
				<el-table-column  label="调度"  width="95px">
					<template slot-scope="scope">	
						<el-button style="font-size: 13px;" size="mini" type="primary" @click="schedulingids(scope.row.productId)">调度</el-button>
					</template>										
				</el-table-column>
			  </el-table>	
			<br /> 
			<el-form-item label="应入库总件数:" style="padding-right: 280px;" prop="register">
				   <span>{{shul}}</span>
				   
			</el-form-item>	
			<el-form-item label="已入库总件数:"  prop="nums">
				   <span>{{nums}}</span>
			</el-form-item>			
		    <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 280px;">		  	     						 
			       <span>{{sumprice}}</span>	 
			</el-form-item>							
			<el-form-item label="已入库总成本:" prop="moneys">		  	     						 
			      	<span>{{moneys}}</span>
			</el-form-item>			   			  					
		</el-form>	
	</div>
	<div slot="footer" class="dialog-footer">		
		<el-button type="primary" size="mini" round @click="close()">返回</el-button>
	</div>
	</el-dialog>
</template>

<script>
	export default {
		name:'gaschedulingsheet',
		data() {			
	     	return {
				shul:0,
				sumprice:0.0,
				gatherId:'',
				payTags:'',
				nums:0,
				moneys:0.0,
				dataPicke: new Date,
				custom: 'custom',
				productId:'',
				labelPosition: 'right',				       
				gaschedulingsheet_gascheduling_dialog_visible: false,			   								
				schedulingid:{},
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
						alert(this.schedulingid.payTag);
						var b=0;
						var s=0;
						for(var a=0;this.schedulingid.length>a;a++){
							b=b+this.schedulingid[a].mustAmount;
							s=b*this.schedulingid[a].realCostPrice;
						}
						this.shul=b;
						this.sumprice=s;
						if(this.schedulingid.payTag==1||this.schedulingid.payTag==2){
							this.payTags='已调度';
						}else{
							this.payTags='等待调度';
						}
					})
			},
			schedulingids(productId){
				if(this.productId!=null){
					this.$router.push({path: '/index/gascheduling.html/gaschedulingdetails/'+productId})
				}else{
					this.$message({
					    showClose: true,
					    message: '请进行安全库存配置!',
					    type: 'danger',
						duration:2000
					});									
				}
				
				
			}
				
		}		
	}
</script>

<style scoped="scoped">
	#box{
		width: 855px;
		height: 450px;
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
