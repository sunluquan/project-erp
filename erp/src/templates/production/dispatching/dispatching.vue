<template>
	<div id="dispatching">
		<insert-dispatching v-model="insert_dispatching_dialog_visible"
		:product="product" @update="renewSearch"></insert-dispatching>
		<!-- <router-view @update="renewSearch"></router-view> -->
		<el-tabs type="border-card"  :tab-position="tabPosition" >
			<el-tab-pane label="按产品划分">
				按产品划分
				<product-divide ref="product_divide" @make_dispatching="makeDispatching"></product-divide>
			</el-tab-pane>
			<el-tab-pane label="按生成产品单划分">按生成产品单划分
			
			</el-tab-pane>
		</el-tabs>
		
	</div>
</template>
<script>
	import insert_dispatching from '@/components/dispatching/insert_dispatching.vue'
	import product_divide from '@/components/dispatching/product_divide.vue'
	export default {
		name: 'dispatching',
		data() {
			return {
				tabPosition: 'top',
				insert_dispatching_dialog_visible:false,
				product:null
			}
		},
		methods: {
			makeDispatching(product){
				this.insert_dispatching_dialog_visible=true
				this.product=product
			},
			makeDispatching1(id,data){
				let params={product_id:id,productionPlanDetailIds:data}
				params=JSON.stringify(params)
				let fullPath=this.$route.fullPath
				this.$router.push({path:`${fullPath}/insert/${params}`})
			},renewSearch(){
				this.$refs.product_divide.getMeetDispatchingProducts();
			}
		},components:{
			'product-divide':product_divide,
			'insert-dispatching':insert_dispatching
		}
	}
</script>
