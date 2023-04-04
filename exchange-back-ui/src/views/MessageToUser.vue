<template>
	<div>
		<h2>Royal酒店后台消息发送</h2>
		<el-row>
			<el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="20" >
				<el-card style="margin-right: 0.5rem;height: 100%">
					<el-row>
						<el-col :span="1"><el-avatar :size="30" :src="this.$store.getters.getUser.avatar"></el-avatar></el-col>
						<el-col :span="3">{{this.$store.getters.getUser.name}}</el-col>
					</el-row>
					<el-row style="margin-top: 0.5rem;">
						<el-input v-model="this.message.theme" placeholder="Input Message Theme"></el-input>
					</el-row>
					<Toolbar
									style="border-bottom: 1px solid #ccc"
									:editor="editorRef"
									:defaultConfig="toolbarConfig"
									:mode="mode"
									v-if="true"
					/>
					<Editor
									style=" overflow-y: hidden;"
									v-model="this.message.text"
									:defaultConfig="{placeholder: 'Type here...',readOnly : false}"
									:mode="mode"
									@onCreated="handleCreated"
									@onChange="handleChange"/>
					<el-row>
						<el-col :span="21"></el-col>
						<el-col :span="3" style="text-align: right;">
							<el-button type="primary" @click="handup"><el-icon><Position /></el-icon></el-button>
						</el-col>
					</el-row>
				</el-card>
			</el-col>
			<el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="4">
				<RightSide></RightSide>
			</el-col>
		</el-row>
	</div>

</template>

<script>
	import axios from 'axios'
	import '@wangeditor/editor/dist/css/style.css' // import css
	import { onBeforeUnmount, ref, shallowRef, onMounted,getCurrentInstance } from 'vue'
	import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
	import { DomEditor } from '@wangeditor/editor'
	import { useRoute,useRouter } from "vue-router";
	import RightSide from "@/views/RightSide";
	export default {
		name: "MessageToUser",
		components: { Editor, Toolbar,RightSide },
		setup() {
			let { proxy } = getCurrentInstance();
			//
			const route = useRoute();
			// editor instance, use `shallowRef`
			const editorRef = shallowRef()
			// content HTML
			const valueHtml = ref('<p>Write Your Review</p>')


			// Simulate ajax async set HTML
			onMounted(() => {
				setTimeout(() => {
					valueHtml.value = proxy.message.text
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
				// 'group-image',
				// 'group-video',
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
				proxy.message.text=editor.getHtml()
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
				message:{
					text:'',
					readStatus:0,
					accountName:'',
					createTime:'',
					theme:'',
				}
			}
		},
		methods:{
			handup() {
				console.log("this.message")
				console.log(this.message)
				axios.post('/message/alluser',this.message).then(res=>{
					console.log("message_res")
					console.log(res)
					if(res.data.code==200){
						this.$message.success(res.data.data.msg)
					}else{
						this.$message.warning(res.data.data.msg)
					}
				})
			},
		},
	}
</script>

<style scoped>

</style>
