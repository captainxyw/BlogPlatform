package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/{name}")
    public Object index(@PathVariable String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        if(user == null) {
            return "redirect:/login.html";
        }
        ModelAndView mv = new ModelAndView("account/" + name);
        return mv;
    }


    @RequestMapping("/password/change")
    public Object changePassword(String oldPassword, String password) {
        accountService.updatePassword(oldPassword, password);
        return "redirect:/account/password";
    }
    
}
