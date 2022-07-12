package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论的数据传输对象
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

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
}
