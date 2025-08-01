import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/Login.vue'
import Test from '@/components/Test.vue'
import Regist from '@/components/Regist.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/test', component: Test },
  { path: '/regist', component: Regist}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
