package com.goodwin.ggblog.service.impl;

import com.goodwin.ggblog.dao.FansMapper;
import com.goodwin.ggblog.dao.UserMapper;
import com.goodwin.ggblog.entity.FansVo;
import com.goodwin.ggblog.entity.Follow;
import com.goodwin.ggblog.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author goodwin
 */
@Service("fansService")
public class FansServiceImpl implements FansService {
    @Autowired
    @Qualifier("fansMapper")
    FansMapper fansMapper;

    @Autowired
    @Qualifier("userMapper")
    UserMapper userMapper;


    @Override
    public boolean follow(Follow follow) {
        return fansMapper.add(follow) > 0 && userMapper.followIncrease(follow.getUserId()) >0 && userMapper.fansIncrease(follow.getFollowId()) > 0;
    }

    @Override
    public boolean unfollow(Follow follow) {
        return fansMapper.remove(follow) > 0 && userMapper.followDecrease(follow.getUserId()) >0 && userMapper.fansDecrease(follow.getFollowId()) > 0;
    }

    @Override
    public List<FansVo> getFansList(int id) {
        List<Integer> fansIdList = fansMapper.getFansIdList(id);
        List<FansVo> fansList = userMapper.getUserList4Fans(fansIdList);
        return fansList;
    }

    @Override
    public List<FansVo> getFollowList(int id) {
        List<Integer> followIdList = fansMapper.getFollowIdList(id);
        List<FansVo> followList = userMapper.getUserList4Fans(followIdList);
        return followList;
    }

    @Override
    public boolean isFollow(Follow follow){
        int res = fansMapper.exist(follow);
        return res > 0;
    }
}
