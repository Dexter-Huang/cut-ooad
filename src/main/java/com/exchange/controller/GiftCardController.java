package com.exchange.controller;

import com.exchange.vo.ApiResponse;
import com.exchange.entity.GiftCard;
import com.exchange.service.GiftCardService;
import com.exchange.service.GiftCardUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftcard")
public class GiftCardController {
	@Autowired
	private GiftCardService giftCardService;

	@Autowired
	private GiftCardUserService giftCardUserService;

	@GetMapping("/list/{type}")
	public ApiResponse getListByType(@PathVariable("type")Integer type){
//		giftCardService.
		List<GiftCard> ans=giftCardService.getListByType(type);
		return ApiResponse.success(ans);
	}

	@GetMapping("/getcard/{giftId}/{accountId}")
	public ApiResponse getCard(@PathVariable("giftId")Integer giftId, @PathVariable("accountId")Integer accountId){
		System.out.println("====++");
		System.out.println(giftId);
		System.out.println(accountId);
		return giftCardService.getCard(giftId,accountId);
	}

	@GetMapping("/getusercard/{accountId}/{type}")
	public ApiResponse getUserCard(@PathVariable("accountId")Integer accountId, @PathVariable("type")Integer type){



		return ApiResponse.success(giftCardService.getUserCard(accountId,type));
	}
}
