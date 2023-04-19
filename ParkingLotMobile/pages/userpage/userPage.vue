<template>
	<view class="content">
		<view id="header" class="headerbox">
			<image class="backgroundpic" src="../../static/indexbackground.jpg" mode="	aspectFit" style="width:100%;height:300rpx;" ></image>
		</view>
		<view id="main" class="mainbox ">
			<view style="height: 26px;"></view>
			<view class="username">用户信息</view>
			<view class="divider"/>
			<view class="mesbox"> 
				<view class="mesbody">车牌号
					<text class="mes">{{ carId }}</text>
				</view>
				<view class="mesbody">占用车位
					<text class="mes">{{ parkingPlaceId }}</text>
				</view>
				<view class="mesbody">预约有效期
					<text class="mes">{{ deadline }}</text>
				</view>
				<view class="mesbody">入场时间
					<text class="mes">{{ enterTime }}</text>
				</view>
				<view class="mesbody">离场时间
					<text class="mes">{{ leaveTime }}</text>
				</view>
				<view class="mesbody">停车时长
					<text class="mes">{{ parkTime }}小时</text>
				</view>
				<view class="mesbody">停车费用
					<text class="mes">{{ cost }}元</text>
				</view>
				<view class="mesbody">月卡卡号
					<text class="mes">{{ vip }}</text>
				</view>
				<view class="mesbody">月卡到期时间
					<text class="mes">{{ vipDeadline }}</text>
				</view>
			</view>
			<view class="divider"/>
			<view class="btnbox">
				<view :class="{hiddenBox: isHidden}">
					<button class="button"  @click="carRegister" >{{ btnname }}车辆信息</button>
				</view>
			</view>
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
import {mapState,mapMutations} from 'vuex';
export default {
	data() {
			return {
				//消息盒子类型
				msgType: '',
				//消息盒子内容
				messageText: '',
				btnname:'',
				openId:'',
				carId:'',
				parkingPlaceId:'',
				deadline:'',
				enterTime:'',
				leaveTime:'',
				parkTime:'',
				cost:'',
				vip:'',
				vipDeadline:'',
				isHidden:false,
				
			}
		},
	onLoad() {
		
	},
	onShow() {
		var loginCustomerInfo = uni.getStorageSync("LoginCustomerInfo");
		this.openId = loginCustomerInfo.openId;
		this.carId = this.FormatMsg(loginCustomerInfo.carId);
		this.parkingPlaceId = this.FormatMsg(loginCustomerInfo.parkingPlaceId);
		this.deadline = this.FormatMsg(loginCustomerInfo.deadline);
		this.enterTime = this.FormatMsg(loginCustomerInfo.enterTime);
		this.leaveTime = this.FormatMsg(loginCustomerInfo.leaveTime);
		this.parkTime = this.FormatMsg(loginCustomerInfo.parkTime);
		this.cost = this.FormatCost(loginCustomerInfo.cost);
		this.vip = this.FormatMsg(loginCustomerInfo.vip);
		this.vipDeadline = this.FormatMsg(loginCustomerInfo.vipDeadline);
		// console.log(loginCustomerInfo)
		//控制功能按钮的显示
		if(loginCustomerInfo.carId == null | loginCustomerInfo.carId == ""){
			this.btnname = '登记';
		}else{
			this.btnname = '修改';
		}
	},
	onReady() {
		
	},
	methods: {
		//登录消息盒子
		messageBox(msgType,messageText){
			this.msgType = msgType
			this.messageText = messageText
			this.$refs.message.open();
		},
		FormatMsg(msg){
			if(msg == '' || msg == null){
				return "--"
			}else{
				return msg;
			}
		},
		FormatCost(msg){
			if(msg == '' || msg == null){
				return "0"
			}else{
				return msg;
			}
		},
		carRegister() {
			var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			if(loginCustomer.openId == null | loginCustomer.openId == ''){
				this.messageBox("error","请先登录！")
				setTimeout(function(){
					uni.switchTab({
						url:'/pages/index/index'
					})
				},1500)
			}else{
				uni.navigateTo({
					//保留当前页面，跳转到应用内的某个页面
					url:'/pages/carregister/carregister'
				})
			}
			
		},
		 //转化日期时间格式
		handleDateTimeFormat(datet) {
		  var date = new Date(datet);
		  let Y = date.getFullYear(),
			M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1),
			D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()),
			h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()),
			m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes())
		  return Y + '-' + M + '-' + D + ' ' + h + ':' + m;
		},
	}
}
</script>

<style>
	.test{
		border: 1px solid red;
	}
	.headerbox{
		z-index: -1;
		position: relative;
	}
	.backgroundpic{
		z-index: 0;
		/* position: absolute; */
		/* bottom: 0; */
	}
	.mainbox{
		width: 100%;
		height: 700px;
		background-color: #FFF;
		border-radius: 40px 40px 0px 0px;
		margin-top: -37px;
		z-index: 10;
		align-items: center;
		justify-content: center;
	}
	.username{
		z-index: 20;
		text-align: center;
		font-size: 18px;
	}
	.divider{
		width: 75%;
		height: 0rpx;
		border: 1px dashed #E0E3DA;
		margin-top: 15px;
		margin-left: 12.5%;
	}
	.mesbox{
		width: 80%;
		align-items: center;
		margin: 10px 0px 0px 10%;
		font-size: 18px;
	}
	.mesbody{
		line-height: 30px;
	}
	.mes{
		display: inline-block;
		float: right;
	}
	.btnbox{
		margin-top: 20px;
	}
	.button{
		width: 150px;
		border-radius: 24px;
		box-shadow: 0px 1px 4px 2px rgba(0, 0, 0, 0.1);
		background-color: #80b0df;
		color: #FFF;
		Letter-spacing: 1px;
	}
	.hiddenBox{
		display: none;
	}
</style>

