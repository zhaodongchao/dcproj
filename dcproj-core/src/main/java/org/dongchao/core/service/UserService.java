package org.dongchao.core.service;

import org.dongchao.core.dao.UserDao;
import org.dongchao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaodongchao on 2017/5/4.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao ;
    public List<User> findAllUser(){
        List<User> users = userDao.findAll();
        return users ;
    }
}
