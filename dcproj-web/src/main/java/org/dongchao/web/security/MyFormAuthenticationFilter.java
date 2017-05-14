package org.dongchao.web.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaodongchao on 2017/5/14.
 */
public class MyFormAuthenticationFilter extends AuthenticatingFilter {
    private static final Logger logger = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    public static final String DEFAULT_USERNAME_PARAM = "username";
    public static final String DEFAULT_PASSWORD_PARAM = "password";

    private String usernameParam = DEFAULT_USERNAME_PARAM;
    private String passwordParam = DEFAULT_PASSWORD_PARAM;

    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String username = getUsername(request);
        String password = getPassword(request);
        return createToken(username, password, request, response);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //检测请求是否为登录请求 特点 url = loginUrl and method = post
        if (isLoginRequest(request, response)) {//判断loginUrl
            logger.info("正在访问url:" + getPathWithinApplication(request) + "  method:" + WebUtils.toHttp(request).getMethod());
            if (isLoginSubmission(request)) {//判断是否为post请求
                logger.info("检测到登录请求...................");
                return executeLogin(request, response); //执行登录操作
            } else {
                //allow them to see the login page ;
                return true;
            }
        } else {
            logger.info("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + getLoginUrl() + "]");
            //将这个请求保存下来，并且去请求授权，当授权成功时，继续访问年当前请求
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        logger.info("---------------->登录成功");
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        logger.info("------------>登录失败 " + e.getMessage());
        setFailureAttribute(request, e);//将登录异常信息存放在request中
        //login failed, let request continue back to the login page:
        return true; //还回true让过滤连继续走下去，跟成功登录的流程一样，所以会访问上一个请求，也就是表单提交请求
    }

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String className = ae.getClass().getName();
        request.setAttribute(getFailureKeyAttribute(), className);
    }

    public String getUsernameParam() {
        return usernameParam;
    }

    public void setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    public String getPasswordParam() {
        return passwordParam;
    }

    public void setPasswordParam(String passwordParam) {
        this.passwordParam = passwordParam;
    }

    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    protected String getPassword(ServletRequest request) {
        return WebUtils.getCleanParam(request, getPasswordParam());
    }

    protected String getUsername(ServletRequest request) {
        return WebUtils.getCleanParam(request, getUsernameParam());
    }

    protected boolean isLoginSubmission(ServletRequest request) {
        return (request instanceof HttpServletRequest) && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
    }
}
