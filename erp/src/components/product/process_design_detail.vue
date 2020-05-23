<template>
	<!-- :summary-method="getSummaries" show-summary  -->
	<el-table  :summary-method="getSummaries" show-summary :border="true"  :highlight-current-row="true" style="width: 100%" :data="processDetails"
	 :default-sort="{prop: 'details_number', order: 'asc'}"
	 @expand-change="setActiveProcess"
	 ref="process_table"
	 >
		<el-table-column type="expand">
		    <template slot-scope="props" v-if="props.row.material_tag==1">
					<process-material-detail
					:id="props.row.id"
					@set_cache_material="set_cache_material"
					@get_cache_material="get_cache_material"
					></process-material-detail>
			</template>
		</el-table-column>
		<el-table-column label="工序执行顺序">
			<template slot-scope="scope">
				{{scope.row.details_number}}
			</template>
		</el-table-column>
		<el-table-column label="工序名称">
			<template slot-scope="scope">
				{{scope.row.process.processName}}
			</template>
		</el-table-column>
		<el-table-column label="工序描述">
			<template slot-scope="scope">
				<span title="scope.row.message">{{scope.row.process|whetherShow('message','暂无描述信息')}}</span>
			</template>
		</el-table-column>
		<el-table-column label="工时数" prop="labour_hour_amount" width="120px">
			<template slot-scope="scope">
				<span>{{scope.row.labour_hour_amount|formNumerical(2,scope.row.time_unit)}}</span>
			</template>
		</el-table-column>
		<el-table-column width="120px" label="工时/单位 成本" prop="cost_price">
			<template slot-scope="scope">
				<span>{{scope.row.cost_price|formNumerical(2,'元')}}</span>
			</template>
		</el-table-column>
		<el-table-column label="小计" prop="total">
			<template slot-scope="scope">
				{{scope.row.cost_price|total(scope.row.labour_hour_amount)}}
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	import process_material_detail from '@/components/process/process_material_detail.vue'
	export default {
		name: 'process_design_detail',
		props: ['id'],
		data() {
			return {
				processDetails: [],
				mapCache: {},
				activeProcess:'',
				cacheMaterial:{}
			}
		},
		methods: {
			set_cache_material(data){
				this.cacheMaterial.set(this.activeProcess.id,data)
			},get_cache_material(id,callback){
				if(this.cacheMaterial.has(id)){
					callback(this.cacheMaterial.get(id))
				}else{
					callback([])
				}
			},
			setActiveProcess(data){//点击下标键
				if(this.activeProcess!=''&&this.activeProcess.id==data.id){
					this.activeProcess='';
				}else{
					if(this.activeProcess!=''){
						this.$refs.process_table.toggleRowExpansion(this.activeProcess,false)
					}
					this.activeProcess=data
				}
			},
			getProcessDesignDetail(id) {
				if (!this.mapCache.has(id)) {
					this.$axios.get(`/api/processDetail/byParentId/${id}`)
						.then(response => {
							this.mapCache.set(id, response.data)
							this.processDetails =this.mapCache.get(id)
						})
				}else{
					this.processDetails =this.mapCache.get(id)
				}
			},getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				let length=columns.length
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
						sums[index] =sums[index].toFixed(2);
					} else {
						if(index===length-1){
							let total=0;
							data.forEach(item=>{
								let hourAmount=Number(item.labour_hour_amount)
								let costPrice=Number(item.cost_price)
								if(!isNaN(hourAmount)&&!isNaN(costPrice)){
									total+=hourAmount*costPrice
								}
							})
							sums[index]='工时成本:'+total.toFixed(2)+'元'
						}else{
							sums[index]="N/A"
						}
					}
				});

				return sums;
			},
		},
		created() {
			this.mapCache = new Map()
			this.cacheMaterial=new Map()
		},
		watch: {
			id(newVal) {
				if(newVal){
					this.getProcessDesignDetail(newVal)
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
			formNumerical(numerical,fixed,suffix) {
				//保留两位小数
				return parseFloat(numerical).toFixed(fixed)+' '+suffix;
			},
			total(value1, value2, prefix) {
				let total = parseFloat(value1) * parseFloat(value2)
				if (!prefix) prefix = '元'
				return total.toFixed(2) + ' ' + prefix
			}
		},components:{
			'process-material-detail':process_material_detail
		}
	}
</script>

<style>
</style>
