import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/main.css'
import { createPinia } from 'pinia'
import piniaPluginPersistedState from "pinia-plugin-persistedstate"

createApp(App).use(router).use(createPinia().use(piniaPluginPersistedState)).mount('#app')
