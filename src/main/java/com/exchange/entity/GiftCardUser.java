package com.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gift_card_user")
public class GiftCardUser {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField("accountId")
	private Integer accountId;

	@TableField("giftCardId")
	private Integer giftCardId;

	@TableField("status")
	private Integer status;

	@TableField("usedTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime usedTime;

	@TableField("createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

}
