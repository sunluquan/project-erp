

const manufacture={
	state:{
		manufacture:{}
	},getters:{
		getManufacture(state){
			return state.manfacture
		}
	},mutations:{
		clear(state,payload){
			state.manfacture={}
		},setManufacture(state,payload){
			let product=payload.product;
			let productionPlans=payload.productionPlans
			let manfacture = state.manfacture;
			manfacture={
				product_id:product.product_id,
				product_name:product.product_name,
			}
		}
	},actions:{
		
	}
}