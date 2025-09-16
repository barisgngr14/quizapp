import { createRouter, createWebHistory } from 'vue-router'
import GuestLayout from '@/components/layouts/GuestLayout.vue'
import AppLayout from '@/components/layouts/AppLayout.vue'
import WelcomeGuest from '@/components/pages/WelcomeGuest.vue'
import HowToPlay from '@/components/pages/HowToPlay.vue'
import UserDashboard from '@/components/pages/UserDashboard.vue'
import LoginSignUp from '@/components/pages/LoginSignUp.vue'
import QQManagement from '@/components/pages/QQManagement.vue'
import { useUserStore } from '@/stores/user'
import AdminDashboard from '@/components/pages/AdminDashboard.vue'
import AdminProfile from '@/components/pages/AdminProfile.vue'
import EnterQuiz from '@/components/pages/EnterQuiz.vue'
import GroupManage from '@/components/pages/GroupManage.vue'
import MyGroups from '@/components/pages/MyGroups.vue'
import ScoreManage from '@/components/pages/ScoreManage.vue'
import Scoreboard from '@/components/pages/Scoreboard.vue'
import SuggestQ from '@/components/pages/SuggestQ.vue'
import SuggestionManage from '@/components/pages/SuggestionManage.vue'
import UserProfile from '@/components/pages/UserProfile.vue'
import QuizPage from '@/components/pages/QuizPage.vue'

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
      {path: 'dashboard', name: 'UserDashboard', component: UserDashboard, props: true, meta: { requiresAuth: true }},
      {path: 'profile', name: 'UserProfile', component: UserProfile, props: true, meta: { requiresAuth: true }},
      {path: 'enter-quiz', name: 'EnterQuiz', component: EnterQuiz, props: true, meta: { requiresAuth: true }},
      {path: 'scoreboard', name: 'Scoreboard', component: Scoreboard, props: true, meta: { requiresAuth: true }},
      {path: 'my-groups', name: 'MyGroups', component: MyGroups, props: true, meta: { requiresAuth: true }},
      {path: 'suggest-question', name: 'SuggestQ', component: SuggestQ, props: true, meta: { requiresAuth: true }}
    ],
  },
  {
    path: '/app/quiz/:quizId',
    name: 'QuizPage',
    component: QuizPage,
    props: true,
    meta: { requiresAuth: true }
  },
  {
    path: '/admin',
    component: AppLayout,
    children: [
      {path: 'dashboard', name: 'AdminDashboard', component: AdminDashboard, props: true, meta: { requiresAuth: true }},
      {path: 'profile', name: 'AdminProfile', component: AdminProfile, props: true, meta: { requiresAuth: true }},
      {path: 'manage-qq', name: 'QQManagement', component: QQManagement, props: true, meta: { requiresAuth: true }},
      {path: 'manage-scores', name: 'ScoreManage', component: ScoreManage, props: true, meta: { requiresAuth: true }},
      {path: 'manage-groups', name: 'GroupManage', component: GroupManage, props: true, meta: { requiresAuth: true }},
      {path: 'manage-suggestions', name: 'SuggestionManage', component: SuggestionManage, props: true, meta: { requiresAuth: true }},
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
  if (to.meta.requiresAuth && !store.token) {
    next('/auth/login')
    return
  }
  if (store.token && Date.now() > store.tokenExp) {
    store.clearUser()
    next('/auth/login')
    return
  }

  next()
})

export default router