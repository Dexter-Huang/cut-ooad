<template>
	<div class="common-layout" style="opacity: 0.85">
		<el-row>
			<el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="20" >
				<el-row style="margin-right: 0.5rem">
					<UserHeaderPage style="width: 100%;opacity: 0.85"></UserHeaderPage>
				</el-row>
				<el-row  style="margin-top: 0.5rem;margin-right: 0.5rem">
					<UserOrder style="width: 100%;opacity: 0.85;"></UserOrder>
				</el-row>
				<el-row style="margin-top: 0.5rem;margin-right: 0.5rem">

				</el-row>
			</el-col>
			<el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="4">
				<RightSide style="opacity: 0.85"></RightSide>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import axios from 'axios'
	import UserHeaderPage from "@/views/user/userHeaderPage";
	import UserOrder from "@/views/user/userBill/userBill";

	import RightSide from "@/views/RightSide"
	export default {
		name: "userInfo",
		components:{
			RightSide,
			UserHeaderPage,
			UserOrder,
		},
		data(){
			return{
				basicInfo:[],
				detailInfo:[],
				currentName: this.$store.getters.getUser.name,
				searchRes: ''
			}
		},
		created() {
			this.basicInfo=this.$store.getters.getUser;
			console.log(this.basicInfo)
			axios.get('/account/getuserinfo/'+this.basicInfo.id,
				{headers: {"Authorization": localStorage.getItem("token")}}
			).then(res=>{
				// console.log(res);
				if(res.data.data==='用户凭证错误'){
					this.$router.push("/account/reloginnotice")
				}else{
					// this.$message.warning('用户凭证过期或错误')
					// console.log('jjj')
				}
			})
			axios.post('/user/searchcredit', {name: this.currentName}).then(res => {
				if (res.data.code === 200) {
					this.searchRes = res.data.data
				} else {
					this.$message.warning('查看积分失败')
				}
			})
		},
	}
</script>

<style lang="scss" scoped>
.common-layout /deep/ {
  background:url("../user/coupon.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
}
	//如果是单独设置表头换行

	.el-table th>.cell {
		white-space: pre-wrap;
	}


	//或者全局换行
	.el-table{
		.cell {
			white-space: pre-wrap;
		}
	}

	.el-tag{
		white-space: normal;
		height:auto;
	}
</style>
