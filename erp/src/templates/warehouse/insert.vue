<template>
	<el-dialog id="insertwarehouse" :before-close="close" width="40%" title="新增仓库" :visible.sync="insert_warehouse_dialog_visible">
		<el-form :model="warehouse" :rules="rules" ref="insertwarehouse" :label-position="labelPosition" label-width="90px">
			<el-form-item label="仓库编号:" prop="sid" >
				<el-input v-model="house.deptId" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="仓库名称:" prop="storeName" >
				<el-input autocomplete="off" v-model="house.storeName"></el-input>
			</el-form-item>
			<el-form-item label="仓库地址:" >
				<el-input autocomplete="off" v-model="house.storeAddress"></el-input>
			</el-form-item>	
			<el-form-item label="仓库说明:" prop="storeRemark" >
				<el-input autocomplete="off" v-model="house.storeRemark"></el-input>
			</el-form-item>		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close()">取 消</el-button>
			<el-button type="button" v-on:click="insertwarehouse()">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		data() {
			var validatorCheckuid = (role, value, callback) => {
				if (value === '') {
					callback(new Error('部门编号不能为空!'))
				} 
				//else {
				//	this.$axios.get('api/user/checkuid',{params:{uid:value}})
				//		.then(response => {
				//			if (response.statusCord == 200) {

				//			}else{
				//				callback(new Error('该用户名已存在!'))
				///			}
				//		})
			};
			return {
				
				labelPosition: 'right',				       
				insert_warehouse_dialog_visible: false,
				house: {
					sid: '',
					storeName: '',
					storeAddress: '',
					storeRemark:''
				},
				isadmin: 3,
				rules: {
					deptId: [{
						validator: validatorCheckuid,
						trigger: 'blur'
					}], deptName: [{
						required: true,
						message: '部门名称不能为空',
						trigger: 'blur'
					}]
				}
			}
		},
		created() {
			this.insert_warehouse_dialog_visible = true;
		},
		methods: {
			close() {
				this.insert_warehouse_dialog_visible = false;
				this.$router.go(-1)
			},
			searchwarehouse(){
				this.$axios.post('/api/warehouse/getAllHouse')
					.then(response=>{
						
					})
			},
			insertwarehouse(){				
						this.$axios.post('/api/warehouse/addHouse', this.house).then(response => {
								let responseData = response.data;
								if (response.statusCord == 200) {
									this.$message({
										showClose: true,
										message: '新增成功!',
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
							})
							this.searchwarehouse();
			}
		},
	}
</script>

<style>
</style>
