package com.exchange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.GiftCard;

import java.util.List;

public interface GiftCardService extends IService<GiftCard> {
	public List<GiftCard> getListByType(Integer type);

	public ApiResponse getCard(Integer giftId, Integer accountId);

	public List<GiftCard> getUserCard(Integer accountId,Integer type);
}
