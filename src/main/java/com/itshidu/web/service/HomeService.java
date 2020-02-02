package com.itshidu.web.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * Package:com.itshidu.web.service
 * Description:
 *
 * @Date:2020/2/2 21:53
 * @Author:xuyewei
 */

public interface HomeService {

    void follows(Integer page, ModelAndView mv);

    void fans(Integer page, ModelAndView mv);
}
