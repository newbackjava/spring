<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
const store = useAuthStore();
const router = useRouter();
const logout = (e) => {
  // 로그아웃
  store.logout();
  router.push('/');
};
</script>
<template>
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>
