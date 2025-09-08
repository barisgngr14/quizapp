import { defineStore } from 'pinia'
import { ref } from 'vue'

export const changeSidebarState = defineStore('isSidebarActive', ()=>{
    const isSidebarActive = ref(true)

    const toggleSidebar = () => {
        isSidebarActive.value = !isSidebarActive.value
    }

    return{
        isSidebarActive,
        toggleSidebar
    }
})