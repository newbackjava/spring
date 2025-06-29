<script setup>
import { reactive, ref } from 'vue'; // 반응형 객체와 ref를 사용하기 위해 import
import { useRouter } from 'vue-router'; // 라우터 기능 사용
import authApi from '@/api/authApi'; // 인증 관련 API 모듈 import

const router = useRouter(); // 라우터 인스턴스 생성
const avatar = ref(null); // 아바타 파일 참조용 ref
const checkError = ref(""); // 사용자 ID 중복 체크 결과 메시지

const member = reactive({	// 회원 정보 저장용 객체
    username: 'hong',       // 테스트용 초기 ID
    email: 'hong@gmail.com',// 테스트용 초기 이메일
    password: '12',         // 테스트용 초기 비밀번호
    password2: '12',        // 테스트용 비밀번호 확인
    avatar: null,           // 아바타 이미지 파일
});

const disableSubmit = ref(true); // 회원가입 버튼 비활성화 여부

// 사용자 ID 중복 체크 함수
const checkUsername = async () => {
    if (!member.username) { // ID 미입력 시 알림
        return alert('사용자 ID를 입력하세요.');
    }

    // API로 중복 체크 후 결과 저장
    disableSubmit.value = await authApi.checkUsername(member.username);
    console.log(disableSubmit.value, typeof disableSubmit.value); // 결과 디버깅 로그
    checkError.value = disableSubmit.value ? '이미 사용중인 ID입니다.' : '사용가능한 ID입니다.'; // 메시지 설정
};

// 사용자 ID 입력 시 호출되는 핸들러
const changeUsername = () => {
    disableSubmit.value = true; // 다시 중복 체크 필요하므로 비활성화
    if (member.username) {
        checkError.value = 'ID 중복 체크를 하셔야 합니다.'; // 안내 메시지
    } else {
        checkError.value = ''; // 입력 없을 시 메시지 제거
    }
};

// 회원가입 제출 함수
const join = async () => {
    if (member.password != member.password2) { // 비밀번호 불일치 확인
        alert('비밀번호가 일치하지 않습니다.');
    }

    if (avatar.value.files.length > 0) { // 아바타 파일이 존재할 경우
        alert("첨부파일이 존재합니다.");
        member.avatar = avatar.value.files[0]; // 파일 객체 저장
    }

    try {
        await authApi.create(member); // 회원가입 API 호출
        router.push({ name: 'home' }); // 성공 시 홈으로 이동
    } catch (e) {
        console.error(e); // 오류 로그 출력
    }
};
</script>

<template>
    <div class="mt-5 mx-auto" style="width: 500px">
        <h1 class="my-5">
            <i class="fa-solid fa-user-plus"></i>
            회원 가입
        </h1>
        <form  @submit.prevent="join"> <!-- 제출 시 페이지 새로고침 방지, join 함수 호출 -->
            <div class="mb-3 mt-3">
                <label for="username" class="form-label">
                    <i class="fa-solid fa-user"></i>
                    사용자 ID :
                    <button type="button" 
                    class="btn btn-success btn-sm py-0 me-2"
                    @click="checkUsername"> <!-- 중복 확인 버튼 클릭 시 함수 호출 -->
                        ID 중복 확인</button>
                     <span :class="disableSubmit.value ? 'text-primary' : 'text-danger'">{{ checkError }}</span> <!-- 중복 확인 결과 메시지 -->
                </label>
                <input type="text" class="form-control" 
                placeholder="사용자 ID" id="username"
                @input="changeUsername" v-model="member.username" /> <!-- 입력 시 핸들러 호출 + 양방향 바인딩 -->
                <!-- // 입력을 하면 자동으로 "중복체크하셔야합니다. 메시지를 띄우기위해 함수를 호출함." -->
            </div>

            <div>
                <label for="avatar" class="form-label">
                    <i class="fa-solid fa-user-astronaut"></i>
                    아바타 이미지:
                </label>
                <input type="file" class="form-control" ref="avatar" id="avatar" accept="image/png, image/jpeg" /> <!-- 파일 업로드 참조 -->
            </div>

            <div class="mb-3 mt-3">
                <label for="email" class="form-label">
                    <i class="fa-solid fa-envelope"></i>
                    email
                </label>
                <input type="email" class="form-control" 
                placeholder="Email" id="email" 
                v-model="member.email" /> <!-- 이메일 입력과 데이터 바인딩 -->
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">
                    <i class="fa-solid fa-lock"></i> 비밀번호:
                </label>
                <input type="password" class="form-control" 
                placeholder="비밀번호" id="password" v-model="member.password"/> <!-- 비밀번호 입력 (v-model 없음) -->
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">
                    <i class="fa-solid fa-lock"></i> 비밀번호 확인:
                </label>
                <input type="password" class="form-control" placeholder="비밀번호 확인" id="password2" v-model="member.password2" /> <!-- 비밀번호 확인 입력 (v-model 없음) -->
            </div>

            <button type="submit" class="btn btn-primary mt-4">
                <i class="fa-solid fa-user-plus"></i>
                확인
            </button>
        </form>
    </div>
</template>

<style scoped></style> <!-- 컴포넌트 범위로 스타일 제한 -->