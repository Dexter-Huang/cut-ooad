package com.exchange.util.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.exchange.entity.Accounts;
import com.exchange.entity.constant.AccountStatus;
import com.exchange.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

	@Autowired
	private AccountService accountService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken) token;

		String accountId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

		Accounts accounts = accountService.getById(Long.valueOf(accountId));
		if (accounts == null) {
			throw new UnknownAccountException("账户不存在666");
		}

		if (accounts.getStatus().equals( AccountStatus.LOCKED)) {
			throw new LockedAccountException("账户已被锁定");
		}

		AccountProfile profile = new AccountProfile();
		BeanUtil.copyProperties(accounts, profile);
		//返回一些非私密可公开的信息
		return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
