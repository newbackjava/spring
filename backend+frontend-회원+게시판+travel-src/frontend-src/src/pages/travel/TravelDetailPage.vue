<script setup>
import TravelHeader from '@/components/travel/TravelHeader.vue';
import TravelImages from '@/components/travel/TravelImages.vue';
import TravelMap from '@/components/travel/TravelMap.vue';

import { useRoute, useRouter } from 'vue-router';
import api from '@/api/travelApi';
import { ref } from 'vue';

const cr = useRoute();
const router = useRouter();

const no = cr.params.no;
const travel = ref({});

const back = () => {
  router.push({ name: 'travel/list', query: cr.query });
};

// 여행지 정보를 비동기로 불러오는 함수
const load = async () => {
  // API로부터 여행지 데이터를 받아와 travel에 저장
  travel.value = await api.get(no);

  // 설명 내용에서 '. 공백'이 나올 때마다 <p/><p> 태그를 추가해서 문단 나눔
  // "마침표 + 공백"을 문자열 전체에서 줄 단위로 찾아라!
  //   /~~/ → 정규표현식의 시작과 끝
  //  \.공백 → 마침표앞에는 \써주어야 문자로 인식함. .(마침표)와 공백이 나오면 이라는 정규표현식
  // g → 문자열 전체에서 해당 패턴을 모두 찾음
  // m → multiline (다중 줄 모드), 여러 줄에 다 적용
  let descriptions = travel.value.description.replace(
    /\. /gm,
    (t) => t + '<p/><p>'
  );
  travel.value.description = `<p>${descriptions}</p>`;
};

// 페이지가 열리자마자 load 함수 실행해서 여행지 정보 불러옴
load();
</script>

<template>
  <!-- 여행지 제목/헤더 정보를 보여주는 컴포넌트 -->
  <travel-header :travel="travel" />

  <!-- 여행지 설명을 HTML 형식으로 출력 -->
  <div class="content" v-html="travel.description"></div>

  <!-- 여행지 사진들을 보여주는 컴포넌트 -->
  <travel-images :images="travel.images"></travel-images>

  <!-- 여행지 전화번호를 아이콘과 함께 출력 -->
  <div>
    <i class="fa-solid fa-square-phone-flip"></i> 전화번호: {{ travel.phone }}
  </div>

  <!-- 지도 위치 -->
  <travel-map :address="travel.address" :title="travel.title" />

  <div class="my-5">
    <button class="btn btn-primary me-2" @click="back">
      <i class="fa-solid fa-list"></i> 목록
    </button>
  </div>
</template>
