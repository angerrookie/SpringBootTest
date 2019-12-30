package com.springboot.dao;

import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @description 实现类
 * @date 2019/12/30
 */
@Repository("dao")
public class DaoImp implements IDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "select userId,userName,passWord,address from User";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User findById(Integer userId) {
        String sql = "select userId,userName,passWord,address from User where userId=?";
        Object[] params = {userId};
        return jdbcTemplate.queryForObject(sql,params,new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public boolean insert(User user) {
        String sql = "insert into User(userName,passWord,address)value(?,?,?)";
        Object[] params = {user.getUserName(),user.getPassWord(),user.getAddress()};
        return jdbcTemplate.update(sql,params)>0;
    }

    @Override
    public boolean updateById(User userId) {
        String sql = "update User set userName=?,passWord=?,address=? where userId=?";
        User user = findById(userId.getUserId());
        Object[] params ={user.getUserName().equals(userId.getUserName())?user.getUserName():userId.getUserName(),
        user.getPassWord().equals(userId.getPassWord())?user.getPassWord():userId.getPassWord(),
        user.getAddress().equals(userId.getAddress())?user.getAddress():userId.getAddress(),userId.getUserId()};
        return jdbcTemplate.update(sql,params)>0;
    }

    @Override
    public boolean deleteById(Integer userId) {
        String sql = "delete from User where userId=?";
        return jdbcTemplate.update(sql,new Object[]{userId})>0;
    }
}
