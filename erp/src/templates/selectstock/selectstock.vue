<template>
	<div id="selectstock">
		<router-view></router-view>
		<el-row type="flex" class="row-bg" justify="space-around">
			<el-form :inline="true" class="demo-form-inline">
				
				<el-form-item label="产品名称:">					
					<el-input v-model="product_name" placeholder="产品名称"></el-input>					
				</el-form-item>
				<el-form-item label="登记日期:">					
					<el-date-picker v-model="registertime"  type="date"  placeholder="产品配置登记日期">
					</el-date-picker>					
				</el-form-item>							
				<el-form-item>					
					<el-button native-type="button" @click="search()"   type="primary" icon="el-icon-search">搜索</el-button>
				</el-form-item>
			</el-form>						
		</el-row> 
		
		   <el-table :data="selectPage" border style="width: 100%" :border="true" :highlight-current-row="true">
			   
			  <el-table-column prop="safetystockId" width="270px" label="配置单编号" :sortable="custom">
				  <template slot-scope="scope">
					  <el-link @click="selectdetails(scope.row.safetystockId)"><span>{{ scope.row.safetystockId }}</span></el-link> 	
				  </template>
			  </el-table-column>
		      <el-table-column prop="productid" width="210px" label="产品编号" :sortable="custom">
				  <template slot-scope="scope">
				  	<span>{{ scope.row.product_id }}</span>
					
				  </template>
		      </el-table-column>
		      <el-table-column prop="productname" label="产品名称" :sortable="custom" >
				  <template slot-scope="scope">
				  	<span>
				  		{{ scope.row.product_name }}</span>
				  </template>
		      </el-table-column>
		     <el-table-column prop="registertime" label="登记时间" :sortable="custom" width="220px">
		     		<template slot-scope="scope">
		     		     <span>{{ scope.row.registertime }}</span>
		     		</template>
		     </el-table-column>
			  <el-table-column prop="configStatus"   label="配置单状态" :sortable="custom">
				  <template slot-scope="scope">
				  	<span style="color: #409EFF;">
				  		{{ scope.row.configStatus }}</span>
				  </template>
			  </el-table-column>
			  <el-table-column prop="checktag"  label="复核状态" :sortable="custom">
				  <template slot-scope="scope">
				  	<span style="color: #409EFF;">
				  		{{ scope.row.checktag }}</span>
				  </template>
			  </el-table-column>			  
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'selectstock',
		 data(){
			 return {				 
			 	custom:'custom',
				configStatus:'',
				checktag:'',
				product_name:'',
				registertime:'',
			 	selectPage:{},		 	
			 	params:{
			 		pageNum:1,
					keyWord:'',
			 		product_type_id:5,					
			 		orderingRule:{
			 			prop:'sort',
			 			order:'ascending'
			 		}
			 	}
			 }
	  },created() {
	  	 		this.searchSelect();										
	  	 	},
	  methods: {		 
	  	 		sortChange(sort){
	  	 			this.searchreview();
	  	 			this.params.orderingRule.prop=sort.prop;
	  	 			this.params.orderingRule.order=sort.order;
	  	 			
	  	 		},
				  searchSelect(){
						this.$axios.get('api/safetystock/selectSafetyStocks').then(response => {
								this.selectPage = response.data;
					    }).catch(err=>{
			     			alert('请求失败')
			          })
					},
					selectdetails(safetystockId){
				           this.$router.push({path:'/index/selectstock.html/selectstockdetails/'+safetystockId})
					},
					search(){
						if(this.productName!=''){
							let productName=this.productName;							
							this.$axios.get('api/safetystock/selectByName/'+productName).then(response => {
									this.selectPage = response.data;
							}).catch(err=>{
									 alert('请求失败')
							 })
							 
						}else{
							this.searchSelect();
						}
						
						
						if(this.registertime!=''){							
							let registertime=this.registertime;
							this.$axios.get('api/safetystock/selectByTime/'+registertime).then(response => {
									this.selectPage = response.data;
							}).catch(err=>{
									 alert('请求失败')
							 })
							
						}else{
							this.searchSelect();							
						}	
						 
						},
										
	},
	
	filters:{	  	 		
    },
	
	}
</script>

<style>
</style>
