import axios from "axios";

const service = axios.create({
  baseURL: "/api", // 统一前缀，vite.config.js 可做代理
  timeout: 10000,
});

// 请求拦截器，添加token
service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// 响应拦截器，统一错误处理
service.interceptors.response.use(
  (response) => response.data,
  (error) => {
    // 可统一弹窗提示
    return Promise.reject(error);
  }
);

export default service;