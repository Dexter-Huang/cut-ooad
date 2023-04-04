<template>
	<div>
		<el-card>
			<el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
				<el-tab-pane label="全部订单" name="first"><user-order-pane :type="-1"/></el-tab-pane>
				<el-tab-pane label="已预订" name="second"><user-order-pane :type="1"/></el-tab-pane>
				<el-tab-pane label="待付款" name="third"><user-order-pane :type="0"/></el-tab-pane>
				<el-tab-pane label="待评价" name="fourth"><user-order-pane :type="3"/></el-tab-pane>
			</el-tabs>
		</el-card>
	</div>
</template>

<script>
	import userOrderPane from "@/views/user/userBill/userBillPane";
	export default {
		name: "userBill",
		components:{
			userOrderPane
		},
		data(){
			return{
				activeName:'first'
			}
		},
		methods:{

		},
	}
</script>

<style scoped>

</style>
