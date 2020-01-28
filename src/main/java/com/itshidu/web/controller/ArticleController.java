package com.itshidu.web.controller;

import com.itshidu.web.entity.User;
import com.itshidu.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/28 20:28
 * @Author:xuyewei
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/article/create", method = RequestMethod.GET)
    public Object create(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginInfo");
        if(user == null)
            return "redirect:/login.html";

        ModelAndView mv = new ModelAndView("article/create");

        return mv;
    }


    @RequestMapping(value = "/article/create", method = RequestMethod.POST)
    public Object create(String title, long group, String content, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginInfo");
        if(user == null)
            return "redirect:/login.html";


        articleService.save(title, group, content, request);

        return "redirect:/home";
    }

    @RequestMapping(value = "/view/{articleId}.html", method = RequestMethod.GET)
    public Object view(@PathVariable long articleId, HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("article/view");
        mv.addObject("articleInfo", articleService.getArticleById(articleId));
        return mv;
    }

}
