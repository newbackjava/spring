<script setup>
import { useRoute } from 'vue-router';
const cr = useRoute();

const props = defineProps({
  travel: { Type: Object, required: true },
});
</script>

<template>
  <div class="card" style="width: 100%">
    <router-link
      :to="{
        name: 'travel/detail',
        params: { no: travel.no },
        query: cr.query,
      }"
    >
      <img
        class="card-img-top"
        :src="travel.images[0].url"
        :alt="travel.title"
      />
    </router-link>

    <div class="card-body">
      <h5 class="card-title">
        <router-link
          :to="{
            name: 'travel/detail',
            params: { no: travel.no },
            query: cr.query,
          }"
        >
          {{ travel.title }}
        </router-link>
      </h5>
      <p class="card-text">{{ travel.description.substring(0, 250) }}</p>
    </div>
  </div>
</template>

<style>
/* 카드 이미지에 고정 높이 부여 + 비율 유지되도록 설정 */
.card-img-top {
  height: 200px;
  object-fit: cover; /* 이미지를 잘라서 공간에 맞게 채움 */
}

/* 텍스트가 길 경우 ... 처리 + 두 줄까지만 표시 */
.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2; /* 최대 줄 수 2줄로 제한 */
  -webkit-box-orient: vertical; /* 수직 박스 방향 설정 */
}

/* 링크 밑줄 제거 */
a {
  text-decoration: none;
}
</style>
