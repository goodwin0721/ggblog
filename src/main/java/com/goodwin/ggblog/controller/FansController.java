package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.entity.FansVo;
import com.goodwin.ggblog.entity.Follow;
import com.goodwin.ggblog.entity.UserVo;
import com.goodwin.ggblog.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 粉丝系统controller
 * @author goodwin
 */
@RestController
@RequestMapping("/fans")
@CrossOrigin
public class FansController {

    @Autowired
    @Qualifier("fansService")
    FansService fansService;

    @RequestMapping(value = "/follow",method = RequestMethod.POST)
    public boolean follow(@RequestBody Follow follow){
        return fansService.follow(follow);
    }

    @RequestMapping(value = "/unfollow",method = RequestMethod.POST)
    public boolean unfollow(@RequestBody Follow follow){
        return fansService.unfollow(follow);
    }

    @RequestMapping("/fansList/{id}")
    public List<FansVo> getFansList(@PathVariable int id){
        return fansService.getFansList(id);
    }

    @RequestMapping("/followList/{id}")
    public List<FansVo> getFollowList(@PathVariable int id){
        return fansService.getFollowList(id);
    }

    @RequestMapping(value = "/isFollow",method = RequestMethod.POST)
    public boolean isFollow(@RequestBody Follow follow){
        return fansService.isFollow(follow);
    }
}
