<template>
	<el-date-picker type="datetimerange"
	 :picker-options="pickerOptions" 
	 range-separator="至" start-placeholder="开始日期"
	 end-placeholder="结束日期" align="right"
	 :default-time="defaultTime"
	 value-format="yyyy-MM-dd HH:mm:ss"
	 v-model="dateTime"
	  @change="changeDateTime">
	</el-date-picker>
</template>

<script>
	export default {
		name: 'dateTime',
		model:{
			prop:'value',
			event:'update'
		},props:{
			value:{
				type:Array,
				default:()=>([])
			}
		},
		data() {
			return {
				dateTime:[],
				defaultTime:['00:00:00','23:59:59'],
				pickerOptions: {
					shortcuts: [{
						text: '最近一周',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近一个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近三个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
							picker.$emit('pick', [start, end]);
						}
					}]
				},
			}
		},
		methods: {
			getDate(){
				if(this.value.length>0){
					this.dateTime=JSON.parse(JSON.stringify(this.value))
				}else{
					let date=new Date();
					var year=date.getFullYear();
					var month=date.getMonth()+1;
					var day=date.getDate();
					date =`${year}-${month}-${day}`;
					let startDate=`${date} 00:00:00`;
					let endDate=`${date} 23:59:59`;
					this.dateTime=[startDate,endDate]
					this.update()
				}
			},update(){
				this.$emit('update',this.dateTime)
			},changeDateTime(){
				this.update()
			}
		},created() {
			this.getDate()
		}
	}
</script>

<style>
</style>
