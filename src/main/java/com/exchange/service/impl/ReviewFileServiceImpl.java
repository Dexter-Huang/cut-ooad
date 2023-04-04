package com.exchange.service.impl;

import com.exchange.entity.ReviewFile;
import com.exchange.mapper.ReviewFileMapper;
import com.exchange.service.ReviewFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewFileServiceImpl extends ServiceImpl<ReviewFileMapper, ReviewFile> implements ReviewFileService {
	@Autowired
	private ReviewFileMapper reviewFileMapper;

	@Override
	public int addOne(ReviewFile reviewFile) {
		return reviewFileMapper.insert(reviewFile);
	}
}
