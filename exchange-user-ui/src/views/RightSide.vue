<template>
	<div>
		<el-row >
			<el-col :span="8">
				<el-card @click="this.$router.push('/userCredit')">
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/creditChange2.jpg`"></el-image>
<!--					积分兑换-->
				</el-card>
			</el-col>
			<el-col :span="8">
				<el-card @click="this.$router.push('/userMessage')">
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/noticeInform.jpg`"></el-image>
<!--					消息通知-->
				</el-card>
			</el-col>
			<el-col :span="8">
				<el-card>
					<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/viewview.jpg`"></el-image>
<!--					点评收藏-->
				</el-card>
			</el-col>
		</el-row>
		<el-row  style="margin-top: 0.5rem">
			<el-card @click="this.$router.push('/imserver')">
				<el-image :src="`https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/coupon/giftCard/commu.jpg`"></el-image>
			</el-card>
		</el-row>
		<el-row style="margin-top: 0.5rem">
			<el-card style="width: 100%" @click="this.$router.push('/userCredit')">
				我的积分: {{this.userCredit}}
			</el-card>
		</el-row>
	</div>
</template>

<script>
	import axios from "axios";

	export default {
		name: "RightSide",
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

		}
	}
</script>

<style scoped>
	.el-card :hover{
			cursor: pointer;
		}

</style>
