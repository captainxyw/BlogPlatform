package com.itshidu.web.service;

import com.itshidu.web.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/27 23:07
 * @Author:xuyewei
 */

public interface ForumService {

    void findForumArticles(String forumCode, String sortType, int page, ModelAndView mv);

}
