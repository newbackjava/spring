import { useAuthStore } from '@/stores/auth';

//로그인 여부에 따라 인증을 할 수 있는
//로그인 페이지를 호출하도록 함수를 정의함.
//로그인 여부가 저장되어있는 pinia저장소 필요
export const isAuthenticated = (to, from) => {
  //중앙저장소에서 로그인정보 가지고 와서
  //아직 로그인안했으면
  //로그인 페이지를 호출해줘.
  const auth = useAuthStore();

  if (!auth.isLogin) {
    console.log('로그인 필요함.');
    return { name: 'login', query: { next: to.name } };
  }
  console.log('인증가드가 로그인 인증 먼저 함.');
};

//로그인 페이지를 호출할때는
//호출할 다음 페이지를 queryString으로
//전달해주세요.
