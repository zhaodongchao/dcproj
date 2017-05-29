package org.dongchao.core.service;


import org.dongchao.core.dao.UserDao;
import org.dongchao.model.common.ResultModel;
import org.dongchao.model.entity.Permission;
import org.dongchao.model.entity.Role;
import org.dongchao.model.entity.User;
import org.dongchao.utils.common.JsonUtil;
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
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
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

    public String findUserInfoForWS(String username) {
        ResultModel result;
        User user = findUserByName(username);
        if (null == user) {
            result = new ResultModel(false, "没有找到名字为" + username + "的用户");
            return JsonUtil.Object2JsonString(result);
        }
        result = new ResultModel(true, "成功找到用户：" + username, user.toString());
        return JsonUtil.Object2JsonString(result);
    }

    /**
     * 获取当前session中的登录用户的信息
     */
    public User getCurrentUser(){
        User currentUser = null ;
        return currentUser ;
    }

    /**
     * 将用户的密码加密存入数据库
     */
    public void encryptPassword(User user) {

        user.setPassword("");
    }
}
