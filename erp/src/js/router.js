import VueRouter from 'vue-router'
import common from '@/js/common/common.js'
import store from '@/js/vuex/store.js'

import index from '@/templates/system/index.vue'

import login from '@/templates/system/login.vue'

import notPage from '@/templates/system/404.vue'

import unauth from '@/templates/system/unauth.vue'


//import personalCenter from '@/src/templates/system/personalcenter.vue'
import user from '@/templates/user/user.vue'
import dept from '@/templates/dept/index.vue'
import reserve from '@/templates/reserve/reserve.vue'
import safetystockss from '@/templates/safetystockss/safetystockss.vue'
import review from '@/templates/review/review.vue'
import insertUser from '@/templates/user/insert_user.vue'
import permission from '@/templates/permission/permission.vue'
import insertPermission from '@/templates/permission/insertPermission.vue'
import updatePermission from '@/templates/permission/updatePermission.vue'
import role from '@/templates/role/role.vue'
import insertRole from '@/templates/role/insertRole.vue'
import updateRole from '@/templates/role/updateRole.vue'
import updatedept from '@/templates/dept/update_dept.vue'
import insertdept from '@/templates/dept/insert.vue'
import warehouse from '@/templates/warehouse/warehouse.vue'
import insertwarehouse from '@/templates/warehouse/insert.vue'
import updateWarehouse from '@/templates/warehouse/update_warehouse.vue'
import safetysheet from '@/templates/safetystockss/safetysheet.vue'
import reviewsheet from '@/templates/review/reviewsheet.vue'
import selectstock from '@/templates/selectstock/selectstock.vue'
import selectstockdetails from '@/templates/selectstock/selectstockdetails.vue'
import safetystockchange from '@/templates/safetystockchange/safetystockchange.vue'
import updatestockchange from '@/templates/safetystockchange/updatestockchange.vue'
import gather from '@/templates/gather/gather.vue'
import gatherproducts from '@/templates/gather/gatherproduct.vue'
import gatherreview from '@/templates/gatherreview/gatherreview.vue'
import gascheduling from '@/templates/gascheduling/gascheduling.vue'
import gaschedulingsheet from '@/templates/gascheduling/gaschedulingsheet.vue'
import gaschedulingdetails from '@/templates/gascheduling/gaschedulingdetails.vue'
import inventoryRegistration from '@/templates/inventoryRegistration/inventoryRegistration.vue'
import inventoryRegistrationSheet from '@/templates/inventoryRegistration/inventoryRegistrationSheet.vue'
import invtRegstreview from '@/templates/inventoryRegistration/invtRegstreview.vue'
import invtRegstreviewSheet from '@/templates/inventoryRegistration/invtRegstreviewSheet.vue'
import invtRegstselect from '@/templates/inventoryRegistration/invtRegstselect.vue'
import invtRegstselectSheet from '@/templates/inventoryRegistration/invtRegstselectSheet.vue'
//出库申请管理
import stockOut from '@/templates/stockOut/stockOut.vue'
import stockOut_product from '@/templates/stockOut/stockOut_product.vue'
import stockOutCheck from '@/templates/stockOut/stockOutCheck.vue'
import stockOutCheckView from '@/templates/stockOut/stockOutCheckView.vue'
import stockOutSelect from '@/templates/stockOut/stockOutSelect.vue'
import stockOutSelectAll from '@/templates/stockOut/stockOutSelectAll.vue'
//出入库调度管理
import outbound from '@/templates/outbound/outbound.vue'
import outboundResult from '@/templates/outbound/outboundResult.vue'
//出库管理
import stockOutRegist from '@/templates/stockOutRegist/stockOutRegist.vue'
import stockOutRegistView from '@/templates/stockOutRegist/stockOutRegistView.vue'
import outboudRegistResult from '@/templates/stockOutRegist/outboudRegistResult.vue'



//需要权限验证的
export const permission_router = [{
		path: 'personalcenter.html',
		component: () => import('@/templates/system/personalcenter.vue'),
		children: [{
			path: 'updatePwd',
			component: () => import('@/components/updatePwd.vue')
		}]
	},
	{
		path: 'user.html',
		component: user,
		children: [{
			path: 'insert',
			component: insertUser
		}]
	},
	{
		path: 'permission.html',
		component: permission,
		children: [{
				path: 'insert',
				component: insertPermission
			},
			//props:(route)=>({pId:route.query.pId})
			{
				path: 'update/:pId',
				component: updatePermission,
				props: true
			}
		]
	},
	{
		path: 'role.html',
		component: role,
		children: [{
				path: 'insert',
				component: insertRole
			},
			{
				path: 'update/:rid',
				component: updateRole,
				props: true
			}
		]
	},
	{
		path: 'dept.html',
		component: dept,
		children: [{
				path: 'insert',
				component: insertdept
			},
			{
				path: 'update/:deptId',
				component: updatedept,
				props: true
			}
		]
	},
	{
		path: 'warehouse.html',
		component: warehouse,
		children: [{
				path: 'insert',
				component: insertwarehouse
			},
			{
				path: 'update/:deptId',
				component: updatedept,
				props: true
			}
		]
	},
	{
			path: 'safetystockss.html',
			component: safetystockss,
			children: [{
				path: 'safetysheet/:productId',
				component: safetysheet,
				props: true
			}]
		},
		{
			path: 'review.html',
			component: review,
			children: [{
				path: 'reviewsheet/:safetystockId',
				component: reviewsheet,
				props: true
			}]
		},
		{
			path: 'selectstock.html',
			component: selectstock,
			children: [{
				path: 'selectstockdetails/:productId',
				component: selectstockdetails,
				props: true
			}]
		},
		{
			path: 'safetystockchange.html',
			component: safetystockchange,
			children: [{
				path: 'updatestockchange/:productId',
				component: updatestockchange,
				props: true
			}]
		},	
		{
			path: 'gather.html',
			component: gather,
			children: [{
				path: 'gatherproduct',
				component: gatherproducts,
	
			}]
		},
		{
			path: 'gascheduling.html',
			component: gascheduling,
			children: [{
					path: 'gaschedulingsheet/:gatherId',
					component: gaschedulingsheet,
	
				},
				{
					path: 'gaschedulingdetails/:id',
					component: gaschedulingdetails,
				}
			]
		},
		{
			path: 'inventoryRegistration.html',
			component: inventoryRegistration,
			children: [{
					path: 'inventoryRegistrationSheet/:gatherId',
					component: inventoryRegistrationSheet,
		            props: true
				},
				{
					path: 'invtRegstselectSheet/:gatherId',
					component: invtRegstselectSheet,
					props: true
				}				
			]
		},
			{
				path: 'stockOut.html',                   
				component: stockOut,
				children: [{
						path: 'stockOut_product',
						component: stockOut_product,
					},
					{
						path: 'stockOutCheckView/:payId',
						component :stockOutCheckView,
						props: true
					},
						{
							path: 'stockOutSelectAll/:payId',
							component :stockOutSelectAll,
							props: true
						}
				]
			}
	
]
//错误页面
export const error_router = [{
	path: '/*',
	component: notPage
}]

const router = new VueRouter({
	//mode:'history',
	routes: [{
			path: '/',
			redirect: '/index'
		},
		{
			path: '/index',
			component: index,
			children: [{
				path: 'updatePwd',
				component: () => import('@/components/updatePwd.vue'),
			}]
		},
		{
			path: '/login.html',
			component: login
		}
	]
})
//to表示  要去的路由对象  from 表示正在离开的路由对象 next表示是否跳转路由
// router.beforeEach((to, from, next) => {
// 	let arrivePath = to.path; //抵达的路径
// 	console.log('抵达的路径:' + arrivePath)
// 	let loginUrl = store.getters.getLoginUrl; //登录的路径
// 	if (arrivePath === loginUrl) {
// 		console.log('在登录页面')
// 		next() //放行
// 		return;
// 	}

// 	if (!store.getters.isLogin) {
// 		let data = common.getSessionStorage(common.getVuex_storage_name());
// 		if (data != null) {
// 			next()
// 			return;
// 		}
// 		next({
// 			path: loginUrl
// 		})
// 		return;
// 	}
// 	//登录了
// 	next()
// })
//export default router
//测试用的  方便

// const router = new VueRouter({
// 	//mode:'history',
// 	routes: [{
// 			path: '/',
// 			redirect: '/login.html'
// 		},
// 		{
// 			path: '/index',
// 			component: index,
// 			children: [{
// 					path: 'dept.html',
// 					component: dept,
// 					children: [{
// 							path: 'insert',
// 							component: insertdept
// 						},
// 						{
// 							path: 'update/:deptId',
// 							component: updatedept,
// 							props: true
// 						}
// 					]
// 				},
// 				{
// 					path: 'warehouse.html',
// 					component: warehouse,
// 					children: [{
// 							path: 'insert',
// 							component: insertwarehouse
// 						},
// 						{
// 							path: 'update/:deptId',
// 							component: updatedept,
// 							props: true
// 						}
// 					]
// 				},
// 				{
// 									path: 'safetystockss.html',
// 									component: safetystockss,
// 									children: [{
// 										path: 'safetysheet/:id',
// 										component: safetysheet,
// 										props: true
// 									}]
// 								},
// 								{
// 									path: 'review.html',
// 									component: review,
// 									children: [{
// 										path: 'reviewsheet/:id',
// 										component: reviewsheet,
// 										props: true
// 									}]
// 								},
// 								{
// 									path: 'selectstock.html',
// 									component: selectstock,
// 									children: [{
// 										path: 'selectstockdetails/:safetystockId',
// 										component: selectstockdetails,
// 										props: true
// 									}]
// 								},
// 								{
// 									path: 'safetystockchange.html',
// 									component: safetystockchange,
// 									children: [{
// 										path: 'updatestockchange/:safetystockId',
// 										component: updatestockchange,
// 										props: true
// 									}]
// 								},
// 								{
// 									path: 'gather.html',
// 									component: gather,
// 									children: [{
// 										path: 'gatherproduct',
// 										component: gatherproducts,
				
// 									}]
// 								},
// 								{
// 									path: 'gascheduling.html',
// 									component: gascheduling,
// 									children: [{
// 											path: 'gaschedulingsheet/:stockid',
// 											component: gaschedulingsheet,
				
// 										},
// 										{
// 											path: 'gaschedulingdetails/:id',
// 											component: gaschedulingdetails,
// 										}
// 									]
// 								},
// 								{
// 									path: 'inventoryRegistration.html',
// 									component: inventoryRegistration,
// 									children: [{
// 											path: 'inventoryRegistrationSheet/:gatherId',
// 											component: inventoryRegistrationSheet,
// 								            props: true
// 										},						
													
// 									]
// 								},
// 								{
// 									path: 'invtRegstreview.html',
// 									component: invtRegstreview,
// 									children: [{
// 											path: 'invtRegstreviewSheet/:gatherId',
// 											component: invtRegstreviewSheet,
// 								            props: true
// 										},						
													
// 									]
// 								},
// 								{
// 									path: 'invtRegstselect.html',
// 									component: invtRegstselect,
// 									children: [{
// 											path: 'invtRegstselect',
// 											component: invtRegstselect,
// 								            props: true
// 										},
													
// 									]
// 								},
// 								{
// 									path: 'reserve.html',
// 									component: reserve,
// 									children: [{
// 											path: 'reserve',
// 											component: reserve,
// 								            props: true
// 										},
													
// 									]
// 								},
// 				{
// 					path: 'checkerInternalProductionRegister.html',
// 					component: () => import('@/templates/production/internal_production/checker_internal_production_register.vue'),
// 					meta: '内部生产登记核审'
// 				},
// 				{
// 					path: 'internalProductionRegister.html',
// 					component: () => import('@/templates/production/internal_production/internal_production_register.vue'),
// 					meta: '内部生产登记',
// 				},
// 				{
// 					path: 'manufacture.html',
// 					component: () => import('@/templates/production/manufacture/manufacture.vue'),
// 					meta: '制定单查询',
// 					children: [{
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/manufacture/update_manufacture.vue')
// 					}]
// 				},
// 				{
// 					path: 'checkerManufacture.html',
// 					component: () => import('@/templates/production/dispatching/checker_manufacture.vue'),
// 					meta: '审核制定单',

// 				},
// 				{
// 					path: 'dispatching.html',
// 					component: () => import('@/templates/production/dispatching/dispatching.vue'),
// 					meta: '生成派工单',
// 					children: [{
// 						path: 'insert/:data',
// 						component: () => import('@/templates/production/dispatching/insert_dispatching.vue'),
// 						props: true,
// 						meta: '新增派工单'
// 					}]
// 				},
// 				{
// 					path: 'checkerProductionPlan.html',
// 					component: () => import('@/templates/production/production_plan/checker_production_plan.vue'),
// 					meta: '生产计划审核',
// 					children: [{
// 						path: 'checker/:id',
// 						component: () => import('@/templates/production/production_plan/confirm_checker_production_plan.vue'),
// 						meta: '生产计划核审确认',
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'productionPlan.html',
// 					component: () => import('@/templates/production/production_plan/production_plan.vue'),
// 					meta: '查询生产计划',
// 					children: [{
// 						path: 'insert',
// 						component: () => import('@/templates/production/production_plan/insert_production_plan.vue'),
// 						meta: '新增生产计划'
// 					}, {
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/production_plan/update_production_plan.vue'),
// 						meta: '修改生产计划',
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'processMaterial.html',
// 					component: () => import('@/templates/production/process_material_design/process_material.vue'),
// 					meta: '查询工序物料设计',
// 					children: [{
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/process_material_design/update_process_material.vue'),
// 						meta: '修改物料工序设计'
// 					}]
// 				},
// 				{
// 					path: 'checkerProcessMaterial.html',
// 					component: () => import('@/templates/production/process_material_design/checker_process_material.vue'),
// 					meta: '审核工序物料设计单',
// 					children: [{
// 						path: 'checker/:id',
// 						component: () => import(
// 							'@/templates/production/process_material_design/confirm_checker_process_material.vue'),
// 						meta: '确认审核工序物料设计'
// 					}]
// 				},
// 				{
// 					path: 'makingProcessMaterial.html',
// 					component: () => import('@/templates/production/process_material_design/making_process_material.vue'),
// 					meta: '制定工序物料',
// 					children: [{
// 						path: 'making/:id',
// 						component: () => import('@/templates/production/process_material_design/making_process_material_one.vue'),
// 						meta: '执行工序物料的制定',
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'process.html',
// 					component: () => import('@/templates/production/process_design/process.vue'),
// 					meta: '工序设计查询',
// 					children: [{
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/process_design/update_process.vue'),
// 						meta: '修改工序设计查询',
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'checkerProcess.html',
// 					meta: '审核工序设计单',
// 					component: () => import('@/templates/production/process_design/checker_process.vue'),
// 					children: [{
// 						path: 'checker/:id',
// 						component: () => import('@/templates/production/process_design/confirm_checker_process.vue'),
// 						props: true,
// 					}]
// 				},
// 				{
// 					path: 'makingProcess.html',
// 					component: () => import('@/templates/production/process_design/making_process.vue'),
// 					children: [{
// 						path: 'design/:id',
// 						component: () => import('@/templates/production/process_design/making_process_one.vue'),
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'productMaterialDesign.html',
// 					component: () => import('@/templates/production/material_constitute/product_material_design.vue'),
// 					children: [{
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/material_constitute/update_product_material_design.vue'),
// 						props: true
// 					}]

// 				},
// 				{
// 					path: 'checkerProductMaterialDesign.html',
// 					component: () => import('@/templates/production/material_constitute/checker_product_material_design.vue'),
// 					children: [{
// 						path: 'confirm/:id',
// 						component: () => import('@/templates/production/material_constitute/confirm_product_material_design.vue'),
// 						props: true
// 					}]

// 				},
// 				{
// 					path: 'materialConstitute.html',
// 					component: () => import('@/templates/production/material_constitute/material_constitute.vue'),
// 					children: [{
// 						path: 'design/:id',
// 						component: () => import('@/templates/production/material_constitute/material_constitute_design.vue'),
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'checkerProduct.html',
// 					component: () => import('@/templates/production/productdesign/checker_product_design.vue'),
// 					children: [{
// 						path: 'confirm/:id',
// 						component: () => import('@/templates/production/productdesign/confirm_checker_product.vue'),
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'product.html',
// 					component: () => import('@/templates/production/productdesign/product_design.vue'),
// 					children: [{
// 						path: 'insert',
// 						component: () => import('@/templates/production/productdesign/insert_product_design.vue')
// 					}, {
// 						path: 'update/:id',
// 						component: () => import('@/templates/production/productdesign/update_product_design.vue'),
// 						props: true
// 					}]
// 				},
// 				{
// 					path: 'updatePwd',
// 					component: () => import('@/components/updatePwd.vue'),
// 				}, {
// 					path: 'personalcenter.html',
// 					component: () => import('@/templates/system/personalcenter.vue'),
// 					children: [{
// 						path: 'updatePwd',
// 						component: () => import('@/components/updatePwd.vue')
// 					}]
// 				},
// 				{
// 					path: 'user.html',
// 					component: user,
// 					children: [{
// 						path: 'insert',
// 						component: insertUser
// 					}]
// 				},
// 				{
// 					path: 'permission.html',
// 					component: permission,
// 					children: [{
// 							path: 'insert',
// 							component: insertPermission
// 						},
// 						//props:(route)=>({pId:route.query.pId})
// 						{
// 							path: 'update/:pId',
// 							component: updatePermission,
// 							props: true
// 						}
// 					]
// 				},
// 				{
// 					path: 'role.html',
// 					component: role,
// 					children: [{
// 							path: 'insert',
// 							component: insertRole
// 						},
// 						{
// 							path: 'update/:rid',
// 							component: updateRole,
// 							props: true
// 						}
// 					]
// 				}
// 			]
// 		},
// 			{
// 				path: 'stockOut.html',                   
// 				component: stockOut,
// 				children: [{
// 						path: 'stockOut_product',
// 						component: stockOut_product,
// 					},
// 					{
// 						path: 'stockOutCheckView/:payId',
// 						component :stockOutCheckView,
// 						props: true
// 					},
// 						{
// 							path: 'stockOutSelectAll/:payId',
// 							component :stockOutSelectAll,
// 							props: true
// 						}
// 				]
// 			},
// 		{
// 			path: '/login.html',
// 			component: login
// 		},
// 		{
// 			path: '/*',
// 			component: notPage
// 		}
// 	]
// })

export default router
router.beforeEach((to, from, next) => {
	let arrivePath = to.path; //抵达的路径
	console.log('抵达的路径:' + arrivePath)
	let loginUrl = store.getters.getLoginUrl; //登录的路径
	if (arrivePath === loginUrl) {
		console.log('在登录页面')
		next() //放行
		return;
	}

	if (!store.getters.isLogin) {
		let data = common.getSessionStorage(common.getVuex_storage_name());
		if (data != null) {
			next()
			return;
		}
		next({
			path: loginUrl
		})
		return;
	}
	//登录了
	next()
})
// {
// 					path: 'updatePwd',
// 					component: () => import('@/components/updatePwd.vue')
// 				},
// 				{
// 					path: 'personalcenter.html',
// 					component: () => import('@/templates/system/personalcenter.vue'),
// 					children: [{
// 						path: 'updatePwd',
// 						component: () => import('@/components/updatePwd.vue')
// 					}]
// 				},
// 				{
// 					path: 'user.html',
// 					component: user,
// 					children: [{
// 						path: 'insert',
// 						component: insertUser
// 					}]
// 				},
// 				{
// 					path: 'permission.html',
// 					component: permission,
// 					children: [{
// 							path: 'insert',
// 							component: insertPermission
// 						},
// 						//props:(route)=>({pId:route.query.pId})
// 						{
// 							path: 'update/:pId',
// 							component: updatePermission,
// 							props: true
// 						}
// 					]
// 				},
// 				{
// 					path: 'role.html',
// 					component: role,
// 					children: [{
// 							path: 'insert',
// 							component: insertRole
// 						},
// 						{
// 							path: 'update/:rid',
// 							component: updateRole,
// 							props: true
// 						}
// 					]
// 				}
// 			]
