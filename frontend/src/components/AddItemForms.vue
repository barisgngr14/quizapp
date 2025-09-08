<script setup>
    import { useModal } from '@/utils/useModal';
    import { modalConfigs } from '@/utils/form-items';
</script>

<template>
    <div id="components-modal-demo-position">
        
        <a-modal
            v-model:open="useModal.modal2Visible"
            :title="modalConfigs[useModal.modalType]?.title || ''"
            centered
            :type="useModal.modalType"
            @ok="useModal.modal2Visible=false"
        >
            <form>
                <div class="form-item" v-for="field in modalConfigs[useModal.modalType].fields" :key="field.name">
                    <label :for="field.name">{{ field.label }}</label>
                    <input
                        v-if="field.type==='text'"  
                        :type="field.type"
                        :name="field.name"
                        :placeholder="field.label"  
                    >
                    <div v-else-if="field.type === 'radio'" class="radio-inputs">
                        <div v-for="option in field.options" :key="option" class="radio-option">
                        <input
                            :type="field.type"
                            :id="field.name + '-' + option"
                            :name="field.name"
                            :value="option"
                        />
                            <label :for="field.name + '-' + option">{{ option }}</label>
                        </div>
                    </div>

                    <select v-else-if="field.type === 'select'" :name="field.name">
                        <option v-for="option in field.options" :key="option" :value="option">
                            {{ option }}
                        </option>
                    </select>

                    <input 
                        v-if="field.type === 'time'"
                        :type="field.type"
                        :name="field.name"
                        :placeholder="field.label"
                    />
                    <input 
                        v-if="field.type === 'datetime-local'"
                        :type="field.type"
                        :name="field.name"
                    />
                </div>
            </form>
        </a-modal>
    </div>
</template>

<style scoped>
    .radio-inputs{
        display: flex;
        justify-content: flex-start;
        align-items: center;
        gap: 20px;
    }

    .form-item{
        display: flex;
        gap: 20px;
        height: 30px;
        align-items: center;
    }
</style>