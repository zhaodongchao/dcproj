package org.dongchao.service;


import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.dongchao.facade.security.ClientPasswordCallback;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaodongchao on 2017/5/12.
 */
public class UserServiceClient {
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dynamicClientFactory.createClient("http://localhost:8088/facade/service/user?wsdl");

        Map<String, Object> wss4jConfig = new HashMap<>();
        wss4jConfig.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        wss4jConfig.put(WSHandlerConstants.USER, "joe");
        wss4jConfig.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        wss4jConfig.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());

        WSS4JOutInterceptor wss4JOutInterceptor = new WSS4JOutInterceptor(wss4jConfig);

        client.getEndpoint().getOutInterceptors().add(wss4JOutInterceptor);
        QName name = new QName("http://interfaces.webservices.facade.dongchao.org/", "getUserByname");
        Object[] results = client.invoke(name, "dongdong");
        System.out.println(results[0]);
    }

}
