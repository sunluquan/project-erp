<template>
	<!-- mouseover鼠标移入事件  mouseleave鼠标移出事件 -->
	<div @mouseover="mouseover" @mouseleave="showUpdate=false">
		<span v-show="!showUpdate">{{value|formNumerical(precision)}}</span>
		<el-input-number @focus="focus($event)" v-focus :precision="precision" v-show="showUpdate" size="mini"
		 v-model="numerical"
		 :min="min"
		 :max="max"
		 controls-position="right"
		 :step-strictly="step_strictly"
		 :step="step"
		 @change="update"></el-input-number>
	</div>
</template>

<script>
	export default{
		name:'production_register_number',//生产工序物料登记的时候需要的独有的number组件
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
			},step_strictly:{
				type:Boolean,
				default:()=>(false)
			},step:{
				type:Number,
				default:()=>(1)
			},production_process_material:{
				type:Object,
				default:()=>({})
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
			update(newVal){
				let data=this.production_process_material
				let remainder = newVal%data.single_material_design_amount;
				if(remainder>0){
					this.numerical=(newVal+data.single_material_design_amount)-remainder;
				}
				this.$emit('update',this.numerical);
				this.$emit('change',this.production_process_material)
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
