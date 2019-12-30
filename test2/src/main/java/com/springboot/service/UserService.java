package com.springboot.service;

import com.springboot.dao.DaoImp;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @description 业务层
 * @date 2019/12/30
 */
@Service("service")
public class UserService {
    @Autowired
    @Qualifier("dao")
    private DaoImp daoImp;

    /**
     * 功能描述: 
     * @Param: []
     * @Return: java.util.List<com.example.pojo.User>
     * @Author: Administrator
     * @Date: 2019/12/30 14:54
     */
    public List<User> findAll(){
        return daoImp.findAll();
    }
    /**
     * 功能描述: 
     * @Param: [userId]
     * @Return: com.example.pojo.User
     * @Author: Administrator
     * @Date: 2019/12/30 14:54
     */
    public User findById(Integer userId){
        return daoImp.findById(userId);
    }
    /**
     * 功能描述: 
     * @Param: [user]
     * @Return: boolean
     * @Author: Administrator
     * @Date: 2019/12/30 14:54
     */
    @Transactional
    public boolean insert(User user){
        return daoImp.insert(user);
    }
    /**
     * 功能描述: 
     * @Param: [user]
     * @Return: boolean
     * @Author: Administrator
     * @Date: 2019/12/30 14:54
     */
    @Transactional
    public boolean updateById(User user){
        return daoImp.updateById(user);
    }
    /**
     * 功能描述: 
     * @Param: [userId]
     * @Return: boolean
     * @Author: Administrator
     * @Date: 2019/12/30 14:54
     */
    @Transactional
    public boolean deleteById(Integer userId){
        return daoImp.deleteById(userId);
    }
}
