package com.springboot.dao;

import com.springboot.pojo.User;

import java.util.List;

public interface IDao {
    public List<User> findAll();
    public User findById(Integer userId);
    public boolean insert(User user);
    public boolean updateById(User user);
    public boolean deleteById(Integer userId);
}
