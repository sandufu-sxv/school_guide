package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Feedback;
import com.jiang.school_guide.dao.FeedbackMapper;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    // TODO: 2021/4/7
    @Override
    public ServerResponse addFeedback(Feedback feedback) {
        int id = TokenUntil.getIdByToken();
        feedback.setUserId(id);
        feedback.setState(0);
        feedback.setCreateTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        if(feedbackMapper.insert(feedback) == 1){
            return ServerResponse.createByErrorMessage("添加反馈意见成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
    }

    @Override
    public ServerResponse getAllFeedback(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Feedback> feedbackList = feedbackMapper.selectList(null);
        PageInfo<Feedback> pageInfo= new PageInfo<>(feedbackList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getAllFeedbackByState(Pagination pagination) {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Feedback> feedbackList = feedbackMapper.selectList(queryWrapper);
        PageInfo<Feedback> pageInfo= new PageInfo<>(feedbackList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getFeedback(Pagination pagination) {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Feedback> feedbackList = feedbackMapper.selectList(queryWrapper);
        PageInfo<Feedback> pageInfo= new PageInfo<>(feedbackList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getFeedbackByState(Pagination pagination) {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id)
                .eq("state",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Feedback> feedbackList = feedbackMapper.selectList(queryWrapper);
        PageInfo<Feedback> pageInfo= new PageInfo<>(feedbackList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse updateFeedback(Feedback feedback) {
        int id = TokenUntil.getIdByToken();
        feedback.setState(1);
        feedback.setAdminId(id);
        feedback.setUpdateTime(LocalDateTime.now());
        UpdateWrapper<Feedback> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",feedback.getId());
        if(feedbackMapper.update(feedback,updateWrapper) == 1){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse deleteFeedback(Integer feedbackId) {
        if(feedbackMapper.deleteById(feedbackId) == 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }
}
