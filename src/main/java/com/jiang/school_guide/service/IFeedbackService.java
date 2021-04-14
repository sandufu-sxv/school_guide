package com.jiang.school_guide.service;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Feedback;
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
public interface IFeedbackService extends IService<Feedback> {
    //添加反馈意见
    ServerResponse addFeedback(Feedback feedback);

    //查询所有反馈意见（依据状态）
    ServerResponse getAllFeedback(Pagination pagination);

    //查询单个用户的反馈意见.
    ServerResponse getFeedback(Pagination pagination);

    //查询单个用户的反馈意见(依据状态).
    ServerResponse getFeedbackByState(Pagination pagination);

    //更新反馈意见
    ServerResponse updateFeedback(Feedback feedback);

    //删除反馈意见
    ServerResponse deleteFeedback(Integer feedbackId);

}
