package com.exchange.dto;

import cn.hutool.core.map.MapUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
public class ReviewFileDto implements Serializable {
	private Integer errno;
	private Object data;
	public ReviewFileDto(Integer errno,Object data){
		this.errno=errno;
		this.data=data;
	}

	public static ReviewFileDto buildSuccess(String url,String name){
		return new ReviewFileDto(0, MapUtil.builder()
				.put("url",url )
				.put("alt",name )
				.put("href",url )
				.map());
	}

	public static Map fail(String message){
		return MapUtil.builder()
				.put("errno",1 )
				.put("message", message)
				.map();
	}

}
