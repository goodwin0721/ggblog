package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章的数据传输对象
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private int userId;
    private String title;
    private String text;
    private List<String> pictureUrl;
    private String type;
}
