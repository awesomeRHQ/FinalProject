<template>
  <div>
    <el-dialog :title="dialogName" :visible.sync="dialogVisible" width="70%"   close-on-click-modal 
		destroy-on-close @close="handleClose" >
      <el-form :model="DialogForm" label-width="auto" label-position size="medium" :rules="sfrules" ref="DialogForm" class="dialogform">
        <el-col :span="16">
          <el-col :span="12">
            <el-form-item label="工号" prop="StaffId">
              <el-input v-model="DialogForm.StaffId" :disabled="true" clearable placeholder="自动生成"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="StaffName">
              <el-input v-model="DialogForm.StaffName" :disabled="dialogInputDisabled" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="13">
            <el-form-item label="身份证" prop="IDCard">
              <el-input v-model="DialogForm.IDCard" :disabled="dialogInputDisabled" style="width:265px" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="Gender">
              <el-input v-model="DialogForm.Gender" :disabled="dialogInputDisabled" placeholder="根据身份证号自动获取"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年龄" prop="Age">
              <el-input v-model="DialogForm.Age" :disabled="dialogInputDisabled" placeholder="根据身份证号自动获取"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="岗位" prop="Post">
              <el-input v-model="DialogForm.Post" :disabled="dialogInputDisabled" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职日期" prop="CreateTime">
              <el-date-picker type="date"  v-model="DialogForm.CreateTime" :disabled="dialogInputDisabled" style="width:265px
              " clearable>
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="功能存储" class="hiddenBox">
              <el-input v-model="DialogForm.ID" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-col>
        <el-col :span="8">
          <el-form-item label="照片" prop="Photo">
            <el-upload
              class="avatar-uploader"
              action=""
              :show-file-list="true"
              :on-change="UploadImage"
              :before-upload="beforeAvatarUpload"
              :disabled="isHidden">
              <img v-if="ImageUrl" :src="ImageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

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
                  <el-submenu index>
                    <template slot="title">欢迎，{{ LoginUser.UserAccount }}</template>
                    <el-menu-item style="text-align: center;" @click="outLogin">退出登录</el-menu-item>
                  </el-submenu>
               </div>
              </el-menu>
            </el-col>
        </el-row>

    <el-row>
    <el-container>
        <NavMenu />
        <el-container >
              <el-header >
                <el-form :model="queryForm" label-width="80px" size="small" stripe>
                  <div class="formtitle">
                    <span class="formtitletext">员工搜索</span>
                  </div>
                  <el-row>
                    <el-col :span="4">
                      <el-form-item label="姓名">
                        <el-input v-model="queryForm.staffName" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="工号">
                        <el-input v-model="queryForm.staffId" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="岗位" >
                        <el-input v-model="queryForm.post" clearable></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="性别" >
                        <el-select v-model="queryForm.gender" clearable>
                          <el-option label="男" value="男"></el-option>
                          <el-option label="女" value="女"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item class="navbtn">
                        <el-button type="primary" @click="onSearch" icon="el-icon-search">查询</el-button>
                        <el-button type="primary" @click="addDialog" icon="el-icon-circle-plus-outline">注册</el-button>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </el-header>

              <el-main>
                <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" borderhighlight-current-row border
                :header-cell-style="{background:'#eef1f6',color:'#606266'}" height="440"
        				:default-sort="{prop: 'createTime', order: 'descending'}">
                  <el-table-column type="index" prop="index" label="序号" width="50"></el-table-column>
                  <el-table-column prop="StaffName" label="姓名" width="120">
                    <template slot-scope="scope">
                      <el-link type="primary" @click="showDialog(scope.row)">
                        {{scope.row.StaffName}}
                      </el-link>
                    </template>
                  </el-table-column>
                  <el-table-column prop="StaffId" label="工号" width="150"></el-table-column>
                  <el-table-column prop="Gender" label="性别" width="90"></el-table-column>
                  <el-table-column prop="Age" label="年龄" width="90"></el-table-column>
                  <el-table-column prop="IDCard" label="身份证" width="200"></el-table-column>
                  <el-table-column prop="CreateTime" label="入职日期" width="200" sortable></el-table-column>
                  <el-table-column prop="Post" label="岗位" width="120"></el-table-column>
                  <el-table-column label="操作" >
                  <template slot-scope="scope">
                    <el-button @click="onEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="onDelete(scope.row)" type="text" size="small" style="color:red">删除</el-button>
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
</el-row>



</div>  
</template>

<script>
// @ is an alias to /src
import NavMenu from '@/components/NavMenu.vue'
import { assertLiteral } from '@babel/types';
import axios from 'axios';
import { mapState,mapMutations } from 'vuex';

export default {
  name: 'StaffView',
  components: {
    NavMenu
  },
  data () {
    var validateStaffName = (rule, value, callback) => {
        let containSpecial = RegExp(/[(\ )(\~)(\~)(\!)(\！)(\@)(\#)(\$)(\￥)(\%)(\^)(\……)(\&)(\*)(\()(\（)(\))(\）)(\-)(\_))(\——)(\+)(\=)(\[)(\【)(\])(\】)(\{)(\})(\|))(\、))(\)(\\)(\;)(\；)(\:)(\：)(\')(\‘)(\’)(\")(\“)(\”)(\,)(\，)(\.)(\。)(\/)(\《)(\<)(\>)(\》)(\?)(\？)(\)]+/);
        if (value === ''){
          callback(new Error('请输入姓名'));
        }else if (containSpecial.test(value)){
          callback(new Error("名称不能含有特殊字符"));
        }else{
            callback();
        }
      };
    var validateIDCard = (rule, value, callback) => {
        let containSpecial = RegExp(/[(\ )(\~)(\~)(\!)(\！)(\@)(\#)(\$)(\￥)(\%)(\^)(\……)(\&)(\*)(\()(\（)(\))(\）)(\-)(\_))(\——)(\+)(\=)(\[)(\【)(\])(\】)(\{)(\})(\|))(\、))(\)(\\)(\;)(\；)(\:)(\：)(\')(\‘)(\’)(\")(\“)(\”)(\,)(\，)(\.)(\。)(\/)(\《)(\<)(\>)(\》)(\?)(\？)(\)]+/);
        if (value === ''){
          callback(new Error('请输入身份证号'));
        }else if (value.length != 18){
          callback(new Error("请输入有效的18位身份证号"));
        }else if (containSpecial.test(value)){
          callback(new Error("身份证不能含有特殊字符"));
        }else{
          for(let i = 0; i<16;i++){
            
              if(isNaN(Number(value[i]))){
                callback(new Error("身份证格式错误"));
                console.log(Number(value[i]))
                break;
              }
          }
          console.log(Number(value[17]))
          if(isNaN(Number(value[17])) && value[17] != 'X')
              callback(new Error("身份证格式错误"));
          else
            callback();
        }
      };
    return{ 
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
      //头像路径
      ImageUrl:'', 
      //头像上传flag
      UploadFlag:'',
      //提交表单
      DialogForm: {
        ID: '',
        StaffId:'',
        StaffName:'',
        Gender:'',
        Age:'',
        IDCard:'',
        Photo:'',
        Post:'',
        CreateTime:''
      },
      //弹窗表单验证规则
      sfrules:{
        StaffName: [
            { required: true, validator: validateStaffName , trigger: 'blur' }
          ],
        IDCard: [
            { required: true, validator: validateIDCard, trigger: 'blur' }
          ],
        Post: [
            { required: true, message: '请输入岗位', trigger: 'blur' }
          ],
        CreateTime: [
            { required: true, message: '请选择入职日期', trigger: 'blur' }
          ],
      },
      //查询表单数据
      queryForm: {
        staffId: '',
        staffName: '',
        gender:'',
        post:''
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
        'http://localhost:8899/staff/getlist',
        this.queryForm
      ).then((res)=>{
        var oblist = res.data.data.list;
        console.log(oblist)
        for(let i = 0;i<oblist.length;i++){
          oblist[i].CreateTime = this.handleDateFormat(oblist[i].CreateTime);
        }
        this.tableData = oblist;
        this.totalItems = oblist.length;
      });
    },
    //查询
    onSearch(){
      console.log(JSON.stringify(this.queryForm));
      this.getDataList();
    },
    //编辑
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
          this.$http.delete('http://localhost:8899/staff/delete',{
            params:{
              Id: row.ID
            }
            }).then((res)=>{
              this.getDataList();
            });            
            this.$message({
                type: 'success',
                message: '删除成功!'
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
        this.dialogName = "新员工申请",
        this.dialogVisible = true
    },
    //打开弹窗时，将数据填入弹窗
    setDialogData(row) {
      this.DialogForm.ID = row.ID
      this.DialogForm.StaffId = row.StaffId
      this.DialogForm.StaffName = row.StaffName
      this.DialogForm.Gender = row.Gender
      this.DialogForm.Age = row.Age 
      this.DialogForm.IDCard = row.IDCard 
      this.DialogForm.Post = row.Post 
      this.DialogForm.Photo = row.Photo
      this.DialogForm.CreateTime = row.CreateTime
      this.ImageUrl = row.Photo
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
      this.DialogForm.Id = ''
      this.DialogForm.StaffId = ''
      this.DialogForm.StaffName = ''
      this.DialogForm.Gender = ''
      this.DialogForm.Age = ''
      this.DialogForm.IDCard = ''
      this.DialogForm.Photo = ''
      this.DialogForm.Post = ''
      this.DialogForm.CreateTime = ''
      this.ImageUrl = ''
      this.dialogVisible = false
      this.dialogInputDisabled = false
      this.isHidden = false
    },
    //弹窗表单提交
    onSubmit(){
      this.DialogForm.CreateTime = this.handleDateTimeFormat(this.DialogForm.CreateTime);
      console.log(this.DialogForm)
      this.$http.post(
        'http://localhost:8899/staff/save',
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
    //根据身份证号自动获取信息
    selectClient(){
        var IDCard = this.DialogForm.IDCard;
        if(this.DialogForm.IDCard.length == 18){
          this.DialogForm.Age = new Date().getFullYear() - Number(IDCard.slice(6,10));
          var g = IDCard.slice(16,17);
          if(g%2==0){
            this.DialogForm.Gender = "女";
          }else{
            this.DialogForm.Gender = "男";
          }
        }else{
          this.DialogForm.Age = '';
          this.DialogForm.Gender = '';
        }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
        this.UploadFlag = false;
      }
      else if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
        this.UploadFlag = false;
      }
      else{
        this.UploadFlag = true;
      }
      return isJPG && isLt2M;
    },
    UploadImage(file,filelist){
      if(this.UploadFlag != false){
      let fd = new FormData()
      fd.append('file', file.raw) //传给后台接收的名字 file
      axios.post(
        "http://localhost:8899/staff/upload",
        fd,
        {headers: {'Content-Type': 'multipart/form-data'}}
      ).then(res =>{
        console.log(res.data)
        this.ImageUrl = res.data; 
        this.DialogForm.Photo = res.data;
      })
      }else{
        console.log("取消上传")
      }
      
    }

  }
}
</script>

<style>
.test{
  border: 1px solid red !important
}
body{
  height: 100%;
  margin: 0;
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
.el-header .el-form{
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
  background-color: white;
}
.el-footer{
    background-color: white;
}

.el-dialog{
  height: 55%;
}
.dialogform{
  border: 1px solid black !important;
}
.el-form-item{
  margin-top: 10px;
}
.hiddenBox{
    display: none;
}
.dialogbtn{
    float: right;
    margin-top: -20px;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 210px;
    line-height: 210px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 210px;
    display: block;
  }
</style>