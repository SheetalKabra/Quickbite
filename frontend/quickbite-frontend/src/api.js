import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081/api/v1', // adjust as needed
});

// Add token to every request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token'); // or sessionStorage, or a global state
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;
