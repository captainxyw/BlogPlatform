package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.UserDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.TAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/1/31 21:56
 * @Author:xuyewei
 */
@Service
public class TAServiceImpl implements TAService {

    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;


    @Override
    public void findData(long userId, int page, ModelAndView model) {
        User user = userDao.getOne(userId);
        model.addObject("user", user);

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page, 10, sort);
        Page<Article> data = articleDao.findByUserId(userId, pageable);
        model.addObject("data", data);

    }
}
