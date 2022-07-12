package com.goodwin.ggblog.service.impl;

import com.goodwin.ggblog.dao.ArticleMapper;
import com.goodwin.ggblog.dao.CommentMapper;
import com.goodwin.ggblog.entity.Comment;
import com.goodwin.ggblog.entity.CommentDto;
import com.goodwin.ggblog.entity.Page;
import com.goodwin.ggblog.service.CommentService;
import com.goodwin.ggblog.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author goodwin
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;
    private ArticleMapper articleMapper;

    @Autowired
    @Qualifier("commentMapper")
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
    @Autowired
    @Qualifier("articleMapper")
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 评论
     * @param commentDto 评论的数据传输对象
     * @return 成功与否
     */
    @Override
    public boolean comment(CommentDto commentDto) {
        boolean flag1,flag2;
        Comment comment = new Comment();
        comment.setUserId(commentDto.getUserId());
        comment.setUsername(commentDto.getUsername());
        comment.setArticleId(commentDto.getArticleId());
        comment.setComment(commentDto.getComment());
        flag1 = commentMapper.add(comment) > 0;
        flag2 = articleMapper.commentIncrease(commentDto.getArticleId()) > 0;
        return flag1 && flag2;
    }


    /**
     * 分页获取文章的评论列表
     *
     * @param articleId     文章ID
     * @param lastCommentId 上一次评论查询的最后一条评论ID
     * @return 一页评论数据
     */
    @Override
    public Page<Comment> getComment(int articleId, int lastCommentId) {
        Page<Comment> page = new Page<>();
        List<Comment> list = commentMapper.get(articleId,lastCommentId,page.getSize());
        for (Comment c:list) {
            c.setCreationTime(DateTimeUtils.ridDot(c.getCreationTime()));
        }
        page.setList(list);
        return page;
    }

    /**
     * 点赞
     *
     * @param id 评论ID
     * @return
     */
    @Override
    public boolean love(int id) {
        return commentMapper.loveIncrease(id) > 0;
    }
}
