<template>
	<!-- mouseover鼠标移入事件  mouseleave鼠标移出事件 -->
	<div @mouseover="mouseover" @mouseleave="showUpdate=false">
		<span v-show="!showUpdate">{{value|formNumerical(precision)}}</span>
		<el-input-number @focus="focus($event)" v-focus :precision="precision" v-show="showUpdate" size="mini"
		 v-model="numerical"
		 :min="min"
		 :max="max"
		 controls-position="right"
<<<<<<< HEAD
<<<<<<< HEAD
		 :step-strictly="step_strictly"
		 :step="step"
=======
>>>>>>> warehouse
=======
		 :step-strictly="step_strictly"
		 :step="step"
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		 @change="update"></el-input-number>
	</div>
</template>

<script>
	export default{
		name:'number',
		model:{
			prop:'value',
			event:'update'
		},
		props:{
			value:{
				type:Number
			},precision:{
				type:Number,
				default:()=>(0)
			},min:{
				type:Number,
				default:()=>(1)
			},max:{
				type:Number,
				default:()=>(Infinity)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			},step_strictly:{
				type:Boolean,
				default:()=>(false)
			},step:{
				type:Number,
				default:()=>(1)
<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
			}
		},
		data(){
			return{
				numerical:1,
				showUpdate:false
			}
		},methods:{
			focus(event){
				event.currentTarget.select()
			},
			update(){
				this.$emit('update',this.numerical);
			},mouseover(){//鼠标移入
				this.showUpdate=true
				this.numerical=this.value
			}
		},created() {
			
		},directives:{
			focus:{
				bind(){
				},inserted(el,binding){
					el.focus()
				},update(){
					
				}
			}
		},filters:{
			formNumerical(numerical,fixed) {
				//保留两位小数
				return parseFloat(numerical).toFixed(fixed);
			},
		},watch:{
		}
	}
</script>

<style scoped="scoped">
	.el-input-number{
		width: 100px;
	}
</style>
