package com.itshidu.web.service.impl;

import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.AccountService;
import com.itshidu.web.util.DigestHelper;
import com.itshidu.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/25 22:47
 * @Author:xuyewei
 */

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    UserDao userDao;

    @Override
    public Result updatePassword(String oldPassword, String newPassword) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginInfo");

        User user = userDao.getOne(loginUser.getId());
        String m = oldPassword;  //明文密码
        String s = user.getSalt();      //密码盐值
        String r = md5(sha512(m) + md5(s) + sha512(m + s));

        if (r.equals(user.getPassword())) {
            user.setSalt(UUID.randomUUID().toString());//改个新的盐，更加安全
            String m1 = newPassword;
            String s1 = user.getSalt();
            String r1 = md5(sha512(m1) + md5(s1) + sha512(m1 + s1));

            user.setPassword(r1);
            userDao.save(user);
            return Result.of(200);
        } else {
            return Result.of(300);
        }
    }


    private String md5(String text) {
        return DigestHelper.md5(text);
    }

    private String sha512(String text) {
        return DigestHelper.sha512(text);
    }


}
