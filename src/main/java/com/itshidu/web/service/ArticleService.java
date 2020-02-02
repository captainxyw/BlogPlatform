package com.itshidu.web.service;

import com.itshidu.web.entity.Article;
import com.itshidu.web.vo.Result;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/28 20:45
 * @Author:xuyewei
 */

public interface ArticleService {

    Result save(String title, long group, String content, HttpServletRequest request);

    void view(long articleId, ModelAndView modelAndView);

    Article getArticleById(long articleId);
}
