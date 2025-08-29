import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () =>{
    const currentUser = ref({
        name: null,
        surname: null,
        username: null
    })

    function setUser(user){
        currentUser.value = user
    }

    return { currentUser, setUser }
})