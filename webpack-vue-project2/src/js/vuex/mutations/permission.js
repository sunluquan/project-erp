import Vuex from 'vuex'
import Vue from 'vue'
import axios from 'axios'
Vue.use(Vuex)
// const store=Vuex.Store({
// 	mutations:{
		
// 	},actions:
// })
const permission={
	state:{
		loginUrl:'/login.html',
		indexUrl:'/index',
		activeUser:{},
		treeMenu:[]
	},getters:{
		getLoginUrl(state,getters){
			return state.loginUrl
		},getIndexUrl(state,getters){
			return state.indexUrl
		},getActiveUser(state,getters){
			return state.activeUser
		},isLogin(state,getters){
			return Object.keys(state.activeUser).length>0
		},getTreeMenu(state,getters){
			return state.treeMenu
		}
	},mutations:{
		updateActiveUser(state,payload){
			let objName=payload.obj
			let propertyName=payload.property
			let value=payload.value
			console.log(objName)
			console.log(propertyName)
			console.log(value)
			let obj = state.activeUser[objName];
			obj[propertyName]=value
		},setActiveUser(state,payload){
			let activeUser=payload.activeUser;
			if(activeUser==null||activeUser==''){
				activeUser={};
			}
			state.activeUser=activeUser;
		},setTreeMenu(state,payload){
			let treeMenu=payload.treeMenu
			if(treeMenu==null||treeMenu==''){
				treeMenu=[];
			}
			state.treeMenu=treeMenu;
		}
	},actions:{
		getActiveUser(context){
			return new Promise((resolve,reject) => {
				if(!context.getters.isLogin){//如果activeUser中没有数据  就去获取数据
					axios.get('api/activeUser')
						.then(response=>{
							//console.log(response.data)
							context.commit({
								type:'setActiveUser',
								activeUser:response.data
							})
							resolve(context.getters.isLogin)
						})
				}else{
					resolve(context.getters.isLogin)
				}
			})
			
		},getTreeMenu(context){
			let treeMenu=context.state.treeMenu;
			if(treeMenu!=''&&treeMenu.length>0)
				return;
			axios.get('api/common/treeMenu')
				.then(response=>{
					if(response.statusCord==200){
						context.commit({
							type:"setTreeMenu",
							treeMenu:response.data
						})
					}else{
						
					}
				})
		}
	}
}
export default permission