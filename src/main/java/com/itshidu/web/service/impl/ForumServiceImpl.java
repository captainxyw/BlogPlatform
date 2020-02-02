package com.itshidu.web.service.impl;

import com.itshidu.web.dao.ArticleDao;
import com.itshidu.web.dao.CommentDao;
import com.itshidu.web.dao.FavorDao;
import com.itshidu.web.entity.Article;
import com.itshidu.web.service.ForumService;
import com.itshidu.web.util.EhcacheUtil;
import com.itshidu.web.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    CommentDao commentDao;
    @Autowired
    FavorDao favorDao;

    @Override
    public void findForumArticles(String forumCode, String sortType, int page, ModelAndView mv) {

        Sort sort = null;
        if (sortType == null || sortType.trim().length() == 0) {
            sort = new Sort(Sort.Direction.DESC, "createTime");
        } else if ("newest".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "createTime");
        } else if ("hottest".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "hits");
        }
        Pageable pageable = PageRequest.of(page - 1, 7, sort);

        Page<Article> data = articleDao.findByForumCode(forumCode, pageable);

        //将分页信息传到页面（不使用其中的文章信息）
        mv.addObject("pageInfo", data);

        List<ArticleVO> list = new ArrayList<>();
        //查询每个文章的评论数、喜欢数
        for (int i=0; i<data.getContent().size(); i++) {
            Article a = data.getContent().get(i);
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(a, articleVO);
            list.add(articleVO);

            //加缓存
            String commentKey = "articleComment_" + a.getId();
            Integer commnetCount = EhcacheUtil.get("mytest", commentKey);
            if(commnetCount == null) {
                commnetCount = commentDao.countByArticleId(a.getId());
                System.out.println(commnetCount);
                EhcacheUtil.put("mytest", commentKey, commnetCount, 10, 10);
            }
            articleVO.setCommentCount(commnetCount);


            String favorKey = "articleFavor" + a.getId();
            Integer favorCount = EhcacheUtil.get("mytest", favorKey);
            if(favorCount == null) {
               favorCount = favorDao.countByArticle(a.getId());
               EhcacheUtil.put("mytest", favorKey, favorCount, 10, 10);
            }
            articleVO.setLikeCount(favorCount);

        }

        mv.addObject("articleList", list);

    }
}
