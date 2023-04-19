import {
    USER_LOGIN,
    INIT_USER,
    OUT_LOGIN
} from './mutations-type'

export default {
    state: {
        loginStatus: false, //登录状态
        token: null, //token
        userInfo: {}//用户信息
    },
    getters: {
    },
    mutations: {
        //设置
        [USER_LOGIN](state, user) {
            state.loginStatus = true;
            state.userInfo = user;
            // userInfo["UserAccount"] = user.UserAccount;
            // userInfo["StaffId"] = user.StaffId;
            // userInfo["UserRole"] = user.UserRole;
            //持久化存储 => 本地存储
            localStorage.setItem("LoginUserInfo", JSON.stringify(user));
        },
        //读取
        [INIT_USER]() {
            let loginUser = JSON.parse(localStorage.getItem("LoginUserInfo"));
            if (loginUser) {
                state.loginStatus = true;
                state.userInfo = user;
            }
        },
        [OUT_LOGIN](state) {
            state.loginStatus = false;
            state.userInfo = {};
            localStorage.removeItem("LoginUserInfo");
        }
    },
    actions: {
    },
    modules: {
    }
}