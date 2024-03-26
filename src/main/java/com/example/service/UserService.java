package com.example.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.pojo.User;

@Component
public interface UserService {
	public void registerUser(User user);

	void deleteUserById(int id);

	void updateUser(User user);

	User getUserById(int id);

	List<User> getAllUsers();

	public User login(String username);

	public boolean isUsernameExists(String username);


	
}
