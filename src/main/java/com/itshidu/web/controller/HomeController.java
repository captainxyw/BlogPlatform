package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    HomeService homeService;

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

    @RequestMapping(value = {"/follows"})
    public Object follows(@RequestParam(defaultValue = "1") Integer page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        if(user == null) {
            return "redirect:/login.html";
        }

        ModelAndView mv = new ModelAndView("home/follows");
        homeService.follows(page, mv);
        return mv;
    }


    @RequestMapping(value = {"/fans"})
    public Object fans(@RequestParam(defaultValue = "1") Integer page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        if(user == null) {
            return "redirect:/login.html";
        }

        ModelAndView mv = new ModelAndView("home/fans");
        homeService.fans(page, mv);
        return mv;
    }

}
