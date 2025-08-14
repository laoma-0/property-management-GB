import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/store/userStore'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { requiresAuth: false }
  },
  {
    path: '/',
    name: 'Main',
    component: () => import('@/components/layout/MainLayout.vue'),
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/User.vue'),
        meta: { title: '用户管理' }
      },
      {
        path: 'property',
        name: 'Property',
        component: () => import('@/views/Property.vue'),
        meta: { title: '物业管理' }
      },
      {
        path: 'payment',
        name: 'Payment',
        component: () => import('@/views/Payment.vue'),
        meta: { title: '缴费管理' }
      },
      {
        path: 'repair',
        name: 'Repair',
        component: () => import('@/views/Repair.vue'),
        meta: { title: '报修管理' }
      },
      {
        path: 'announcement',
        name: 'Announcement',
        component: () => import('@/views/Announcement.vue'),
        meta: { title: '公告管理' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  // 获取用户存储实例
  const userStore = useUserStore()
  
  // 检查是否需要认证
  if (to.meta.requiresAuth !== false) {
    // 检查登录是否过期
    if (userStore.isLoginExpired()) {
      userStore.logout()
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
      return
    }
    
    // 检查是否有token
    if (!userStore.token) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
      return
    }
  }
  
  next()
})

export default router