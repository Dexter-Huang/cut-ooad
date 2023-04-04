<template>
	<div>
		<form onSubmit="return false;">
			<label>文本</label><input type="text" id="message" name="message" placeholder="这里输入消息" /> <br />
			<br /> <el-button @click="send">发送消息</el-button>
			<hr color="black" />
			<h3>服务端返回的应答消息</h3>
			<textarea id="responseText" style="width: 1024px;height: 300px;"></textarea>
		</form>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		name: "BackChat",
		created() {
			if(window.WebSocket){
				this.socket = new WebSocket("ws://localhost:8088/ws");
				// console.log('"ws://localhost:8088/ws"')
				// console.log(this.socket)
				this.socket.onmessage = function(event){
					let textarea = document.getElementById('responseText');
					textarea.value += event.data+"\r\n";
				};
				this.socket.onopen = function(event){
					let textarea = document.getElementById('responseText');
					textarea.value = "Netty-WebSocket服务器。。。。。。连接  \r\n";
				};
				this.socket.onclose = function(event){
					let textarea = document.getElementById('responseText');
					textarea.value = "Netty-WebSocket服务器。。。。。。关闭 \r\n";
				};
			} else {
				alert("您的浏览器不支持WebSocket协议！");
			}
		},
		data(){
			return{
				socket:'',
			}
		},
		methods:{
			send(){
				if(!window.WebSocket){
					console.log('HHHH')
					return;
				}
				if(this.socket.readyState === WebSocket.OPEN) {
					console.log("hmmm")
					let message = document.getElementById('message').value;
					this.socket.send(message);
				} else {
					alert("WebSocket 连接没有建立成功！");
				}
			}
		},
	}
</script>

<style scoped>

</style>
