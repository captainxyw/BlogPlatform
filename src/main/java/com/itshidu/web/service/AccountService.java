package com.itshidu.web.service;

import com.itshidu.web.vo.Result;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/25 22:46
 * @Author:xuyewei
 */

public interface AccountService {

    Result updatePassword(String oldPassword, String newPassword);

    Result updateProfile(String nickname, String sign);
}
