// 请求拦截器
axios.interceptors.request.use(
    config => {
        // 从sessionStorage获取token（因为你使用的是sessionStorage）
        const token = sessionStorage.getItem('token');

        // 如果token存在，添加到请求头
        if (token) {
            config.headers['token'] = token;
        }
        return config;
    },
    error => {
        console.error('请求错误:', error);
        return Promise.reject(error);
    }
);