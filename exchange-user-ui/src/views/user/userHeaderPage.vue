<template>
	<div>
		<el-card >
			<el-container>
				<el-main>
					<el-row>
						<el-avatar :size="70" :src="this.userInfo.avatar" ></el-avatar>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<el-card>用户名: {{this.userInfo.name}}</el-card>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<el-card>用户等级: {{this.userInfo.userType}}</el-card>
					</el-row>
				</el-main>
				<el-footer style="height: 2rem;">
					<el-row >
						<el-col :span="8" style="text-align: center">
							<router-link :to="{name:'userGift'}">我的礼品卡</router-link>
						</el-col>
						<el-col :span="8" style="text-align: center">
							<router-link :to="{name:'userCredit'}">我的积分</router-link>
						</el-col>
					</el-row>
				</el-footer>
			</el-container>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: "userHeaderPage",
		data(){
			return{
				userInfo:[],
			}
		},
		created() {
			this.userInfo=this.$store.getters.getUser;
			console.log(this.userInfo.avatar)
		},
		methods:{

		}
	}
</script>

<style scoped>

</style>
