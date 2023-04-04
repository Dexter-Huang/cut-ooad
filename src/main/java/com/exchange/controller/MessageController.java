package com.exchange.controller;


import com.exchange.vo.ApiResponse;
import com.exchange.entity.Message;
import com.exchange.service.MessageService;
import com.exchange.util.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@PostMapping("/alluser")
	public ApiResponse sendAllUser(@RequestBody Message message){
		System.out.println("---message");
		System.out.println(message);
		WebSocketServer.sendAllUser(message.getTheme(),message.getText());
		return ApiResponse.success("发送成功");
	}

	@GetMapping("/getmessage/{accountName}/{currentPage}")
	public ApiResponse listUserMessage(@PathVariable(value = "accountName")String accountName, @PathVariable("currentPage")Integer currentPage){
		return ApiResponse.success(messageService.listMessage(currentPage,accountName));
	}

}
