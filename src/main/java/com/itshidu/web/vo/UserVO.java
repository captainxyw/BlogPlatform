package com.itshidu.web.vo;

import com.itshidu.web.entity.User;

/**
 * Package:com.itshidu.web.vo
 * Description:
 *
 * @Date:2020/2/2 22:24
 * @Author:xuyewei
 */

public class UserVO extends User {
    private int articleCount;   //文章数
    private int commentCount;

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
