import Vuex from 'vuex'
import common from '@/js/vuex/mutations/common.js'
import permission from '@/js/vuex/mutations/permission.js'
import websocket from '@/js/vuex/mutations/websocket.js'
import manufacture from '@/js/vuex/mutations/manufacture.js'
import Vue from 'vue'
Vue.use(Vuex)
const store=new Vuex.Store({
	modules:{
		"common":common,//引入公共的模块
		"permission":permission,//权限模块
		websocket,
		manufacture
	}
})
export default store