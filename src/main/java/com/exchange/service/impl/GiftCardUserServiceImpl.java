package com.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exchange.entity.GiftCardUser;
import com.exchange.mapper.GiftCardUserMapper;
import com.exchange.service.GiftCardUserService;
import org.springframework.stereotype.Service;

@Service
public class GiftCardUserServiceImpl extends ServiceImpl<GiftCardUserMapper, GiftCardUser> implements GiftCardUserService {
}
