package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/27 23:07
 * @Author:xuyewei
 */
@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Page<Article> findForumArticles(String forumCode, String sortType, int page) {

        Sort sort = null;
        if (sortType == null || sortType.trim().length() == 0) {
            sort = new Sort(Sort.Direction.DESC, "createTime");
        } else if ("newest".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "createTime");
        } else if ("hottest".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "hits");
        }
        Pageable pageable = PageRequest.of(page-1, 7, sort);

        return articleDao.findByForumCode(forumCode, pageable);
    }
}
