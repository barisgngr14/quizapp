<script setup>
    import QuizTable from '../QuizTable.vue';
    import { ref, onMounted } from 'vue';
    import axios from 'axios';
    import { useUserStore } from '@/stores/user';

    const quizzes = ref([])
    const store = useUserStore()

    onMounted(async()=>{
        const quizList = await axios.get('http://localhost:8080/api/quizzes/assigned', {
            headers: {
                Authorization: `${store.token}`
            }
        })
        quizzes.value = quizList.data;
    })

</script>

<template>
    <QuizTable :quizzes="quizzes"></QuizTable>
</template>

<style scoped>

</style>