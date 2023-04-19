import {
    CUSTOMER_LOGIN,
    CUSTOMER_PARKING_PLACE,
    OUT_LOGIN
} from './mutations-type'

export default {
    state: {
		id:null,
        openid: null, 
        carid:null,
		parkingplaceid:null,
		deadline:null,
		isBooking:null,
		latitude:null,
		longitude:null,
		enterTime:null,
		outerTime:null,
		cost:null,
		parkTime:null
    },
    getters: {
    },
    mutations: {
        //设置
        [CUSTOMER_LOGIN](state, customer) {
            state.loginStatus = true;
			state.id = customer.id;
            state.openid = customer.openId;
			state.carid = customer.licensePlateId;
			state.parkingplaceid = customer.parkingPlaceId;
			state.deadline = customer.deadline;
			state.isBooking	= customer.isBooking;
			state.latitude	= customer.latitude;
			state.longitude = customer.longitude;
			state.enterTime = customer.enterTime;
			state.leaveTime = customer.leaveTime;
            //持久化存储 => 本地存储
			uni.setStorage({
				key:"LoginCustomerInfo",
				data:customer
			}) 
        },
        //
        [CUSTOMER_PARKING_PLACE](state , place) {
            state.latitude	= place.Latitude;
            state.longitude = place.Longitude;
        },
        [OUT_LOGIN](state) {
            state.loginStatus = false;
            state.openid = null;
            state.carid = null;
            state.parkinglotid = null;
            localStorage.removeItem("LoginCustomerInfo");
        }
    },
    actions: {
    },
    modules: {
    }
}