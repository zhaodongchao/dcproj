package org.dongchao.facade.webservices;

import org.dongchao.core.service.UserService;
import org.dongchao.facade.webservices.interfaces.IUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by zhaodongchao on 2017/5/12.
 */
@Component
@WebService(endpointInterface = "org.dongchao.facade.webservices.interfaces.IUserFacade")
public class UserServiceImpl implements IUserFacade {
    @Autowired
    private UserService userService;
    @Override
    public String hello() {
        return "haha";
    }

    @Override
    public String getUserByname(String username) {
        return userService.findUserByName(username).getUsername();
    }
}
