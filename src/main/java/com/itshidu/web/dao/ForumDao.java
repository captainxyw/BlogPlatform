package com.itshidu.web.dao;

import com.itshidu.web.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/1/27 23:14
 * @Author:xuyewei
 */

public interface ForumDao extends JpaRepository<Forum, Long> {

}
