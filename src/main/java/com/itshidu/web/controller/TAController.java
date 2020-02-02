package com.itshidu.web.controller;

import com.itshidu.web.service.TAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/31 16:54
 * @Author:xuyewei
 */

@Controller
@RequestMapping("")
public class TAController {

    @Autowired
    TAService taService;


    @RequestMapping("/ta/{userId}.html")
    public Object ta(@PathVariable long userId, @RequestParam(defaultValue = "1") Integer pn) {
        ModelAndView mv = new ModelAndView("ta/index");


        taService.findData(userId, pn, mv);


        return mv;

    }



}
