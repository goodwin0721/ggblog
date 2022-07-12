package com.goodwin.ggblog.dao;

import com.goodwin.ggblog.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @author goodwin
 */
public interface ArticleMapper {
    /**
     * 获得文章记录信息
     * @param id 文章ID
     * @return 文章
     */
    Article getArticle(int id);

    /**
     * 按map里的条件，获得文章集合，其中文章记录为简略记录
     * @param map 包括用户ID，记录的起始位置，记录条数
     * @return
     */
    List<Article> getBriefArticleListByUserId(Map<String,Object> map);

    /**
     * 根据专栏类型查询
     * @param map
     * @return
     */
    List<Article> getBriefArticleListByType(Map<String, Object> map);

    /**
     * 增加
     * @param article
     * @return
     */
    int add(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 浏览数+1
     * @param id
     */
    void viewIncrease(int id);

    /**
     * 计算动态数量
     * @param userIdList
     * @return
     */
    int countByUserId(List<Integer> userIdList);

    /**
     * 根据专栏类型统计
     * @param type
     * @return
     */
    int countByType(String type);

    /**
     * 根据关键字模糊查询包含关键字的标题
     * @param keyword 关键字
     * @return 包含关键字的标题集合
     */
    List<String> titleFuzzySearchByKeyword(List<String> keyword);

    /**
     * 根据关键字统计，统计标题包含关键字的记录条数
     * @param keywords 关键字集合
     * @return 条数
     */
    int countByTitle(List<String> keywords);

    /**
     * 根据关键字模糊查询，查询标题包含关键字的记录
     * @param map 条件
     * @return 结果
     */
    List<Article> fuzzySearchByKeyword(Map<String, Object> map);

    /**
     * 评论数+1
     * @param articleId 文章ID
     * @return 修改条数
     */
    int commentIncrease(int articleId);

    /**
     * 点赞数+1
     * @param id 文章ID
     * @return 成功标志
     */
    int loveIncrease(int id);

    /**
     * 获得用户动态ID列表
     * @param userId
     * @return
     */
    List<Integer> getArticleIdList(int userId);
}
