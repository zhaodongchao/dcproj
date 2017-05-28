package org.dongchao.facade.webservices.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by zhaodongchao on 2017/5/8.
 */
@WebService
public interface IUserFacade {

    String getUserByname(@WebParam(name = "username") String username);
}
