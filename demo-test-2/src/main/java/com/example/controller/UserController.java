package com.example.controller;

import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/register")
    public String registerUser( User user) {
    	System.out.println(user);
    	System.out.println(user);
    	System.out.println(user);
    	System.out.println(user);
        userMapper.addUser(user);
        return "User registered successfully!";
    }
    @PostMapping("/updateProfile")
    public ModelAndView updateUser(User user) {
    	userMapper.updateUser(user);
    	ModelAndView modelAndView = new ModelAndView("/profile.html");
    	return modelAndView;
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
        	System.out.println(user);
            System.out.println("验证失败！！！");
            model.addAttribute("error", "Invalid username or password");
            ModelAndView modelAndView = new ModelAndView("/profile.html");
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
