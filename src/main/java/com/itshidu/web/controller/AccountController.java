package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.AccountService;
import com.itshidu.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Object index(@PathVariable String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        if (user == null) {
            return "redirect:/login.html";
        }
        ModelAndView mv = new ModelAndView("account/" + name);
        return mv;
    }

    @ResponseBody
    @RequestMapping("/password/change")
    public Object changePassword(String oldPassword, String password) {

        return accountService.updatePassword(oldPassword, password);
    }

    @RequestMapping("/profile/change")
    public Object changeProfile(String nickname, String sign) {
        accountService.updateProfile(nickname, sign);
        return "redirect:/account/profile";
    }


    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public Object updateAvatar(int x, int y, int width, int height, String path, HttpServletRequest request) {
        Result r = accountService.updateAvatar(x, y, width, height, path, request);

        int code = (int) r.get("code");
        if (code == 1) {
            return "redirect:/login.html";
        }
        if (code == 2) {
            return "redirect:/account/avatar";
        }
        return "redirect:/500.html";

    }
    @ResponseBody
    @RequestMapping(value = "/favor", method = RequestMethod.GET)
    public Object favor(long id, HttpServletRequest request) {

        return accountService.saveFavor(id, request);

    }

    //关注,要求已登录
    @ResponseBody
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public Object follow(long id, HttpServletRequest request) {

        return accountService.saveFollow(id);
    }

    //检查是否关注某个人，要求已登陆
    @ResponseBody
    @RequestMapping(value = "/follow/check/{userId}", method = RequestMethod.POST)
    public Object followCheck(@PathVariable long userId, HttpServletRequest request) {
        return accountService.followCheck(userId);
    }

    //取消关注
    @ResponseBody
    @RequestMapping(value = "/unfollow", method = RequestMethod.GET)
    public Object unfollow(long id, HttpServletRequest request) {

        return accountService.unfollow(id);
    }

}
