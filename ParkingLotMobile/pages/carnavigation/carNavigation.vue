<template>
	<view>
		<view id="mapbox">
			<map ref="mymap" id="mymap" :style="{height: windowHeight + 'px'}"
			:latitude="doorLatitude" :longitude="doorLongitude" :markers="markers" :polyline="polyline"
			 rotate=170 ></map>
		</view>
		<!-- 提示信息弹窗 -->
		<view> 
			<uni-popup ref="message" type="message">
				<uni-popup-message :type="msgType" :message="messageText" :duration="2000"></uni-popup-message>
			</uni-popup>
		</view>
	</view>
</template>

<script>
import {
		mapState,
		mapMutations,
		mapGetters,
		mapActions
	} from 'vuex'
// 引入SDK核心类，js文件根据自己业务，位置可自行放置
var QQMapWX = require('@/utils/qqmap-wx-jssdk.min.js')
var qqmapsdk = new QQMapWX({
			key: 'U67BZ-GJTEX-V464V-ZC7IB-J23R7-V2F4Y'
		})
export default {
	data(){
		return{
			//消息盒子类型
			msgType: '',
			//消息盒子内容
			messageText: '',
			//地图初始化中心
			doorLatitude:22.361735,
			doorLongitude:113.550950, //大东小西
			latitude:'',
			longitude:'',
			markers:[],
			polyline:[],
			windowHeight:'',
			//定时器
			timer:null,
			lc:null,
		} 
	},
	onReady() {
		//创建地图画布
		this.mapContext = uni.createMapContext('mymap',this);
	},
	onLoad(){
	},
	onShow() {
		var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
		var enterFlag = uni.getStorageSync("enterFlag");
		if(loginCustomer.openId == null | loginCustomer == null){//未登录
			this.messageBox('error','请先登录！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},1500)
		}else if(loginCustomer.carId == null | loginCustomer.carId == ''){//未登记车牌信息
			this.messageBox('error','请绑定车牌！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/userpage/userPage"
				})
			},1500)
		}else if(loginCustomer.enterTime == null && enterFlag == ''){
			this.messageBox('error','您未入场！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},1500)
		}else if(loginCustomer.leaveTime != null){
			this.messageBox('error','您已离场！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},1500)
		}else{
			console.log("车位导航：车位导航前置条件满足")
			var that = this;
			//获取用户授权后定位位置信息
			uni.getSetting({
				success(res) {
					// 如果没有授权
					if (!res.authSetting['scope.userLocation']) {
						// 则拉起授权窗口
						uni.authorize({
							scope: 'scope.userLocation',
							success(){
								console.log("车位导航：用户已授权（授权窗口），开始执行程序")
								//进入程序主方法
								that.main();
							},
							fail(error) {
								//点击了拒绝授权后--就一直会进入失败回调函数--此时就可以在这里重新拉起授权窗口
								console.log('拒绝授权', error)
								//提示
								uni.showModal({
									title: '提示',
									content: '若点击不授权，将无法使用位置功能',
									cancelText: '不授权',
									cancelColor: '#999',
									confirmText: '授权',
									confirmColor: '#f94218',
									success(res) {
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
						});
					}
					//若有授权，执行业务方法
					else{
						console.log("车位导航：用户已授权（二次），开始执行程序")
						that.main();
					}
				},
				fail(err) {
					console.log("授权获取错误：",err);
				}
			})
		}
	}, 
	//监听页面隐藏
	onHide(){
		console.log(this.markers);
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		this.markers = [];
		console.log("车位导航：退出程序");
	},
	//监听页面卸载
	onUnload(){
		console.log(this.markers);
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		this.markers = [];
		console.log("车位导航：退出程序");
	},
	methods:{
		//登录消息盒子
		messageBox(msgType,messageText){
			this.msgType = msgType
			this.messageText = messageText
			this.$refs.message.open();
		},
		//程序主方法
		main(){
			var that = this;
			uni.getSystemInfo({
				success(res) {
					that.windowHeight = res.windowHeight;
				}
			})
			var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			this.lc = loginCustomer;
			that.MapNavigation();
			that.createMarkers();
			that.timer = setInterval( () => {
			    that.MarkCustomerLocation();
				that.handleErrorPark();
			}, 2000);
		},
		//获取用户位置并标记
		MarkCustomerLocation(){
			var that = this;
			uni.getLocation({
				type: 'wgs84',
				// geocode: true , //设置该参数为true可直接获取经纬度及城市信息
				// isHighAccuracy:true,
				success: function(res) {
					console.log("用户当前定位信息：",res);
					that.userLatitude = res.latitude;
					that.userLongitude = res.longitude;
					
					//测试
					that.userLatitude = that.lc.latitude;
					that.userLongitude = that.lc.longitude;
					
					if(that.markers.length == 0 || that.markers[that.markers.length-1].id != 9990){
						that.markers.push({
							id:9990,
							width:25,
							height:25,
							longitude:that.userLongitude,
							latitude:that.userLatitude,
							iconPath:'/static/icon/user-navi.png',
						});
					}else{
						that.markers.pop();
						that.markers.push({
							id:9990,
							width:25,
							height:25,
							longitude:that.userLongitude,
							latitude:that.userLatitude,
							iconPath:'/static/icon/user-navi.png',
						});
					}
				},
				fail(error) {
					console.log('失败', error)
				}
			});
		},
		//标记车位，并把路线规划出来
		MapNavigation(){
			var that = this;
			var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			let doorLocation = this.doorLatitude+","+this.doorLongitude; 
			let parkLocation = loginCustomer.latitude+","+loginCustomer.longitude;
			//车位标记
			var userPLMarker = {
				id:9999,
				width:25,
				height:25,
				longitude:loginCustomer.longitude,
				latitude:loginCustomer.latitude,
				iconPath:'/static/icon/userplace.png',
				callout:{
					content:"您的车位:"+loginCustomer.parkingPlaceId,
					fontSize:14,//文本大小
					borderRadius:15,//边框圆角
					borderWidth:'10',
					bgColor:'#1296db',//背景颜色
					display:'ALWAYS',//常显
				}
			}
			this.markers.push(userPLMarker);
			
			qqmapsdk.direction({
				mode:'driving', //导航方式，驾车
				from:doorLocation, //起点经纬度 '22.361735,113.550950'
				to:parkLocation, //终点经纬度
				success:function(res){
					// console.log("direction res",res)
					var ret = res;
					var coors = ret.result.routes[0].polyline, pl = [];
					//坐标解压（返回的点串坐标，通过前向差分进行压缩）
					var kr = 1000000;
					for (var i = 2; i < coors.length; i++) {
					  coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
					}
					//将解压后的坐标放入点串数组pl中
					for (var i = 0; i < coors.length; i += 2) {
					  pl.push({ latitude: coors[i], longitude: coors[i + 1] })
					}
					//设置polyline属性，将路线显示出来,将解压坐标第一个数据作为起点
					that.latitude=pl[0].latitude,
					that.longitude=pl[0].longitude,
					that.polyline= [{
						points: pl,
						color: '#1296db',
						width: 4
					}] 
				},
				fail:function(res){
					console.log('err',res)
				}
			})
		},
		//标记车位
		createMarkers(){
			var that = this;
			uni.request({
				url:"http://localhost:8899/parkingplace/getlist",
				method:'POST',
				data:{},
				success(res) {
					console.log(res.data.data.list)
					var logincustomer = uni.getStorageSync("LoginCustomerInfo");
					var places = res.data.data.list;
					for(var i= 0 ; i<places.length;i++ ){
						if(places[i].ParkingPlaceId == logincustomer.parkingPlaceId){
							// console.log("busy:",places[i].ParkingPlaceId)
							continue;
						}
						if(places[i].IsParking == 1){
							var place = {
									id:places[i].Id,
									width:25,
									height:25,
									longitude:places[i].Longitude,
									latitude:places[i].Latitude,
									iconPath:'/static/icon/fullplace.png',
								}
						}else{
							var place = {
									id:places[i].Id,
									width:25,
									height:25,
									longitude:places[i].Longitude,
									latitude:places[i].Latitude,
									iconPath:'/static/icon/emptyplace.png',
								}
						}
						that.markers.push(place);
					}
				}
			})
		},
		handleErrorPark(){
			if(Math.abs(this.lc.latitude-this.userLatitude) > 0.000020 && Math.abs(this.lc.longitude - this.userLongitude) > 0.000010){
				this.messageBox("error","请停放到系统指示的区域中！");
			}else if(Math.abs(this.lc.latitude-this.userLatitude) <= 0.000020 && Math.abs(this.lc.longitude - this.userLongitude) <= 0.000010){
				this.messageBox("success","您已到达指定位置，可以停车！");
			}else{
				
			}
		},
	},
}
</script>

<style>
	/* #mapbox{
		width: 100px;
		height: 300px;
	} */
	#mymap{
		width: 100%;
	}
</style>