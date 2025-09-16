<script setup>
    import { ref, reactive , onMounted} from "vue";
    import { EditOutlined, CloseOutlined, SearchOutlined } from "@ant-design/icons-vue";
    import AddItemForms from "../AddItemForms.vue";
    import { openModal } from "@/utils/useModal";
    import axios from "axios";

    const dataQuestions = ref([]);
    const dataQuizzes = ref([]);

    onMounted(async()=>{
        const responseQuestion = await axios.get("http://localhost:8080/api/questions/fetch")
        const responseQuiz = await axios.get("http://localhost:8080/api/quizzes/fetch")

        dataQuestions.value = responseQuestion.data
        dataQuizzes.value = responseQuiz.data
    })

    const questionState = reactive({ searchText: "", searchedColumn: "" });

    const columnsQuestions = [
        { title: "ID", key: "index", scopedSlots: { customRender: 'index' }},
        {
            title: "Soru",
            dataIndex: "questionText",
            key: "questionText",
            customFilterDropdown: true,
            onFilter: (value, record) => record.questionText.toLowerCase().includes(value.toLowerCase())
        },
        { title: "İşlem", key: "actions" }
    ];

    const quizState = reactive({ searchText: "", searchedColumn: "" });

    const columnsQuizzes = [
        { title: "ID", key: "index", scopedSlots: { customRender: 'index' }},
        { title: "Grup", dataIndex: "quizGroup", key: "quizGroup" },
        {
            title: "Quiz",
            dataIndex: "quizName",
            key: "quizName",
            customFilterDropdown: true,
            onFilter: (value, record) => record.quizName.toLowerCase().includes(value.toLowerCase())
        },
        { title: "İşlem", key: "actions" }
    ];

    const handleSearch = (selectedKeys, confirm, dataIndex, state) => {
        confirm();
        state.searchText = selectedKeys[0];
        state.searchedColumn = dataIndex;
    };

    const handleReset = (clearFilters, state) => {
        clearFilters({ confirm: true });
        state.searchText = "";
    };

    const editQuestion = (record) => {
        openModal("question", record);
    };

    const deleteQuestion = (questionId) => {
        dataQuestions.value = dataQuestions.value.filter((q) => q.questionId !== questionId);
    };

    const editQuizzes = (record) => {
        openModal("quiz", record);
    };

    const deleteQuiz = (quizId) => {
        dataQuizzes.value = dataQuizzes.value.filter((i) => i.quizId !== quizId);
    };

</script>

<template>
    <section class="qq-management">
        <div class="section-label">
            <h1>SORU/QUIZ YÖNETİMİ</h1>
        </div>

        <div class="management-container">
            <a-card title="Soru Havuzu" class="custom-card">
                <a-table :data-source="dataQuestions" :columns="columnsQuestions" rowKey="questionId" bordered :pagination="{ pageSize: 5 }" :filtered-value="questionState.searchText ? [questionState.searchText] : null">
                    <template #customFilterDropdown="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }">
                        <div style="padding: 8px">
                            <a-input ref="questionSearchInput" :placeholder="`Ara ${column.dataIndex}`"
                                :value="selectedKeys[0]" style="width: 188px; margin-bottom: 8px; display: block"
                                @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
                                @pressEnter="handleSearch(selectedKeys, confirm, column.dataIndex, questionState)" />
                            <a-button type="primary" size="small" style="width: 90px; margin-right: 8px"
                                @click="handleSearch(selectedKeys, confirm, column.dataIndex, questionState)">
                                <template #icon>
                                    <SearchOutlined />
                                </template>
                                Ara
                            </a-button>
                            <a-button size="small" style="width: 90px" @click="handleReset(clearFilters, questionState)">
                                Temizle
                            </a-button>
                        </div>
                    </template>
                    <template #customFilterIcon="{ filtered }">
                        <SearchOutlined :style="{ color: filtered ? '#108ee9' : undefined }" />
                    </template>
                    <template #bodyCell="{ column, record , index}">
                        <span v-if="column.key === 'index'">
                            {{ index + 1 }}
                        </span>
                        <span v-else-if="column.key !== 'actions'">
                            {{ record[column.dataIndex] }}
                        </span>
                        <span v-else>
                            <a-button type="link" @click="editQuestion(record)">
                                <template #icon>
                                    <EditOutlined />
                                </template>
                            </a-button>
                            <a-button type="link" danger @click="deleteQuestion(record.questionId)">
                                <template #icon>
                                    <CloseOutlined />
                                </template>
                            </a-button>
                        </span>
                    </template>
                </a-table>
                <div class="actions">
                    <a-button type="primary" @click="openModal('question')">
                        <i class="fa-solid fa-plus"></i> Soru Ekle
                    </a-button>
                </div>
            </a-card>

            <a-card title="Quizler" class="custom-card">
                <a-table :data-source="dataQuizzes" :columns="columnsQuizzes" rowKey="quizId" bordered :pagination="{ pageSize: 5 }"  :filtered-value="quizState.searchText ? [quizState.searchText] : null">
                    <template #customFilterDropdown="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }">
                        <div style="padding: 8px">
                            <a-input ref="quizSearchInput" :placeholder="`Ara ${column.dataIndex}`" :value="selectedKeys[0]"
                                style="width: 188px; margin-bottom: 8px; display: block"
                                @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
                                @pressEnter="handleSearch(selectedKeys, confirm, column.dataIndex, quizState)" />
                            <a-button type="primary" size="small" style="width: 90px; margin-right: 8px"
                                @click="handleSearch(selectedKeys, confirm, column.dataIndex, quizState)">
                                <template #icon>
                                    <SearchOutlined />
                                </template>
                                Ara
                            </a-button>
                            <a-button size="small" style="width: 90px" @click="handleReset(clearFilters, quizState)">
                                Temizle
                            </a-button>
                        </div>
                    </template>
                    <template #customFilterIcon="{ filtered }">
                        <SearchOutlined :style="{ color: filtered ? '#108ee9' : undefined }" />
                    </template>
                    <template #bodyCell="{ column, record , index}">
                        <span v-if="column.key === 'index'">
                            {{ index + 1 }}
                        </span>
                        <span v-else-if="column.key !== 'actions'">
                            {{ record[column.dataIndex] }}
                        </span>
                        <span v-else>
                            <a-button type="link" @click="editQuizzes(record)">
                                <template #icon>
                                    <EditOutlined />
                                </template>
                            </a-button>
                            <a-button type="link" danger @click="deleteQuiz(record.quizId)">
                                <template #icon>
                                    <CloseOutlined />
                                </template>
                            </a-button>
                        </span>
                    </template>
                </a-table>
                <div class="actions">
                    <a-button type="primary" @click="openModal('quiz')">
                        <i class="fa-solid fa-plus"></i> Quiz Ekle
                    </a-button>
                </div>
            </a-card>
        </div>
        <add-item-forms v-if="dataQuestions.length" :questions="dataQuestions"></add-item-forms>
    </section>
</template>

<style scoped>
    .qq-management {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .section-label {
        text-align: center;
        margin: 2rem 0;
    }

    .management-container {
        margin-top: 2rem;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        gap: 2rem;
        width: 100%;
    }

    .custom-card {
        width: 40%;
        min-width: 400px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .actions {
        margin-top: 16px;
        text-align: center;
    }
    
</style>