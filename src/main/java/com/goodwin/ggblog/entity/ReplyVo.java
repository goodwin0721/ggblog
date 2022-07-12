package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVo extends CommentBase{
    /**
     * 评论还是回复
     */
    private String type;

    /**
     * 动态标题
     */
    private String title;

    /**
     * 如果类型是回复，回复所在的评论ID
     */
    private int commentId;
}
