package com.goodwin.ggblog.service;

import com.goodwin.ggblog.entity.*;

import java.util.List;

/**
 * @author goodwin
 */
public interface ArticleService {
    /**
     * 根据文章ID获得文章信息
     * @param id
     * @return
     */
    ArticleVo getArticle(int id);

    /**
     * 获得用户文章数据
     * @param uid 用户ID
     * @param pageIndex 第几页
     * @return 文章集合
     */
    Page<DynamicCardVo> getArticleList(int uid, int pageIndex);

    /**
     * 查询用户的关注用户动态列表
     * @param uid 用户ID
     * @param pageIndex 第几页
     * @return 结果集
     */
    Page<DynamicCardVo> getFollowArticleList(int uid, int pageIndex);

    /**
     * 根据专栏类型获得文章集合
     * @param type 专栏类型
     * @param pageIndex 第几页
     * @return 结果集
     */
    Page<DynamicCardVo> getArticleListByType(String type,int pageIndex);

    /**
     * 根据关键字模糊查询获得标题
     * @param keyword 关键字
     * @return 包含关键字的标题集合
     */
    List<String> getSuggestTitles(String keyword);

    /**
     * 根据关键字模糊查询
     * @param keyword 关键字
     * @param pageIndex 页数
     * @return 结果集
     */
    Page<DynamicCardVo> getArticleListByKeyword(String keyword, int pageIndex);

    /**
     * 写文章
     * @param articleDto 文章传输对象
     * @return 操作结果
     * @throws Exception 异常
     */
    boolean writeArticle(ArticleDto articleDto) throws Exception;

    /**
     * 删除文章动态
     * @param articleId 文章ID
     * @return 操作结果
     */
    boolean deleteArticle(int articleId);

    /**
     * 更新动态
     * @param article 动态
     * @return 操作结果
     */
    boolean updateArticle(Article article);

    /**
     * 点赞动态
     * @param id 动态ID
     * @return 成功标志
     */
    boolean love(int id);
}
