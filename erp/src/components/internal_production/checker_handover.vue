<template>
	<el-dialog title="工序交接" :before-close="close" :visible.sync="dialog_visible" width="30%" center append-to-body>
		<el-form :model="finish_process" :rules="rules" ref="finish_process">
			<el-form-item label="当前工序设计数量:">
				<span>{{production_process.demand_amount}}</span>
			</el-form-item>
			<el-form-item label="当前工序合格数量" prop="real_amount">
				<el-input v-model.number="finish_process.real_amount"></el-input>
			</el-form-item>
		</el-form>
		<span slot="footer" class="dialog-footer">
			<el-button @click="close">取消</el-button>
			<el-button type="primary" @click="submitCheckerHandover">工序完成交接</el-button>
		</span>
	</el-dialog>
</template>

<script>
	export default {
		name: 'checker_handover',//审核交接状态
		model: {
			prop: 'dialog_visible',
			event: 'update'
		},
		props: {
			dialog_visible: {
				type: Boolean
			},
			production_process: {
				type: Object,
				default: () => ({})
			}
		},
		data() {
			var checkAmount = (rule, value, callback) => {
				if (value==='') {
					return callback(new Error('合格数量不能为空'));
				}
				setTimeout(() => {
					if (!Number.isInteger(value)) {
						callback(new Error('请输入数字值'));
					} else {
						if (value < 0) {
							callback(new Error('合格数量不能小于零'));
						} else {
							callback();
						}
					}
				}, 1000);
			};
			return {
				finish_process: {
					real_amount: 0, //合格数量
				},
				rules: {
					real_amount: [{
						validator: checkAmount,
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			close() {
				this.$emit('update', false)
			},
			submitCheckerHandover(){
				let production_process=JSON.parse(JSON.stringify(this.production_process));
				production_process.real_amount=this.production_process.real_amount
				this.$axios.post('/api/productionProcess/checker_handover',production_process)
					.then(response=>{
						if(response.statusCord == 200){
							this.$message.success('审核交接成功!');
							if(response.data){
								//说明该工序为没有别的审核登记状态
								//和交接审核状态了
							}else{
								//说明该工序该有别的审核登记状态
								//和交接审核状态
							}
							this.refresh(response.data)
							this.close()
						}else{
							this.$message.error(response.message)
						}
					})
			},refresh(allUpdate){//更新
				this.$emit('refresh',allUpdate)
			}
		},
		created() {

		},
		watch: {
			dialog_visible(newVal) {
				if (newVal) {
					this.finish_process.real_amount = this.production_process.real_amount;
				}
			}
		}
	}
</script>

<style>
</style>
