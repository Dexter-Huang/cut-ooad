package com.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.exchange.entity.constant.StaffType;
import lombok.*;
import lombok.experimental.Accessors;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 关联account的uuid
     */
    private String uuid;

    @TableField("hotelId")
    private Integer hotelId;

    @TableField("staffType")
    private StaffType staffType;

    @TableField("credit")
    private Integer credit;
}
