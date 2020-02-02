package com.itshidu.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Package:com.itshidu.web.entity
 * Description:
 *
 * @Date:2020/2/2 17:46
 * @Author:xuyewei
 */
@Entity
@Table(name = "blog_favor")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "article_id")
    private Article article;
    private Date created;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
