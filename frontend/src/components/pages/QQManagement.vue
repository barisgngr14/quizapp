<script setup>
    import AddItemForms from '../AddItemForms.vue';
    import { openModal } from '@/utils/useModal';
</script>

<template>
    <section class="qq-management">
        <div class="section-label">
            <h1>SORU/QUIZ YÖNETİMİ</h1>
        </div>
        <div class="searchbar-container">
            <div class="search-question">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Sorular içinde arayın..." class="input-field">
            </div>
            <div class="search-quiz">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Quizler içinde arayın..." class="input-field">
            </div>
        </div>
        <div class="management-container">
            <table class="question-management">
                <thead>
                    <tr>
                        <th colspan="3">SORU HAVUZU</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>Soru Metni</th>
                        <th>İşlem</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>BİTES ne zaman kurulmuştur?</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>BİTES ne zaman kurulmuştur?</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>BİTES ne zaman kurulmuştur?</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>BİTES ne zaman kurulmuştur?</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>BİTES ne zaman kurulmuştur?</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table class="quiz-management">
                <thead>
                    <tr>
                        <th colspan="4">QUIZLER</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>Grup</th>
                        <th>Quiz</th>
                        <th>İşlem</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Herkes</td>
                        <td>BİTES Weekly</td>
                        <td class="table-buttons">
                            <button class="edit"><i class="fa-solid fa-pencil"></i></button>
                            <button class="delete"><i class="fa-solid fa-x"></i></button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="add-buttons">
            <a-button @click="openModal('question')"><i class="fa-solid fa-plus"></i> Soru Ekle</a-button>
            <a-button @click="openModal('quiz')"><i class="fa-solid fa-plus"></i> Quiz Ekle</a-button>
        </div>

        <add-item-forms></add-item-forms>
    </section>
</template>

<style scoped>
    .qq-management{
        width: 100%;
    }

    .section-label{
        display: flex;
        justify-content: center;
        align-items: center;
        height: calc(20vh - 66px);
    }

    .input-field {
        width: 100%;
        padding: 10px 10px 10px 35px; /* ikon için boşluk */
        border: 1.5px solid #ccc;
        border-radius: 15px;
    }

    .search-question i,
    .search-quiz i{
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        color: #888;
    }

    .search-quiz i,
    .search-question i{
        padding-left: 8px;
    }

    .searchbar-container,
    .add-buttons{
        position: relative;
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
        padding: 0.5rem 0;
    }

    .searchbar-container{
        height: calc(20vh - 66px);
    }

    .search-question,
    .search-quiz{
        position: sticky;
        display: inline-flex;
        min-width: 350px;
        box-sizing: border-box;
    }

    .management-container{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
        width: 100%;
        height: calc(45vh - 66px);
    }

    .question-management,
    .quiz-management{
        width: 38%;
        font-family: 'Segoe UI', sans-serif;
        box-shadow: 18px 18px 18px rgba(0, 0, 0, 0.08);
        border-collapse: separate; 
        border-spacing: 0;
        border: 2px solid rgb(221, 181, 246);
        border-radius: 12px;
        overflow: hidden;
    }

    .question-management thead,
    .quiz-management thead{
        background-color: #fffaf5;;
        color: #333;
        text-align: center;
        font-size: 1.2rem;
    }

    .question-management th,
    .quiz-management th{
        padding: 1rem;
    }

    .question-management th,
    .question-management td,
    .quiz-management th,
    .quiz-management td {
        border-bottom: 1px solid rgb(221, 181, 246);
    }

    .question-management tbody td,
    .quiz-management tbody td{
        padding: 0.75rem 1rem;
        text-align: center;
        font-size: 1rem;
        color: #444;
    }

    .question-management tbody tr:nth-child(even),
    .quiz-management tbody tr:nth-child(even){
        background-color: #fffaf5;
    }

    .question-management tbody tr,
    .quiz-management tbody tr{
        background-color: rgb(210, 206, 206);
    }

    .question-management tbody tr:hover,
    .quiz-management tbody tr:hover{
        background-color: rgb(230, 245, 244);
        transition: background-color 0.3s ease;
    }

    .question-management tr:not(:last-child) td,
    .quiz-management tr:not(:last-child) td {
        border-bottom: 1px solid rgb(221, 181, 246);
    }

    .question-management td:not(:last-child),
    .question-management th:not(:last-child),
    .quiz-management td:not(:last-child),
    .quiz-management th:not(:last-child) {
        border-right: 1px solid rgb(221, 181, 246);

    }

    .question-management thead tr:nth-child(2) th,
    .quiz-management thead tr:nth-child(2) th {
        border-top: 1px solid rgb(221, 181, 246);
    }

    .question-management tr:last-child td,
    .quiz-management tr:last-child td {
        border-bottom: none;
    }

    .add-buttons{
        height: 15vh;
    }

    .add-buttons button{
        background-color: rgb(179, 244, 187);
        border: 1.5px solid black;
        border-radius: 15px;
        height: 40px;
        transition: 0.3s ease;
    }

    .add-buttons button:hover{
        transform: scale(1.05);
        background-color: rgb(195, 240, 236);
    }

    .table-buttons {
        text-align: center; 
        vertical-align: middle; 
        white-space: nowrap; 
    }

    .edit,
    .delete{
        border: none;
        border-radius: 15px;
        display: inline-block;
        margin: 0 5px;
        height: 25px;
        color: white;
        transition: transform 0.3s ease;
    }

    .edit{
        background-color: rgb(0, 119, 255);
    }
    .delete{
        background-color: red;
    }

    .table-buttons button:hover{
        transform: scale(1.1);
    }

</style>