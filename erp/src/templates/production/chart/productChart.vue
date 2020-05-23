<template>
	<el-row>
		<el-col :span="12">
			<div class="grid-content bg-purple">
				{{productChartData}}
			</div>
		</el-col>
		<el-col :span="12">
			<div class="grid-content bg-purple-light">
				<ve-histogram :data="productChartData" :settings="chartSettings"></ve-histogram>
			</div>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		data() {
			this.chartSettings = {
				stack:{},
				labelMap:{
					'product_id':'产品编号',
					'product_name':'产品名称',
					'price_sum':'总金额',
					'real_material_price_sum':'实际总物料',
					'real_labour_price_sum':'实际总工时',
					'tested_amount_sum':'实际生产数量',
					'material_price_sum':'设计总物料',
					'labour_price_sum':'设计总工时',
					'amount_sum':'设计生产数量',
				}
				// axisSite: {
				// 	right: ['下单率']
				// },
				// yAxisType: ['KMB', 'percent'],
				// yAxisName: ['数值', '比率']
			}
			return {
				productChartData: {
					columns: [
						'year_month', 'product_id', 'product_name',
						'price_sum', 'real_material_price_sum', 'real_labour_price_sum',
						'tested_amount_sum', 'amount_sum', 'material_price_sum',
						'labour_price_sum'
					],
					rows: []
				}
			}
		},
		methods: {
			getProductProductionFinishChartResult() {
				//获取产品生产完成的产品  按照每个月进行
				this.$axios.get('/api/manufacture/getProductProductionFinishChartResult')
					.then(response => {
						//this.productChartData.row = response.data
						response.data.forEach(item=>{
							if(item){
								this.productChartData.rows.push(item)
							}
						})
					})
			}
		},
		created() {
			this.getProductProductionFinishChartResult()
		},
		components: {}
	}
</script>

<style scoped="scoped">

</style>
