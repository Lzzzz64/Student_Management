import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/student',
    name: 'student',
    component: () => import("../views/vues/StudentView.vue")
  },
  {
    path: '/course',
    name: 'course',
    component: () => import("../views/vues/CourseView.vue")
  },
  {
    path: '/',
    name: 'login',
    component: () => import("../views/vues/LoginView.vue")
  },
  {
    path: '/teacher',
    name: 'teacher',
    component: () => import("../views/vues/TeacherView.vue")
  },
  {
    path: '/dormitory',
    name: 'dormitory',
    component: () => import("../views/vues/DormitoryView.vue")
  }
]

const router = new VueRouter({
  mode:"history",
  routes
})

export default router
