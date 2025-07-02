<script setup>
import api from '@/api/boardApi';
import { ref, reactive, computed } from 'vue';
import moment from 'moment';
import { useRoute, useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
const auth = useAuthStore();

const islogin = computed(() => auth.isLogin);
const username = computed(() => auth.username);

const cr = useRoute();
//컴포넌트를 호출할 때 입력값을 줄 수 있다.
const router = useRouter();

//이 컴포넌트가 마운트되었을 때
//자동으로 axios를 불러서 list를 가지고 와야함.
//load()함수 정의후 --> 시작하자 마자 호출
//list를 가지고 와서  articles에 넣을 예정임.

//페이징을 내일 할 예정
const page = ref({});
//페이지바뀔 때마다 데이터를 가지고 올 예정인데
//그 데이터가 바뀌는지 자동 변동 여부 설정

//페이지에 따라 가지고 온 정보가 바뀌면 바뀐 데이터로
//자동으로 계산되어 articles에 넣어주세요.
const articles = computed(() => page.value);

const load = async () => {
  try {
    page.value = await api.getList();
    console.log(page.value);
  } catch {}
};

load(); //해당 컴포넌트가 로드되자마자 load()함수를 불러서
//백엔드 서버로 부터 json으로 받아오자.
</script>

<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i> 게시글 목록</h1>

    <table class="table table-striped">
      <thead>
        <tr>
          <th style="width: 60px">No</th>
          <th>제목</th>
          <th style="width: 100px">작성자</th>
          <th style="width: 120px">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.no">
          <td>{{ article.no }}</td>
          <td>
            <router-link
              :to="{ name: 'board/detail', params: { no: article.no } }"
            >
              {{ article.title }}
            </router-link>
          </td>
          <td>
            {{ article.writer }}
          </td>
          <td>
            {{ moment(article.regDate).format('YYYY - MM - DD') }}
          </td>
        </tr>
      </tbody>
    </table>
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">페이지 페이징</div>
      <div>
        <template v-if="islogin">
          <router-link :to="{ name: 'board/create' }" class="btn btn-primary">
            <i class="fa-solid fa-pen-to-square"></i> 글 작성</router-link
          >
        </template>
      </div>
    </div>
  </div>
</template>
