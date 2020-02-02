package com.itshidu.web.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/1/31 21:55
 * @Author:xuyewei
 */

public interface TAService {

    void findData(long userId, int page, ModelAndView model);
}
