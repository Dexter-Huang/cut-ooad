package com.exchange.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.exchange.entity.GiftCard;
import com.exchange.entity.GiftCardUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GiftCardUserMapper extends MPJBaseMapper<GiftCardUser> {
	@Select("select * from gift_card where id in(select giftCardId from gift_card_user where accountId=#{accountId});")
	public List<GiftCard> getUserAllCards(Integer accountId);

	@Select("select * from gift_card where id in(select giftCardId from gift_card_user where accountId=1 and createTime<now() and endTime >now());")
	public List<GiftCard> getUnLateCards(Integer accountId);

	@Select("select * from gift_card where id in(select giftCardId from gift_card_user where accountId=1 and endTime <now());")
	public List<GiftCard> getLateCards(Integer accountId);

	@Select("select * from gift_card where id in(select giftCardId from gift_card_user where accountId=1 and status=1);")
	public List<GiftCard> getUsedCards(Integer accountId);



}
