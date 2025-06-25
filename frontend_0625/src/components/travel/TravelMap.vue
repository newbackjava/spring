<script setup>
// https://vue3-kakao-maps-docs.vercel.app/components/kakaoMap
import { reactive, ref } from 'vue';
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';

const props = defineProps({
  title: { Type: String, required: true },
  address: { Type: String, required: true },
});

const coordinate = reactive({
  lat: 37.566826,
  lng: 126.9786567,
});

const map = ref();

const onLoadKakaoMap = (mapRef) => {
  map.value = mapRef; // 지도 객체
  //향후 지도에 대해서 직접 제어시 map.value로 처리

  const geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색
  geocoder.addressSearch(props.address, (result, status) => {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
      coordinate.lat = result[0].y;
      coordinate.lng = result[0].x;
    }
  });
};

const visibleRef = ref(false);

const onClickKakaoMapMarker = () => {
  visibleRef.value = !visibleRef.value;
};
</script>

<template>
  <div><i class="fa-solid fa-map-location-dot"></i> 주소: {{ address }}</div>
  <kakao-map :lat="coordinate.lat" :lng="coordinate.lng" :level="3" :draggable="true" style="width: 100%" @onLoadKakaoMap="onLoadKakaoMap">
    <kakao-map-marker :lat="coordinate.lat" :lng="coordinate.lng" :clickable="true" :infoWindow="{ content: title, visible: visibleRef }" @onClickKakaoMapMarker="onClickKakaoMapMarker" />
  </kakao-map>
</template>
