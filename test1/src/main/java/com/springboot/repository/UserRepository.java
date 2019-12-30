package com.springboot.repository;

import com.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @description
 * @date 2019/12/26
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
