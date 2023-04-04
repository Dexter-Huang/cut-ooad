package com.exchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.exchange.entity.GiftCard;
import com.exchange.entity.GiftCardUser;
import com.exchange.mapper.AccountMapper;
import com.exchange.mapper.GiftCardMapper;
import com.exchange.mapper.GiftCardUserMapper;
import com.exchange.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiftCardServiceImpl extends ServiceImpl<GiftCardMapper, GiftCard> implements GiftCardService {
	@Autowired
	private GiftCardMapper giftCardMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private GiftCardUserMapper giftCardUserMapper;


	@Override
	public List<GiftCard> getListByType(Integer type) {
		List<GiftCard> ans=giftCardMapper.selectList(new QueryWrapper<GiftCard>()
				.eq("type",type)
				.le("createTime", LocalDateTime.now())
				.ge("endTime",LocalDateTime.now()));
		return ans;
	}

	@Override
	public ApiResponse getCard(Integer giftId, Integer accountId) {
		GiftCard giftCard=giftCardMapper.selectById(giftId);
		Accounts accounts=accountMapper.selectById(accountId);

		Assert.notNull(accounts,"账户不存在");
		Assert.notNull(giftCard,"礼品卡不存在");

		if(giftCard.getCredit()>accounts.getAccountCredit()){
			return ApiResponse.fail("账户积分不足");
		}
		else if(giftCard.getStock()<=0){
			return ApiResponse.fail("礼品卡库存不足");
		}
		else{
			accounts.setAccountCredit(accounts.getAccountCredit()-giftCard.getCredit());
			accountMapper.updateById(accounts);

			giftCard.setStock(giftCard.getStock()-1);
			giftCardMapper.updateById(giftCard);
			GiftCardUser giftCardUser=new GiftCardUser();
			giftCardUser.setAccountId(accountId);
			giftCardUser.setGiftCardId(giftId);
			giftCardUser.setStatus(0);
			giftCardUserMapper.insert(giftCardUser);

			return ApiResponse.success("礼品卡兑换成功",1);

		}


	}

	@Override
	public List<GiftCard> getUserCard(Integer accountId,Integer type) {
		if(type==0)//全部
			return giftCardUserMapper.getUserAllCards(accountId);
		else if(type==1)//未过期
			return giftCardUserMapper.getUnLateCards(accountId);
		else if(type==2)//已过期
			return giftCardUserMapper.getLateCards(accountId);
		else if(type==3)//已使用
			return giftCardUserMapper.getUsedCards(accountId);
		else
			return null;
	}
}
