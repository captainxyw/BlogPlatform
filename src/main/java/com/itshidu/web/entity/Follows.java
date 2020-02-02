package com.itshidu.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 关注
 * Package:com.itshidu.web.entity
 * Description:
 *
 * @Date:2020/2/2 20:17
 * @Author:xuyewei
 */
@Entity
@Table(name = "blog_follows")
public class Follows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "source_id")
    private User source;  //发起关注的人
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "target_id")
    private User target;    //被关注的人
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
