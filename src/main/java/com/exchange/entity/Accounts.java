package com.exchange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.exchange.entity.constant.AccountStatus;
import com.exchange.entity.constant.AccountType;
import com.exchange.entity.constant.Sex;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid;

    @Length(min = 3, max = 20, message = "用户名长度范围为2~20个字符")
    @NotBlank(message = "用户名不能为空")
    private String name="";

    private String avatar="https://hotel-1313882515.cos.ap-guangzhou.myqcloud.com/user_avatar/default.jpg";

    @Email(message = "邮箱格式不符")
    private String email="";

//    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码长度应在6-12个字符之间")
    private String password="";

    private AccountStatus status=AccountStatus.OK;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created=LocalDateTime.now();

    @TableField("lastLogin")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin=LocalDateTime.now();

    @TableField("IdNum")
    private Long IdNum=0L;

    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "手机格式不正确")
    @NotBlank(message = "手机号不能为空")
    private String phone="";

    private Sex sex;

    private Integer age=0;

    @TableField("accountType")
    private AccountType accountType=AccountType.USER;

    @TableField("accountCredit")
    private Integer accountCredit;


}
