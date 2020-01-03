package com.springboot.pojo;

import javax.persistence.*;

/**
 * @author Administrator
 * @description 实体类
 * @date 2020/1/3
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userName")
    private String passWord;

    @Column(name = "userName")
    private String address;

}
