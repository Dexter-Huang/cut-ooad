<template>
	<div class="common-layout">
		<el-row>
			<el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="20" >
				<el-row style="margin-right: 0.5rem">
					<UserHeaderPage style="width: 100%;"></UserHeaderPage>
				</el-row>
				<el-row  style="margin-top: 0.5rem;margin-right: 0.5rem">
					<UserCreditPane style="width: 100%;"></UserCreditPane>
				</el-row>
			</el-col>
			<el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="4">
				<RightSide></RightSide>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import UserHeaderPage from "@/views/user/userHeaderPage";
	import RightSide from "@/views/RightSide"
	import UserCreditPane from '@/views/user/userCredit/userCreditPane'
	export default {
		name: "userCredit",
		components:{
			RightSide,
			UserHeaderPage,
			UserCreditPane
		},
		data(){

		},
		created() {
		},
	}
</script>

<style scoped>

</style>
