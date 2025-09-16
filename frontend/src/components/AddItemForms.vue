<script setup>
    import { reactive, watch, onMounted, ref } from "vue";
    import { useModal } from "@/utils/useModal";
    import { modalConfigs } from "@/utils/form-items";
    import { questionService, quizService } from "@/utils/api";
    import axios from "axios";
    import { message } from 'ant-design-vue';
    
    const emit = defineEmits(["quizAdded",'questionAdded']);
    const props = defineProps({
        questions: Array
    })

    const selectedQuestions = ref([])
    const groupData = ref([])

    const formState = reactive({
        type: null,
        questions: [{}],
        options: [{ optiontext: "" , isCorrect: ""}, { optiontext: "" , isCorrect: ""}],
        answer: "",

        quizName: "",
        quizGroup: null,
        questionCount: 1,
        quizTime: null,
        availableTime: null,

        questionText: null,
        difficulty: null,
        questionType: null,
        score: null,
        correctOption: ""
    });

    const addOption = () => {
        formState.options.push({ optiontext: "" , isCorrect: ""});
    };

    const removeOption = (index) => {
        if (formState.options[index].text === formState.answer) {
            formState.answer = "";
        }
        formState.options.splice(index, 1);
    };

    function getSelectedQuestions(valArray) {
        return valArray.map(val => {
            const index = parseInt(val.replace("q-", ""));
            return props.questions[index];
        }).filter(Boolean);
    }

    watch(
        () => selectedQuestions.value,
        (newVal, oldVal) => {
            if (newVal.length > formState.questionCount) {
                selectedQuestions.value = oldVal;
                message.warning(`En fazla ${formState.questionCount} soru seçebilirsiniz.`);
            }
        },

        () => formState.questionType,
        (type) => {
            formState.correctOption = null;
        },

        () => formState.options,
        (newVal) => {
            if (!newVal.some((opt) => opt.text === formState.answer)) {
                formState.answer = "";
            }
        },

        { deep: true }
    );

    const resetForm = () => {
        formState.type = "";
        formState.options = [{ optiontext: "" , isCorrect: ""}];
        formState.answer = "";
    };

    const groupDataFetched = ref(false);

    async function fetchGroupsIfNeeded() {
        if (!groupDataFetched.value) {
            await axios.get("http://localhost:8080/api/groups/get").then((res) => {
                groupData.value = res.data.map(g => ({
                    label: g.groupName,
                    value: g.groupId
                }));   
                groupDataFetched.value = true;
            });
        }
    }

    onMounted(() => {
        const data = useModal.currentData;
        if (data) {
            formState.type = "quiz";
            formState.answer = ""; 
        }
        fetchGroupsIfNeeded()
    });

    const handleOk = async () => {
        const questions = getSelectedQuestions(selectedQuestions.value)
        if (useModal.modalType === "quiz") {
            console.log(formState.availableTime)
            const payload = {
                quizName: formState.quizName,
                quizGroup: formState.quizGroup,
                questionCount: formState.questionCount,
                quizTime: formState.quizTime,
                availableTime: formState.availableTime,
                questions: questions
            };
        
            try {
                await quizService.addQuiz(payload);
                console.log("Quiz başarıyla eklendi!");
                emit("quizAdded");
            } catch (error) {
                console.error("Quiz ekleme hatası:", error);
            }

        } else if (useModal.modalType === "question") {
            let options = []

            if (formState.questionType === 'TRUE_FALSE') {
                options = [
                    { optionText: "Doğru", isCorrect: formState.correctOption === true },
                    { optionText: "Yanlış", isCorrect: formState.correctOption === false }
                ]
            } else {
                options = formState.options.map(opt => ({
                    optionText: opt.text,
                    isCorrect: opt.text === formState.correctOption
                }))
            }
            
            const payload = {
                questionText: formState.questionText,
                difficulty: formState.difficulty,
                questionType: formState.questionType,
                score: formState.score,
                options
            };
            try {
                await questionService.addQuestion(payload);
                useModal.modal2Visible = false;
                resetForm();
                emit("questionAdded");
            } catch (error) {
                message.error("Lütfen tüm zorunlu alanları doldurunuz.");
            }
        }

    };

    function normalizeTreeData(questions) {
        return questions.map((q, index) => ({
            title: `Soru ${index + 1}`, 
            value: `q-${index}`,                          
            key: `q-${index}`,                            
            children: [] ,
            label: `Soru ${index + 1}: ${q.questionText}`                               
        }));
    }

    const treeData = ref(normalizeTreeData(props.questions));

</script>

<template>
    <a-modal v-model:open="useModal.modal2Visible" :title="modalConfigs[useModal.modalType]?.title || ''" centered ref="formRef"
        @ok="handleOk" @cancel="useModal.modal2Visible = false">
        <a-form layout="vertical">
            <template v-if="useModal.modalType === 'question'">
                <a-form-item label="Soru Tipi Seçimi" name="questionType">
                    <a-select v-model:value="formState.questionType" placeholder="Soru tipi seçiniz">
                        <a-select-option value="MULTIPLE_CHOICE">Çoktan Seçmeli</a-select-option>
                        <a-select-option value="TRUE_FALSE">Doğru / Yanlış</a-select-option>
                    </a-select>
                </a-form-item>

                <a-form-item label="Zorluk Seviyesi" name="difficulty">
                    <a-select v-model:value="formState.difficulty" placeholder="Zorluk seviyesi seçiniz">
                        <a-select-option value="EASY">Kolay</a-select-option>
                        <a-select-option value="MEDIUM">Orta</a-select-option>
                        <a-select-option value="HARD">Zor</a-select-option>
                    </a-select>
                </a-form-item>

                <a-form-item label="Soru Metni" name="questionText">
                    <a-input v-model:value="formState.questionText" placeholder="Soru metnini giriniz"/>
                </a-form-item>

                <a-form-item label="Puan" name="score" >
                    <a-input-number v-model:value="formState.score" :min="1" placeholder="Puanı giriniz"/>
                </a-form-item>

                <template v-if="formState.questionType === 'MULTIPLE_CHOICE'">
                    <a-form-item label="Şıklar" name="options">
                        <div v-for="(option, index) in formState.options" :key="option.id" class="option-row">
                            <a-input v-model:value="option.text" :placeholder="`Şık ${index + 1}`" style="width: 70%" />
                            <a-button type="link" danger @click="removeOption(index)" v-if="formState.options.length > 2">
                                Sil
                            </a-button>
                        </div>
                            <a-button type="dashed" block @click="addOption" style="margin-top: 10px">
                                + Şık Ekle
                            </a-button>
                        </a-form-item>

                    <a-form-item label="Doğru Cevap" name="correctOption">
                        <a-select v-model:value="formState.correctOption" placeholder="Doğru şıkkı seçiniz" allowClear>
                            <a-select-option v-for="(opt, i) in formState.options" :key="i" :value="opt.text">
                                {{ opt.text || `Şık ${i + 1}` }}
                            </a-select-option>
                        </a-select>
                    </a-form-item>
                </template>

                <template v-else-if="formState.questionType === 'TRUE_FALSE'">
                    <a-form-item label="Doğru Cevap">
                        <a-radio-group v-model:value="formState.correctOption">
                            <a-radio :value="true">Doğru</a-radio>
                            <a-radio :value="false">Yanlış</a-radio>
                        </a-radio-group>
                    </a-form-item>
                </template>
            </template>
            <template v-if="useModal.modalType === 'quiz'">
                <a-form-item label="Quiz Adı" name="quizName">
                    <a-input v-model:value="formState.quizName" placeholder="Quiz Adını Giriniz" />
                </a-form-item>
                <a-form-item label="Grup Seçimi" name="quizGroup">
                    <a-select v-model:value="formState.quizGroup" placeholder="Grubu Seçiniz" :options="groupData"></a-select>
                </a-form-item>
                <a-form-item label="Soru Sayısı" name="questionCount">
                    <a-input-number v-model:value="formState.questionCount" :min="1" />
                </a-form-item>
                <a-form-item label="Quiz Süresi (Dk)" name="quizTime">
                    <a-input-number v-model:value="formState.quizTime"
                        :min="1"
                        :step="5"
                        placeholder="Örn: 20" />
                </a-form-item>
                <a-form-item label="Quiz Bitiş Tarih ve Saati" name="quizAvailable">
                    <a-date-picker v-model:value="formState.availableTime" show-time format="YYYY-MM-DD HH:mm:ss" placeholder="Tarih/Saat Seçiniz"/>
                </a-form-item>

                <a-form-item label="Sorular" name="questions">
                    <a-tree-select
                        v-model:value="selectedQuestions"
                        show-search
                        style="width: 100%"
                        :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                        placeholder="Soru Seçiniz"
                        allow-clear
                        multiple
                        tree-default-expand-all
                        :tree-data="treeData"
                        tree-node-filter-prop="label"
                    >
                        <template #title="{ value: val, label }">
                            <span>{{ label }}</span>
                        </template>
                    </a-tree-select>
                </a-form-item>
                
            </template>
        </a-form>
    </a-modal>
</template>

<style scoped>
    
    .option-row {
        display: flex;
        align-items: center;
        gap: 8px;
        margin-bottom: 6px;
    }

</style>