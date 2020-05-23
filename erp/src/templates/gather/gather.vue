<template>
  <div id="gather">
	  <router-view @insert="insertgather"></router-view>
	  <div style="margin-left: 804px;">		  
				<el-button type="primary" v-on:click="addproduct" size="mini" plain>添加产品</el-button>
				
				<el-button type="primary" size="mini"  v-on:click="insert()" plain>提交</el-button>			     		   		  	 
	  </div>
				  
	  <div id="body">
		<p style="color: #000000;text-align: center;font-size: 18px;margin-top: 0px;">入库申请单</p>	  		  	
	  	<el-form :inline="true" :model="gather" :label-position="labelPosition" label-width="165px">			
	  		<el-form-item  label="入库人:" style="padding-right: 150px;" prop="product_id">
	  		  <el-input class="MyClass" v-model="gather.storer"></el-input>
	  		</el-form-item>
			<el-form-item label="入库理由:" prop="product_name">
			   <el-input  class="MyClass" v-model="gather.reasonexact"></el-input>
			</el-form-item>				
				
	  		<el-table  ref="multipleTable" :data="gather.gatherDetails" border :header-cell-style="{background:'whitesmoke'}" style="width: 89%;margin-left: 60px;"
	  		 tooltip-effect="dark" @selection-change="handleSelectionChange">
			    
	  		    <el-table-column prop="productName" label="产品名称"  width="140px">
	  				<template slot-scope="scope">			
							<span>{{scope.row.productName}}</span>											
	  				</template>					
	  		    </el-table-column>
	  			<el-table-column prop="productId" label="产品编号" width="200px">
					<template slot-scope="scope">
						<span>{{scope.row.productId}}</span>
					</template>
	  			</el-table-column>
	  			<el-table-column prop="address" label="数量" width="120px">
	  				<template slot-scope="scope">	  					
	  						<el-input class="MyClasssum" size="mini"  v-model="scope.row.amount">	  							
	  						</el-input>													
	  				</template>
	  			</el-table-column>
	  			<el-table-column prop="address" label="单位" width="90px">
	  				<template slot-scope="scope">	  					
							<span>{{scope.row.productUnit}}</span>													
	  				</template>
	  			</el-table-column>	
	  			<el-table-column prop="costPrice" label="成本单价" width="100px">
	  				<template slot-scope="scope">	  					
							<span>{{scope.row.realCostPrice}}</span>														
	  				</template>
	  			</el-table-column>
	  			<el-table-column prop="address" label="小计" width="101px">
	  				<template slot-scope="scope">
	  					<el-input class="MyClasssum" size="mini"  v-model="gather.gatherDetails.subtotal">							
						</el-input>									
	  				</template>
	  			</el-table-column>
				<el-table-column prop="address" label="操作" width="110px">
					<template slot-scope="scope">
					<el-button type="text"  @click="delete(scope.row.id)">删除</el-button>
					</template>
				</el-table-column>
	  		  </el-table> 
						   
	  		  <el-form-item label="总件数:" style="padding-right: 120px;" prop="register">
	  		     <el-input class="MyClasssum" v-model="gather.amountSum"></el-input>
	  		  </el-form-item>	
	  		     <el-form-item label="总金额:" prop="registertime">	  	     
	  		  		 <el-input  class="MyClasssum" v-model="gather.costPriceSum"></el-input>
	  		     </el-form-item>
	  			<el-form-item label="登记人:"  prop="register">
					<span>{{register}}</span>
	  			   <!-- <el-input  class="MyClass" v-model="register"></el-input> -->
	  			</el-form-item>	
	  			<el-form-item label="登记时间:" prop="registertime">		  	     
	  				<el-date-picker type="date" placeholder="选择日期" size="small">						   
	  			    </el-date-picker> 
	  			</el-form-item>
	  			<el-form-item label="备注:" style="padding-right: 85px;">
	  			      <el-input type="textarea" style="width:700px;height:80px" v-model="gather.remark"></el-input>
	  			</el-form-item>				   
	  	</el-form>
	  </div>
	  
  </div>
  			
</template>

<script>
	export default {
		name:'gather',
		data() {			
	     	return {						
				multipleSelection:[],				
				dataPicke: new Date,
				custom: 'custom',				
				labelPosition: 'right',	
																									
				gather:{
					add:[],
					gatherId:'',
					storer:'',
					reasonexact:'',
					amount:'',
					amountSum:'',
					costPriceSum:'',
					remark:'',
					register:'',
					gatherDetails:[]									
				},
				isadmin: 3,										
		}
		},
		
		created(){							
		    //this.getddbh();		
			//this.insertgather();
			
		},
		methods: {	
			delete(id){
				alert('进来了');
				let index=this.multipleSelection.findIndex(item =>{
					return item.id === id
				})
				this.gatherDetails.splice(index,1);
			},
			handleSelectionChange(val) {
			        this.multipleSelection = val;
					/*let nums=0;
					let amount=this.multipleSelection.concat('amount');														
						nums=nums+amount;
						this.amountSum=nums;
						alert(this.amountSum);	*/					
			      },			
			addproduct(){
				this.$router.push({path: '/index/gather.html/gatherproduct'})
			},
			insertgather(product){
				let gatherDetails=JSON.parse(JSON.stringify(product));																					
				this.gather.gatherDetails.push(gatherDetails);
			},						
			insert(){				 					
					  this.$axios.post('/api/gather/addgather',this.gather).then(response => {
								
							    let responseData=response.data;
								alert(responseData.gatherId);
								//this.gathers=response.data;	
								//this.details();
								if (response.statusCord == 200) {
									this.$message({
										showClose: true,
										message: '提交成功!',
										message:'需要复核!!',
										type: 'success',
										duration: 2000
									});	
								
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
			
			},
			computed: {
			   register(){
				   return this.$store.getters.getActiveUser.user.uid
			   }
		}
		
	}
</script>

<style>
	#body{
		padding-top: 20px;
		margin-top: 5px;
		margin-left: 80px;
		width: 970px;
		height: 540px;
		border:1px solid lightgray;
	}
	.MyClass input.el-input__inner{
		width: 200px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		/*outline: medium;*/
	}
	.input input.el-input__inner{
		width: 200px;
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
