<template>
	<div >
		<el-row>
			<el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="6">
				<el-card style=" height: 100%; color: #333">
					<div style="padding-bottom: 10px; border-bottom: 1px solid #ccc">在线客服<span style="font-size: 12px">（点击聊天气泡开始聊天）</span></div>
					<div style="padding: 0.5rem 0" v-for="user in users" :key="user.username">
						<el-avatar :src="user.avatar"></el-avatar>
						<el-button class="el-icon-chat-dot-round" style="margin-left: 10px; font-size: 16px; cursor: pointer"
							 @click="chooseChatter(user)">{{ user.username }}</el-button>
						<span style="font-size: 12px;color: limegreen; margin-left: 5px" v-if="user.username === chatUser.username">chatting...</span>
					</div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="18">
				<div v-if="chatUser.username==undefined">
					<div style="margin-left: 0.5rem;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc" >
						<div style="text-align: center; line-height: 50px;background-color: white;">
							Web聊天室（{{ chatUser.username }}）
						</div>
						<el-card style="height: 350px; overflow:auto; border-top: 1px solid #ccc" v-html="content[chatUser.username]"></el-card>
						<div style="height: 16rem;margin-top: 0.5rem;background-color: white;">
							<el-input  :rows="6" type="textarea" v-model="text" style="height: 80%;margin-right: 0.5rem"></el-input>
							<div style="text-align: right; padding-right: 10px">
								<el-button type="primary" size="mini" @click="send">发送</el-button>
							</div>
						</div>
					</div>
				</div>
				<div v-for="user in users">
					<div v-if="chatUser.username==user.username">
						<div style="margin-left: 0.5rem;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc" >
							<div style="text-align: center; line-height: 50px;background-color: white;">
								Web聊天室（{{ chatUser.username }}）
							</div>
							<el-card style="height: 350px; overflow:auto; border-top: 1px solid #ccc" v-html="content[chatUser.username]"></el-card>
							<div style="height: 16rem;margin-top: 0.5rem;background-color: white;">
            		<el-input  :rows="6" type="textarea" v-model="text" style="height: 80%;margin-right: 0.5rem"></el-input>
								<div style="text-align: right; padding-right: 10px">
									<el-button type="primary" size="mini" @click="send">发送</el-button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	// import request from "@/utils/request";
	import axios from 'axios'
	let socket;
	export default {
		name: "Chat",
		data() {
			return {
				circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
				user: {},
				isCollapse: false,
				users: [],
				chatUser: '',
				text: "",
				messages: [],
				content: {},
				chatAvatar:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
			}
		},
		created() {
			console.log(sessionStorage.getItem('userInfo'))
			this.user = sessionStorage.getItem('userInfo') ? JSON.parse(sessionStorage.getItem('userInfo')) : {}
			let username = this.user.name;
			let _this = this;
			if (typeof (WebSocket) == "undefined") {
				console.log("您的浏览器不支持WebSocket");
			} else {
				console.log("您的浏览器支持WebSocket");
				let socketUrl = "ws://localhost:8080/imserver/" + username;
				if (socket != null) {
					socket.close();
					socket = null;
				}
				// 开启一个websocket服务
				socket = new WebSocket(socketUrl);
				//打开事件
				socket.onopen = function () {
					console.log("websocket已打开");
				};
				//  浏览器端收消息，获得从服务端发送过来的文本消息
				socket.onmessage = function (msg) {
					console.log("收到数据====" + msg.data)
					let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
					console.log('data',data)
					if (data.users) {  // 获取在线人员信息
						_this.users = data.users.filter(user => user.username != username&&user.accountType!='USER')  // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
					} else {
						// 如果服务器端发送过来的json数据 不包含 users 这个key，那么发送过来的就是聊天文本json数据
						//  // {"from": "zhang", "text": "hello"}
						// if (data.from === _this.chatUser.username) {
							_this.messages.push(data)
							// 构建消息内容
							_this.createContent(data.from, null, data.text)
						console.log("this.content")
						console.log(_this.content)
						// }
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
			}
		},
		methods: {
			getlist(){
				let arr=[]

			},
			chooseChatter(user){
				// console.log("user")
				// console.log(user)
				this.chatUser = user
				console.log("this.content[this.chatUser.username]")
				console.log(this.content)
				console.log(this.content[this.chatUser.username])
				// let welcomeText='你好,'+this.chatUser.username
				// let message = {from: this.user.name, to: this.chatUser.username, text: welcomeText}
				// console.log("message",message)
				// socket.send(JSON.stringify(message));  // 将组装好的json发送给服务端，由服务端进行转发
				// this.messages.push({user: this.user.name, text: welcomeText})
				// 构建消息内容，本人消息

				if(this.content[this.chatUser.username]==undefined){
					console.log('kkk')
					this.content[this.chatUser.username]=''
					console.log(this.content[this.chatUser.username])
				}
				this.text = '';
			},
			send() {
				if (!this.chatUser.username) {
					this.$message({type: 'warning', message: "请选择聊天对象"})
					return;
				}
				if (!this.text) {
					this.$message({type: 'warning', message: "请输入内容"})
				} else {
					if (typeof (WebSocket) == "undefined") {
						console.log("您的浏览器不支持WebSocket");
					} else {
						console.log("您的浏览器支持WebSocket");
						// 组装待发送的消息 json
						// {"from": "zhang", "to": "admin", "text": "聊天文本"}
						let message = {from: this.user.name, to: this.chatUser.username, text: this.text}
						console.log("message",message)
						socket.send(JSON.stringify(message));  // 将组装好的json发送给服务端，由服务端进行转发
						this.messages.push({user: this.user.name, text: this.text})
						// 构建消息内容，本人消息
						this.createContent(null, this.user.name, this.text)
						this.text = '';
					}
				}
			},
			createContent(remoteUser, nowUser, text) {  // 这个方法是用来将 json的聊天消息数据转换成 html的。
				let html
				// 当前用户消息
				if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
					html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
						"  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
						"    <div class=\"tip left\">" + text + "</div>\n" +
						"  </div>\n" +
						"  <div class=\"el-col el-col-2\">\n" +
						"  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
						"    <img src=\""+this.$store.getters.getUser.avatar+"\" style=\"object-fit: cover;\">\n" +
						"  </span>\n" +
						"  </div>\n" +
						"</div>"
					// console.log(html)
					if(this.content[this.chatUser.username]==undefined){
						this.content[this.chatUser.username]=html
					}else{
						this.content[this.chatUser.username]+= html;
					}
				} else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
					console.log('pppppp')
					console.log(remoteUser)
					let avatar=''
					console.log(this.users)
					for(var i in this.users){
						console.log(this.users[i])
						if (this.users[i].username==remoteUser){
							avatar=this.users[i].avatar
						}
					}
					html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
						"  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
						"  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
						"    <img src=\""+avatar+"\" style=\"object-fit: cover;\">\n" +
						"  </span>\n" +
						"  </div>\n" +
						"  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
						"    <div class=\"tip right\">" + text + "</div>\n" +
						"  </div>\n" +
						"</div>";
					if(this.content[remoteUser]==undefined){
						this.content[remoteUser]=html
					}else{
						this.content[remoteUser]+= html;
					}
				}
			},
		}
	}
</script>
<style>
	.tip {
		color: white;
		text-align: center;
		border-radius: 10px;
		font-family: sans-serif;
		padding: 10px;
		width:auto;
		display:inline-block !important;
		display:inline;
	}
	.right {
		background-color: deepskyblue;
	}
	.left {
		background-color: forestgreen;
	}
</style>
