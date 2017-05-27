package org.dongchao.web.controller;

import org.dongchao.core.service.MenuService;
import org.dongchao.model.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhaodongchao on 2017/5/27.
 */
@Controller
@RequestMapping("admin/menu/")
public class MenuHandler {
    @Autowired
    private MenuService menuService ;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getMenus(){
        return menuService.getMenus();
    }
}
