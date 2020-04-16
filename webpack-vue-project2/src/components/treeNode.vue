<template>
	<div style="background-color:#545c64;">
		<div id="logo"  style="height: 70px;">
			<h3 style="line-height: 70px;color: #66B1FF;text-align: center;margin: 0px;font-size: 25px;">E r p 系统</h3>
		</div>
		<el-menu  @select="callBackIndexPath" router :collapse="isCollapse" default-active="current_router" class="el-menu-vertical-demo" background-color="#545c64"
		 text-color="#fff" active-text-color="#ffd04b" :unique-opened='true'>
			<menuItem v-for="(treeMenu,key,index) in treeMenus" :tree_menu="treeMenu">
			</menuItem>
		</el-menu>
		 <!-- <el-menu
		      default-active="2"
		      class="el-menu-vertical-demo"
		      >
		      <el-submenu >
		        <template slot="title">
		          <i class="el-icon-location"></i>
		          <span>导航一</span>
		        </template>
		        <el-menu-item-group>
		          <template slot="title">分组一</template>
		          <el-menu-item index="1-1">选项1</el-menu-item>
		          <el-menu-item index="1-2">选项2</el-menu-item>
		        </el-menu-item-group>
		        <el-menu-item-group title="分组2">
		          <el-menu-item index="1-3">选项3</el-menu-item>
		        </el-menu-item-group>
		        <el-submenu index="1-4">
		          <template slot="title">选项4</template>
		          <el-menu-item index="1-4-1">选项1</el-menu-item>
		        </el-submenu>
		      </el-submenu>
		      <el-submenu >
		        <template slot="title">
		          <i class="el-icon-location"></i>
		          <span>导航二</span>
		        </template>
				<el-menu-item >导航二-选项1</el-menu-item>
				<el-menu-item >导航二-选项1</el-menu-item>
		      </el-submenu>
		      <el-menu-item index="3" disabled>
		        <i class="el-icon-document"></i>
		        <span slot="title">导航三</span>
		      </el-menu-item>
		      <el-menu-item index="4">
		        <i class="el-icon-setting"></i>
		        <span slot="title">导航四</span>
		      </el-menu-item>
		    </el-menu> -->
	</div>
</template>

<script>
	import menuItem from '@/components/menu.vue'
	export default {
		name: 'treeNode',
		data() {
			return {
				treeMenus: [],
				isCollapse: false,
				//screenWidth: document.body.clientWidth,
				timer:false
			}
		},
		methods: {
			setRouterNames(routerNames){
				this.$store.commit({type:'setRouterNames',routerNames})
			},getTreeMenus() {
				this.$axios.get('api/common/getTreeMenus')
					.then(response => {
						console.log(response.data)
						this.treeMenus = response.data;
					})
			},
			windowChange() {//判断宽度  是否显示或者隐藏
				window.screenWidth = document.body.clientWidth
				let screenWidth = window.screenWidth
				let fals=screenWidth>900?false:true
				if(fals==this.isCollapse)return;
				this.isCollapse=fals
			},callBackIndexPath(index,indexPath){//点击菜单路由跳转的回调函数
				if(index==null){
					this.$message({
						type:'info',
						message:'该菜单还没有对应的路径去跳转!'
					})
					return;
				}
				console.log('index:'+index)
				console.log('indexPath:'+indexPath)
				console.log(this.$router)
				let maxLength=indexPath.length-1;
				if(indexPath instanceof Array){
					//因为最后一个是它本身  它本身不需要考虑进去
					let routerNames=[];
					this.getRouterNames(this.treeMenus,routerNames,indexPath,0,maxLength,index);
					console.log('routerNames:'+routerNames)
					this.setRouterNames(routerNames)
				}
			},getRouterNames(treeMenus,routerNames,indexPath,currentLength,maxLength,index){
				if(currentLength==maxLength){//说明是最后一个
					//说明这是最后一个routerNames最后一个存储的是要跳转的url
					//所以最后一个需要判断url是否相等
					treeMenus.findIndex(treeMenu=>{
						if(index===treeMenu.url||index.indexOf(treeMenu.url)>=0){
							routerNames.push(treeMenu.title)
						}
					})
					// routerNames.push(treeMenus.title)
					return;
				}
				 treeMenus.findIndex((treeMenu)=>{
					if(treeMenu.id==indexPath[currentLength]){
						routerNames.push(treeMenu.title);
						if(treeMenu.children&&treeMenu.children.length>0){
							this.getRouterNames(treeMenu.children,routerNames,indexPath,++currentLength,maxLength,index);
						}
					}
				})
			}
		},
		created() {
			this.getTreeMenus();
		},
		computed: {
			current_router(){
				let path = this.$router.path.split('/');
				console('path:'+path)
				console.log(this.$router.path)
				return '/'+path[1]
			}
		},
		components: {
			menuItem
		},
		watch: {
			// screenWidth(newval, oldval) {
			// 	// 为了避免频繁触发resize函数导致页面卡顿，使用定时器
			// 	if (!this.timer) {
			// 		// 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
			// 		this.screenWidth = newVal
			// 		this.timer = true
			// 		let that = this
			// 		setTimeout(function() {
			// 			// 打印screenWidth变化的值
			// 			console.log(that.screenWidth)
			// 			that.timer = false
			// 		}, 400)
			// 	}
			// }
		},
		mounted() {
			window.onresize = this.windowChange;
		}
	}
</script>

<style>
</style>
