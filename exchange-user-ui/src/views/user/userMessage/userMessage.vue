<template>
	<div>
		<el-row>
			<el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="20" >
				<el-row style="margin-right: 0.5rem">
					<UserHeaderPage style="width: 100%;"></UserHeaderPage>
				</el-row>
				<el-row  style="margin-top: 0.5rem;margin-right: 0.5rem">
					<h3>我的消息</h3>
					<el-timeline >
						<el-timeline-item color="#0bbd87" center :hollow=true :timestamp="item.createTime" placement="top" v-for="item in messages">
							<el-card style="margin-bottom: 0.5rem;width:60rem">
								<Editor style="overflow-y: hidden;"
												v-model="item.text"
												:defaultConfig="{placeholder: 'Type here...',readOnly : true,}"
												:mode="mode"
												@onCreated="handleCreated"
												@onChange="handleChange"/>
							</el-card>
						</el-timeline-item>
					</el-timeline>
				</el-row>
				<div style="text-align: center;">
					<el-pagination class="mpage" layout="prev, pager, next"
												 :current-page="currentPage"
												 :page-size="pageSize"
												 :total="total"
												 @current-change=page />
				</div>
			</el-col>
			<el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="4">
				<RightSide></RightSide>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import RightSide from "@/views/RightSide";
	import UserHeaderPage from '@/views/user/userHeaderPage'
	import axios from 'axios'
	import '@wangeditor/editor/dist/css/style.css' // import css
	import { onBeforeUnmount, ref, shallowRef, onMounted,getCurrentInstance } from 'vue'
	import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
	import { useRoute,useRouter } from "vue-router";
	export default {
		name: "userNews",
		components:{
			Editor, Toolbar,
			RightSide,
			UserHeaderPage
		},

		setup() {
			let { proxy } = getCurrentInstance();
			//
			const route = useRoute();
			// editor instance, use `shallowRef`
			const editorRef = shallowRef()
			// content HTML
			const valueHtml = ref('<p>Write Your Review</p>')

			// const reviewUUId=route.params.reviewUUId
			// proxy.reviewUUID=route.params.reviewUUID

			// Simulate ajax async set HTML
			onMounted(() => {
				setTimeout(() => {
					valueHtml.value = proxy.messages.text
				}, 1500)

			})

			const toolbarConfig = {}
			toolbarConfig.excludeKeys = [
				'headerSelect',
				'group-more-style', // 排除菜单组，写菜单组 key 的值即可
				'fontSize',
				'fontFamily',
				'lineHeight',
				'bulletedList',
				'numberedList',
				'todo',
				'group-justify',
				'group-indent',
				'codeBlock',
				'divider',
				'group-image',
				'group-video',
				'insertTable',
				'color',
				'bgColor',
				'blockquote'
			]
			const editorConfig = {
				placeholder: 'Type here...',
				readOnly : true,
			}


			// Timely destroy `editor` before vue component destroy.
			onBeforeUnmount(() => {
				const editor = editorRef.value
				if (editor == null) return
				editor.destroy()
			})

			const handleCreated = (editor) => {
				editorRef.value = editor // record editor instance
			}

			const handleChange=(editor)=>{
				proxy.messages.text=editor.getHtml()
				// console.log("proxy.review")
				// console.log(proxy.review)
				// const toolbar = DomEditor.getToolbar(editor)
				//
				// const curToolbarConfig = toolbar.getConfig()
				// console.log( curToolbarConfig.toolbarKeys ) // 当前菜单排序和分组
			}

			return {
				editorRef,
				mode: 'default', // or 'simple'
				valueHtml,
				toolbarConfig,
				editorConfig,
				handleCreated,
				handleChange
			};
		},
		data(){
			return{
				currentPage:'',
				total:'',
				pageSize:'',
				messages:'',
			}
		},
		created() {
			this.page(1);
		},
		methods:{
			page(currentPage) {
				axios.get("/message/getmessage/"+this.$store.getters.getUser.name+"/" + currentPage).then(res => {
					// console.log(res)
					this.messages = res.data.data.records
					this.currentPage = res.data.data.current
					this.total = res.data.data.total
					this.pageSize = res.data.data.size
				})
			},
			hadread(){

			},
		}
	}
</script>

<style scoped>

</style>
