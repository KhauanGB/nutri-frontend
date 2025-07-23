import axios from 'axios';
const API = 'http://localhost:8080/api/alimentos';

export const listarAlimentos = () => axios.get(API);