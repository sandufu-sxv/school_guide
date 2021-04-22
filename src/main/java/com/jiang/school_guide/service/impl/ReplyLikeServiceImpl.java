package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.dao.ReplyMapper;
import com.jiang.school_guide.entity.Reply;
import com.jiang.school_guide.entity.ReplyLike;
import com.jiang.school_guide.dao.ReplyLikeMapper;
import com.jiang.school_guide.service.IReplyLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-09
 */
@Service
public class ReplyLikeServiceImpl extends ServiceImpl<ReplyLikeMapper, ReplyLike> implements IReplyLikeService {

    @Autowired
    private ReplyLikeMapper replyLikeMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public ServerResponse addReplyLike(ReplyLike replyLike) {
        int id =  TokenUntil.getIdByToken();
        replyLike.setCreateTime(LocalDateTime.now());
        replyLike.setUpdateTime(LocalDateTime.now());
        replyLike.setUserId(id);
        Reply reply = replyMapper.selectById(replyLike.getReplyId());
        reply.setLikes(reply.getLikes() + 1);
        replyMapper.updateById(reply);
        if(replyLikeMapper.insert(replyLike) == 1){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public boolean getReplyLike(Integer ReplyId) {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<ReplyLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_id", ReplyId)
                .eq("user_id",id);
        if(replyLikeMapper.selectCount(queryWrapper) == 0){
            return false;
        }
        return true;
    }


    @Override
    public ServerResponse deleteReplyLike(Integer ReplyId) {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<ReplyLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_id", ReplyId)
                .eq("user_id",id);
        Reply reply = replyMapper.selectById(ReplyId);
        reply.setLikes(reply.getLikes() - 1);
        replyMapper.updateById(reply);
        if(replyLikeMapper.delete(queryWrapper) == 1){
            return ServerResponse.createBySuccessMessage("取消点赞");
        }
        return ServerResponse.createBySuccessMessage("系统错误，请稍后重试!");
    }

    @Override
    public ServerResponse deleteReplyLikeByReply(Integer replyId) {
        QueryWrapper<ReplyLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_id", replyId);
        replyLikeMapper.delete(queryWrapper);
        return ServerResponse.createBySuccessMessage("删除成功");
    }
}
