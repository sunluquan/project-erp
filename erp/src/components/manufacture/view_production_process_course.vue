<template>
	<!-- :summary-method="getSummaries" show-summary -->
	<el-dialog :before-close="close"  append-to-body :title="production_process.process_name+'工序过程'" :visible.sync="dialog_visible">
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="工序名称: ">
					{{production_process.process_name}}
				</el-form-item>
				<el-form-item label="当前工序状态: ">
					<template v-if="production_process.process_finish_tag==0">
						<el-tag type="info" effect="dark">暂无</el-tag>
					</template>
					<template v-else-if="production_process.process_finish_tag==1">
						<el-tag type="success" effect="dark">完成</el-tag>
						&nbsp;&nbsp;
						<span>交接状态: &nbsp;</span>
						<template v-if="production_process.process_transfer_tag==1">
							<el-tag type="success" effect="dark">交接已完成</el-tag>
						</template>
						<template v-else-if="production_process.process_transfer_tag==0">
							<el-tag type="info" effect="dark">未交接</el-tag>
						</template>
						<template v-else-if="production_process.process_transfer_tag==2">
							<el-tag type="primary" effect="dark">交接审核中</el-tag>
						</template>
					</template>
					<template v-else-if="production_process.process_finish_tag==2||scope.row.process_finish_tag==3">
						<el-tag type="" effect="dark">审核中</el-tag>
					</template>
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="工序设计工时: ">
					{{production_process.labour_hour_amount|formNumerical(2,production_process.time_unit)}}
				</el-form-item>
				<el-form-item label="工序实际工时: ">
					{{production_process.real_labour_hour_amount|formNumerical1('暂无合格数量',2,production_process.time_unit)}}
				</el-form-item>
			</el-form>
		</div>
		<div id="columns">
			<el-form label-position="left" inline class="demo-table-expand">
				<el-form-item label="工序投产数量: ">
					{{production_process.demand_amount|formNumerical(2)}}
				</el-form-item>
				<el-form-item label="工序合格数量: ">
					{{production_process.real_amount|formNumerical1('暂无合格数量',2)}}
				</el-form-item>
			</el-form>
		</div>
		<el-table @row-dblclick="viewMaterialCourse" :summary-method="getSummaries" show-summary
		 :border="true" ref="production_process_course" :highlight-current-row="true" style="width: 100%" :data="productionProcessCourses"
		 :default-sort="{prop: 'register_time', order: 'descending'}"
		 @expand-change="viewMaterialCourse">
			<el-table-column type="expand">
			    <template slot-scope="props">
					<view-production-process-material-course
					:process_course_id="props.row.id"
					@set_cache_material="setCacheMaterial"
					@get_cache_material="getCacheMaterial">
					</view-production-process-material-course>
				</template>
			</el-table-column>
			<el-table-column label="工序名称" prop="process_name" sortable>
				<template slot-scope="scope">
					{{scope.row.process_name}}
				</template>
			</el-table-column>
			<el-table-column label="负责人"  sortable>
				<template slot-scope="scope">
					{{scope.row.responsible_person|whetherShow1('暂无负责人信息')}}
				</template>
			</el-table-column>
			<el-table-column label="使用工时" prop="labour_hour_amount" sortable>
				<template slot-scope="scope">
					{{scope.row.labour_hour_amount|formNumerical(2,scope.row.time_unit)}}
				</template>
			</el-table-column>
			<el-table-column label="单位工时单价" prop="cost_price" sortable>
				<template slot-scope="scope">
					{{scope.row.cost_price|formNumerical(2,'元')}}
				</template>
			</el-table-column>
			<el-table-column label="登记时间" prop="register_time" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{scope.row.register_time}}</span>
				</template>
			</el-table-column>
			<el-table-column label="工时成本小计" prop="subtotal" sortable>
				<template slot-scope="scope">
					{{scope.row.subtotal|formNumerical1('暂无工时成本',2,'元')}}
				</template>
			</el-table-column>
		</el-table>
			<div id="columns">
				<el-form label-position="left" inline class="demo-table-expand">
					<el-form-item label="设计物料成本: ">
						{{production_process.module_subtotal|formNumerical(2,'元')}}
					</el-form-item>
					<el-form-item label="实际物料成本: ">
						{{production_process.real_module_subtotal|formNumerical1('暂无物料价格',2,'元')}}
					</el-form-item>
				</el-form>
			</div>
		<div slot="footer" class="dialog-footer">
			<el-button  @click="close">关 闭</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import view_production_process_material_course from '@/components/manufacture/view_production_process_material_course.vue'
	export default {
		name: 'view_production_process_course',//查看生产工序过程
		model:{
			prop:'dialog_visible',
			event:'update'
		},
		props: {
			dialog_visible:{
				type:Boolean
			},
			production_process: {
				type: Object,
				default: () => ({})
			}
		},
		data() {
			return {
				productionProcessCourses: [],
				activeProductionProcessCourse:null
			}
		},
		methods: {
			getCacheMaterial(id,callback){
				let index=this.productionProcessCourses.findIndex(item=>item.process_id===id);
			
				let productionProcessCourse = this.productionProcessCourses[index]
				
				if(productionProcessCourse['materials']!=undefined){
					callback(productionProcessCourse.materials)
				}else{
					callback([])
				}
			},setCacheMaterial(id,data){
				let index=this.productionProcessCourses.findIndex(item=>item.process_id===id);
				let productionProcessCourse = this.productionProcessCourses[index]
				this.$set(productionProcessCourse,'materials',data)
				console.log(productionProcessCourse)
				this.$emit('set_cache_material',productionProcessCourse.process_id,productionProcessCourse)
			},
			viewMaterialCourse(row){
				//当点击某一行展开或者关闭或者双击某一行
				if(this.activeProductionProcessCourse!=null&&this.activeProductionProcessCourse.id===row.id){
					//说明当前行已经被展开 需要关闭
					this.$refs.production_process_course.toggleRowExpansion(this.activeProductionProcessCourse,false)
					this.activeProductionProcessCourse=null
				}else{
					this.activeProductionProcessCourse=row
				}
			},
			close(){
				this.$emit('update',false)
			},
			getProductionProcessCourseByParentId() {
				//需要根据生产主表的编号和当前选中的
				//工序编号  查询 生产工序过程记录集合
				let process_id = this.production_process.process_id;
				let manufacture_id = this.production_process.parent_id;
				this.$emit('get_cache_material',process_id,data=>{this.productionProcessCourses=data})
				if(this.productionProcessCourses.length==0){
					this.$axios.get('/api/productionProcessCourse/byManufactureIdAndProcessId', {
							params: {
								manufacture_id: manufacture_id,
								process_id: process_id
							}
						})
						.then(response => {
							this.productionProcessCourses = response.data
							this.$emit('set_cache_material',process_id,response.data)
						})
				}
			},getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length = columns.length
				columns.forEach((column, index) => {
					if (index === 0) {
						sums[index] = '总价';
						return;
					}
					const values = data.map(item => Number(item[column.property]));
					if (!values.every(value => isNaN(value))) {
						sums[index] = values.reduce((prev, curr) => {
							const value = Number(curr);
							if (!isNaN(value)) {
								return prev + curr;
							} else {
								return prev;
							}
						}, 0);
						sums[index] = sums[index].toFixed(2);
					} else {
							sums[index] = ""
					}
				});
				return sums;
			},
		},computed:{
			process_id(){
				return this.production_process.process_id;
			},size(){
				return this.productionProcessCourses.length;
			}
		},
		watch: {
			dialog_visible(newVal){
				if(newVal&&this.production_process.length==0){
					this.getProductionProcessCourseByParentId()
				}
			},
			process_id(newVal){
				console.log(this.production_process)
				if(newVal){
					this.getProductionProcessCourseByParentId()
				}
			}
		},components:{
			'view-production-process-material-course':view_production_process_material_course
		},
		filters: {
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
		},created() {
			
		}
	}
</script>

<style scoped="scoped">
	element .style {
		min-height: 50px;
		width: 300px;
	}
	
	#columns {
		font-size: 0;
	}
	
	#columns label {
		width: 90px;
		color: #99a9bf;
	}
	
	#columns .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
</style>
