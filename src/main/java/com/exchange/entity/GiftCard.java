package com.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.exchange.entity.constant.GiftCardType;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gift_card")
public class GiftCard {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField("credit")
	private Integer credit;

	@TableField("name")
	private String name;

	@TableField("createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	@TableField("endTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	@TableField("stock")
	private Integer stock;

	@TableField("img")
	private String img;

	@TableField("description")
	private String description;

	@TableField("type")
	private GiftCardType type;


}
