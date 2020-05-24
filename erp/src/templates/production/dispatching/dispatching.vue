<template>
	<div id="dispatching">
		<insert-dispatching v-model="insert_dispatching_dialog_visible"
		:product="product" @update="renewSearch"></insert-dispatching>
		<!-- <router-view @update="renewSearch"></router-view> -->
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		<el-tabs  type="border-card" v-model="typeName"  :tab-position="tabPosition" >
			<el-tab-pane label="按产品划分" name="product_divide">
				按产品划分
				<template v-if="typeName==='product_divide'">
					<product-divide ref="product_divide" @make_dispatching="makeDispatching"></product-divide>
				</template>
			</el-tab-pane>
			<el-tab-pane label="按生成产品单划分" name="production_divide">按生成产品单划分
				<template v-if="typeName==='production_divide'">
					<production-divide ref="production_divide" @make_dispatching="makeDispatching"></production-divide>
				</template>
<<<<<<< HEAD
=======
		<el-tabs type="border-card"  :tab-position="tabPosition" >
			<el-tab-pane label="按产品划分">
				按产品划分
				<product-divide ref="product_divide" @make_dispatching="makeDispatching"></product-divide>
			</el-tab-pane>
			<el-tab-pane label="按生成产品单划分">按生成产品单划分
			
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			</el-tab-pane>
		</el-tabs>
		
	</div>
</template>
<script>
	import insert_dispatching from '@/components/dispatching/insert_dispatching.vue'
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	//按产品进行派工
	import product_divide from '@/components/dispatching/product_divide.vue'
	//按生产单进行派工
	import production_divide from '@/components/dispatching/production_divide.vue'
<<<<<<< HEAD
=======
	import product_divide from '@/components/dispatching/product_divide.vue'
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
	export default {
		name: 'dispatching',
		data() {
			return {
				tabPosition: 'top',
				insert_dispatching_dialog_visible:false,
<<<<<<< HEAD
<<<<<<< HEAD
				product:null,
				typeName:'product_divide'
=======
				product:null
>>>>>>> warehouse
=======
				product:null,
				typeName:'product_divide'
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				if(this.typeName=="product_divide"){
					this.$refs.product_divide.getMeetDispatchingProducts();
				}else if(this.typeName=="production_divide"){
					this.$refs.production_divide.getMeetDispatchingProductions()
				}
			}
		},components:{
			'product-divide':product_divide,
			'insert-dispatching':insert_dispatching,
			'production-divide':production_divide
<<<<<<< HEAD
=======
				this.$refs.product_divide.getMeetDispatchingProducts();
			}
		},components:{
			'product-divide':product_divide,
			'insert-dispatching':insert_dispatching
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		}
	}
</script>
