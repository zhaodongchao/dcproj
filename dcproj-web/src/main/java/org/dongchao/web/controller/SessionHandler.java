package org.dongchao.web.controller;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基于shiro的会话管理控制类
 * Created by zhaodongchao on 2017/5/21.
 */
@RequestMapping(value = {"admin"})
@Controller
public class SessionHandler {
    @Autowired
    private SessionDAO sessionDAO;
    @RequestMapping(value = {"session/list"},method = RequestMethod.GET)
    public String listSession(Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        Set<Map<String,Object>> sessionInfos = new HashSet<>();
        Iterator it = sessions.iterator();
        while(it.hasNext()){
            Session session = (Session) it.next();
            Object principal = session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
            Object is_authenticated = session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY");
            if (null != principal && is_authenticated.equals(true) ){
                Map<String,Object> sessionInfo = new Hashtable<>();
                Set princiapal = ((SimplePrincipalCollection)principal).asSet() ;
                sessionInfo.put("sessionId",session.getId());
                sessionInfo.put("loginUser",princiapal.iterator().next());
                sessionInfo.put("loginIP",session.getHost());
                sessionInfo.put("createTime",sdf.format(session.getStartTimestamp()));
                sessionInfo.put("lastAccessTime",sdf.format(session.getLastAccessTime()));
                sessionInfos.add(sessionInfo);
            }
        }
        model.addAttribute("sessions",sessionInfos);
        return "admin/system/sessionManager" ;
    }
    @RequestMapping("session/forceLogout/{sessionId}")
    public String forceLogout(@PathVariable("sessionId") String sessionId,
                              RedirectAttributes redirectAttributes) {
        try {
            Session session = sessionDAO.readSession(sessionId);
            if(session != null) {
                session.setAttribute("", Boolean.TRUE);
            }
        } catch (Exception e) {/*ignore*/}
        redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
        return "redirect:/session/list";
    }
}
