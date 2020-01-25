package com.itshidu.web.dao;

import com.itshidu.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package:com.itshidu.web.dao
 * Description:
 *
 * @Date:2020/1/25 0:00
 * @Author:xuyewei
 */

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
