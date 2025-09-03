import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getTokenExpiration } from '@/utils/jwt'

export const useUserStore = defineStore('user', () => {
    const currentUser = ref({
        name: null,
        surname: null,
        username: null
    })

    const token = ref(null)
    const tokenExp = ref(null)

    function setUser(user, jwtToken){
        currentUser.value = user
        token.value = jwtToken
        tokenExp.value = getTokenExpiration(jwtToken)
    }

    function clearUser() {
        currentUser.value = {
            name: null,
            surname: null,
            username: null
        }
        token.value = null
        tokenExp.value = null
    }

    return { 
        currentUser, 
        setUser, 
        token, 
        tokenExp, 
        clearUser 
    }
}, {
    persist: {
        enabled: true,
        strategies: [
            {
                key: 'user-store',
                storage: localStorage,
            }
        ]
    }
})