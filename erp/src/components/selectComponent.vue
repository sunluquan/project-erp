<template>
	<div @mouseover="showSelected=true" @mouseleave="showSelected=false">
		<span v-show="!showSelected">{{unitName}}</span>
		<!-- <select v-show="showSelected" @change="unitNameChange($event)" v-model="unitName">
			<option v-for="(unit,index) in units" :key="index" :value="unit">{{unit}}</option>
		</select> -->
		<el-select v-show="showSelected" @change="unitNameChange" v-model="unitName" placeholder="请选择">
			<el-option v-for="(unit,index) in units" :key="index"
			 :label="unit" :value="unit">
			</el-option>
		</el-select>
	</div> 
</template>

<script>
	export default {
		name: 'select-component',
		model: {
			prop: 'value',
			event: 'update'
		},
		props: {
			value: {
				type: String,
			},unit_key:{
				type: String,
				default:()=>('')
			},defaultselect:{
				type:Boolean,
				default:()=>(true)
			},default_units:{
				type:Array,
				default:()=>([])
			}
		},data(){
			return{
				units:[],
				unitName:'',
				showSelected:false
			}
		},methods: {
			getUnits(){
				if(this.default_units.length>0){
					this.units=this.default_units
				}else if(this.unit_key!=''){
					let key=this.unit_key;
					this.$axios.get(`/api/unit/${key}`)
						.then(response=>{
							this.units=response.data.units
						})
				}
				if(this.value){
					this.unitName=this.value
				}else if(this.defaultselect){
					this.unitName=this.units[0]
					this.unitNameChange(this.unitName)
				}
			},unitNameChange(currentUnitName){
				this.$emit('update',currentUnitName)
			}
		},created() {
			this.getUnits()
		}
	}
</script>

<style>
</style>
