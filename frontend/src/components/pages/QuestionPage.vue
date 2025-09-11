<template>
  <div class="page-wrapper">
    <div class="top-bar">
      <button class="back-btn" @click="goBack">← Geri Dön</button>
    </div>
    
    <div v-if="loading" class="quiz-container">
      <p>Sorular yükleniyor...</p>
    </div>

    <div v-else-if="error" class="quiz-container">
      <p class="error-message">{{ error }}</p>
    </div>

    <div v-else-if="questions.length > 0" class="quiz-row">
      <button class="nav-button left" @click="goToPrevious">&#8592;</button>

      <div class="quiz-container" :style="{ borderColor: borderColor, borderStyle: 'solid', borderWidth: '4px' }">
        <div class="timer-box">Süre: {{ timeLeft }} sn</div>

        <div class="question-area">
          <p class="question-text">{{ questions[currentIndex].questionText }}</p>
        </div>

        <div v-if="questions[currentIndex].questionType === 'TRUE_FALSE'" class="answer-buttons">
          <button @click="submitAnswer(true)" class="btn correct">Doğru</button>
          <button @click="submitAnswer(false)" class="btn incorrect">Yanlış</button>
        </div>

        <div v-if="questions[currentIndex].questionType === 'MULTIPLE_CHOICE'" class="answer-buttons">
          <button
            v-for="option in questions[currentIndex].options"
            :key="option.optionId"
            @click="submitAnswer(option)"
            class="btn option"
          >
            {{ option.optionText }}
          </button>
        </div>
        
        <div class="footer">
          <span class="page-info">Soru {{ currentIndex + 1 }} / {{ questions.length }}</span>
        </div>
      </div>

      <button class="nav-button right" @click="goToNext">&#8594;</button>
    </div>

    <div v-else class="quiz-container">
      <p>Hiç soru bulunamadı. Lütfen backend'e soru ekleyin.</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';

export default {
  name: 'QuizQuestion',
  setup() {
    const questions = ref([]);
    const currentIndex = ref(0);
    const loading = ref(true);
    const error = ref(null);
    const timeLeft = ref(0);
    const timer = ref(null);
    const borderColor = ref("#000");

    const fetchQuestions = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/questions');
        questions.value = response.data; 
        error.value = 'Sorular yüklenemedi. Lütfen backend sunucusunun çalıştığından emin olun.';
        console.error('API isteği hatası:', err);
      } finally {
        loading.value = false;
      }
    };

    const setRandomBorderColor = () => {
      const colors = ['#FF6B6B', '#4CAF50', '#2196F3', '#FF9800', '#9C27B0', '#00BCD4'];
      borderColor.value = colors[Math.floor(Math.random() * colors.length)];
    };

    const goBack = () => {
    };

    const startTimer = () => {
      timer.value = setInterval(() => {
        timeLeft.value++;
      }, 1000);
    };

    const submitAnswer = (answer) => {
      clearInterval(timer.value);
      const currentQuestion = questions.value[currentIndex.value];

      let isCorrect = false;
      if (currentQuestion.questionType === 'MULTIPLE_CHOICE') {
        isCorrect = currentQuestion.correctOption.optionId === answer.optionId;
      } else {
        isCorrect = currentQuestion.correctOption.optionText.toLowerCase() === answer.toLowerCase();
      }

      console.log("Cevap:", answer);
      console.log("Doğru mu?", isCorrect);

      startTimer();
      timeLeft.value = 0;
    };

    const goToNext = () => {
      if (currentIndex.value < questions.value.length - 1) {
        currentIndex.value++;
        setRandomBorderColor();
      }
    };

    const goToPrevious = () => {
      if (currentIndex.value > 0) {
        currentIndex.value--;
        setRandomBorderColor();
      }
    };

    onMounted(() => {
      fetchQuestions();
      startTimer();
      setRandomBorderColor();
    });

    return {
      questions,
      currentIndex,
      timeLeft,
      borderColor,
      loading,
      error,
      goBack,
      submitAnswer,
      goToNext,
      goToPrevious,
    };
  }
};
</script>

<style scoped>
.page-wrapper {
  position: relative;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.timer-box {
  position: fixed;
  top: 20px;
  right: 20px;
  background: red;
  padding: 30px 15px;
  border-radius: 12px;
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
  font-weight: bold;
  font-size: 20px;
  color: white;
  z-index: 100;
}

.quiz-row {
  display: flex;
  align-items: center;
  gap: 20px;
}

.quiz-container {
  background: white;
  padding: 100px 150px;
  border-radius: 16px;
  box-shadow: 0 0px 80px rgba(77, 69, 69, 0.842);
  max-width: 500px;
  width: 100%;
  text-align: center;
  font-family: 'Poppins', sans-serif;
}

.quiz-header {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

body {
  background-color: #f3f0ea;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='200' height='200'%3E%3Ctext x='10' y='30' font-size='30'%3E%3F%3C/text%3E%3Ctext x='80' y='60' font-size='40'%3E%3F%3C/text%3E%3Ctext x='130' y='150' font-size='25'%3E%3F%3C/text%3E%3Ctext x='50' y='180' font-size='35'%3E%3F%3C/text%3E%3C/svg%3E");
  background-repeat: repeat;
  background-size: 120px;
}

.question-text {
  font-size: 24px;
  margin-bottom: 20px;
  color: #444;
}

.answer-buttons {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.btn {
  padding: 12px 24px;
  font-size: 16px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.btn:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 12px rgba(0,0,0,0.2);
}

.correct {
  background-color: #4caf50;
  color: white;
}

.correct:hover {
  background-color: #43a047;
}

.incorrect {
  background-color: #f44336;
  color: white;
}

.incorrect:hover {
  background-color: #e53935;
}

.footer {
  font-size: 14px;
  color: #555;
  margin-top: 10px;
}

.nav-button {
  background-color: white;
  border: none;
  border-radius: 50%;
  font-size: 24px;
  padding: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.2);
  cursor: pointer;
  transition: all 0.3s ease;
}

.nav-button:hover {
  transform: scale(1.1);
  background-color: #f0f0f0;
}

.question-image {
  width: 100%;
  max-height: 200px;
  object-fit: contain;
  margin-bottom: 20px;
}

.btn.option {
  background-color: #798894;
  color: white;
}

.btn.option:hover {
  background-color: #1976d2;
  transform: scale(1.05);
}

.top-bar {
  position: fixed;
  top: 20px;
  left: 20px;
  z-index: 200;
}

.back-btn {
  background: #fff;
  border: 2px solid #333;
  border-radius: 8px;
  padding: 8px 16px;
  font-size: 14px;
  font-weight: bold;
  cursor: pointer;
  transition: 0.3s ease;
}

.back-btn:hover {
  background: #f0f0f0;
}
</style>

<style>
body {
  background-color: #f3f0ea;
  background-image: url("data:image/svg+xml,...");
  background-repeat: repeat;
  background-size: 120px;
}
</style>