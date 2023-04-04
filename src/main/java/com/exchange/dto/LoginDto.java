package com.exchange.dto;

import com.exchange.entity.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String name;

    private Long phone;

    private String email;

    private AccountType accountType;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String captchaCode;
    /**
     * Name 用户名登录
     * phone 手机号登陆
     * email 邮箱登录
     */
    private String loginType;
}
