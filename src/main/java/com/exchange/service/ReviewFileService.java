package com.exchange.service;

import com.exchange.entity.ReviewFile;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReviewFileService extends IService<ReviewFile> {
	public int addOne(ReviewFile reviewFile);

}
