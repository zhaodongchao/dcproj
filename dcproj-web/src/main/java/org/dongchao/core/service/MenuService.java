package org.dongchao.core.service;

import org.dongchao.core.dao.MenuDao;
import org.dongchao.model.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaodongchao on 2017/5/27.
 */
@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;
    public List<Menu> getMenus(String node){
        return menuDao.findAllByParentIdEquals(node);
    }
}
