<template>
	<el-dialog title="添加工序" :before-close="close" :visible.sync="process_dialog_visible"  append-to-body>
		<el-table  :data="uncheckedProcess" :border="true" :highlight-current-row="true"
		:default-sort="{prop: 'id', order: 'ascending'}"
		@cell-dblclick="put_and_close">
			<el-table-column property="id" prop="id" sortable label="工序编号" width="150">
			</el-table-column>
			<el-table-column property="processName" sortable prod="processName" label="工序名称" width="200"></el-table-column>
			<el-table-column  label="描述">
				<template slot-scope="scope">
					<span>{{scope.row|whetherShow('message','暂无描述信息')}}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<!-- circle是否为圆形按钮  false -->
					<el-button type="success" @click="put(scope.row)" title="添加工序" icon="el-icon-plus" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
	</el-dialog>
</template>

<script>
	export default {
		name: 'process_module',
		model:{
			prop:'process_dialog_visible',
			event:'update'
		},
		props:{
			process_dialog_visible:Boolean,
			selected:{
				type:Array,
				default:()=>([])
			}
		},
		data() {
			return {
				process:[],
			}
		},methods:{
			put(data){
				 this.$emit('put_process_detail',data)
				 if(this.selected.length>0&&this.uncheckedProcess.length==0){
					 this.close()
				 }
				// if(!fals){
				// 	this.$message.error('当前工序已制定  不能选择同样的工序');
				// }
				//return fals;
			},
			put_and_close(data){
				this.put(data);
				this.close()
			},getProcess(){
				if(this.process.length>0)
					return;
				this.$axios.get('/api/process/selectUsable')
					.then(response=>{
						this.process=response.data
					})
			},close(){
				this.$emit('update',false)
			}
		},
		created() {
		},watch:{
			process_dialog_visible(newVal){
				if(newVal){
					this.getProcess()
					
				}
			}
		},computed:{
			uncheckedProcess(){
				if(this.selected.length>0){
					return this.process.filter(item=>{
						let index = this.selected.findIndex(id=> id==item.id)
						return index<0
					})
				}
				return this.process;
			}
		},filters:{
			whetherShow(obj, property, message) {
				if (message == undefined || message == '')
					message = "无"
				if (obj == '' || obj == null) {
					return message;
				}
				if (obj[property] == '' || obj[property] == null)
					return message;
				return obj[property];
			},
			formNumerical(numerical) {
				//保留两位小数
				return parseInt(numerical).toFixed(2);
			}
		}
	}
</script>

<style scoped="scoped">
</style>
