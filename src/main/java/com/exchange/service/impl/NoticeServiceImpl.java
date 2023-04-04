package com.exchange.service.impl;

import com.exchange.entity.Notice;
import com.exchange.mapper.NoticeMapper;
import com.exchange.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
