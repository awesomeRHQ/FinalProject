<template>

    <div>
        <el-row>
            <el-col :span="6">
                <el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
                    background-color="#FFFFFF" text-color="#262626" active-text-color="#309CFF"  router
                    @select="handleselect">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-user-solid"></i>
                            <span>员工管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/staff" >员工信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-truck"></i>
                            <span>车辆管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/car">入场车辆信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>车位管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/packingplace">停车场车位信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-s-ticket"></i>
                            <span>月卡管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/vip">月卡信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-s-custom"></i>
                            <span>顾客管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/customer">顾客信息</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>系统管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/user">用户管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                
                </el-menu>
            </el-col>
        </el-row>
    </div>
       
 
</template>

<script>
    export default {
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handleselect(key,keyPath){
                this.$router.push(key).catch(err => {});
            }
        }
    }
</script>

<style scoped>
.test{
    border: 1px solid black;
    height: auto;
    width: auto;
}

.el-menu{
    height: 91vh;
    font-weight: 400;
    text-align: left;
}
.el-menu .el-menu-item{
    font-weight: 400;
    text-align: left;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-menu .el-menu-item:hover{
    outline: 0 !important;
    color: #2E95FB !important;
    background-color: #fff !important;
}
.el-menu .el-submenu .el-menu-item.is-active{
    outline: 0 !important;
    background-color: #E6F7FF !important;
}
.el-submenu{
    height: auto;
}
.el-menu-item{
    height: auto;
}
</style>