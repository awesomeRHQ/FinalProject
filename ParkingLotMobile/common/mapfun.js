//微信小程序要调用的js文件
// #ifdef MP  
import amap from '@/utils/amap-wx.130.js';

// #endif
//获取位置信息
const getlocation = (opt) => {
	return new Promise((resolve, reject) => {
		
		///小程序开始
		// #ifdef MP
		var amapPlugin = new amap.AMapWX({
			key: '7e5015294123295e9fbbcbb45b0502e1'  //此处为高德平台申请的微信小程序的key
		});
		// uni.showLoading({
		// 	title: '获取信息中'
		// });
		amapPlugin.getRegeo({
			success: function(data) {
				resolve(data)
			},
			fail: function(err) {
				reject(err)
			},
			complete: function() {
				uni.hideLoading();
			}
		});
		// #endif
		//小程序结束
	})
};
export default {
	getlocation: getlocation
}