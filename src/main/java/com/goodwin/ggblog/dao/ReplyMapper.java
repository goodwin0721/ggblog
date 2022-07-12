package com.goodwin.ggblog.dao;

import com.goodwin.ggblog.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author goodwin
 */
public interface ReplyMapper {
    /**
     * 新增一条回复
     * @param reply 回复体
     * @return 成功标志
     */
    int add(Reply reply);

    /**
     * 获取数条回复的信息
     * @param commentId 回复所在的评论ID
     * @param offset 跳过的符合条件的记录数
     * @param length 获取记录数
     * @return 集合
     */
    List<Reply> get(@Param("commentId") int commentId,@Param("offset") int offset,@Param("length") int length);

    /**
     * 处理点赞回复
     * @param id 回复的ID
     * @return 成功标志
     */
    int loveIncrease(int id);

    /**
     * 用户未读回复消息总数
     * @param userId 用户ID
     * @param lastReadReplyId 最后已读回复ID
     * @return 总数
     */
    int getNewReplyCount(@Param("userId") int userId,@Param("lastReadReplyId") int lastReadReplyId);

    /**
     * 用户未读回复消息列表
     * @param userId 用户ID
     * @param lastReadReplyId 最后已读回复ID
     * @return 未读回复消息列表
     */
    List<Reply> getNewReply(@Param("userId")int userId,@Param("lastReadReplyId") int lastReadReplyId);
}
