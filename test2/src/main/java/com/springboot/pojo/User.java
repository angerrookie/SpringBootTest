package com.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @description 实体类
 * @date 2019/12/30
 */
@Data//自动创建setter get
@AllArgsConstructor//有参构造器
@NoArgsConstructor//无参构造器
public class User {
    private Integer userId;
    private String userName;
    private String passWord;
    private String address;
}
