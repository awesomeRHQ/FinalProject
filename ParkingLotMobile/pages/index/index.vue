<template>
	<view class="content">
		<view id="header">
			<image src="../../static/indexbackground.jpg" mode="aspectFit" style="width:100%;height:375rpx;" ></image>
		</view>
		
		<view> 
			<!-- 登录提示窗示例 -->
			<uni-popup ref="loginDialog" type="dialog">
				<uni-popup-dialog type="success" cancelText="关闭" confirmText="登录" title="提示" content="欢迎使用,请先登录!" @confirm="LoginDialogConfirm"
					@close="DialogClose('已取消登录...')">
				</uni-popup-dialog>
			</uni-popup>
		</view>
		<view>
			<!-- 预约提示窗示例 -->
			<uni-popup ref="bookingDialog" type="dialog">
				<uni-popup-dialog type="success" cancelText="关闭" confirmText="预约" title="提示" content="本次预约有效时长为20分钟,是否预约？" @confirm="BookingDialogConfirm"
					@close="DialogClose('已取消预约...')">
				</uni-popup-dialog>
			</uni-popup>
		</view>
		<view>
			<!-- 离场提示窗示例 -->
			<uni-popup ref="leaveDialog" type="dialog">
				<uni-popup-dialog type="success" cancelText="关闭" confirmText="离场" title="提示" content="即将离场,是否确认？" @confirm="LeaveDialogConfirm"
					@close="DialogClose('已取消离场...')">
				</uni-popup-dialog>
			</uni-popup>
		</view>
		<button lang="zh" @click="handleFindParkingLots()" class="bookbtn">停车场搜寻</button> 
		<button lang="zh" @click="handleBooking()" class="bookbtn">预约车位</button>
		<view class="iconboxes">
			<view class="iconbox"> 
				<!-- <button lang="zh" @click="enter()" class="commonbtn test"></button> -->
				<uni-icons type="navigate" color="#3567cc" size="50" class="commonbtn" @click="handleEnter()"></uni-icons>
				<view class="btntitle">入场</view>
			</view>
			<view class="iconbox">
				<!-- <button lang="zh" @click="getUserInfo()" class="commonbtn test"></button> -->
				<uni-icons type="search" color="#3567cc" size="50" class="commonbtn" @click="FindCar()"></uni-icons>
				<view class="btntitle">寻车</view>
			</view>
			<view class="iconbox">
				<!-- <button lang="zh" @click="getUserInfo()" class="commonbtn test"></button> -->
				<uni-icons type="redo" color="#3567cc" size="50" class="commonbtn" @click="Leave()"></uni-icons>
				<view class="btntitle">离场</view>
			</view>
		</view>
		
		
		<view class="titleText">欢迎使用停车场智能导航系统</view>
		
		<template>
			  <view class="divider"/>
		</template>
		
		<view class="subtitlebox">
			<view class="subtitletext">使用提示</view>
			<text class="subtitlecontent">
				为了便捷您与他人的停车体验，请按照系统规定停放车辆。
				<text class="attentionbox">
					<text class="usertitle">蓝色标记</text>：您为该车位的使用者。
				</text>
				<text class="attentionbox">
					<text class="fulltitle">红色标记</text>：该车位已被占用。
				</text>
				<text class="attentionbox">
					<text class="emptytitle">绿色标记</text>：该车位未被占用。
				</text>
			</text>
		</view>
		
		<view>
			<!-- 提示信息弹窗 -->
			<uni-popup ref="message" type="message">
				<uni-popup-message :type="msgType" :message="messageText" :duration="2000"></uni-popup-message>
			</uni-popup>
		</view>
		
	</view>
</template>

<script>
import {mapState,mapMutations} from 'vuex';

	export default {
		data() {
			return {
				// title: 'Hello',
				// type: 'center',
				//消息盒子类型
				msgType: '',
				//消息盒子内容
				messageText: '',
				value: '',
				loginCustomer:{}
			}
		},
		//监听器
		watch:{
			//监听请求成功之后提示成功
			// '$store.state.customer.openid':{
			// 	deep:true,
			// 	handler(val){
			// 		this.messageBox('success',`12成功！`);
			// 		// this.isHidden = false;
			// 		this.loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			// 	}
			// }
		}, 
		//监听页面加载
		onLoad() {
			//打开登录弹窗
			this.onLogin();
		},
		//监听页面显示
		onShow() {
			//获取缓存中的LoginCustomerInfo
			this.loginCustomer = uni.getStorageSync("LoginCustomerInfo");
		},
		//监听页面初次渲染完成
		onReady() {
		},
		//监听页面隐藏
		onHide(){
			
		},
		//监听页面卸载
		onUnload(){
			//清除本次登录缓存
			uni.removeStorage({
				key:"LoginCustomerInfo"
			})
		},
		onPullDownRefresh(){
			var that = this;
			wx.login({
				success(res){
					if(res.code){
						wx.request({
							url: 'http://localhost:8899/customer/customerlogin',
							data:{
								code:res.code
							},
							success(res){
								console.log("用户登录：登录接口(customerlogin)请求完毕，后台处理完毕，返回处理结果");
								//LoginCustomer/Customer类返回
								// console.log(res.data)
								//有预约信息则修改时间格式
								if(res.data.deadline != 0 & res.data.deadline != null ){
									res.data.deadline = that.handleDateTimeFormat(res.data.deadline.time);
								}
								if(res.data.enterTime != 0 & res.data.enterTime != null ){
									res.data.enterTime = that.handleDateTimeFormat(res.data.enterTime.time);
								}
								if(res.data.vipDeadline != 0 & res.data.vipDeadline != null ){
									res.data.vipDeadline = that.handleDateTimeFormat(res.data.vipDeadline.time);
								}
								//后台修改预约信息，规定预约超时状态为：isbooking==0 && deadline!=null
								if(res.data.isBooking == 0 && res.data.deadline != null && res.data.enterTime == null){
									setTimeout(function(){
										that.messageBox("error","注意，您的预约已超时!")
									},2000)
								}
								//将用户信息存入缓存
								that.CUSTOMER_LOGIN(res.data); 
							},
							fail(err) {
								that.messageBox('error',`登录失败，请再次尝试！`) 
								console.log("requestfail",err)
							},
							complete(){
								uni.stopPullDownRefresh()
							}
						}) 
					}
				}
			}) 
			
		},
		computed:{ 
			...mapState({
				openid:state=>state.customer.openid,
				carid:state=>state.customer.carid,
				parkingplaceid:state=>state.customer.parkingplaceid,
				})
		},
		methods: {
			...mapMutations(["CUSTOMER_LOGIN","CUSTOMER_PARKING_PLACE"]),
			//登录弹窗
			onLogin(type) {
				this.msgType = type
				this.$refs.loginDialog.open();
			},
			//登录弹窗提交
			LoginDialogConfirm() {
				//获取登录用户信息，并查询后台数据库
				this.getUserInfo();
				uni.showLoading({
					title:"登录中...",
					mask:true
				})
			},
			//获取登录用户信息，并查询后台数据库
			getUserInfo(){
				var that = this;
				wx.login({
					success(res){
						if(res.code){
							wx.request({
								url: 'http://localhost:8899/customer/customerlogin',
								data:{
									code:res.code
								},
								success(res){
									console.log("用户登录：登录接口(customerlogin)请求完毕，后台处理完毕，返回处理结果");
									//LoginCustomer/Customer类返回
									// console.log(res.data)
									//有预约信息则修改时间格式
									if(res.data.deadline != 0 & res.data.deadline != null ){
										res.data.deadline = that.handleDateTimeFormat(res.data.deadline.time);
									}
									if(res.data.enterTime != 0 & res.data.enterTime != null ){
										res.data.enterTime = that.handleDateTimeFormat(res.data.enterTime.time);
									}
									if(res.data.vipDeadline != 0 & res.data.vipDeadline != null ){
										res.data.vipDeadline = that.handleDateTimeFormat(res.data.vipDeadline.time);
									}
									//后台修改预约信息，规定预约超时状态为：isbooking==0 && deadline!=null
									if(res.data.isBooking == 0 && res.data.deadline != null && res.data.enterTime == null){
										setTimeout(function(){
											that.messageBox("error","注意，您的预约已超时!")
										},2000)
									}
									//将用户信息存入缓存
									that.CUSTOMER_LOGIN(res.data); 
									that.messageBox('success',`登录成功！`) 
								},
								fail(err) {
									that.messageBox('error',`登录失败，请再次尝试！`) 
									console.log("requestfail",err)
								},
								//完成之后关闭加载效果
								complete() {
									uni.hideLoading();
								}
							}) 
						}
					}
				}) 
			},
			//找寻停车场的方法
			handleFindParkingLots(){
				if(this.loginCustomer.openId != "" && this.loginCustomer.openId != null){
					console.log("找寻停车场：找寻停车场前置条件达成，开始调用接口")
					uni.navigateTo({
						url:'/pages/parkinglotnavigation/parkingLotNavigation'
					})
				}	
				else{
					this.messageBox('error','请先登录！');
					this.onLogin("");
				}
				
			},
			//预约车位的方法
			handleBooking(){
				if(this.loginCustomer.openId == null | this.loginCustomer.openId == ""){
					this.messageBox('error','请先登录！'); 
					this.onLogin();
				}else if(this.loginCustomer.carId == null | this.loginCustomer.carId == ""){
					this.messageBox('error','请绑定车辆！');
					setTimeout(function(){
						uni.switchTab({
							url:"/pages/userpage/userPage"
						})
					},1500)
				}else{
					this.$refs.bookingDialog.open();
				}
			},
			//确认预约车位
			BookingDialogConfirm() {
				var that = this;
				uni.showLoading({
					title:"处理中...",
					mask:true,
				}) 
				//从缓存中获取信息，先判断是否存在预约信息，且预约是否有效
				//从缓存中获取数据，并赋值给变量
				this.loginCustomer = uni.getStorageSync('LoginCustomerInfo');
				if(this.loginCustomer.enterTime != null){
					//车辆已入场
					this.messageBox("error",'您已入场，不能预约！');
					uni.hideLoading();
				}else if(this.loginCustomer.isBooking == 1 && this.compareTime(this.loginCustomer.deadline)){
					//存在预约信息 
					this.messageBox("error",'您已预约，请勿重复预约！');
					uni.hideLoading();
				}else{
					console.log("车位预约：预约前置条件达成，开始调用接口")
					//没有预约(包括预约时间过期)
					var that = this;
					//调用后端接口
					uni.request({
						url: 'http://localhost:8899/car/booking',
						method:'GET',
						data:{
							CarId:that.loginCustomer.carId, //车牌信息
							OpenId:that.loginCustomer.openId //用户OpenId信息
						},
						success(res){
							console.log("车位预约：预约接口(booking)请求完毕，后台处理完毕，返回处理结果");
							console.log(res.data)
							//返回Car类
							if(res.data == null){
								that.messageBox('error','车位已满，无法预约!');
							}else{ 
								//更新vuex和缓存
								that.CUSTOMER_LOGIN(res.data);
								that.messageBox('success','预约成功!');
								that.loginCustomer = uni.getStorageSync("LoginCustomerInfo")
							} 
						},
						fail(err) {
							console.log("requestfail",err)
						},
						//完成之后关闭加载效果
						complete() {
							uni.hideLoading();
						}
					})
				}
			}, 
			//模拟车辆进场的方法
			handleEnter(){
				var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
				console.log("test:",loginCustomer)
				if(loginCustomer.openId == null | loginCustomer.openId == ""){
					this.messageBox('error','请先登录！');
					this.onLogin("");
				}else if(loginCustomer.carId == null | loginCustomer.carId == ""){
					this.messageBox('error','请绑定车辆！');
					setTimeout(function(){
						uni.switchTab({
							url:"/pages/userpage/userPage"
						})
					},2000)
				}else{
					if(loginCustomer.isBooking == 1 & this.compareTime(loginCustomer.deadline) & loginCustomer.enterTime == null){
						//预约有效
						uni.showLoading({
							title:"处理中...",
							mask:true 
						});
						console.log("预约有效");
						this.EnterRequest();
					}else if(loginCustomer.enterTime != null && loginCustomer.leaveTime == null){
						this.messageBox('error','您的已入场，请先离场后再次进入！');
					}else{
						this.messageBox('error','您未预约或预约已过时，为您重新搜索车位！');
						uni.showLoading({
							title:"处理中...",
							mask:true 
						});
						console.log("预约超时");
						this.EnterRequest();
						
					} 
				}
			},
			//确认车辆进场的方法
			EnterRequest(){
				console.log("进场前置条件达成，开始调用接口")
				var that = this;
				var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
				//获取该车辆最新的信息
				uni.request({
					url:'http://localhost:8899/car/enter',
					method:'POST',
					data:loginCustomer,
					success(res){
						console.log("车辆入场：入场接口（enter）请求完毕，后台处理完毕，返回处理结果");
						if(res.data.parkingPlaceId=="" && res.data.enterTime==""){
							that.messageBox("error","车位已满，请等待");
						}else{
							res.data.enterTime = that.handleDateTimeFormat(res.data.enterTime);
							that.CUSTOMER_LOGIN(res.data);
							uni.setStorageSync("enterFlag",'ec');
							uni.switchTab({
								url:'/pages/carnavigation/carNavigation'
							})	
						}
					},
					fail(err) {
						console.log("enterfail",err)
					},
					//完成之后关闭加载效果
					complete() {
						uni.hideLoading();
					}
				})
			},
			//寻车的方法
			FindCar(){
				var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
				//判断用户是否登录和绑定车辆
				if(loginCustomer.openId == null | loginCustomer.openId == ""){
					this.messageBox('error','请先登录！');
					this.onLogin();
				}else if(loginCustomer.carId == null | loginCustomer.carId == ""){
					this.messageBox('error','请绑定车辆！');
					setTimeout(function(){
						uni.switchTab({
							url:"/pages/userpage/userPage"
						})
					},2000)
				}else if(loginCustomer.leaveTime == null){
					uni.showLoading({
						title:"处理中...",
						mask:true 
					})
					setTimeout(function(){
						uni.hideLoading();
						uni.switchTab({
							url:'/pages/findcar/findcar'
						});
					},1500)
				}else{
					this.messageBox("error","寻车失败，请先将车停入停车场！");
				}
			},
			Leave(){
				var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
				if(loginCustomer.openId == null){
					this.messageBox("error","请先登录！");
					this.$refs.loginDialog.open();
				}else if(loginCustomer.carId == null){
					this.messageBox("error","请先绑定车辆！");
					setTimeout(function(){
						uni.switchTab({
							url:'/pages/userpage/userPage'
						})
					},1500);
				}else if(loginCustomer.enterTime == null){
					this.messageBox("error","请先入场停放车辆！");
				}else if(loginCustomer.leaveTime != null){
					this.messageBox("error","您已离场！");
				}else{
					this.$refs.leaveDialog.open();
				}
			}, 
			LeaveDialogConfirm(){
				console.log("车辆离场：预约前置条件达成，开始调用接口")
				uni.showLoading({
					title:"处理中...",
					mask:true
				})
				var that = this;
				var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
				console.log(loginCustomer)
				wx.request({
					url:'http://localhost:8899/car/leave',
					data:loginCustomer,
					method:'POST',
					success(res){
						console.log("车辆离场：离场接口(leave)请求完毕，后台处理完毕，返回处理结果");
						console.log("leaveDialogConfirm：",res);
						if(res.data.leaveTime != 0 & res.data.leaveTime != null ){
							res.data.leaveTime = that.handleDateTimeFormat(res.data.leaveTime);
						}
						that.CUSTOMER_LOGIN(res.data);
						if(res.data.vip != ""){
							that.messageBox("succes","月卡用户停车:"+res.data.parkTime+"小时");
						}else{
							that.messageBox("succes","停车:"+res.data.parkTime+"小时,"+"缴费:"+res.data.cost+"元");
						}
					},
					fail(err){
						console.log("leave_err:",err);
					},
					complete(){
						uni.hideLoading();
						uni.removeStorageSync("enterFlag");
					}
				})
			},
			compareTime(time){
				let bookingTime = new Date(time).getTime()
				let currentTime =  new Date().getTime()
				console.log("bot:",bookingTime)
				console.log("crt:",currentTime) 
				//预约已过时
				if(bookingTime - currentTime < 0)
					return false;
				return true;
			},
			//转化日期时间格式
			handleDateTimeFormat(datet) {
			  var date = new Date(datet);
			  let Y = date.getFullYear(),
				M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1),
				D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()),
				h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()),
				m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()),
				s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds())
			  return Y + '-' + M + '-' + D + ' ' + h + ':' + m + ':' + s;
			},
			//处理日期格式
			handleDateFormat(datet) {
			  var date = new Date(datet);
			  let Y = date.getFullYear(),
				M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1),
				D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate())
			  return Y + '-' + M + '-' + D;
			},
			//登录消息盒子
			messageBox(msgType,messageText){
				this.msgType = msgType
				this.messageText = messageText
				this.$refs.message.open();
			},
			//关闭弹窗
			DialogClose(mes){
				this.messageBox('warn',mes);
			}, 
		}
	}
</script>

<style>
	.test{
		border: 1px solid red;
	}
	#header{
		width: 100%;
		
	}
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}
	.titleText{
		margin-top: 30px;
		font-size: 15px;
		color: #3c87ce;
	}
	.divider{
		width: 70%;
		height: 0rpx;
		border: 1px dashed #E0E3DA;
		margin-top: 20px;
	}
	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	.hiddenBox{
		display: none;
	}
	.bookbtn{
		background-color: #80b0df;
		color: white;
		width: 200px;
		border-radius: 24px;
		box-shadow: 0px 1px 4px 2px rgba(0, 0, 0, 0.1);
		margin-top: 25px;
	}
	.iconboxes{
		margin-top: 10px;
	}
	.iconbox{
		width: 100px;
		height: 70px;
		display: inline-block;
		justify-content: space-between;
		align-items: center;
		margin: 3px;
	}
	.commonbtn{
		/* float: left; */
		width: 100%;
		background-color: white;
		border: white;
		display: flex;
	    flex-direction: column;
		align-items: center;
	}
	.uni-icons{
		width: 50px;
		height: 50px;
		color: #80b0df;
	}
	.btntitle{
		width: 50%;
		font-size: 20px;
		align-items: center;
		margin-left: 31%;
	}
	.subtitlebox{
		width: 100%;
		margin-top: 10px;
	}
	.subtitletext{
		margin-left: 20px;
		line-height: 25px;
		border-left:4px solid #3c87ce;
		padding-left: 5px;
		font-size: 18px;
	}
	.subtitlecontent{
		display: block;
		width: 90%;
		margin: 0 auto;
		/* margin-top: 5px;
		margin-left: 25px; */
		font-size: 15px;
	}
	.attentionbox{
		display: block;
	}
	.fulltitle{
		color: #d81e06;
		font-weight: 600;
	}
	.emptytitle{
		color: #47ee53;
		font-weight: 600;
	}
	.usertitle{
		color: #1296db;
		font-weight: 600;
	}
</style>
