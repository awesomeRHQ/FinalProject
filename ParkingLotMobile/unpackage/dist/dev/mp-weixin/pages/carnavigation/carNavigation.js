(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["pages/carnavigation/carNavigation"],{

/***/ 57:
/*!*************************************************************************************************!*\
  !*** E:/finalproject/ParkingLotMobile/main.js?{"page":"pages%2Fcarnavigation%2FcarNavigation"} ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(wx, createPage) {

var _interopRequireDefault = __webpack_require__(/*! @babel/runtime/helpers/interopRequireDefault */ 4);
__webpack_require__(/*! uni-pages */ 26);
var _vue = _interopRequireDefault(__webpack_require__(/*! vue */ 25));
var _carNavigation = _interopRequireDefault(__webpack_require__(/*! ./pages/carnavigation/carNavigation.vue */ 58));
// @ts-ignore
wx.__webpack_require_UNI_MP_PLUGIN__ = __webpack_require__;
createPage(_carNavigation.default);
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/wx.js */ 1)["default"], __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ 2)["createPage"]))

/***/ }),

/***/ 58:
/*!******************************************************************************!*\
  !*** E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue ***!
  \******************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./carNavigation.vue?vue&type=template&id=78181490& */ 59);
/* harmony import */ var _carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./carNavigation.vue?vue&type=script&lang=js& */ 61);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));
/* harmony import */ var _carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./carNavigation.vue?vue&type=style&index=0&lang=css& */ 63);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js */ 32);

var renderjs





/* normalize component */

var component = Object(_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_3__["default"])(
  _carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__["default"],
  _carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["render"],
  _carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"],
  false,
  null,
  null,
  null,
  false,
  _carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["components"],
  renderjs
)

component.options.__file = "pages/carnavigation/carNavigation.vue"
/* harmony default export */ __webpack_exports__["default"] = (component.exports);

/***/ }),

/***/ 59:
/*!*************************************************************************************************************!*\
  !*** E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=template&id=78181490& ***!
  \*************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./carNavigation.vue?vue&type=template&id=78181490& */ 60);
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["recyclableRender"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "components", function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_template_id_78181490___WEBPACK_IMPORTED_MODULE_0__["components"]; });



/***/ }),

/***/ 60:
/*!*************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=template&id=78181490& ***!
  \*************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "render", function() { return render; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return staticRenderFns; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return recyclableRender; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "components", function() { return components; });
var components
try {
  components = {
    uniPopup: function () {
      return __webpack_require__.e(/*! import() | uni_modules/uni-popup/components/uni-popup/uni-popup */ "uni_modules/uni-popup/components/uni-popup/uni-popup").then(__webpack_require__.bind(null, /*! @/uni_modules/uni-popup/components/uni-popup/uni-popup.vue */ 90))
    },
    uniPopupMessage: function () {
      return Promise.all(/*! import() | uni_modules/uni-popup/components/uni-popup-message/uni-popup-message */[__webpack_require__.e("common/vendor"), __webpack_require__.e("uni_modules/uni-popup/components/uni-popup-message/uni-popup-message")]).then(__webpack_require__.bind(null, /*! @/uni_modules/uni-popup/components/uni-popup-message/uni-popup-message.vue */ 117))
    },
  }
} catch (e) {
  if (
    e.message.indexOf("Cannot find module") !== -1 &&
    e.message.indexOf(".vue") !== -1
  ) {
    console.error(e.message)
    console.error("1. 排查组件名称拼写是否正确")
    console.error(
      "2. 排查组件是否符合 easycom 规范，文档：https://uniapp.dcloud.net.cn/collocation/pages?id=easycom"
    )
    console.error(
      "3. 若组件不符合 easycom 规范，需手动引入，并在 components 中注册该组件"
    )
  } else {
    throw e
  }
}
var render = function () {
  var _vm = this
  var _h = _vm.$createElement
  var _c = _vm._self._c || _h
}
var recyclableRender = false
var staticRenderFns = []
render._withStripped = true



/***/ }),

/***/ 61:
/*!*******************************************************************************************************!*\
  !*** E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=script&lang=js& ***!
  \*******************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--13-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./carNavigation.vue?vue&type=script&lang=js& */ 62);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_13_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ 62:
/*!**************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--13-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=script&lang=js& ***!
  \**************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(uni) {

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;
var _vuex = __webpack_require__(/*! vuex */ 34);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

// 引入SDK核心类，js文件根据自己业务，位置可自行放置
var QQMapWX = __webpack_require__(/*! @/utils/qqmap-wx-jssdk.min.js */ 38);
var qqmapsdk = new QQMapWX({
  key: 'U67BZ-GJTEX-V464V-ZC7IB-J23R7-V2F4Y'
});
var _default = {
  data: function data() {
    return {
      //消息盒子类型
      msgType: '',
      //消息盒子内容
      messageText: '',
      //地图初始化中心
      doorLatitude: 22.361735,
      doorLongitude: 113.550950,
      //大东小西
      latitude: '',
      longitude: '',
      markers: [],
      polyline: [],
      windowHeight: '',
      //定时器
      timer: null,
      lc: null
    };
  },
  onReady: function onReady() {
    //创建地图画布
    this.mapContext = uni.createMapContext('mymap', this);
  },
  onLoad: function onLoad() {},
  onShow: function onShow() {
    var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
    var enterFlag = uni.getStorageSync("enterFlag");
    if (loginCustomer.openId == null | loginCustomer == null) {
      //未登录
      this.messageBox('error', '请先登录！');
      setTimeout(function () {
        uni.switchTab({
          url: "/pages/index/index"
        });
      }, 1500);
    } else if (loginCustomer.carId == null | loginCustomer.carId == '') {
      //未登记车牌信息
      this.messageBox('error', '请绑定车牌！');
      setTimeout(function () {
        uni.switchTab({
          url: "/pages/userpage/userPage"
        });
      }, 1500);
    } else if (loginCustomer.enterTime == null && enterFlag == '') {
      this.messageBox('error', '您未入场！');
      setTimeout(function () {
        uni.switchTab({
          url: "/pages/index/index"
        });
      }, 1500);
    } else if (loginCustomer.leaveTime != null) {
      this.messageBox('error', '您已离场！');
      setTimeout(function () {
        uni.switchTab({
          url: "/pages/index/index"
        });
      }, 1500);
    } else {
      console.log("车位导航：车位导航前置条件满足");
      var that = this;
      //获取用户授权后定位位置信息
      uni.getSetting({
        success: function success(res) {
          // 如果没有授权
          if (!res.authSetting['scope.userLocation']) {
            // 则拉起授权窗口
            uni.authorize({
              scope: 'scope.userLocation',
              success: function success() {
                console.log("车位导航：用户已授权（授权窗口），开始执行程序");
                //进入程序主方法
                that.main();
              },
              fail: function fail(error) {
                //点击了拒绝授权后--就一直会进入失败回调函数--此时就可以在这里重新拉起授权窗口
                console.log('拒绝授权', error);
                //提示
                uni.showModal({
                  title: '提示',
                  content: '若点击不授权，将无法使用位置功能',
                  cancelText: '不授权',
                  cancelColor: '#999',
                  confirmText: '授权',
                  confirmColor: '#f94218',
                  success: function success(res) {
                    if (res.confirm) {
                      // 选择弹框内授权
                      uni.openSetting({
                        success: function success(res) {
                          console.log(res.authSetting);
                        }
                      });
                    } else if (res.cancel) {
                      // 选择弹框内 不授权
                      console.log('用户点击不授权');
                    }
                  }
                });
              }
            });
          }
          //若有授权，执行业务方法
          else {
            console.log("车位导航：用户已授权（二次），开始执行程序");
            that.main();
          }
        },
        fail: function fail(err) {
          console.log("授权获取错误：", err);
        }
      });
    }
  },
  //监听页面隐藏
  onHide: function onHide() {
    console.log(this.markers);
    //离开页面时清除计时器
    clearInterval(this.timer);
    this.timer = null;
    this.markers = [];
    console.log("车位导航：退出程序");
  },
  //监听页面卸载
  onUnload: function onUnload() {
    console.log(this.markers);
    //离开页面时清除计时器
    clearInterval(this.timer);
    this.timer = null;
    this.markers = [];
    console.log("车位导航：退出程序");
  },
  methods: {
    //登录消息盒子
    messageBox: function messageBox(msgType, messageText) {
      this.msgType = msgType;
      this.messageText = messageText;
      this.$refs.message.open();
    },
    //程序主方法
    main: function main() {
      var that = this;
      uni.getSystemInfo({
        success: function success(res) {
          that.windowHeight = res.windowHeight;
        }
      });
      var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
      this.lc = loginCustomer;
      that.MapNavigation();
      that.createMarkers();
      that.timer = setInterval(function () {
        that.MarkCustomerLocation();
        that.handleErrorPark();
      }, 2000);
    },
    //获取用户位置并标记
    MarkCustomerLocation: function MarkCustomerLocation() {
      var that = this;
      uni.getLocation({
        type: 'wgs84',
        // geocode: true , //设置该参数为true可直接获取经纬度及城市信息
        // isHighAccuracy:true,
        success: function success(res) {
          console.log("用户当前定位信息：", res);
          that.userLatitude = res.latitude;
          that.userLongitude = res.longitude;

          //测试
          that.userLatitude = that.lc.latitude;
          that.userLongitude = that.lc.longitude;
          if (that.markers.length == 0 || that.markers[that.markers.length - 1].id != 9990) {
            that.markers.push({
              id: 9990,
              width: 25,
              height: 25,
              longitude: that.userLongitude,
              latitude: that.userLatitude,
              iconPath: '/static/icon/user-navi.png'
            });
          } else {
            that.markers.pop();
            that.markers.push({
              id: 9990,
              width: 25,
              height: 25,
              longitude: that.userLongitude,
              latitude: that.userLatitude,
              iconPath: '/static/icon/user-navi.png'
            });
          }
        },
        fail: function fail(error) {
          console.log('失败', error);
        }
      });
    },
    //标记车位，并把路线规划出来
    MapNavigation: function MapNavigation() {
      var that = this;
      var loginCustomer = uni.getStorageSync("LoginCustomerInfo");
      var doorLocation = this.doorLatitude + "," + this.doorLongitude;
      var parkLocation = loginCustomer.latitude + "," + loginCustomer.longitude;
      //车位标记
      var userPLMarker = {
        id: 9999,
        width: 25,
        height: 25,
        longitude: loginCustomer.longitude,
        latitude: loginCustomer.latitude,
        iconPath: '/static/icon/userplace.png',
        callout: {
          content: "您的车位:" + loginCustomer.parkingPlaceId,
          fontSize: 14,
          //文本大小
          borderRadius: 15,
          //边框圆角
          borderWidth: '10',
          bgColor: '#1296db',
          //背景颜色
          display: 'ALWAYS' //常显
        }
      };

      this.markers.push(userPLMarker);
      qqmapsdk.direction({
        mode: 'driving',
        //导航方式，驾车
        from: doorLocation,
        //起点经纬度 '22.361735,113.550950'
        to: parkLocation,
        //终点经纬度
        success: function success(res) {
          // console.log("direction res",res)
          var ret = res;
          var coors = ret.result.routes[0].polyline,
            pl = [];
          //坐标解压（返回的点串坐标，通过前向差分进行压缩）
          var kr = 1000000;
          for (var i = 2; i < coors.length; i++) {
            coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
          }
          //将解压后的坐标放入点串数组pl中
          for (var i = 0; i < coors.length; i += 2) {
            pl.push({
              latitude: coors[i],
              longitude: coors[i + 1]
            });
          }
          //设置polyline属性，将路线显示出来,将解压坐标第一个数据作为起点
          that.latitude = pl[0].latitude, that.longitude = pl[0].longitude, that.polyline = [{
            points: pl,
            color: '#1296db',
            width: 4
          }];
        },
        fail: function fail(res) {
          console.log('err', res);
        }
      });
    },
    //标记车位
    createMarkers: function createMarkers() {
      var that = this;
      uni.request({
        url: "http://localhost:8899/parkingplace/getlist",
        method: 'POST',
        data: {},
        success: function success(res) {
          console.log(res.data.data.list);
          var logincustomer = uni.getStorageSync("LoginCustomerInfo");
          var places = res.data.data.list;
          for (var i = 0; i < places.length; i++) {
            if (places[i].ParkingPlaceId == logincustomer.parkingPlaceId) {
              // console.log("busy:",places[i].ParkingPlaceId)
              continue;
            }
            if (places[i].IsParking == 1) {
              var place = {
                id: places[i].Id,
                width: 25,
                height: 25,
                longitude: places[i].Longitude,
                latitude: places[i].Latitude,
                iconPath: '/static/icon/fullplace.png'
              };
            } else {
              var place = {
                id: places[i].Id,
                width: 25,
                height: 25,
                longitude: places[i].Longitude,
                latitude: places[i].Latitude,
                iconPath: '/static/icon/emptyplace.png'
              };
            }
            that.markers.push(place);
          }
        }
      });
    },
    handleErrorPark: function handleErrorPark() {
      if (Math.abs(this.lc.latitude - this.userLatitude) > 0.000020 && Math.abs(this.lc.longitude - this.userLongitude) > 0.000010) {
        this.messageBox("error", "请停放到系统指示的区域中！");
      } else if (Math.abs(this.lc.latitude - this.userLatitude) <= 0.000020 && Math.abs(this.lc.longitude - this.userLongitude) <= 0.000010) {
        this.messageBox("success", "您已到达指定位置，可以停车！");
      } else {}
    }
  }
};
exports.default = _default;
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ 2)["default"]))

/***/ }),

/***/ 63:
/*!***************************************************************************************************************!*\
  !*** E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=style&index=0&lang=css& ***!
  \***************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/mini-css-extract-plugin/dist/loader.js??ref--6-oneOf-1-0!./node_modules/css-loader/dist/cjs.js??ref--6-oneOf-1-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/stylePostLoader.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--6-oneOf-1-2!./node_modules/postcss-loader/src??ref--6-oneOf-1-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./carNavigation.vue?vue&type=style&index=0&lang=css& */ 64);
/* harmony import */ var _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_D_applicationDownload_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_carNavigation_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ 64:
/*!*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/mini-css-extract-plugin/dist/loader.js??ref--6-oneOf-1-0!./node_modules/css-loader/dist/cjs.js??ref--6-oneOf-1-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/stylePostLoader.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--6-oneOf-1-2!./node_modules/postcss-loader/src??ref--6-oneOf-1-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!E:/finalproject/ParkingLotMobile/pages/carnavigation/carNavigation.vue?vue&type=style&index=0&lang=css& ***!
  \*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

// extracted by mini-css-extract-plugin
    if(false) { var cssReload; }
  

/***/ })

},[[57,"common/runtime","common/vendor"]]]);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/carnavigation/carNavigation.js.map