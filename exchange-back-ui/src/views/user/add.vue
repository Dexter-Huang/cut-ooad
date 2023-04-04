<template>
<div>
	<el-form :model="form" ref="form" :rules="rules" label-width="auto">
		<el-row>
			<el-col :span="12">
				<el-form-item label="Name" prop="name">
					<el-input v-model="form.name" />
				</el-form-item>
			</el-col>
			<el-col :span="12">
				<el-form-item label="Age" prop="age">
					<el-input v-model="form.age" />
				</el-form-item>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="12">
				<el-form-item label="Password" prop="password">
					<el-input v-model="form.password" >
					</el-input>
				</el-form-item>
			</el-col>
			<el-col :span="12">
				<el-form-item label="Phone" prop="phone">
					<el-input v-model="form.phone">
					</el-input>
				</el-form-item>
			</el-col>
		</el-row>
		<el-form-item label="Sex" prop="sex">
			<el-radio-group v-model="form.sex" >
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
		<el-form-item label="Email" prop="email">
			<el-input v-model="form.email" />
		</el-form-item>

		<el-form-item label="Avatar" prop="avatar">
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
					Drop file here or <em>click to upload</em>
				</div>
			</el-upload>
		</el-form-item>

		<el-form-item label="Status" prop="status">
			<el-radio-group v-model="form.status" >
				<el-radio label="OK" value="OK">
					<el-tag type="success" disable-transitions>
						OK
					</el-tag>
				</el-radio>
				<el-radio label="LOCKED" value="LOCKED">
					<el-tag type="success" disable-transitions>
						LOCKED
					</el-tag>
				</el-radio>
			</el-radio-group>
		</el-form-item>
		<el-form-item label="userType" prop="userType">
			<el-radio-group v-model="form.userType" >
				<el-radio label="USER" value="USER">
					<el-tag type="success" disable-transitions>
						USER
					</el-tag>
				</el-radio>
				<el-radio label="VIPUSER" value="VIPUSER">
					<el-tag type="success" disable-transitions>
						VIPUSER
					</el-tag>
				</el-radio>
			</el-radio-group>
		</el-form-item>
		<el-form-item label="Credit" prop="credit">
			<el-input v-model="form.credit" />
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="onsubmit('form')">Submit</el-button>
			<el-button @click="close(form)">Cancel</el-button>
		</el-form-item>
	</el-form>
</div>

</template>

<script>
	import { ElMessageBox } from 'element-plus'
	import { ref} from 'vue'
	import axios from 'axios'
	export default {
		name: "add.vue",
		data(){
			return{
				form :{
					name:'',
					avatar:'',
					email:'',
					credit:'',
					password:'',
					phone:'',
					sex:'',
					status:'',
					userType:'',
					age:''
				},
				rules: {
					name:[{required:true,message:'用户名不能为空',trigger:'blur'},
								{min:3, max:20, message: '长度在 3 到 20 个字符',trigger: 'blur'},
								{validator:this.checkName,trigger: 'blur'}],
					email:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					credit:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					password:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					phone:[{required:true,message:'用户名不能为空',trigger:'blur'},
									{validator:this.checkPhone,trigger: 'blur'}],
					sex:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					status:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					userType:[{required:true,message:'用户名不能为空',trigger:'blur'}],
					age:[{required:true,message:'用户名不能为空',trigger:'blur'}],
				},
				defaultAvatar:'https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/user_avatar/default.jpg'
			}
		},
		methods:{
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
			onsubmit(form){
				this.$refs[form].validate((valid)=>{
					if(valid){
						console.log('submit-push');
						console.log(this.form);
						this.form.avatar=(this.form.avatar==='')?this.defaultAvatar:this.form.avatar;
						axios.post('/user/add',this.form).then(res=>{
							console.log("res")
							console.log(res)
							if(res.data.code===200){
								this.$message({
									type:'success',
									message:'添加成功'
								});
								this.$emit('updateTable',form)
							}else{
								this.$message({
									type:'warning',
									message:'添加失败'
								});
							}
						})
					}else {
						return false;
					}
				})

			},
			close(form){
				this.$emit('hideDialog',form);
			},
			//上传文件成功的操作
			handleAvatarSuccess(res,file){
				if(res.code==200){
					this.form.avatar=res.data
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
		},
	}
</script>

<style scoped>

</style>