import { createRouter, createWebHistory } from 'vue-router'
import GuestLayout from '@/components/layouts/GuestLayout.vue'
import AppLayout from '@/components/layouts/AppLayout.vue'
import WelcomeGuest from '@/components/pages/WelcomeGuest.vue'
import HowToPlay from '@/components/pages/HowToPlay.vue'
import Auth from '@/components/pages/Auth.vue'

const routes = [
    {
        path: '/',
        component: GuestLayout,
        children: [
            {path: '', name: WelcomeGuest, component: WelcomeGuest},
            {path: 'how-to-play', name: HowToPlay, component: HowToPlay},
            {path: 'auth', name: Auth, component: Auth}
        ]
    },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router