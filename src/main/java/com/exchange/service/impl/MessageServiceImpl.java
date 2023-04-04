package com.exchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exchange.entity.Message;
import com.exchange.mapper.MessageMapper;
import com.exchange.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	public static Integer PAGE_SIZE=6;

	@Override
	public IPage<Message> listMessage(Integer currentPage, String name) {
		Page page=new Page(currentPage,PAGE_SIZE);
		IPage<Message> list=messageMapper.selectPage(page,new QueryWrapper<Message>().eq("accountName",name).orderByDesc("createTime"));
		return list;
	}

	@Override
	public int addOne(Message message) {
		int ans=messageMapper.insert(message);
		return ans;
	}

	@Override
	public int addList(List<String> userNames, String theme,String text) {
		for(String str:userNames){
			Message message=new Message();
			message.setAccountName(str);
			message.setText(text);
			message.setReadStatus(0);
//			message.setCreateTime()
			messageMapper.insert(message);
		}
		return 0;
	}


	@Override
	public int deleteOne(Message message) {
		return messageMapper.delete(new QueryWrapper<Message>().eq("accountName",message.getAccountName()).eq("createTime",message.getCreateTime()));
	}

	@Override
	public int deleteList(List<Message> messageList) {
		int ans=1;
		for(Message message:messageList){
			ans=ans&messageMapper.delete(new QueryWrapper<Message>().eq("accountName",message.getAccountName()).eq("createTime",message.getCreateTime()));
		}
		return ans;
	}

	@Override
	public int updateOne(Message message) {
		return messageMapper.updateById(message);
	}

	@Override
	public int updateList(Map<String, Object> data) {
		return 0;
	}
}
