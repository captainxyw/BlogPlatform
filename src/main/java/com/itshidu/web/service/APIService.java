package com.itshidu.web.service;

import com.itshidu.web.entity.Article;
import com.itshidu.web.entity.User;

import java.util.List;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/31 17:04
 * @Author:xuyewei
 */

public interface APIService {

    List<User> hotusers(int maxSize);

    List<Article> latests(int maxSize);

    List<Article> hots(int maxSize);
}
