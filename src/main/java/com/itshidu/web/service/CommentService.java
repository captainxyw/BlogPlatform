package com.itshidu.web.service;

import com.itshidu.web.vo.Result;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/28 23:15
 * @Author:xuyewei
 */

public interface CommentService {

    Result save(Long articleId, Long targetCommnetId, String text);

    Result list(Long articleId, int pageSize, int pn);
}
