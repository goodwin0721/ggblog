package com.goodwin.ggblog.service.impl;

import com.goodwin.ggblog.dao.*;
import com.goodwin.ggblog.entity.*;
import com.goodwin.ggblog.service.ReplyService;
import com.goodwin.ggblog.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodwin
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    private ReplyMapper replyMapper;
    private ArticleMapper articleMapper;
    private UserMapper userMapper;
    private CommentMapper commentMapper;
    private MessageMapper messageMapper;

    @Autowired
    @Qualifier("replyMapper")
    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

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
    @Qualifier("commentMapper")
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    @Qualifier("messageMapper")
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    /**
     * 回复评论
     *
     * @param dto 数据传输对象
     * @return 成功与否
     */
    @Override
    public boolean reply(ReplyDto dto) {
        boolean flag1,flag2,flag3;
        Reply reply = new Reply();
        reply.setUserId(dto.getUserId());
        reply.setUsername(dto.getUsername());
        reply.setArticleId(dto.getArticleId());
        reply.setCommentId(dto.getCommentId());
        reply.setReplyId(dto.getReplyId());
        reply.setComment(dto.getComment());
        reply.setReplyName(userMapper.getUsername(dto.getReplyId()));
        flag1 = replyMapper.add(reply) > 0;
        flag2 = commentMapper.replyIncrease(dto.getCommentId()) > 0;
        flag3 = articleMapper.commentIncrease(dto.getArticleId()) > 0;
        return flag1 && flag2 && flag3;
    }

    @Override
    public Page<Reply> getReply(int commentId, int currentPage,int pageSize) {
        Page<Reply> page = new Page<>();
        if (pageSize > 0){
            page.setSize(pageSize);
        }
        page.setIndex(currentPage);
        int offset = (currentPage - 1 ) * page.getSize();
        int length = page.getSize();
        List<Reply> list = replyMapper.get(commentId,offset,length);
        for (Reply reply:list) {
            reply.setCreationTime(DateTimeUtils.ridDot(reply.getCreationTime()));
        }
        page.setList(list);
        return page;
    }

    /**
     * 点赞一条回复
     *
     * @param replyId 点赞的回复的ID
     * @return 成功与否
     */
    @Override
    public boolean love(int replyId) {
        return replyMapper.loveIncrease(replyId) > 0;
    }

    /**
     * 获得用户未读回复消息数量
     *
     * @param userId 用户ID
     * @return 数量
     */
    @Override
    public int getNewReplyCount(int userId) {
        int lastReadReplyId = messageMapper.getLastReadReplyId(userId);

        lastReadReplyId = Math.max(lastReadReplyId, 0);
        int lastReadCommentId = messageMapper.getLastReadCommentId(userId);
        lastReadCommentId = Math.max(lastReadCommentId,0);
        List<Integer> articleIdList = articleMapper.getArticleIdList(userId);
        if(articleIdList.size() == 0){
            return 0;
        }
        int newCommentCount = commentMapper.getNewCommentCount(articleIdList,lastReadCommentId);
        int newReplyCount = replyMapper.getNewReplyCount(userId,lastReadReplyId);
        return newCommentCount + newReplyCount;
    }

    /**
     * 获得新回复用户的消息列表
     *
     * @param userId 用户ID
     * @return 新回复列表
     */
    @Override
    public List<ReplyVo> getNewReply(int userId) {
        int lastReadReplyId = messageMapper.getLastReadReplyId(userId);
        int lastReadCommentId = messageMapper.getLastReadCommentId(userId);
        List<Integer> articleIdList = articleMapper.getArticleIdList(userId);
        List<Comment> newCommentList = commentMapper.getNewComment(articleIdList,lastReadCommentId);
        List<Reply> newReplyList = replyMapper.getNewReply(userId,lastReadReplyId);
        List<ReplyVo> replyVos = new ArrayList<>();
        if(newCommentList.size() > 0){
            messageMapper.setLastReadCommentId(userId,newCommentList.get(0).getId());
        }
        if(newReplyList.size() > 0){
            messageMapper.setLastReadReplyId(userId,newReplyList.get(0).getId());
        }
        //将评论列表和回复列表组合
        ArrayList<Comment> comments = new ArrayList<>(newCommentList);
        ArrayList<Reply> replies = new ArrayList<>(newReplyList);
        int i = 0;
        int j = 0;
        while (i < comments.size() || j < replies.size()){
            if(i < comments.size() && j == replies.size()){
                replyVos.add(getReplyVo(comments.get(i)));
                i++;
            }else if( j < replies.size() && i == comments.size()){
                replyVos.add(getReplyVo(replies.get(j)));
                j++;
            }else if(DateTimeUtils.compareTo(DateTimeUtils.ridDot(comments.get(i).getCreationTime()),DateTimeUtils.ridDot(replies.get(j).getCreationTime())) > 0){
                replyVos.add(getReplyVo(comments.get(i)));
                i++;
            }else{
                replyVos.add(getReplyVo(replies.get(j)));
                j++;
            }
        }

        return replyVos;
    }


    private ReplyVo getReplyVo(Comment comment){
        ReplyVo vo = new ReplyVo();
        vo.setType("评论");
        vo.setId(comment.getId());
        vo.setUserId(comment.getUserId());
        vo.setUsername(comment.getUsername());
        vo.setArticleId(comment.getArticleId());
        vo.setComment(comment.getComment());
        vo.setCreationTime(DateTimeUtils.ridDot(comment.getCreationTime()));
        return vo;
    }

    private ReplyVo getReplyVo(Reply reply){
        ReplyVo vo = new ReplyVo();
        vo.setType("回复");
        vo.setId(reply.getId());
        vo.setUserId(reply.getUserId());
        vo.setUsername(reply.getUsername());
        vo.setArticleId(reply.getArticleId());
        vo.setComment(reply.getComment());
        vo.setCreationTime(DateTimeUtils.ridDot(reply.getCreationTime()));
        vo.setCommentId(reply.getCommentId());
        return vo;
    }
}
