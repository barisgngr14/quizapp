<script setup>
    import { useUserStore } from '@/stores/user'
    import { onMounted , computed} from 'vue'
    import axios from 'axios'
    import { useRouter } from 'vue-router'
    import { userItems, adminItems } from '@/utils/sidebar-items'
    import { changeSidebarState } from '@/stores/changeSidebarState'

    const router = useRouter()
    const changeState = changeSidebarState()
    const userStore = useUserStore()

    const logout = () => {
        userStore.clearUser()
    }

    const actionsMap = {
        logout,
        goToDashboard: () => router.push('/app/dashboard'),
        goToProfile: () => router.push('/app/profile'),
        enterQuiz: () => router.push('/app/enter-quiz'),
        goToScoreboard: () => router.push('/app/scoreboard'),
        goToMyGroups: () => router.push('/app/my-groups'),
        goToSuggest: () => router.push('/app/suggest-question'),

        goToAdminDash: () => router.push('/admin/dashboard'),
        goToAdminProfile: () => router.push('/admin/profile'),
        goToQQManage: () => router.push('/admin/manage-qq'),
        goToScoreManage: () => router.push('/admin/manage-scores'),
        goToGroupManage: () => router.push('/admin/manage-groups'),
        goToSuggestManage: () => router.push('/admin/manage-suggestions')
    };

    const handleAction = (actionKey) => {
        const fn = actionsMap[actionKey];
        if(typeof fn === 'function'){
            fn();
        } else {
            console.warn(`${actionKey} metodu bulunamadı`);
        }
    }

    const menuItems = computed(() => {
        if (typeof userStore.currentUser.redirectPath === 'string' && userStore.currentUser.redirectPath.includes('admin')) {
            return adminItems
        }
        return userItems
    })

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
        <button class="sidebar-button" @click="changeState.toggleSidebar"><i class="fa-solid fa-bars"></i></button>
        <img src="/logo.ico" alt="logo">
        <h1>QuizUp</h1>  
    </header>

    <div class="sidebar" :class="{ open: changeState.isSidebarActive }">
        <button class="close-btn" @click="changeState.toggleSidebar"><i class="fa-solid fa-xmark"></i></button>
        <div class="logo">
            <img src="/logo.ico" alt="logo">
            <h1>QuizUp</h1>
        </div>
        <div class="profile-details">
            <img src="/images.png" alt="">
            <p>{{userStore.currentUser.username}}</p>
        </div>
        <ul>
            <li v-for="item in menuItems" :key="item.label">
                <RouterLink :to="item.path" @click="handleAction(item.action)">
                    <i :class="item.icon"></i>
                    <span>{{ item.label }}</span>
                </RouterLink>
            </li>
        </ul>
        <div class="sidebar-footer">
            <p>&copy; 2025 QuizUp</p>
        </div>
    </div>

    <main :class="{shifted: changeState.isSidebarActive}">
        <router-view></router-view>
    </main>
</template>

<style scoped>

    header{
        display: flex;
        padding: 0.5rem 1rem;
        height: 50px;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
        align-items: center;
        gap: 0.5rem;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
        color: black;
        width: auto;
    }

    header h1{
        margin: 0;
    }

    .sidebar{
        position: fixed;
        top: 0;
        width: 250px;
        height: 100%;
        box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        padding: 1rem;
        z-index: 3;
        left: -300px;
        transition: left 0.3s ease;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
    }

    .sidebar.open{
        left: 0;
    }

    main {
        display: flex;
        flex-wrap: wrap;
        transition: transform 0.3s ease;
        transform-origin: right;
        height: 100%;
        overflow: hidden;
    }

    main.shifted{
        transform: scale(0.85);
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

    .sidebar-footer{
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .logo{
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        gap: 0.5rem;
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