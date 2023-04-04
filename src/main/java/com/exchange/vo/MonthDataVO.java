package com.exchange.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthDataVO implements Serializable {
	private Integer m7;
	private Integer m1;
	private Integer m2;
	private Integer m3;
	private Integer m4;
	private Integer m5;
	private Integer m6;
}
