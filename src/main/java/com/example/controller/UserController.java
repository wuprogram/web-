package com.example.controller;

import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	
	

	 @GetMapping("/checkusername")
	 @ResponseBody
	    public String checkUsername(@RequestParam String username) {
	        // 调用 UserMapper 中的方法检查用户名是否存在
	        if (userMapper.existsByUsername(username)) {
	            return "exists"; // 如果用户名存在，返回 'exists'
	        } else {
	            return "available"; // 如果用户名不存在，返回 'available'
	        }
	    }
	 
    @PostMapping("/register")
    @ResponseBody
    public String registerUser( User user) {
    	if(userMapper.existsByUsername(user.getUsername())) {
    		return "用户名已存在，注册失败！";
    	}else if(!user.getPassword().equals(user.getConfirmPassword())) {
    		return "两次密码输入不同，注册失败！";
    	}
    	else {
    		userMapper.addUser(user);
            return "用户注册成功！";
		}
    }
    @PostMapping("/updateProfile")
    public ModelAndView updateUser(User user) {
    	if(userMapper.existsByUsername(user.getUsername())) {
    		ModelAndView modelAndView = new ModelAndView("/profile.html");
        	return modelAndView;
    	}
    	else {
    		userMapper.updateUser(user);
        	ModelAndView modelAndView = new ModelAndView("/profile.html");
        	return modelAndView;
		}
    	
    }
    @PostMapping("/confirm")
    public ModelAndView  confirm(@RequestParam String username, @RequestParam String password,Model model) {
        // 根据用户名和密码进行认证
        User user = userMapper.getUserByname(username);

        if (user != null && password.equals(user.getPassword())) {
            // 认证成功，跳转到信息展示界面
            System.out.println("验证成功！！！");
            ModelAndView modelAndView = new ModelAndView("/updateProfile.html");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            // 认证失败，返回登录页面并显示错误消息
            System.out.println("验证失败！！！");
            model.addAttribute("error", "Invalid username or password");
            ModelAndView modelAndView = new ModelAndView("/profile.html");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
    }
    
    @PostMapping("/login")
    public ModelAndView  login(@RequestParam String username, @RequestParam String password,Model model) {
        // 根据用户名和密码进行认证
        User user = userMapper.getUserByname(username);

        if (user != null && password.equals(user.getPassword())) {
            // 认证成功，跳转到信息展示界面
            System.out.println("登陆成功！！！");
            model.addAttribute("user", user);
            ModelAndView modelAndView = new ModelAndView("/profile.html");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            // 认证失败，返回登录页面并显示错误消息
        	System.out.println(user);
            System.out.println("登陆失败！！！");
            model.addAttribute("error", "Invalid username or password");
            ModelAndView modelAndView = new ModelAndView("/login.html");
            return modelAndView;
        }
    }
    
    @RequestMapping("/demo")
    public void demo() {
    	System.out.println("demo");
    }
}
