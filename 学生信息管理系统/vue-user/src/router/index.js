import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/myInformation',
    name: 'myInformation',
    component: () => import("../views/vues/MyInformation.vue")
  },
  {
    path: '/chooseCourse',
    name: 'chooseCourse',
    component: () => import("../views/vues/ChooseCourse.vue")
  },
  {
    path: '/',
    name: 'login',
    component: () => import("../views/vues/LoginView.vue")
  },
  {
    path: '/chooseDormitory',
    name: 'chooseDormitory',
    component: () => import("../views/vues/ChooseDormitory.vue")
  },
  {
    path: '/myCourse',
    name: 'myCourse',
    component: () => import("../views/vues/MyCourse.vue")
  }
]

const router = new VueRouter({
  mode:"history",
  routes
})

export default router
