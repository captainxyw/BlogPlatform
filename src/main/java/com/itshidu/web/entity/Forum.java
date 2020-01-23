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
    private long id;
    private String name;
    private String code;    //版块标记
    private boolean status;  //状态:true显示, false隐藏

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
