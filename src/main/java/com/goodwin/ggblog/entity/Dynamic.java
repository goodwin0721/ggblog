package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin
 *
 */
@Data
@NoArgsConstructor
public class Dynamic {
    /**
     * 动态的ID
     */
    private int id;

    /**
     * 作者的ID
     */
    private int userId;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 点赞数
     */
    private int love;

    /**
     * 评论数
     */
    private int comment;

    /**
     * 收藏数
     */
    private int star;

    /**
     * 浏览数
     */
    private int view;

    public Dynamic(int id, int userId, String creationTime, String updateTime, int love, int comment, int star, int view) {
        this.id = id;
        this.userId = userId;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.love = love;
        this.comment = comment;
        this.star = star;
        this.view = view;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", userId=" + userId +
                ", creationTime='" + creationTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", love=" + love +
                ", comment=" + comment +
                ", star=" + star +
                ", view=" + view +
                '}';
    }
}
