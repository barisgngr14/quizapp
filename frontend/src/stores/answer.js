import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAnswerStore = defineStore('answerStore', () => {
  const answerData = ref({});
  const solveDataId = ref(null);

  const setSolveDataId = (id) =>{
    solveDataId.value = id
  }

  const initialize = (questions) => {
    const initialData = {};
    questions.forEach((q) => {
      initialData[q.questionId] = {
        selectedOption: null
      };
    });
    answerData.value = initialData;
  };

  const answer = (questionId, selectedOption) => {
    if (!answerData.value[questionId]) return;

    answerData.value[questionId] = {
      ...answerData.value[questionId],
      selectedOption
    };
  };

  return {
    answerData,
    solveDataId,
    setSolveDataId,
    initialize,
    answer,
  };
});
