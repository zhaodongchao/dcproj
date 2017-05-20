package org.dongchao.web.security.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;


/**
 *  自定义密码匹配器
 * Created by zhaodongchao on 2017/5/20.
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {
    public MyCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    /**
     * 重写密码匹配规则，实现自己的登录控制逻辑
     * @param token 页面输入的登录认证信息
     * @param info  数据库中存储的对应的用户信息
     * @return true 密码输入正确；false 密码匹配错误
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //TODO
        return super.doCredentialsMatch(token, info);
    }
}
