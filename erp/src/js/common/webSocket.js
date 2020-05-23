import store from '@/js/vuex/store.js';
import router from '@/js/router.js'
import common from '@/js/common/common.js'
let socket; //连接通信实例

const lockReconnet = false; //防止重复连接
 
const webSocketUrl = "ws://localhost:8011/imserver/";

const global_callback = null;

// let socket = { 
// 	init() {
// 		let activeUser = store.getters.getActiveUser
// 		if (activeUser) {
// 			this.WS = new WebSocket(webSocketUrl)
// 			this.onOpen();
// 			this.onError();
// 			this.onClose();
// 		} else {
// 			let loginUrl = store.getters.getLoginUrl;
// 			this.router.push({
// 				path: loginUrl
// 			});
// 		}
// 	},
// 	onMessage(callback) {
// 		if (!this.WS) this.init()
// 		this.Ws.onmessage = responseData => {
// 			try{
// 				let response = JSON.parse(responseData.data);
// 				console.log('获取到后端发送过来的数据:'+response)
// 				if (response.statusCord == 303) { //说明身份认证过期了
// 					let loginUrl = store.getters.getLoginUrl;
// 					if (router.currentRoute.path != loginUrl) { //说明身份过期而且不在登录页面
// 						alert('身份认证过期!即将跳入登录页面')
// 						//清楚sessionStorage中的数据
// 						common.removeSessionStorage(common.getVuex_storage_name())
// 						sessionStorage.clear()
// 						router.push({
// 							path: loginUrl
// 						})
// 					}
// 				} else {
// 					if (response.statusCord == 200) {
// 						store.commit({
// 							type: 'onMessageCallBack',
// 							data: response
// 						})
// 					}
// 				}
// 			}catch(e){
// 				console.log(e)
// 			}
// 		}
// 	},
// 	onError() {
// 		this.WS.onerror=response=>{
// 			console.log('连接错误!!')
// 		}
// 	},
// 	onClose() {
// 		this.WS.onclose=response =>{
// 			console.log('连接关闭了')
// 		}
// 	},onOpen(){
// 		this.Ws.onopen=response=>{
// 			console.log('连接成功!')
// 		}
// 	}
// }

//创建createSocket
let createSocket = () => {
	//try{/imserver/{userId}
	let url = webSocketUrl;
	let userId = store.getters.getActiveUser.user.uid;
	url += userId;
	console.log('url:' + url)
	if ('WebSocket' in window) {
		socket = new WebSocket(url)
	} else if ('MozWebSocket' in window) {
		socket = new MozWebSocket(url)
	}
	initSocket();
	// }catch(e){

	// }
}
let initSocket = () => {
	socket.onopen = onopen;
	socket.onmessage = onmessage;
	socket.onclose = onclose;
	socket.onerror = onerror;
}
let onopen = () => {
	console.log('连接成功!')
}
let onmessage = (responseData) => {
	console.log('获取到的服务器的数据:' + responseData.data);
	let response = JSON.parse(responseData.data);
	if (response.statusCord == 303) { //说明身份认证过期了
		let loginUrl = store.getters.getLoginUrl;
		if (router.currentRoute.path != loginUrl) { //说明身份过期而且不在登录页面
			// elementUI.message({
			// 	showClose: true,
			// 	message: '身份认证过期!即将跳入登录页面',
			// 	type: 'error',
			// 	duration: 2000
			// });
			alert('身份认证过期!即将跳入登录页面')
			//清楚sessionStorage中的数据
			common.removeSessionStorage(common.getVuex_storage_name())
			sessionStorage.clear()
			router.push({path:loginUrl})
		}
	}else{
		if(response.statusCord == 200){
			store.commit({type:'onMessageCallBack',data:response})
		}
	}
}
let onclose = () => {
	console.log('调用onclose')
}
let onerror = (e) => {
	console.log('调用onerror:' + e);
}
let onMessageCallBack = () => {

}
export default {
	createSocket,

	// createSocket(){
	// 	createSocket1();
	// }
}
