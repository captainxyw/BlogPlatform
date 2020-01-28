package com.itshidu.web.service.impl;

import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.AccountService;
import com.itshidu.web.util.DigestHelper;
import com.itshidu.web.vo.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Value("${STORE_ROOT_PATH}")
    String StoreRootPath;   //存储根目录


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

    @Override
    public Result updateProfile(String nickname, String sign) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginInfo");

        User user = userDao.getOne(loginUser.getId());

        user.setNickname(nickname);
        user.setSign(sign);
        userDao.save(user);

        session.setAttribute("loginInfo", user);
        return null;
    }

    @Override
    public Result updateAvatar(int x, int y, int width, int height, String path, HttpServletRequest request) {

        Date date = new Date();
        String a = "/store/avatar/"
            + new SimpleDateFormat("yyyy").format(date) + "/"
            + new SimpleDateFormat("MM").format(date) + "/"
            + new SimpleDateFormat("dd").format(date) + "/"
            + UUID.randomUUID().toString() + ".jpg";

        File local = new File(StoreRootPath, a);
        File dir = local.getParentFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(StoreRootPath, path);

        try {
            Thumbnails.of(file).sourceRegion(x, y, width, height)
                .size(width, height)
                .outputFormat("jpg")
                .toFile(local);
            User loginUser = (User) request.getSession().getAttribute("loginInfo");
            if(loginUser == null) {
                return Result.of(1);    //1代表未登录
            }
            User user = userDao.getOne(loginUser.getId());
            user.setAvatar(a);
            userDao.save(user);
            request.getSession().setAttribute("loginInfo", user);
            return Result.of(2);    //2代表修改成功


        } catch (IOException e) {
            e.printStackTrace();
            return Result.of(3, e.toString());    //3代表异常
        }

    }

}
