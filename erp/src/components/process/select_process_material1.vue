<template>
	<el-dialog  width="60%" :before-close="close" title="产品工序物料查看" :visible.sync="dialog_visible" append-to-body>
		<el-table @row-dblclick="putAndClose" style="text-align: center;width: 100%" :border="true" :data="materials"
		 ref="product_table" :highlight-current-row="true">
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
			<el-table-column label="操作" >
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
	export default{
		name:'select_process_material',//选择工序的物料
		model:{
			prop:'dialog_visible',
			event:'update'
		},
		props:{
			dialog_visible:{
				type:Boolean
			},product_id:{
				type:String,
				default:()=>('')
			}
		},
		data(){
			return{
				materials:[],
			}
		},methods:{
			clear(){
				this.materials=[]
			},
			putAndClose(data){
				this.put(data)
				this.close()
			},
			put(data){
				// this.$emit('put_material',data)
				// if(data.residual_amount==1){
				// 	let index= this.materials.findIndex(item=>{
				// 		return data.id==item.id
				// 	})
				// 	this.materials.splice(index,1)
				// }else{
				// 	data.residual_amount-=1
				// }
			},close(){
				this.$emit('update',false)
			},getMaterialDetail(){
				let product_id=this.product_id;
				if(product_id&&this.materials.length==0){
					//这里注意一下  因为这个工序单
					//和工序详细单下面没有与物料详细表有对应的相关联的东西
					//所以只有通过产品编号去查询  物料总表 再查 物料详细表
					this.$axios.get(`/api/materialDesignDetail/byProductId/${product_id}`)
						.then(response=>{
							this.materials=response.data
							//this.materials=new Map();
							// this.materials.forEach(item=>{
							// 	//这里需要注意必须这么添加  不然  对象
							// 	//后添加的属性  是不具有相应式的
							// 	this.$set(item,'useAmount',0)
							// })
						})
				}
			}
		},watch:{
			dialog_visible(newVal){
				if(newVal){
					this.getMaterialDetail()
				}
			}
		},created() {
			
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
		}
	}
</script>

<style>
</style>
