<template>
	<div>
		<el-row >
			<el-col :span="12">
				<el-card>职工积分</el-card>
			</el-col>
			<el-col :span="12">
				<el-card @click="this.$router.push('/notices')">消息通知</el-card>
			</el-col>
		</el-row>
		<el-row  style="margin-top: 0.5rem">
			<el-card style="width: 100%">
				Royal职工社区
			</el-card>
		</el-row>
		<el-row style="margin-top: 0.5rem">
			<el-card style="width: 100%" @click="this.$router.push('/userCredit')">
				Royal职工积分: {{this.staffCredit}}
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
				staffCredit: ''
			}
		},
		created() {

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
