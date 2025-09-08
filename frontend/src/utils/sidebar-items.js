export const userItems = [
    { label: 'Ana Sayfa', path: '/app/dashboard', icon: 'fa-solid fa-house-chimney-user', action: 'goToDashboard'},
    { label: 'Profilim', path: '/app/profile', icon: 'fa-solid fa-user', action: 'goToProfile'},
    { label: 'Quize Katıl', path: '/app/enter-quiz', icon: 'fa-solid fa-play', action: 'enterQuiz'},
    { label: 'Skorbord', path: '/app/scoreboard', icon: 'fa-solid fa-ranking-star', action: 'goToScoreboard'},
    { label: 'Gruplarım', path: '/app/my-groups', icon: 'fa-solid fa-user-group', action: 'goToMyGroups' },
    { label: 'Soru Öner', path: '/app/suggest-question', icon: 'fa-solid fa-question', action: 'goToSuggest'},
    { label: 'Çıkış', path: '/auth/login', icon: 'fa-solid fa-arrow-right-from-bracket', action: 'logout'}
]

export const adminItems = [
    { label: 'Ana Sayfa', path: '/admin/dashboard', icon: 'fa-solid fa-house-chimney-user', action: 'goToAdminDash'},
    { label: 'Profilim', path: '/admin/profile', icon: 'fa-solid fa-user', action: 'goToAdminProfile'},
    { label: 'Soru/Quiz Yönetimi', path: '/admin/manage-qq', icon: 'fa-solid fa-play', action: 'goToQQManage'},
    { label: 'Skor Yönetimi', path: '/admin/manage-scores', icon: 'fa-solid fa-ranking-star', action: 'goToScoreManage'},
    { label: 'Grup Yönetimi', path: '/admin/manage-groups', icon: 'fa-solid fa-user-group', action: 'goToGroupManage' },
    { label: 'Soru Öneri Yönetimi', path: '/admin/manage-suggestions', icon: 'fa-solid fa-question', action: 'goToSuggestManage'},
    { label: 'Çıkış', path: '/auth/login', icon: 'fa-solid fa-arrow-right-from-bracket', action: 'logout'}
]