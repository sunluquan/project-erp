


<template>
	<!-- expand-change: 树状表格的状态改变时的回调函数-->
  <el-table
    :data="tableData"
    style="width: 100%" ref="table" @expand-change="expandChange">
    <el-table-column type="expand">
      <template slot-scope="props">
		  <!-- accordion:手风琴
		       show-checkbox:显示复选框
			   node-key:唯一的key
			   default-expanded-keys:默认展开的数据 数组
			   default-expanded-all:所有
			   default-checked-keys:默认选中
			   expand-on-click-node:点击节点是否选中
		   -->
		  <el-tree
		        accordion
		        :data="data"
		        show-checkbox
		        node-key="id"
				ref="tree"
		        :default-expanded-keys="[2, 3]"
		        :default-checked-keys="defaultCheckedKeys"
		        :expand-on-click-node="false"
				@check-change="checkChange">
				<!-- check-change:选中节点发生变化的回调函数 -->
		        <span class="custom-tree-node" slot-scope="{ node, data }">
		          <span>{{ node.label }}</span>
		        </span>
		      </el-tree>
			   <div style="text-align: center;">
				   <el-button type="success" @click="getCheckedKeys" icon="el-icon-check" circle></el-button>
				    <el-button type="danger" @click="recoverCheckedKeys" icon="el-icon-close" circle></el-button>
			   </div>
		   <!-- <el-table
		      :data="array"
			  stripe
			  height="250"
		      style="width: 100%" 
			   @selection-change="selectionChange">
			  <el-table-column
			        type="selection"
			        width="55"
					:show-overflow-tooltip="true">
			  </el-table-column>
		      <el-table-column
		        prop="name"
		        label="商品名称">
		      </el-table-column>
		      <el-table-column
		        prop="shop"
		        label="所属店铺">
		      </el-table-column>
		      <el-table-column
		        prop="id"
		        label="商品id">
		      </el-table-column>
		    </el-table> -->
        <!-- <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="商品名称">
            <span>{{ props.row.name }}</span>
          </el-form-item>
          <el-form-item label="所属店铺">
            <span>{{ props.row.shop }}</span>
          </el-form-item>
          <el-form-item label="商品 ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
          <el-form-item label="店铺 ID">
            <span>{{ props.row.shopId }}</span>
          </el-form-item>
          <el-form-item label="商品分类">
            <span>{{ props.row.category }}</span>
          </el-form-item>
          <el-form-item label="店铺地址">
            <span>{{ props.row.address }}</span>
          </el-form-item>
          <el-form-item label="商品描述">
            <span>{{ props.row.desc }}</span>
          </el-form-item>
        </el-form> -->
      </template>
    </el-table-column>
    <el-table-column
      label="商品 ID"
      prop="id">
    </el-table-column>
    <el-table-column
      label="商品名称"
      prop="name">
    </el-table-column>
    <el-table-column
      label="描述"
      prop="desc">
    </el-table-column>
  </el-table>
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>

<script>
  export default {
    data() {
      return {
		array:[
			{
			  id: '12987122',
			  name: '好滋好味鸡蛋仔',
			  category: '江浙小吃、小吃零食',
			  desc: '荷兰优质淡奶，奶香浓而不腻',
			  address: '上海市普陀区真北路',
			  shop: '王小虎夫妻店',
			  shopId: '10333'
			}
		],
        tableData: [{
          id: '12987122',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987123',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987125',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }, {
          id: '12987126',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }],
		data: [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: '一级 2',
        children: [{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 6,
          label: '二级 2-2'
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }],prevRow:'',isChange:false,
	  defaultCheckedKeys:[2,5,6]
      }
    },methods:{
		selectionChange(value){
			
		},getCheckedKeys(keys){
			console.log("keys:"+this.$refs.tree.getCheckedKeys());
			console.log("defaultCheckedKeys:"+this.defaultCheckedKeys);
			console.log(keys==this.defaultCheckedKeys)
		},recoverCheckedKeys(){
			this.$refs.tree.setCheckedKeys(this.defaultCheckedKeys,false);
		},checkChange(){
			this.isChange=true;
		},expandChange(row,expandedRows){//点击那个每一行
			if(row==this.prevRow){//表示只有一行被点开
				if(this.isChange){
					this.isChange=false;
					this.$confirm('是否保存更改操作?', '提示', {
					          confirmButtonText: '确定',
					          cancelButtonText: '取消',
					          type: 'warning'
					        }).then(() => {
					          this.$message({
					            type: 'success',
					            message: '保存成功!'
					          });
					        }).catch(() => {
							  this.recoverCheckedKeys();
					          this.$message({
					            type: 'info',
					            message: '已取消保存'
					          });    
								
					        });
				}
			}else{
				//表示还有一行没有隐藏  就点击了另外一行
				this.$refs.table.toggleRowExpansion(this.prevRow,false);
				this.prevRow=row;
			}
			
			
		}
	}
  }
</script>