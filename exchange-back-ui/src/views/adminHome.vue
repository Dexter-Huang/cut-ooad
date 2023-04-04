<template>
	<el-container class="home_container">
		<!-- 侧边栏 -->
		<el-aside :width="isCollapse ? '0px':'160px'">
			<!-- 侧边栏菜单区域 -->
			<el-menu :router='true'
							 active-text-color="#409Eff"
							 background-color="#545c64"
							 text-color="#fff" unique-opened :collapse="isCollapse" :collapse-transition="false">
				<el-menu-item>
					<div>
						<span>SUSTech Exchange后台管理系统</span>
					</div>
				</el-menu-item>
				<el-sub-menu index="/admin/users" >
					<template #title>
						<el-icon><User /></el-icon>
						<span>用户</span>
					</template>
					<el-menu-item index='/userInfo'>用户信息</el-menu-item>
				</el-sub-menu>


				<el-sub-menu index="137">
					<template #title>
						<el-icon><Tickets /></el-icon>
						<span>账单</span>
					</template>
					<el-menu-item index='/billInfo'>账单信息</el-menu-item>
				</el-sub-menu>

				<el-sub-menu index="/admin/notices">
					<template #title>
						<el-icon><Notification /></el-icon>
						<span>通知</span>
					</template>
					<el-menu-item index='/notice/add'>添加通知</el-menu-item>
					<el-menu-item index='/notices'>所有通知</el-menu-item>
					<el-menu-item index='/messageToUser'>发送消息</el-menu-item>
				</el-sub-menu>

			</el-menu>
		</el-aside>
		<!-- 页面主体区域 -->
		<el-container>
			<!-- 头部区域 -->
			<el-header>
				<div class="toggle-button" @click="toggleCollapse" v-if="isCollapse">
					<el-icon style="font-size: 25px;"><Expand /></el-icon>
				</div>
				<div class="toggle-button" @click="toggleCollapse" v-if="!isCollapse">
					<el-icon style="font-size: 25px;"><Fold /></el-icon>
				</div>

				<div style="text-align: right">
					<el-dropdown style="margin-right: 0.5rem">
						<el-avatar :src="this.$store.getters.getUser.avatar" ></el-avatar>
						<template #dropdown>
							<el-dropdown-menu>
								<el-dropdown-item  @click="logout"><el-icon><Expand /></el-icon>退出账号</el-dropdown-item>
								<el-dropdown-item @click="turn_to_userInfo"><el-icon><User /></el-icon>账号管理</el-dropdown-item>
								<el-dropdown-item ><el-icon><Setting /></el-icon>系统设置</el-dropdown-item>
							</el-dropdown-menu>
						</template>
					</el-dropdown>

					<p>{{this.$store.getters.getUser.name}}</p>
				</div>
			</el-header>

			<!-- 右侧内容主体区域 -->
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	import axios from "axios";

	export default {
		data () {
			return {
				// 是否折叠
				isCollapse: false
			}
		},
		created () {
		},
		methods: {
			logout() {
				axios.get("/account/logout", {
					headers: {
						"Authorization": localStorage.getItem("token")
					}
				}).then(res => {
					this.$store.commit("REMOVE_INFO")
					this.$router.push("/account/login")

				})
			},
			// 点击按钮，切换菜单的折叠与展开
			toggleCollapse () {
				this.isCollapse = !this.isCollapse
			}
		}
	}
</script>
<style lang="less" scoped>
	.home_container {
		height: 100%;
	}
	.el-header {
		background-color: #363d40;
		// 给头部设置一下弹性布局
		display: flex;
		// 让它贴标左右对齐
		justify-content: space-between;
		// 清空图片左侧padding
		padding-left: 0;
		// 按钮居中
		align-items: center;
		// 文本颜色
		color: #fff;
		// 设置文本字体大小
		font-size: 20px;
		// 嵌套
		> div {
			// 弹性布局
			display: flex;
			// 纵向上居中对齐
			align-items: center;
			// 给文本和图片添加间距，使用类选择器
			span {
				margin-left: 15px;
			}
		}
	}
	.el-aside {
		background-color: #313743;
		transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-moz-transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-o-transition: width 0.15s;
		.el-menu {
			border-right: none;
			transition: all 10ms;
		}
	}
	.el-main {
		background-color: #e9edf1;
	}
	.iconfont{
		margin-right: 10px;
	}
	.toggle-button{
		// 添加背景颜色
		background-color: #4A5064;
		// 设置文本大小
		font-size:10px;
		// 设置文本行高
		line-height:24px;
		// 设置文本颜色
		color:#fff;
		// 设置文本居中
		text-align: center;
		// 设置文本间距
		letter-spacing: 0.2em;
		// 设置鼠标悬浮变小手效果
		cursor:pointer;
	}
</style>
