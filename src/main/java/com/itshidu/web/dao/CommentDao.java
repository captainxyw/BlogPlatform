package com.itshidu.web.dao;

import com.itshidu.web.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/1/28 23:15
 * @Author:xuyewei
 */

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query("from Comment c where c.article.id=?1")
    Page<Comment> findByArticleId(Long articleId, Pageable pageable);


    @Query("select count(*) from Comment c where c.article.id=?1")
    int countByArticleId(Long articleId);
}
