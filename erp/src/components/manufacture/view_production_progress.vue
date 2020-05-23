<template>
	<el-drawer :title="'生产计划单编号:'+manufacture.manufacture_id" :visible.sync="view_production_progress_direction"
	 :before-close="close">
		<div style="padding: 10px;">
			<template v-if="timeLines&&timeLines.length>0">
				<div class="radio">
					排序：
					<el-radio-group v-model="reverse">
						<el-radio :label="true">倒序</el-radio>
						<el-radio :label="false">正序</el-radio>
					</el-radio-group>
				</div>
				<div class="block">
					<el-timeline :reverse="reverse">
						<el-timeline-item v-for="(timeLine,key,index) in timeLines" :key="index" :timestamp="timeLine.registerTime"
						 placement="top">
							<el-card>
								<h4>更新 工序 {{timeLine.registerContent}}</h4>
								<p>{{timeLine.register}} 提交于 {{timeLine.registerTime}}</p>
								<p>
									<template v-if="timeLine.checker">
										{{timeLine.checker}} 审核于 {{checkTime}}
									</template>
								</p>
								<p>备注:{{timeLine.describe|whetherShow('暂无备注')}}</p>
							</el-card>
						</el-timeline-item>
					</el-timeline>
				</div>
			</template>
			<template v-else>
				<h1>暂无生产信息</h1>
			</template>
		</div>
	</el-drawer>
</template>

<script>
	export default {
		name: 'view_production_progress',
		//查看生产工序过程
		props: {
			manufacture: {
				type: Number,
				default: () => (null)
			},
		},
		data() {
			return {
				view_production_progress_direction: false,
				timeLines: [],
				reverse: true
			}
		},
		methods: {
			open() {
				this.view_production_progress_direction = true;
			},
			close() {
				this.view_production_progress_direction = false
			},
			getProductionProgressByManufactureId() {
				let manufacture_id = this.manufacture.id
				this.$axios.get(`/api/manufacture/getProductionProgressByManufactureId/${manufacture_id}`)
					.then(response => {
						this.timeLines = response.data
					})
			},
		},
		created() {},
		computed: {
			manufacture_id() {
				return this.manufacture.id
			}
		},
		watch: {
			manufacture_id(newVal) {
				if (newVal) {
					this.getProductionProgressByManufactureId()
				}
			}
		},
		filters: {
			whetherShow(data, message) {
				if (data) {
					return data
				} else {
					return message
				}
			}
		}
	}
</script>

<style>
</style>
