package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/25 21:08
 * @Author:xuyewei
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = {"", "/", "/index"})
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        if(user == null) {
            return "redirect:/login.html";
        }

        ModelAndView mv = new ModelAndView("home/index");
        return mv;
    }

}
