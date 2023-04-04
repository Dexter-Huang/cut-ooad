<template>
	<div>
		<el-card>
			重新登陆
		</el-card>
		<el-button @click="relogin">
			重新登陆
		</el-button>
	</div>
</template>

<script>
	export default {
		name: "ReLoginNotice",
		methods:{
			relogin(){
				this.$router.push('/account/login')
			}
		},
	}
</script>

<style scoped>

</style>
