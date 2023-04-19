// 引入腾讯地图jssdk文件
import QQMapWX from "../../utils/qqmap-wx-jssdk.min.js"
 
export default {
	// 为当前模块开启命名空间
	namespaced: true,
	state: {
		// 默认城市
		city: '北京市',
        //当前位置的经纬度
		x: 0,
		y: 0
	},
	mutations: {
		newCityFun(state, newCity) {
			state.city = newCity
			console.log(newCity)
			console.log(state.city)
		},
		getCity() {
			var that = this
			// 获取用户是否开启 授权获取当前的地理位置、速度的权限。
			uni.getSetting({
				success(res) {
					console.log(res)
 
					// 如果没有授权
					if (!res.authSetting['scope.userLocation']) {
						// 则拉起授权窗口
						uni.authorize({
							scope: 'scope.userLocation',
							success() {
								let qqmapsdk = new QQMapWX({
									key: 'U67BZ-GJTEX-V464V-ZC7IB-J23R7-V2F4Y'
								});
								//点击允许后--就一直会进入成功授权的回调 就可以使用获取的方法了
								uni.getLocation({
									type: 'wgs84',
									success: function(res) {
										that.x = res.longitude
										that.y = res.latitude
										console.log("if", res)
										console.log('当前位置的经度：' + res.longitude)
										console.log('当前位置的纬度：' + res.latitude)
										// 逆地址解析方法
										qqmapsdk.reverseGeocoder({
											location: {
												latitude: res.latitude,
												longitude: res.longitude
											},
											success(res) {
												var newCity = ''
												console.log(res)
												// 取到用户的定位城市，赋值传递出去
												newCity = res.result
													.address_component.city
												// that.commit('m_location/newCityFun')
											}
										})
										uni.showToast({
											title: '当前位置的经纬度：' + res.longitude +
												',' + res.latitude,
											icon: 'success',
											mask: true
										})
									},
									fail(error) {
										console.log('失败', error)
									}
								})
							},
							fail(error) {
								//点击了拒绝授权后--就一直会进入失败回调函数--此时就可以在这里重新拉起授权窗口
								console.log('拒绝授权', error)
 
								uni.showModal({
									title: '提示',
									content: '若点击不授权，将无法使用位置功能',
									cancelText: '不授权',
									cancelColor: '#999',
									confirmText: '授权',
									confirmColor: '#f94218',
									success(res) {
										console.log(res)
										if (res.confirm) {
											// 选择弹框内授权
											uni.openSetting({
												success(res) {
													console.log(res.authSetting)
												}
											})
										} else if (res.cancel) {
											// 选择弹框内 不授权
											console.log('用户点击不授权')
										}
									}
								})
							}
						})
					} else {
						let qqmapsdk = new QQMapWX({
							key: 'U67BZ-GJTEX-V464V-ZC7IB-J23R7-V2F4Y'
						});
						// 有权限则直接获取
						uni.getLocation({
							type: 'wgs84',
							success: function(res) {
								that.x = res.longitude
								that.y = res.latitude
								console.log("else", res)
								console.log('当前位置的经度：' + res.longitude)
								console.log('当前位置的纬度：' + res.latitude)
								// 逆地址解析方法
								qqmapsdk.reverseGeocoder({
									location: {
										latitude: res.latitude,
										longitude: res.longitude
									},
									success(res) {
										var newCity = ''
										console.log(res)
										// 取到用户的定位城市，赋值传递出去
										newCity = res.result.address_component.city
										// that.commit('m_location/newCityFun')
									},
									fail(res) {
										console.log("错误", res)
									}
								})
								//打开地图
								uni.openLocation({
									latitude: that.y,
									longitude: that.x,
									success: function() {}
								});
								uni.showToast({
									title: '当前位置的经纬度：' + res.longitude + ',' + res.latitude,
									icon: 'success',
									mask: true
								})
							},
							fail(error) {
								uni.showToast({
									title: '请勿频繁调用！',
									icon: 'none',
								})
								console.log('失败', error)
							}
						})
					}
				}
			})
		}
	},
	actions: {
 
	}
}