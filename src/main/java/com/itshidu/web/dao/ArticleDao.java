package com.itshidu.web.dao;

import com.itshidu.web.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/1/27 23:14
 * @Author:xuyewei
 */

public interface ArticleDao extends JpaRepository<Article, Long> {
    @Query("from Article a where  a.forum.code=?1")
    Page<Article> findByForumCode(String forumCode, Pageable pageable);


    @Query("from Article a where  a.user.id=?1")
    Page<Article> findByUserId(long userId, Pageable pageable);

    @Query("select count(id) from Article a where a.user.id=?1")
    int countByUser(long userId);

}
