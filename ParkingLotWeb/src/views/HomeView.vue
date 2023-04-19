<template>
  <div class="home">
    <el-container>
      <el-header>
        <img class="img" alt="logo" src="../assets/logo.png">
        <h1>智能停车场管理系统</h1>
      </el-header>

      <el-main>
        <el-form ref="LoginForm" :model="LoginForm" label-width="auto" :rules="rules">
          <el-form-item label="账号" prop="userAccount">
            <el-input v-model="LoginForm.userAccount" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="LoginForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onLogin">登  录</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
    
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import axios from 'axios';
import {mapMutations} from 'vuex'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data(){
    var validateAccount = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('账号不能为空'));
        } else {
          setTimeout(() => {
            if (this.LoginForm.UserAccount !== '') {
              this.$refs.LoginForm.validateField('userAccount');
            }
            callback();
          }, 1000);
        }
      };
    var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('密码不能为空'));
        } else {
          setTimeout(() => {
            if (this.LoginForm.Password !== '') {
              this.$refs.LoginForm.validateField('password');
            }
            callback();
          }, 1000);
        }
      };
    return{
      LoginForm:{
        userAccount:'',
        password:''
      },
      rules:{
        userAccount: [
            { validator: validateAccount, trigger: 'blur' },
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
      }
    }
  },
  methods:{
    ...mapMutations(['USER_LOGIN']),
    onLogin(){
      axios({
        method:'post',
        url:'http://localhost:8899/user/login',
        data:this.LoginForm ,
      }).then(res => {
        if(res.data.code == 200 && res.data.data.UserAccount != null && res.data.data.StaffId != null){
          this.$message({
            message: '登录成功!',
            type: 'success'
          });
          this.USER_LOGIN(res.data.data);
          console.log(res.data.data.UserRole)
          if (res.data.data.UserRole == '0') {
            this.$router.push({ path: '/staff'});
          }else{
            this.$router.push({ path: '/usercar'});
          }
          
        }else{
          this.$message.error('登录失败，账号或密码错误！');
        }
      })
    }
  }
}
</script>

<style scoped>
.img{
  width: 200px;
  height: 200px;
}
.test{
  border: 1px solid black;
}
.el-main{
  margin-top: 200px;
}
.el-form{
  border: 0px;
}
.el-form-item{
  width: 350px;
  margin: 0 auto;
  margin-top: 20px;
}
.el-button{
  background-color: #1890ff;
  width: 250px;
}
</style>