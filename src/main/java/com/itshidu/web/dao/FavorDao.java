package com.itshidu.web.dao;

import com.itshidu.web.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/2/2 17:51
 * @Author:xuyewei
 */

public interface FavorDao extends JpaRepository<Favor, Long> {

    @Query("from Favor f where f.user.id = ?1 and f.article.id = ?2")
    Favor find(long user, long article);

    @Query("select count(id) from Favor f where f.article.id = ?1")
    int countByArticle(long articleId);
}
