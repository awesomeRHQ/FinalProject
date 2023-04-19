import {
    CAR_BOOKING
} from './mutations-type'

export default {
    state: {
        openid: null, 
        carid:null,
		parkingplaceid:null,
		isBooking:null,
		deadline:null
    },
    getters: {
    },
    mutations: {
        //设置
        [CAR_BOOKING](state, car) {
            state.openid = car.openId;
			state.carid = car.LicensePlateId;
			state.parkingplaceid = car.parkingPlaceId;
			state.isBooking = car.isBooking;
			state.deadline = car.deadline;
            //持久化存储 => 本地存储
			uni.setStorage({
				key:"BookingCarInfo",
				data:car
			})
        },
       
    },
    actions: {
    },
    modules: {
    }
}