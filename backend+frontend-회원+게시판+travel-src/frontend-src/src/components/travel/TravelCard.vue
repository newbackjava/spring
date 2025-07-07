<script setup>
import { useRoute } from 'vue-router';
const cr = useRoute();

const props = defineProps({
  travel: { Type: Object, required: true },
});
</script>

<template>
  <div class="card" style="width: 100%">
    <router-link :to="{ name: 'travel/detail', params: { no: travel.no }, query: cr.query }">
      <img class="card-img-top" :src="travel.images[0].url" :alt="travel.title" />
    </router-link>

    <div class="card-body">
      <h5 class="card-title">
        <router-link :to="{ name: 'travel/detail', params: { no: travel.no }, query: cr.query }">
          {{ travel.title }}
        </router-link>
      </h5>
      <p class="card-text">{{ travel.description.substring(0, 250) }}</p>
    </div>
  </div>
</template>
<style>
.card-img-top {
  height: 200px;
  object-fit: cover;
}

.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

a {
  text-decoration: none;
}
</style>

