<template>
	<el-dialog width="60%" :before-close="close" title="产品工序物料查看" :visible.sync="dialog_visible" append-to-body>
		<el-table :default-sort="order" @row-dblclick="putAndClose" style="text-align: center;width: 100%" :border="true"
		 :data="materials" ref="material" :highlight-current-row="true">
			<el-table-column label="物料编号">
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="物料名称">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="用途类型">
				<template slot-scope="scope">
					{{scope.row.useName}}
				</template>
			</el-table-column>
			<el-table-column label="单位">
				<template slot-scope="scope">
					{{scope.row.product_unit}}
				</template>
			</el-table-column>
			<el-table-column label="单价" prop="cost_price">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计数量" prop="amount" sortable>
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="可用数量" prop="residual_amount" sortable>
				<template slot-scope="scope">
					{{scope.row.residual_amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button type="success" @click="put(scope.row)" title="添加物料" icon="el-icon-plus" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">关闭</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default {
		name: 'select_process_material', //选择工序的物料
		model: {
			prop: 'dialog_visible',
			event: 'update'
		},
		props: {
			dialog_visible: {
				type: Boolean
			},
			product_id: {
				type: String,
				default: () => ('')
			}
		},
		data() {
			return {
				materials: [],
				order: {
					prop: 'residual_amount',
					order: 'descending'
				}
			}
		},
		methods: {
			getMaterials() {
				return this.materials;
			},
			putMaterial(data) { //那边删除  这边接收
				if (this.materials.length == 0) {
					let product_id=this.product_id
					this.$axios.get(`/api/materialDesignDetail/byProductId/${product_id}`)
						.then(response => {
							this.materials = response.data
							let index = this.materials.findIndex(item => item.id === data.id)
							if (index >= 0) {
								this.materials[index].residual_amount += data.useAmount
							} else {
								let data = JSON.parse(JSON.stringify(data))
								this.materials.push(data)
							}
						})
				} else {
					let index = this.materials.findIndex(item => item.id === data.id)
					if (index >= 0) {
						this.materials[index].residual_amount += data.useAmount
					} else {
						let data = JSON.parse(JSON.stringify(data))
						this.materials.push(data)
					}
				}


			},
			anewOrder() {
				if (this.$refs.material != undefined)
					this.$refs.material.sort(this.order.prop, this.order.order)
			},
			clear() {
				this.materials = []
			},
			setMaterialAmount(id, amount) {
				let index = this.materials.findIndex(item => {
					return item.id == id
				})
				let oldAmount = this.materials[index].residual_amount
				this.materials[index].residual_amount = oldAmount > amount ? oldAmount - ammount : 0

			},
			putAndClose(data) {
				this.put(data)
				this.close()
			},
			put(data) {
				return new Promise((resolve, reject) => {
					if (data.residual_amount == 0) {
						this.$message.error('已经没有可用的数量了!')
						reject();
						// let index= this.materials.findIndex(item=>{
						// 	return data.id==item.id
						// })
						// this.materials.splice(index,1)
					} else {
						this.$emit('put_material', data)
						data.residual_amount -= 1
						if (data.residual_amount == 0)
							this.anewOrder()
					}
					resolve()
				})
			},
			close() {
				this.$emit('update', false)
			},
			getMaterialDetail() {
				let product_id = this.product_id;
				if (product_id && this.materials.length == 0) {
					//这里注意一下  因为这个工序单
					//和工序详细单下面没有与物料详细表有对应的相关联的东西
					//所以只有通过产品编号去查询  物料总表 再查 物料详细表
					this.$axios.get(`/api/materialDesignDetail/byProductId/${product_id}`)
						.then(response => {
							this.materials = response.data
							//this.materials=new Map();
							// this.materials.forEach(item=>{
							// 	//这里需要注意必须这么添加  不然  对象
							// 	//后添加的属性  是不具有相应式的
							// 	this.$set(item,'useAmount',0)
							// })
						})
				}
			}
		},
		watch: {
			dialog_visible(newVal) {
				if (newVal) {
					this.getMaterialDetail()
				}
			}
		},
		created() {
			this.getMaterialDetail()
		},
		filters: {
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
			formNumerical(numerical, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return parseFloat(numerical).toFixed(2)
			}
		},
		mounted() {

		}
	}
</script>

<style>
</style>
