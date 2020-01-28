package com.itshidu.web.listener;

import com.itshidu.web.dao.GroupDao;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.util.SpringContext;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Package:com.itshidu.web.listener
 * Description:
 *
 * @Date:2020/1/27 22:06
 * @Author:xuyewei
 */

public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext ctx = SpringContext.getApplicationContext();
        GroupDao groupDao = ctx.getBean(GroupDao.class);

        /*Forum  probe = new Forum();
        probe.setStatus(true);
        ExampleMatcher matcher =ExampleMatcher.matching();
        matcher.withIgnoreCase("id");
        Example<Forum> example = Example.of(probe, matcher);
        List<Forum> list = groupDao.findAll(example);*/

        List<Forum> list = groupDao.findByStatus(true);

        sce.getServletContext().setAttribute("FORUM_LIST", list);



    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
