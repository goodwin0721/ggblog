package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.entity.Comment;
import com.goodwin.ggblog.entity.CommentDto;
import com.goodwin.ggblog.entity.Page;
import com.goodwin.ggblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * 处理评论的controller
 * @author goodwin
 */
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    @Qualifier("commentService")
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public boolean comment(@RequestBody CommentDto commentDto){
        return commentService.comment(commentDto);
    }

    @GetMapping(value = "/get/{articleId}/{lastCommentId}")
    public Page<Comment> getComment(@PathVariable int articleId, @PathVariable int lastCommentId){
        return commentService.getComment(articleId,lastCommentId);
    }

    @PostMapping("/love")
    public boolean love(@RequestParam int commentId){
        return commentService.love(commentId);
    }
}
