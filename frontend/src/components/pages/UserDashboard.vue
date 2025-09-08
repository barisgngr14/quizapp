<script setup>
    import { useUserStore } from '@/stores/user';
    const userStore = useUserStore()
</script>

<template>
  <section class="dashboard-container">
    <div class="dashboard-content">
        <div class="scoreboard-container">
            <div class="my-point">
                <p>35: {{ userStore.currentUser.username }} = 0</p>
            </div>
            <div class="step-container">
                <div class="step-text">
                    <img src="/images.png" alt="">
                    testuser1
                </div>
                <div id="second-step" class="bg-blue step centerBoth podium-number">
                    <p>2</p>
                    <p class="point">478341</p>
                </div>
            </div>

            <div class="step-container">
                <div class="step-text">
                    <img src="/images.png" alt="">
                    testuser
                </div>
                <div id="first-step" class="bg-blue step centerBoth podium-number">
                    <i class="fa-solid fa-star"></i>
                    <p>1</p>
                    <p class="point">578341</p>
                </div>
            </div>

            <div class="step-container">
                <div class="step-text">
                    <img src="/images.png" alt="">
                    testuser2
                </div>
                <div id="third-step" class="bg-blue step centerBoth podium-number">
                    <p>3</p>
                    <p class="point">478041</p>
                </div>
            </div>
        </div>
        <div class="quizzes-container">
            <table class="quizzes-content">
                <thead>
                    <tr>
                        <th colspan="3">QUIZLER</th>
                    </tr>
                    <tr>
                        <th>Grup</th>
                        <th>Quiz</th>
                        <th>Kalan Süre</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td>5 gün</td>
                    </tr>
                    <tr>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td>5 gün</td>
                    </tr>
                </tbody>
            </table>
            
        </div>
    </div>
    <div class="buttons">
        <button class="scoreboard-btn">Skorborda Git &rarr;</button>
        <button class="quizzes-btn">Quizleri Gör &rarr;</button>
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
        overflow: hidden;
        height: 100%;
    }

    .dashboard-content{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: flex-end;
        min-height: calc(80vh - 166px);
    }

    .quizzes-container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 40%;
    }

    .quizzes-content{
        width: 100%;
        font-family: 'Segoe UI', sans-serif;
        box-shadow: 18px 18px 18px rgba(0, 0, 0, 0.08);
        border-collapse: separate; 
        border-spacing: 0;
        border: 2px solid rgb(221, 181, 246);
        border-radius: 12px;
        overflow: hidden;
    }

    .quizzes-content thead{
        background-color: #fffaf5;;
        color: #333;
        text-align: center;
        font-size: 1.2rem;
    }

    .quizzes-content th{
        padding: 1rem;
    }

    .quizzes-content th,
    .quizzes-content td {
        border: none;
    }

    .quizzes-content tbody td{
        padding: 0.75rem 1rem;
        text-align: center;
        font-size: 1rem;
        color: #444;
    }

    .quizzes-content tbody tr:nth-child(even){
        background-color: rgb(244, 214, 193);
    }

    .quizzes-content tbody tr{
        background-color: rgb(249, 201, 169);
    }

    .quizzes-content tbody tr:hover{
        background-color: rgb(195, 240, 236);
        transition: background-color 0.3s ease;
    }

    .quizzes-content tr:not(:last-child) td {
        border-bottom: 1px solid rgb(221, 181, 246);
    }

    .quizzes-content td:not(:last-child),
    .quizzes-content th:not(:last-child) {
        border-right: 1px solid rgb(221, 181, 246);
    }

    .quizzes-content thead tr:nth-child(2) th {
        border-top: 1px solid rgb(221, 181, 246);
    }

    button {
        width: 200px;
        height: 80px;
        border: none;
        border-radius: 20px;
        cursor: pointer;
        transform-origin: center;
        font-size: 22px;
        transition: 0.3s ease;
        background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
    }

    button:hover{
        transform: scale(1.05);
        background: rgb(195, 240, 236);
    }

    .buttons{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
        min-height: 20vh;
    }

    .scoreboard-container{
        width: 40%;
        height: 500px;
        display: flex;          
        justify-content: space-between;
        position: relative;
    }

    .my-point{
        position: absolute;
        left: 50%;
        text-align: center;
        transform: translateX(-50%);
        width: max-content;
        border: none;
        background-color: #063b65;
        padding: 0 5px;
        color:white;
        font-weight: bold;
    }

    .step-container {
        flex: 1;                 
        position: relative;
        margin: 0 5px;          
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        align-items: center;
    }

    .step-text {
        margin-bottom: 10px;
        text-align: center;
        display: flex;
        flex-direction: column;
    }

    .step {
        width: 100%;
        position: relative;
    }

    .bg-blue {
        background-color: #063b65;
    }

    #first-step {
        height: 50%;
    }

    #second-step {
        height: 35%;
    }

    #third-step {
        height: 30%;
    }

    .centerBoth {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .podium-number {
        font-weight: bold;
        font-size: 4em;
        color: white;
    }

    #first-step p,
    #second-step p,
    #third-step p{
        margin: 0;
        padding: 0;
    }

    #first-step i{
        color: gold;
        font-size: 32px;
    }

    .point{
        font-size: 20px;
    }

    .step-text img{
        border: 2px solid black;
        width: 120px;
        height: 120px;
        border-radius: 50%;
        object-fit: cover;
    }
    
</style>