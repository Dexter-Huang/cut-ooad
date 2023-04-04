<template>
  <body>
	<div id = "ForgetPassword">
		<div  id = "admin">
			<div class = "pos">
				<!--        <span>返回</span>-->
				<h1 class = "adminh1">Royal酒店,畅然而居  </h1>
				<h2 class = "adminh1"> 忘记密码</h2>
				<el-form :model="emailForm" status-icon :rules="emailRules" ref="emailForm" label-width="20px" class="demo-ruleForm">
					<el-form-item prefix-icon="iconfont icon-sousuo"  prop="name">
						<el-input v-model="emailForm.name" placeholder="username"></el-input>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item prefix-icon="iconfont icon-sousuo"  prop="email">
						<el-input type="email" v-model="emailForm.email" placeholder="email"></el-input>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item  prop="captchaCode">
						<el-row>
							<el-col :span="12">
								<el-input v-model="emailForm.captchaCode" placeholder="captcha"></el-input>
							</el-col>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<el-col :span="5">
								<el-button type="primary" :disabled="this.emailCaptchaDisabled" @click="getEmailCaptchaCode">{{this.captchaButtonContent}}</el-button>
							</el-col>
						</el-row>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item>
						<el-button type="primary" @click="EmailLoginForm('emailForm')">确认</el-button>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<el-button type="primary" @click="this.$router.push('/account/login')">取消</el-button>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<el-button @click="resetForm('emailForm')">重置</el-button>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
				</el-form>
			</div>
		</div>

	</div>
  </body>
</template>

<script>
	import axios from 'axios';
	import {ElMessage} from "element-plus";
	export default {
		name: "ForgetPassword",
		created() {
		},
		data() {
			return {
				emailCaptchaDisabled:false,
				captchaButtonContent:'获取验证码',
				timeDiff:60,
				emailForm: {
					name: '',
					password: '----',
					phone:'',
					email:'',
					captchaCode: '',
					accountType: 'USER',
					loginType:'email',
				},
				emailRules: {
					name: [
						{ required: true, message: '请输入用户名', trigger: 'blur' },
						{ min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
					],
					email: [
						{ required: true, message: "请输入用户邮箱", trigger: "blur" },
						{ pattern:/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/, message: "邮箱格式不正确", trigger: "blur" }
					],
					captchaCode:[
						{ required: true, message: "请输入邮箱验证码", trigger: "blur" },
					],
				}
			};
		},
		methods: {
			RefleshImg() {
				console.log('HHHHHHHH')
				// this.("#captcha_img").attr("src","http://localhost:8080/api/v1/captcha/get_captcha")
				// $('#captcha_img').reload();
			},
			SignUpForm(formName){
				this.$refs[formName].validate((valid)=>{
					if(valid){
						console.log('addStaff')
						axios.post('account/addstaff')
					}else{
						return false;
					}
				})
			},
			LoginForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						axios.post('/account/userlogin', this.ruleForm).then(res => {
							const jwt = res.headers['authorization']
							const userInfo = res.data.data
							// console.log(userInfo)
							// console.log(jwt)
							// 把数据共享出去
							this.$store.commit("SET_TOKEN", jwt)
							this.$store.commit("SET_USERINFO", userInfo)
							// 获取
							// console.log(this.$store.getters.getUser)

							this.$router.push("/notices")

						})

					} else {
						// console.log('error submit!!');
						return false;
					}
				});
			},
			EmailLoginForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						axios.post('/account/userlogin', this.emailForm).then(res => {
							const jwt = res.headers['authorization']
							const userInfo = res.data.data
							// console.log(userInfo)
							// console.log(jwt)
							// 把数据共享出去
							this.$store.commit("SET_TOKEN", jwt)
							this.$store.commit("SET_USERINFO", userInfo)
							// 获取
							// console.log(this.$store.getters.getUser)

							this.$router.push("/account/resetpasswword")
						})

					} else {
						// console.log('error submit!!');
						return false;
					}
				});
			},

			getEmailCaptchaCode(){
				if(!this.emailForm.email){
					this.$message.warning("请输入邮箱账号!")
					return;
				}else{
					//
					// this.emailForm.email=JSON.stringify(this.emailForm.email)
					console.log(this.emailForm.email)
					axios.post('/captcha/email_captcha',this.emailForm).then(res=>{
						if(res.data.code===200){
							ElMessage({
								type: 'success',
								message: '发送成功',
							})
							let clock = window.setInterval(() => {
								this.timeDiff--
								this.emailCaptchaDisabled=true
								this.captchaButtonContent=this.timeDiff+'s后再试'
								if (this.timeDiff < 0) {
									this.captchaButtonContent = '获取验证码'
									window.clearInterval(clock)
									this.timeDiff = 60
									this.emailCaptchaDisabled = false
									return;
								}
							}, 1000)
						}else{
							ElMessage({
								type:'warning',
								message:res.data.msg
							});
						}
					})
				}
			},

			SignInForm(formName) {
				// this.$refs[formName].validate((valid)=>{
				// 	if(valid){
				// 		axios.post('/signIn')
				// 	}
				// })
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
	#ForgetPassword{
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
		height: 470px;
		background: #fff;
		border-radius: 10%;
		box-shadow: 8px 10px 10px rgb(177, 223, 242);
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
