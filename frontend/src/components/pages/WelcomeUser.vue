<script setup>
    import { useUserStore } from '@/stores/user'
    const userStore = useUserStore()
</script>

<template>
    <section class="welcome-user">
        <div class="profile-details">
            <h1>HOŞ GELDİN, {{userStore.currentUser.username}}!</h1>
            <img src="/images.png" alt="">
        </div>

        <div class="menu-btns-layout">
            <button class="menu-btn profile-btn"><i class="fa-solid fa-user"></i> Profilim &rarr;</button>
            <button class="menu-btn quiz-btn"><i class="fa-solid fa-play"></i> Quize Katıl &rarr;</button>
            <button class="menu-btn scoreboard-btn"><i class="fa-solid fa-ranking-star"></i> Skorbord &rarr;</button>
            <button class="menu-btn recent-scores-btn"><i class="fa-solid fa-clock"></i> Geçmiş Sonuçlarım &rarr;</button>
            <button class="menu-btn groups-btn"><i class="fa-solid fa-user-group"></i> Gruplarım &rarr;</button>
            <button class="menu-btn suggestions-btn"><i class="fa-solid fa-question"></i> Soru Öner &rarr;</button>
            <button class="menu-btn exit-btn"><i class="fa-solid fa-arrow-right-from-bracket"></i> Çıkış Yap &rarr;</button>
        </div>
    </section>
</template>

<style scoped>
    h1 {
        margin-block-start: 0;
        margin-block-end: 0;
        text-align: center;
    }

    .welcome-user {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        height: calc(100vh - 66px);
        width: 100%;
        position: relative;
        z-index: 0;
    }

    .profile-details {
        text-align: center;
        flex: 0 0 auto;
        height: 360px;
        width: 100%;
        box-sizing: border-box;
    }

    .profile-details h1{
        font-size: 48px;
    }

    .profile-details img{
        border: 2px solid black;
        aspect-ratio: 1 / 1;
        border-radius: 100%;
        object-fit: cover;
        margin-bottom: 2rem;
        margin-top: 2rem;
        width: 240px;
        display: inline-block;
    }
    .menu-btns-layout {
        width: 100%;
        height: 515px;
        background-color: rgb(255, 255, 126);
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: auto auto auto;
        gap: 30px;
        align-items: center;
        margin: 0 auto;
    }

    .menu-btn {
        width: 200px;
        height: 80px;
        border: 2px solid black;
        border-radius: 20px;
        cursor: pointer;
        transform-origin: center;
        font-size: 22px;
        transition: 0.3s ease;
    }

    .menu-btn:hover{
        transform: scale(1.05);
        background-color: #ff6f61;
    }

    .quiz-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 2;
        grid-row: 2;
        justify-self: center;
        width: 300px;
        height: 120px;
        font-size: 32px;
    }

    .scoreboard-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 3;
        grid-row: 2;
        justify-self: center;
    }

    
    .profile-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 1;
        grid-row: 1;
        justify-self: end;
    }

    .recent-scores-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 1;
        grid-row: 3;
        justify-self: end;
    }

    .groups-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 3;
        grid-row: 1;
    }

    .suggestions-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 1;
        grid-row: 2;
        justify-self: center;
    }

    .exit-btn{
        background-color: black;
        color: white;
        font-weight: bold;
        grid-column: 3;
        grid-row: 3;
    }
    
</style>