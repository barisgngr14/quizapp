<script setup>
  import { onMounted, watch } from 'vue'
  import { useRoute } from 'vue-router'

  const route = useRoute()

  watch(
    () => route.path,
    (newPath) => {
      document.body.className = ''
      if (newPath.startsWith('/app/quiz/')) {
        document.body.classList.add('question-bg')
      }
    }
  )

  onMounted(() => {
    if (route.path.startsWith('/app/quiz/')) {
      document.body.classList.add('question-bg')
    }
  })

</script>

<template>
  <router-view></router-view>
</template>

<style>
  body.question-bg {
    background-color: #f3f0ea;
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='200' height='200'%3E%3Ctext x='10' y='30' font-size='30'%3E%3F%3C/text%3E%3Ctext x='80' y='60' font-size='40'%3E%3F%3C/text%3E%3Ctext x='130' y='150' font-size='25'%3E%3F%3C/text%3E%3Ctext x='50' y='180' font-size='35'%3E%3F%3C/text%3E%3C/svg%3E");
    background-repeat: repeat;
    background-size: 120px;
  }
</style>
