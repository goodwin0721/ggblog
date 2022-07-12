package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回复的数据传输对象
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    /**
     * 评论用户的ID
     */
    private int userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 回复所在的动态ID
     */
    private int articleId;

    /**
     * 回复所在的评论ID
     */
    private int commentId;

    /**
     * 接收回复的用户ID
     */
    private int replyId;

    /**
     * 回复的内容
     */
    private String comment;
}
