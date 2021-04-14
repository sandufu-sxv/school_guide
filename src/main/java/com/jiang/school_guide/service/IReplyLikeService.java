package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.ReplyLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-09
 */
public interface IReplyLikeService extends IService<ReplyLike> {
    //添加
    ServerResponse addReplyLike(ReplyLike replyLike);

    //查询
    boolean getReplyLike(Integer ReplyId);

    //删除
    ServerResponse deleteReplyLike(Integer ReplyLikeId);

    //删除（依据评论ID）
    ServerResponse deleteReplyLikeByReply(Integer replyId);
}
