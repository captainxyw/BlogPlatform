package com.itshidu.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Package:com.itshidu.web.entity
 * Description:
 *
 * @Date:2020/1/28 23:00
 * @Author:xuyewei
 */

@Entity
@Table(name = "blog_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;    //文章
    private Date created;       //评论时间
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;        //评论者
    @Column(columnDefinition = "TEXT")
    private String content;     //评论内容
    @ManyToOne
    @JoinColumn(name = "target_id")
    private Comment target;     //回复的目标

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getTarget() {
        return target;
    }

    public void setTarget(Comment target) {
        this.target = target;
    }
}
