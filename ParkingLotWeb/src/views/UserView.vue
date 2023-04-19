<template>
  <div class="mainbox">

    <el-dialog :title="dialogName" :visible.sync="dialogVisible" width="70%"  close-on-click-modal 
		destroy-on-close @close="handleClose" user>
      <el-form :model="DialogForm"  label-position size="medium" :rules="rules" ref="DialogForm" class="dialogform">
        <el-col :span="12">
          <el-form-item label="工号" prop="StaffId">
            <el-input v-model="DialogForm.StaffId" :disabled="dialogInputDisabled" style="width: 400px;" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户账号" prop="userAccount">
            <el-input v-model="DialogForm.UserAccount" :disabled="true" style="width: 400px;" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="密码" prop="UserPassword">
            <el-input type="password" v-model="DialogForm.UserPassword" clearable style="width:400px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="权限" prop="userRole">
            <el-select v-model="DialogForm.UserRole" placeholder="请选择" style="width:400px">
              <el-option label="管理员" value="0"></el-option>
              <el-option label="普通用户" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker type="date"  v-model="DialogForm.CreateTime" disabled="true" style="width:400px" clearable>
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="功能存储" class="hiddenBox">
            <el-input v-model="DialogForm.ID" clearable></el-input>
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
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" id="topnav"
            background-color="#001529" text-color="#fff" active-text-color="#fff">
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
                  <div class="formtitle ">
                    <span class="formtitletext">用户搜索</span>
                  </div>
                  <el-row>
                    <el-col :span="5">
                      <el-form-item label="账号">
                        <el-input v-model="queryForm.UserAccount" clearable style="width:100"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="5">
                      <el-form-item label="工号" >
                        <el-input v-model="queryForm.StaffId" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="5">
                      <el-form-item label="权限" clearable>
                        <el-select v-model="queryForm.UserRole" style="width:233px" clearable>
                          <el-option label="管理员" value="0"></el-option>
                          <el-option label="普通用户" value="1"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="9">
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
                  <el-table-column type="index"  label="序号" width="70"></el-table-column>
                  <el-table-column prop="UserAccount" label="用户账号" width="150"></el-table-column>
                  <el-table-column prop="StaffId" label="工号" width="150"></el-table-column>
                  <el-table-column prop="UserRole" label="用户权限" width="120"></el-table-column>
                  <el-table-column prop="CreateTime" label="创建时间" width="200"></el-table-column>
                  <el-table-column prop="updateTime" label="更新时间" width="200"></el-table-column>
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
import NavMenu from '@/components/NavMenu.vue';
import { mapState,mapMutations } from 'vuex';

export default {
  name: 'UserView',
  components: {
    NavMenu
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
        ID: '',
        StaffId:'',
        UserAccount:'',
        UserPassword:'',
        UserRole:'',
        CreateTime:'',
        },
      //弹窗表单验证规则
      rules:{

        UserPassword:[
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          UserRole:[
          { required: true, message: '请输入权限', trigger: 'blur' }
          ]
      },
      //查询表单数据
      queryForm: {
        UserAccount: '',
        StaffId: '',
        UserRole: ''
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
        'http://localhost:8899/user/getlist',
        this.queryForm
      ).then((res)=>{
        console.log(res);
        var oblist = res.data.data.list;
        for(let i = 0;i<oblist.length;i++){
          oblist[i].CreateTime = this.handleDateFormat(oblist[i].CreateTime);
          oblist[i].updateTime = this.handleDateFormat(oblist[i].updateTime);
          if(oblist[i].UserRole == 0){
            oblist[i].UserRole = "管理员"
          }else{
            oblist[i].UserRole = "普通用户"
          }
        }
        this.tableData = oblist;
        this.totalItems = oblist.length;
      });
    },
    //查询
    onSearch(){
      this.getDataList();
    },
    //编辑弹窗
    onEdit(row) {
      this.setDialogData(row)
      this.dialogName = "编辑"
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
    handleSizeChange(val) {
      //选择分页条数时改变分页
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //打开新增弹窗,初始化数据
    addDialog() {
        this.dialogName = "创建用户",
        this.dialogVisible = true
    },
    //打开弹窗时，将数据填入弹窗
    setDialogData(row) {
      this.DialogForm.ID = row.ID
      this.DialogForm.StaffId = row.StaffId
      this.DialogForm.UserAccount = row.userAccount
      this.DialogForm.UserRole = row.UserRole
      this.DialogForm.CreateTime = row.createTime
    },
    //查看弹窗
    showDialog(row) {
      this.setDialogData(row)
      this.dialogName = "员工明细"
      this.dialogVisible = true
      this.dialogInputDisabled = true
      this.isHidden = true
    },
    
    //弹窗关闭时清空输入框
    handleClose() {
      this.DialogForm.ID = ''
      this.DialogForm.StaffId = ''
      this.DialogForm.UserAccount = ''
      this.DialogForm.UserRole = ''
      this.DialogForm.CreateTime = ''
      this.dialogVisible = false
      this.dialogInputDisabled = false
      this.isHidden = false
    },
    //弹窗表单提交
    onSubmit(){
      if(this.DialogForm.createTime != ""){
        this.DialogForm.createTime = this.handleDateTimeFormat(this.DialogForm.createTime);
      }
      this.$http.post(
        'http://localhost:8899/user/register',
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
.mainbox{
  /* background-color: #EEF1F6; */
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
.el-container .el-container{
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