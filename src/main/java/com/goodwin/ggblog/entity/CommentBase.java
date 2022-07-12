package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentBase {
    /**
     * 评论的ID
     */
    private int id;

    /**
     * 评论用户的ID
     */
    private int userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 评论的动态ID
     */
    private int articleId;

    /**
     * 评论的内容
     */
    private String comment;

    /**
     * 点赞数
     */
    private int love;

    /**
     * 评论时间
     */
    private String creationTime;
}
