package com.itshidu.web.dao;

import com.itshidu.web.entity.Notify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/2/2 23:33
 * @Author:xuyewei
 */

public interface NotifyDao extends JpaRepository<Notify, Long> {

    @Query("from Notify n where n.user.id = ?1")
    Page<Notify> findByUser(long userId, Pageable pageable);
}
