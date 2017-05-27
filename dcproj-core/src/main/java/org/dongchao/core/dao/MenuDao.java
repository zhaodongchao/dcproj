package org.dongchao.core.dao;

import org.dongchao.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhaodongchao on 2017/5/27.
 */
public interface MenuDao extends JpaRepository<Menu,Integer> {
     List<Menu> findAllByParentIdIsNull();
}
