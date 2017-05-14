package org.dongchao.facade.webservices.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-05-14T19:28:49.769+08:00
 * Generated source version: 3.1.6
 */
@WebService(targetNamespace = "http://interfaces.webservices.facade.dongchao.org/", name = "IUserFacade")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserFacade {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getUserByname", targetNamespace = "http://interfaces.webservices.facade.dongchao.org/", className = "org.dongchao.facade.webservices.interfaces.GetUserByname")
    @WebMethod
    @ResponseWrapper(localName = "getUserBynameResponse", targetNamespace = "http://interfaces.webservices.facade.dongchao.org/", className = "org.dongchao.facade.webservices.interfaces.GetUserBynameResponse")
    String getUserByname(
            @WebParam(name = "username", targetNamespace = "")
                    String username
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://interfaces.webservices.facade.dongchao.org/", className = "org.dongchao.facade.webservices.interfaces.Hello")
    @WebMethod
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://interfaces.webservices.facade.dongchao.org/", className = "org.dongchao.facade.webservices.interfaces.HelloResponse")
    String hello();
}
