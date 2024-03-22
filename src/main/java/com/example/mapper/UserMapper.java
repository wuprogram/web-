package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.User;

@Mapper
public interface UserMapper {
    // 添加用户
    void addUser(User user);
    
    // 删除用户
    void deleteUserById(int id);
    
    // 更新用户信息
    void updateUser(User user);
    
    // 根据ID查询用户信息
    User getUserById(int id);
    
    //根据用户名查询用户信息
    User getUserByname(String username);
    
    // 查询所有用户
    List<User> getAllUsers();

	boolean existsByUsername(String username);
}
