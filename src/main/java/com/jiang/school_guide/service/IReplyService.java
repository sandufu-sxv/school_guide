package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.school_guide.entity.form.Pagination;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
public interface IReplyService extends IService<Reply> {
    //添加评论
    ServerResponse addReply(Reply reply);

    //获取所有一级评论(依据地点id)
    ServerResponse getReplyByPlaceId(Pagination pagination);

    //获取二级评论()
    ServerResponse getReplyByOneId(Pagination pagination);

    //删除一级评论
    ServerResponse deleteReplyOne(Integer replyId);

    //删除二级评论
    ServerResponse deleteReplyTwo(Integer replyId);

    //更新评论状态
    ServerResponse getReply(Integer replyId);

    //依据地点删除评论
    ServerResponse deleteReplyByPlace(Integer placeId);



}
