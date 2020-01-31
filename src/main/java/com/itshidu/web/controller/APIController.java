package com.itshidu.web.controller;

import com.itshidu.web.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package:com.itshidu.web.controller
 * Description:
 *
 * @Date:2020/1/31 16:54
 * @Author:xuyewei
 */

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    APIService apiService;
    /**
     * 粉丝最多的用户（先用最新用户代替
     * @param maxResults
     * @return
     */
    @RequestMapping("/hotusers.json")
    public Object hotusers(int maxResults) {
        return null;
    }

    /**
     * 最新发布的文章
     * @param maxResults
     * @return
     */
    @RequestMapping("/latests.json")
    public Object latests(int maxResults) {
        return apiService.latests(maxResults);
    }

    /**
     * 点击量最高的文章
     * @param maxResults
     * @return
     */
    @RequestMapping("/hots.json")
    public Object hots(int maxResults) {
        return null;
    }

}
