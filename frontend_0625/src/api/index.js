import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";

const instance = axios.create({
  timeout: 1000,
});

// 요청 인터셉터
instance.interceptors.request.use(
  (config) => {
    // JWT 추출
    const { getToken } = useAuthStore();
    const token = getToken();
    if (token) {
      // 토큰이 있는 경우
      config.headers["Authorization"] = `Bearer ${token}`;
      console.log(config.headers.Authorization);
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 응답 인터셉터
instance.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      return response;
    }
    if (response.status === 404) {
      return Promise.reject("404: 페이지 없음 " + response.request);
    }
    return response;
  },
  async (error) => {
    if (error.response?.status === 401) {
      const { logout } = useAuthStore();
      logout();
      router.push("/auth/login?error=login_required");
      return Promise.reject({ error: "로그인이 필요한 서비스입니다." });
    }
    return Promise.reject(error);
  }
);

export default instance; // 인터셉터가 적용된 axios 인스턴스
