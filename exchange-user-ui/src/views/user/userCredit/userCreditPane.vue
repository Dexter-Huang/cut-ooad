<template>
	<div>
		<el-card style="width: 100%">
			我的积分: {{this.userCredit}}
		</el-card>
		<el-button @click="gotoGift"> 前往积分兑换</el-button>
	</div>
</template>

<script>
	import axios from "axios";

	export default {
		name: "userCreditPane",
		data(){
			return{
				userCredit: ''
			}
		},
		created() {
			axios.post('/user/searchcredit', {name: this.$store.getters.getUser.name}).then(res => {
				if (res.data.code === 200) {
					this.userCredit = res.data.data
				} else {
					this.$message.warning('查看积分失败')
				}
			})
		},
		methods:{
			gotoGift(){
				this.$router.push('/giftCards')
			}
		},

	}
</script>

<style scoped>

</style>
