package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.APIService;
import com.itshidu.web.util.EhcacheUtil;
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

    @Autowired
    UserDao userDao;

    @Override
    public List<User> hotusers(int maxSize) {
        //先查缓存
        //没有，查询
        List<User> data = EhcacheUtil.get("Cache1", "hotusers");

        if (data == null || data.isEmpty()) {
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            Pageable pageable = PageRequest.of(0, maxSize, sort);
            Page<User> page = userDao.findAll(pageable);
            data = page.getContent();
            EhcacheUtil.put("Cache1", "hotusers", data, 30, 30);
        }
        return data;
    }

    @Override
    public List<Article> latests(int maxSize) {
        //先查缓存
        //没有，查询
        List<Article> data = EhcacheUtil.get("Cache1", "article_latests");

        if (data == null || data.isEmpty()) {
            System.out.println("未发现缓存，发起sql");
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            Pageable pageable = PageRequest.of(0, maxSize, sort);
            Page<Article> page = articleDao.findAll(pageable);
            data = page.getContent();
            EhcacheUtil.put("Cache1", "article_latests", data, 10, 0);
        } else {
            System.out.println("缓存命中，直接使用");
        }


        return data;
    }

    @Override
    public List<Article> hots(int maxSize) {
        //先查缓存
        //没有，查询
        List<Article> data = EhcacheUtil.get("Cache1", "hotArticles");

        if (data == null || data.isEmpty()) {
            Sort sort = new Sort(Sort.Direction.DESC, "hits");
            Pageable pageable = PageRequest.of(0, maxSize, sort);
            Page<Article> page = articleDao.findAll(pageable);
            data = page.getContent();
            EhcacheUtil.put("Cache1", "hotArticles", data, 60, 60);
        }
        return data;
    }
}
