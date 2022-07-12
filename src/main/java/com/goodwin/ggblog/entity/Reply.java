package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回复体
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends CommentBase{

    /**
     * 回复所在的评论ID
     */
    private int commentId;

    /**
     * 接收回复的用户ID
     */
    private int replyId;

    /**
     * 接收回复的用户名
     */
    private String replyName;
}
