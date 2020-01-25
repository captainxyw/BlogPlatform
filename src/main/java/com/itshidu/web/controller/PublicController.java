package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/24 21:45
 * @Author:xuyewei
 */

@Controller
public class PublicController {

    @Autowired
    RegisterService _regService;

    @RequestMapping("/register.html")
    public Object register(ModelAndView mv) {
        mv.setViewName("register");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/public/register")
    public Object register(User user) {
        Map<String, Object> result = _regService.register(user);
        return result;
    }

    @ResponseBody
    @RequestMapping("/public/login")
    public Object login(String username, String password) {
        return _regService.login(username, password);
    }

    @RequestMapping("/public/logout")
    public Object logout(HttpServletRequest request) {
//        request.getSession().removeAttribute("loginInfo");
//        request.getSession().setMaxInactiveInterval(1);     //1s超时
        request.getSession().invalidate();
        return "redirect:/login.html";

    }
}
