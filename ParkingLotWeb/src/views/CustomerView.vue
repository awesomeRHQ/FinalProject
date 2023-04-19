<template>
    <div class="mainbox">

        <el-row>
            <el-col :span="24">
              <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" id="topnav"
              background-color="#001529" text-color="#fff" active-text-color="#fff"
                @select="handleSelect">
                <span class="navtitle">智能停车场后台管理系统</span>
                <div class="loginuserbox">
                  <el-submenu >
                    <template slot="title">欢迎用户，{{ LoginUser.UserAccount }}</template>
                    <el-menu-item style="text-align: center;" @click="outLogin">退出登录</el-menu-item>
                  </el-submenu>
               </div>
                
              </el-menu>
            </el-col>
        </el-row>
          
        <el-container>
          <NavMenu />
          <el-container>
                <el-header >
                  <el-form :model="queryForm" label-width="80px" size="small" stripe>
                    <div class="formtitle">
                      <span class="formtitletext">顾客搜索</span>
                    </div>
                    <el-row>
                      <el-col :span="6">
                        <el-form-item label="顾客">
                          <el-input v-model="queryForm.openId" clearable style="width:100"></el-input>
                        </el-form-item>
                      </el-col>
                      <el-col :span="6">
                        <el-form-item label="创建日期">
                          <el-date-picker v-model="queryForm.createTime" type="date" placeholder="选择日期" clearable></el-date-picker>
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item class="navbtn">
                          <el-button type="primary" @click="onSearch" icon="el-icon-search">查询</el-button>
                          <el-button type="primary" @click="addDialog" icon="el-icon-circle-plus-outline">新增</el-button>
                        </el-form-item>
                      </el-col>
                    </el-row>
                  </el-form>
                </el-header>
                
                <el-main>
                  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" borderhighlight-current-row border
                  :header-cell-style="{background:'#eef1f6',color:'#606266'}" height="440"
                          :default-sort="{prop: 'ApplicationDate', order: 'descending'}">
                    <el-table-column type="index" label="序号" width="70"></el-table-column>
                    <el-table-column prop="openId" label="顾客openId" ></el-table-column>
                    <el-table-column prop="createTime" label="创建时间" ></el-table-column>
                    <el-table-column label="操作" >
                    <template slot-scope="scope">
                      <el-button @click="onEdit(scope.row)" type="text" size="small">编辑
                      </el-button>
                      <el-button @click="onDelete(scope.row)" type="text" size="small" style="color:red">删除
                      </el-button>
                    </template>
                  </el-table-column>
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
  import NavMenu from '@/components/NavMenu.vue'
  import { mapState,mapMutations } from 'vuex';
  
  export default {
    name: 'CustomerView',
    components: {
      NavMenu
    },
    data () {
      return {
        LoginUser:{
        UserAccount:'',
        UserRole:''
      },
        //查询表单数据
        queryForm: {
          openId: '',
          createTime:''
        },
        tableData: [],
          //当前页码
          currentPage: 1,
          //页条数
          pageSize: 10,
          //总数据条数
          totalItems: '',
  
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
      this.LoginUser.UserAccount= loginuser.UserAccount;
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
        'http://localhost:8899/customer/getlist', 
        this.queryForm 
      ).then((res)=>{
        console.log(res);
        var oblist = res.data.data.list;
        for(let i = 0;i<oblist.length;i++){
          oblist[i].createTime = this.handleDateTimeFormat(oblist[i].createTime);
        }
        this.tableData = oblist;
        this.totalItems = oblist.length;
      });
    },
      //查询
    onSearch(){
      if(this.queryForm.createTime!=null){
        this.queryForm.createTime = this.handleDateFormat(this.queryForm.createTime);
      }
      this.getDataList();
    },
    //打开新增弹窗,初始化数据
    addDialog() {
        this.dialogName = "新申请",
        this.dialogVisible = true
    },
     //编辑弹窗
     onEdit(row) {
      this.setDialogData(row)
      this.dialogName = "编辑"
      this.dialogVisible = true
    },
    //删除
    onDelete(row){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.delete('http://localhost:8899/customer/delete',{
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
      // DialogForm: {
      //   Id:'',
      //   licensePlateId:'',
      //   enterTime:'',
      //   leaveTime:'',
      //   cost:''
      // },
      //打开弹窗时，将数据填入弹窗
      setDialogData(row) {
        this.DialogForm.Id = row.Id
        this.DialogForm.licensePlateId = row.licensePlateId
        this.DialogForm.parkingPlaceId = row.parkingPlaceId
        this.DialogForm.enterTime = row.enterTime
        this.DialogForm.leaveTime = row.leaveTime
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
      this.DialogForm.CreateTime = this.handleDateTimeFormat(this.DialogForm.CreateTime);
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
  .navbtn{
    float: right;
    margin-right: 100px;
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
    margin-top: 70px;
    height: max-content;
    background-color: white;
  }
  .el-footer{
      background-color: white;
  }
</style>