<template>
	<el-dialog width="60%" :before-close="close" title="产品工序物料设计" :visible.sync="process_material_dialog_visible" append-to-body>
		<select-process-material 
		:product_id="product_id"
		 v-model="select_process_material_dialog_visible"
		 @put_material="putMaterial" ref="myMaterial"></select-process-material>
		<div style="text-align: right;">
			<el-button type="primary" @click="selectProcessMaterial" size="small">查看物料</el-button>
		</div>
		<br />
		<el-table style="text-align: center;width: 100%" :border="true" :data="materialMap.get(id)"
		 ref="product_table" :highlight-current-row="true">
			<template slot="empty">
				暂无物料数据,
				<el-button type="primary" @click="selectProcessMaterial" title="点击添加" icon="el-icon-plus" circle></el-button>
			</template>
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
			<el-table-column label="设计数量" prop="amount">
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="可用数量" prop="residual_amount">
				<template slot-scope="scope">
					{{scope.row.residual_amount|formNumerical()}}
				</template>
			</el-table-column>
			<el-table-column label="需要数量" prop="amount">
				<template slot-scope="scope">
					<number v-model="scope.row.useAmount" :max="scope.row.residual_amount"  :min="1"></number>
					<!-- <el-input-number size="mini" v-model="scope.row.amount"></el-input-number> -->
				</template>
			</el-table-column>
			<el-table-column label="操作" >
				<template slot-scope="scope">
					<el-button type="danger" @click="deleteMaterial(scope.row.id)" title="删除物料" icon="el-icon-delete" circle></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div slot="footer" class="dialog-footer">
			<el-button type="danger" @click="close">取 消</el-button>
			<el-button type="primary" @click="preserveDesign">保存</el-button>
			
		</div>
	</el-dialog>
</template>
<script>
	import number from '@/components/number.vue'
	import select_process_material from '@/components/process/select_process_material.vue'
	export default{ 
		name:'process_material_design',//工序物料设计
		model:{
			prop:'process_material_dialog_visible',
			event:'update'
		},
		props:{
			product_id:{//根据产品编号查询
				type:String,
				default:()=>('')
			},check_materials:{//如果有已经设置过物料的工序
				type:Array,
				default:()=>([])
			},process_material_dialog_visible:{
				type:Boolean
			},id:{
				type:Number
			}
		},
		data(){
			return{
				materialMap:{},
				select_process_material_dialog_visible:false,//查看物料模态框状态
			}
		},methods:{
			deleteMaterial(materialId){
				let materials=this.materialMap.get(this.id)
				let index = materials.findIndex(item=>{
					return item.id==materialId
				})
				this.$refs.myMaterial.setMaterialDetail(materials[index]);
				materials.splice(index,1)
			},
			putMaterial(data){
				let material=JSON.parse(JSON.stringify(data))
				this.$set(material,'useAmount',1);
				let id=this.id
				let materials=this.materialMap.get(id)
				let index = materials.findIndex(item=>{
					return material.id==item.id
				})
				if(index>=0){
					materials[index]+=material.useAmount
				}else{
					materials.push(material);
				}
			},
			selectProcessMaterial(){
				this.select_process_material_dialog_visible=true
			},
			preserveDesign(){//保存
				let id=this.id;
				let materials=this.materialMap.get(id)
				this.$emit('set_process_material',id,materials);
				 this.$message({
				          showClose: true,
				          message: '保存成功!',
				          type: 'success',
						  duration:1000
				        });
				this.$refs.myMaterial.clear()
				this.$emit('update',false);
			},
			close(){
				this.$refs.myMaterial.restore()
				this.materialMap.set(this.id,[])
				this.$emit('update',false);
				
			},init(){
				this.materialMap=new Map()
				let id=this.id;
				let materials=this.materialMap.get(id)
				if(id&&materials==undefined){
					this.materialMap.set(id,[])
				}
			}
		},created() {
			this.init()
		},watch:{
			id(newVal){
				if(!newVal){
					this.close()
					return;
				}
				if(!this.materialMap.has(newVal)){
					this.materialMap.set(newVal,[])
				}
			},check_materials(newVal){
				
			},process_material_dialog_visible(newVal){
				if(newVal&&this.check_materials.length>0){
					let data=JSON.parse(JSON.stringify(this.check_materials))
					this.materialMap.set(this.id,data)
				}
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
			formNumerical(numerical,suffix) {
				//保留两位小数
				if(suffix){
					return parseFloat(numerical).toFixed(2)+' '+suffix
				}
				return parseFloat(numerical).toFixed(2)
			}
		},components:{
			number,
			'select-process-material':select_process_material
		}
	}
</script>

<style>
</style>
