<template>
	<el-dialog :before-close="close" title="工序物料查看" append-to-body :visible.sync="view_process_material_dialog_visible">
		<el-table  @row-dblclick="insert_material_and_close"
		style="text-align: center;width: 100%" :border="true" :data="processMaterials" :highlight-current-row="true">
			<template slot="empty">
				该工序暂无物料数据
			</template>
			<el-table-column label="物料编号">
				<template slot-scope="scope" prop="product_id">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="物料名称" prop="product_name">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="单价" prop="cost_price">
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计数量" prop="amount">
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="操作" >
				<template slot-scope="scope">
					<el-button size="small" type="primary" @click="insert_material(scope.row)" title="点击添加" icon="el-icon-plus" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">关 闭</el-button>
		</div>
	</el-dialog>
</template>

<script>
	export default{
		name:'view_process_material1',
		props:['production_process_id'],
		data(){
			return{
				view_process_material_dialog_visible:false,
				processMaterials:[],
				old_production_process_id:''
			}
		},methods:{
			open(){
				this.view_process_material_dialog_visible=true;
				this.getProductionProcessMaterialsByParentId()
			},close(){
				this.view_process_material_dialog_visible=false
			},getProductionProcessMaterialsByParentId(){
				//根据生产工序id获取生产工序物料集合
				let production_process_id=this.production_process_id;
				if(production_process_id){
					if(production_process_id==this.old_production_process_id&&this.processMaterials.length>0)
						return;
					this.$axios.get(`/api/productionProcessMaterial/byParentId/${production_process_id}`)
						.then(response=>{
							this.processMaterials=response.data
							this.old_production_process_id=production_process_id
						})
				}
			},insert_material(data){
				this.$emit('set_process_materials',data);
			},insert_material_and_close(data){
				this.insert_material(data);
				this.close()
			}
		},created() {
		},watch:{
			production_process_id(newVal){
				this.getProductionProcessMaterialsByParentId()
			}
		},filters:{
			whetherShow1(data, suffix) {
				if (suffix) {
					return parseFloat(data).toFixed(2) + suffix;
				}
				return parseFloat(data).toFixed(2)
			},
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
			formNumerical1(numerical, suffix) {
				if (!suffix) {
					suffix = ''
				}
				if (numerical) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return 0.00
			},
			formNumerical(numerical, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(2) + ' ' + suffix
				}
				return parseFloat(numerical).toFixed(2)
			},
			total(number1, number2, suffix) {
				let totalNumber = parseFloat(number1) * parseFloat(number2)
				return totalNumber.toFixed(2) + ' ' + suffix
			}
		}
	}
</script>

<style>
</style>
