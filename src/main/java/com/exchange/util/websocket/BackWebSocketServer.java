package com.exchange.util.websocket;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exchange.entity.Accounts;
import com.exchange.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BackWebSocketServer implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("BackWebSocketServer 开始啦啦了来咯？");
		// 定义一对线程组  主线程组  用于接收客户端的连接请求，不做任何处理
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 定义一对线程组  从线程组  主线程组会把任务丢给从线程组，让从线程组去处理
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// netty服务器的创建，ServerBootstrap是一个启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup) // 设置主从线程组
					.channel(NioServerSocketChannel.class) // 设置NIO双向通道类型
					.childHandler(new WebSocketServerInitializer()); // 子处理器，用于处理workerGroup

			// 启动server，绑定8088端口启动，并且同步等待方式启动
			ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

			// 监听关闭的channel， 设置为同步的方式
			channelFuture.channel().closeFuture().sync();
		} finally {
			// 关闭我们的主线程组和从线程组
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private static AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService){
		BackWebSocketServer.accountService=accountService;
	}


	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	/**
	 * 记录当前在线连接数
	 */
	public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
		sessionMap.put(username, session);
		log.info("有新用户加入，username={}, 22当前在线人数为：{}", username, sessionMap.size());
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		result.set("users", array);
		for (Object key : sessionMap.keySet()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.set("username", key);
			System.out.println("key");
			System.out.println(key);
			String avatar=accountService.getOne(new QueryWrapper<Accounts>().eq("name",key)).getAvatar();
//			System.out.println("avatar");
//			System.out.println(avatar);
			jsonObject.set("avatar",avatar);
			// {"username", "zhang", "username": "admin"}
			array.add(jsonObject);
		}
//        {"users": [{"username": "zhang"},{ "username": "admin"}]}
//		sendAllMessage(JSONUtil.toJsonStr(result));  // 后台发送消息给所有的客户端
	}
}
