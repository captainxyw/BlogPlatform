package com.itshidu.web.service;

import com.itshidu.web.entity.Article;
import com.itshidu.web.vo.Result;

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

    Result view(long articleId);

    Article getArticleById(long articleId);
}
