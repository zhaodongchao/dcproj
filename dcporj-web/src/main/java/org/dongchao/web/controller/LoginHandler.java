package org.dongchao.web.controller;

import org.dongchao.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhaodongchao on 2017/5/3.
 */
@RequestMapping("test")
@Controller
public class LoginHandler {
    @Autowired
    private UserService userService ;
    @RequestMapping("hello")
    public ModelAndView helloWorld(){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("users",userService.findAllUser());
        return  modelAndView ;
    }
    @RequestMapping("home")
    public String goHome(){
        return "hello" ;
    }
}
