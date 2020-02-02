package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.FavorDao;
import com.itshidu.web.dao.ForumDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Forum;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.ArticleService;
import com.itshidu.web.util.EhcacheUtil;
import com.itshidu.web.vo.ArticleVO;
import com.itshidu.web.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    FavorDao favorDao;

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
    public void view(long articleId, ModelAndView mv) {
        Article a = articleDao.getOne(articleId);
        ArticleVO vo = new ArticleVO();
        BeanUtils.copyProperties(a, vo);

        String favorKey = "articleFavor" + a.getId();
        Integer favorCount = EhcacheUtil.get("mytest", favorKey);
        if(favorCount == null) {
            favorCount = favorDao.countByArticle(a.getId());
            EhcacheUtil.put("mytest", favorKey, favorCount, 10, 10);
        }
        vo.setLikeCount(favorCount);
        mv.addObject("articleInfo", vo);
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleDao.getOne(articleId);
    }
}
