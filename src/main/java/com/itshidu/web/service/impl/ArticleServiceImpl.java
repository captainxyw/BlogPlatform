package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.ForumDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.ArticleService;
import com.itshidu.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/28 20:45
 * @Author:xuyewei
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    UserDao userDao;
    @Autowired
    ForumDao forumDao;
    @Autowired
    ArticleDao articleDao;

    @Override
    public Result save(String title, long group, String content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginInfo");

        User user = userDao.getOne(loginUser.getId());
        Forum forum = forumDao.getOne(group);

        Article article =  new Article();
        article.setContent(content);
        article.setCreateTime(new Date());
        article.setForum(forum);
        article.setHits(0L);
        article.setTitle(title);
        article.setUser(user);

        articleDao.save(article);

        return null;
    }

    @Override
    public Result view(long articleId) {
        Article a = articleDao.getOne(articleId);

        return Result.of(1).put("article", a);
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleDao.getOne(articleId);
    }
}
