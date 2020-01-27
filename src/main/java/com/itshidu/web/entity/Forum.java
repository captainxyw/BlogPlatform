package com.itshidu.web.entity;

import javax.persistence.*;

/**
 * Package:com.itshidu.web.entity
 * Description:
 *
 * @Date:2020/1/23 20:15
 * @Author:xuyewei
 */

@Entity
@Table(name = "blog_group")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;    //版块标记,使用字符和数字，访问时候会作为url一部分
    private Boolean status;  //状态:true显示, false隐藏

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
