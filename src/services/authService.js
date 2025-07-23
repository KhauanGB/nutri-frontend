import axios from 'axios';
const API = 'http://localhost:8080/api/auth';

export const login = (dados) => axios.post(`${API}/login`, dados);