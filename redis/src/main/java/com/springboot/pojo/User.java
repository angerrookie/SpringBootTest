package com.springboot.pojo;

import javax.persistence.*;

/**
 * @author Administrator
 * @description 实体类
 * @date 2020/1/3
 */
@Entity
@Table(name = "springboot")
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

    public User() {
    }

    public User(String userName, String passWord, String address) {
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
