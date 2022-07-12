package com.goodwin.ggblog.service;

import com.goodwin.ggblog.entity.Page;
import com.goodwin.ggblog.entity.Reply;
import com.goodwin.ggblog.entity.ReplyDto;
import com.goodwin.ggblog.entity.ReplyVo;

import java.util.List;

/**
 * @author goodwin
 */
public interface ReplyService {
    /**
     * 回复评论
     * @param dto 数据传输对象
     * @return 成功与否
     */
    boolean reply(ReplyDto dto);

    /**
     * 获得指定评论的回复消息页数据
     * @param commentId 评论ID
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @return 一页在指定评论下的回复数据
     */
    Page<Reply> getReply(int commentId, int currentPage,int pageSize);

    /**
     * 点赞一条回复
     * @param replyId 点赞的回复的ID
     * @return 成功与否
     */
    boolean love(int replyId);

    /**
     * 获得用户未读回复消息数量
     * @param userId 用户ID
     * @return 数量
     */
    int getNewReplyCount(int userId);

    /**
     * 获得新回复用户的消息列表
     * @param userId 用户ID
     * @return 新回复列表
     */
    List<ReplyVo> getNewReply(int userId);
}
