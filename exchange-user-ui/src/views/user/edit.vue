<template>
	<div>
		<el-form :model="form" ref="form" :rules="rules" label-width="120px">
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
			<el-form-item label="Avatar" prop="avatar" v-if="!form.avatar==''">
				<el-upload
								class="upload-demo" action="http://localhost:8080/upload/image1"
								accept="image/jpeg,image/gif,image/png"
								style="width: 100%"
								:limit="1"
								:before-upload="beforeUpload"
								:on-success="handleAvatarSuccess"
								:on-exceed="handleExceed">
					<el-avatar :src="form.avatar"></el-avatar>
				</el-upload>
			</el-form-item>

			<el-form-item label="Avatar" prop="avatar" v-if="form.avatar==''">
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
		name: "edit.vue",
		props:["rowData","rowIndex"],
		created() {
			console.log("rowData")
			console.log(this.rowData)
			this.form.id=this.rowData.id
			this.form.uuid=this.rowData.uuid
			this.form.name=this.rowData.name
			this.form.avatar=this.rowData.avatar
			this.form.email=this.rowData.email
			this.form.credit=this.rowData.credit
			this.form.password=this.rowData.password
			this.form.phone=this.rowData.phone
			this.form.sex=this.rowData.sex
			this.form.status=this.rowData.status
			this.form.userType=this.rowData.userType
			this.form.age=this.rowData.age
		},
		data(){
			return{
				form :{
					id:'',
					uuid:'',
					name:'',
					avatar:'',
					email:'',
					credit:'',
					password:'',
					phone:'',
					sex:'',
					status:'',
					userType:'',
					age:'',
				},
				rules: {
					name:[{required:true,message:'用户名不能为空',trigger:'blur'},
								{min: 3,max: 20, message: '长度在 3 到 20 个字符',trigger: 'blur'}],
					email:[{required:true,message:'邮箱不能为空',trigger:'blur'}],
					credit:[{required:true,message:'积分不能为空',trigger:'blur'}],
					password:[{required:true,message:'密码不能为空',trigger:'blur'}],
					phone:[{required:true,message:'电话不能为空',trigger:'blur'}],
					sex:[{required:true,message:'性别不能为空',trigger:'blur'}],
					status:[{required:true,message:'账户状态不能为空',trigger:'blur'}],
					userType:[{required:true,message:'用户类型不能为空',trigger:'blur'}],
					age:[{required:true,message:'年龄不能为空',trigger:'blur'}],
				},
			}
		},
		methods:{
			onsubmit(form){
				this.$refs[form].validate((valid)=>{
					if(valid){
						console.log("form")
						console.log(this.form)
						axios.post('/user/update',this.form).then(res=>{
							console.log(res)
							if(res.data.code===200){
								this.$message({
									type:'success',
									message:'添加成功'
								});
								this.$emit('updateTable')
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
			close(){
				this.$emit('hideDialog');
			},
			//上传文件成功的操作
			handleAvatarSuccess(res,file){
				this.form.avatar=res.data
				this.$message({
					type:'success',
					message:'上传成功'
				});
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