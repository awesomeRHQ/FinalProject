// 引入腾讯地图jssdk文件
import QQMapWX from "../../utils/qqmap-wx-jssdk.min.js"

export default {
	// 为当前模块开启命名空间
	namespaced: true,
	state: {
		userLatitude:0,
		userLongitude:0,
	  },
	  getters: {
	  },
	  mutations: {
		  //获取用户位置并标记
		  MarkCustomerLocation(){
		  	var that = this;
		  	uni.getLocation({
		  		type: 'wgs84',
		  		// geocode: true , //设置该参数为true可直接获取经纬度及城市信息
		  		// isHighAccuracy:true,
		  		success: function(res) {
		  			console.log(res)
		  			that.userLatitude = res.latitude
		  			that.userLongitude = res.longitude
		  			// && that.markers[that.markers.length-1].id != 9990
		  			if(that.markers.length == 0 ){
		  				that.markers.push({
		  					id:9990,
		  					width:25,
		  					height:25,
		  					longitude:res.longitude,
		  					latitude:res.latitude,
		  					iconPath:'/static/icon/user-navi.png',
		  				});
		  			}else{
		  				that.markers.pop();
		  				that.markers.push({
		  					id:9990,
		  					width:25,
		  					height:25,
		  					longitude:res.longitude,
		  					latitude:res.latitude,
		  					iconPath:'/static/icon/user-navi.png',
		  				});
		  			}
		  			
		  		},
		  		fail(error) {
		  			console.log('失败', error)
		  		}
		  	});
		  }
	  },
	  actions: {
	  }
}
 