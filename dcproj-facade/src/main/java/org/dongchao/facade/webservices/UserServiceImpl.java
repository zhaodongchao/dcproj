package org.dongchao.facade.webservices;

import org.dongchao.facade.webservices.interfaces.IUserFacade;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by zhaodongchao on 2017/5/12.
 */
@Component
@WebService(endpointInterface = "org.dongchao.facade.webservices.interfaces.IUserFacade")
public class UserServiceImpl implements IUserFacade {

   /*
      提供WebService服务
     */
    @Override
    public String getUserByname(String username) {
        return username ;
    }
}
