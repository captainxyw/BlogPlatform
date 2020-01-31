package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/31 17:04
 * @Author:xuyewei
 */
@Service
public class APIInterfaceImpl implements APIService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<User> hotusers(int maxSize) {
        return null;
    }

    @Override
    public List<Article> latests(int maxSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, maxSize, sort);
        Page<Article> page = articleDao.findAll(pageable);
        return page.getContent();
    }

    @Override
    public List<Article> hots(int maxSize) {
        return null;
    }
}
