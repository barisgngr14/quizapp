import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

export const quizService = {
  addQuiz: (data) => axios.post(`${BASE_URL}/quizzes/add`, data),
  getAll: () => axios.get(`${BASE_URL}/quizzes/fetch`),
  getAssigned: (token) =>
    axios.get(`${BASE_URL}/quizzes/assigned`, {
      headers: { Authorization: token }
    })
};

export const questionService = {
  addQuestion: (data) => axios.post(`${BASE_URL}/questions/add`, data),
  getAll: () => axios.get(`${BASE_URL}/questions/fetch`)
};