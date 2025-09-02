import { createRouter, createWebHistory } from 'vue-router'
import GuestLayout from '@/components/layouts/GuestLayout.vue'
import AppLayout from '@/components/layouts/AppLayout.vue'
import WelcomeGuest from '@/components/pages/WelcomeGuest.vue'
import HowToPlay from '@/components/pages/HowToPlay.vue'
import WelcomeUser from '@/components/pages/WelcomeUser.vue'
import Dashboard from '@/components/pages/Dashboard.vue'
import LoginSignUp from '@/components/pages/LoginSignUp.vue'

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
            {path: '', name: 'WelcomeUser', component: WelcomeUser},
            {path: 'dashboard', name: 'Dashboard', component: Dashboard, props: true}
        ]
    },
    {
        path: '/admin',
        component: AppLayout,
        props: true
    }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router