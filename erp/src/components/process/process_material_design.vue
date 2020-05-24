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
		<el-table style="text-align: center;width: 100%" :border="true" :data="materials"
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
					<number
					 @set_material="set_material" 
					 v-model="scope.row.useAmount" 
					 :max="scope.row.residual_amount"  
					 :min="1"
					 :id="scope.row.id"></number>
					<!-- <el-input-number size="mini" v-model="scope.row.amount"></el-input-number> -->
				</template>
			</el-table-column>
			<el-table-column label="操作" >
				<template slot-scope="scope">
					<el-button type="danger" @click="deleteMaterial(scope.row)" title="删除物料" icon="el-icon-delete" circle></el-button>
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
	import number from '@/components/process/number.vue'
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
			},material_tag:{//如果有已经设置过物料的工序
				type:Number
			},process_material_dialog_visible:{
				type:Boolean
			},id:{
				type:Number
			},isUpdate:false,//修改的状态
		},
		data(){
			return{
				materials:[],
				select_process_material_dialog_visible:false,//查看物料模态框状态
			}
		},methods:{
			setIsUpdate(){
				if(!this.isUpdate){
					this.isUpdate=true
				}
			},
			set_material(id,amount){//数量改变的回调函数
				this.setIsUpdate()
				this.$refs.myMaterial.setMaterialAmount(id,amount)
			},
			deleteMaterial(data){
				this.setIsUpdate()
				let index=this.materials.findIndex(item=>item.material_id===data.material_id)
				this.$refs.myMaterial.putMaterial(data);
				this.materials.splice(index,1)
			},
			putMaterial(data){
				this.setIsUpdate()
				let material=JSON.parse(JSON.stringify(data))
				this.$set(material,'useAmount',1)
				material['material_id']=material.id
				let materials=this.materials
				let index = materials.findIndex(item=>{
					return item.material_id==material.material_id
				})
				if(index>=0){
					let newUseAmount=materials[index].useAmount+material.useAmount
					let maxAmount=materials[index].residual_amount
					materials[index].useAmount=maxAmount>newUseAmount?maxAmount-newUseAmount:maxAmount
				}else{
					materials.push(material)
				}
			},
			selectProcessMaterial(){
				this.select_process_material_dialog_visible=true
			},
			preserveDesign(){//保存
				if(this.isUpdate){//如果更改了才进行提交
					let  materials=this.$refs.myMaterial.getMaterials();
					let processDetail={
						id:this.id,
						processMaterials:this.materials,
						material_tag:this.material_tag,
						materials:materials
					}
					this.$axios.post('/api/processDetail/updateMaterial',processDetail)
						.then(response=>{
							if(response.statusCord==200){
								this.$message.success('保存成功!')
								//更新设计状态
								this.$emit('update_material_tag',this.id,response.data)
								this.clear()
								this.$emit('update',false);
							}else{
								this.$message.error(response.message)
							}
						})
				}else{
					this.close()
				}
			},	
			clear(){
				this.$refs.myMaterial.clear();
				this.materials=[]
			},
			close(){
				if(this.isUpdate){
					if(this.isUpdate||this.materials.length>0){
						if(confirm('是否保存?')){
							this.preserveDesign()
							return;
						}else{
							this.clear()
						}
					}
				}
				this.materials=[]
				this.$emit('update',false);
			},getMaterialByParentId(){//根据工序id查询
				if(this.id!=null&&this.material_tag==1){
					let id=this.id
					this.$axios.get(`/api/processMaterialDetail/byParentId/${id}`)
						.then(response=>{
							this.materials=response.data
							this.materials.forEach(item=>item.id=item.material_id)
						})
				}
			}
		},created() {
			
		},watch:{
			process_material_dialog_visible(newVal){
				if(newVal){
					this.getMaterialByParentId()
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
