package com.itshidu.web.service.impl;

import com.itshidu.web.dao.CommentDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.Comment;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.CommentService;
import com.itshidu.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/28 23:16
 * @Author:xuyewei
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public Result save(Long articleId, Long targetCommnetId, String text) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");

        if(user == null) {
            return Result.of(0, "未登录");
        }


        Comment c = new Comment();      //创建一个新对象
        Article article = new Article();
        article.setId(articleId);
        Comment target = null;
        if(targetCommnetId != 0) {
            target = new Comment();
            target.setId(targetCommnetId);
        }

        c.setArticle(article);
        c.setAuthor(user);
        c.setContent(text);
        c.setCreated(new Date());
        c.setTarget(target);
        commentDao.save(c);

        return Result.of(1, "发表评论成功");
    }

    @Override
    public Result list(Long articleId, int pageSize, int pn) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(pn-1, pageSize, sort);
        Page<Comment> data = commentDao.findByArticleId(articleId, pageable);
        return Result.of(1, "OK", data);
    }
}
