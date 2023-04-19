// 项目的入口文件，主要用来初始化vue实例并使用需要的插件
import App from './App'
import store from './store'
import mmap from '@/common/mapfun.js'

// #ifndef VUE3
import Vue from 'vue'
Vue.prototype.$mmap = mmap
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App,
	store
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
Vue.prototype.$mmap = mmap
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif