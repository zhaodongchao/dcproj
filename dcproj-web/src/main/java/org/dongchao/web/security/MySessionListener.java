package org.dongchao.web.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaodongchao on 2017/5/23.
 */
public class MySessionListener implements SessionListener {
    private static Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.info("--->正在创建Session id=" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.info("--->正在销毁Session id=" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("--->Session过期 id" + session.getId());
    }
}
