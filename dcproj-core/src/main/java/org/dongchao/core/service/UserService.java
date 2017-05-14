package org.dongchao.core.service;

import org.dongchao.core.dao.UserDao;
import org.dongchao.model.entity.Permission;
import org.dongchao.model.entity.Role;
import org.dongchao.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (null == user) {
            return;
        }
        List<String> roles = new ArrayList<>();
        Set<String> permissions = new HashSet<>();
        for (Role role : user.getRole()) {
            roles.add(role.getRoleCode());
            for (Permission permission : role.getPermissions()) {
                permissions.add(permission.getPermissionCode());
            }
        }
        user.setRoles(roles);
        user.setPermissions(permissions);
    }
}
