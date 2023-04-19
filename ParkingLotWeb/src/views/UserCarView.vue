<template>
    <div class="mainbox">
      <el-dialog :title="dialogName" :visible.sync="dialogVisible" width="70%"  close-on-click-modal 
          destroy-on-close @close="handleClose">
        <el-form :model="DialogForm" label-width="auto" label-position size="medium" :rules="rules" ref="DialogForm" class="dialogform">
          <el-col :span="8">
            <el-form-item label="车牌号" prop="carId">
              <el-input v-model="DialogForm.carId" :disabled="dialogInputDisabled" style="width: 240px;" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否预约" prop="isBooking">
              <el-input v-model="DialogForm.isBooking" :disabled="dialogInputDisabled"  style="width: 240px;" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预约期限" prop="deadline">
              <el-date-picker v-model="DialogForm.deadline" type="datetime" :disabled="dialogInputDisabled"  style="width: 240px;" clearable></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="车位号" prop="parkingPlaceId">
              <el-input v-model="DialogForm.parkingPlaceId" :disabled="dialogInputDisabled"  style="width: 240px;" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="进场时间" prop="enterTime">
              <el-date-picker v-model="DialogForm.enterTime" type="datetime" :disabled="dialogInputDisabled"  style="width: 240px;" placeholder="--"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="离场时间" prop="leaveTime">
              <el-date-picker v-model="DialogForm.leaveTime" type="datetime" :disabled="dialogInputDisabled"  style="width: 240px;" placeholder="--"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="停车时长" prop="parkTime">
              <el-input v-model="DialogForm.parkTime" :disabled="dialogInputDisabled"  style="width: 240px;" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="停车费用" prop="cost">
              <el-input v-model="DialogForm.cost" :disabled="dialogInputDisabled"  style="width: 240px;" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="功能存储" class="hiddenBox">
              <el-input v-model="DialogForm.Id" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span='24' :class="{hiddenBox: isHidden}">
            <el-form-item class="dialogbtn">
              <el-button @click="handleClose" size="small">取消</el-button>
              <el-button type="primary" @click="onSubmit('DialogForm')" size="small">保存</el-button>
            </el-form-item>
          </el-col>
        </el-form>
        </el-dialog>
  
          <el-row>
              <el-col :span="24">
                <el-menu class="el-menu-demo" mode="horizontal" id="topnav"
                background-color="#001529" text-color="#fff" active-text-color="#fff">
                  <span class="navtitle">智能停车场后台管理系统</span>
                  <div class="loginuserbox">
                    <el-submenu index="" >
                      <template slot="title">欢迎用户，{{ LoginUser.UserAccount }}</template>
                    <el-menu-item style="text-align: center;" @click="outLogin">退出登录</el-menu-item>
                    </el-submenu>
                 </div>
                  
                </el-menu>
              </el-col>
          </el-row>
          
        <el-container>
          <UserNavMenu />
        <el-container>
                <el-header >
                  <el-form :model="queryForm" label-width="80px" size="small" stripe>
                    <div class="formtitle">
                      <span class="formtitletext">车辆搜索</span>
                    </div>
                    <el-row>
                      <el-col :span="6">
                        <el-form-item label="车牌号">
                          <el-input v-model="queryForm.carId" clearable style="width:130"></el-input>
                        </el-form-item>
                      </el-col>
                      <el-col :span="6">
                        <el-form-item label="是否预约" clearable>
                          <el-select v-model="queryForm.isBooking" clearable>
                            <el-option label="已预约" value="1"></el-option>
                            <el-option label="未预约" value="0"></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col :span="8">
                        <el-form-item class="navbtn">
                          <el-button type="primary" @click="onSearch" icon="el-icon-search">查询</el-button>
                          <!-- <el-button type="primary" @click="addDialog" icon="el-icon-circle-plus-outline">新增</el-button> -->
                        </el-form-item>
                      </el-col>
                    </el-row>
                  </el-form>
                </el-header>
                
                <el-main>
                  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" borderhighlight-current-row border
                  :header-cell-style="{background:'#eef1f6',color:'#606266'}" height="440"
                          :default-sort="{prop: 'enterTime', order: 'descending'}">
                    <el-table-column  type="index"  label="序号" width="50"></el-table-column>
                    <el-table-column prop="carId" label="车牌号" width="100">
                      <template slot-scope="scope">
                        <el-link type="primary" @click="showDialog(scope.row)">
                          {{scope.row.carId}}
                        </el-link>
                      </template>
                    </el-table-column>
                    <el-table-column prop="isBooking" label="是否预约" width="120"></el-table-column>
                    <el-table-column prop="deadline" label="预约期限" width="170"></el-table-column>
                    <el-table-column prop="parkingPlaceId" label="车位号" width="120"></el-table-column>
                    <el-table-column prop="enterTime" label="进场时间" width="220" sortable></el-table-column>
                    <el-table-column prop="leaveTime" label="离场时间" width="220"></el-table-column>
                    <el-table-column prop="parkTime" label="停车时长(h)" width="140"></el-table-column>
                    <el-table-column prop="cost" label="停车费用(元)" width="140"></el-table-column>
                    
                  </el-table>
                </el-main>
  
                <el-footer style="text-align: right; font-size: 12px">
                  <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-sizes="[1, 5, 10, 20, 50, 100]" :page-size="pageSize"
                    layout="total,sizes, prev, pager, next, jumper" :total="totalItems">
                  </el-pagination>
                </el-footer>
          </el-container>
    </el-container>
    </div>
      
      
  </template>
  
  <script>
  // @ is an alias to /src
  import UserNavMenu from '@/components/UserNavMenu.vue';
  import { mapState,mapMutations } from 'vuex';
  
  export default {
    name: 'UserCarView',
    components: {
        UserNavMenu
    },
    data () {
      return {
        LoginUser:{
            UserAccount:'',
            UserRole:''
        },
        //表单名称
        dialogName: '',
        //表单是否显示（默认不显示）
        dialogVisible: false,
        //表单内组件是否可用（默认可用）
        dialogInputDisabled: false,
        //表单按钮是否显示（默认显示）
        isHidden: false,
        //提交表单
        DialogForm: {
          Id:'',
          carId:'',
          isBooking:'',
          deadline:'',
          parkingPlaceId:'',
          enterTime:'',
          leaveTime:'',
          parkTime:'',
          cost:''
        },
        //弹窗表单验证规则
        rules:{
  
        },
        //查询表单数据
        queryForm: {
          carId:'',
          isBooking:''
        },
        tableData: [],
          //当前页码
          currentPage: 1,
          //页条数
          pageSize: 10,
          //总数据条数
          totalItems: 0,
  
      }  
    },
    created(){
        this.getLoginUser();
        this.getDataList();
    },
    computed:{
      ...mapState({
        userInfo:state=> state.user.userInfo
      })
    },
    methods:{
      ...mapMutations(['OUT_LOGIN']),
    //获取登录用户状态
    getLoginUser(){
        var loginuser = JSON.parse(localStorage.getItem("LoginUserInfo"));
        this.LoginUser.UserAccount = loginuser.UserAccount;
        this.LoginUser.UserRole = loginuser.UserRole;
    },
      //退出登录
      outLogin(){
        this.OUT_LOGIN();
        this.$router.push({ path: '/'});
      },
      //获取数据列表
      getDataList(){
        this.$http.post(
          'http://localhost:8899/car/getlist',
          this.queryForm
        ).then((res)=>{
          console.log(res.data.data.list);
          var oblist = res.data.data.list;
          for(let i = 0;i<oblist.length;i++){
            if(oblist[i].isBooking == 1){
              oblist[i].isBooking = '已预约';
            }else{
              oblist[i].isBooking = '未预约';
            }
            oblist[i].deadline = this.handleNullDateTime(oblist[i].deadline);
            oblist[i].enterTime = this.handleNullDateTime(oblist[i].leaveTime);
            oblist[i].leaveTime = this.handleNullDateTime(oblist[i].leaveTime);
          }
          this.tableData = oblist;
          this.totalItems = oblist.length;
        });
      },
      handleNullDateTime(datetime){
        if(datetime == null){
          datetime = '--'
        }
        else{
          datetime = this.handleDateTimeFormat(datetime);
        }
        return datetime;
      },
      //查询
      onSearch(){
        this.getDataList();
      },
      handleSizeChange(val) {
        //选择分页条数时改变分页
        this.pageSize = val;
      },
      handleCurrentChange(val) {
        this.currentPage = val;
      },
      //打开新增弹窗,初始化数据
      addDialog() {
          this.dialogName = "新申请",
          this.dialogVisible = true
      },
       //编辑弹窗
       onEdit(row) {
        this.setDialogData(row)
        this.dialogName = "编辑车辆信息"
        this.dialogVisible = true
      },
      //删除
      onDelete(row){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete('http://localhost:8899/user/delete',{
              params:{
                Id: row.Id
              }
              }).then((res)=>{
                this.getDataList();
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
              });    
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });          
          }); 
        },
        DialogForm: {
          Id:'',
          licensePlateId:'',
          enterTime:'',
          leaveTime:'',
          cost:''
        },
        //打开弹窗时，将数据填入弹窗
        setDialogData(row) {
          this.DialogForm.Id = row.Id
          this.DialogForm.carId = row.carId
          this.DialogForm.isBooking = row.isBooking
          this.DialogForm.deadline = row.deadline
          this.DialogForm.parkingPlaceId = row.parkingPlaceId
          this.DialogForm.enterTime = row.enterTime
          this.DialogForm.leaveTime = row.leaveTime
          this.DialogForm.parkTime = row.parkTime
          this.DialogForm.cost = row.cost
        },
        //查看弹窗
        showDialog(row) {
          this.setDialogData(row)
          this.dialogName = "车辆停车明细"
          this.dialogVisible = true
          this.dialogInputDisabled = true
          this.isHidden = true
        },
         //弹窗关闭时清空输入框
        handleClose() {
          this.DialogForm.Id = ''
          this.DialogForm.licensePlateId = ''
          this.DialogForm.parkingPlaceId = ''
          this.DialogForm.enterTime = ''
          this.DialogForm.leaveTime = ''
          this.DialogForm.cost = ''
          this.dialogVisible = false
          this.dialogInputDisabled = false
          this.isHidden = false
        },
        //弹窗表单提交
      onSubmit(){
        // this.DialogForm.CreateTime = this.handleDateTimeFormat(this.DialogForm.CreateTime);
        this.DialogForm.enterTime = this.handleFromNullDateTime(this.DialogForm.enterTime);
        this.DialogForm.leaveTime = this.handleFromNullDateTime(this.DialogForm.leaveTime);
        this.DialogForm.deadline = this.handleFromNullDateTime(this.DialogForm.deadline);
        if(this.DialogForm.isBooking == '已预约'){
          this.DialogForm.isBooking = 1;
        }else{
          this.DialogForm.isBooking = 0;
        }
        this.$http.post(
          'http://localhost:8899/car/save',
          this.DialogForm,
          "application/json;charset:utf-8"
        ).then((res)=>{
          this.$message({
              type: 'success',
              message: '保存成功!'
            });
          this.dialogVisible = false;
          this.getDataList();
        })
      },
      handleFromNullDateTime(datetime){
        if(datetime == '--')
          return null;
        else
          return this.handleDateTimeFormat(datetime);
      },
       //处理日期格式
       handleDateFormat(datet) {
        var date = new Date(datet);
        let Y = date.getFullYear(),
          M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1),
          D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate())
        return Y + '-' + M + '-' + D;
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
      border: 1px solid black;
    }
    body{
      /* margin: 0; */
    }
    /* .el-dialog{
      height: 45%;
    } */
    .navtitle{
      /* border: 1px solid white; */
      color: #fff;
      font-size: 26px;
      font-weight: 800;
      float: left;
      text-align: center;
      height: 50px;
      margin-top: 10px;
      margin-left: 20px;
    }
    #topnav .el-submenu .is-opened>.el-submenu__title .el-submenu__icon-arrow{
      display: none; 
    }
    #topnav .el-submenu >.el-submenu__title .el-submenu__icon-arrow{
      display: none;
    }
    #topnav > .el-submenu__title .el-menu-item{
      background-color: #fff !important;
      text-align: center;
    }
    .el-menu-item{
      background-color: #fff;
      text-decoration-color: #000;
    } 
  
    .loginuserbox{
      float: right;
      margin-right: 50px;
      color: #fff;
    }
    .el-form{
      border: 2px solid #EEF1F6;
      border-radius: 2px;
      margin-top: 10px;
      background-color: #fff;
    }
    .formtitle{
      text-align: left;
      font-size: 12px;
      width:100%;
      height:30px;
    }
    .formtitletext{
      font-size: 15px;
      font-weight: 800;
    }
    .el-main{
      margin-top: 60px;
      height: max-content;
      background-color: white;
    }
    .el-footer{
        background-color: white;
    }
  </style>