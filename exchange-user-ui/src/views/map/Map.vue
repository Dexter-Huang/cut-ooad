<template>
	<div id="index_container">
		<div id="search_wrap">
			<Search></Search>
		</div>
		<div id="map_wrap" style="margin-top: 2rem" >
			<MapContainer ></MapContainer>
		</div>
	</div>
</template>

<script>
	import Search from './search/Search'
	import MapContainer from "@/views/map/container/MapContainer"
	// import HeatMapSearchBox from '@/components/heatMapSearchBox/heatMapSearchBox.vue'
	export default {
		name: "Map.vue",
		props:["lat","lng"],
		components: {MapContainer, Search},
		created() {
			console.log("map ",this.lat,this.lng)
		}
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
		height: 95%;
		width: 95%;
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
