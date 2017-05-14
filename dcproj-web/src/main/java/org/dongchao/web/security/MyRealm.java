package org.dongchao.web.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.dongchao.core.service.UserService;
import org.dongchao.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhaodongchao on 2017/5/13.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        User shiroUser = (User) principals.getPrimaryPrincipal();
        User user = userService.findUserByName(shiroUser.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(user.getRoles());
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    /**
     * 认证回调函数,登录时调用.
     *  在ModularRealmAuthenticator中调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String loginName = usernamePasswordToken.getUsername();
        User user = userService.findUserByName(loginName);
        if (null != user) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;
    }
}
