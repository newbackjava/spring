import { isAuthenticated } from '@/util/guards';

export default [
  {
    path: '/travel/list',
    name: 'travel/list',
    component: () => import('../pages/travel/TravelListPage.vue'),
  },
  {
    path: '/travel/detail/:no',
    name: 'travel/detail',
    component: () => import('../pages/travel/TravelDetailPage.vue'),
  },
];
