package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.entity.User;
import com.goodwin.ggblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author goodwin
 * @RestController 加了RestController所有方法将返回json字符串，不用每个方法添加@ResponseBody
 * @CrossOrigin 跨域
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public boolean register(@RequestBody User user){
        return userService.register(user);
    }

    @RequestMapping("/register/isExistUsername/{username}")
    @ResponseBody
    public boolean registerValidateUsername(@PathVariable String username){
        return userService.isExistUsername(username);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestBody User user){
        return userService.login(user);
    }

    @RequestMapping("/uid/{userId}")
    @ResponseBody
    public User getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public boolean update(@RequestBody User user){
        return userService.update(user);
    }
}
