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

    //获取所有一级评论(需依据地点id),点赞数排序
    ServerResponse getReplyByPlaceIdAndLikes(Pagination pagination);

    //获取所有一级评论(需依据地点id),创建时间排序排序
    ServerResponse getReplyByPlaceIdAndTime(Pagination pagination);

    //获取二级评论
    ServerResponse getReplyByOneId(Pagination pagination);

    //获取被举报的评论（按被举报次数）
    ServerResponse getReplyByReports(Pagination pagination);

    //删除一级评论
    ServerResponse deleteReplyOne(Integer replyId);

    //删除二级评论
    ServerResponse deleteReplyTwo(Integer replyId);

    //获取单个评论
    ServerResponse getReply(Integer replyId);

    //更新评论信息
    ServerResponse updateReply(Reply reply);

    //依据地点删除评论
    ServerResponse deleteReplyByPlace(Integer placeId);

}
