package com.itshidu.web.service.impl;

import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.RegisterService;
import com.itshidu.web.util.DigestHelper;
import com.itshidu.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/24 23:55
 * @Author:xuyewei
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserDao _userDao;

    @Override
    public Map<String, Object> register(User user) {


        User t = _userDao.findByUsername(user.getUsername());
        if (t != null) {
            return Result.of(100, "用户名已经存在");
        }
        if (_userDao.findByEmail(user.getEmail()) != null) {
            return Result.of(101, "邮箱已经存在，请更换");
        }

        user.setAvatar("default.jpg");
        user.setCreateTime(new Date());
        user.setStatus(0);  //未激活

        user.setSalt(UUID.randomUUID().toString());
        String m = user.getPassword();
        String s = user.getSalt();
        String r = md5(sha512(m) + md5(s) + sha512(m+s));
//        String r1 = sha512(md5(sha512(m) + md5(s) + sha512(m+s)) ).substring(1, 20);

        user.setPassword(r);

        _userDao.save(user);

        return Result.of(200, "注册成功", user);

    }



    private String md5(String text) {
        return DigestHelper.md5(text);
    }

    private String sha512(String text) {
        return DigestHelper.sha512(text);
    }


    @Override
    public Result login(String username, String password) {
        User user = _userDao.findByUsername(username);
        if(user == null) {
            return Result.of(100, "用户名不存在");
        }

        String m = password;  //明文密码
        String s = user.getSalt();      //密码盐值
        String r = md5(sha512(m) + md5(s) + sha512(m+s));

        if(r.equals(user.getPassword())) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", user);


            return Result.of(200, "登录成功");
        }

        return Result.of(100, "密码不正确");

    }
}
