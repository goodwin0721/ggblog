package com.goodwin.ggblog.service.impl;

import com.goodwin.ggblog.dao.MessageMapper;
import com.goodwin.ggblog.dao.UserMapper;
import com.goodwin.ggblog.entity.User;
import com.goodwin.ggblog.entity.UserVo;
import com.goodwin.ggblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author goodwin
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapper mapper;
    private MessageMapper messageMapper;

    @Autowired
    @Qualifier("userMapper")
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    @Qualifier("messageMapper")
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public User login(User user) {
        User u = mapper.getUser(user);
        if(u != null) {
            u.setPassword(null);
        }
        return u;
    }

    @Override
    public boolean register(User user) {
        if(isExistUsername(user.getUsername())) {
            return false;
        }
        if(mapper.addUser(user) <= 0){
            return false;
        }
        User u = mapper.getUser(user);
        int userId = u.getId();
        //新增信息读取记录
        messageMapper.add(userId);
        return true;
    }

    @Override
    public boolean isExistUsername(String username) {
        User user = mapper.getUserByUsername(username);
        if(user != null){
            if(user.getUsername() != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(int userId) {
        return mapper.getUserExceptPsw(userId);
    }


    @Override
    public boolean update(User user) {
        return mapper.update(user) > 0 ;
    }
}
