package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论体
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends CommentBase{

    /**
     * 回复数
     */
    private int reply;

}
