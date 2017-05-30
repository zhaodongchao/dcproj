package org.dongchao.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于shiro的会话管理控制类
 * 现在只能管理当前用户相关的Session
 * Created by zhaodongchao on 2017/5/21.
 */
@RequestMapping(value = {"admin"})
@Controller
public class SessionHandler {
    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value = {"session/list"})
    @ResponseBody
    public Map<String, Object> listSessions() {
        Map<String,Object> mb = new TreeMap<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(principal, false);
        mb.put("sessionInfo",sessionInformationList);
        return mb;
    }

}
