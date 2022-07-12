package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo extends Dynamic{

    private String author;
    private String title;
    private String text;
    private List<String> pictureList;
    private String type;

    public ArticleVo(Article article,String username){
        super(article.getId(), article.getUserId(), article.getCreationTime(),
                article.getUpdateTime(), article.getLove(), article.getComment(),
                article.getStar(), article.getView());
        this.author = username;
        this.title = article.getTitle();
        this.text = article.getText();
        this.type = article.getType();
        this.pictureList = new ArrayList<>();
        String path = article.getPictureUrlGroup();
        if (path != null && !"".equals(path.trim())){
            String[] temp = path.split("#");
            pictureList.addAll(Arrays.asList(temp));
        }
    }

    @Override
    public String toString() {
        String res = super.toString();
        return "ArticleVo{" +
                res.substring(res.indexOf("{") + 1,res.indexOf("}")) +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", pictureList=" + pictureList +
                ", type='" + type + '\'' +
                '}';
    }
}
