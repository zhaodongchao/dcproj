package org.dongchao.service;


import org.dongchao.facade.webservices.UserServiceImplService;

/**
 * Created by zhaodongchao on 2017/5/12.
 */
public class UserServiceClient {
    public static void main(String[] args) {
        UserServiceImplService serviceImplService = new UserServiceImplService();
        String s = serviceImplService.getUserServiceImplPort().getUserByname("dongdong");
        String s1 = serviceImplService.getUserServiceImplPort().hello();
        System.out.println(s);
        System.out.println(s1);
    }

}
