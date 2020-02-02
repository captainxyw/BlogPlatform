package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.CommentDao;
import com.itshidu.web.dao.FollowsDao;
import com.itshidu.web.entity.Follows;
import com.itshidu.web.entity.User;
import com.itshidu.web.service.HomeService;
import com.itshidu.web.util.LoginUtil;
import com.itshidu.web.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.itshidu.web.service.impl
 * Description:
 *
 * @Date:2020/2/2 21:54
 * @Author:xuyewei
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    FollowsDao followsDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public void follows(Integer page, ModelAndView mv) {
        User loginUser = LoginUtil.getLoginUser();
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<Follows> data = followsDao.findFollows(loginUser.getId(), pageable);

        List<UserVO> list = new ArrayList<>();
        for (Follows fo : data.getContent()) {
            User user = fo.getTarget();
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            //该用户发表的文章数
            int articleCount = articleDao.countByUser(user.getId());
            int commentCount = commentDao.countByUserId(user.getId());

            vo.setArticleCount(articleCount);
            vo.setCommentCount(commentCount);
            list.add(vo);
        }

        mv.addObject("data", data);
        mv.addObject("voList", list);

    }
}
