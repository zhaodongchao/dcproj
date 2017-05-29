package org.dongchao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhaodongchao on 2017/5/3.
 */
@RequestMapping(value = "login")
@Controller
public class LoginHandler {

    @RequestMapping(value = {"index"})
    public String login(){
        return "admin/login" ;
    }
    @RequestMapping(value = {"success"})
    public String loginSuccess(){
        return "admin/homepage";
    }
}
