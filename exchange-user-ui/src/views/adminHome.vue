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
						<span>Royal 酒店</span>
					</div>
				</el-menu-item>

				<el-sub-menu index="/admin/hotel" >
					<template #title>
						<el-icon><OfficeBuilding /></el-icon>
						<span>Exchange</span>
					</template>
					<el-menu-item index='/hotelInfo'>主题文化</el-menu-item>
					<el-menu-item index='/giftCards'>积分兑换</el-menu-item>
				</el-sub-menu>


				<el-sub-menu index="/admin/notices">
					<template #title>
						<el-icon><ChatDotRound /></el-icon>
						<span>SUSTech社区</span>
					</template>
					<el-menu-item index='/notice/add'>分享博客</el-menu-item>
					<el-menu-item index='/notices'>浏览博客</el-menu-item>
					<el-menu-item index='/imserver'>SUSTech-Chat</el-menu-item>
				</el-sub-menu>

				<el-sub-menu index="12">
					<template #title>
						<el-icon><User /></el-icon>
						<span>用户中心</span>
					</template>
					<el-menu-item index='/userInfo'>我的首页</el-menu-item>
					<el-menu-item index='/userBill'>订单</el-menu-item>
					<el-menu-item index='/userGiftCard'>礼品卡</el-menu-item>
					<el-menu-item index='/userCredit'>积分</el-menu-item>
					<el-menu-item index='/userMessage'>我的消息</el-menu-item>
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
					<el-popover
									placement="bottom"
									:width="50"
									trigger="hover"
									content="联系客服">
						<template #reference>
							<div @click="chat" style="margin-right: 0.5rem">
								<el-icon ><Service /></el-icon>
							</div>
						</template>
					</el-popover>

					<el-popover
									placement="bottom"
									:width="50"
									trigger="hover"
									content="查看消息">
						<template #reference>
							<div @click="message" style="margin-right: 0.5rem">
								<el-badge is-dot >
									<el-icon><Message /></el-icon>
								</el-badge>
							</div>
						</template>
					</el-popover>


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
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>
<script>
	import axios from "axios";
	import { h } from 'vue'
	import { ElNotification } from 'element-plus'
	let socket;
	export default {
		data () {
			return {
				// 是否折叠
				isCollapse: false,
				str:'/imserver/'
			}
		},
		created () {
			let _this = this;
			if(window.WebSocket){
				if (socket != null) {
					socket.close();
					socket = null;
				}
				socket = new WebSocket("ws://localhost:8080/imserver/"+this.$store.getters.getUser.name);
				console.log('"ws://localhost:8088/ws"')
				console.log(socket)
				// socket.onmessage = function(event){
				// 	// alert(event.data)
				// 	console.log("event.data")
				// 	console.log(event.data)
				// 	ElNotification({
				// 		title: '您收到一条新消息',
				// 		message: h('i', { style: 'color: teal' }, event.data),
				// 	})
				//
				// };
				//  浏览器端收消息，获得从服务端发送过来的文本消息
				socket.onmessage = function (msg) {
					console.log("收到数据====" + msg.data)
					let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
					console.log('data',data)
					if(data.from=='RoyalHotel'){
							ElNotification({
								title: '您收到一条新消息',
								message: h('i', { style: 'color: teal' }, data.text),
							})
					}
				};
				//关闭事件
				socket.onclose = function () {
					console.log("websocket已关闭");
				};
				//发生了错误事件
				socket.onerror = function () {
					console.log("websocket发生了错误");
				}
			} else {
				alert("您的浏览器不支持WebSocket协议！");
			}
		},
		methods: {
			test(){
				console.log('oooo')
			},
			message(){
				this.$router.push('/userMessage')
			},
			chat(){
				this.$router.push('/serveChat')
			},
			coupon(){
			},
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
			turn_to_userInfo(){
				this.$router.push('/userInfo');
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
