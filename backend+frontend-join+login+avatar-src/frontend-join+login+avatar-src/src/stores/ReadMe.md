🔴 ===> login시 스프링시큐리트를 이용해 로그인 처리를 할 예정이므로 필터의 주소와 동일해야함.

![image](https://github.com/user-attachments/assets/f3d57684-f62b-47e6-99eb-2cc7a3b529e6)


![image](https://github.com/user-attachments/assets/1b532cdc-676b-4d4b-9e40-42c9a0ed04c0)


```

import { ref, computed, reactive } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const initState = {
  token: '', // 접근 토큰(JWT)
  user: {
    username: '', // 사용자 ID
    email: '', // Email
    roles: [], // 권한 목록
  },
};

export const useAuthStore = defineStore('auth', () => {
  const state = ref({ ...initState });

  const isLogin = computed(() => !!state.value.user.username); // 로그인 여부

  const username = computed(() => state.value.user.username); // 로그인 사용자 ID

  const email = computed(() => state.value.user.email); // 로그인 사용자 email

  const login = async (member) => {
    state.value.token = 'test token';
    state.value.user = {
      username: member.username,
      email: member.username + '@test.com',
    };

    // api 호출
    const { data } = await axios.post('/api/auth/login', member);

    // @Log4j2
    // @Component
    // public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //     public JwtUsernamePasswordAuthenticationFilter(
    //             AuthenticationManager authenticationManager,
    //             LoginSuccessHandler loginSuccessHandler,
    //             LoginFailureHandler loginFailureHandler) {
    //         super(authenticationManager);
    //         setFilterProcessesUrl("/api/auth/login"); //필터적용 주소(****)
    //         setAuthenticationSuccessHandler(loginSuccessHandler); //성공했을 때
    //         setAuthenticationFailureHandler(loginFailureHandler);  //실패했을 때
    //     }

    state.value = { ...data };

    console.log(state);

    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  const logout = () => {
    localStorage.clear();
    state.value = { ...initState };
  };

  const getToken = () => state.value.token;

  const load = () => {
    const auth = localStorage.getItem('auth');
    if (auth != null) {
      state.value = JSON.parse(auth);
      console.log(state.value);
    }
  };

  load();

  return { state, username, email, isLogin, login, logout, getToken };
});



```
