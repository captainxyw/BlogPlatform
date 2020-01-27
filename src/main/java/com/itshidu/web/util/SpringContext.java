package com.itshidu.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Package:com.itshidu.web.util
 * Description:
 *
 * @Date:2020/1/27 22:07
 * @Author:xuyewei
 */
@Component
public class SpringContext {


    private static ApplicationContext ctx;

    @Autowired
    public void set(ApplicationContext ctx) {
        this.ctx = ctx;
    }


    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}
