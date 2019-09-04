package com.wyk;

import java.io.Serializable;

/**
 * @author wuyankun
 * @title: User
 * @projectName json-util
 * @description: TODO
 * @date 2019/9/39:38
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
