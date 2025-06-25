<script setup>
import { computed } from "vue";
import MenuItem from "./MenuItem.vue";
import AccountMenuItem from "./AccountMenuItem.vue";
import LogoutMenuItem from "./LogoutMenuItem.vue";
import config from "@/config";

const { login, join } = config.accoutMenus;
import { useAuthStore } from "@/stores/auth.js";

const auth = useAuthStore();

const islogin = computed(() => auth.isLogin);
const username = computed(() => auth.username);
</script>

<template>
  <ul class="navbar-nav ms-auto">
    <template v-if="islogin">
      <AccountMenuItem :username="username" />
      <LogoutMenuItem />
    </template>
    <template v-else>
      <MenuItem :menu="login" />
      <MenuItem :menu="join" />
    </template>
  </ul>
</template>
