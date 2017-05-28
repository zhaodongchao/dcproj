package org.dongchao.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.dongchao.web.security.filter.MyFormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaodongchao on 2017/5/3.
 */
@RequestMapping("/")
@Controller
public class LoginHandler {
    /**
     * 正真的登录验证是在FormAuthenticationFilter中进行的
     * 由于shiro在登录时会保存上一次的请求路径
     * GET请求用于请求登录页面，POST请求进行身份验证
     */
    @RequestMapping(value = {"login","/"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * * 在FormAuthenticationFilter.executeLogin 方法中的登录异常时会调用MyFormAuthenticationFilter.onLoginFailure
     * 会将登录异常信息添加到request中，不会抛出登录异常信息（关键）
     * 因此会导致就算登录失败，也不会提示失败，且会以登录成功的方式处理。也就是访问 /login post ，会执行到下面方法
     * 也就是说，只要是请求为loginUrl method=POST时， 不管MyFormAuthenticationFilter验证成功或者失败都会执行上一次的请求及表单提交的POST请求地址
     */
    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public ModelAndView success(HttpServletRequest request) {
        ModelAndView mv;
        if (null != request.getAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME)) {
            String exceptionClassName = request.getAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME).toString();
            mv = new ModelAndView("login");
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                mv.addObject("errorMsg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                mv.addObject("errorMsg", "用户名/密码错误");
            } else if ("randomCodeError".equals(exceptionClassName)) {
                mv.addObject("errorMsg", "验证码错误");
            } else {
                mv.addObject("errorMsg", "系统未知错误！");
            }
            return mv;
        }
        mv = new ModelAndView("admin/homepage");
        return mv;
    }

}
