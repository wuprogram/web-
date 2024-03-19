package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;

import jakarta.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        userMapper.addUser(user);
    }
    @Override
    public User login(String username) {
    	return userMapper.getUserByname(username);
    };

    @Override
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
    
}
