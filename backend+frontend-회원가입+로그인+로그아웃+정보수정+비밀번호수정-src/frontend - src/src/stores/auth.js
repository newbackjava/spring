import { ref, computed, reactive } from 'vue'; // Vue 기본 API
import { defineStore } from 'pinia'; // Pinia 스토어 생성
import axios from 'axios'; // HTTP 통신용 라이브러리

// 초기 상태
const initState = {
  token: '', // JWT 토큰
  user: {
    username: '', // 사용자 ID
    email: '', // 이메일
    roles: [], // 권한 목록
  },
};

// Pinia 스토어 정의
export const useAuthStore = defineStore('auth', () => {
  const state = ref({ ...initState }); // 상태 저장소

  const isLogin = computed(() => !!state.value.user.username); // 로그인 여부
  //!! -->	값을 불리언(boolean) 으로 변환. truthy → true, falsy → false

  const username = computed(() => state.value.user.username); // 사용자 ID
  const email = computed(() => state.value.user.email); // 이메일

  // 로그인 함수
  const login = async (member) => {
    // state.value.token = 'test token'; // 테스트용 토큰 (임시)
    // state.value.user = {
    //   username: member.username,
    //   email: member.username + '@test.com',
    // };

    // 로그인 API 호출
    const { data } = await axios.post('/api/auth/login', member);

    /**
     * 백엔드에서는 아래와 같은 필터로 처리:
     * - 경로: setFilterProcessesUrl("/api/auth/login")
     * - 성공: setAuthenticationSuccessHandler()
     * - 실패: setAuthenticationFailureHandler()
     */

    state.value = { ...data }; // 응답 데이터 저장
    console.log(state); // 디버그 출력

    localStorage.setItem('auth', JSON.stringify(state.value)); // 로컬스토리지 저장
  };

  // 로그아웃 함수
  const logout = () => {
    localStorage.clear(); // 로컬스토리지 제거
    state.value = { ...initState }; // 초기화
  };

  // 토큰 반환
  const getToken = () => state.value.token;

  // 로컬스토리지에서 상태 복원
  const load = () => {
    const auth = localStorage.getItem('auth');
    if (auth != null) {
      state.value = JSON.parse(auth);
      console.log(state); // 디버그 출력
    }
  };

  const changeProfile = (member) => {
    state.value.user.email = member.email;
    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  load(); // 초기 로딩

  return {
    state,
    username,
    email,
    isLogin,
    changeProfile,
    login,
    logout,
    getToken,
  }; // 외부 노출
});
