package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Feedback;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IFeedbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/school_guide/feedback")
public class FeedbackController {
    
    @Autowired
    private IFeedbackService iFeedbackService;

    @ApiOperation("添加意见信息")
    @PostMapping("/add")
    @Permission(roles = {Const.USER})
    public ServerResponse addFeedback(@RequestBody @NonNull Feedback Feedback) {
        return iFeedbackService.addFeedback(Feedback);
    }

    @ApiOperation("用户获取自己的意见信息")
    @GetMapping("/get")
    @Permission(roles = {Const.USER})
    public ServerResponse getFeedback(Pagination pagination) {
        return iFeedbackService.getFeedback(pagination);
    }

    @ApiOperation("管理员获取所有未处理的意见信息")
    @GetMapping("/getAll")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse getAllFeedback(Pagination pagination) {
        return iFeedbackService.getAllFeedback(pagination);
    }

    @ApiOperation("用户更新自己的意见信息（无用，写着玩的）")
    @PostMapping("/update")
    @Permission(roles = {Const.USER})
    public ServerResponse addAdmin(@RequestBody @NonNull Feedback Feedback) {
        return iFeedbackService.updateFeedback(Feedback);
    }

    @ApiOperation("用户删除自己的意见信息")
    @DeleteMapping("/delete/")
    @Permission(roles = {Const.USER})
    public ServerResponse deleteFeedback(Integer id) {
        return iFeedbackService.deleteFeedback(id);
    }
}
