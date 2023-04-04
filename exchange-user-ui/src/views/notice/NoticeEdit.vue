<template>
	<div>
		<h1>添加通知</h1>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
			<el-form-item label="标题" prop="title">
				<el-input v-model="ruleForm.title"></el-input>
			</el-form-item>
			<el-form-item label="摘要" prop="description">
				<el-input type="textarea" v-model="ruleForm.description"></el-input>
			</el-form-item>
			<el-form-item label="内容" prop="content">
				<v-md-editor v-model="ruleForm.content" ></v-md-editor>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
				<el-button @click="resetForm('ruleForm')">重置</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	import Header from '../../components/header'
	import axios from 'axios'
	export default {
		name: "NoticeEdit",
		components: {Header},
		data() {
			return {
				ruleForm: {
					id: '',
					title: '',
					description: '',
					content: ''
				},
				rules: {
					title: [
						{ required: true, message: '请输入标题', trigger: 'blur' },
						{ min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
					],
					description: [
						{ required: true, message: '请输入摘要', trigger: 'blur' }
					],
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					// console.log(formName)
					if (valid) {
						axios.post('/notice/edit', this.ruleForm, {
							headers: {
								"Authorization": localStorage.getItem("token")
							}
						}).then(res => {
							// console.log(res)
							this.$alert('操作成功', '提示', {
								confirmButtonText: '确定',
								callback: action => {
									this.$router.push("/notices")
								}
							});

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
		},
		created() {
			const noticeId = this.$route.params.noticeId
			// console.log(blogId)
			if(noticeId) {
				axios.get('/notice/' + noticeId).then(res => {
					const notice = res.data.data
					this.ruleForm.id = notice.id
					this.ruleForm.title = notice.title
					this.ruleForm.description = notice.description
					this.ruleForm.content = notice.content
				})
			}

		}
	}
</script>

<style scoped>
	.m-content {
		text-align: center;
	}
</style>
