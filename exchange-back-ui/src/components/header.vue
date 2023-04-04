<template>
	<div>
		<h3>欢迎来到Hotel</h3>
		<div class="block">
			<el-avatar :size="50" :src="user.avatar"></el-avatar>
			<div>{{ user.name }}</div>
		</div>

		<div class="maction">
			<span><el-link href="/notices">主页</el-link></span>
			<el-divider direction="vertical"></el-divider>
			<span><el-link type="success" href="/notice/add">发表博客</el-link></span>

			<el-divider direction="vertical"></el-divider>
			<span v-show="!hasLogin"><el-link type="primary" href="/account/login">登录</el-link></span>

			<span v-show="hasLogin"><el-link type="danger" @click="logout">退出</el-link></span>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		name: "header",
		data() {
			return {
				user: {
					name: '请先登录',
					avatar: 'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/user_avatar/default.jpg'
				},
				hasLogin: false
			}
		},
		methods: {
			logout() {
				axios.get("/account/logout", {
					headers: {
						"Authorization": localStorage.getItem("token")
					}
				}).then(res => {
					this.$store.commit("REMOVE_INFO")
					this.$router.push("/account/login")

				})
			}
		},
		created() {
			if(this.$store.getters.getUser) {
				this.user.name = this.$store.getters.getUser.name
				this.user.avatar = this.$store.getters.getUser.avatar
				this.hasLogin = true
			}

		}
	}
</script>

<style scoped>

</style>