<script setup>
    import { useUserStore } from '@/stores/user';
    const userStore = useUserStore()
</script>

<template>
  <section class="dashboard-container">
    <div class="user-detail">
        <h1>{{userStore.currentUser.username}}</h1>
    </div>
    <div class="dashboard-content">
        <div class="scoreboard-container">
            <table class="scoreboard-content">
                <thead>
                    <tr>
                        <th colspan="3">SKORBORD</th>
                    </tr>
                    <tr>
                        <th colspan="3">Tüm Zamanlar</th>
                    </tr>
                    <tr>
                        <th>Sıra</th>
                        <th>Kullanıcı Adı</th>
                        <th>Puan</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>testuser1</td>
                        <td>67468</td>
                    </tr>
                    <tr>
                        <td>...</td>
                        <td>...</td>
                        <td>...</td>
                    </tr>
                    <tr>
                        <td>48</td>
                        <td>{{userStore.currentUser.username}}</td>
                        <td>0</td>
                    </tr>
                </tbody>
            </table>

            <button>Skorborda Git &rarr;</button>
        </div>
        <div class="quizzes-container">
            <table class="quizzes-content">
                <thead>
                    <tr>
                        <th colspan="4">QUIZLER</th>
                    </tr>
                    <tr>
                        <th>Quiz</th>
                        <th>Grup</th>
                        <th>Soru Sayısı</th>
                        <th>Kalan Süre</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>BİTES Weekly</td>
                        <td>Herkes</td>
                        <td>20</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>BİTES Weekly</td>
                        <td>Herkes</td>
                        <td>20</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>BİTES Weekly</td>
                        <td>Herkes</td>
                        <td>20</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>BİTES Weekly</td>
                        <td>Herkes</td>
                        <td>20</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>BİTES Weekly</td>
                        <td>Herkes</td>
                        <td>20</td>
                        <td>5 gün</td>
                    </tr>
                </tbody>
            </table>
            <button>Quizleri Gör &rarr;</button>
        </div>
    </div>
  </section>
</template>

<style scoped>
    .dashboard-container {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        min-height: calc(100vh - 66px);
        width: 100%;
        position: relative;
        z-index: 0;
    }

    .user-detail{
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 36px;
    }

    .scoreboard-container,
    .quizzes-container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .dashboard-content{
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
    }

    .scoreboard-content ,
    .quizzes-content{
        width: 100%;
        max-width: 600px;
        margin: 2rem auto;
        border-collapse: collapse;
        font-family: 'Segoe UI', sans-serif;
        background-color: #fffaf5;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        border-radius: 8px;
        overflow: hidden;
        min-width: 500px;
    }

    .scoreboard-content thead ,
    .quizzes-content thead{
        background-color: #ffe4c4;
        color: #333;
        text-align: center;
        font-size: 1.2rem;
    }

    .scoreboard-content th ,
    .quizzes-content th{
        padding: 1rem;
    }

    .scoreboard-content th,
    .scoreboard-content td,
    .quizzes-content th,
    .quizzes-content td {
        border: 2px solid #f5d5b5;
    }

    .scoreboard-content tbody td ,
    .quizzes-content tbody td{
        padding: 0.75rem 1rem;
        text-align: left;
        border-bottom: 1px solid #f5e1d6;
        font-size: 1rem;
        color: #444;
    }

    .scoreboard-content tbody tr:nth-child(even) ,
    .quizzes-content tbody tr:nth-child(even){
        background-color: #fff2e6;
    }

    .scoreboard-content tbody tr:hover ,
    .quizzes-content tbody tr:hover{
        background-color: #ffe9d6;
        transition: background-color 0.3s ease;
    }

    .scoreboard-content tbody tr:last-child td {
        font-weight: bold;
        color: #d35400;
    }

    button {
        width: 200px;
        height: 80px;
        border: 2px solid black;
        border-radius: 20px;
        cursor: pointer;
        transform-origin: center;
        font-size: 22px;
        transition: 0.3s ease;
        margin-bottom: 32px;
    }

    button:hover{
        transform: scale(1.05);
        background-color: rgb(255, 255, 126);
    }
</style>