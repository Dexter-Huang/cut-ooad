<template>
  <body>
	<div class="common-layout">
		<div style="text-align: center">Royal酒店-畅然而居</div>
		<div style="margin: 3rem auto; background: white; width: 30rem; border-radius: 1rem;">
			<div style="text-align: center">设置新密码</div>
			<el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
				<el-form-item prop="name">
					<el-input v-model="ruleForm.name" placeholder="username"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" v-model="ruleForm.password" placeholder="new password"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="SureReset('ruleForm')">确认</el-button>
					<el-button type="primary" @click="this.$router.push('/notices')">取消</el-button>
				</el-form-item>
			</el-form>
		</div>

	</div>
  </body>
</template>

<script>
	import axios from 'axios';
	import {ElMessage} from "element-plus";
	export default {
		name: "ResetPassword",
		created() {
			this.ruleForm.name=this.$store.getters.getUser.name
		},
		data() {
			return {
				ruleForm: {
					name: '',
					password: '',
					phone:'',
					email:'',
					captchaCode: '111111',
					accountType: 'USER',
					loginType:'name'
				},
				rules: {
					name: [
						{ required: true, message: '请输入用户名', trigger: 'blur' },
						{ min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
					],
					password: [
						{ required: true, message: "请输入用户密码", trigger: "blur" },
						{ min: 6, max: 12, message: "长度在 6 到 12 个字符", trigger: "blur" }
					]
				},
			};
		},
		methods: {
			SureReset(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						console.log(this.ruleForm)
						axios.post('/account/resetpassword', this.ruleForm,
                {headers: {"Authorization": localStorage.getItem("token")}})
							.then(res => {
							if (res.data.code==200){
								this.$message.success(res.data.msg)
							}else{
								this.$message.warning(res.data.msg)
							}
							console.log(res);
							this.$router.push('/hotelInfo')
						})

					} else {
						// console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>

<style lang="less" scoped>

body{
  background:url("VCG21135494558.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
}
	#ResetPassword {
		font-family: "Avenir", Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		color: #2c3e50;
	}
	#admin {
		position: absolute;
		top: 30%;
		left: 50%;
		margin-top: -200px;
		margin-left: -250px;
		width: 500px;
		height: 450px;
		background: #fff;
		border-radius: 10%;
		box-shadow: 8px 10px 10px rgb(177, 223, 242);
		text-align: center;
	}
	.adminh1 {
		margin: 20px 0;
		/*text-shadow: 10px 13px 3px rgb(207, 207, 207);*/
		text-align: center;
	}
	.pos {
		width: 450px;
		height: 350px;
		position: absolute;
		top: 25px;
		left: 25px;
	}
</style>
