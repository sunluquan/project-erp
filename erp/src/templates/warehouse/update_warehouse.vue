<template>
	<el-dialog id="updateWarehouse" :before-close="close" width="40%" title="修改部门" :visible.sync="update_warehouse_dialog_visible">
		<el-form :model="dept" :rules="rules" ref="updateWarehouse" :label-position="labelPosition" label-width="90px">			
			<el-form-item label="仓库名称:" prop="storeName" :label-width="formLabelWidth">
				<el-input autocomplete="off" v-model="house.storeName"></el-input>
			</el-form-item>
			<el-form-item label="仓库地址:" :label-width="formLabelWidth">
				<el-input autocomplete="off" v-model="house.storeAddress"></el-input>
			</el-form-item>
			<el-form-item label="仓库说明:" :label-width="formLabelWidth">
				<el-input autocomplete="off" v-model="house.storeRemark"></el-input>
			</el-form-item>			
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close()">取 消</el-button>
			<el-button type="button" v-on:click="updatewarehouse()">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default{
		name:'updateWarehouse',
		//props:['rid'],
		data(){
			return{
				labelPosition: 'right',	
				sid:null,
				house:{},
				update_warehouse_dialog_visible:false,
				rules:{
					deptName:{

					}
				}
			}
		},
		methods:{
			getwarehouseById(){
				let sid=this.sid;
				this.$axios.get(`api/warehouse/getHouseById/${sid}`)
					.then(response=>{
						this.house=response.data;
					})
			},
			close(){
				this.update_warehouse_dialog_visible=false;
				this.$router.go(-1);
			},
			updatewarehouse(){
				this.$axios.post('/api/warehouse/updateHouse',this.house).then(response=>{
						if(response.statusCord==200){
							this.$message({
							    showClose: true,
							    message: '修改成功!',
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
			}
		},created(){
			this.sid=this.$route.params[Object.keys(this.$route.params)[0]];
			this.getwarehouseById();
			this.update_warehouse_dialog_visible=true;
		}
	}
</script>

<style>
</style>
