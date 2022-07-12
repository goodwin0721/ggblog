package com.goodwin.ggblog.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author goodwin
 */
public interface MessageMapper {

    /**
     * 获得最后一次查看的回复ID
     * @param id 用户ID
     * @return 回复ID
     */
    int getLastReadReplyId(int id);

    /**
     * 获得最后一次查看的评论ID
     * @param id 用户ID
     * @return 评论ID
     */
    int getLastReadCommentId(int id);

    /**
     * 增加一条新纪录
     * @param userId
     * @return
     */
    int add(int userId);

    /**
     * 设置新的已读评论ID
     * @param userId
     * @param id
     * @return
     */
    int setLastReadCommentId(@Param("userId") int userId,@Param("id") int id);

    /**
     * 设置新的已读回复ID
     * @param userId
     * @param id
     * @return
     */
    int setLastReadReplyId(@Param("userId")int userId,@Param("id") int id);
}
