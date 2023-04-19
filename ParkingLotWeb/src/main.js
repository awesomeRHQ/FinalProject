import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueSource from 'vue-source'
import axios from 'axios'
import { Notification } from 'element-ui'

Vue.use(VueSource)
Vue.use(ElementUI)
Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

//登录拦截
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {//requireAuth若为true则该路由需要判断是否登录
    if (localStorage.LoginUserInfo) {//判断当前的userName数据是否存在
      next();
    }
    else {
      next({//返回登录页面
        path: '/',
        query: { redirect: to.fullPath }
      })
      Notification.error({
        message: '请先登录!',
        duration: 1500,
        position: 'top-right',
        showClose: false
      });
      //ElementUI.Message.error({ message: '请先登录!' })
    }
  }
  else {
    next();
  }
})