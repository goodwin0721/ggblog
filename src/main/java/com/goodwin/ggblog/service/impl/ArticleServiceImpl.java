package com.goodwin.ggblog.service.impl;

import com.goodwin.ggblog.dao.ArticleMapper;
import com.goodwin.ggblog.dao.FansMapper;
import com.goodwin.ggblog.dao.UserMapper;
import com.goodwin.ggblog.entity.*;
import com.goodwin.ggblog.service.ArticleService;
import com.goodwin.ggblog.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author goodwin
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;
    private UserMapper userMapper;
    private FansMapper fansMapper;

    @Autowired
    @Qualifier("articleMapper")
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    @Qualifier("userMapper")
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    @Qualifier("fansMapper")
    public void setFansMapper(FansMapper fansMapper) {
        this.fansMapper = fansMapper;
    }

    @Override
    public ArticleVo getArticle(int id) {
        articleMapper.viewIncrease(id);
        Article article = articleMapper.getArticle(id);
        if (article == null){
            return null;
        }
        String username = userMapper.getUsername(article.getUserId());
         ArticleVo articleVo = new ArticleVo(article,username);
         articleVo.setCreationTime(DateTimeUtils.ridDot(articleVo.getCreationTime()));
         articleVo.setUpdateTime(DateTimeUtils.ridDot(articleVo.getUpdateTime()));
        return articleVo;
    }

    /**
     * 获得用户文章数据
     * @param uid  用户ID
     * @param pageIndex 第几页
     * @return 文章集合
     */
    @Override
    public Page<DynamicCardVo> getArticleList(int uid, int pageIndex) {
        Map<String,Object> map = new HashMap<>(3);
        Page<DynamicCardVo> page = new Page<>();
        int defaultSize  = Page.DEFAULT_SIZE;
        int dynamicCount;
        int pageCount;
        if(page.getSize() <= 0){
            page.setSize(defaultSize);
        }
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(uid);
        dynamicCount = articleMapper.countByUserId(userIdList);
        pageCount = dynamicCount % page.getSize() == 0 ? dynamicCount/page.getSize() : dynamicCount/page.getSize() + 1;
        page.setPageCount(pageCount);
        page.setIndex(pageIndex);
        if (pageCount >= pageIndex){
            map.put("userIdList",userIdList);
            map.put("start",(pageIndex - 1) * page.getSize());
            map.put("length",page.getSize());
            List<Article> list = articleMapper.getBriefArticleListByUserId(map);
            page.setList(getDynamicVoList(list));
        }
        return page;
    }

    /**
     * 获得用户的关注用户动态信息
     * @param uid 用户ID
     * @param pageIndex 第几页
     * @return 结果集
     */
    @Override
    public Page<DynamicCardVo> getFollowArticleList(int uid, int pageIndex) {
        //条件map
        Map<String,Object> map = new HashMap<>(3);
        Page<DynamicCardVo> page = new Page<>();
        //动态总数
        int dynamicCount;
        //页总数
        int pageCount;
        if(page.getSize() <= 0){
            page.setSize(Page.DEFAULT_SIZE);
        }
        //用户关注的用户id
        List<Integer> followList = fansMapper.getFollowIdList(uid);
        //包括用户本身
        List<Integer> userIdList = new ArrayList<>(followList);
        userIdList.add(uid);
        //获得动态总数
        dynamicCount = articleMapper.countByUserId(userIdList);
        //计算页总数
        pageCount = dynamicCount % page.getSize() == 0 ? dynamicCount/page.getSize() : dynamicCount/page.getSize() + 1;
        page.setPageCount(pageCount);
        page.setIndex(pageIndex);
        if(pageCount >= pageIndex){
            map.put("userIdList",userIdList);
            map.put("start",(pageIndex - 1) * page.getSize());
            map.put("length",page.getSize());
            List<Article> list = articleMapper.getBriefArticleListByUserId(map);
            page.setList(getDynamicVoList(list));
        }
        return page;
    }

    /**
     * 根据专栏类型获得文章集合
     *
     * @param type 专栏类型
     * @return 结果集
     */
    @Override
    public Page<DynamicCardVo> getArticleListByType(String type,int pageIndex) {
        Map<String,Object> map = new HashMap<>(3);
        List<Article> articleList;
        Page<DynamicCardVo> page = new Page<>();
        int dynamicCount;
        int pageCount;
        String t = type;
        if(page.getSize() <= 0 ){
            page.setSize(Page.DEFAULT_SIZE);
        }
        String all = "all";
        String all2 = "全部";
        if(type == null || type.equals(all) || type.equals(all2)){
            t = "";
        }
        dynamicCount = articleMapper.countByType(t);
        pageCount = dynamicCount % page.getSize() == 0 ? dynamicCount / page.getSize() : dynamicCount / page.getSize() + 1;
        page.setPageCount(pageCount);
        page.setIndex(pageIndex);
        if (pageCount >= pageIndex){
            map.put("type",t);
            map.put("start",(pageIndex - 1) * page.getSize());
            map.put("length",page.getSize());
            articleList = articleMapper.getBriefArticleListByType(map);
            page.setList(getDynamicVoList(articleList));
        }
        return page;
    }

    /**
     * 根据关键字模糊查询获得标题
     *
     * @param keyword 关键字
     * @return 包含关键字的标题集合
     */
    @Override
    public List<String> getSuggestTitles(String keyword) {
        String[] key = keyword.split("\\s+");
        List<String> list = Arrays.asList(key);
        return articleMapper.titleFuzzySearchByKeyword(list);
    }

    /**
     * 根据关键字模糊查询
     *
     * @param keyword   关键字
     * @param pageIndex 页数
     * @return 结果集
     */
    @Override
    public Page<DynamicCardVo> getArticleListByKeyword(String keyword, int pageIndex) {
        Page<DynamicCardVo> page = new Page<>();
        if ("".equals(keyword.trim()) || pageIndex < 1){
            return page;
        }
        List<String> keywords = Arrays.asList(keyword.split("\\s+"));
        Map<String,Object> map = new HashMap<>(3);
        List<Article> articleList;
        int dynamicCount;
        int pageCount;
        if(page.getSize() <= 0 ){
            page.setSize(Page.DEFAULT_SIZE);
        }

        dynamicCount = articleMapper.countByTitle(keywords);
        pageCount = dynamicCount % page.getSize() == 0 ? dynamicCount / page.getSize() : dynamicCount / page.getSize() + 1;
        page.setPageCount(pageCount);
        page.setIndex(pageIndex);
        if (pageCount >= pageIndex){
            map.put("keywords",keywords);
            map.put("start",(pageIndex - 1) * page.getSize());
            map.put("length",page.getSize());
            articleList = articleMapper.fuzzySearchByKeyword(map);
            page.setList(getDynamicVoList(articleList));
        }
        return page;
    }

    /**
     * 写文章
     * @param articleDto 传输对象
     * @return 操作结果
     */
    @Override
    public boolean writeArticle(ArticleDto articleDto) throws Exception {
        Article article = new Article();
        article.setUserId(articleDto.getUserId());
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setType(articleDto.getType());
        String url = "";
        for (int i = 0; i < articleDto.getPictureUrl().size(); i++) {
            url = url + articleDto.getPictureUrl().get(i);
            if(i != articleDto.getPictureUrl().size() - 1){
                url += "#";
            }
        }
        article.setPictureUrlGroup(url);
        int res1 = articleMapper.add(article);
        int res2 = userMapper.dynamicIncrease(article.getUserId());
        if(res1 > 0 && res2 >0 ) {
            return true;
        }else{
            throw new Exception();
        }
    }

    @Override
    public boolean deleteArticle(int articleId) {
        Article article = articleMapper.getArticle(articleId);
        int userId = article.getUserId();
        int res1 = articleMapper.delete(articleId);
        int res2 = userMapper.dynamicDecrease(userId);
        return res1 > 0 && res2 > 0;
    }

    @Override
    public boolean updateArticle(Article article) {
        return articleMapper.update(article) > 0;
    }

    /**
     * 点赞动态
     *
     * @param id 动态ID
     * @return 成功标志
     */
    @Override
    public boolean love(int id) {
        return articleMapper.loveIncrease(id) > 0;
    }

    private List<DynamicCardVo> getDynamicVoList(List<Article> list){
        if (list == null){
            return null;
        }
        List<DynamicCardVo> voList = new ArrayList<>();
        DynamicCardVo cardVo;
        for (Article item : list) {
            cardVo = new DynamicCardVo();
            cardVo.setId(item.getId());
            cardVo.setTitle(item.getTitle());
            cardVo.setUserId(item.getUserId());
            cardVo.setUsername(userMapper.getUsername(item.getUserId()));
            cardVo.setCreationTime(DateTimeUtils.ridDot(item.getCreationTime()));
            cardVo.setShortText(item.getText());
            cardVo.setComment(item.getComment());
            cardVo.setLove(item.getLove());
            cardVo.setView(item.getView());
            voList.add(cardVo);
        }
        return voList;
    }
}
