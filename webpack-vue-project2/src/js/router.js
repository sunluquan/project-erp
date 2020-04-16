import VueRouter from 'vue-router'

import store from '@/js/vuex/store.js'

import index from '@/templates/system/index.vue'

import login from '@/templates/system/login.vue'

import notPage from '@/templates/system/404.vue'

import unauth from '@/templates/system/unauth.vue'


//import personalCenter from '@/src/templates/system/personalcenter.vue'
import user from '@/templates/user/user.vue'
import insertUser from '@/templates/user/insert_user.vue'
import permission from '@/templates/permission/permission.vue'
import insertPermission from '@/templates/permission/insertPermission.vue'
import updatePermission from '@/templates/permission/updatePermission.vue'
import role from '@/templates/role/role.vue'
import insertRole from '@/templates/role/insertRole.vue'
import updateRole from '@/templates/role/updateRole.vue'

const router=new VueRouter({
	//mode:'history',
	routes:[
		{path:'/',redirect:'/index'},
		{path:'/index',component:index,
			children:[
				{path:'updatePwd',component:()=>import('@/components/updatePwd.vue')},
				{path:'personalcenter.html',component: ()=>import('@/templates/system/personalcenter.vue'),
					children:[
						{path:'updatePwd',component:()=>import('@/components/updatePwd.vue')}
					]
				 },
				{path:'user.html',component:user,
					children:[
						{path:'insert',component:insertUser}
					]
				},
				{path:'permission.html',component:permission,
					children:[
						{path:'insert',component:insertPermission},
						//props:(route)=>({pId:route.query.pId})
						{path:'update/:pId',component:updatePermission,props:true}
					]
				},
				{path:'role.html',component:role,
					children:[
						{path:'insert',component:insertRole},
						{path:'update/:rid',component:updateRole,props:true}
					]
				}
				
			]
		},
		{path:'/login.html',component:login},
		{path:'/302.html',component:unauth},
		{path:'/*',component:notPage}
	]
})
//to表示  要去的路由对象  from 表示正在离开的路由对象 next表示是否跳转路由
router.beforeEach((to,from,next)=>{
	let arrivePath=to.path;//抵达的路径
	console.log('抵达的路径:'+arrivePath)
	let loginUrl=store.getters.getLoginUrl;//登录的路径
	if(arrivePath === loginUrl){
		console.log('在登录页面')
		next() //放行
		return;
	}
	if(!store.getters.isLogin){
		console.log('还没有登录!')
		next({path:loginUrl})
		return;
	}
	// store.dispatch('getActiveUser')
	// 	.then(response=>{
	// 		if(!response){//不存在登录数据
	// 			console.log('没有登录')
	// 			next({path:loginUrl})
	// 		}
	// 	})
	console.log('登录了')
	//登录了
	next()
})
export default router