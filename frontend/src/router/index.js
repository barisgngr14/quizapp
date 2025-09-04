import { createRouter, createWebHistory } from 'vue-router'
import GuestLayout from '@/components/layouts/GuestLayout.vue'
import AppLayout from '@/components/layouts/AppLayout.vue'
import WelcomeGuest from '@/components/pages/WelcomeGuest.vue'
import HowToPlay from '@/components/pages/HowToPlay.vue'
import Dashboard from '@/components/pages/Dashboard.vue'
import LoginSignUp from '@/components/pages/LoginSignUp.vue'
import AdminDashboard from '@/components/pages/AdminDashboard.vue'
import { useUserStore } from '@/stores/user'

const routes = [
    {
        path: '/',
        component: GuestLayout,
        children: [
            {path: '', name: 'WelcomeGuest', component: WelcomeGuest, props: true},
            {path: 'how-to-play', name: 'HowToPlay', component: HowToPlay},
            {path: 'auth/:tab', name: 'LoginSignUp', component: LoginSignUp, props: true}
        ]
    },
    {
        path: '/app',
        component: AppLayout,
        children: [
            {path: 'dashboard', name: 'Dashboard', component: Dashboard, props: true, meta: { requiresAuth: true }}
        ],
    },
    {
        path: '/admin',
        component: AppLayout,
        children: [
            {path: 'dashboard', name: 'AdminDashboard', component: AdminDashboard}
        ],
        props: true
    }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const store = useUserStore()
  if (store.token && Date.now() > store.tokenExp) {
    store.clearUser()
    return next('/auth/login')
  }
  if (to.meta.requiresAuth && !store.token) {
    next('/auth/login')
  } else {
    next()
  }
})

export default router