package org.dongchao.core.dao;

import org.dongchao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaodongchao on 2017/5/4.
 */
public interface UserDao extends JpaRepository<User,Integer>{
    User findUserByUsername(String username);
}
