<template>
	<view>
		<view>
			<!-- 提示信息弹窗 -->
			<uni-popup ref="message" type="message">
				<uni-popup-message :type="msgType" :message="messageText" :duration="2000"></uni-popup-message>
			</uni-popup>
		</view> 
		<form @submit="onSubmit" :modelValue="formData">
			<view class="carregisterbox">
				<text class="title">输入车牌号</text>
				<picker @columnchange="handlePickerChange" :value="cityRes" :range="city" mode='multiSelector'>
					<view class="carIdPrifix">{{ city[0][cityRes[0]] }}{{ city[1][cityRes[1]] }}:</view>
				</picker>
				<input class="carIdSuffix" type="text" v-model="carIdSuffix" placeholder="在此输入车牌号" @blur="checkData"/>
			</view>
			<button form-type="submit" class="submitbtn">确定</button>
		</form>
		
	</view>
</template>

<script>
export default {
	onReady() {
	},
	onUnload() {
		let pages = getCurrentPages().length - 1;
	},
	data() {
		return {
			msgType: '',
			messageText: '',
			submitFlag:false,
			city:[
				['京','津','沪','渝','辽','黑','吉','冀','鲁','苏','浙','闽','豫','鄂','湘','皖',
				'赣','粤','桂','甘','宁','晋','蒙','陕','新','青','藏','川','贵','滇','琼'],
				['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
				'T','U','V','W','X','Y','Z']
				],
			cityRes:[0,0],
			carIdPrifix:'',
			carIdSuffix:'',
			
			loginCustomer:null,
		}
	},
	methods: {
		handlePickerChange: function(e) {
			this.cityRes[e.detail.column] = e.detail.value;
			switch(e.detail.column){
				case 0:
					switch(this.cityRes[0]){
						case 0://北京
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','V','Y'];
							break;
						case 1://天津
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','Q','R'];
							break;
						case 2://上海
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','AX','BX','DX'];
							break;
						case 3://重庆
							this.city[1] = ['A','B','C','D','F','G','H'];
							break;
						case 4://辽宁
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P'];
							break;
						case 5://黑龙江
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P'];
							break;
						case 6://吉林
							this.city[1] = ['A','B','C','D','F','G','H','J','K'];
							break;
						case 7://山西
							this.city[1] = ['A','B','C','D','F','G','H','J','R','T'];
							break;
						case 8://山东
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S',
											'U','Y']
							break;
						case 9://江苏
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','U']
							break;
						case 10://浙江
							this.city[1] = ['A','B','C','D','F','G','H','J','K','L']
							break;
						case 11://福建
							this.city[1] = ['A','B','C','D','F','G','H','J','K']
							break;
						case 12://河南
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S',
											'U'];
							break;
						case 13://湖北
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','AW'];
							break;
						case 14://湖南
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','U'];
							break;
						case 15://安徽
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S'];
							break;
						case 16://江西
							this.city[1] = ['A','B','C','D','F','G','H','J','K','L'];
							break;
						case 17://广东
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S',
											'T','U','V','W','X','Y','Z'];
							break;
						case 18://广西
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','R']
							break;
						case 19://甘肃
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P']
							break;
						case 20://宁夏
							this.city[1] = ['A','B','C','D','E']
							break;
						case 21://山西
							this.city[1] = ['A','B','C','D','E','F','H','J','K','L','M']
							break;
						case 22://内蒙古
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M']
							break;
						case 23://陕西
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','U','V']
							break;
						case 24://新疆
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','N','P','Q','R']
							break;
						case 25://青海
							this.city[1] = ['A','B','C','D','F','G','H']
							break;
						case 26://西藏
							this.city[1] = ['A','B','C','D','F','G','H','J']
							break;
						case 27://四川
							this.city[1] = ['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S',
											'T','U','V','W','X','Y','Z']
							break;
						case 28://贵州
							this.city[1] = ['A','B','C','D','E','F','G','H','J']
							break;
						case 29://云南
							this.city[1] = ['A','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S']
							break;
						case 30://海南
							this.city[1] = ['A','B','C','D','E','F','G','H']
							break;
					}
					this.cityRes.splice(0,0);
				case 1:
					this.cityRes.splice(1,0)
					break;
			}
		},
		onSubmit() {
			this.carIdPrifix = this.city[0][this.cityRes[0]]+this.city[1][this.cityRes[1]]+':';
			var that = this;
			var _loginCustomer = uni.getStorageSync("LoginCustomerInfo");
			this.carIdSuffix =  this.FormatUpperCase(this.carIdSuffix);
			_loginCustomer.carId = this.carIdPrifix + this.carIdSuffix;
			uni.setStorageSync("LoginCustomerInfo",_loginCustomer);
			if(this.submitFlag){
				console.log("车辆注册：调用接口，返回数据");
				uni.request({
					url:'http://localhost:8899/customer/link',
					data:_loginCustomer,
					method:'POST',
					success(res) {
						that.loginCustomer = res.data;
						uni.setStorageSync("LoginCustomerInfo",that.loginCustomer);
						setTimeout(function() {
							that.messageBox('success',"车辆保存成功！")
						}, 1500);
						uni.navigateBack({
							delta:1
						});
					},
					complete() {
						that.submitFlag = false;
					}
				})
			}else{
				this.messageBox("warn","请完善表单")
			}
		},
		checkData(input){
			console.log(input);
			var containSpecial = RegExp(/[(\ )(\~)(\~)(\!)(\！)(\@)(\#)(\$)(\￥)(\%)(\^)(\……)(\&)(\*)(\()(\（)(\))(\）)(\-)(\_))(\——)(\+)(\=)(\[)(\【)(\])(\】)(\{)(\})(\|))(\、))(\)(\\)(\;)(\；)(\:)(\：)(\')(\‘)(\’)(\")(\“)(\”)(\,)(\，)(\.)(\。)(\/)(\《)(\<)(\>)(\》)(\?)(\？)(\)]+/);
			if(input.detail.value==null || input.detail.value ==''){
				this.messageBox('error',"请登记您的车牌号");
			}else if(input.detail.cursor < 5 || input.detail.cursor >6){
				this.messageBox('error',"请输入正确的车牌号形式");
			}else if(containSpecial.test(input.detail.value)){
				this.messageBox('error',"车牌号不能含有特殊字符");
			}else{
				console.log("车牌信息验证完毕")
				this.submitFlag=true
			}
		},
		//规范车牌字母为大写字母
		FormatUpperCase(carIdSuffix){
			var [...caridsuffix] = carIdSuffix;
			for (var i = 0; i < carIdSuffix.length; i++) {
				if(	carIdSuffix[i] >= 'a' && carIdSuffix[i] <= 'z'){
					caridsuffix[i] = carIdSuffix[i].toUpperCase();
				}
			}
			return caridsuffix.join("");
		},
		//消息盒子
		messageBox(msgType,messageText){
			this.msgType = msgType
			this.messageText = messageText
			this.$refs.message.open();
		},
	}
}
</script>

<style>
	input{
		border: 1px solid black;
		width: 80%;	
	}
	.pickerarea{
		background-color: red;
		height: 100px;
		width: 20%;
	}
	.carregisterbox{
		width: 100%;
		margin-top: 20px;
		align-items: center;
	}
	.carIdPrifix{
		font-size: 20px;
		text-align: center;
		display: inline-block;
		float: left;
		width: 20%;
		height: 35px;
		margin-top: 10px;
		margin-left: 17px;
		border: 1px solid black;
	}
	.carIdSuffix{
		font-size: 20px;
		display: inline-block;
		float: left;
		width: 70%;
		height: 35px;
		margin-top: 10px;
		margin-left: 5px;
	}
	.title{
		margin-left: 20px;
		line-height: 25px;
		border-left:4px solid #3c87ce;
		padding-left: 5px;
		font-size: 18px;
	}
	.carinput{
		width: 85%;
		height: 35px;
		line-height: 35px;
		margin-left: 20px;
		margin-top: 10px;
		font-size: 20px;
	}
	.submitbtn{
		background-color: #3c87ce;
		color: #fff;
		width: 65%;
		margin-top: 60px;
		font-size: 20px;
		align-items: center;
	}
</style>