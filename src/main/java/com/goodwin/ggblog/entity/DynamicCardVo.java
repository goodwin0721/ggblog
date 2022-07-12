package com.goodwin.ggblog.entity;


/**
 * @author goodwin
 */
public class DynamicCardVo {
    private int id;
    private String title;
    private int userId;
    private String username;
    private String shortText;
    private String creationTime;
    private int love;
    private int view;
    private int comment;

    public DynamicCardVo(int id, String title, int userId, String username, String shortText, String creationTime, int love, int view, int comment) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.username = username;
        this.shortText = shortText;
        this.creationTime = creationTime;
        this.love = love;
        this.view = view;
        this.comment = comment;
    }

    public DynamicCardVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DynamicCardVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", shortText='" + shortText + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", love=" + love +
                ", view=" + view +
                ", comment=" + comment +
                '}';
    }
}
