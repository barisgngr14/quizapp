export const modalConfigs = {
    question: {
        title: 'Soru Ekle',
        fields: [
            { name: 'questionText', label: 'Soru', type: 'text' },
            { name: 'questionType', label: 'Soru Tipi', type: 'radio', options: ['Kolay', 'Orta', 'Zor'] },
            { name: 'point', label: 'Puan', type: 'text'},
            { name: 'difficulty', label: 'Zorluk', type: 'radio', options: ['Kolay', 'Orta', 'Zor'] },
            ...Array.from({ length: 4 }, (_, i) => ({
                name: `option${i + 1}`,
                label: `Şık ${i + 1}`,
                type: 'text'
            })),
        ]
    },
    quiz: {
        title: 'Quiz Ekle',
        fields: [
            { name: 'quizHeader', label: 'Başlık', type: 'text' },
            { name: 'quizGroup', label: 'Grubu', type: 'text'},
            { name: 'questionCount', label: 'Soru Sayısı', type: 'text'},
            { name: 'quizTime', label: 'Süre', type: 'time'},
            { name: 'availableTime', label: 'Aktiflik Süresi', type: 'datetime-local'},
            { name: 'questions', label: 'Sorular', type: 'select', options: ['1', '2'] }
        ]
    }
}