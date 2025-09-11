<script setup>
import { useRouter } from 'vue-router'

const props = defineProps({
  quiz:{
    type: Array
  }
})

const router = useRouter()

function goToQuestion() {
  router.push('/question')
}
</script>

<template>
  <div class="modal-overlay">
    <div class="modal-content">
      <h2>{{ quiz.quizName }} Quizine Hoş Geldin!</h2>
      <p>⏱️ Süre:{{ quiz.quizTime }}</p>
      <p>❓ Soru Sayısı: {{ quiz.questionCount }}</p>
      <ul class="rules">
        <li>Her soru için tek cevap hakkın var.</li>
        <li>Geriye doğru sayan süreye dikkat et.</li>
        <li>Skor hesaplanırken sürenin de önemli olduğunu unutma.</li>
        <li>Sorular çoktan seçmeli ve doğru-yanlış şeklindedir.</li>
      </ul>
      <div class="modal-actions">
        <button class="cancel-btn" @click="$emit('close')">İptal</button>
        <button class="start-btn" @click="goToQuestion">Hazırım!</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}
.modal-content {
  background: white;
  padding: 30px;
  border-radius: 16px;
  width: 400px;
  text-align: center;
  font-family: 'Poppins', sans-serif;
}
.rules {
  text-align: left;
  margin: 20px 0;
  padding-left: 20px;
  color: #555;
}
.modal-actions {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}
.start-btn {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 16px;
  border-radius: 8px;
  cursor: pointer;
}
.cancel-btn {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 16px;
  border-radius: 8px;
  cursor: pointer;
}
</style>
