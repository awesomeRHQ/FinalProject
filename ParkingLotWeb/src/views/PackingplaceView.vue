<template>
    <div class="mainbox">

      <el-dialog :title="dialogName" :visible.sync="dialogVisible" width="70%"  close-on-click-modal 
		destroy-on-close @close="handleClose" pp>
      <el-form :model="DialogForm" label-width="auto" label-position size="medium" :rules="rules" ref="DialogForm" class="dialogform">
        <el-col :span="8">
          <el-form-item label="车位号" prop="parkingPlaceId">
            <el-input v-model="DialogForm.ParkingPlaceId" :disabled="dialogInputDisabled" style="width: 250PX;" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="坐标(纬度)" prop="latitude">
            <el-input v-model="DialogForm.Latitude" :disabled="dialogInputDisabled" style="width: 250PX;" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="坐标(经度)" prop="longitude">
            <el-input v-model="DialogForm.Longitude" :disabled="dialogInputDisabled" style="width: 250PX;" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否空闲" prop="isParking">
            <el-select v-model="DialogForm.IsParking" placeholder="请选择" :disabled="dialogInputDisabled" style="width: 250PX;">
              <el-option label="空闲" value="0"></el-option>
              <el-option label="正在停放" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="累计停放" prop="totalCars">
            <el-input v-model="DialogForm.TotalCars" :disabled="dialogInputDisabled" clearable style="width:250px" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="累计收费" prop="totalCost">
            <el-input v-model="DialogForm.TotalCost" :disabled="dialogInputDisabled" clearable style="width:250px"></el-input>
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
                    <span class="formtitletext">车位搜索</span>
                  </div>
                  <el-row>
                    <el-col :span="4">
                      <el-form-item label="车位号">
                        <el-input v-model="queryForm.PackingplaceId" clearable style="width:100"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="是否空闲" clearable>
                        <el-select v-model="queryForm.IsParking" style="width:233px">
                          <el-option label="空闲" value="0"></el-option>
                          <el-option label="正在使用" value="1"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="16">
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
        				:default-sort="{prop: 'createTime', order: 'descending'}">
                  <el-table-column type="index" label="序号" width="70"></el-table-column>
                  <el-table-column prop="ParkingPlaceId" label="车位号" width="">
                    <template slot-scope="scope">
                      <el-link type="primary" @click="showDialog(scope.row)">
                        {{scope.row.ParkingPlaceId}}
                      </el-link>
                    </template>
                  </el-table-column>
                  <el-table-column prop="Latitude" label="坐标(纬度)" width=""></el-table-column>
                  <el-table-column prop="Longitude" label="坐标(经度)" width=""></el-table-column>
                  <el-table-column prop="IsParking" label="是否空闲" width=""></el-table-column>
                  <el-table-column prop="TotalCost" label="累计收费" width=""></el-table-column>
                  <el-table-column prop="TotalCars" label="累计停放" width=""></el-table-column>
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
  name: 'PackingplaceView',
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
        ParkingPlaceId:'',
        Coordinate:'',
        IsParking:'',
        TotalCars:'',
        TotalCost:''
        },
      //弹窗表单验证规则
      rules:{
        
      },
      //查询表单数据
      queryForm: {
        PackingplaceId:'',
        IsParking: ''
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
        'http://localhost:8899/parkingplace/getlist',
        this.queryForm
      ).then((res)=>{
        console.log(res.data.data.list);
        var oblist = res.data.data.list;
        for(let i = 0;i<oblist.length;i++){
          if(oblist[i].IsParking == 0){
            oblist[i].IsParking = "空闲"
          }else{
            oblist[i].IsParking = "正在使用"
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
      this.dialogName = "车位信息编辑"
      this.dialogVisible = true
    },
    //删除
    onDelete(row){
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.delete('http://localhost:8899/parkingplace/delete',{
            params:{
              Id: row.Id
            },
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
        this.dialogName = "新建",
        this.dialogVisible = true
    },
    //打开弹窗时，将数据填入弹窗
    setDialogData(row) {
      this.DialogForm.ID = row.ID
      this.DialogForm.ParkingPlaceId = row.ParkingPlaceId
      this.DialogForm.Latitude = row.Latitude
      this.DialogForm.Longitude = row.Longitude
      this.DialogForm.Coordinate = row.Coordinate
      this.DialogForm.IsParking = row.IsParking
      this.DialogForm.TotalCars = row.TotalCars
      this.DialogForm.TotalCost = row.TotalCost
    },
    //查看弹窗
    showDialog(row) {
      this.setDialogData(row)
      this.dialogName = "车位明细"
      this.dialogVisible = true
      this.dialogInputDisabled = true
      this.isHidden = true
    },
    
    //弹窗关闭时清空输入框
    handleClose() {
      this.DialogForm.ID = ''
      this.DialogForm.ParkingPlaceId = ''
      this.DialogForm.Latitude = ''
      this.DialogForm.Longitude = ''
      this.DialogForm.IsParking = ''
      this.DialogForm.TotalCars = ''
      this.DialogForm.TotalCost = ''
      this.dialogVisible = false
      this.dialogInputDisabled = false
      this.isHidden = false
    },
    //弹窗表单提交
    onSubmit(){
      this.$http.post(
        'http://localhost:8899/parkingplace/save',
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
/* .el-dialog{
  height: 40%;
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
.dialogbtn{
  margin-top: 1px;
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