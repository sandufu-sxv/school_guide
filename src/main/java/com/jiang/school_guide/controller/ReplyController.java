package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.Reply;
import com.jiang.school_guide.entity.form.Pagination;
import com.jiang.school_guide.service.IReplyService;
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
@RequestMapping("/school_guide/reply")
public class ReplyController {

    @Autowired
    private IReplyService iReplyService;
    
    @ApiOperation("添加评论")
    @PostMapping("/add")
    @Permission(roles = {Const.USER})
    public ServerResponse addReply(@RequestBody @NonNull Reply reply) {
        return iReplyService.addReply(reply);
    }
    
    @ApiOperation("获取所有一级评论(需依据地点id),点赞数排序")
    @GetMapping("/getOneByLikes")
    public ServerResponse getReplyByPlaceIdAndLikes(Pagination pagination) {
        return iReplyService.getReplyByPlaceIdAndLikes(pagination);
    }

    @ApiOperation("获取所有一级评论(需依据地点id),点赞数排序")
    @GetMapping("/getOneByTime")
    public ServerResponse getReplyByPlaceId(Pagination pagination) {
        return iReplyService.getReplyByPlaceIdAndTime(pagination);
    }

    @ApiOperation("获取二级评论(需一级评论id)")
    @GetMapping("/getTwo")
    public ServerResponse getReplyByOneId(Pagination pagination) {
        return iReplyService.getReplyByOneId(pagination);
    }

    @ApiOperation("获取被举报的评论（按被举报次数）")
    @GetMapping("/getByReports")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse getReplyByReports(Pagination pagination) {
        return iReplyService.getReplyByReports(pagination);
    }

    @ApiOperation("删除一级评论")
    @GetMapping("/deleteOne")
    @Permission(roles = {Const.ADMIN,Const.USER})
    public ServerResponse deleteReplyOne(Integer replyId) {
        return iReplyService.deleteReplyOne(replyId);
    }

    @ApiOperation("删除二级评论")
    @GetMapping("/deleteTwo")
    @Permission(roles = {Const.ADMIN,Const.USER})
    public ServerResponse deleteReplyTwo(Integer replyId) {
        return iReplyService.deleteReplyTwo(replyId);
    }

    @ApiOperation("更新评论（判断未违规时使用，需要将被举报数设置为0，状态设置为未举报（0））")
    @PostMapping("/update")
    @Permission(roles = {Const.ADMIN})
    public ServerResponse updateReply(@RequestBody @NonNull Reply reply) {
        return iReplyService.updateReply(reply);
    }


}
