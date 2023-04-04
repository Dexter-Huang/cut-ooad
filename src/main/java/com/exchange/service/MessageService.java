package com.exchange.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exchange.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;



public interface MessageService extends IService<Message> {
	public IPage<Message> listMessage(Integer currentPage,String name);

	public int addOne (Message message);
	public int addList(List<String> userNames, String theme,String text);

	public int deleteOne(Message message);
	public int deleteList(List<Message> messageList);

	public int updateOne(Message message);
	public int updateList(Map<String,Object> data);

}
