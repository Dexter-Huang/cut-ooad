<template>
	<div id="index_container">
		<div id="search_wrap">
			<Search></Search>
		</div>
		<div id="map_wrap">
			<MapContainer></MapContainer>
		</div>
		<div id="shadow">
			<HeatMapSearchBox></HeatMapSearchBox>
		</div>
	</div>
</template>

<script>
	import Search from '../components/search/Search'
	import MapContainer from "@/components/container/MapContainer"
	// import HeatMapSearchBox from '@/components/heatMapSearchBox/heatMapSearchBox.vue'
	export default {
		name: "Map.vue",
		components: {MapContainer, Search},
	}
</script>

<style scoped>
	#index_container {
		overflow: hidden;
		height: 100%;
		width: 100%;
		position: relative;

	}

	#index_container> #map_wrap {
		z-index: 10;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		height: 60%;
		width: 60%;
	}
	#index_container> #search_wrap {
		z-index: 10;
		position: absolute;
		top: 10%;
		left: 50%;
		height: 5rem;
		width: 25rem;
		transform: translate(-50%, -50%);
	}
	#index_container> #shadow {
		height: 80%;
		width: 80%;
		position: absolute;
		top: 40%;
		left: 50%;
		transform: translate(-50%, -50%);
		background-color: rgb(255, 255, 255);
	}
	#index_container>#heatMap_wrap {
		z-index: 12;
		position: absolute;
		top: 15%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
</style>