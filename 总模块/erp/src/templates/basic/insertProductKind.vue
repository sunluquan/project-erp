<template>
	<el-dialog :before-close="close" title="新增产品类别" :visible.sync="insert_kind_dialog_visible">
		<el-form :rules="rules" :model="productKind" ref="productKind">
			<el-form-item label="产品类别名称:" prop="kindName">
				<el-input placeholder="产品类别名称名称" v-model="productKind.kindName" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="产品类别父类" prop="parentId">
				<el-cascader clearable v-model="productKind.parentId" :options="parentProductKinds" placeholder="请选择"
				 :props="propsParams">
					<template slot-scope="{ node, data }">
						<span>{{ data.title }}</span>
						<span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
					</template>
				</el-cascader>
			</el-form-item>
			<el-form-item label="产品类别说明:" prop="describe">
				<el-input placeholder="产品类别说明" type="textarea" v-model="productKind.describe"></el-input>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="insertProductKind">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'insertProductKind',
		data() {
			return {
				productKind: {
					kindName: '',
					parentId: '',
					describe: ''
				}, //checkStrictly是否严格的遵守父子节点不互相关联
				//expandTrigger次级菜单的展开方式
				propsParams: {
					checkStrictly: true,
					expandTrigger: 'hover',
					label: 'title',
					value: 'value',
				},
				insert_kind_dialog_visible: false,
				parentProductKinds: [],
				rules: {
					kindName: {
						required: true,
						message: '请输入类别名称',
						trigger: 'blur',
					}
				}
			}
		},
		methods: {
			update(){
				this.$emit('update')
			},
			close() {
				this.$router.go(-1)
				this.insert_kind_dialog_visible = false
			},
			open() {
				this.insert_kind_dialog_visible = true
			},
			getParentProductKinds() {
				this.$axios.get('/api/cascaded/productKind')
					.then(response => {
						this.parentProductKinds = response.data
					})
			},
			insertProductKind() {
				this.$refs.productKind.validate((valid) => {
					if (valid) {
						let data = this.productKind.parentId;
						if(data instanceof Array){
							this.productKind.parentId=data[data.length-1];
						}
						this.$axios.post('/api/productKind/insert', this.productKind)
							.then(response => {
								if (response.statusCord == 200) {
									this.$message({
										showClose: true,
										message: '新增成功!',
										type: 'success',
										duration: 2000
									});
									this.update()
									this.close();
								} else {
									this.$message({
										showClose: true,
										message: response.message,
										type: 'error',
										duration: 2000
									})
								}
							})
					} else {
						
					}
				})
			}
		},
		created() {
			this.open();
			this.getParentProductKinds()
		}
	}
</script>

<style>
</style>
