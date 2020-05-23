
const webpack={
	namespaced:true,
	state:{
		communicationData:{},//双向通信的数据
	},getters:{
		getCommunicationData(state,getters){
			return state.communicationData
		}
	},mutations:{
		onMessageCallBack(state,payload){
			let fals=false
			if(fals){//如果就是存储在 permission模块中的数据的话
				
			}else{
				//将数据存储到这个中去
				state.communicationData=payload.data.data;
			}
		}
	},actions:{
		
	}
}