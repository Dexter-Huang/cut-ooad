<template>
	<div>
		<div class="mNotice">
			<h2> {{ notice.title }}</h2>
			<el-link class="el-icon--check" v-if="ownNotice">
				<router-link :to="{name: 'NoticeEdit', params: {noticeId: notice.id}}" >
					编辑
				</router-link>
			</el-link>
			<el-divider></el-divider>
			<div class="markdown-body" v-html="notice.content"></div>

		</div>
	</div>
</template>

<script>
	import 'github-markdown-css/github-markdown-light.css'
	import Header from '../../components/header'
	import axios from 'axios'
	export default {
		name: "NoticeDetail",
		components: {Header},
		data() {
			return {
				notice: {
					id: "",
					title: "",
					content: ""
				},
				ownNotice: false
			}
		},
		created() {
			const noticeId = this.$route.params.noticeId
			// console.log(blogId)
			axios.get('/notice/' + noticeId).then(res => {
				const notice = res.data.data
				this.notice.id = notice.id
				this.notice.title = notice.title

				var MardownIt = require("markdown-it")
				var md = new MardownIt()

				var result = md.render(notice.content)
				this.notice.content = result
				this.ownNotice = (notice.userId === this.$store.getters.getUser.id)

			})
		}
	}
</script>

<style scoped>
	.mNotice {
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
		width: 100%;
		min-height: 700px;
		padding: 20px 15px;
	}
</style>
