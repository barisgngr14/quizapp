<script setup>
  import { ref } from 'vue'
  import QuizModal from './QuizModal.vue'

  defineProps({
    quizzes:{
      type: Array
    }
  })

  const selectedQuiz = ref(null)
  const showModal = ref(false)

  function openModal(quiz) {
    selectedQuiz.value = quiz
    showModal.value = true
  }

  function closeModal() {
    selectedQuiz.value = null
    showModal.value = false
  }

  function calculateDaysLeft(availableTime) {
    const now = new Date();
    const end = new Date(availableTime);
    const diffMs = end - now;
    return diffMs > 0 ? Math.ceil(diffMs / (1000 * 60 * 60 * 24)) : 0;
  }

</script>

<template>
  <div class="quizzes-container">
    <h1 class="quiz-title">Quizlerim</h1>
    <table class="quiz-table">
      <thead>
        <tr>
          <th>Organizasyon Adı</th>
          <th>Quiz</th>
          <th>Bitiş Tarihi</th>
          <th>Kalan Gün</th>
          <th>İşlem</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(quiz, index) in quizzes" :key="index">
          <td class="org">{{ quiz.quizGroup }}</td>
          <td class="title">{{ quiz.quizName }}</td>
          <td class="end">{{ quiz.availableTime }}</td>
          <td class="days">{{ calculateDaysLeft(quiz.availableTime) }}</td>
          <td class="action">
            <button @click="openModal(quiz)">Katıl</button>
          </td>
        </tr>
      </tbody>
    </table>
    <QuizModal v-if="showModal" :quiz="selectedQuiz" @close="closeModal" />
  </div>
</template>

<style scoped>

  .quizzes-container {
    max-width: 960px;
    margin: 60px auto;
    padding: 40px;
    border-radius: 16px;
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: #fdfdfd;
    border: 2px solid #e0e0e0;
  }


  h1 {
    color: #333;
    margin-bottom: 20px;
    text-align: center;
  }

  .table-wrapper {
    overflow-x: auto;
    max-width: 100%;
  }

  .quiz-table {
    width: 100%;
    border-collapse: collapse;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
    border: 1px solid #ccc;
    transition: box-shadow 0.3s ease;
    background-color: #fdfdfd;
  }

  .quiz-table th,
  .quiz-table td {
    border: 1px solid #ddd;
    padding: 16px;
    text-align: center;
    font-weight: bold;
    font-size: 15px;
  }

  .quiz-table th {
    background: #ffffff;
    color: #333;
  }

  .quiz-table tbody tr:hover {
    background-color: #f7f7f7;
    box-shadow: inset 0 0 6px rgba(0,0,0,0.05);
    transform: scale(1.005);
    transition: all 0.2s ease-in-out;
  }

  .org {
    border-left: 6px solid #6a5acd;
    background-color: inherit;
    padding-left: 10px;
  }

  .title,
  .start,
  .end,
  .days,
  .action {
    background: #ffffff;
  } 

  .start,
  .end {
    color: #5a5655;
  }

  .days {
    color: rgb(239, 12, 12);
  }

  button {
    padding: 8px 16px;
    border: none;
    border-radius: 20px;
    background: linear-gradient(45deg, #6a5acd, #00bfff);
    color: white;
    font-weight: bold;
    cursor: pointer;
    transition: 0.3s ease;
  }

  button:hover {
    background: linear-gradient(45deg, #00bfff, #6a5acd);
    transform: scale(1.05);
  }

</style>
