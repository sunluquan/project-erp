<template>
	<div id="gascheduling">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">				
				<el-form-item label="产品建档日期">
					<el-input  placeholder="产品建档日期"></el-input>
				</el-form-item>
				
				<el-form-item>					
					<el-button native-type="button" v-on:click.prevent="cx()"  type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>						
		</el-row> 
		
		   <el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="schedulingtPage"  border
		   :default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange">
		      <el-table-column prop="gatherId" label="入库单编号" :sortable="custom" >
				  <template slot-scope="scope">
				  	{{scope.row.gatherId}}
				  </template>
		      </el-table-column>
		      <el-table-column prop="reasonexact" label="入库理由" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.reasonexact}}
				  </template>
		      </el-table-column>
		      <el-table-column prop="registerTime" label="登记时间" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.registerTime}}
				  </template>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
		      </el-table-column>`
			  <el-table-column prop="amount" label="总件数" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.amount}}
<<<<<<< HEAD
=======
		      </el-table-column>
			  <el-table-column prop="mustAmount" label="总件数" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.sumnum}}
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				  </template>
			  </el-table-column>
			  <el-table-column prop="moneys" label="总金额" :sortable="custom">
				  <template slot-scope="scope">
<<<<<<< HEAD
<<<<<<< HEAD
				  	{{scope.row.moneys}}
=======
				  	{{scope.row.summoneys}}
>>>>>>> warehouse
=======
				  	{{scope.row.moneys}}
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
				  </template>
			  </el-table-column>
			  <el-table-column label="入库调度" width="120px">
			  	<template slot-scope="scope">
			  		<el-button type="text" @click="scheduling(scope.row.gatherId)">入库调度</el-button>
			  	</template>
			  </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'gascheduling',
		 data(){
			 return {				
			 	custom:'custom',
			 	schedulingtPage:{},		 	
			 	params:{
			 		pageNum:1,					
			 		product_kind_id:5,					
			 		orderingRule:{
			 			prop:'sort',
			 			order:'ascending'
			 		}
			 	}
			 }
	  },
	  methods: {
	  	 		sortChange(sort){
	  	 			this.selectProList();
	  	 			this.params.orderingRule.prop=sort.prop;
	  	 			this.params.orderingRule.order=sort.order;
	  	 			
	  	 		},
	  	 		selectProList(){			
	  	 			this.$axios.post('/api/gather/gatherScheduling').then(response=>{
	  	 				this.schedulingtPage=response.data;
						
	  	 				})
	  	 		},				   
				scheduling(gatherId){
					this.$router.push({path: '/index/gascheduling.html/gaschedulingsheet/'+gatherId})
				},
	  	 		/*
				//cx(){
	  	 		//	if(this.keyWords!=''){
	  	 		//						 let storeName=this.keyWords;
	  	 			//					this.$axios.post('/api/stock/selectByName/'+storeName).then(response=>{
	  	 		//							alert('神经病');
	  	 		//								 this.warehousePage=response.data;										 
	  //
	  	 		//									
	  	 		//								 	this.$message({
	  //
	  	 		//								 	})
	  	 										 																			 
	  	 		//						 })
	  	 		//					 }else{
	  	 		//					   this.searchWarehouse();
	  	 		//					 }
	  	 		//},
				*/				
	  	 	currentPageNumChange(pageNum){//当前页改变时触发
	  	 		this.params.pageNum=pageNum;
	  	 		this.selectProList();
	  	 	},
			},
	  	 	created() {
	  	 		this.selectProList();
	  	 	},
			computed:{
	  	 		pageSizes(){
	  	 			return this.$store.getters.getPageSizes;
	  	 		},
	  	 	filters:{
	  	 		whetherShow(obj,property,message){
	  	 			if(message==undefined||message=='')
	  	 				message="无"
	  	 			if(obj==''||obj==null){
	  	 				return message;
	  	 			}
	  	 			if(obj[property]==''||obj[property]==null)
	  	 				return message;
	  	 			return obj[property];
	  	 		}
	  	 	},
	},
	
	}
</script>

<style>
</style>
