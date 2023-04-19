import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserView from '../views/UserView.vue'
import StaffView from '../views/StaffView.vue'
import CarView from '../views/CarView.vue'
import PackingplaceView from '../views/PackingplaceView.vue'
import CustomerView from '../views/CustomerView.vue'
import VipView from '../views/VipView.vue'
import UserCar from '../views/UserCarView.vue'
import UserParkingPlace from '../views/UserParkingPlaceView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/user',
    name: 'UserView',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/UserView.vue'], resolve)
  },
  {
    path: '/staff',
    name: 'StaffView',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/StaffView.vue'], resolve)
  },
  {
    path: '/car',
    name: 'CarView',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/CarView.vue'], resolve)
  },
  {
    path: '/packingplace',
    name: 'Packingplace',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/PackingplaceView.vue'], resolve)
  },
  {
    path: '/customer',
    name: 'Customer',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/CustomerView.vue'], resolve)
  },
  {
    path: '/vip',
    name: 'Vip',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/VipView.vue'], resolve)
  },
  {
    path: '/userparkingplace',
    name: 'UserParkingPlace',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/UserParkingPlaceView.vue'], resolve)
  },
  {
    path: '/usercar',
    name: 'UserCar',
    meta: {
      requireAuth: true
    },
    component: resolve => require(['../views/UserCarView.vue'], resolve)
  },
]

const router = new VueRouter({
  routes 
})

export default router
