<template>
	<div id="invtRegstselect">
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
		
		   <el-table :border="true" :highlight-current-row="true" style="width: 100%" :data="registrationPage"  border
		   :default-sort = "{prop: 'sort', order: 'ascending'}" @sort-change="sortChange">
		      <el-table-column prop="gatherId" label="入库单编号" :sortable="custom" width="220px">
				  <template slot-scope="scope">
				  	<el-link @click="selectdetails(scope.row.gatherId)"><span>{{scope.row.gatherId}}</span></el-link>
				  </template>
		      </el-table-column>
		      <el-table-column prop="reasonexact" label="入库理由" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.reasonexact}}
				  </template>
		      </el-table-column>	      
			  <el-table-column prop="gatheredAmountSum" label="入库总件数" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.gatheredAmountSum}}
				  </template>
			  </el-table-column>
			  <el-table-column prop="costPriceSum" label="入库总金额" :sortable="custom">
				  <template slot-scope="scope">
				  	{{scope.row.costPriceSum}}
				  </template>
			  </el-table-column>
			  <el-table-column prop="registerTime" label="登记时间" :sortable="custom" width="200px">
			  				  <template slot-scope="scope">
			  				  	{{scope.row.registerTime}}
			  				  </template>
			  </el-table-column>
			  <el-table-column prop="checkTag" label="审核状态" :sortable="custom">
			  				  <template slot-scope="scope">
			  				  	<el-button type="primary" plain>{{scope.row.checkTag}}</el-button>
			  				  </template>
			  </el-table-column>
			  <el-table-column prop="storeTag" label="库存标记" :sortable="custom">
			  				  <template slot-scope="scope">
			  				  	<el-button type="primary" plain>{{scope.row.storeTag}}</el-button>
			  				  </template>
			  </el-table-column>
		    </el-table>
	</div>

</template>

<script>
	export default {
	 	name: 'invtRegstselect',
		 data(){
			 return {				
			 	custom:'custom',
			 	registrationPage:{},		 	
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
	  	 			this.$axios.post('/api/gather/selectRegistration').then(response=>{
	  	 				this.registrationPage=response.data;
						
	  	 				})
	  	 		},	
				selectdetails(gatherId){
					this.$router.push({path:'/index/inventoryRegistration.html/invtRegstselectSheet/'+gatherId})
				},
				//registration(gatherId){	
				//	alert(gatherId);
				//	this.$router.push({path: '/index/invtRegstreview.html/invtRegstreviewSheet/'+gatherId})
				//},	  	 		
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
