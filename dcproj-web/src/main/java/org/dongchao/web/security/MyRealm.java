package org.dongchao.web.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.dongchao.core.service.UserService;
import org.dongchao.model.entity.User;
import org.dongchao.web.security.credentials.MyCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
            String salt = user.getSalt();
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        }
        return null;
    }

    /**
     * HashedCredentialsMatcher只用于密码验证，且可以提供自己的盐，而不是随机生成盐，
     * 且生成密码散列值的算法需要自己写，因为能提供自己的盐
     * 密码匹配详情查看@link HashedCredentialsMatcher.doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info);
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        MyCredentialsMatcher matcher = new MyCredentialsMatcher(UserService.HASH_ALGORITHM);
        matcher.setHashIterations(UserService.HASH_INTERATIONS);
        super.setCredentialsMatcher(matcher);
    }

    public static void main(String[] args) {
        //根据明文密码与盐值生成数据库存储的密码
        String password = "123" ;
        String salt = "hello" ;
        Hash hash=new SimpleHash(UserService.HASH_ALGORITHM, new SimpleByteSource(password),new SimpleByteSource(salt),UserService.HASH_INTERATIONS);
        System.out.println(hash.toHex());
    }
}
