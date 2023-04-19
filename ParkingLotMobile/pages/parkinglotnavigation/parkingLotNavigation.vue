<template>
	<view>
		<view id="mapbox">
			<map ref="mymap" id="mymap" :style="{height:windowHeight + 'px'}"
			:latitude="userLatitude" :longitude="userLongitude" :markers="markers" :polyline="polyline"
			 rotate=0 ></map>
		</view> 
		 <!-- :markers="markers" :polyline="polyline" -->
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
			userLatitude:0,
			userLongitude:0,
			customerLocation:'',
			//最近且有空位的停车场信息
			close_empty_pl:'',
			//地图标记
			markers:[],
			//规划路线
			polyline:[],
			//停车场信息
			parkinglots:[],
			//用户设备高度信息
			windowHeight:0,
			//计时器
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
		var that = this;
		//获取用户授权后定位位置信息
		uni.getSetting({
			success(res) {
				console.log(res);
				// 如果没有授权
				if (!res.authSetting['scope.userLocation']) {
					// 则拉起授权窗口
					uni.authorize({
						scope: 'scope.userLocation',
						success(){
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
				//若有授权
				else{
					//进入程序主方法
					that.main();
				}
			}
		})
	},
	//监听页面隐藏
	onHide(){
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		console.log("找寻停车场：退出程序1");
		console.log(this.markers);
	},
	//监听页面卸载
	onUnload(){
		//离开页面时清除计时器
		clearInterval(this.timer);
		this.timer = null;
		console.log("找寻停车场：退出程序2");
		console.log(this.markers);
	},
	
	methods:{
		//程序入口
		main(){
			var that = this;
			//获取用户设备高度信息，调整地图大小
			uni.getSystemInfo({
				success(res) {
					that.windowHeight = res.windowHeight;
				}
			})
			//从后台获取停车场信息
			that.GetParkingLotLocations();
			//获取最近停车场信息，并进行路径规划
			that.FindCloseParkingLot();
			//1秒执行一次获取位置信息，并标记，以模拟导航
			that.timer = setInterval( () => {
			    that.MarkCustomerLocation();			
			}, 2000)
		},
		//获取最近停车场信息，并进行路径规划
		FindCloseParkingLot(){
			console.log("找寻停车场：查询最近停车场信息，并进行路径规划");
			var that = this;
			//获取用户当前位置的经纬度，返回到本地缓存中
			this.GetCustomerLocation();
			//用户坐标
			var cl = uni.getStorageSync("CustomerLocation");
			//停车场坐标信息
			var parkingLotLocations = uni.getStorageSync("parkinglots");
			//定义最短距离
			var minDistance = 1000;
			//将最近的有空余车位的停车场找出来
			for(let i=0;i<parkingLotLocations.length;i++){
				if(parkingLotLocations[i].isFull == 1)
					continue;
				var distance = this.ComputeDistance(parkingLotLocations[i],cl);
				//将最短路径的坐标记录下来
				if(minDistance>distance){
					minDistance = distance;
					// this.close_empty_pll = parkingLotLocations[i].plLatitude + ',' +  parkingLotLocations[i].plLongitude;
					this.close_empty_pl = parkingLotLocations[i];
				}
			}
			//路径规划
			this.Direction(cl);
			//添加起点终点标记
			this.markers = [
				//起点标记
				{
					id:0,
					width:25,
					height:25,
					longitude:cl.longitude,
					latitude:cl.latitude,
					iconPath:'/static/icon/userplace.png',
					callout:{
						content:"出发点",
						fontSize:14,//文本大小
						borderRadius:15,//边框圆角
						borderWidth:'10',
						bgColor:'#1296db',//背景颜色
						display:'ALWAYS',//常显
					}
				},
				//最近且有空余的停车场标记
				{
					id:1,
					width:25,
					height:25,
					longitude:this.close_empty_pl.plLongitude,
					latitude:this.close_empty_pl.plLatitude,
					iconPath:'/static/icon/userplace.png',
					callout:{
						content:"最近且有空余的停车场："+this.close_empty_pl.plName,
						fontSize:14,//文本大小
						borderRadius:15,//边框圆角
						borderWidth:'10',
						bgColor:'#1296db',//背景颜色
						display:'ALWAYS',//常显
					}
				}
			]
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
					// && that.markers[that.markers.length-1].id != 9990
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
			});
		},
		//获取用户位置一次
		GetCustomerLocation(){
			var that = this;
			uni.getLocation({
				type: 'wgs84',
				geocode: true , //设置该参数为true可直接获取经纬度及城市信息
				isHighAccuracy:true,
				success: function(res) {
					uni.setStorageSync("CustomerLocation",res)
					that.userLatitude = res.latitude
					that.userLongitude = res.longitude
				},
				fail(error) {
					console.log('失败', error)
				}
			});
		},
		//从后台获取停车场信息
		GetParkingLotLocations(){
			console.log("找寻停车场：开始调用接口")
			var that = this;
			uni.request({
				url:"http://localhost:8899/parkinglot/getlist",
				success(res) {
					console.log("找寻停车场：获取停车位信息接口（getlist）请求完毕，后台处理完毕，返回处理结果");
					uni.setStorageSync("parkinglots",res.data);
					// console.log("parkinglots:",res)
				}
			})
		},
		//路线规划
		Direction(customerLocation){
			var that = this;
			//用户坐标
			var cl = customerLocation.latitude + ',' + customerLocation.longitude;
			//停车场坐标
			var pll = this.close_empty_pl.plLatitude + ',' +  this.close_empty_pl.plLongitude;
			//路径规划
			qqmapsdk.direction({
				mode:'driving',
				from:cl, //用户当前位置：起点经纬度
				to:pll, //停车场位置：终点
				success:function(res){
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
					that.polyline = [{
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
		//计算用户位置到停车场的距离
		ComputeDistance(parkingLotLocation,customerLocation){
			//停车场坐标
			var pll = parkingLotLocation.plLatitude + ',' +  parkingLotLocation.plLongitude;
			//用户坐标
			var cl = customerLocation.latitude + ',' + customerLocation.longitude;
			//计算距离
			var distance = Math.sqrt( Math.pow((parkingLotLocation.plLatitude-customerLocation.latitude),2) + Math.pow((parkingLotLocation.plLongitude-customerLocation.longitude),2) )
			return distance;
		},
		//提示弹窗
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