<template>
	<el-dialog id="updatedept" :before-close="close" width="40%" title="修改部门" :visible.sync="update_dept_dialog_visible">
		<el-form :model="dept" :rules="rules" ref="updatedept" :label-position="labelPosition" label-width="90px">
			<el-form-item label="部门编号:" prop="deptId" :label-width="formLabelWidth">
				<el-input v-model="dept.deptId" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="部门名称:" prop="deptName" :label-width="formLabelWidth">
				<el-input autocomplete="off" v-model="dept.deptName"></el-input>
			</el-form-item>
			<el-form-item label="部门说明:" :label-width="formLabelWidth">
				<el-input autocomplete="off" v-model="dept.deptRemark"></el-input>
			</el-form-item>			
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="close()">取 消</el-button>
			<el-button type="button" v-on:click="updatedept()">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default{
		name:'updatedept',
		//props:['rid'],
		data(){
			return{
				labelPosition: 'right',	
				deptId:null,
				dept:{},
				update_dept_dialog_visible:false,
				rules:{
					deptName:{

					}
				}
			}
		},
		methods:{
			getdeptById(){
				let deptId=this.deptId;
				this.$axios.get(`api/dept/getDeptById/${deptId}`)
					.then(response=>{
						this.dept=response.data;
					})
			},
			close(){
				this.update_dept_dialog_visible=false;
				this.$router.go(-1);
			},
			updatedept(){
				this.$axios.post('/api/dept/updateDept',this.dept)
					.then(response=>{
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
			this.deptId=this.$route.params[Object.keys(this.$route.params)[0]];
			this.getdeptById();
			this.update_dept_dialog_visible=true;
		}
	}
</script>

<style>
</style>
