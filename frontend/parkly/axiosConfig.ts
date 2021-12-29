import axios from 'axios';

axios.interceptors.response.use(
    response => {
        return response;
    }, error => {
        if (error.response?.status === 401) {
            window.location.href = '/logout';
        }
        throw error;
    }
)