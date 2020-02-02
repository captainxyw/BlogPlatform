package com.itshidu.web.vo;

import com.itshidu.web.entity.Article;

/**
 * Package:com.itshidu.web.vo
 * Description:
 *
 * @Date:2020/1/31 23:19
 * @Author:xuyewei
 */

public class ArticleVO extends Article {
    private long likeCount;
    private long commentCount;

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }
}
