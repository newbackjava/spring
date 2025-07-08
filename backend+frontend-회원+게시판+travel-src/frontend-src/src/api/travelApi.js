import api from '@/api';

const BASE_URL = '/api/travel';

export default {
  async getList(params) {
    const { data } = await api.get(BASE_URL, { params });
    console.log('TRAVEL GET LIST: ', data);
    return data;
  },

  async get(no) {
    const { data } = await api.get(`${BASE_URL}/${no}`);
    console.log('TRAVEL GET', data);
    return data;
  },
};
