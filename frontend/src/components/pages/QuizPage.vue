<script setup>
  import { ref, onMounted, computed } from 'vue'
  import axios from 'axios'
  import { useRoute, useRouter } from 'vue-router'
  import { useUserStore } from '@/stores/user'
  import { useAnswerStore } from '@/stores/answer'
  import { Modal } from 'ant-design-vue';
  import { h } from 'vue';

  const questions = ref({ quizTime: 0, questions: []})
  const currentIndex = ref(0)
  const loading = ref(true)
  const error = ref(null)
  const selectedOptionIds = ref({})
  
  const timer = ref(0)
  const borderColor = ref("#000")
  const route = useRoute()
  const store = useUserStore()
  const answerStore = useAnswerStore()
  const router = useRouter()
  const timeLeft = ref(0)
  const solveDataId = ref("")

  const fetchQuestions = async () => {
    try {
      const response = await axios.post(
        'http://localhost:8080/api/questions/by-quiz', 
        { quizId: route.params.quizId },
        { headers: { Authorization: `${store.token}`}}
      )
      questions.value = response.data
    } catch (err) {
      error.value = 'Sorular yüklenemedi. Lütfen backend sunucusunun çalıştığından emin olun.'
      console.error('API isteği hatası:', err)
    } finally {
      loading.value = false
    }
  }

  const createEmptySolveData = async() =>{
    try {
      const response = await axios.post(
        'http://localhost:8080/api/solvedata/create',
        { quizId: route.params.quizId },
        { headers: { Authorization: `${store.token}`}}
      )
      solveDataId.value = response.data
    } catch (err) {
      error.value = 'Oluşturma hatası.'
      console.error('API isteği hatası:', err)
    }
  }

  const setRandomBorderColor = () => {
    const colors = ['#FF6B6B', '#4CAF50', '#2196F3', '#FF9800', '#9C27B0', '#00BCD4']
    borderColor.value = colors[Math.floor(Math.random() * colors.length)]
  }

  const submitQuiz = async () => {
    const quizPayload = {
      answers: answerStore.answerData,
      solveDataId: answerStore.solveDataId,
      solveTime: parseInt(route.query.quizTime, 10) * 60 - timeLeft.value,
      timeLeft: timeLeft.value
    }

    try {
      const response = await axios.post('http://localhost:8080/api/answer/save',
        quizPayload
      )
      console.log(response.data)
      router.push({
        path: '/app/enter-quiz',
        query: {
          quizName: route.query.quizName,
        }
      }).then(() => {
        Modal.success({
          title: `${route.query.quizName} quizini başarıyla tamamladınız!`,
          content: h('div', {}, [
            h('p', `Çözme Süreniz: ${response.data.solveTime}`),
            h('p', `Doğru Sayısı: ${response.data.correctCount}`),
            h('p', `Yanlış Sayısı: ${response.data.wrongCount}`),
            h('p', `Boş Sayısı: ${response.data.unansweredCount}`),
            h('p', `Toplam Puan: ${response.data.quizScore}`),
          ]),
          okText: `Cevapları Görüntüle`,
          onOk() {
            router.push('/app/scoreboard')
          }
        })
      }).catch(err => {
        console.error("Yönlendirme hatası:", err);
      });
    } catch (error) {
      console.error('API isteği hatası:', error)
    }
    
  }

  const startTimer = () => {
    timer.value = setInterval(() => {
      timeLeft.value--
    }, 1000)
  }

  const submitAnswer = (answer) => {
    clearInterval(timer.value)
    const currentQuestion = questions.value.questions[currentIndex.value]
    selectedOptionIds.value[currentQuestion.questionId] = answer.optionId
    answerStore.answer(currentQuestion.questionId, answer)
    console.log(timeLeft.value)
    console.log(parseInt(route.query.quizTime, 10) * 60 - timeLeft.value)

    startTimer()
  }

  const goToNext = () => {
    if (currentIndex.value < questions.value.questions.length - 1) {
      currentIndex.value++
      setRandomBorderColor()
    }
  }

  const goToPrevious = () => {
    if (currentIndex.value > 0) {
      currentIndex.value--
      setRandomBorderColor()
    }
  }

  const hasQuestions = computed(() => {
    return questions.value.questions && questions.value.questions.length > 0
  })


  const currentQuestion = computed(() => {
    return questions.value.questions[currentIndex.value].questionText || null
  })

  const currentOptions = computed(()=>{
    return questions.value.questions[currentIndex.value].options
  })

  const formattedTime = computed(() => {
    const minutes = Math.floor(timeLeft.value / 60).toString().padStart(2, '0')
    const seconds = (timeLeft.value % 60).toString().padStart(2, '0')
    return `${minutes}:${seconds}`
  })

  const info = () => {
    Modal.confirm({
      title: 'Quizi Bitir',
      content: h('div', {}, [
        h('p', 'Bitirmek istediğinize emin misiniz?'),
        h('p', 'Şu ana kadarki işaretlemeleriniz geçerli sayılacak ve quizi bir daha alamayacaksınız.'),
      ]),
      okText: 'Evet, Bitir',
      cancelText: 'Hayır, Devam Et',
      onOk() {
        submitQuiz();
      },
      onCancel() {
        console.log('Kullanıcı iptal etti');
      },
    });
  };


  onMounted(async() => {
    await fetchQuestions()
    if (route.query.quizTime) {
      timeLeft.value = parseInt(route.query.quizTime, 10) * 60
    }
    await createEmptySolveData()
    answerStore.initialize(questions.value.questions)
    answerStore.setSolveDataId(solveDataId.value)
    startTimer()
    setRandomBorderColor()
  });

</script>

<template>
  <div class="page-wrapper">
    <div class="top-bar">
      <button class="back-btn" @click="info">← Geri Dön</button>
    </div>
    
    <div v-if="loading" class="quiz-container">
      <p>Sorular yükleniyor...</p>
    </div>

    <div v-else-if="error" class="quiz-container">
      <p class="error-message">{{ error }}</p>
    </div>

    <div v-else-if="hasQuestions" class="quiz-row">
      <button class="nav-button left" @click="goToPrevious">&#8592;</button>

      <div class="quiz-container" :style="{ borderColor: borderColor, borderStyle: 'solid', borderWidth: '4px' }">
        <div class="timer-box">⏱️: {{ formattedTime }}</div>

        <div class="question-area">
          <p class="question-text">{{ currentQuestion }}</p>
        </div>

        <div class="answer-buttons">
          <button
            v-for="option in currentOptions"
            :key="option.optionId"
            @click="submitAnswer(option)"
            :class="['btn option', { selected: selectedOptionIds[option.question.questionId] === option.optionId }]"
          >
            {{ option.optionText }}
          </button>
        </div>

        <div class="finish-quiz">
          <button @click="info">Quizi Bitir</button>
        </div>
        
        <div class="footer">
          <span class="page-info">Soru {{ currentIndex + 1 }} / {{ route.query.questionCount }}</span>
        </div>
      </div>

      <button class="nav-button right" @click="goToNext">&#8594;</button>
    </div>

    <div v-else class="quiz-container">
      <p>Hiç soru bulunamadı. Lütfen backend'e soru ekleyin.</p>
    </div>
  </div>
</template>

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
    padding: 10px 10px;
    border-radius: 12px;
    border: 2px solid black;
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
    margin-left: 5px;
    transition: background-color 0.3s ease;
  }

  .btn.option:hover {
    background-color: #1976d2;
    transform: scale(1.05);
  } 

  .btn.option.selected {
    background-color: #1976d2;
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

  .finish-quiz{
    position: fixed;
    bottom: 20vh;
    right: 15vw;
  }

  .finish-quiz button{
    background: red;
    padding: 10px 10px;
    border-radius: 12px;
    box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
    font-weight: bold;
    font-size: 20px;
    color: white;
    transition: all 0.3s ease;
  }

  .finish-quiz button:hover{
    transform: scale(1.05);
  }

</style>