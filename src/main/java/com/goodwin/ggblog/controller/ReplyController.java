package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.entity.*;
import com.goodwin.ggblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理回复的controller
 * @author goodwin
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    @Qualifier("replyService")
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/reply")
    public boolean comment(@RequestBody ReplyDto dto){
        return replyService.reply(dto);
    }

    @GetMapping(value = "/get/{commentId}/{currentPage}/{pageSize}")
    public Page<Reply> getReply(@PathVariable int commentId, @PathVariable int currentPage, @PathVariable int pageSize){
        return replyService.getReply(commentId,currentPage,pageSize);
    }

    @PostMapping("/love")
    public boolean love(@RequestParam int replyId){
        return replyService.love(replyId);
    }

    /**
     * 获得回复消息信息列表
     * @param userId
     * @return
     */
    @GetMapping("/message/list/{userId}")
    public List<ReplyVo> getReplyList(@PathVariable int userId){
        return null;
    }

    /**
     * 获得最新回复消息信息列表
     * @param userId
     * @return
     */
    @GetMapping("/message/new/{userId}")
    public List<ReplyVo> getNewReply(@PathVariable int userId){
        return replyService.getNewReply(userId);
    }

    @GetMapping("/message/new/count/{userId}")
    public int getNewReplyCount(@PathVariable int userId){
        return replyService.getNewReplyCount(userId);
    }
}
