<template>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
 <el-dialog id="inventoryRegistrationSheet" :before-close="close" width="66%" title="入库单" style="text-align: center;" :visible.sync="inventoryRegistrationSheet_gascheduling_dialog_visible">
  
	<div id="box">					
		<el-form :inline="true" :model="registration" class="demo-form-inline" :label-position="labelPosition" label-width="160px">
			<el-form-item  label="入库单编号:" style="padding-right: 170px;" prop="gatherId">
			  <el-input class="input" v-model="registration.gatherId"></el-input>
			</el-form-item>
			<el-form-item label=".......">
			   <el-input  class="input"></el-input>
			</el-form-item>		
			<el-form-item  label="入库理由:" style="padding-right: 180px;" prop="reasonexact">
			   <el-input  class="MyClass" v-model="registration.reasonexact"></el-input>
			</el-form-item>	
			<el-form-item  label="入库详细理由:" >
			   <el-input  class="MyClass"></el-input>
			</el-form-item>
			<el-table :data="registration.gatherDetails" size="small" :header-cell-style="{background:'whitesmoke'}"  border style="width: 97%;margin-left: 16px;">
			    <el-table-column prop="id" label="序号"  width="60px;">
					<template slot-scope="scope">
					<span>{{scope.row.id}}</span>
					</template>
			    </el-table-column>	
			    <el-table-column prop="storeName" label="产品编号"  width="180px">
					<template slot-scope="scope">
						    <span>{{scope.row.productId}}</span>
					</template>					
			    </el-table-column>
				<el-table-column prop="storeAddress" label="产品名称" width="146px">		
				    <template slot-scope="scope">
						<span>{{scope.row.productName}}</span>
				    </template>
				</el-table-column>	
				<el-table-column prop="amountSum" label="库房名称"  width="99px">
					<template slot-scope="scope">	
							<span>{{scope.row.storeName}}</span>
					</template>										
				</el-table-column>		
				<el-table-column prop="amountSum" label="存放地址"  width="220px">
					<template slot-scope="scope">	
						<span>{{scope.row.storeAddress}}</span>
					</template>										
				</el-table-column>
				<el-table-column prop="amountSum" label="应入库数量"  width="90px">
					<template slot-scope="scope">	
							<span>{{scope.row.amount}}</span>
					</template>										
				</el-table-column>		
				<!-- <el-table-column prop="amountSum" label="已入库数量"  width="90px">
					<template slot-scope="scope">	
						<span>{{scope.row.paidAmount}}</span>
					</template>										
				</el-table-column> -->
				<el-table-column prop="amount"  label="本次入库数量"  width="110px">
					<template slot-scope="scope">	
				      <el-input class="MyClasssum" v-model="scope.row.paidAmount" size="mini"></el-input>			      
					</template>										
				</el-table-column>
			  </el-table>
			  <br />
			   <el-form-item label="应入库总件数" style="padding-right: 170px;">
				   <el-input class="input" v-model="registration.amountSum"></el-input>
			   </el-form-item>			  
			   <el-form-item label="已入库总件数:"  prop="register">
			   	   <el-input  class="input" v-model="registration.gatheredAmountSum"></el-input>
			   </el-form-item>	
			   <el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 170px;">
			         <el-input class="input" v-model="registration.costPriceSum"></el-input>	 
			   </el-form-item>							
			   <el-form-item label="已入库总成本:" prop="registertime">		  	     						 
			         <el-input class="input">0.0</el-input>	 
			   </el-form-item>	
			<el-form-item label="登记人:" style="padding-right: 178px;" prop="activeUser">
			   <el-input class="input" v-model="registration.register"></el-input>
			</el-form-item>	
			<el-form-item label="登记时间:" prop="registertime">		  	     
				<el-input class="input" v-model="registration.registerTime"></el-input>
			</el-form-item>										    		   			  					
		</el-form>
	</div>
	<div slot="footer" class="dialog-footer">		
	     <el-button size="mini" round @click="updnum()">确定</el-button>
		<el-button size="mini" round @click="close()">返回</el-button>
	</div>
<<<<<<< HEAD
=======
	<el-dialog id="inventoryRegistrationSheet" :before-close="close" width="61%" title="入库单" style="text-align: center;"
	 :visible.sync="inventoryRegistrationSheet_gascheduling_dialog_visible">

		<div id="box">
			<el-form :inline="true" :model="registration" class="demo-form-inline" :label-position="labelPosition" label-width="197px">
				<el-form-item label="入库单编号:" style="padding-right: 40px;margin-top: 22px;" prop="gatherId">
					<el-input class="input" v-model="registration.gatherId"></el-input>
				</el-form-item>
				<el-form-item label=".......">
					<el-input class="input"></el-input>
				</el-form-item>
				<el-form-item label="入库理由:" style="padding-right: 40px;" prop="reasonexact">
					<el-input class="MyClass" v-model="registration.reasonexact"></el-input>
				</el-form-item>
				<el-form-item label="入库详细理由:">
					<el-input class="MyClass"></el-input>
				</el-form-item>
				<el-table :data="registration.gatherDetails" size="medium" :header-cell-style="{background:'whitesmoke'}" border
				 style="width: 95%;margin-left: 26px;">
					<el-table-column prop="id" label="序号" width="60px;">
						<template slot-scope="scope">
							<span>{{scope.row.id}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="storeName" label="产品编号" width="180px">
						<template slot-scope="scope">
							<span>{{scope.row.productId}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="storeAddress" label="产品名称" width="146px">
						<template slot-scope="scope">
							<span>{{scope.row.productName}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="amountSum" label="库房名称" width="99px">
						<template slot-scope="scope">
							<span>{{scope.row.storeName}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="amountSum" label="存放地址" width="180px">
						<template slot-scope="scope">
							<span>{{scope.row.storeAddress}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="realCostPrice" label="单价" width="90px">
						<template slot-scope="scope">
							<span>{{scope.row.realCostPrice}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="amount" label="应入库数量" width="90px">
						<template slot-scope="scope">
							<span>{{scope.row.amount}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="amountSum" label="已入库数量" width="90px">
						<template slot-scope="scope">
							<!--<span>{{scope.row.paidAmount}}</span>-->
						</template>
					</el-table-column>
					<el-table-column prop="paidAmount" label="本次入库数量" width="110px">
						<template slot-scope="scope">
							<el-input class="MyClasssum" v-model="scope.row.paidAmount" size="mini"></el-input>
						</template>
					</el-table-column>
				</el-table>
				<br />
				<el-form-item label="应入库总件数:" style="padding-right: 270px;">
					<span>{{shul}}</span>
				</el-form-item>
				<el-form-item label="本次入库总件数:" prop="register">
					<span>{{nums}}</span>
				</el-form-item>
				<el-form-item label="应入库总成本:" prop="registertime" style="padding-right: 270px;">
					<span>{{summoney}}</span>
				</el-form-item>
				<el-form-item label="本次入库总成本:" prop="registertime">
					<spna>
						{{totalMoney}}
					</spna>
				</el-form-item>
				<el-form-item label="登记人:" style="padding-right: 50px;" prop="activeUser">
					<el-input class="input" v-model="registration.register"></el-input>
				</el-form-item>
				<el-form-item label="登记时间:" prop="registertime">
					<el-input class="input" v-model="registration.registerTime"></el-input>
				</el-form-item>
			</el-form>
		</div>
		<div slot="footer" class="dialog-footer">
			<el-button size="mini" round @click="updnum()">确定</el-button>
			<el-button size="mini" round @click="close()">返回</el-button>
		</div>
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	</el-dialog>
</template>

<script>
	export default {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		name:'inventoryRegistrationSheet',
		data() {			
	     	return {
				shul:0,
				id:'',
				dataPicke: new Date,
				custom: 'custom',							
				amount:'',
				paidAmount:'',
				labelPosition: 'right',				       
				inventoryRegistrationSheet_gascheduling_dialog_visible: false,			   								
				registration:{},								
				isadmin: 3,					
		}
		},
		
		created(){			
			let gatherId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.gatherId=gatherId;			
			console.log(this.$route);
			this.inventoryRegistrationSheet_gascheduling_dialog_visible = true;						
			this.getAllregistrationById();
			// this.qwe();
		},
		methods: {
			// qwe(){
				
			// 	this.registration.forEach(then=>{
			// 		alert(1);
			// 	})
			// },
<<<<<<< HEAD
=======
		name: 'inventoryRegistrationSheet',
		data() {
			return {
				id: '',
				shul: 0,
				summoney: 0.0,
				dataPicke: new Date,
				custom: 'custom',
				amount: '',
				nums: 0,
				paidAmount: '',
				labelPosition: 'right',
				inventoryRegistrationSheet_gascheduling_dialog_visible: false,
				registration: {},
				isadmin: 3,
			}
		},

		created() {
			let gatherId = this.$route.params[Object.keys(this.$route.params)[0]];
			this.gatherId = gatherId;
			console.log(this.$route);
			this.inventoryRegistrationSheet_gascheduling_dialog_visible = true;
			this.getAllregistrationById();
			//this.numss();
		},
		methods: {
			numss() {
				// alert('憨憨');
				// var b = 0;
				// var s = 0;
				// for (var a = 0; this.registration.length > a; a++) {
				// 	b = b + this.registration.gatherDetails[a].qwe;
				// 	s = b * this.registration.gatherDetails[a].realCostPrice;
				// }
				// this.nums = b;
				// this.costprice = s;
			},
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			close() {
				this.inventoryRegistrationSheet_gascheduling_dialog_visible = false;
				this.$router.go(-1)
			},
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			getAllregistrationById(){						
				this.$axios.get('api/gather/byIdAll/'+this.gatherId).then(response=>{
						this.registration=response.data;
					})
			},updnum(){
				this.$axios.post('/api/gather/register',this.registration).then(response=>{
						if(response.statusCord==200){
							this.$message.success('产品登记成功')
						}else{
							this.$message.error(response.data)
							}
					})
					}
					
			// }
		//    updnum(){
		// 		alert('进来了');
		// 		this.schedulings.id=this.id;
		// 		this.$axios.post('api/gather/updgatherDetailsNum',this.schedulings).then(response=>{
		// 			this.schedulings=response.data;
		// 			 if(response.statusCord==200){
		// 			 	this.$message({
		// 			 	    showClose: true,
		// 			 	    message: '产品调度成功!',
		// 			 	    type: 'success',
		// 			 		duration:2000
		// 			 	});
		// 			 	this.close();
		// 			 }else{
		// 			 	this.$message({
		// 			 	    showClose: true,
		// 			 	    message: '调度失败!',
		// 			 	    type: 'error',
		// 			 		duration:2000
		// 			 	});
		// 			 }
		// 		});						
		// 		this.gaschedulingdetails_gascheduling_dialog_visible = false;
		// 		this.$router.go(-1);
		},
	  
		
		computed: {
			activeUser() {
						return this.$store.getters.getActiveUser.user.uid;
				},
			routerNames() {
						return this.$store.getters.getRouterNames;
			}			
		}
				
<<<<<<< HEAD
=======
			getAllregistrationById() {
				this.$axios.get('api/gather/byIdAll/' + this.gatherId).then(response => {
					// this.registration[0].paidAmount=0;
					this.registration = response.data;
					var b = 0;
					var s = 0;
					
					this.shul = b;
					this.summoney = s;
				})
			},
			updnum() {
				this.$axios.post('/api/gather/register', this.registration).then(response => {
					if (response.statusCord == 200) {
						this.$message.success('产品登记成功');
					} else {
						this.$message.error(response.data)
					}
				})
			},

		},
		computed: {
			activeUser() {
				return this.$store.getters.getActiveUser.user.uid;
			},
			routerNames() {
				return this.$store.getters.getRouterNames;
			},totalMoney(){
				let shul=0;
				let summoney=0;
				let nums=0;
				let totalMoney=0;
				this.registration.gatherDetails.forEach(item=>{
					let amount=parseInt(item.amount)
					let realCostPrice=parseFloat(item.realCostPrice)
					let paidAmount=parseInt(item.paidAmount);
					if(isNaN(paidAmount))paidAmount=0;
					shul+=amount;
					summoney+=realCostPrice*amount;
					nums+=paidAmount;
					totalMoney+=paidAmount*realCostPrice;
				})
				this.shul=shul;
				this.summoney=summoney;
				this.nums=nums;
				totalMoney=totalMoney.toFixed(2)+'元'
				return totalMoney
			}
		}

>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	}
</script>

<style>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	#box{
		width: 935px;
		height: 480px;
		border:1px solid gray;
	}
	.MyClass input.el-input__inner{
<<<<<<< HEAD
=======
	#box {
		width: 855px;
		height: 520px;
		border: 1px solid gray;
	}

	.MyClass input.el-input__inner {
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		width: 160px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		/*outline: medium;*/
	}
<<<<<<< HEAD
<<<<<<< HEAD
	.input input.el-input__inner{
=======

	.input input.el-input__inner {
>>>>>>> warehouse
=======
	.input input.el-input__inner{
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		width: 160px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 0px;
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
    .MyClasssum input.el-input__inner{
    	width:80px;
    	border-top-width: 0px;
    	border-left-width: 0px;
    	border-right-width: 0px;
    	border-bottom-width: 0px;
    	/*outline: medium;*/
    }		
<<<<<<< HEAD
=======

	.MyClasssum input.el-input__inner {
		width: 80px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 0px;
		/*outline: medium;*/
	}
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
</style>
