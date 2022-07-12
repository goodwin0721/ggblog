package com.goodwin.ggblog.service;

import com.goodwin.ggblog.entity.Comment;
import com.goodwin.ggblog.entity.CommentDto;
import com.goodwin.ggblog.entity.Page;

/**
 * @author goodwin
 */
public interface CommentService {
    /**
     * 评论
     * @param commentDto 评论的数据传输对象
     * @return 成功与否
     */
    boolean comment(CommentDto commentDto);

    /**
     * 分页获取文章的评论列表
     * @param articleId 文章ID
     * @param lastCommentId 上一次评论查询的最后一条评论ID
     * @return 一页评论数据
     */
    Page<Comment> getComment(int articleId, int lastCommentId);

    /**
     * 点赞
     * @param id 评论ID
     * @return
     */
    boolean love(int id);
}
