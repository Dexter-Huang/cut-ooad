package com.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AliPay {
	public static int ALIPAY=0;
	public static int WX=1;

	private String subject;
	private String traceNo;
	private String totalAmount;
}
