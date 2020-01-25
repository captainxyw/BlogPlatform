package com.itshidu.web.service;

import com.itshidu.web.entity.User;
import com.itshidu.web.vo.Result;

import java.util.Map;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/24 23:55
 * @Author:xuyewei
 */

public interface RegisterService {
    Map<String, Object> register(User user);

    Result login(String username, String password);
}
