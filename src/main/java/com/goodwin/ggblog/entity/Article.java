package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章类型动态
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Dynamic{

    /**
     * 文章标题
     */
    private String title;

    /**
     * 正文
     */
    private String text;

    /**
     * 图片地址集合
     */
    private String pictureUrlGroup;

    /**
     * 文章专栏类型
     */
    private String type;

    @Override
    public String toString() {
        String res = super.toString();
        return "Article{" +
                res.substring(res.indexOf("{") + 1,res.indexOf("}")) +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", pictureUrlGroup='" + pictureUrlGroup + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
