import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import customer from './modules/customer.js'
import userlocation from './modules/getLocation.js'

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    customer,
	userlocation
  }
})