package com.exchange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exchange.entity.Visitor;

public interface VisitorService extends IService<Visitor> {
	public void addOne(Visitor visitor);
}
