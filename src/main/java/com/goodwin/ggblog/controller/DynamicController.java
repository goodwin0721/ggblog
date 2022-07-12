package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.entity.*;
import com.goodwin.ggblog.service.ArticleService;
import com.goodwin.ggblog.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author goodwin
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicController {

    private ArticleService articleService;

    @Autowired
    @Qualifier("articleService")
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/article/{id}")
    public ArticleVo getArticle(@PathVariable int id){
        return articleService.getArticle(id);
    }

    /**
     * 获得指定ID用户指定页的文章数据列表
     * @param uid
     * @return
     */
    @RequestMapping("/article/list/{uid}/{page}")
    public Page<DynamicCardVo> getArticleList(@PathVariable int uid, @PathVariable int page){
        return articleService.getArticleList(uid,page);
    }

    /**
     * 获得关注用户的动态
     * @param id 用户ID
     * @param page 页数
     * @return 包涵动态卡片的页
     */
    @GetMapping("/follow/{id}/{page}")
    public Page<DynamicCardVo> getFollowList(@PathVariable int id, @PathVariable int page){
        return articleService.getFollowArticleList(id,page);
    }

    /**
     * 根据关键字获得包含关键字的标题集合
     * @param keyword 关键字
     * @return 结果
     */
    @GetMapping("/search/suggest/title/{keyword}")
    public List<String> getSuggestTitles(@PathVariable String keyword){
         return articleService.getSuggestTitles(keyword);
    }

    /**
     * 根据关键字查询
     * @param keyword 关键字
     * @return 查询结果
     */
    @RequestMapping("/article/keyword/{keyword}/{pageIndex}")
    public Page<DynamicCardVo> getArticleListByKeyword(@PathVariable String keyword, @PathVariable int pageIndex){
        return articleService.getArticleListByKeyword(keyword,pageIndex);
    }

    /**
     * 根据文章类型查询
     * @param type
     * @return
     */
    @RequestMapping("/article/type/{type}/{pageIndex}")
    public Page<DynamicCardVo> getArticleListByType(@PathVariable String type,@PathVariable int pageIndex){
        return articleService.getArticleListByType(type,pageIndex);
    }

    @RequestMapping(value = "/article/write",method = RequestMethod.POST)
    public boolean writeArticle(@RequestBody ArticleDto articleDto) throws Exception {
        return articleService.writeArticle(articleDto);
    }

    @RequestMapping(value = "/article/delete",method = RequestMethod.POST)
    public boolean deleteArticle(@RequestParam("articleId") int articleId) {
        return articleService.deleteArticle(articleId);
    }

    @RequestMapping(value = "/article/update",method = RequestMethod.POST)
    public boolean updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    /**
     * 点赞
     * @return
     */
    @RequestMapping(value = "/article/love",method = RequestMethod.POST)
    public boolean love(@RequestParam int id){
        return articleService.love(id);
    }

    /**
     * 取消点赞
     * @return
     */
    @RequestMapping(value = "/article/love/cancel",method = RequestMethod.POST)
    public boolean cancelLove(){
        return false;
    }

    /**
     * 评论
     * @return
     */
    @RequestMapping(value = "/article/comment",method = RequestMethod.POST)
    public boolean comment(){
        return false;
    }

    /**
     * 回复
     * @return
     */
    @RequestMapping(value = "/article/reply",method = RequestMethod.POST)
    public boolean reply(){
        return false;
    }

    @RequestMapping(value = "/upload/picture",method = RequestMethod.POST)
    public String uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if(file == null || file.isEmpty()){
            return "null";
        }
        System.out.println(file);
        String res = UploadUtils.uploadImage(file,request);
        System.out.println(res);
        return res;
    }
}
