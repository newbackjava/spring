<script setup>
// Vue의 Composition API에서 사용하는 핵심 함수들을 임포트
import { computed, reactive, ref } from 'vue';
// 인증 관련 상태를 관리하는 Pinia 스토어 가져오기
import { useAuthStore } from '@/stores/auth';
// 라우팅 기능을 사용하기 위해 Vue Router의 useRouter 훅 가져오기
import { useRouter } from 'vue-router';

// router 인스턴스를 생성하여 페이지 이동 등에 사용
const router = useRouter();
// 인증 관련 Pinia 스토어 인스턴스를 생성하여 상태나 액션에 접근
const auth = useAuthStore();

// 사용자 입력 값을 담을 반응형 객체 생성
const member = reactive({
  username: '', // 사용자 ID 입력 필드
  password: '', // 비밀번호 입력 필드
});

// 오류 메시지를 표시하기 위한 반응형 참조값
const error = ref('');

// '아이디와 비밀번호가 모두 입력되었는가?'를 판단하는 계산 속성
// 둘 다 입력되지 않으면 true → 로그인 버튼 비활성화
const disableSubmit = computed(() => !(member.username && member.password));

const login = async () => {
  console.log(member);
  try {
    await auth.login(member);
    router.push('/');
  } catch (e) {
    // 로그인 에러
    console.log('에러=======', e);
    error.value = e.response.data;
  }
};
</script>

<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-right-to-bracket"></i>
      로그인
    </h1>

    <form @submit.prevent="login">
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i>
          사용자 ID:
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          v-model="member.username"
        />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i>
          비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          v-model="member.password"
        />
      </div>

      <div v-if="error" class="text-danger">{{ error }}</div>

      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-right-to-bracket"></i>
        로그인
      </button>
    </form>
  </div>
</template>
