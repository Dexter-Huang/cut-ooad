package com.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDto implements Serializable {
	@Builder.Default
	private String name="";
	@Builder.Default
	private String email="";
	@Builder.Default
	private Boolean isOK=false;
	@Builder.Default
	private Boolean isDeleted=false;
	@Builder.Default
	private Boolean isLocked=false;
	@Builder.Default
	private String created="";
	@Builder.Default
	private String created2="";
	@Builder.Default
	private String phone="";
	@Builder.Default
	private Boolean isMan=false;
	@Builder.Default
	private Boolean isWoman=false;
	@Builder.Default
	private Integer age=-1;
	@Builder.Default
	private Integer age2=-1;

	@Builder.Default
	private Integer credit=-1;
	@Builder.Default
	private Integer credit2=-1;
	@Builder.Default
	private Boolean isNotVIP=false;
	@Builder.Default
	private Boolean isVIP=false;
}
