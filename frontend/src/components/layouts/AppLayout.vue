<script setup>
    import { ref } from 'vue'
    import { useUserStore } from '@/stores/user'
    const isSidebarActive = ref(false)
    
    const toggleSidebar = () => {
        isSidebarActive.value = !isSidebarActive.value
    }

    const userStore = useUserStore()

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
            <li>
                <i class="fa-solid fa-house-chimney-user"></i>
                <RouterLink to="/app">Ana Sayfa</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-user"></i>
                <RouterLink to="/app">Profilim</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-play"></i>
                <RouterLink to="/app">Quize Katıl</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-ranking-star"></i>
                <RouterLink to="/app">Skorbord</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-clock"></i>
                <RouterLink to="/app">Geçmiş Sonuçlarım</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-user-group"></i>
                <RouterLink to="/app">Gruplarım</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-question"></i>
                <RouterLink to="/app">Soru Öner</RouterLink>
            </li>
            <li>
                <i class="fa-solid fa-arrow-right-from-bracket"></i>
                <RouterLink to="/app">Çıkış</RouterLink>
            </li>
        </ul>
    </div>

    <main>
        <router-view></router-view>
    </main>
</template>

<style scoped>
    header, main, .sidebar{
        background-color: #ff6f61;
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
        padding: 1rem ;
        border-radius: 30px;
        transition: 0.3s ease;
    }

    .sidebar ul li a{
        text-decoration: none;
        color:black;
        padding: 0 0.5rem;
    }

    .sidebar ul li:hover{
        transform: scale(1.05);
        background-color: rgb(255, 255, 126);
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
</style>