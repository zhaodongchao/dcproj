package org.dongchao.facade.webservices;

import org.dongchao.facade.webservices.interfaces.IUserFacade;

import javax.jws.WebService;

/**
 * Created by zhaodongchao on 2017/5/12.
 */
@WebService(endpointInterface = "org.dongchao.facade.webservices.interfaces.IUserFacade")
public class UserServiceImpl implements IUserFacade {
    @Override
    public String hello() {
        return "haha";
    }

    @Override
    public String getUserByname(String username) {

        return "调用过了" + username;
    }
}
