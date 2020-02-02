package com.itshidu.web.util;

import com.itshidu.web.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Package:com.itshidu.web.util
 * Description:
 *
 * @Date:2020/2/2 21:58
 * @Author:xuyewei
 */

public class LoginUtil {

    public static String loginInfo = "loginInfo";

    public static User getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (User) session.getAttribute(loginInfo);
    }
}
