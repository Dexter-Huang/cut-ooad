<template>
	<div>
		<h2>账单查询</h2>
	</div>
</template>

<script>
	import axios from "axios";
	export default {
		name: "BillInfo",
		created() {
			this.page(1)
		},
		data(){
			return {
				currentPage: 1,
				total: 0,
				pageSize: 1,
			}
		},
		methods:{
			page(currentPage) {

			},
		}
	}
</script>

<style scoped>

</style>
