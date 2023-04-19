<template>
	<view>
		<view id="mapbox">
			<map ref="mymap" id="mymap" :style="{height:windowHeight + 'px'}"
			:latitude="latitude" :longitude="longitude" :markers="markers" :polyline="polyline"
			 rotate=0 ></map>
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
			//地图初始化中心点，默认为用户当前定位
			latitude:'',
			longitude:'',
			markers:[],
			polyline:[],
			windowHeight:'',
			timer:null,
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
		if(loginCustomer.openId == null){//未登录
			this.messageBox('error','请先登录！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},1500)
		}else if(loginCustomer.enterTime == null || loginCustomer.leaveTime != null){//未进场或已离场无法寻车
			this.messageBox('error','您未进场或已离场，寻车失败！');
			setTimeout(function(){
				uni.switchTab({
					url:"/pages/index/index"
				})
			},1500)
		}else{
			console.log("反向寻车：车位导航前置条件满足")
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
								console.log("反向寻车：用户已授权（授权窗口），开始执行程序")
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
						console.log("反向寻车：用户已授权（二次），开始执行程序")
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
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		this.markers.pop();
		console.log("反向寻车：退出程序");
		console.log(this.markers);
	},
	//监听页面卸载
	onUnload(){
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		this.markers.pop();
		console.log("反向寻车：退出程序");
		console.log(this.markers);
	},
	methods:{
		main(){
			var that = this;
			uni.getSystemInfo({
				success(res) {
					that.windowHeight = res.windowHeight;
				}
			})
			//获取用户当前位置的经纬度，返回到本地缓存中
			that.GetCustomerLocation();
			//用户坐标(地图渲染中心)
			var location = uni.getStorageSync("CustomerLocation");
			that.latitude = location.latitude;
			that.longitude = location.longitude;
			//路径规划
			that.Direction();
			that.timer = setInterval( () => {
			    that.MarkCustomerLocation();			
			}, 2000);
		},
		//获取用户位置
		GetCustomerLocation(){
			var that = this;
			uni.getLocation({
				type: 'wgs84',
				geocode: true , //设置该参数为true可直接获取经纬度及城市信息
				isHighAccuracy:true,
				success:res=>{
					uni.setStorageSync("CustomerLocation",res);
				}
			});
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
					if(that.markers.length == 0 || that.markers[that.markers.length-1].id != 9990){
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
					console.log('失败', error);
				}
			})
		},
		//路径规划
		Direction(){
			var that = this;
			//车位坐标
			var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			var parkingPlaceLocation = loginCustomer.latitude + ',' +  loginCustomer.longitude;
			//用户坐标
			var location = uni.getStorageSync("CustomerLocation");
			var customerLocation = location.latitude + ',' + location.longitude;
			console.log("customerlocation:",customerLocation)
			that.markers = [
			// 	//用户标记
			// 	{
			// 	id:0,
			// 	width:25,
			// 	height:25,
			// 	longitude:location.longitude,
			// 	latitude:location.latitude,
			// 	iconPath:'/static/icon/userplace.png',
			// 	callout:{
			// 		content:"您的位置",
			// 		fontSize:14,//文本大小
			// 		borderRadius:15,//边框圆角
			// 		borderWidth:'10',
			// 		bgColor:'#1296db',//背景颜色
			// 		display:'ALWAYS',//常显
			// 	}
				
			// },
			//车位标记
			{
				id:1,
				width:25,
				height:25,
				longitude:loginCustomer.longitude,
				latitude:loginCustomer.latitude,
				iconPath:'/static/icon/userplace.png',
				callout:{
					content:"车位:"+loginCustomer.parkingPlaceId,
					fontSize:14,//文本大小
					borderRadius:15,//边框圆角
					borderWidth:'10',
					bgColor:'#1296db',//背景颜色
					display:'ALWAYS',//常显
				}
			}]
			qqmapsdk.direction({
				mode:'walking',
				from:customerLocation, //用户当前位置：起点经纬度
				to:parkingPlaceLocation, //停车位置：终点
				success:function(res){
					console.log("direction res",res)
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
		messageBox(msgType,messageText){
			this.msgType = msgType
			this.messageText = messageText
			this.$refs.message.open();
		},
	}
}
</script>

<style>
	#mymap{
		width: 100%;
	}
</style>