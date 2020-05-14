<template>
	
</template>

<script>
	export default{
		name:'production_divide',//按生产产品计划编号划分
		data(){
			return{
				productions:[]
			}
		},methods:{
			getMeetDispatchingProductions(){//获取满足可以派工生产的生产单
				this.$axios.get('/api/productionPlan/meetDespatching')
					.then(response=>{
						this.productions=response.data
					})
			}
		}
	}
</script>

<style>
</style>
