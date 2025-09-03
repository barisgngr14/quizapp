<script setup>
    import { ref } from 'vue'
    import { useUserStore } from '@/stores/user'
    import { onMounted } from 'vue'
    import axios from 'axios'
    import { useRouter } from 'vue-router'

    const isSidebarActive = ref(false)
    const router = useRouter()
    
    const toggleSidebar = () => {
        isSidebarActive.value = !isSidebarActive.value
    }

    const userStore = useUserStore()

    const logout = () => {
        userStore.clearUser()
    }

    const menuItems = [
        { label: 'Ana Sayfa', path: '/app', icon: 'fa-solid fa-house-chimney-user', action: (() => router.push('/app'))},
        { label: 'Profilim', path: '/app', icon: 'fa-solid fa-user', action: (() => router.push('/app'))},
        { label: 'Quize Katıl', path: '/app', icon: 'fa-solid fa-play', action: (() => router.push('/app'))},
        { label: 'Skorbord', path: '/app', icon: 'fa-solid fa-ranking-star', action: (() => router.push('/app'))},
        { label: 'Geçmiş Sonuçlarım', path: '/app/dashboard', icon: 'fa-solid fa-clock', action: (() => router.push('/app/dashboard'))},
        { label: 'Gruplarım', path: '/app', icon: 'fa-solid fa-user-group', action: (() => router.push('/app')) },
        { label: 'Soru Öner', path: '/app', icon: 'fa-solid fa-question', action: (() => router.push('/app'))},
        { label: 'Çıkış', path: '/auth/login', icon: 'fa-solid fa-arrow-right-from-bracket', action: logout}
    ]

    onMounted(async() => {
        const store = useUserStore()
        if (!store.token || Date.now() > store.tokenExp) {
            store.clearUser()
            router.push('/auth/login')
        }

        if(store.currentUser) return

        try {
            const { data } = await axios.get('http://localhost:8080/api/verify', {
                headers: {
                    Authorization: `${store.token}`
                }
            })
            store.setUser(data, store.token)
        } catch {
            store.clearUser()
            router.push('/auth/login')
        }
    })

</script>

<template>
    <header>
        <button class="sidebar-button" @click="toggleSidebar"><i class="fa-solid fa-bars"></i></button>  
    </header>

    <div class="sidebar" :class="{ open: isSidebarActive }">
        <button class="close-btn" @click="toggleSidebar"><i class="fa-solid fa-xmark"></i></button>
        <div class="logo">
            <img src="/favicon.ico" alt="logo">
            <h1>QuizUp</h1>
        </div>
        <div class="profile-details">
            <img src="/images.png" alt="">
            <p>{{userStore.currentUser.username}}</p>
        </div>
        <ul>
            <li v-for="item in menuItems" :key="item.label">
                <RouterLink :to="item.path" @click="item.action">
                    <i :class="item.icon"></i>
                    <span>{{ item.label }}</span>
                </RouterLink>
            </li>
        </ul>
    </div>

    <main>
        <router-view></router-view>
    </main>
</template>

<style scoped>
    header, main{
        background-color: #f3f0ea;
    }

    header{
        display: flex;
        padding: 0.5rem 1rem;
        height: 50px;
    }

    .sidebar{
        position: fixed;
        top: 0;
        width: 250px;
        height: 100%;
        box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        padding: 1rem;
        z-index: 1000;
        left: -300px;
        transition: left 0.3s ease;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
    }

    .sidebar.open{
        left: 0;
    }

    .sidebar-button,
    .close-btn{
        background: none;
        border: none;
        font-size: 1.5rem;
        cursor: pointer;
        color: black;
    }

    .sidebar ul{
        list-style: none;
        padding: 0 20px;
    }

    .sidebar ul li{
        border-radius: 30px;
        transition: 0.3s ease;
    }

    .sidebar ul li a{
        text-decoration: none;
        color: inherit;
        padding: 0 0.5rem;
        display: flex;
        align-items: center;
        gap: 10px;
        width: 100%;
        height: 100%;
        padding: 1rem ;
    }

    .sidebar ul li:hover{
        transform: scale(1.05);
        background-color: rgb(195, 240, 236);
    }

    .logo{
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }

    .profile-details{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 0.5rem;
    }

    .profile-details img{
        border: 2px solid black;
        width: 120px;
        height: 120px;
        border-radius: 50%;
        object-fit: cover;
        margin-bottom: 0;
        margin-top: 1rem;
    }

    @media (max-height: 700px) {
        .sidebar {
            height: 90vh;
            overflow-y: auto;
            overflow-x: hidden;
        }

        .sidebar-link {
            padding: 10px 12px;
            font-size: 14px;
        }

        .toggle-btn {
            top: 8px;
        }
    }

</style>