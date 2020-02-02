package com.itshidu.web.service;

import com.itshidu.web.vo.Result;

import javax.servlet.http.HttpServletRequest;

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

    Result updateAvatar(int x, int y, int width, int height, String path, HttpServletRequest request);

    Result saveFavor(long articleId, HttpServletRequest request);

    Result saveFollow(long id);
    /** 检查是否关注了某个人 */
    Result followCheck(long userId);

    /** 取消关注某个人*/
    Result unfollow(long id);
}
