package com.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("visitor")
public class Visitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField("name")
	private String name;

	@TableField("visitTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime visitTime;

	@TableField("totalNum")
	private Integer totalNum;

	@TableField("visitType")
	private Integer visitType;
}
