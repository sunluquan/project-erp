<template>
	<el-table show-summary style="text-align: center;width: 100%" :border="true"
	:data="productionProcessMaterialCourses" :highlight-current-row="true">
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
				{{scope.row.cost_price|formNumerical(2,'元')}}
			</template>
		</el-table-column>
		<el-table-column label="当前使用数量" prop="use_amount">
			<template slot-scope="scope">
				{{scope.row.use_amount|formNumerical(2)}}
			</template>
		</el-table-column>
		<el-table-column label="使用物料成本小计" prop="subtotal">
			<template slot-scope="scope">
				{{scope.row.subtotal|formNumerical(2,'元')}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	export default{
		name:'view_production_process_material_course',//查看生产工序物料过程
		props:{
			process_course_id:{
				type:Number,
				default:()=>(null)
			}
		},
		data(){
			return{
				productionProcessMaterialCourses:[]
			}
		},methods:{
			//通过生产工序过程id获取生产工序物料过程
			getProductionProcessMaterialCoursesByProcessCourseId(){
				let process_course_id=this.process_course_id
				this.$emit('get_cache_material',process_course_id,data=>{this.productionProcessMaterialCourses=data})
				if(this.productionProcessMaterialCourses.length==0){
					if(process_course_id){
						this.$axios.get(`/api/productionProcessMaterialCourse/getParentId/${process_course_id}`)
							.then(response=>{
								this.productionProcessMaterialCourses=response.data
								this.$emit('get_cache_material',process_course_id,response.data)
							})
					}
				}
			}
		},created() {
			this.getProductionProcessMaterialCoursesByProcessCourseId()
		},filters:{
			formNumerical1(numerical, message, fixed, suffix) {
				if (message == undefined || message == '')
					message = "无"
				if (!fixed) {
					fixed = 2
				}
				if (!suffix) {
					suffix = ''
				}
				if (numerical) {
					return parseFloat(numerical).toFixed(fixed) + suffix
				} else {
					return message
				}
			},
			whetherShow1(data, message) {
				if (message == undefined || message == '')
					message = "无"
				if (data) {
					return data;
				}
				return message
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
			formNumerical(numerical, fixed, suffix) {
				//保留两位小数
				if (suffix) {
					return parseFloat(numerical).toFixed(fixed) + ' ' + suffix;
				}
				return parseFloat(numerical).toFixed(fixed)
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		}
	}
</script>

<style>
</style>
