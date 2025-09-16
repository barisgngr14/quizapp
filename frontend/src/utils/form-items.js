export const modalConfigs = {
    question: {
        title: 'Soru Ekle',
        fields: [
            { name: 'questionType', label: 'Soru Tipi', type: 'radio', options: ['Çoktan Seçmeli','Doğru-Yanlış']},
            { name: 'questionText', label: 'Soru Metni', type: 'text' },
            { name: 'difficulty', label: 'Zorluk', type: 'radio', options: ['Kolay', 'Orta', 'Zor'] },
            { name: 'point', label: 'Puan', type: 'text'},
            ...Array.from({ length: 4 }, (_, i) => ({
                name: `option${i + 1}`,
                label: `Şık ${i + 1}`,
                type: 'text',
                showIfQuestionType: 'Çoktan Seçmeli'
            })),
            {
                name: 'correctOption',
                label: 'Doğru Şık',
                type: 'radio',
                options: ['Şık 1', 'Şık 2', 'Şık 3', 'Şık 4'],
                showIfQuestionType: 'Çoktan Seçmeli'
            },
            {
                name: 'trueFalseAnswer',
                label: 'Cevap',
                type: 'radio',
                options: ['Doğru', 'Yanlış'],
                showIfQuestionType: 'Doğru-Yanlış'
            }
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