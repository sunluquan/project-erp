<template>
	<el-dialog id="insertdept" :before-close="close" width="40%" title="新增部门" :visible.sync="insert_dept_dialog_visible">
		<el-form :model="dept" :rules="rules" ref="insertdept" :label-position="labelPosition" label-width="90px">
			<el-form-item label="部门编号:" prop="deptId" >
				<el-input v-model="dept.deptId" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="部门名称:" prop="deptName" >
				<el-input autocomplete="off" v-model="dept.deptName"></el-input>
			</el-form-item>
			<el-form-item label="部门说明:" >
				<el-input autocomplete="off" v-model="dept.deptRemark"></el-input>
			</el-form-item>			
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close()">取 消</el-button>
			<el-button type="button" v-on:click="insertdept()">确 定</el-button>
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
				insert_dept_dialog_visible: false,
				dept: {
					deptId: '',
					deptName: '',
					deptRemark: ''					
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
			this.insert_dept_dialog_visible = true;
		},
		methods: {
			close() {
				this.insert_dept_dialog_visible = false;
				this.$router.go(-1)
			},
			searchdept(){
				this.$axios.post('/api/dept/getAllDept').then(response=>{
					})
			},
			insertdept(){				
						this.$axios.post('/api/dept/addDept', this.dept).then(response => {
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
							this.searchdept();
			}
		},
	}
</script>

<style>
</style>
