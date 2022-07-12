package com.goodwin.ggblog.service;

import com.goodwin.ggblog.entity.FansVo;
import com.goodwin.ggblog.entity.Follow;
import com.goodwin.ggblog.entity.UserVo;

import java.util.List;

/**
 * @author goodwin
 */
public interface FansService {
    /**
     * 关注
     * @param follow
     * @return
     */
    boolean follow(Follow follow);

    /**
     * 取关
     * @param follow
     * @return
     */
    boolean unfollow(Follow follow);

    /**
     * 获得指定ID用户的粉丝列表
     * @param id
     * @return
     */
    List<FansVo> getFansList(int id);

    /**
     * 获得指定ID用户的关注列表
     * @param id
     * @return
     */
    List<FansVo> getFollowList(int id);

    /**
     * 判断是否关注
     * @param follow
     * @return
     */
    boolean isFollow(Follow follow);
}
