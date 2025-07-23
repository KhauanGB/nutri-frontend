import axios from 'axios';
const API = 'http://localhost:8080/api/planos';

export const criarPlano = (dados) => axios.post(API, dados);
export const listarPlanos = () => axios.get(API);
export const visualizarPlano = (id) => axios.get(`${API}/${id}`);