package com.itshidu.web.controller;

import com.itshidu.web.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/24 21:45
 * @Author:xuyewei
 */

@Controller
public class ForumController {

    @Autowired
    ForumService forumService;

    @RequestMapping("/forum/{code}")
    public Object group(@PathVariable String code, String ord, @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
        //版块code
        ModelAndView mv = new ModelAndView("forum");
        forumService.findForumArticles(code, ord, page, mv);
        return mv;
    }

    @RequestMapping("/")
    public Object index() {

        return "redirect:/forum/blog";
    }
}
