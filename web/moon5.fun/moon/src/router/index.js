import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/components/Login.vue'
import Regist from '@/components/Regist.vue'
import Port from '@/components/Port.vue'
import IndexPage from '@/components/index.vue'
import MainLayout from '@/components/MainLayout.vue' // 导入布局组件
import file_upload_1 from '@/components/hark/file_upload/file_upload_1.vue'
import Hack from '@/components/hark/hark.vue'
const routes = [
  // 不需要导航栏的页面（直接渲染）
  { path: '', redirect: '/login' }, // 默认重定向到首页
  { path: '/login', component: Login },
  { path: '/regist', component: Regist },

  //关卡页面
  { path: '/hark/file_upload/file_upload_1', component:file_upload_1},
  
  // 需要导航栏的页面（嵌套在布局组件中）
  { 
    path: '/', 
    component: MainLayout,
    children: [
      { path: 'index', component: IndexPage },
      { path: 'port', component: Port },
      { path: 'hack/hack', component: Hack},
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
