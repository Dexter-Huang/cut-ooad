package com.exchange.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffQueryDto implements Serializable {
	@Builder.Default
	private String name="";
	@Builder.Default
	private String email="";
	@Builder.Default
	private Integer isOK=0;
	@Builder.Default
	private Integer isDeleted=0;
	@Builder.Default
	private Integer isLocked=0;
	@Builder.Default
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created=LocalDateTime.of(9999,12,30,23,59);
	@Builder.Default
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created2=LocalDateTime.of(9999,12,30,23,59);
	@Builder.Default
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLogin=LocalDateTime.of(9999,12,30,23,59);
	@Builder.Default
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLogin2=LocalDateTime.of(9999,12,30,23,59);
	@Builder.Default
	private String phone="";
	@Builder.Default
	private Integer isMan=0;
	@Builder.Default
	private Integer isWoman=0;
	@Builder.Default
	private Integer age=-1;
	@Builder.Default
	private Integer age2=-1;
	@Builder.Default
	private Integer hotelId=-1;
	@Builder.Default
	private Integer isCleaner=0;
	@Builder.Default
	private Integer isServer=0;
	@Builder.Default
	private Integer isManager=0;
	@Builder.Default
	private Integer isBoss=0;
	@Builder.Default
	private Integer isAdmin=0;
	@Builder.Default
	private Integer credit=0;
	@Builder.Default
	private Integer credit2=0;
}
