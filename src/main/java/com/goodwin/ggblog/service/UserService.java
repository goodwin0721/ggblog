package com.goodwin.ggblog.service;

import com.goodwin.ggblog.entity.User;

/**
 * @author goodwin
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户名是都存在
     * @param username
     * @return
     */
    boolean isExistUsername(String username);

    /**
     * 根据用户ID获得用户信息
     * @param userId
     * @return
     */
    User getUser(int userId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean update(User user);
}
