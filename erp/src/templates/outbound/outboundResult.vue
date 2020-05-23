<template>
	<!--出库调度详细页面-->
	<el-dialog id="outboundResult" :before-close="close" width="80%" :visible.sync="dialog_visible">
		<div id="outboundResult">
				<router-view></router-view>
				<div id="body" :data="spay">
					<p style="color: #000000;text-align: center;font-size: 20px;">出库调度单</p>
					<el-form :inline="true" style="padding-left: 200px;" :model="spay" label-width="90px">
						<el-form-item label="出库单编号:" prop="payId">
							<span>{{spay.payId}}</span>
						</el-form-item>
						<el-form-item label="">
							<span></span>
						</el-form-item><br/>
						<el-form-item label="出库人:" prop="storer">
							<span>{{spay.storer}}</span>
						</el-form-item>
						<el-form-item label="出库理由:" prop="reasonexact" style="padding-left: 150px;">
							<span>{{spay.reasonexact}}</span>
						</el-form-item>
						<el-table ref="multipleTable" :data="spay.spayDetails" border :header-cell-style="{background:'whitesmoke'}" style="width:95%;margin-left:-40px;"
						 tooltip-effect="dark">
							<el-table-column prop="warehouseId" label="仓库名称">
								<template slot-scope="scope">
									<el-select v-model="warehouseId" placeholder="仓库" style="width:95px;">
										<el-option v-for="(h,index) in house" v-bind:key="index" 
										    :label="h.storeName"   :value="h.sid" >
											<!-- {{h.storeName}} -->
										</el-option>
									</el-select>
								</template>
							</el-table-column>
							<el-table-column prop="productId" label="产品编号">
								<template slot-scope="scope">
									<span>{{scope.row.productId}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="productId" label="产品编号">
								<template slot-scope="scope">
									<span>{{scope.row.productId}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="address" label="数量">
								<template slot-scope="scope">
									<span>{{scope.row.amount}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="amounUnit" label="单位">
								<template slot-scope="scope">
									<span>{{scope.row.amounUnit}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="costPrice" label="成本单价">
								<template slot-scope="scope">
									<span>{{scope.row.costPrice}}</span>
								</template>
							</el-table-column>
							<el-table-column prop="detailSum" label="本次出库数量">
								<template slot-scope="scope">
									<number :min="0" :max="scope.row.amount" v-model="scope.row.detailSum" >
									</number>
								</template>
							</el-table-column>
						</el-table><br/>
						<el-form-item label="登记人:" style="padding-right: 120px;" prop="register">
							<span>{{spay.storer}}</span>
						</el-form-item>
						<el-form-item label="登记时间:" prop="registertime">
							<span>{{spay.registerTime}}</span>
						</el-form-item><br/>
						<el-form-item label="备注:" prop="remark">
							<span>{{spay.remark}}</span>
						</el-form-item>
						<el-form-item style="padding-left:400px;">
							<el-button type="primary" @click="sure()" >确定</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
	</el-dialog>
</template>
	
<script>
		import number from '@/components/number.vue'
		export default{
			name:'outboundResult',
			data(){
				return{
					dialog_visible:false,
					spay:{},
					details:{},
					detailSum:0,
					house:[],
					warehouseId:''
				}
			},
			methods:{
				close(){
					this.$router.go(-1);
					this.dialog_visible = false;
				},
				getAllHouse(){		//根据产品不同的用途查询不同的仓库
						let productId=this.$route.params[Object.keys(this.$route.params)[1]];
						this.$axios.get(`/api/storehouse/selectByPId/${productId}`).then(response=>{
						this.house = response.data;
						if(!this.spay.warehouseId){
							this.spay.warehouseId=this.house[0]
						}
					}).catch(err=>{
						alert('请求失败')
					})
				},
				selectByPayId(){		//获取出库单编号，查询当前	
					let payId=this.$route.params[Object.keys(this.$route.params)[0]];
					let productId=this.$route.params[Object.keys(this.$route.params)[1]];
					let sPayDetails={
						parentId:payId,
						productId:productId
					}
					this.$axios.get("/api/stockOut/selectDetails",{params:sPayDetails}).then(response=>{
						this.spay = response.data;
					}).catch(err=>{
						alert('请求失败')
					})
				},
				sure(){			
					this.spay.spayDetails[0]['detailSum']=this.detailSum;
					this.spay.spayDetails[0]['warehouseId']=this.warehouseId;
					this.$axios.post('/api/stockOut/upSum',this.spay.spayDetails).then(response => {
						let responseData = response.data;
						if (response.statusCord == 200) {
							this.$message({
								showClose: true,
								message: '调度成功!',
								type: 'success',
								duration: 2000
							});
							this.close();
						} else {
							this.$message({
								showClose: true,
								message: responseData.message,
								type: 'error',
								duration: 2000
							});
						}
					this.dialog_visible = false;
				})
			}
		},
		created(){
			this.dialog_visible = true;
			this.selectByPayId();
			this.getAllHouse();
		},components:{
			number
		}
	}
	</script>

<style>
</style>
