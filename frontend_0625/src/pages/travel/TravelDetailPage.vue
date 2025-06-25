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

const load = async () => {
  travel.value = await api.get(no);

  let descriptions = travel.value.description.replace(/\. /gm, (t) => t + '<p/><p>');

  travel.value.description = `<p>${descriptions}</p>`;

  console.log(travel.value);
};

load();
</script>

<template>
  <travel-header :travel="travel" />

  <div class="content" v-html="travel.description"></div>

  <travel-images :images="travel.images"></travel-images>

  <div><i class="fa-solid fa-square-phone-flip"></i> 전화번호: {{ travel.phone }}</div>

  <travel-map :title="travel.title" :address="travel.address" />

  <div class="my-5">
    <button class="btn btn-primary me-2" @click="back"><i class="fa-solid fa-list"></i> 목록</button>
  </div>
</template>
