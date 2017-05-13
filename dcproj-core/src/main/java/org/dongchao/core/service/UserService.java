package org.dongchao.core.service;

import org.dongchao.core.dao.UserDao;
import org.dongchao.model.entity.Role;
import org.dongchao.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public User findUserByName(String username) {
        User user = userDao.findUserByUsername(username);
        this.formatUser(user);
        return user;
    }

    private void formatUser(User user) {
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRole()) {
            roles.add(role.getRoleCode());

        }
        user.setRoles(roles);
    }
}
