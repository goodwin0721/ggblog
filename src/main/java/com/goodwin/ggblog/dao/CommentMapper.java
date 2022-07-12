package com.goodwin.ggblog.dao;

import com.goodwin.ggblog.entity.Comment;
import com.goodwin.ggblog.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author goodwin
 */
public interface CommentMapper {
    /**
     * 添加一条评论记录
     * @param comment 评论
     * @return 插入条数
     */
    int add(Comment comment);

    /**
     * 查询一页评论数据
     * @param id 文章ID
     * @param lastCommentId 上次查询的最后一条评论ID
     * @param length 评论条数
     * @return 新的一页评论数据
     */
    List<Comment> get(@Param(value = "id") int id, @Param("lastCommentId") int lastCommentId, @Param("length") int length);

    /**
     * 点赞评论
     * @param id 评论的ID
     * @return
     */
    int loveIncrease(int id);

    /**
     * 回复数加1
     * @param id 评论的ID
     * @return
     */
    int replyIncrease(int id);

    /**
     * 获得用户指定动态下的未读评论数量总和
     * @param articleIdList 动态ID列表
     * @param lastReadCommentId 最后一条已读评论ID
     * @return 总数
     */
    int getNewCommentCount(@Param("list") List<Integer> articleIdList,@Param("lastReadCommentId") int lastReadCommentId);

    /**
     * 获得未读的评论消息列表
     * @param articleIdList 动态ID列表
     * @param lastReadCommentId 最后一条已读评论ID
     * @return 未读评论消息列表
     */
    List<Comment> getNewComment(@Param("list")List<Integer> articleIdList,@Param("lastReadCommentId") int lastReadCommentId);
}
