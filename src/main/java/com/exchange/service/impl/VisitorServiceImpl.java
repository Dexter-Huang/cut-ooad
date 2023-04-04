package com.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exchange.entity.Visitor;
import com.exchange.mapper.VisitorMapper;
import com.exchange.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {
	@Autowired
	private VisitorMapper visitorMapper;


	@Override
	public void addOne(Visitor visitor) {
		visitorMapper.insert(visitor);
	}
}
