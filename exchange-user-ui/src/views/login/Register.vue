<template>
  <body>
	<div id = "Register">
		<div  id = "admin">
			<div class = "pos">
				<h1 class = "adminh1">Royal酒店,畅然而居  </h1>
				<h2 class = "adminh1"> 用户注册</h2>
				<el-form :model="registerForm" ref="form" :rules="registerRules" label-width="auto">
					<el-row >
						<el-col :span="12">
							<el-form-item prop="name" :span="12">
								<el-input v-model="registerForm.name" placeholder="name" />
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item prop="age" :span="12">
								<el-input v-model="registerForm.age" placeholder="age" />
							</el-form-item>
						</el-col>
					</el-row>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-row>
						<el-col :span="12">
							<el-form-item prop="password">
								<el-input v-model="registerForm.password" placeholder="password" >
								</el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item prop="phone">
								<el-input v-model="registerForm.phone" placeholder="phone">
								</el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item prop="email">
						<el-input v-model="registerForm.email" placeholder="email"/>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item prop="sex">
						<el-radio-group v-model="registerForm.sex">
							<el-radio label="MAN" value="MAN">
								<el-tag type="success" disable-transitions>
									MAN
								</el-tag>
							</el-radio>
							<el-radio label="WOMAN" value="WOMAN">
								<el-tag type="success" disable-transitions>
									WOMAN
								</el-tag>
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item prop="avatar">
						<el-upload
										class="upload-demo" action="http://localhost:8080/upload/image1"
										accept="image/jpeg,image/gif,image/png"
										drag multiple
										style="width: 100%"
										:limit="1"
										:before-upload="beforeUpload"
										:on-success="handleAvatarSuccess"
										:on-exceed="handleExceed">
							<el-icon class="el-icon--upload"><upload-filled /></el-icon>
							<div class="el-upload__text">
								Drop Your Avatar here or <em>click to upload</em>
							</div>
						</el-upload>
					</el-form-item>
					<label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<el-form-item>
						<el-button type="primary" @click="SignUpForm('form')">Sign up</el-button>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<el-button @click="this.$router.push('/')">Cancel</el-button>
					</el-form-item>
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
		name: "Register",
		data() {
			return {
				registerForm:{
					uuid:'',
					name:'',
					avatar:'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/user_avatar/default.jpg',
					email:'',
					credit:0,
					password:'',
					phone:'',
					sex:'',
					status:'',
					userType:'',
					age:'',
					created:'',
					lastLogin:'',
					IdNum:'',
					accountType:'USER',

				},
				registerRules:{
					name:[{required:true,message:'用户名不能为空',trigger:'blur'},
						{min:3, max:20, message: '长度在 3 到 20 个字符',trigger: 'blur'},
						{validator:this.checkName,trigger: 'blur'}],
					credit:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					phone:[{required:true,message:'用户名不能为空',trigger:'blur'},
						{validator:this.checkPhone,trigger: 'blur'}],
					sex:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					status:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					userType:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					age:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					password: [
						{ required: true, message: "请输入用户密码", trigger: "blur" },
						{ min: 6, max: 12, message: "长度在 6 到 12 个字符", trigger: "blur" }
					],
					email: [
						{ required: true, message: "请输入用户邮箱", trigger: "blur" },
						{ pattern:/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/, message: "邮箱格式不正确", trigger: "blur" }
					],
				},
			};
		},
		methods: {
			SignUpForm(formName){
				this.$refs[formName].validate((valid)=>{
					if(valid){
						console.log('addStaff')
						axios.post('account/userregister',this.registerForm).then(res=>{
							if (res.data.code==200){
								this.$message.success(res.data.msg)
								this.$router.push('/account/login')
							}else{
								this.$message.warning(res.data.msg)
							}
						})
					}else{
						return false;
					}
				})
			},

			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			checkName(rule, value, callback){
				axios.get('/user/checkname?name='+value).then(res=>{
					console.log(res)
					if(res.data.msg=='已存在该用户'){
						callback(new Error("已存在同名用户"))
					}else{
						callback();
					}
				})
			},
			checkPhone(rule, value, callback){
				console.log(value)
				axios.get('/user/checkphone?phone='+value).then(res=>{
					if(res.data.msg=='已存在该用户'){
						callback(new Error("该手机号已被注册"))
					}else{
						callback();
					}
				})
			},
			//上传文件成功的操作
			handleAvatarSuccess(res,file){
				if(res.code==200){
					this.registerForm.avatar=res.data
					this.$message({
						type:'success',
						message:'上传成功'
					});
				}else{
					this.$message({
						type:'warning',
						message:'上传失败'
					});
				}

			},
			//上传文件之前
			beforeUpload(file){
				// console.log('type',file.type)
				let isIMAGE=false
				if(file.type=='image/jpeg'||file.type=='image/gif'||file.type=='image/png'){
					isIMAGE=true;
				}
				const isLt10M = file.size / 1024 / 1024 < 10;
				// console.log('1',isIMAGE)
				// console.log('2',isLt10M)
				if (!isIMAGE) {
					this.$message.error('上传文件只能是图片格式!');
				}
				if (!isLt10M) {
					this.$message.error('上传文件大小不能超过 10MB!');
				}
				return isIMAGE && isLt10M;
			},
			//超出文件个数的回调
			handleExceed(){
				this.$message({
					type:'warning',
					message:'超出最大上传文件数量的限制！'
				});return
			},
		}
	}
</script>

<style lang="less" scoped>

body{
  background:url("VCG21135494558.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
}
	#Register {
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
		height: 750px;
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
