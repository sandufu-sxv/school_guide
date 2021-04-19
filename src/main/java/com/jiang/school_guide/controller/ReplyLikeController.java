package com.jiang.school_guide.controller;


import com.jiang.school_guide.common.annotation.Permission;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.ReplyLike;
import com.jiang.school_guide.service.IReplyLikeService;
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
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/school_guide/reply-like")
public class ReplyLikeController {
    
    @Autowired
    private IReplyLikeService iReplyLikeService;

    @ApiOperation("添加点赞信息")
    @PostMapping("/add")
    @Permission(roles = {Const.USER})
    public ServerResponse addReplyLike(@RequestBody @NonNull ReplyLike ReplyLike) {
        return iReplyLikeService.addReplyLike(ReplyLike);
    }

//    @ApiOperation("用户获取自己的点赞信息（无用）")
//    @GetMapping("/get")
//    @Permission(roles = {Const.USER})
//    public ServerResponse getReplyLike(Integer ReplyId) {
//        return iReplyLikeService.getReplyLike(ReplyId);
//    }

    @ApiOperation("取消点赞")
    @DeleteMapping("/delete")
    @Permission(roles = {Const.USER})
    public ServerResponse deleteReplyLike(Integer ReplyLikeId) {
        return iReplyLikeService.deleteReplyLike(ReplyLikeId);
    }
}
