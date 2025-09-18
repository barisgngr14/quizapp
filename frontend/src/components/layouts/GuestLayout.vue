<script setup>
    import { useRouter } from 'vue-router'

    const router = useRouter()

    function goHome() {
        const targetRoute = '/'

        if (router.currentRoute.value.path === targetRoute) {
            window.location.reload()
        } else {
            router.push(targetRoute)
        }
        
    }

    function loadWelcomePage() {
        router.push('/auth/login');
    }

</script>

<template>
    <header>
        <div role="button" class="logo" @click="goHome">
            <img src="/favicon.ico" alt="logo">
            <h1>QuizUp</h1>
        </div>
        <nav>
            <RouterLink to="/how-to-play" class="nav-item">Nasıl Oynanır?</RouterLink>
            <p class="separator">&#x2502;</p>
            <RouterLink :to="{ name: 'LoginSignUp', params: { tab: 'login' }}" class="nav-item-auth">Giriş Yap</RouterLink>
            <RouterLink :to="{ name: 'LoginSignUp', params: { tab: 'signup' }}" class="nav-item-auth">Kayıt Ol</RouterLink>
        </nav>
    </header>

    <main>
        <router-view :loadWelcomePage="loadWelcomePage"></router-view>
    </main>

    <footer>
        <p>&copy; 2025 QuizUp</p>
    </footer>
</template>

<style scoped>
    header{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1rem 2rem;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
        color: black;
        width: auto;
        height: 50px;
    }

    .logo{
        display: flex;
        align-items: center;
        gap: 0.5rem;
        cursor: pointer;
    }

    nav{
        display: flex;
        align-items: center;
        gap: 1rem;
    }

    .nav-item, 
    .nav-item-auth{
        color: black;
        text-decoration: none;
        font-weight: bold;
        align-content: center;
    }

    .nav-item{
        transition: color 0.3s ease;
    }

    .nav-item:hover{
        color: rgb(195, 240, 236);
        transition: 0.3s ease;
    }

    .nav-item-auth{
        padding: 0.5rem 1rem;
        border: 2px solid black;
        border-radius: 20px;
        background-image: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
        margin-left: auto;
        box-shadow: 6px 6px 6px rgba(0, 0, 0, 0.2);
        transition: 0.3s ease;
    }

    .nav-item-auth:hover{
        background-image: linear-gradient(to left, rgb(195, 240, 236), rgb(195, 240, 236));
        transform: scale(1.05);
        transition: 0.3s ease;
    }

    footer{
        padding: 1rem 2rem;
        text-align: center;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
        color: black;
        bottom: 0;
        width: auto;
        height: 50px;
    }

    .separator{
        font-size: 48px;
    }
    
</style>