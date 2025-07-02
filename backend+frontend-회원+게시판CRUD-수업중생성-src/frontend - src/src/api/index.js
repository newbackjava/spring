import axios from 'axios';
import { useAuthStore } from '@/stores/auth';
import router from '@/router';

//axios객체 생성
//==> 프로젝트 전체에서 사용할 객체
//==> request/response 인터셉터 설정이 된 axios객체를 사용하기 위함

const instance = axios.create({
  timeout: 1000,
});

//request 인터셉터 설정 --> JWT를 header에 포함
instance.interceptors.request.use(
  (config) => {
    //JWT를 가지고 와야함.

    const { getToken } = useAuthStore();
    const token = getToken();

    //config객체를 이용해서 http의 header에 넣어줌.
    if (token) {
      //토큰이 있는 경우 header에 포함시킴
      config.headers['Authorization'] = `Bearer ${token}`;
      console.log('전송될 jwt>> ' + config.headers.Authorization);
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

//response 인터셉터 설정 --> 응답 처리
// 응답 인터셉터
instance.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      return response;
    }
    if (response.status === 404) {
      return Promise.reject('404: 페이지 없음 ' + response.request);
    }
    return response;
  },
  async (error) => {
    if (error.response?.status === 401) {
      //localStorage에서 토큰 제거
      //localStorage.removeItem('auth');
      const { logout } = useAuthStore();
      logout();
      router.push('/auth/login?error=login_required');
      return Promise.reject({ error: '로그인이 필요한 서비스입니다.' });
    }
    return Promise.reject(error);
  }
);
export default instance;
