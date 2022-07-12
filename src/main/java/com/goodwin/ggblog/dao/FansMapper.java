package com.goodwin.ggblog.dao;

import com.goodwin.ggblog.entity.Follow;

import java.util.List;

/**
 * @author goodwin
 */
public interface FansMapper {
    /**
     * 新增关注信息
     * @param follow
     * @return
     */
    int add(Follow follow);

    /**
     * 移除关注信息
     * @param follow
     * @return
     */
    int remove(Follow follow);

    /**
     * 获得粉丝ID列表
     * @param id
     * @return
     */
    List<Integer> getFansIdList(int id);

    /**
     * 获得关注列表
     * @param id
     * @return
     */
    List<Integer> getFollowIdList(int id);

    /**
     * 是否存在记录
     * @param follow
     * @return
     */
    int exist(Follow follow);
}
