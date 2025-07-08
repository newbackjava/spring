<script setup>
import api from '@/api/travelApi';
import { ref, reactive, computed, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import TravelCard from '@/components/travel/TravelCard.vue';

const cr = useRoute();
const router = useRouter();

const page = ref({});

const travels = computed(() => page.value.list);

const pageRequest = reactive({
  page: parseInt(cr.query.page) || 1,
  amount: parseInt(cr.query.amount) || 12,
});
// 페이지네이션 페이지 변경
const handlePageChange = async (pageNum) => {
  router.push({
    query: { page: pageNum, amount: pageRequest.amount },
  });
};

//cr(라우트 객체)을 감시(watch)해서 변경되면, pageRequest객체 키 값을 변경함.
watch(cr, async (newValue) => {
  pageRequest.page = parseInt(cr.query.page);
  pageRequest.amount = parseInt(cr.query.amount);
  await load(pageRequest);
});

const load = async (query) => {
  try {
    page.value = await api.getList(query);
    console.log(page.value);
  } catch {}
};
load(pageRequest);
</script>
<template>
  <div>
    <h1 class="mb-3">
      <i class="fa-solid fa-person-walking-luggage"></i> 여행지 목록
    </h1>
    <div class="">전체 여행지: {{ page.totalCount }}건</div>
    <div class="row">
      <div
        class="col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3"
        v-for="travel in travels"
        :key="travel.no"
      >
        <travel-card :travel="travel" />
      </div>
    </div>
    <div class="mt-5 flex-grow-1 text-center">
      <vue-awesome-paginate
        :total-items="page.totalCount"
        :items-per-page="pageRequest.amount"
        :max-pages-shown="5"
        :show-ending-buttons="true"
        v-model="pageRequest.page"
        @click="handlePageChange"
      >
        <template #first-page-button
          ><i class="fa-solid fa-backward-fast"></i
        ></template>
        <template #prev-button><i class="fa-solid fa-caret-left"></i></template>
        <template #next-button
          ><i class="fa-solid fa-caret-right"></i
        ></template>
        <template #last-page-button
          ><i class="fa-solid fa-forward-fast"></i
        ></template>
      </vue-awesome-paginate>
    </div>
  </div>
</template>
