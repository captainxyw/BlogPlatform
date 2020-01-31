package com.itshidu.web.controller;

import com.itshidu.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package:com.itshi/du.web.controller
 * Description:
 *
 * @Date:2020/1/28 23:19
 * @Author:xuyewei
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


    @ResponseBody
    @RequestMapping("/comment/submit")
    public Object submit(Long toId, Long pid, String text) {
        //toId:文章id pid:针对的评论 text:内容

        return commentService.save(toId, pid, text);
    }

    @ResponseBody
    @RequestMapping("/comment/list/{articleId}")
    public Object submit(@PathVariable Long articleId, int pageSize, int pn) {


        return commentService.list(articleId, pageSize, pn).get("data");
    }
}
