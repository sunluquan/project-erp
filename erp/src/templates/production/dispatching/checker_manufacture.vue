<template>
	<div id="checker_manufacture">
		<confirm-checker-manufacture 
		v-model="dialog_visible" 
		:manufacture="manufacture"
		@update_manufacture="getCheckerManufactures"></confirm-checker-manufacture>
		<!-- <router-view @update="getcheckerProcessMaterial"></router-view> -->
		<!-- 
			 default-sort  默认的排序 是一个对象
			 属性:prop:指定的排序列名 order:排序方式
			 有 ascending or descending
			 -->
		<!-- :default-sort="sort_regulation" -->
		<el-table @row-dblclick="checkerManufacture" :border="true" ref="manufacture_table" :highlight-current-row="true" style="width: 100%"
		 :data="search" :default-sort="{prop: 'manufacture_id', order: 'descending'}">
			<el-table-column label="制造单编号" prop="manufacture_id" sortable>
				<template slot-scope="scope">
					{{scope.row.manufacture_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品编号" prop="product_id" sortable>
				<template slot-scope="scope">
					{{scope.row.product_id}}
				</template>
			</el-table-column>
			<el-table-column label="产品名称" prop="product_name">
				<template slot-scope="scope">
					{{scope.row.product_name}}
				</template>
			</el-table-column>
			<el-table-column label="制定人">
				<template slot-scope="scope">
					{{ scope.row.designer|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column label="登记人">
				<template slot-scope="scope">
					{{ scope.row.designer}}
				</template>
			</el-table-column>
			<el-table-column label="投产数量" prop="amount" sortable>
				<template slot-scope="scope">
					{{scope.row.amount|formNumerical}}
				</template>
			</el-table-column>
			<el-table-column label="设计物料成本" sortable prop="material_cost_price_sum">
				<template slot-scope="scope">
					{{scope.row.material_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="设计工时成本" sortable prop="labour_cost_price_sum">
				<template slot-scope="scope">
					{{ scope.row.labour_cost_price_sum|formNumerical('元')}}
				</template>
			</el-table-column>
			<el-table-column label="说明" prop="cost_price">
				<template slot-scope="scope">
					{{ scope.row.remark|whetherShow1}}
				</template>
			</el-table-column>
			<el-table-column align="right" width="100px">
				<template slot="header" slot-scope="scope">
					<el-input v-model="keyWord" placeholder="关键字搜索" />
				</template>
				<template slot-scope="scope">
					<el-button type="primary" size="small" title="核审制定单" @click="checkerManufacture(scope.row)">核审生产制定单</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	import confirm_checker_manufacture from '@/components/dispatching/confirm_checker_manufacture.vue'
	export default {
		name: 'checker_manufacture',
		data() {
			return {
				manufactures: [],
				keyWord: '',
				manufacture:{},
				dialog_visible:false
			}
		},
		methods: {
			getCheckerManufactures() {
				this.$axios.get('/api/manufacture/checker')
					.then(response => {
						this.manufactures = response.data
					})
			},
			checkerManufacture(data) {
				this.manufacture=data
				this.dialog_visible=true
			}
		},
		created() {
			this.getCheckerManufactures()
		},
		computed: {
			search() {
				let keyWord = this.keyWord
				if (keyWord != '') {
					return this.manufactures.filter(manufacture => {
						return manufacture.manufacture_id.includes(keyWord) ||
							manufacture.product_name.includes(keyWord) ||
							manufacture.product_id.includes(keyWord)
					})
				}
				return this.manufactures;
			}
		},
		filters: {
			whetherShow1(data, message) {
				if (data) {
					return data
				} else {
					if (!message) {
						message = "暂无数据!"
					}
					return message;
				}
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
		},components:{
			"confirm-checker-manufacture":confirm_checker_manufacture
		}
	}
</script>

<style>
</style>
